package com.cdzic.zicTemplate.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @creator yaotao
 * @date 2018/8/20 11:20
 * @describe:
 */
public class FileUtils {
    /**
     * 保存文件
     * 多文件上传
     * @param file
     * @return
     * @throws Exception
     */
    public static String executeUpload1(MultipartFile file, String projectName, String filePath) throws IOException {
        //文件后缀名  file.getOriginalFilename() 是得到上传的文件名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String fileName = projectName+UUID.randomUUID() + suffix;
        //服务端保存的文件对象
        File serverFile = new File(filePath + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return fileName;
    }

    /**
     * 保存文件
     * 上传文件到本地
     * @param file
     * @param fileName
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String executeUpload2(MultipartFile file, String fileName, String filePath) throws IOException {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String fileNameT = fileName + suffix;
        //服务端保存的文件对象
        File serverFile = new File(filePath + fileNameT);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return fileName;
    }

    public static String executeUpload3(MultipartFile file, String filePath) throws IOException {
//        //文件后缀名
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        //上传文件名
//        String fileNameT = fileName + suffix;
        String fileName = file.getOriginalFilename();
        //服务端保存的文件对象
        File serverFile = new File(filePath + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return fileName;
    }
}
