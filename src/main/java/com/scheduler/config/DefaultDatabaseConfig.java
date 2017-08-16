package com.scheduler.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Created by cloud4u on 2016-04-05.
 */
@Configuration
@EnableTransactionManagement
@Lazy
@MapperScan("com.scheduler.mapper")
@EnableConfigurationProperties({ConfigProperties.class})
public class DefaultDatabaseConfig implements TransactionManagementConfigurer{
    @Autowired
    private ConfigProperties configProperties;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        String driverClassName = configProperties.getDataSource().get("driverClassName").toString();
        String url = configProperties.getDataSource().get("url").toString();
        String username = configProperties.getDataSource().get("username").toString();
        String password = configProperties.getDataSource().get("password").toString();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String mybatisConfigPath = configProperties.getMybatis().get("configPath").toString();

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource(mybatisConfigPath));
        return bean.getObject();
    }

    @Bean(destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
