package com.mxiaixy.service;

import com.mxiaixy.dto.MonthDto;
import com.mxiaixy.pojo.Finance;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/23.
 */
public interface StatementService {
    List<Finance> findAllDays();

    List<Finance> findAllDays(Map<String, Object> map);

    Long countAllFinance();

    List<Map<String,Object>> findPieByDay(String day);

    List<MonthDto> findMonth(Map<String, Object> map);

    Long countMouth();
}
