package com.cdzic.zicTemplate.utils.file;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Date 2019/05/08 下午 04:58
 * @Author 靳东
 * @ClassName ImgUpload
 */
@Service
public class ImgUpload implements FileUploadService{

    @Value("${project.name}")
    private String projectName;

    @Value("${file.save.path}")
    private String filePath;

    @Override
    public Map<String, Object> uploadImgGoo(MultipartFile files) throws Exception {
        Map<String,Object> map = new HashMap<>();
        String name = files.getOriginalFilename();//文件名称
        String fileName = projectName + UUID.randomUUID() + name.substring(name.lastIndexOf("."));//项目名+UUID.后缀名

        File fileDirectory = new File(filePath);
        if(!fileDirectory.exists()){
            fileDirectory.mkdirs();
        }
        //上传图片
        File serverFile = new File(filePath + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        files.transferTo(serverFile);
        //优秀的图片处理的Google开源Java类库
//        Thumbnails.of(serverFile).size(240, 135).toFile(filePath+fileName);
        Thumbnails.of(serverFile).width(240).height(135).toFile(filePath+fileName);
        map.put("code",0);
        map.put("imgUrl","/web/"+fileName);
//        map.put("imgUrl","/"+fileName);
        return map;
    }


    /*上传图片*/
    @Override
    public Map<String,Object> uploadImg(MultipartFile files) throws  Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        if (files.isEmpty()) {
            map.put("code",1);//"1：未选择任何文件！为空"
            return map;
        }
        long size = files.getSize();//文件大小
        if (1024*1024*1024<size) {
            map.put("code",2);//"2:上传失败，只支持最大为1G的单个文件上传"
            return map;
        }
        String name = files.getOriginalFilename();//文件名称
        String fileName = projectName + UUID.randomUUID() + name.substring(name.lastIndexOf("."));//项目名+UUID.后缀名
        String zipFileName = projectName + UUID.randomUUID() + name.substring(name.lastIndexOf("."));//项目名+UUID.后缀名，要压缩图片名称
        String suffixName = name.substring(name.lastIndexOf(".")+1);//文件后缀
        if(!"jpg".equals(suffixName)&&!"png".equals(suffixName)){
            map.put("code",3);//"3：:图片格式不正确：jpg、png、才能压缩"
            return map;
        }
        File fileDirectory = new File(filePath);
        if(!fileDirectory.exists()){
            fileDirectory.mkdirs();
        }
        //上传图片
        File serverFile = new File(filePath + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        files.transferTo(serverFile);

        //原图片指定尺寸压缩
        String oldPath = filePath + fileName;
        File oldFile = new File(oldPath);
        String newPath = filePath + zipFileName;
        File newFile = new File(newPath);
        //压缩图片
        int w = 240;
        int h = 135;
        float quality = 0.9f;
        String zipUrl = ImageZipUtil.zipWidthHeightImageFile(oldFile, newFile, w, h, quality);//调用压缩方法
        oldFile.delete();//压缩完成  删除原图");
        map.put("code",0); //"0:上传成功！
        map.put("imgUrl","/web/"+zipFileName);//web是指定的打包成war包的 线上路径
//        map.put("imgUrl","/"+zipFileName);//web是指定的打包成war包的
        return map;
    }


    /*封装上传方法*/
    public void upload(MultipartFile file,File fileDirectory,String fileName) throws Exception{
        BufferedInputStream in=null;
        BufferedOutputStream os=null;
        try {
            in = new BufferedInputStream(new ByteArrayInputStream(file.getBytes()));//输入流
            os = new BufferedOutputStream(new FileOutputStream(new File(fileDirectory,fileName)));
            byte[] buf = new byte[1024];
            int i ;//   buf,0,1024   一次读1024字节
            while ((i = in.read(buf)) != -1) {
                os.write(buf, 0, 1024);
            }
            in.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();//最终关流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();//最终关流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //try catch代码块 可以直接用   file.transferTo(new File(fileDirectory,fileName));替换

    }


}
