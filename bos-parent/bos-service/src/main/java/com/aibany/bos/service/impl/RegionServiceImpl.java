package com.aibany.bos.service.impl;

import com.aibany.bos.dao.IRegionDao;
import com.aibany.bos.domain.Region;
import com.aibany.bos.service.IRegionService;
import com.aibany.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/28.
 */
@Service
@Transactional  /*事务注解*/
public class RegionServiceImpl implements IRegionService{

    @Autowired
    private IRegionDao regionDao;

    @Override
    public void saveRegionList(List<Region> regionList) {

        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public List<Region> findListByKey(String q) {
        if (StringUtils.isNotEmpty(q)) {
            return regionDao.findListByKey(q);
        }
        return null;
    }
}
