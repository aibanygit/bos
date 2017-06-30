package com.aibany.bos.service;

import com.aibany.bos.domain.Staff;
import com.aibany.bos.utils.PageBean;

import java.util.List;

/**
 * Created by mac on 2017/6/18.
 */
public interface IStaffService {

    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);

    List<Staff> findValidList();
}
