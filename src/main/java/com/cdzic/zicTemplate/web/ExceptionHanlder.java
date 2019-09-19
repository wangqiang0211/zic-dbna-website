package com.cdzic.zicTemplate.web;

import com.cdzic.zicTemplate.domain.myexception.MyException;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHanlder {

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ModelAndView exceptionHandler(MyException e, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        ResponseObj responseObj=new ResponseObj(ResponseObj.ERROR_CODE_FAIL);
        responseObj.setErrorMsg(request.getRequestURL()+":"+e.getMessage());
        modelAndView.setViewName("wechat-404");
        modelAndView.addObject("responseObj",responseObj);
        return modelAndView;
    }
}
