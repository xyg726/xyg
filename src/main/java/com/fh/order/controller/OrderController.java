package com.fh.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.member.model.User;
import com.fh.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService os;

    @RequestMapping("buildOrder")
    public ServerResponse buildOrder(String listStr, Integer payType, Integer addressId, User user){
        List<Cart> cartList = new ArrayList<>();
        if(StringUtils.isNotEmpty(listStr)){
            cartList = JSONObject.parseArray(listStr,Cart.class);
        }else {
            return ServerResponse.error("请选择商品");
        }
        return os.buildOrder(cartList,payType,addressId,user);
    }




}
