package com.wg.basics.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 逻辑流程：
 *  1.规划文件上传的保存路径以及以日期的方式来区分上传文件
 *  2.上传文件命名（使其不要重复）
 *  3.保存上传文件
 *  4.返回上传文件的路径（浏览器上可以访问到）
 * 注：
 *  1. fileToUpload:本地要上传的文件
 *  2. 文件上传只需要两点：
 *       *保存路径
 *       *新文件名
 *  3.返回的路径：实际上就是：协议 + 域名 + 端口 + 文件保存路径 + 日期 + 新文件名
 *  4.@RequestParam(value = "uploadFile") 名字要与html的name一致否则报错：java.lang.NullPointerException: null
 */
@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @PostMapping("/upload")
    public String fileUpload(@RequestParam(value = "uploadFile") MultipartFile fileToUpload, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String date = sdf.format(new Date());
        File file = new File(realPath,date);
        if (!file.isDirectory()){
            boolean mkdir = file.mkdir();//在c盘没有创建文件权限的情况下，导致创建失败。
            if (!mkdir){
                return "创建文件失败";
            }
        }
        String originalFilename = fileToUpload.getOriginalFilename();
        String newFile = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            fileToUpload.transferTo(new File(file,newFile));
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + date + newFile;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "上传失败";
    }

}
