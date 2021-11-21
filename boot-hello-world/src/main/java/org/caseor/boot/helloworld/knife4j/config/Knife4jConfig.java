package org.caseor.boot.helloworld.knife4j.config;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author Fu Kai
 * @since 20210604
 */

@Data
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
  private final static String BASE_PACKAGE = "org.casey.boot.knife4j.controller";

  @Bean
  public Docket defaultApi2() {
    return new Docket(DocumentationType.SWAGGER_2)
      .enable(true)
      .apiInfo(apiInfo())
      // .securitySchemes(apiKeyList())
      // .securityContexts(securityContextList())
      .groupName("1.0.0").select()
      .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .contact(new Contact("code2021", "http://github.com/code-2021", "code2021@bb.com"))
      .title("EPM Enterprise Personnel Management")
      .description("企业人事管理系统API")
      .termsOfServiceUrl("http://github.com/code-2021")
      .version("0.1.0")
      .build();
  }

  // private List<SecurityScheme> apiKeyList() {
  //   List<SecurityScheme> list = new ArrayList<>();
  //   list.add(new ApiKey("BearerToken", "Authorization", "header"));
  //   return list;
  // }
  //
  // private List<SecurityContext> securityContextList() {
  //   List<SecurityContext> list = new ArrayList<>();
  //   list.add(SecurityContext.builder()
  //     .securityReferences(defaultAuth())
  //     // 所有添加Authorization
  //     // .forPaths(PathSelectors.regex("/.*"))
  //     // 仅/epm/auth/token添加Authorization
  //     // .forPaths(PathSelectors.regex("/epm/auth/token"))
  //     // 仅/epm/auth/token不添加Authorization
  //     .forPaths(PathSelectors.regex("/epm/auth/token").negate())
  //     .build());
  //   return list;
  // }
  //
  // private List<SecurityReference> defaultAuth() {
  //   AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
  //   List<SecurityReference> list = new ArrayList<>();
  //   list.add(new SecurityReference("BearerToken", authorizationScopes));
  //   return list;
  // }

}
