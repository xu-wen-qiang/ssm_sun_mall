package com.xwq.service.impl;

import com.xwq.dao.CartDao;
import com.xwq.entity.Cart;
import com.xwq.entity.Order;
import com.xwq.dao.OrderDao;
import com.xwq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("orderService")

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    CartDao cartDao;
    @Override
    public Order queryById(Integer id) {
        return null;
    }

    @Override
    public List<Order> queryAllByLimit(int offset, int limit) {
        return orderDao.queryAllByLimit(offset,limit);
    }

    @Override
    public List<Order> queryAll(Order order) {
        return orderDao.queryAll(order);
    }

    @Override
    public int insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public int insertList(String cartIds, String address) {
        List<Order> orderList = new ArrayList<Order>();
        Integer id = null;
        String[] idStrings = cartIds.split(",");
        //dao层接受的全部id数据,pIdList
        int[] cid = new int[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            id = Integer.valueOf(idStrings[i]);
            //得到一个id
            cid[i] = id;
            Cart cart = cartDao.queryOneById(id);

            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setProductId(cart.getProductId());
            order.setUserId(cart.getUserId());
            order.setQuantity(cart.getQuantity());
            order.setAddress(address);
            order.getPayment(cart.getProduct().getPrice()*cart.getQuantity());
            orderList.add(order);
        }
        return orderDao.insertList(orderList);
    }


    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int deleteById(Integer id) {
        return orderDao.deleteById(id);
    }
}