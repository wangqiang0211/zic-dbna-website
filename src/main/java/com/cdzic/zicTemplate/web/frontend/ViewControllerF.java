package com.cdzic.zicTemplate.web.frontend;

import com.cdzic.zicTemplate.dao.mapper.*;
import com.cdzic.zicTemplate.dao.repo.*;
import com.cdzic.zicTemplate.domain.*;
import com.cdzic.zicTemplate.domain.page.PreAndNextEntity;
import com.cdzic.zicTemplate.service.*;
import com.cdzic.zicTemplate.utils.sort.SortUtil;
import com.cdzic.zicTemplate.utils.sort.domain.SortDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @creator yaotao
 * @date 2018/11/22 15:53
 * @describe:
 */
@RestController
public class ViewControllerF {
    private static final Logger LOGGER = LogManager.getLogger(ViewControllerF.class);

    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AirConditionRepo airConditionRepo;
    @Autowired
    private AirConditionService airConditionService;
    @Autowired
    private AirConditionMapper airConditionMapper;
    @Autowired
    private ContactUsMapper contactUsMapper;
    @Autowired
    private ContactUsRepo contactUsRepo;
    @Autowired
    private ProjectCaseRepo projectCaseRepo;
    @Autowired
    private ProjectCaseService projectCaseService;
    @Autowired
    private ProjectCaseMapper projectCaseMapper;
    @Autowired
    private VideoShowRepo videoShowRepo;
    @Autowired
    private VideoShowService videoShowService;
    @Autowired
    private VideoShowMapper videoShowMapper;


    /**
     * 全国服务电话
     *
     * @return
     */
    public ContactUs hotline() {
        ContactUs contactUses = contactUsRepo.findBySort(2);
        return contactUses;
    }

    /**
     * 负责人电话
     *
     * @return
     */
    public ContactUs person() {
        ContactUs contactUses = contactUsRepo.findBySort(3);
        return contactUses;
    }


