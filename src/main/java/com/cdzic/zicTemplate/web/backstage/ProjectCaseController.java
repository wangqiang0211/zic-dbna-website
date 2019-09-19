package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.ProjectCase;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.ProjectCaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/11 下午 02:13
 * @Author 靳东
 * @ClassName ProjectCaseController
 */
@RestController
@RequestMapping("bg")
public class ProjectCaseController {
    private static final Logger LOGGER = LogManager.getLogger(ProjectCaseController.class);

    @Autowired
    private ProjectCaseService projectCaseService;

    /**
     * 案例列表
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/project/page")
    public ResponseObj projectConditionQuery(@RequestParam int page,
                                             @RequestParam int limit,
                                             @RequestParam(required = false,value = "searchKey") String searchKey,
                                             @RequestParam(required = false,value = "searchValue") String searchValue){
        Page<Object> startPage = PageHelper.startPage(page,limit);
        List<ProjectCase> list = projectCaseService.findByCondition(searchKey,searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,null,new RespObjSqlItem(startPage.getTotal(),list));
    }

    /**
     * 添加案例
     * @param thumbnail
     * @param headline
     * @param projectCaseContent
     * @return
     */
    @PostMapping("/project/add")
    public ResponseObj projectAdd(@RequestParam String thumbnail,
                                  @RequestParam String headline,
                                  @RequestParam String projectCaseContent){
        try {
            projectCaseService.save(headline,thumbnail,projectCaseContent);
        }catch (Exception e){
            return new ResponseObj(ResponseObj.getErrorCodeFail(),"添加失败",null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,"添加成功",null);
    }


    /**
     * 删除案例
     * @param id
     * @return
     */
    @PostMapping("/project/delete")
    public ResponseObj projectDelete(@RequestParam Long id){
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            projectCaseService.delete(id);
        }catch (Exception e){
            LOGGER.error("删除失败！原因：{}"+e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }

    @PostMapping("/project/update")
    public ResponseObj projectUpdate(@RequestParam String headline,
                                     @RequestParam String thumbnail,
                                     @RequestParam String projectCaseContent,
                                     @RequestParam Long id){
        projectCaseService.updatePeojectById(headline,thumbnail,projectCaseContent,id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,"修改成功",null);
    }

}
