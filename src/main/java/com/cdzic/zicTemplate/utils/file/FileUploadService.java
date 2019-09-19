package com.cdzic.zicTemplate.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Date 2019/05/08 下午 05:01
 * @Author 靳东
 * @ClassName FileUploadService
 */
public interface FileUploadService {

    Map<String,Object> uploadImg(MultipartFile files) throws  Exception;


    Map<String,Object> uploadImgGoo(MultipartFile files) throws  Exception;
}
