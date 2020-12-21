package com.sping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileUploadController {

    @RequestMapping("/upload")
    public String fileUpload(HttpServletRequest request, MultipartFile file){
        System.out.println("文件上传");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        return "success";
    }

    @RequestMapping("/upload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile file) throws IOException {
        System.out.println("文件上传2");
        String path = "D:/uploadfile";
        File filePath = new File(path);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        file.transferTo(new File(path,fileName));
        return "success";
    }

}
