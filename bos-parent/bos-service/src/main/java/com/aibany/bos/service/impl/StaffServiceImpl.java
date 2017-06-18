package com.aibany.bos.service.impl;

import com.aibany.bos.dao.IStaffDao;
import com.aibany.bos.domain.Staff;
import com.aibany.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mac on 2017/6/18.
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService{

    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }
}
