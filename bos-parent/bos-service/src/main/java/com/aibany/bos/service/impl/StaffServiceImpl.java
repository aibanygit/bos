package com.aibany.bos.service.impl;

import com.aibany.bos.dao.IStaffDao;
import com.aibany.bos.domain.Staff;
import com.aibany.bos.service.IStaffService;
import com.aibany.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
       if (StringUtils.isNotEmpty(ids)) {
           String[] staffIds = ids.split(",");
           for (String id: staffIds) {
                staffDao.executeUpdate("staff.delete",id);
           }
       }
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }
}
