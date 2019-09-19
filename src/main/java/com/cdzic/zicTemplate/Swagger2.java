package com.cdzic.zicTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
//    访问http://localhost:port/swagger-ui.html

//    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public String putUser(@PathVariable Long id, @RequestBody User user) {
//        User u = users.get(id);
//        u.setName(user.getName());
//        u.setAge(user.getAge());
//        users.put(id, u);
//        return "success";
//    }

//    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
//    @RequestMapping(value="/{id}", method=RequestMethod.GET)
//    public User getUser(@PathVariable Long id) {
//        return users.get(id);
//    }

//    @Api用在类上，说明该类的作用
//     接口注释@ApiOperation


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cdzic.zicTemplate.web.")) //为当前包路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("成都子程后台模板APIs")//页面标题
//                .description("学生管理系统接口") //描述
                .description("好时光视频全网视频管理系统接口") //描述
                .version("1.0")//版本号
                .build();
    }
}
