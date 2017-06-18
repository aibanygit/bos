package com.aibany.bos.web.action;

import com.aibany.bos.domain.Staff;
import com.aibany.bos.service.IStaffService;
import com.aibany.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by mac on 2017/6/18.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

    @Autowired
    private IStaffService staffService;

    /**
     * 添加取派员
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }
}
