package com.oneapm.annotation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan(basePackages = { "com.oneapm.annotation" }, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Service.class }) })
public class MultiTransactionConfig implements TransactionManagementConfigurer {
    
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
    
    private static String basePackage = "com.oneapm.annotation.test.mapper";
    
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        //
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(basePackage);
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //
        return mapperScannerConfigurer;
    }
    
    @Bean(name = "txManager")
    public PlatformTransactionManager txManager() {
        //
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        // txManager.setGlobalRollbackOnParticipationFailure(false);
        //
        return txManager;
    }
    
    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor() {
        //
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(txManager());
        Properties transactionAttributes = new Properties();
        // transactionAttributes.put("*",
        // "PROPAGATION_REQUIRES_NEW,ISOLATION_DEFAULT,-Exception");
        transactionAttributes.put("*", "PROPAGATION_REQUIRED,ISOLATION_DEFAULT,-Exception");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        //
        return transactionInterceptor;
    }
    
    @Bean(name = "beanNameAutoProxyCreator")
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        //
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        beanNameAutoProxyCreator.setBeanNames(new String[] { "tblAMapper", "tblBMapper" });
        beanNameAutoProxyCreator.setInterceptorNames(new String[] { "transactionInterceptor" });
        //
        return beanNameAutoProxyCreator;
    }
    
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
    
}
