package com.aibany.bos.web.action;

import com.aibany.bos.domain.Subarea;
import com.aibany.bos.service.ISubareaService;
import com.aibany.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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

    public String pageQuery() {

        subareaService.pageQuery(pageBean);
        outputJson(pageBean);

        return NONE;
    }


}
