package id.co.bca.spring.hellospring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("prod")
@Configuration
public class ProdConfig {

    @PostConstruct
    public void test(){
        System.out.println("in production environment!");
    }
}
