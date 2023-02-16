package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: liushuangyu
 * @Date: 2023/2/15 14:06
 * @Description:
 */

@ConfigurationProperties(
        prefix = "my.datasource"
)
@Data
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
