package com.xwq.service.impl;


import com.xwq.dao.CartDao;
import com.xwq.entity.Cart;
import com.xwq.service.CartService;
import com.xwq.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CartDao cartDao;

    public List<Cart> queryAll(Cart cart) {
        return cartDao.queryAll(cart);
    }

    public Cart queryOne(Cart cart) {
        return cartDao.queryOne(cart);
    }

    public Cart queryById(Integer cid, Integer uid) {
        return cartDao.queryById(cid, uid);
    }

    public int insert(Cart cart) {
        return cartDao.insert(cart);
    }

    public int update(Cart cart) {
        return cartDao.update(cart);
    }

    public int deleteById(Integer id) {
        return cartDao.deleteById(id);
    }

    public ErrorMsg deleteList(String ids, Integer uid) {
        Integer id = null;
        String[] idStrings = ids.split(",");
        //dao层接受的全部id数据,pIdList
        int[] pIdList = new int[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            id = Integer.valueOf(idStrings[i]);
            //得到一个id
            pIdList[i] = id;
            //调用dao层方法,传入yybIdList参数
            cartDao.deleteList(pIdList, uid);
            //查询传入的最后一个id,如果为空,说明删除成功,返回ok;如果不为空,说明删除失败,返回任意字符串;
            Cart cart = cartDao.queryById(id, uid);
            if (cart != null) {
                return ErrorMsg.DELETE_ERROR;
            }
        }
        return ErrorMsg.DELETE_SUCCESS;
    }
}