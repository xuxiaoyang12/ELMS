package com.mxiaixy.service.impl;

import com.google.common.collect.Lists;
import com.mxiaixy.exception.ServiceException;
import com.mxiaixy.mapper.PanMapper;
import com.mxiaixy.pojo.Disk;
import com.mxiaixy.service.PanService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mxia on 2017/2/21.
 */
@Service
public class PanServiceImpl implements PanService {

    @Autowired
    private PanMapper panMapper;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<Disk> findDiskByFid(Integer fid) {


        return panMapper.findDiskByFid(fid);
    }

    /**
     * 添加文件夹
     * @param fid
     * @param sourceName
     */
    @Override
    public void addFolder(Integer fid, String sourceName) {

        Disk disk = new Disk();

        disk.setSourceName(sourceName);
        disk.setFid(fid);
        disk.setCreateTime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        //TODO 修补完登陆系统后更改创建者获取方式
        disk.setCreateUser("jack");
        disk.setType(Disk.FOLDER_TYPE);

        //保存文件夹
        panMapper.addFolder(disk);
    }

    /**
     * 上传文件
     * @param fid
     * @param file
     */
    @Override
    @Transactional//添加事物
    public void upload(Integer fid, MultipartFile file) {
        //1.保存上传文件到指定目录
        String newFileName = UUID.randomUUID().toString();
        String sourceFileName = file.getOriginalFilename();
        Long size = file.getSize();
        if(!file.isEmpty()){
            newFileName += sourceFileName.substring(sourceFileName.lastIndexOf("."));
        }
        try {
            //文件保存路径
            File saveFile = new File(new File(uploadPath), newFileName);
            //获取输出流
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            //获取输入流
            InputStream inputStream = file.getInputStream();

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }catch(IOException e){
            throw new ServiceException("文件上传失败");
        }
        //2.保存文件数据到数据库
        Disk disk = new Disk();
        disk.setSourceName(sourceFileName);
        disk.setFid(fid);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));
        disk.setType(Disk.FILE_TYPE);
        disk.setName(newFileName);
        disk.setCreateTime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        disk.setCreateUser("jack");
        panMapper.addFolder(disk);





    }

    /**
     * 通过文件id获取文件输入流
     * @param id
     * @return
     */
    @Override
    public InputStream download(Integer id) {
        //1.下载文件
        //获取文件名称
        Disk disk = panMapper.findDiskById(id);
        //通过文件对象得到文件的名称
        if(disk == null){
            throw new ServiceException("文件不存在或已被删除");
        }else{
            //获取文件的输入流
            File saveFile = new File(new File(uploadPath),disk.getName());
            try {
                FileInputStream inputStream = new FileInputStream(saveFile);
                return inputStream;
            } catch (FileNotFoundException e) {
                throw new ServiceException("文件现在异常请稍后重试");
            }
        }

    }

    /**
     * 通过id查询文件对象
     * @param id
     * @return
     */
    @Override
    public Disk findDiskById(Integer id) {

        return panMapper.findDiskById(id);
    }

    /**
     * 通过id删除数据
     * @param id
     */
    @Override
    @Transactional
    public void delById(Integer id) {

        Disk disk = panMapper.findDiskById(id);
        if(disk != null){

            //判断文件类型
            if(disk.getType().equals(Disk.FILE_TYPE)){
                //当是文件想删除磁盘中的文件
                File file = new File(new File(uploadPath),disk.getName());
                file.delete();
                //删除数据库
                panMapper.delDiskById(id);
            }else{
                //文件夹
                //找到所有的记录
                List<Disk> diskList = panMapper.findAllDisk();
                //找到所需要删除的id
                List<Integer> idList = Lists.newArrayList();
                //寻找所有即将删除的id
                findDelId(diskList,idList,id);
                //添加上主目录的id
                idList.add(id);
                //批量删除文件文件夹
                System.out.println(idList);
                panMapper.delBatchById(idList);


            }
        }

    }



    /**
     * 此方法用来获取所有即将删除的所有id
     * @param diskList
     * @param idList
     * @param id
     */
    private void findDelId(List<Disk> diskList, List<Integer> idList, Integer id) {

        //循环子目录对象 用来添加子目录中的目录的id
        for(Disk disk: diskList){
            //如果父id与id相等说明是其子目录
            if(disk.getFid().equals(id)){
                //添加到要删除的id列
                idList.add(disk.getId());
                //判断是否问文件夹
                if(disk.getType().equals(Disk.FOLDER_TYPE)){
                    //是文件夹就继续调用这个方法
                    findDelId(diskList,idList,disk.getId());
                }else{
                    //是文件就直接删除
                    File file = new File (new File(uploadPath),disk.getName());
                    file.delete();
                }
            }


        }

    }
}
