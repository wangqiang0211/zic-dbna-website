package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.AirCondition;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.AirConditionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/08 下午 01:17
 * @Author 靳东
 * @ClassName AirConditionController
 * @Description
 */
@RestController
@RequestMapping("/bg")
public class AirConditionControllerBg {
    private static final Logger LOGGER = LogManager.getLogger(AirConditionControllerBg.class);
    @Autowired
    private AirConditionService airConditionService;

    /**
     * 添加空调新闻
     *
     * @param thumbnail
     * @param headline
     * @param airConditionType
     * @param articleContent
     * @return
     */
    @PostMapping("/air/add")
    public ResponseObj newsAdd(@RequestParam String thumbnail,
                               @RequestParam String headline,
                               @RequestParam Integer airConditionType,
                               @RequestParam String abstracts,
                               @RequestParam String articleContent) {
        try {
            airConditionService.save(thumbnail, headline, airConditionType,abstracts, articleContent);
        } catch (Exception e) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "添加失败！", null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加成功！", null);
    }


    /**
     * 查询空调信息
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/air/page")
    public ResponseObj airConditionQuery(@RequestParam int page,
                                         @RequestParam int limit,
                                         @RequestParam(required = false, value = "searchKey") String searchKey,
                                         @RequestParam(required = false, value = "searchKey2") String searchKey2,
                                         @RequestParam(required = false, value = "searchValue") String searchValue,
                                         @RequestParam(required = false, value = "searchValue2") String searchValue2) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<AirCondition> list = airConditionService.findByCondition(searchKey,searchKey2, searchValue,searchValue2);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }

    /**
     * 根据id删除空调新闻
     *
     * @param id
     * @return
     */
    @PostMapping("/air/delete")
    public ResponseObj airDelete(@RequestParam Long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            airConditionService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }


    /**
     * 修改空调新闻
     *
     * @return
     */
    @PostMapping("/air/update")
    public ResponseObj airUpdate(@RequestParam String headline,
                                 @RequestParam String thumbnail,
                                 @RequestParam int airConditionType,
                                 @RequestParam String abstracts,
                                 @RequestParam String articleContent,
                                 @RequestParam Long id) {
        airConditionService.updateNewsById(headline, thumbnail, airConditionType,abstracts, articleContent, id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改成功", null);
    }

}
