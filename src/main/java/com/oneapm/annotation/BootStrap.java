package com.oneapm.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.oneapm.annotation.service.OutterService;
import com.oneapm.tmp.SingleTransactionConfig;

public class BootStrap {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SingleTransactionConfig.class);
        OutterService outterService = (OutterService) ctx.getBean("outterService");
        outterService.case_02();
        ctx.destroy();
    }
    
}
