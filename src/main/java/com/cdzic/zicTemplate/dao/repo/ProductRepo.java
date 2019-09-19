package com.cdzic.zicTemplate.dao.repo;

import com.cdzic.zicTemplate.domain.News;
import com.cdzic.zicTemplate.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Date 2019/04/11 下午 03:54
 * @Author 靳东
 * @ClassName ProductRepo
 */
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Modifying
    @Query("update Product set headline=?1,thumbnail=?2,productType=?3,productContent=?4 where id=?5")
    int updateProductById(String headline, String thumbnail, int productType, String productContent, Long id);

    List<Product> findOneById(Long id);

    List<Product> findProductByProductType(int productType);

    Product findProductById(Long id);
}
