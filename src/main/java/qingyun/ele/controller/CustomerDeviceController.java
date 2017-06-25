package qingyun.ele.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.CustomerDevice;
import qingyun.ele.repository.CustomerDeviceRepository;
import qingyun.ele.ws.Valid;

import java.util.Date;

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
}
