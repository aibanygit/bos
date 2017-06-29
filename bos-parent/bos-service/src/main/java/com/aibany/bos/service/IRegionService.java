package com.aibany.bos.service;

import com.aibany.bos.domain.Region;
import com.aibany.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/28.
 */
public interface IRegionService {

    void saveRegionList(List<Region> regionList);

    void pageQuery(PageBean pageBean);

    List<Region> findAll();

    List<Region> findListByKey(String q);
}
