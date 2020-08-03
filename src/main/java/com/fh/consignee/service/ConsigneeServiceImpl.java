package com.fh.consignee.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ServerResponse;
import com.fh.consignee.mapper.ConsigneeMapper;
import com.fh.consignee.model.Consignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsigneeServiceImpl implements ConsigneeService {
    @Autowired
    private ConsigneeMapper cm;

    @Override
    public ServerResponse queryConsignee() {
        QueryWrapper<Consignee> queryWrapper = new QueryWrapper<>();
        List<Consignee> consigneeList = cm.selectList(queryWrapper);
        Map map = new HashMap();
        map.put("list",consigneeList);
        return ServerResponse.success(map);
    }

    @Override
    public ServerResponse addConsignee(Consignee consignee) {
        cm.insert(consignee);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteConsignee(Integer id) {
        cm.deleteById(id);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updateConsignee(Consignee consignee) {
        cm.updateById(consignee);
        return ServerResponse.success();
    }
}
