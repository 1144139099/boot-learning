package com.hlh.datasources.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.hlh.datasources.mapper.primary",sqlSessionTemplateRef="primarySqlSessionTemplate")
public class PrimaryDataSourceConfig {
    @Bean(name="primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

@Bean(name = "primarySqlSessionFactory")
@Primary
public SqlSessionFactory primarySqlSessionFactory(
        @Qualifier("primaryDataSource") DataSource dataSource)
    throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
bean.setDataSource(dataSource);
//设置XML文件存放位置
bean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources("classpath:mapper/primary/*.xml"));
return bean.getObject();
    }
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory")
                    SqlSessionFactory sqlSessionFactory)throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    }
