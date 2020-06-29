package com.jxkj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

//@RestController
@Controller
public class UploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {
        //req.getSession().getServletContext().getRealPath("/uploadFile/");
        String realPath = "D://temp";
        System.out.println("真实路径：=====》" + realPath);
        //String format = sdf.format(new Date());
        File folder = new File(realPath);
        System.out.println("文件目录：=====》" + folder);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();

        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());

        try{
            uploadFile.transferTo(new File(folder,newName));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/"+ newName;
            return filePath;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败！";
    }
}
