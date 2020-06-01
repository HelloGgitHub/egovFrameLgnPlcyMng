package egovframework;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private String version;
    private String title;

	@Bean
    public Docket api() {
	    version = "V1";
	    title = "전자정부 클라우드 플랫폼 공통 API " + version;
		
        return new Docket(DocumentationType.SWAGGER_2)
        		.useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("egovframework.com"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(title, version))
               .pathMapping("/");
    }
	
    
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "전자정보 클라우드 개발 환경[로그인정책관리] API 테스트",
                version,
                null,
                null,
                null,
                null,
                new ArrayList<>());
    }
}
