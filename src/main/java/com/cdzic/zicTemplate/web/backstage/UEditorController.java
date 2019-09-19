package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.utils.ueditor.ActionEnter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @creator yaotao
 * @date 2018/8/20 13:33
 * @describe:
 */
@ApiIgnore
@RestController
@RequestMapping("/bg")
public class UEditorController {

//    @RequestMapping(value="/config")
//    public void config(HttpServletRequest request,HttpServletResponse response) {
//        response.setContentType("application/json");
//        String rootPath = request.getSession().getServletContext().getRealPath("/");
//        try {
//            request.setCharacterEncoding( "utf-8" );
//            String exec = new ActionEnter(request, rootPath).exec();
//            PrintWriter writer = response.getWriter();
//            writer.write(exec);
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            //富文本编辑器使用的路径
//            String url2=exec.replace("/usr/local/","/");  //线上路径
            String url2=exec.replace("D:/","/");
            PrintWriter writer = response.getWriter();
            writer.write(url2);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @RequestMapping(value = "/config")
//    public void config(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/json");
//        try {
//            request.setCharacterEncoding("utf-8");
//            response.setHeader("Content-Type", "text/html");
//            ServletContext application = request.getServletContext();
//            String rootPath = application.getRealPath("/");
//            PrintWriter out = response.getWriter();
//            out.write(new ActionEnter(request, rootPath).exec());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}