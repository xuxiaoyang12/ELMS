package com.mxiaixy.service;

import com.mxiaixy.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Mxia on 2017/2/21.
 */
public interface PanService {
    List<Disk> findDiskByFid(Integer path);

    void addFolder(Integer fid, String sourceName);

    void upload(Integer fid, MultipartFile file);

    InputStream download(Integer id);

    Disk findDiskById(Integer id);

    void delById(Integer id);
}
