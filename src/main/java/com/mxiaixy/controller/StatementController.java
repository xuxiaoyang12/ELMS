package com.mxiaixy.controller;

import com.google.common.collect.Maps;
import com.mxiaixy.dto.AjaxResult;
import com.mxiaixy.dto.DataTablesResult;
import com.mxiaixy.dto.MonthDto;
import com.mxiaixy.pojo.Finance;
import com.mxiaixy.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/23.
 */
@Controller
@RequestMapping("/statement")
public class StatementController  {

    @Autowired
    private StatementService statementService;

    @GetMapping("/day")
    public String day(){
        return "/statement/dayReport";
    }

    /**
     * 获取日报表
     * @param request
     * @return
     */
    @GetMapping("/day/list")
    @ResponseBody
    public DataTablesResult dayReport(HttpServletRequest request){

        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String day = request.getParameter("day");

        //将数据封装到map集合中
        Map<String ,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("length",length);
        map.put("day",day);

        //查询所有流水
        List<Finance> financeList = statementService.findAllDays(map);
        //查询所有流水数
        Long count = statementService.countAllFinance();

        return new DataTablesResult(draw,count,count,financeList);


      /* List<Finance> financeList =  statementService.findAllDays();

        model.addAttribute("financeList",financeList);
        return "/statement/dayReport";*/
    }

    /**
     * 获取日饼图报表
     * @param day
     * @return
     */
    @GetMapping("/day/daySum")
    @ResponseBody
    public AjaxResult dayReport(String day){
        //通过日期查询各个模块的综合

        List<Map<String,Object>> pieData = statementService.findPieByDay(day);

        return new AjaxResult(pieData);
    }

    @GetMapping("/month")
    public String month(){
        return "/statement/month";
    }

    /**
     * 获取月报表列表
     * @param request
     * @return
     */
    @GetMapping("/mouth/list")
    @ResponseBody
    public DataTablesResult mouthList(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String month = request.getParameter("month");

        Map<String,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("length",length);
        map.put("month",month);
        //查询数据
        List<MonthDto> monthDtoList = statementService.findMonth(map);
        //Long count =statementService.countMouth();
        Long count = 10l;
        return new DataTablesResult(draw,count,count,monthDtoList);
    }
}
