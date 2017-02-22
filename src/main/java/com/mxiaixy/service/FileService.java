package com.mxiaixy.service;

import java.io.InputStream;

/**
 * 合同上传服务逻辑
 * Created by Mxia on 2017/2/17.
 */
public interface FileService {

    String uploadFile(InputStream inputStream, String originalFilename, String contentType);
}
