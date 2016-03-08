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
@ContextConfiguration(classes = { MultiTransactionConfig.class })
public class MultiConfigIT {
    
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
     * 回滚，应该符合预期 <br>
     */
    @Test
    public void multi_01() {
        outterService.multi_01();
    }
    
    /**
     * 回滚，符合预期 <br>
     */
    @Test
    public void multi_02() {
        outterService.multi_02();
    }
    
    /**
     * 不符合预期 <br>
     * 预期：部分提交 <br>
     * 实际：回滚 <br>
     * 猜测：存在bug <br>
     */
    @Test
    public void multi_03() {
        outterService.multi_03();
    }
    
    /**
     * 还是不符合预期 <br>
     * 怀疑：从调用者REQUIRED传递到被调用者REQUIRES_NEW在多层事务拦截器情形下无法把事务隔离开 <br>
     * REQUIRES_NEW事务还是传播进去了 <br>
     */
    @Test
    public void multi_04() {
        outterService.multi_04();
    }
    
}
