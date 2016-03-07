package com.oneapm.annotation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.oneapm" }, includeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { Service.class }) })
public class DataSourceConfig implements TransactionManagementConfigurer {
    
    private String  url                           = "jdbc:mysql://127.0.0.1:3312/test?characterEncoding=utf8&amp;useUnicode=true";
    
    private String  username                      = "root";
    
    private String  password                      = "";
    
    private String  dbType                        = "mysql";
    
    private String  filters                       = "wall,stat,config";
    
    private String  connectionProperties          = "config.decrypt=false";
    
    private int     maxActive                     = 100;
    
    private int     initialSize                   = 5;
    
    private long    maxWaitMillis                 = 60000;
    
    private int     minIdle                       = 5;
    
    private long    timeBetweenEvictionRunsMillis = 60000;
    
    private long    minEvictableIdleTimeMillis    = 300000;
    
    private String  validationQuery               = "SELECT 'x'";
    
    private boolean testWhileIdle                 = true;
    
    private boolean testOnBorrow                  = true;
    
    private boolean testOnReturn                  = false;
    
    private int     maxOpenPreparedStatements     = 20;
    
    private boolean removeAbandoned               = true;
    
    private int     removeAbandonedTimeout        = 1800;
    
    private boolean logAbandoned                  = true;
    
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        //
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDbType(dbType);
        try {
            dataSource.setFilters(filters);
        } catch (SQLException ignore) {
            // skip
        }
        dataSource.setConnectionProperties(connectionProperties);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWaitMillis);
        dataSource.setMinIdle(minIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        dataSource.setRemoveAbandoned(removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        dataSource.setLogAbandoned(logAbandoned);
        //
        return dataSource;
    }
    
    private String mapperLocation = "classpath*:META-INF/test/*Mapper.xml";
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() {
        //
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        try {
            sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        } catch (IOException ignore) {
            // skip
        }
        //
        return sqlSessionFactory;
    }
    
    private static String basePackage           = "com.oneapm.annotation.test.mapper";
    
    private static String sqlSessionFactoryName = "sqlSessionFactory";
    
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        //
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(basePackage);
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(sqlSessionFactoryName);
        //
        return mapperScannerConfigurer;
    }
    
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        //
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        //
        return transactionManager;
    }
    
    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor() {
        //
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager());
        Properties transactionAttributes = new Properties();
        transactionAttributes.put("*", "PROPAGATION_REQUIRED,-Exception");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        //
        return transactionInterceptor;
    }
    
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        //
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("tblAMapper", "tblBMapper");
        beanNameAutoProxyCreator.setInterceptorNames("transactionInterceptor");
        //
        return beanNameAutoProxyCreator;
    }
    
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
    
}
