package com.yyh.web.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.util.UUID;

@RestController
public class TestController {

    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        //指定本地文件夹存储图片
        String savePath = staticPath + File.separator + "images" + File.separator + fileName;
        try {
            //将图片保存到static/images文件夹里
            fileUpload.transferTo(new File(savePath));
            return "success to upload";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail to upload";
        }
    }
}
