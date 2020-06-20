package com.xwq.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (PayInfo)实体类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public class PayInfo implements Serializable {
    private static final long serialVersionUID = -36588576336419965L;

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
     * 支付平台:1-支付宝,2-微信
     */
    private Integer payPlatform;
    /**
     * 支付宝支付流水号
     */
    private String platformNumber;
    /**
     * 创建时间
     */
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

    public Integer getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(Integer payPlatform) {
        this.payPlatform = payPlatform;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}