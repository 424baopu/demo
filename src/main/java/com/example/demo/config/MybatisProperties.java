package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: liushuangyu
 * @Date: 2023/2/16 12:58
 * @Description:
 */
@ConfigurationProperties(
        prefix = "my.mybatis"
)
@Data
public class MybatisProperties {
    private String[] mapperLocations;
    private String typeAliasesPackage;

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public Resource[] resolveMapperLocations() {
        return (Resource[]) Stream.of((String[]) Optional.ofNullable(this.mapperLocations).orElse(new String[0])).flatMap((location) -> {
            return Stream.of(this.getResources(location));
        }).toArray((x$0) -> {
            return new Resource[x$0];
        });
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException var3) {
            return new Resource[0];
        }
    }
}
