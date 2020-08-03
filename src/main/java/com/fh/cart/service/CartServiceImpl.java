package com.fh.cart.service;

import com.alibaba.fastjson.JSONObject;
import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.member.model.User;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductService ps;

    @Override
    public ServerResponse buy(Integer productId, Integer count, HttpServletRequest request) {
        Product product = ps.selectProductById(productId);
        if(product == null){
            return ServerResponse.error("商品不存在或者已下架");
        }
        if(product.getIsUp() == 1){
            return ServerResponse.error("商品已下架");
        }
        User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        boolean exist = RedisUtil.exists(SystemConstant.CART_KEY+user.getId(),productId.toString());
        if(!exist){
            Cart cart = new Cart();
            cart.setCount(count);
            cart.setProductId(productId);
            cart.setName(product.getName());
            cart.setImgUrl(product.getImgUrl());
            cart.setPrice(product.getPrice());
            String jsonString = JSONObject.toJSONString(cart);
            RedisUtil.hset(SystemConstant.CART_KEY+user.getId(),productId.toString(),jsonString);
        }else {
            String productJson =  RedisUtil.hget(SystemConstant.CART_KEY + user.getId(),productId.toString());
            Cart cart = JSONObject.parseObject(productJson,Cart.class);
            cart.setCount(cart.getCount()+count);
            String jsonString = JSONObject.toJSONString(cart);
            RedisUtil.hset(SystemConstant.CART_KEY + user.getId(),productId.toString(),jsonString);
        }
        return ServerResponse.success();
    }
}
