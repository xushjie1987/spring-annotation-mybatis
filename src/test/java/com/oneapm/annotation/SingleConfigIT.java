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
import com.oneapm.tmp.SingleTransactionConfig;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SingleTransactionConfig.class })
public class SingleConfigIT {
    
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
    
    /**
     * 部分提交，符合预期 <br>
     */
    @Test
    public void single_01() {
        outterService.single_01();
    }
    
    /**
     * REQUIRED <br>
     * 回滚，符合预期 <br>
     */
    @Test
    public void single_02() {
        outterService.single_02();
    }
    
    /**
     * REQUIRES_NEW <br>
     * 部分提交，符合预期 <br>
     */
    @Test
    public void single_03() {
        outterService.single_03();
    }
    
}
