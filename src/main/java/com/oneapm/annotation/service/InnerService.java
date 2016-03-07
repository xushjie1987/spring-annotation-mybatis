package com.oneapm.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oneapm.annotation.test.domain.TblA;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@Service(value = "innerService")
public class InnerService {
    
    @Autowired
    private TblAMapper tblAMapper;
    
    @Autowired
    private TblBMapper tblBMapper;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_01() {
        TblA ra = new TblA();
        ra.setA1(2);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRED");
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void case_02() {
        TblA ra = new TblA();
        ra.setA1(2);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRES_NEW");
    }
    
}
