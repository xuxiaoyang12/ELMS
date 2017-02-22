package com.mxiaixy.controller;

import com.mxiaixy.dto.AjaxResult;
import com.mxiaixy.exception.ServiceException;
import com.mxiaixy.pojo.Disk;
import com.mxiaixy.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
@Controller
@RequestMapping("/pan")
public class PanController {

    @Autowired
    private PanService panService;

    /**
     * 显示网盘列表
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(required = false,defaultValue = "0") Integer path , Model model){
        System.out.println(path);
        List<Disk> diskList =   panService.findDiskByFid(path);
        model.addAttribute("diskList",diskList);
        model.addAttribute("fid",path);
        return "/pan/list";
    }

    /**
     * 添加文件夹
     * @param fid
     * @param sourceName
     * @return
     */
    @PostMapping("/folder/new")
    @ResponseBody
    public AjaxResult addFolder(Integer fid,String sourceName){

        panService.addFolder(fid,sourceName);


        return new AjaxResult(AjaxResult.SUCCESS);
    }

    /**
     * 上传文件
     * @param fid
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(Integer fid ,  MultipartFile file){
        //保存上传文件
        try {
            panService.upload(fid, file);
            return new AjaxResult(AjaxResult.SUCCESS);
        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }
    }

    /**
     * 下载文件
     * @param path
     * @return
     */
    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> download(Integer path){
        //获取文件输入流
        try{
            InputStream inputStream = panService.download(path);
            Disk disk = panService.findDiskById(path);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置下载标注为二进制
            httpHeaders.setContentDispositionFormData("attachment",disk.getSourceName(), Charset.forName("UTF-8"));

            return new ResponseEntity<>(new InputStreamResource(inputStream),httpHeaders, HttpStatus.OK);


        }catch(ServiceException e){
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     * 通过id删除文件
     * @param id
     * @return
     */
    @GetMapping("/del/{id:\\d+}")
    @ResponseBody
    public AjaxResult del(@PathVariable Integer id){
        System.out.println(id);
        panService.delById(id);
        return new AjaxResult(AjaxResult.SUCCESS);
    }
}
