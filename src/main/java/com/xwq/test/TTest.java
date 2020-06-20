package com.xwq.test;

import com.xwq.util.Md5Util;

import static org.junit.Assert.*;

public class TTest {

    @org.junit.Test
    public void main() {
        String str = "admin";//c649d6185032697ada52a13e7ea75bf3
        String strMd5 = Md5Util.getMD5(str);
        System.out.println(strMd5);
    }
}