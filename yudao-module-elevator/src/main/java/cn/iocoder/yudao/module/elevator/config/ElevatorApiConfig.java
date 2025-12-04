package cn.iocoder.yudao.module.elevator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ElevatorApiConfig {

    @Bean
    public RestTemplate elevatorRestTemplate() {
        return new RestTemplate();
    }
}
