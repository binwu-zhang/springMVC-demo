package com.atguigu.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FileUpAndDownController {

    @RequestMapping("/testDown")
    @ResponseBody
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建输入流
        InputStream inputStream = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[inputStream.available()];
        //将流读到字节数组中
        int read = inputStream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statsCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, statsCode);
        //关闭输入流
        inputStream.close();
        return responseEntity;

    }


    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        String originalFileName = photo.getOriginalFilename();

        //后缀
        String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid= UUID.randomUUID().toString();
        String fileName = uuid+suffixName;

        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        //判断路径是否存在
        if(!file.exists()){
            boolean mkdir = file.mkdir();
        }
        String finalPath = photoPath+File.separator+fileName;
        photo.transferTo(new File(finalPath));
        return "success";
    }
}