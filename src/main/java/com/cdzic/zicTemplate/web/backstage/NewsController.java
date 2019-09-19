package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.News;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.NewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/03 上午 10:39
 * @Author 靳东
 * @ClassName NewsController
 * @Description
 */
@RestController
@RequestMapping("/bg")
public class NewsController {

    private static final Logger LOGGER = LogManager.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;


    /**
     * 查询
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/news-page")
    public ResponseObj newsQuery(@RequestParam int page,
                                 @RequestParam int limit,
                                 @RequestParam(required = false, value = "searchKey") String searchKey,
                                 @RequestParam(required = false, value = "searchValue") String searchValue,
                                 @RequestParam(required = false, value = "searchKey2") String searchKey2,
                                 @RequestParam(required = false, value = "searchValue2") String searchValue2

                                 ) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<News> list = newsService.findByCondition(searchKey, searchValue,searchKey2,searchValue2);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }

    /**
     * 添加新闻
     * @param thumbnail
     * @param headline
     * @param newsType
     * @param articleContent
     * @return
     */
    @PostMapping("/news/add")
    public ResponseObj newsAdd(@RequestParam String thumbnail,
                               @RequestParam String headline,
                               @RequestParam Integer newsType,
                               @RequestParam String abstracts,
                               @RequestParam String articleContent) {
        try {
            newsService.save(thumbnail, headline, newsType,abstracts, articleContent);
        } catch (Exception e) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "添加失败！", null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加成功！", null);
    }


    /**
     * 删除新闻
     *
     * @return
     */
    @PostMapping("/news/delete")
    public ResponseObj newsDelete(@RequestParam Long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            newsService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }


    /**
     * 修改新闻
     * @return
     */
    @PostMapping("/news/update")
    public ResponseObj updateNews(@RequestParam String headline,
                                  @RequestParam String thumbnail,
                                  @RequestParam int newsType,
                                  @RequestParam String abstracts,
                                  @RequestParam String articleContent,
                                  @RequestParam Long id){
        newsService.updateNewsById(headline,thumbnail,newsType,abstracts,articleContent,id);
        return new  ResponseObj(ResponseObj.ERROR_CODE_SUCCESS,"修改成功",null);
    }

}
