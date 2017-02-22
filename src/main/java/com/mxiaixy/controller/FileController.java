package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.mxiaixy.dto.AjaxResult;
import com.mxiaixy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 文件上传控制器
 * Created by Mxia on 2017/2/17.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 自动注入spring容器中
     */
    @Autowired
    private FileService fileService;

    /**
     * 上传合同
     * @param file 上传文件的信息 类型 和输入流
     * @return 文件的原名称 和 生成你的新名称
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult fileUpload(MultipartFile file){
        //首先接收上传组件传过来的文件信息  在参数中使用MUltipartFile 来获取
        //保存文件到硬盘并且 获取新的文件名称
        try {
            String fileName = fileService.uploadFile(file.getInputStream()
                    ,file.getOriginalFilename(),file.getContentType());
            //源文件名称 和生成的新名称传回客户端
            Map<String, Object> map = Maps.newHashMap();
            map.put("newFileName",fileName);
            map.put("sourceFileName",file.getOriginalFilename());
            return new AjaxResult(map);
        } catch (IOException e) {
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }

    }


}
