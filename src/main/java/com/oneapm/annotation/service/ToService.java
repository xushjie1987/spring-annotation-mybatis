package com.oneapm.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oneapm.annotation.test.domain.TblA;
import com.oneapm.annotation.test.domain.TblB;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@Service(value = "toService")
public class ToService {
    
    @Autowired
    private TblAMapper tblAMapper;
    
    @Autowired
    private TblBMapper tblBMapper;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_03() {
        TblA ra = new TblA();
        ra.setA1(11);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRED");
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void case_04() {
        TblA ra = new TblA();
        ra.setA1(11);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRES_NEW");
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_05() {
        TblB rb = new TblB();
        rb.setB1(22);
        rb.setB2("bb");
        tblBMapper.insertSelective(rb);
        throw new RuntimeException("test for REQUIRED");
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void case_06() {
        TblB rb = new TblB();
        rb.setB1(22);
        rb.setB2("bb");
        tblBMapper.insertSelective(rb);
        throw new RuntimeException("test for REQUIRES_NEW");
    }
    
}
