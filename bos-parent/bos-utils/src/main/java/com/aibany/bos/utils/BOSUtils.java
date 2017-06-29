package com.aibany.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.aibany.bos.domain.User;

import java.util.Arrays;

/**
 * BOS项目的工具类
 * @author zhaoqx
 *
 */
public class BOSUtils {

    /**
     * 获取session对象
     */
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }

    /**
     *  获取登录用户对象
     */
    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }

    /**
     * 获取城市编码
     * 例如：将河北省石家庄市桥西区，转为 HBSJZQX
     */
    public static String getShortCode(String province, String city, String district) {

        if (StringUtils.isNotEmpty(province) && StringUtils.isNotEmpty(city) && StringUtils.isNotEmpty(district)) {

            province = province.substring(0,province.length() - 1);
            city = city.substring(0,city.length() - 1);
            district = district.substring(0,district.length() - 1);

            String join = province + city + district;
            String[] headers = PinYin4jUtils.getHeadByString(join,true);
            return  StringUtils.join(headers,"");
        }
        return null;
    }

    public static String getCityCode(String city) {
        if (StringUtils.isNotEmpty(city)) {
            return PinYin4jUtils.hanziToPinyin(city, "");
        }
        return null;
    }
}
