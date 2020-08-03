package com.fh.product.service;

import com.fh.common.ServerResponse;
import com.fh.product.model.Product;

public interface ProductService {
    ServerResponse queryImageboxList();

    ServerResponse queryList();

    Product selectProductById(Integer productId);

    Long updateStock(Integer id, Integer count);
}
