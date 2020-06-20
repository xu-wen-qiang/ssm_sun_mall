package com.xwq.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 516755168937216116L;
    /**
     * 订单子表id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单号
     */
    private Long orderNo;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片地址
     */
    private String productImage;
    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    private Double currentUnitPrice;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 商品总价,单位是元,保留两位小数
     */
    private Double totalPrice;

    private Date createTime;


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

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Double getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(Double currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}