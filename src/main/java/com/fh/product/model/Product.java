package com.fh.product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@TableName("t_product")
public class Product {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    private Integer isUp;
    private String imgUrl;
    private BigDecimal price;
    /*private Integer brandId;
    private String brandName;*/
    private Integer categorg1;
    private Integer categorg2;
    private Integer categorg3;

    private Integer isHot;

    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getCategorg1() {
        return categorg1;
    }

    public void setCategorg1(Integer categorg1) {
        this.categorg1 = categorg1;
    }

    public Integer getCategorg2() {
        return categorg2;
    }

    public void setCategorg2(Integer categorg2) {
        this.categorg2 = categorg2;
    }

    public Integer getCategorg3() {
        return categorg3;
    }

    public void setCategorg3(Integer categorg3) {
        this.categorg3 = categorg3;
    }

  /*  public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }*/

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