    /**
     * 首页
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/index","/"})
    public ModelAndView loginDbna(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("index");
        Page<News> newsPage = newsRepo.findAll(SortUtil.basicPage(0, 6, new SortDto("asc", "sort"), new SortDto("desc", "updateDate")));
        Page<Product> productPage = productRepo.findAll(SortUtil.basicPage(0, 6, new SortDto("asc", "sort"), new SortDto("desc", "updateDate")));
        modelAndView.addObject("newsPage", newsPage);
        modelAndView.addObject("productPage", productPage);
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 关于我们
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/about")
    public ModelAndView aboutuUs(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("about");
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 新闻动态
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/newlist")
    public ModelAndView newsTrends(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                   ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 6, "updateDate desc");
        List<News> news = null;
        PageInfo pageInfo = null;
        news = newsMapper.findAll();
        pageInfo = new PageInfo<>(news);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("newlist");
        return modelAndView;
    }


    /**
     * 新闻动态(公司/行业)
     *
     * @param modelAndView
     * @param
     * @return
     */
    @GetMapping("/news-company")
    public ModelAndView newsTrends(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                   @RequestParam(required = false, defaultValue = "3") int newsType,
                                   ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 6, "updateDate desc");
        List<News> news = null;
        PageInfo pageInfo = null;
        news = newsMapper.findByCondition1(newsType);
        pageInfo = new PageInfo<>(news);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("newsType", newsType);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("news-company");
        return modelAndView;
    }

    /**
     * 查找一个新闻动态
     *
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("/news-find")
    public ModelAndView newsFindOne(@RequestParam Long id,
                                    @RequestParam int newsType,
                                    ModelAndView modelAndView) {
        long thisId = 0;
        if (null == id) {
            id = thisId;
        }
        List<News> news = null;
        PageInfo pageInfo = null;
        news = newsRepo.findOneById(id);
        pageInfo = new PageInfo<>(news);
        PreAndNextEntity pre = newsService.getPreEntity(id, newsType);//上一篇
        PreAndNextEntity next = newsService.getNextEntity(id, newsType);//下一篇
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pre", pre);//上一篇
        modelAndView.addObject("next", next);//下一篇
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("news-show");
        return modelAndView;
    }


    /**
     * 五恒空调
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/airlist")
    public ModelAndView airTrends(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                  ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 6, "updateDate desc");
        List<AirCondition> airConditions = null;
        PageInfo pageInfo = null;
        airConditions = airConditionMapper.findAll();
        pageInfo = new PageInfo<>(airConditions);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得总页数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("airlist");
        return modelAndView;
    }


    /**
     * 五恒空调(公司/空调常识)
     *
     * @param modelAndView
     * @param
     * @return
     */
    @GetMapping("/air-company")
    public ModelAndView airTrends(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                  @RequestParam(required = false, defaultValue = "3") int airConditionType,
                                  ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 6, "updateDate desc");
        List<AirCondition> news = null;
        PageInfo pageInfo = null;
        news = airConditionMapper.findByCondition1(airConditionType);
        pageInfo = new PageInfo<>(news);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("airConditionType", airConditionType);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("air-company");
        return modelAndView;
    }

    /**
     * 查找一个五恒空调动态
     *
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("/air-find")
    public ModelAndView airFindOne(@RequestParam Long id,
                                   @RequestParam int airConditionType,
                                   ModelAndView modelAndView) {
        long thisId = 0;
        if (null == id) {
            id = thisId;
        }
        List<AirCondition> airConditions = null;
        PageInfo pageInfo = null;
        airConditions = airConditionRepo.findOneById(id);
        pageInfo = new PageInfo<>(airConditions);
        PreAndNextEntity pre = airConditionService.getPreEntity(id, airConditionType);
        PreAndNextEntity next = airConditionService.getNextEntity(id, airConditionType);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pre", pre);
        modelAndView.addObject("next", next);
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("air-show");
        return modelAndView;
    }

    /**
     * 产品展示
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/product-list")
    public ModelAndView productList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                    ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 12, "updateDate desc");
        List<Product> products = null;
        PageInfo pageInfo = null;
        products = productMapper.findAll();
        pageInfo = new PageInfo<>(products);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("product-list");
        return modelAndView;
    }


    /**
     * 产品展示(4类型)
     *
     * @param modelAndView
     * @param
     * @return
     */
    @GetMapping("/product-company")
    public ModelAndView productTrends(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                      @RequestParam(required = false, defaultValue = "4") int productType,
                                      ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 12, "updateDate desc");
        List<Product> products = null;
        PageInfo pageInfo = null;
        products = productMapper.findByCondition1(productType);
        pageInfo = new PageInfo<>(products);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("productType", productType);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageNum());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("product-company");
        return modelAndView;
    }

    /**
     * 查找一个产品
     *
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("/product-find")
    public ModelAndView productFindOne(@RequestParam Long id,
                                       @RequestParam int productType,
                                       ModelAndView modelAndView) {
        long thisId = 0;
        if (null == id) {
            id = thisId;
        }
        List<Product> products = null;
        PageInfo pageInfo = null;
        products = productRepo.findOneById(id);
        pageInfo = new PageInfo<>(products);
        PreAndNextEntity pre = productService.getPreEntity(id, productType);//上一篇
        PreAndNextEntity next = productService.getNextEntity(id, productType);//下一篇
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pre", pre);//上一篇
        modelAndView.addObject("next", next);//下一篇
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("product-show");
        return modelAndView;
    }


    /**
     * 工程案例
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/project-list")
    public ModelAndView projectList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                    ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 12, "updateDate desc");
        List<ProjectCase> projectCases = null;
        PageInfo pageInfo = null;
        projectCases = projectCaseMapper.findAll();
        pageInfo = new PageInfo<>(projectCases);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("project-anli");
        return modelAndView;
    }

    /**
     * 案例详情
     *
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("/project-find")
    public ModelAndView projectFindOne(@RequestParam Long id,
                                       ModelAndView modelAndView) {
        long thisId = 0;
        if (null == id) {
            id = thisId;
        }
        List<ProjectCase> projectCases = null;
        PageInfo pageInfo = null;
        projectCases = projectCaseRepo.findOneById(id);
        pageInfo = new PageInfo<>(projectCases);

        PreAndNextEntity pre = projectCaseService.getPreEntity(id);//上一篇
        PreAndNextEntity next = projectCaseService.getNextEntity(id);//下一篇
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pre", pre);
        modelAndView.addObject("next", next);
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("project-show");
        return modelAndView;
    }

    /**
     * 视频展播
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/video-list")
    public ModelAndView videoList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                  ModelAndView modelAndView) {
        PageHelper.startPage(pageNum, 12, "updateDate desc");
        List<VideoShow> videoShows = null;
        PageInfo pageInfo = null;
        videoShows = videoShowMapper.findAll();
        pageInfo = new PageInfo<>(videoShows);
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pageNum", pageInfo.getPageNum());//获得当前页
        modelAndView.addObject("pageSize", pageInfo.getPageSize());//获得一页显示的条数
        modelAndView.addObject("isFirstPage", pageInfo.isIsFirstPage());//是否是第一页
        modelAndView.addObject("totalPages", pageInfo.getPages());//获得总页数
        modelAndView.addObject("isLastPage", pageInfo.isIsLastPage());//是否是最后一页
        modelAndView.addObject("prePage", pageInfo.getPrePage());//上一页
        modelAndView.addObject("total", pageInfo.getTotal());//获得总页数
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("video-list");
        return modelAndView;
    }


    /**
     * 视频详情
     *
     * @param id
     * @param modelAndView
     * @return
     */
    @GetMapping("/video-find")
    public ModelAndView videoFindOne(@RequestParam Long id,
                                     ModelAndView modelAndView) {
        long thisId = 0;
        if (null == id) {
            id = thisId;
        }
        List<VideoShow> videoShows = null;
        PageInfo pageInfo = null;
        videoShows = videoShowRepo.findOneById(id);
        pageInfo = new PageInfo<>(videoShows);
        PreAndNextEntity pre = videoShowService.getPreEntity(id);//上一篇
        PreAndNextEntity next = videoShowService.getNextEntity(id);//下一篇
        modelAndView.addObject("newsPage", pageInfo);
        modelAndView.addObject("pre", pre);
        modelAndView.addObject("next", next);
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("video-show");
        return modelAndView;
    }


    /**
     * 联系我们
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("/contact-us")
    public ModelAndView contactUsTrends(ModelAndView modelAndView) {
        PageHelper.startPage(1, 100);
        List<ContactUs> contactUses = null;
        PageInfo pageInfo = null;
        contactUses = contactUsMapper.findByCondition1(0);
        pageInfo = new PageInfo<>(contactUses);
        //查询第二组
        List<ContactUs> contactUses1 = null;
        PageInfo pageInfo1 = null;
        contactUses1 = contactUsMapper.findByCondition1(1);
        pageInfo1 = new PageInfo<>(contactUses1);
        //查询第三组
        List<ContactUs> contactUses3 = null;
        PageInfo pageInfo3 = null;
        contactUses3 = contactUsMapper.findByCondition1(2);
        pageInfo3 = new PageInfo<>(contactUses3);
        //查询第四组
        List<ContactUs> contactUses4 = null;
        PageInfo pageInfo4 = null;
        contactUses4 = contactUsMapper.findByCondition1(3);
        pageInfo4 = new PageInfo<>(contactUses4);
        modelAndView.addObject("newsPage", pageInfo);//公司信息
        modelAndView.addObject("newsPage1", pageInfo1);//地图公司信息
        modelAndView.addObject("newsPage3", pageInfo3);//线路信息
        modelAndView.addObject("newsPage4", pageInfo4);//二维码管理
        modelAndView.addObject("hotline", hotline());//全国服务热线
        modelAndView.addObject("person", person());//负责人
        modelAndView.setViewName("contact-us");
        return modelAndView;
    }


}
