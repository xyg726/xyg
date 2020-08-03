package com.fh.consignee.service;

import com.fh.common.ServerResponse;
import com.fh.consignee.model.Consignee;

public interface ConsigneeService {
    ServerResponse queryConsignee();

    ServerResponse addConsignee(Consignee consignee);

    ServerResponse deleteConsignee(Integer id);

    ServerResponse updateConsignee(Consignee consignee);
}
