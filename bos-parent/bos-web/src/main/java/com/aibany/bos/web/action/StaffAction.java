package com.aibany.bos.web.action;

import com.aibany.bos.domain.Staff;
import com.aibany.bos.service.IStaffService;
import com.aibany.bos.utils.PageBean;
import com.aibany.bos.web.action.base.BaseAction;
import com.alibaba.fastjson.JSON;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by mac on 2017/6/18.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

    @Autowired
    private IStaffService staffService;

    private String ids;

    /**
     * 添加取派员
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }

    /**
     * 修改取派员
     */
    public String edit() {

        Staff staff = staffService.findById(model.getId());

        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());

        staffService.update(staff);

        return LIST;
    }

    /**
     * 分页查询取派员信息
     */
    public String pageQuery() throws IOException{

        staffService.pageQuery(pageBean);
        outputJson(pageBean,new String[] {"decidedzones"});

        return NONE;
    }

    public String deleteBatch()  {
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     * 查询所有未删除的取派员
     */
    public String listajax() {

        List<Staff> list = staffService.findValidList();
        outputJson(list);
        return NONE;
    }


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
