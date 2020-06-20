package com.xwq.service.impl;

import com.xwq.dao.OrderItemDao;
import com.xwq.entity.OrderItem;
import com.xwq.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderItem)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    public OrderItem queryById(Integer id) {
        return null;
    }

    public List<OrderItem> queryAllByLimit(int offset, int limit) {
        return null;
    }

    public OrderItem insert(OrderItem orderItem) {
        return null;
    }

    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }
}