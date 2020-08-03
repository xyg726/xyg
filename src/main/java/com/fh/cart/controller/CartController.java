package com.fh.cart.controller;

import com.alibaba.fastjson.JSONObject;
import com.fh.cart.model.Cart;
import com.fh.cart.service.CartService;
import com.fh.common.ServerResponse;
import com.fh.member.model.User;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cs;
    @RequestMapping("buy")
    public ServerResponse buy(Integer productId, Integer count, HttpServletRequest request){
        return cs.buy(productId,count,request);
    }

    @RequestMapping("queryCartProductCount")
    public ServerResponse queryCartProductCount(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        List<String> stringList = RedisUtil.hget(SystemConstant.CART_KEY + user.getId());
        long totalCount = 0;
        if(stringList != null && stringList.size()>0){
            for(String str: stringList){
                Cart cart = JSONObject.parseObject(str, Cart.class);
                totalCount += cart.getCount();
            }
        }else {
            return ServerResponse.success(0);
        }
        return ServerResponse.success(totalCount);
    }

    @RequestMapping("queryCartProductList")
    public ServerResponse queryCartProductList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        List<String> strList = RedisUtil.hget(SystemConstant.CART_KEY + user.getId());
        List<Cart> cartList = new ArrayList<>();
        if(strList != null && strList.size()>0){
            for(String str: strList){
                Cart cart = JSONObject.parseObject(str, Cart.class);
                cartList.add(cart);
            }
        }else {
            return ServerResponse.error("购物车为空");
        }
        Map map = new HashMap();
        map.put("list",cartList);
        return ServerResponse.success(map);
    }
    @RequestMapping("deleteCart/{productId}")
    public ServerResponse deleteCart(HttpServletRequest request, @PathVariable("productId") Integer productId){
        User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
       RedisUtil.del(SystemConstant.CART_KEY + user.getId(),productId.toString());
        return ServerResponse.success();
    }

    @RequestMapping("deleteBatchCart")
    public ServerResponse deleteBatchCart(HttpServletRequest request, @RequestParam("idList") List<Integer> idList){
        User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        for (int i = 0; i < idList.size(); i++) {
            RedisUtil.del(SystemConstant.CART_KEY + user.getId(),idList.get(i).toString());
        }
        return ServerResponse.success();
    }





}
