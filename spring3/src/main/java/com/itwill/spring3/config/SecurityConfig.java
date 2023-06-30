package com.itwill.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration // 스프링 컨테이너에서 빈(bean)으로 생성, 관리 - 필요한 곳에 의존성 주입.
public class SecurityConfig {
    
    // Spring Security ver.5 부터 비밀번호는 반드시 암호화해야함
    // 비밀번호를 암호화하지 않으면 HTTP 403(access denied, 접근 거부) 또는
    // HTTP 500(internal server error, 내부 서버 오류)가 발생함.
    // 비밀번호 인코더(Password Encoder) 객체를 반드시 bean으로 생성해야함
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //로그인할 때 사용할 임시 사용자(메모리에 임시 저장) 생성
    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        // 사용자 상세 정보
        UserDetails user1 = User
                .withUsername("user1") //로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("1111")) //비번
                .roles("UESR") // 사용자 권한(USER, ADMIN, ...)
                .build();
        
        UserDetails user2 = User
                .withUsername("user2") //로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("2222")) //비번
                .roles("UESR", "ADMIN") // 사용자 권한(USER, ADMIN, ...)
                .build();
        
        UserDetails user3 = User
                .withUsername("user3") //로그인할 때 사용할 사용자 이름
                .password(passwordEncoder().encode("3333")) //비번
                .roles("ADMIN") // 사용자 권한(USER, ADMIN, ...)
                .build();
        
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
    
    // Security Filter 설정 bean:
    // 로그인/로그아웃 설정
    // 로그인 페이지 설정 
    // 페이지 접근 권한 - 로그인 해야만 접근 가능한 페이지, 로그인 없이 접근 가능한 페이지
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF(Cross Site Request Forgery) 기능 활성화하면,
        // Ajax POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403 에러가 발생
        // -> CSRF 기능 비활성화
        http.csrf((csrf)->csrf.disable());
        
        // 로그인 페이지 설정 - 스프링에서 제공하는 기본 로그인 페이지를 사용.
        http.formLogin(Customizer.withDefaults());
        
        // 페이지 접근 권한 설정
        http.authorizeHttpRequests((authRequest) ->
                authRequest
                .requestMatchers("/post/create")
                .hasRole("USER")
                .requestMatchers("/**")
                .permitAll());
        
        return http.build();
    }
    
    
    
}
