package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.utils.file.FileUploadService;
import com.cdzic.zicTemplate.utils.file.FileUtils;
import com.cdzic.zicTemplate.utils.file.ImgUpload;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @creator yaotao
 * @date 2018/8/20 9:41
 * @describe:
 */
@ApiIgnore
@RestController
@RequestMapping("/bg")
public class FileController {
    private static final Logger LOGGER = LogManager.getLogger(FileController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @Value("${project.name}")
    private String projectName;
    @Value("${file.save.path}")
    private String filePath;

    /**
     * 上传图片
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public Map<String, Object> uploadImg(@RequestParam(value = "file") MultipartFile files) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = fileUploadService.uploadImg(files);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1:上传失败，请稍后再试");
        }
        return map;
    }


    /**
     * 多文件上传到本地
     *
     * @param files
     */
    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    public Map<String, Object> uploadFiles(@RequestParam(value = "file") MultipartFile[] files) {
        String imgUrl = "";
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null) {
                //调用上传方法
                try {
                    //获取上传后的文件名
                    imgUrl = FileUtils.executeUpload1(files[i], projectName, filePath);
                    map.put("imgUrl", "/web/" + imgUrl);//web是指定的打包成war包的包名  线上路径
//                    map.put("imgUrl", "/" + imgUrl);//web是指定的打包成war包的包名
                    map.put("code", 0);
                } catch (IOException e) {
                    map.put("code", 1);
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 上传单文件到本地
     *
     * @param files
     * @param fileName
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(value = "file") MultipartFile files, @RequestParam String fileName) {
        if (files != null) {
            //调用上传方法
            try {
                FileUtils.executeUpload2(files, fileName, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 下载文件
     *
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
    public String downloadImage(String fileName, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("下载文件的文件名=" + fileName);
        String fileUrl = filePath + fileName;
        if (fileUrl != null) {
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    LOGGER.info("下载文件成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


}
