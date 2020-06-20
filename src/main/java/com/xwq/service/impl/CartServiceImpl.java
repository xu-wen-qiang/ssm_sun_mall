package com.xwq.service.impl;


import com.xwq.dao.CartDao;
import com.xwq.entity.Cart;
import com.xwq.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Cart)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    public Cart queryById(Integer id) {
        return null;
    }

    public List<Cart> queryAllByLimit(int offset, int limit) {
        return null;
    }

    public Cart insert(Cart cart) {
        return null;
    }

    public Cart update(Cart cart) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }
}