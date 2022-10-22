package id.co.bca.spring.hellospring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.annotation.PostConstruct;

@Profile("dev")
@Configuration
public class DevConfig {
    @PostConstruct
    public void test(){
        System.out.println("In development environment!");
    }
}



