package com.oneapm.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oneapm.annotation.test.domain.TblA;
import com.oneapm.annotation.test.domain.TblB;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@Service(value = "outterService")
public class OutterService {
    
    @Autowired
    private TblAMapper   tblAMapper;
    
    @Autowired
    private TblBMapper   tblBMapper;
    
    @Autowired
    private InnerService innerService;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.case_01();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(1);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_02() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.case_02();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(1);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
}
