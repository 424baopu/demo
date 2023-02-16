package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: liushuangyu
 * @Date: 2023/2/15 14:09
 * @Description: 自定义数据库配置
 */
@Slf4j
@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
@EnableConfigurationProperties({DataSourceProperties.class,MybatisProperties.class})
public class DataSourceConfig {

    static final String PACKAGE = "com.example.demo.mapper";

    private final DataSourceProperties dataSourceProperties;

    private final MybatisProperties mybatisProperties;

    public DataSourceConfig(DataSourceProperties dataSourceProperties, MybatisProperties mybatisProperties) {
        this.dataSourceProperties =  dataSourceProperties;
        this.mybatisProperties = mybatisProperties;
    }

    @Bean
    public DataSource druidDataSource() {
        log.info("==== init datasource begin ====");
        String userName = this.dataSourceProperties.getUsername();
        String password = this.dataSourceProperties.getPassword();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.dataSourceProperties.getUrl());
        dataSource.setDriverClassName(this.dataSourceProperties.getDriverClassName());
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager masterTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public SqlSessionFactory masterSqlSessionFactory() {
        log.info("==== init sqlSessionFactory begin ====");

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        try {
            bean.setDataSource(druidDataSource());
            bean.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
            bean.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
            return bean.getObject();
        } catch (Exception var9) {
            log.error("init sqlSessionFactory fail", var9);
            throw new RuntimeException("sqlSessionFactory of mybatis init fail", var9);
        } finally {
            log.info("==== init sqlSessionFactory end ====");
        }
    }
}
