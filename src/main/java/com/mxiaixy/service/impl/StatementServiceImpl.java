package com.mxiaixy.service.impl;

import com.mxiaixy.dto.MonthDto;
import com.mxiaixy.mapper.FinanceMapper;
import com.mxiaixy.pojo.Finance;
import com.mxiaixy.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/23.
 */
@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private FinanceMapper financeMapper;
    /**
     * 查询所有日流水
     * @return
     */
    @Override
    public List<Finance> findAllDays() {
       List<Finance> financeList =  financeMapper.findAll();


        return financeList;
    }

    @Override
    public List<Finance> findAllDays(Map<String, Object> map) {

        return financeMapper.findAllFinance(map);
    }

    @Override
    public Long countAllFinance() {
        return financeMapper.count();
    }

    @Override
    public List<Map<String, Object>> findPieByDay(String day) {

        return financeMapper.findPieDataByDay(day);

    }

    @Override
    public List<MonthDto> findMonth(Map<String, Object> map) {

        return financeMapper.findMouth(map);
    }

    @Override
    public Long countMouth() {
        return financeMapper.countMonth();
    }


}
