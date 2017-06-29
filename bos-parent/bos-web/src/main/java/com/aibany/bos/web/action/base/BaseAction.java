package com.aibany.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.aibany.bos.domain.Staff;
import com.aibany.bos.utils.PageBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 表现层通用实现
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	public static final String HOME = "home";
	public static final String LIST = "list";

	//分页
	protected int page;
	protected int rows;
	protected PageBean<T> pageBean = new PageBean<>();
	
	//模型对象
	protected T model;
	public T getModel() {
		return model;
	}
	
	//在构造方法中动态获取实体类型，通过反射创建model对象
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得BaseAction上声明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		//通过反射创建对象


		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);

		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void setPage(int page) {
		pageBean.setPageSize(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

	public void outputJson(Object object) {
		try {
			String json = JSON.toJSONString(pageBean);
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outputJson(List list) {
		try {
			String json = JSONArray.toJSONString(list);
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
