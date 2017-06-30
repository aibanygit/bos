package com.aibany.bos.service;

import com.aibany.bos.domain.Subarea;
import com.aibany.bos.utils.PageBean;

import java.util.List;

/**
 * Created by 百鹤 on 2017/6/29.
 */
public interface ISubareaService {

    void save(Subarea model);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findValidList();

}
