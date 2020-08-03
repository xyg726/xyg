package com.fh.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.common.ServerResponse;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper pm;

    @Override
    public ServerResponse queryImageboxList() {
        List<Product> list =  pm.queryImageboxList();
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse queryList() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        Page<Product> page = new Page<>();
        IPage<Product> iPage = pm.selectPage(page, queryWrapper);
        List<Product> productList = pm.selectList(queryWrapper);
        Map<String,Object> map = new HashMap();
        map.put("totalcount",iPage.getTotal());
        map.put("list",productList);
        return ServerResponse.success(map);
    }

    @Override
    public Product selectProductById(Integer productId) {
        Product product = pm.selectById(productId);
        return product;
    }

    @Override
    public Long updateStock(Integer id, Integer count) {
        return pm.updateStock(id,count);
    }
}
