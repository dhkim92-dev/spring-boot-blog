package kr.dohoonkim.blog.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*@TODO
JWT Token 구현 후 필터 체인 등록 과정 필요
 */
@Configuration
public class SecurityConfigure{

  @Bean
  public BCryptPasswordEncoder defaultPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .formLogin().disable()
            .csrf().disable()
            .cors().disable()
            .headers().disable()
            .build();
  }

  @Bean
  public WebSecurityCustomizer configure(){
//    @TODO /api/** 삭제
     return (web) -> web.ignoring().requestMatchers(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console",
            "/api/**" // 임시
    );
  }
}
