package com.xwq.entity;

import java.io.Serializable;

/**
 * (Cart)实体类
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = -59996795661409282L;

    private Integer id;

    private Integer userId;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 数量
     */
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}