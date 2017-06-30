package com.aibany.bos.web.action;

import com.aibany.bos.domain.Region;
import com.aibany.bos.domain.Subarea;
import com.aibany.bos.service.ISubareaService;
import com.aibany.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 百鹤 on 2017/6/29.
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

    @Autowired
    private ISubareaService subareaService;

    public String add() {
        subareaService.save(model);
        return LIST;
    }

    public String exportXls() throws Exception{

        List<Subarea> list = subareaService.findAll();

        List<List<Object>> datalist = new ArrayList<>();
        for (Subarea subarea : list) {
            List<Object> row = new ArrayList<>();
            row.add(subarea.getId());
            row.add(subarea.getStartnum());
            row.add(subarea.getEndnum());
            row.add(subarea.getPosition());
            row.add(subarea.getRegion().getName());
            datalist.add(row);
        }

        outputXls("分区数据", new String[] {"分区编号","开始编号", "结束编号", "位置信息", "省市区"}, datalist);

        return NONE;
    }

    public String pageQuery() {

        //条件查询
        forCondtionQuery();

        subareaService.pageQuery(pageBean);
        outputJson(pageBean,new String[] {"subareas","decidedzone"});

        return NONE;
    }

    public String listajax() {

        List<Subarea> list = subareaService.findValidList();
        outputJson(list, new String[]{"region","decidedzone"});

        return NONE;
    }

    private void forCondtionQuery() {

        DetachedCriteria dc = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotEmpty(addresskey)) {
            dc.add(Restrictions.like("addresskey","%" + addresskey + "%"));
        }

        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();

            dc.createAlias("region", "r");

            if (StringUtils.isNotEmpty(province)) {
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }

            if (StringUtils.isNotEmpty(city)) {
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }

            if (StringUtils.isNotEmpty(district)) {
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
    }
}
