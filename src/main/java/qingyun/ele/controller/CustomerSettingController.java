package qingyun.ele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSTableData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaohelong on 2017/6/25.
 */
@RestController
@Transactional(readOnly = true)
public class CustomerSettingController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DicRepository dicRepository;

    /**
     * 已结束项目表格
     * @param start
     * @param draw
     * @param length
     * @return
     */
    @RequestMapping(value = "/sys/customer/settingTable",method = RequestMethod.POST)
    public WSTableData getCustomerSettings(@RequestParam Integer start,@RequestParam Integer draw,
                                           @RequestParam Integer length){
        int page_num = (start.intValue() / length.intValue()) + 1;
        Pageable pageable = new PageRequest(page_num - 1, length);
        Users sessionUser = securityUtils.getCurrentDBUser();
        Page<Customer> customers = customerRepository.findAllCustomersByRoleIdAndStatus(sessionUser.getId(), 8l, pageable);
        List<String[]> list=new ArrayList<>();
        for (Customer customer:customers){
            Users users = usersRepository.findByUsername(customer.getMobile());
            String status=(users!=null)?"1":"0";
            String[] fields={customer.getName(),customer.getMobile(),customer.getProject(),
                    customer.getAlertEmail(),status,customer.getId()+"",customer.getId()+"",customer.getId()+"",customer.getId()+""};
            list.add(fields);
        }
        WSTableData t = new WSTableData();
        t.setDraw(draw);
        t.setRecordsTotal((int) customers.getTotalElements());
        t.setRecordsFiltered((int) customers.getTotalElements());
        t.setData(list);
        return t;
    }

    /**
     * 开通账户
     * @param projId 项目ID
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/customer/openAccount",method = RequestMethod.POST)
    public Valid openAccount(@RequestParam Long projId){
        Valid v=new Valid();
        Customer customer = customerRepository.findOne(projId);
        Users users = usersRepository.findByUsername(customer.getMobile());
        if (users!=null){
            v.setValid(Boolean.FALSE);
            v.setMsg("该用户已开通账户,不能重复开通");
            return v;
        }
        users=new Users();
        users.setName(customer.getName());
        users.setUsername(customer.getMobile());
        users.setPassword(new BCryptPasswordEncoder().encode(customer.getMobile()));
        users.setEmail(customer.getAlertEmail());
        users.setEnabled(1l);
        users.setMobile(customer.getMobile());
        Dic dic = dicRepository.findOne(44l);
        users.setDicByRole(dic);
        usersRepository.save(users);

        v.setValid(Boolean.TRUE);
        return v;
    }

    /**
     * 根据项目ID修改对应用户的密码
     * @param projId 项目ID
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/customer/updateUsersPasswordByProjectId",method = RequestMethod.POST)
    public Valid updateUsersPasswordByProjectId(@RequestParam Long projId,@RequestParam String password){
        Valid v=new Valid();
        Customer customer = customerRepository.findOne(projId);
        Users users = usersRepository.findByUsername(customer.getMobile());
        if (users==null){
            v.setValid(Boolean.FALSE);
            v.setMsg("用户尚未开通账户");
            return v;
        }
        users.setPassword(new BCryptPasswordEncoder().encode(password));
        usersRepository.save(users);
        v.setValid(Boolean.TRUE);
        return v;
    }

    /**
     * 关闭账户
     * @param projId
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/customer/disabledUsersAccount",method = RequestMethod.POST)
    public Valid disabledUsersAccount(@RequestParam Long projId){
        Valid v=new Valid();
        Customer customer = customerRepository.findOne(projId);
        Users users = usersRepository.findByUsername(customer.getMobile());
        if (users==null){
            v.setValid(Boolean.FALSE);
            v.setMsg("用户尚未开通账户");
            return v;
        }
        users.setEnabled(0l);
        usersRepository.save(users);
        v.setValid(Boolean.TRUE);
        return v;
    }

    /**
     * 修改用户邮箱
     * @param projId
     * @param email
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/customer/updateUsersEmail",method = RequestMethod.POST)
    public Valid updateUsersEmail(@RequestParam Long projId,@RequestParam String email){
        Valid v=new Valid();
        Customer customer = customerRepository.findOne(projId);
        Users users = usersRepository.findByUsername(customer.getMobile());
        if (users==null){
            v.setValid(Boolean.FALSE);
            v.setMsg("用户尚未开通账户");
            return v;
        }
        customer.setAlertEmail(email);
        customerRepository.save(customer);
        users.setEmail(email);
        usersRepository.save(users);
        v.setValid(Boolean.TRUE);
        return v;
    }
}
