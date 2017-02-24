package com.mxiaixy.mapper;

import com.mxiaixy.dto.MonthDto;
import com.mxiaixy.pojo.Finance;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxia on 2017/2/23.
 */
public interface FinanceMapper {
    void saveFinance(Finance finance);

    Finance findFinanceByDeviceSerialNumber(String serialNumber);

    void update(Finance finance);

    List<Finance> findAll();

    List<Finance> findAllFinance(Map<String, Object> map);

    Long count();

    List<Map<String,Object>> findPieDataByDay(String day);

    List<MonthDto> findMouth(Map<String, Object> map);

    Long countMonth();
}
