package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.Product;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2019/04/11 下午 03:53
 * @Author 靳东
 * @ClassName ProductController
 */
@RestController
@RequestMapping("bg")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    /**
     * 添加产品
     *
     * @param thumbnail
     * @param headline
     * @param productType
     * @param productContent
     * @return
     */
    @PostMapping("/product/add")
    public ResponseObj productAdd(@RequestParam String thumbnail,
                                  @RequestParam String headline,
                                  @RequestParam Integer productType,
                                  @RequestParam String productContent) {
        try {
            productService.save(thumbnail, headline, productType, productContent);
        } catch (Exception e) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "添加失败！", null);
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加成功！", null);
    }

    /**
     * 查询产品
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/product/page")
    public ResponseObj productQuery(@RequestParam int page,
                                    @RequestParam int limit,
                                    @RequestParam(required = false, value = "searchKey") String searchKey,
                                    @RequestParam(required = false, value = "searchValue") String searchValue,
                                    @RequestParam(required = false, value = "searchKey2") String searchKey2,
                                    @RequestParam(required = false, value = "searchValue2") String searchValue2) {

        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Product> list = productService.findByCondition(searchKey, searchValue,searchKey2,searchValue2);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }

    /**
     * 修改产品
     *
     * @param headline
     * @param thumbnail
     * @param productType
     * @param productContent
     * @param id
     * @return
     */
    @PostMapping("/product/update")
    public ResponseObj productUpdate(@RequestParam String headline,
                                     @RequestParam String thumbnail,
                                     @RequestParam int productType,
                                     @RequestParam String productContent,
                                     @RequestParam Long id) {

        productService.updateProductById(headline, thumbnail, productType, productContent, id);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改成功", null);
    }

    /**
     * 根据id删除产品
     *
     * @param id
     * @return
     */
    @PostMapping("/product/delete")
    public ResponseObj productDelete(@RequestParam Long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        try {
            productService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
        }
        return responseObj;
    }


}
