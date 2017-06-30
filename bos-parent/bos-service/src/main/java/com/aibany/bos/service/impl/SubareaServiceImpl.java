package com.aibany.bos.service.impl;

import com.aibany.bos.dao.ISubareaDao;
import com.aibany.bos.domain.Subarea;
import com.aibany.bos.service.ISubareaService;
import com.aibany.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/29.
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService{

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void save(Subarea model) {

        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    @Override
    public List<Subarea> findValidList() {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(detachedCriteria);
    }
}
