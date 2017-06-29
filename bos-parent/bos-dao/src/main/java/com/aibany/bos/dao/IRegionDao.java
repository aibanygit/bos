package com.aibany.bos.dao;

import com.aibany.bos.dao.base.IBaseDao;
import com.aibany.bos.domain.Region;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/28.
 */
public interface IRegionDao extends IBaseDao<Region>{

    List<Region> findListByKey(String q);
}
