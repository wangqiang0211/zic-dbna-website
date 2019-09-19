package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.VideoShow;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.VideoShowService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/11 上午 11:28
 * @Author 靳东
 * @ClassName VideoShowController
 */
@RestController
@RequestMapping("/bg")
public class VideoShowController {
    private static final Logger LOGGER = LogManager.getLogger(VideoShowController.class);

    @Autowired
    private VideoShowService videoShowService;


    /**
     * 查询视频
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/video/page")
    public ResponseObj videoConditionQuery(@RequestParam int page,
                                         @RequestParam int limit,
                                         @RequestParam(required = false, value = "searchKey") String searchKey,
                                         @RequestParam(required = false, value = "searchValue") String searchValue) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<VideoShow> list = videoShowService.findByCondition(searchKey, searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }


    /**
     * 添加视频
     *
     * @param thumbnail
     * @param headline
     * @param videoShowContent
     * @return
     */
    @PostMapping("/video/add")
    public ResponseObj videoAdd(@RequestParam String thumbnail,
                               @RequestParam String headline,
                               @RequestParam String videoShowContent) {
        try {
            videoShowService.save(headline, thumbnail, videoShowContent);
        } catch (Exception e) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "添加失败！", null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加成功！", null);
    }


    /**
     * 删除视频
     *
     * @return
     */
    @PostMapping("/video/delete")
    public ResponseObj videoDelete(@RequestParam Long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            videoShowService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }

    /**
     * 修改视频
     *
     * @return
     */
    @PostMapping("/video/update")
    public ResponseObj videoUpdate(@RequestParam String headline,
                                   @RequestParam String thumbnail,
                                   @RequestParam String videoShowContent,
                                   @RequestParam Long id) {

        videoShowService.updateVideoById(headline, thumbnail, videoShowContent, id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改成功", null);
    }
}
