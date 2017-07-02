package com.aibany.bos.service;


import com.aibany.bos.domain.Decidedzone;
import com.aibany.bos.utils.PageBean;

/**
 * Created by 百鹤 on 2017/6/30.
 */
public interface IDecidedzoneService {

    void save(Decidedzone model,  String[] subareaid);

    void pageQuery(PageBean pageBean);

}
