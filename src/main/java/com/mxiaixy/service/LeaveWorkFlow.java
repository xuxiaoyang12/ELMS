package com.mxiaixy.service;

import com.mxiaixy.pojo.Leave;
import com.mxiaixy.pojo.User;

import java.util.Map;

/**
 * Created by Mxia on 2017/3/9.
 */
public interface LeaveWorkFlow {

    void processStart(Leave leave, User user, String processDefinitionKey, Map<String, Object> map);
}
