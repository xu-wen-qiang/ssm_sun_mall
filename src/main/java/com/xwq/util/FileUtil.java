package com.xwq.util;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static String upLoadFile(String filePath, MultipartFile upload) {

        String fileName = upload.getOriginalFilename();  //获取上传文件的名字
        // 为了防止上传同名图片导致覆盖文件，引入随机数UUID解决。
        String newname = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        System.out.println("新文件名是:" + newname);
        // 创建文件流对象picfile
        File file = new File("D:\\JAVA\\upload", newname);
        System.out.println("文件流为：" + file);
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            upload.transferTo(file);
        } catch (Exception e) {
            System.out.println("==============================" + e);
            e.fillInStackTrace();

        }
        System.out.println("====================================文件上传成功");
        return newname;
    }
}