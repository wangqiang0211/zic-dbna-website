package com.cdzic.zicTemplate.utils.sort;

import com.cdzic.zicTemplate.utils.sort.domain.SortDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @Date 2019/04/12 下午 01:52
 * @Author 靳东
 * @ClassName 查询排序工具类
 */
public class SortUtil {

    /**
     * 单排序封装
     * @param orderType //排序方式
     * @param orderField //排序字段
     * @return Sort
     */
    public static Sort basicSort(String orderType, String orderField) {
        Sort sort = new Sort(Sort.Direction.fromString(orderType), orderField);
        return sort;
    }

    /**
     * 无参方法则默认为id降序
     * @return
     */
    public static Sort basicSort() {
        return basicSort("desc", "id");
    }

    /**
     * 多排序封装
     * @param dtos //排序对象数组
     * @return
     */
    public static Sort basicSort(SortDto...dtos) {
        Sort result = null;
        for (int i = 0; i < dtos.length; i++) {
            SortDto dto = dtos[i];
            if (result == null) {
                result = new Sort(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField());
            } else {
                result = result.and(new Sort(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField()));
            }
        }
        return result;
    }

    public static Pageable basicPage(int page, int size, SortDto... dtos) {
        Sort sort = basicSort(dtos);
        page = (page <0) ? 0 : page;
        size = (size <= 0) ? 2 : size;
        Pageable pageable = new PageRequest(page, size, sort);
        return pageable;
    }


}
