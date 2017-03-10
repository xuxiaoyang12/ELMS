package com.mxiaixy.mapper;

import com.mxiaixy.pojo.Leave;

/**
 * Created by Mxia on 2017/3/9.
 */
public interface LeaveMapper {

    void saveApply(Leave leave);

    void update(Leave leave);
}
