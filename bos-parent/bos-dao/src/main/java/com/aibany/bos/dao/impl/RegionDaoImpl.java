package com.aibany.bos.dao.impl;

import com.aibany.bos.dao.IRegionDao;
import com.aibany.bos.dao.base.impl.BaseDaoImpl;
import com.aibany.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/28.
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao{

    @Override
    public List<Region> findListByKey(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? OR r.citycode LIKE ? OR r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list =  (List<Region>)this.getHibernateTemplate().find(hql,"%" + q + "%", "%" + q + "%","%" + q + "%","%" + q + "%","%" + q + "%");
        return list;
    }
}
