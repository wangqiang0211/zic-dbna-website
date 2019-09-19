package com.cdzic.zicTemplate;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfiguration {
    /**
     * 注册MyBatis分页插件PageHelper
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
//        System.out.println("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");//禁用合理化 超过最大页数后返回最后一页数据
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
