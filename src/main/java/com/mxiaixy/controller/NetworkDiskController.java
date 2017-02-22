package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.mxiaixy.pojo.Dict;
import com.mxiaixy.pojo.DictContent;
import com.mxiaixy.service.NetworkDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/21.
 */
@Controller
@RequestMapping("/networkDisk")
public class NetworkDiskController {

    @Autowired
    private NetworkDiskService networkDiskService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false,defaultValue = "0") Integer path, Model model){

        List<Dict> dictList = networkDiskService.findDictByDictId(path);
        model.addAttribute("dictList",dictList);


        return "/networkDisk/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> listContent(Integer dictId){
        //通过文件夹id查询文件夹内部文件
        List<Dict> dictList = networkDiskService.findDictByDictId(dictId);
        List<DictContent> dictContentList = networkDiskService.findDictContentByDictId(dictId);

        Map<String,Object> map = Maps.newHashMap();
        map.put("dictList",dictList);
        map.put("dictContentList",dictContentList);
        return map;
    }

}
