package com.xwq.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xwq.entity.Product;
import com.xwq.service.ProductService;
import com.xwq.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Controller
@RequestMapping("product")
public class ProductController {

    /**
     * 服务对象
     */
    @Autowired
    private ProductService productService;

    /**
     * 时间类型转换
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    //分页查询数据
    @RequestMapping("/queryProduct")
    public @ResponseBody
    PageInfo<Product> queryProduct(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

        //1.引入分页插件,pn是第几页，5是每页显示多少行
        PageHelper.startPage(pn, 5);
        //2.紧跟的查询就是一个分页查询
        List<Product> list = productService.findAll();
        System.out.println("=================================================================" + list.toString());
        //3.使用PageInfo包装查询后的结果,5是连续显示的条数
        PageInfo<Product> pageInfo = new PageInfo<Product>(list, 5);
        //4.使用model设置到前端
        model.addAttribute("pageInfo", pageInfo);
        //5.最后设置返回的jsp
//        return "showProduct";
        return pageInfo;
    }

    /**
     * 添加产品
     *
     * @param product            产品
     * @param multiple_mainImage 主图
     * @param multiple_subImages 副图
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/addProduct")
    public String addProduct(Product product, MultipartFile multiple_mainImage, MultipartFile multiple_subImages, HttpServletRequest request, HttpServletResponse response) {
        //设置图片上传的路径
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println("=================================图片上传");
        product.setMainImage(FileUtil.upLoadFile(path, multiple_mainImage));
        product.setSubImages(FileUtil.upLoadFile(path, multiple_subImages));
        // 调用添加方法
        productService.add(product);
        // 内部转发，重新加载商品列表
//        return "redirect:queryProduct";
        return "showProduct";
    }

    //删除商品
    @RequestMapping("/del")
    public String del(int id) {
        productService.del(id);
        return "redirect:queryProduct";
    }

    //查询单条记录
    @RequestMapping("/findOne")
    public @ResponseBody
    Product findOne(int id) {
        Product product = productService.findOne(id);
//        model.addAttribute("product", product);
        //返给更新的方法
        return product;
    }

    //修改数据
    @RequestMapping("/upd")
    public String upd(Product Product, MultipartFile mainImage, MultipartFile subImages, HttpServletRequest request) throws IllegalStateException, IOException {

        //拿到单条数据
//        Product.setMainImage(productService.findOne(Product.getId()).getMainImage());
        // 拿到该条数据的图片路径和名字
        String path = request.getServletContext().getRealPath("/upload");

//        主图修改以后做新判断
        if (mainImage != null) {
            Product.setMainImage(FileUtil.upLoadFile(path, mainImage));
        }
//        副图修改以后做新判断
        if (subImages != null) {
            Product.setMainImage(FileUtil.upLoadFile(path, subImages));
        }
        //修改完成以后调用更新方法
        productService.upd(Product);
        // 内部转发，重新加载商品列表
        return "redirect:queryProduct";
    }

    //分页查询数据
    @RequestMapping("/queryProductByCid")
    public @ResponseBody
    PageInfo<Product> queryProducByCid(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, int cid) {

        //1.引入分页插件,pn是第几页，5是每页显示多少行
        PageHelper.startPage(pn, 5);
        //2.紧跟的查询就是一个分页查询
        List<Product> list = productService.findProductByCid(cid);
        System.out.println("=================================================================" + list.toString());
        //3.使用PageInfo包装查询后的结果,5是连续显示的条数
        PageInfo<Product> pageInfo = new PageInfo<Product>(list, 5);
        //4.使用model设置到前端
//        model.addAttribute("pageInfo",pageInfo);
        //5.最后设置返回的jsp
//        return "showProduct";
        return pageInfo;
    }
}