package com.xwq.service.impl;

import com.xwq.entity.Order;
import com.xwq.dao.OrderDao;
import com.xwq.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    public Order queryById(Integer id) {
        return null;
    }

    public List<Order> queryAllByLimit(int offset, int limit) {
        return null;
    }

    public Order insert(Order order) {
        return null;
    }

    public Order update(Order order) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }
}