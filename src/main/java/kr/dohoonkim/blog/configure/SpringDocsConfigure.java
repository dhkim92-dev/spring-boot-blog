package kr.dohoonkim.blog.configure;

import com.google.common.net.HttpHeaders;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsConfigure {
  @Bean
  public OpenAPI openAPI(@Value("${springdoc.version}") String version) {
    Info info = new Info()
            .title("Blog REST API 문서") // 타이틀
            .version(version) // 문서 버전
            .description("개인 블로그 백엔드 API") // 문서 설명
            .contact(new Contact() // 연락처
                    .name("dhkim92")
                    .email("dhkim92.dev@gmail.com")
                    .url("https://www.dohoon-kim.kr/"));

    // Security 스키마 설정
    SecurityScheme bearerAuth = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);

    // Security 요청 설정
    SecurityRequirement addSecurityItem = new SecurityRequirement();
    addSecurityItem.addList("JWT");

    return new OpenAPI()
            // Security 인증 컴포넌트 설정
//            .components(new Components().addSecuritySchemes("JWT", bearerAuth))
            // API 마다 Security 인증 컴포넌트 설정
//            .addSecurityItem(addSecurityItem)
            .info(info);
  }
}