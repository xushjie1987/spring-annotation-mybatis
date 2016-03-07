package com.oneapm.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.oneapm.annotation.service.OutterService;

public class BootStrap {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        OutterService outterService = (OutterService) ctx.getBean("outterService");
        outterService.case_02();
        ctx.destroy();
    }
    
}
