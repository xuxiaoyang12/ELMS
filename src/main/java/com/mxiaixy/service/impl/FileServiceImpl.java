package com.mxiaixy.service.impl;

import com.mxiaixy.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.*;
import java.util.UUID;

/**
 * Created by Mxia on 2017/2/17.
 */
@Service
public class FileServiceImpl implements FileService {

    //读取配置文件 并获得上传文件位置
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    @Transactional//添加事物
    public String uploadFile(InputStream inputStream, String originalFilename, String contentType) {

        //获取新的文件名称
        String newFileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //合同文件保存到硬盘中
        File file = new File(new File(uploadPath),newFileName);


        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream,outputStream);

        } catch (IOException e) {
            new RuntimeException("上传合同错误 请稍后重试！",e);
        }
        return newFileName;//上传成功后 返回合同的新名称
    }
}
