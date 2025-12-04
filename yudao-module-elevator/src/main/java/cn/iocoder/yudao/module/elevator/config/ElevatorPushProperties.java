package cn.iocoder.yudao.module.elevator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elevator.push")
@Data
public class ElevatorPushProperties {

    private String appKey;
    private String appSecretKey;
    private String corpId;

}
