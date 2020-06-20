package com.xwq.service.impl;

import com.xwq.entity.Shipping;
import com.xwq.dao.ShippingDao;
import com.xwq.service.ShippingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Shipping)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

    public Shipping queryById(Integer id) {
        return null;
    }

    public List<Shipping> queryAllByLimit(int offset, int limit) {
        return null;
    }

    public Shipping insert(Shipping shipping) {
        return null;
    }

    public Shipping update(Shipping shipping) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }
}