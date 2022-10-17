package id.co.bca.spring.hellospring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api")
public class Api {
    private String url;
    private String dataType;

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public String getDataType() {return dataType;}

    public void setDataType(String dataType) {this.dataType = dataType;}
}
