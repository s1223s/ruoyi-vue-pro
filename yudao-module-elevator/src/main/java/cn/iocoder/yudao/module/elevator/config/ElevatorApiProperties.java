package cn.iocoder.yudao.module.elevator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elevator.api")
@Data
public class ElevatorApiProperties {

    private String callAppKey;
    private String callAppSecretKey;
    private String corpId;
    private String baseUrl;

}
