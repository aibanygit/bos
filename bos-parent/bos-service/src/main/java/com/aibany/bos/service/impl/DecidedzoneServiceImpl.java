package com.aibany.bos.service.impl;

import com.aibany.bos.dao.IDecidedzoneDao;
import com.aibany.bos.dao.ISubareaDao;
import com.aibany.bos.domain.Decidedzone;
import com.aibany.bos.domain.Subarea;
import com.aibany.bos.service.IDecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 百鹤 on 2017/6/30.
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService{

    @Autowired
    private IDecidedzoneDao decidedzoneDao;

    @Autowired
    private ISubareaDao subareaDao;

    /**
     * 添加定区， 关联分区
     * @param model
     * @param subareaid
     */
    @Override
    public void save(Decidedzone model, String[] subareaid) {

        decidedzoneDao.save(model);
        for (String sid : subareaid) {
            Subarea subarea = subareaDao.findById(sid);
            subarea.setDecidedzone(model);
            //subareaDao.saveOrUpdate(subarea); //会自动更新
        }

    }
}
