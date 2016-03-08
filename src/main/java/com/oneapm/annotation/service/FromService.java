package com.oneapm.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oneapm.annotation.test.domain.TblA;
import com.oneapm.annotation.test.domain.TblB;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@Service(value = "fromService")
public class FromService {
    
    @Autowired
    private TblAMapper tblAMapper;
    
    @Autowired
    private TblBMapper tblBMapper;
    
    @Autowired
    private ToService  toService;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_03() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            toService.case_03();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_04() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            toService.case_04();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_05() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            toService.case_05();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_06() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            toService.case_06();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
}
