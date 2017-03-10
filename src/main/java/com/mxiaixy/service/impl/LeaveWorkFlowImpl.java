package com.mxiaixy.service.impl;

import com.mxiaixy.mapper.LeaveMapper;
import com.mxiaixy.pojo.Leave;
import com.mxiaixy.pojo.User;
import com.mxiaixy.service.LeaveWorkFlow;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Mxia on 2017/3/9.
 */
@Service
public class LeaveWorkFlowImpl implements LeaveWorkFlow {

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    /**
     * 启动流程
     * @param leave
     * @param user
     * @param processDefinitionKey
     * @param map
     */
    @Override
    @Transactional//添加事物
    public void processStart(Leave leave, User user, String processDefinitionKey, Map<String, Object> map) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //保存申请数据
        leave.setUserId(user.getId().toString());
        leave.setApplyTime(format.format(new Date()));
        leaveMapper.saveApply(leave);
        System.out.println("保存leave");

        identityService.setAuthenticatedUserId(user.getId().toString());

        //启动流程
        ProcessInstance instance =runtimeService
                .startProcessInstanceByKey(processDefinitionKey,leave.getId().toString(),map);
        leave.setProcessInstanceId(instance.getProcessInstanceId());
        System.out.println("ljdflds");
        leaveMapper.update(leave);



    }
}
