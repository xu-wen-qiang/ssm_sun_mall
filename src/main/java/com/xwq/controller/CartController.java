package com.xwq.controller;

import com.mysql.cj.Session;
import com.xwq.entity.Cart;
import com.xwq.service.CartService;
import com.xwq.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (Cart)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param cart 实例对象
     * @return 对象列表
     */
    @RequestMapping("queryAll")
    @ResponseBody
    ErrorMsg<List<Cart>> queryAll(Cart cart) {
        if (cart == null || cart.equals("")) {
            return ErrorMsg.OTHER_SYSTEM_ERROR;
        }
        return ErrorMsg.SUCCESS.setNewData(cartService.queryAll(cart));
    }
    @RequestMapping("queryList")
    @ResponseBody
    ErrorMsg queryList(String ids , Integer uid , HttpSession session){
        session.setAttribute("ids",ids);
        return ErrorMsg.SUCCESS.setNewData(cartService.queryList(ids,uid));
    }
    //测试session
    @RequestMapping("ids")
    @ResponseBody
    String session(HttpSession session){
        System.out.println("ids*****************************"+session.getAttribute("ids").toString());
        System.out.println("user*****************************"+session.getAttribute("user").toString());
       return session.getAttribute("ids").toString();
    }
    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    @RequestMapping("insert")
    @ResponseBody
    ErrorMsg<Integer> insert(Cart cart) {
        if (cart == null || cart.equals("")) {
            return ErrorMsg.INSERT_ERROR;
        }
        Cart myCart = cartService.queryOne(cart);
        if (myCart != null) {
            System.out.println(cart.getQuantity() + "========");
            System.out.println("=========================" + myCart.getQuantity());
            cart.setQuantity(cart.getQuantity() + myCart.getQuantity());
            cart.setId(myCart.getId());
            return ErrorMsg.UPDATE_SUCCESS.setNewData(cartService.update(cart));
        }

        return ErrorMsg.INSERT_SUCCESS.setNewData(cartService.insert(cart));
    }

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    @RequestMapping("update")
    @ResponseBody
    ErrorMsg<Integer> update(Cart cart) {
        if (cart == null || cart.equals("")) {
            return ErrorMsg.UPDATE_ERROR;
        }
        return ErrorMsg.UPDATE_SUCCESS.setNewData(cartService.update(cart));
    }

    ;


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @RequestMapping("deleteById")
    @ResponseBody
    ErrorMsg<Integer> deleteById(Integer id) {
        if (id == null || id.equals("")) {
            return ErrorMsg.DELETE_ERROR;
        }
        return ErrorMsg.DELETE_SUCCESS.setNewData(cartService.deleteById(id));
    }

    ;

    @RequestMapping("deleteList")
    @ResponseBody
    public ErrorMsg deleteById(String ids, Integer uid) {

        System.out.println(uid + "============================================================\n" +
                "============================================================\n");
        return cartService.deleteList(ids, uid);
    }
}