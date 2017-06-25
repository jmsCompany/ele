package qingyun.ele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.CustomerDevice;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerDeviceRepository;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSTableData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaohelong on 2017/6/25.
 */
@RestController
@Transactional(readOnly = true)
public class CustomerDeviceController {

    @Autowired
    private CustomerDeviceRepository customerDeviceRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 新建/编辑项目设备
     * @param customerDevice
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/project/saveDevice",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Valid saveCustomerDevice(@RequestBody CustomerDevice customerDevice){
        Valid v=new Valid();
        CustomerDevice dbCustomerDevice;
        if (customerDevice.getId()==null||customerDevice.getId().equals(0l)){
            dbCustomerDevice=new CustomerDevice();
            dbCustomerDevice.setStatus(1l);  //默认为有效1表示有效,0表示无效
        }else{
            dbCustomerDevice=customerDeviceRepository.findOne(customerDevice.getId());
        }
        dbCustomerDevice.setStatus(customerDevice.getStatus()==null?1:customerDevice.getStatus());
        dbCustomerDevice.setCreatedBy(securityUtils.getCurrentDBUser().getId());
        dbCustomerDevice.setDataloggerAlias(customerDevice.getDataloggerAlias());
        dbCustomerDevice.setInverterAlias(customerDevice.getInverterAlias());
        dbCustomerDevice.setDataloggerSn(customerDevice.getDataloggerSn());
        dbCustomerDevice.setIdCustomer(customerDevice.getIdCustomer());
        dbCustomerDevice.setInverterSn(customerDevice.getInverterSn());
        dbCustomerDevice.setInverterType(customerDevice.getInverterType());
        dbCustomerDevice.setLastUpdated(new Date());
        customerDeviceRepository.save(dbCustomerDevice);
        v.setValid(Boolean.TRUE);
        return v;
    }

    @RequestMapping(value = "/project/findCustomerDeviceById",method = RequestMethod.GET)
    public CustomerDevice getCustomerDeviceById(@RequestParam Long deviceId){
        CustomerDevice device = customerDeviceRepository.findOne(deviceId);
        return device;
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/project/deleteCustomerDevice",method = RequestMethod.POST)
    public Valid deleteCustomerDevice(@RequestParam Long deviceId){
        CustomerDevice customerDevice = customerDeviceRepository.findOne(deviceId);
        customerDevice.setStatus(0l);
        customerDeviceRepository.save(customerDevice);
        Valid v=new Valid();
        v.setValid(Boolean.TRUE);
        return v;
    }

    @RequestMapping(value = "/project/getCustomerDevices",method = RequestMethod.GET)
    public WSTableData getCustomerDevices(@RequestParam Integer start,@RequestParam Integer draw,@RequestParam Integer length){
        int page_num = (start.intValue() / length.intValue()) + 1;
        Pageable pageable = new PageRequest(page_num - 1, length);
        Users users = securityUtils.getCurrentDBUser();
        Customer customer = customerRepository.finByMobile(users.getMobile());
        Page<CustomerDevice> customerDevices = customerDeviceRepository.findByProjectId(customer.getId(),pageable);
        List<String[]> list=new ArrayList<>();
        for (CustomerDevice customerDevice:customerDevices){
            String[] fields={customerDevice.getDataloggerSn(),customerDevice.getDataloggerAlias(),customerDevice.getInverterSn(),
            customerDevice.getInverterType(),customerDevice.getStatus()+"",customerDevice.getLastUpdated()+""};
        }
        WSTableData t = new WSTableData();
        t.setDraw(draw);
        t.setRecordsTotal((int) customerDevices.getTotalElements());
        t.setRecordsFiltered((int) customerDevices.getTotalElements());
        t.setData(list);
        return t;
    }
}
