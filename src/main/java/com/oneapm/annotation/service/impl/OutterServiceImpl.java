package com.oneapm.annotation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oneapm.annotation.service.InnerService;
import com.oneapm.annotation.service.OutterService;
import com.oneapm.annotation.test.domain.TblA;
import com.oneapm.annotation.test.domain.TblB;
import com.oneapm.annotation.test.mapper.TblAMapper;
import com.oneapm.annotation.test.mapper.TblBMapper;

@Service(value = "outterService")
public class OutterServiceImpl implements OutterService {
    
    @Autowired
    private TblAMapper   tblAMapper;
    
    @Autowired
    private TblBMapper   tblBMapper;
    
    @Autowired
    private InnerService innerService;
    
    @Override
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
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
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
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void single_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            TblA ra2 = new TblA();
            ra2.setA1(1);
            ra2.setA2("aa");
            tblAMapper.insertSelective(ra2);
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            TblA ra2 = new TblA();
            ra2.setA1(1);
            ra2.setA2("aa");
            tblAMapper.insertSelective(ra2);
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void single_02() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.single_02();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void single_03() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.single_03();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_02() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.multi_02();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_03() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.multi_03();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_04() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.multi_04();
        } catch (Exception ignore) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
}
