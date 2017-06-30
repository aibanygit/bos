package com.aibany.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.aibany.bos.domain.Staff;
import com.aibany.bos.utils.FileUtils;
import com.aibany.bos.utils.PageBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.ServletOutputStream;

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

			String json = JSON.toJSONString(object);
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

	public  void  outputJson(Object object, String[] excludes) {

		if (object == null) {
			return;
		}

		SimplePropertyPreFilter filter = null;
		if (excludes != null) {
			filter = new SimplePropertyPreFilter();
			for (String propertyName : excludes) {
				filter.getExcludes().add(propertyName);
			}
		}

		String json = "";
		if (object instanceof List) {
			json = JSONArray.toJSONString(object,filter,SerializerFeature.DisableCircularReferenceDetect);
		}else {
			json = JSONObject.toJSONString(object, filter,SerializerFeature.DisableCircularReferenceDetect);
		}

		try {
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void  outputXls(String fileName, String[] headers, List<List<Object>> datas) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(fileName);

			if (headers != null) {
				//title
				HSSFRow headrow = sheet.createRow(0);
				for (String head : headers) {
					headrow.createCell(headrow.getLastCellNum()+1).setCellValue(head);
				}
			}

			if (datas != null) {
				for (List<Object> rowdata : datas) {
					HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
					for (Object value : rowdata) {
						row.createCell(row.getLastCellNum()+1).setCellValue(value+"");
					}
				}
			}

			String file_name = fileName + ".xls";
			String filetype = ServletActionContext.getServletContext().getMimeType(file_name); //"application/vnd.ms-excel"
			String encodedFilename = FileUtils.encodeDownloadFilename(file_name,ServletActionContext.getRequest().getHeader("User-Agent"));

			ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setContentType(filetype + ",charset=utf-8");
			ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename=" + encodedFilename);

			workbook.write(out);


		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outputDoc(String filename,  List<List<Object>> datas) {

		try {
			if (filename != null && datas != null) {

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
