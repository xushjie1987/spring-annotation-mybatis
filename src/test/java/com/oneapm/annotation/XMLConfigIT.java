package com.oneapm.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oneapm.annotation.service.InnerService;
import com.oneapm.annotation.service.OutterService;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/beans.xml" })
public class XMLConfigIT {
    
    @Autowired
    private TblAMapper    tblAMapper;
    
    @Autowired
    private TblBMapper    tblBMapper;
    
    @Autowired
    private OutterService outterService;
    
    @Autowired
    private InnerService  innerService;
    
    @Test
    public void case_01() {
        outterService.case_01();
    }
    
    @Test
    public void case_02() {
        outterService.case_02();
    }
    
}
