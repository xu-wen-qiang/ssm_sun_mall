package com.xwq.entity;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-06-15 14:50:14
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -20385111773061461L;
    /**
     * 类别Id
     */
    private Integer id;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别状态1-正常,2-已废弃
     */
    private Object status;


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

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}