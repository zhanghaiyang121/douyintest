package com.example.wx.controller;

import com.example.db.pojo.Users;
import com.example.db.pojo.Video;
import com.example.db.service.VideoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

    //保存上传视频信息
    @RequestMapping("/upload")
    @ResponseBody
    public com.imooc.utils.IMoocJSONResult saveVideo(@RequestBody Video video){
        Date nowdate=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        video.setCreateTime(Timestamp.valueOf(simpleDate.format(nowdate)));
        String id=videoService.saveVideo(video);
        return com.imooc.utils.IMoocJSONResult.ok(id);
    }

    //测试文件请求，把文件内容写入到本地文件内
    @RequestMapping(value="/testvideo",headers="content-type=multipart/form-data")
    @ResponseBody
    public void testvideo(@RequestBody MultipartFile file) throws IOException {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (file!=null){
                String fileName = file.getOriginalFilename();
                System.out.println(fileName);
                // 文件上传的最终保存路径
                String finalVideoPath = "";
                String userId ="2008198M5TMPMNR4";
                String uploadPathDB = "/" + userId + "/video";
                String arrayFilenameItem[] =  fileName.split("\\.");
                String fileNamePrefix = "";

                for (int i = 0 ; i < arrayFilenameItem.length-1 ; i ++) {
                    fileNamePrefix += arrayFilenameItem[i];
                }

                finalVideoPath = "D:/myworkspace/dyapp" + uploadPathDB + "/" + fileName;
                File outFile = new File(finalVideoPath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    // 创建父文件夹
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            }
        }catch (Exception e){

        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }





    }
}
