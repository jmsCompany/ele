package qingyun.ele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import qingyun.ele.domain.db.InverterAlert;
import qingyun.ele.repository.InverterAlertRepostory;
import qingyun.ele.ws.Valid;

/**
 * Created by zhaohelong on 2017/6/25.
 */
@RestController
@Transactional(readOnly = true)
public class InverterAlertController {

    @Autowired
    private InverterAlertRepostory inverterAlertRepostory;

    /**
     * 新增/编辑
     * @param inverterAlert
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/saveInverterAlert",method = RequestMethod.POST)
    public Valid saveInverterAlert(@RequestBody InverterAlert inverterAlert){
        Valid v=new Valid();
        InverterAlert dbInverterAlert;
        if (inverterAlert.getId()==null||inverterAlert.getId().equals(0l)){
            dbInverterAlert=new InverterAlert();
            dbInverterAlert.setStatus(1l);
        }else{
            dbInverterAlert=inverterAlertRepostory.findOne(inverterAlert.getId());
        }
        dbInverterAlert.setInfo(inverterAlert.getCode());
        dbInverterAlert.setCode(inverterAlert.getCode());
        dbInverterAlert.setInverterSn(inverterAlert.getInverterSn());
        dbInverterAlert.setSs(inverterAlert.getSs());
        dbInverterAlert.setEnd(inverterAlert.getEnd());
        inverterAlertRepostory.save(dbInverterAlert);
        v.setValid(Boolean.TRUE);
        return v;
    }


    @RequestMapping(value = "/sys/getInverterById",method = RequestMethod.GET)
    public InverterAlert getInverterAlertById(@RequestParam Long inverId){
        InverterAlert inverterAlert = inverterAlertRepostory.findOne(inverId);
        return inverterAlert;
    }

    /**
     * 删除
     * @param inverId
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping(value = "/sys/deleteInverterAlterById",method = RequestMethod.POST)
    public Valid deleteInverterAlertById(@RequestParam Long inverId){
        Valid v=new Valid();
        InverterAlert inverterAlert = inverterAlertRepostory.findOne(inverId);
        inverterAlert.setStatus(0l);
        inverterAlertRepostory.save(inverterAlert);
        v.setValid(Boolean.TRUE);
        return v;
    }
}
