package com.fh.order.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
@TableName("t_indent")
public class Indent {

    private long id;

    private String orderid;

    private String name;

    private String filepath;

    private Integer commid;

    private long count;

    private BigDecimal price;

    private BigDecimal subtotalprice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotalprice() {
        return subtotalprice;
    }

    public void setSubtotalprice(BigDecimal subtotalprice) {
        this.subtotalprice = subtotalprice;
    }
}
