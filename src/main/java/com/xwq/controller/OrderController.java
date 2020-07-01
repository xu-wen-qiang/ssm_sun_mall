package com.xwq.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.xwq.entity.Cart;
import com.xwq.entity.Order;
import com.xwq.service.CartService;
import com.xwq.service.OrderService;
import com.xwq.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Controller
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    private Order order = new Order();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping("queryById")
    @ResponseBody
    ErrorMsg queryById(Integer id) {
        return ErrorMsg.SUCCESS.setNewData(orderService.queryById(id));
    }

    /**
     * @param uid
     * @return
     */
    @RequestMapping("queryByUId")
    @ResponseBody
    public ErrorMsg queryByUId(Integer uid) {
        if (uid != null){
            return ErrorMsg.SUCCESS.setNewData(orderService.queryByUId(uid));
        }
        return ErrorMsg.ARGS_ERROR;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @RequestMapping("queryAllByLimit")
    @ResponseBody
    ErrorMsg queryAllByLimit(int offset, int limit) {
        return ErrorMsg.SUCCESS.setNewData(orderService.queryAllByLimit(offset, limit));
    }


    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    @RequestMapping("queryAll")
    @ResponseBody
    ErrorMsg queryAll(Order order) {
        return ErrorMsg.SUCCESS.setNewData(orderService.queryAll(order));
    }

    /**
     * 新增数据
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("insert")
    @ResponseBody
    ErrorMsg insert(String cartIds, String address, HttpSession session) {

        return ErrorMsg.INSERT_SUCCESS.setNewData(orderService.insertList(cartIds, address));
    }


    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @RequestMapping("update")
    @ResponseBody
    ErrorMsg update(Order order) {
        return ErrorMsg.UPDATE_SUCCESS.setNewData(orderService.update(order));
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @RequestMapping("deleteById")
    @ResponseBody
    ErrorMsg deleteById(Integer id) {
        return ErrorMsg.DELETE_SUCCESS.setNewData(orderService.deleteById(id));
    }

    /**
     * 支付功能
     *
     * @param httpResponse
     * @param request
     * @param session
     * @throws IOException
     */
    @RequestMapping("/alipay")
    public void alipay(HttpServletResponse httpResponse, HttpServletRequest request, HttpSession session, Integer cartId, String address ,String username ,String phone) throws IOException {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String WIDsubject = "☀ sun_mall";
        String WIDbody = "购买界面";

        Cart cart = cartService.queryOneById(cartId);
        order.setUserId(cart.getUserId());
        order.setAddress(address);
        order.setUsername(username);
        order.setPhone(phone);
        order.setProductId(cart.getProductId());
        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = new String(request.getParameter("ids").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = UUID.randomUUID().toString();
        order.setOrderNo(out_trade_no);
        //付款金额，必填
//        String total_amount = new String(request.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
        String total_amount = cart.getProduct().getPrice() * +(cart.getQuantity()) + "";
        order.setPayment(new Double(total_amount));
        order.setQuantity(cart.getQuantity());
        //订单名称，必填
        String subject = WIDsubject;
        //商品描述，可空
        String body = WIDbody;

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = "";
        try {
            // 调用SDK生成表单
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(result);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    /**
     * 注意异步返回结果通知是以post请求形式返回的
     *
     * @param request
     * @return
     */
    @RequestMapping("notifyUrl")
    public String notify_url(HttpServletRequest request) {
//
        System.out.println("执行了notifyUrl");
//        Map<String, String> paramsMap = convertRequestParamsToMap(request);
//        String tradeStatus= paramsMap.get("trade_status");
//        try {
//            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
//            //无论同步异步都要验证签名
//            if(signVerified){
//                if(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")){
//                    //处理自己系统的业务逻辑，如：将支付记录状态改为成功，需要返回一个字符串success告知支付宝服务器
//                    System.out.println("System.out.println(\"===========================\"+order.toString());");
//                    if (order != null) {
//                        System.out.println("===========================" + order.toString());
//                        orderService.insert(order);
//                    }
//
//                    System.err.println("System.out.println(\"===========================\"+order.toString())success");
//                    return "redirect:../index";
//                } else {
//                    //支付失败不处理业务逻辑
//                    System.err.println("System.out.println(\"===========================\"+order.toString())error");
//                    return "myCart";
//                }
//            }else {
//                //签名验证失败不处理业务逻辑
//                return "myCart";
//            }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//            return "myCart";
//        }
        return "执行了notifyUrl";
    }

    //注意同步返回结果是以get请求形式返回的
    @RequestMapping("returnUrl")
    public String return_url(HttpServletRequest request, HttpSession session) {
        Map<String, String> paramsMap = convertRequestParamsToMap(request);
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            if (signVerified) {
                if (order != null) {
                        System.out.println("===========================" + order.toString());
                        orderService.insert(order);
                }
            } else {
                //跳转支付失败界面
                System.out.println("System.out.println(\"===========================\"+order.toString())error");
                return "myCart";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "redirect:../index.html";
    }


    //将请求中的参数转换为Map
    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        Iterator var3 = entrySet.iterator();

        while (true) {
            while (var3.hasNext()) {
                Map.Entry<String, String[]> entry = (Map.Entry) var3.next();
                String name = (String) entry.getKey();
                String[] values = (String[]) entry.getValue();
                int valLen = values.length;
                if (valLen == 1) {
                    retMap.put(name, values[0]);
                } else if (valLen <= 1) {
                    retMap.put(name, "");
                } else {
                    StringBuilder sb = new StringBuilder();
                    String[] var9 = values;
                    int var10 = values.length;

                    for (int var11 = 0; var11 < var10; ++var11) {
                        String val = var9[var11];
                        sb.append(",").append(val);
                    }

                    retMap.put(name, sb.toString().substring(1));
                }
            }

            return retMap;
        }
    }

    //将字符串转换为UTF-8编码以防出现乱码错误
    public static String getUTF8XMLString(String xml) {
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }
}