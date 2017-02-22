package com.mxiaixy.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * 生成序列号 类
 * Created by Mxia on 2017/2/20.
 */
public class SerialNumberUtil {

    public static String getSerialNumber(){
        DateTime now = new DateTime();
        String result = now.toString("YYYYMMDDHHmmss");
        result += RandomStringUtils.randomNumeric(4);

        return result;
    }
}
