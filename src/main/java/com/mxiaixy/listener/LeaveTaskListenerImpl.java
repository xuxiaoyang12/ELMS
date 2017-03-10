package com.mxiaixy.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mxia on 2017/3/9.
 */
@Component
public class LeaveTaskListenerImpl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

    }
}
