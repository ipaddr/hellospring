package id.co.bca.spring.hellospring.config;

import id.co.bca.spring.hellospring.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
//        http
//                .csrf().disable()
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests()
//                .mvcMatchers(HttpMethod.GET, "/**").permitAll()
//                .mvcMatchers(HttpMethod.POST,"/**").hasAnyAuthority("admin","employee")
//                .mvcMatchers(HttpMethod.PUT, "/**").hasAuthority("admin")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll();
        return http.build();
    }
}

