package com.fh.consignee.controller;

import com.fh.common.ServerResponse;
import com.fh.consignee.model.Consignee;
import com.fh.consignee.service.ConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("con")
public class ConsigneeController {

    @Autowired
    private ConsigneeService cs;

    @RequestMapping("queryConsignee")
    public ServerResponse queryConsignee(){
        return cs.queryConsignee();
    }
    @RequestMapping("addConsignee")
    public ServerResponse addConsignee(Consignee consignee){
        return cs.addConsignee(consignee);
    }
    @RequestMapping("deleteConsignee/{id}")
    public ServerResponse deleteConsignee(@PathVariable("id")Integer id){
        return cs.deleteConsignee(id);
    }
    @RequestMapping("updateConsignee")
    public ServerResponse updateConsignee(Consignee consignee){
        return cs.updateConsignee(consignee);
    }

}
