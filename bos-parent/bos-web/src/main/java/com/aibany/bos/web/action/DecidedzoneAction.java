package com.aibany.bos.web.action;

import com.aibany.bos.domain.Decidedzone;
import com.aibany.bos.service.IDecidedzoneService;
import com.aibany.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by 百鹤 on 2017/6/30.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

    @Autowired
    private IDecidedzoneService decidedzoneService;

    private String[] subareaid;

    public String add() {
        decidedzoneService.save(model, subareaid);
        return LIST;
    }

    public String pageQuery()  {

        decidedzoneService.pageQuery(pageBean);
        outputJson(pageBean, new String[] {"subareas", "decidedzones"});

        return NONE;
    }

    public String[] getSubareaid() {
        return subareaid;
    }

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }
}
