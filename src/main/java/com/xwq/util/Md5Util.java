package com.xwq.util;

import org.springframework.util.DigestUtils;


/**
 * MD5工具类
 *
 * @author pibigstar
 */
public class Md5Util {
    //用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";

    /**
     * 生成md5
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}



