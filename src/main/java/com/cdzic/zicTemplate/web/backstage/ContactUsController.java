package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.ContactUs;
import com.cdzic.zicTemplate.domain.News;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.ContactUsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/12 上午 10:11
 * @Author 靳东
 * @ClassName ContactUsController
 */
@RestController
@RequestMapping("/bg")
public class ContactUsController {
    private static final Logger LOGGER = LogManager.getLogger(ContactUsController.class);

    @Autowired
    private ContactUsService contactUsService;

    /**
     * 查询
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/contact-us-page")
    public ResponseObj contactUsQuery(@RequestParam int page,
                                      @RequestParam int limit,
                                      @RequestParam(required = false, value = "searchKey") String searchKey,
                                      @RequestParam(required = false, value = "searchValue") String searchValue) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<ContactUs> list = contactUsService.findByCondition(searchKey, searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }


    /**
     * 添加公司信息
     *
     * @param usKey
     * @param usValue
     * @param usType
     * @return
     */
    @PostMapping("/contact/us/add")
    public ResponseObj contactUsAdd(@RequestParam String usKey,
                                    @RequestParam String usValue,
                                    @RequestParam Integer usType,
                                    @RequestParam Integer sort) {
        try {
            contactUsService.save(usKey, usValue, usType, sort);
        } catch (Exception e) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "添加失败！", null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加成功！", null);
    }


    /**
     * 修改信息
     *
     * @return
     */
    @PostMapping("/contact/us/update")
    public ResponseObj contactUsUpdate(@RequestParam String usKey,
                                       @RequestParam String usValue,
                                       @RequestParam int usType,
                                       @RequestParam int sort,
                                       @RequestParam Long id) {

        contactUsService.updateContactUsById(usKey, usValue, usType, sort, id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改成功", null);
    }

    @PostMapping("/contact/sort/update")
    public ResponseObj contactSortUpdate(@RequestParam Integer sort,
                                         @RequestParam Long id) {

        contactUsService.updateContactSortById(sort, id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改成功", null);
    }


    /**
     * 删除信息
     *
     * @return
     */
    @PostMapping("/contact/us/delete")
    public ResponseObj contactUsDelete(@RequestParam Long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            contactUsService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }


}
