package com.aibany.bos.web.action;

import com.aibany.bos.domain.Region;
import com.aibany.bos.service.IRegionService;
import com.aibany.bos.utils.BOSUtils;
import com.aibany.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 百鹤 on 2017/6/28.
 * 区域管理
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{

    private File regionFile;

    @Autowired
    private IRegionService regionService;

    private String ids;

    private String q;

    /**
     * 区域excel导入
     * @return
     */
    public String importXls() throws Exception{

        List<Region> regionList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            String shotcode = BOSUtils.getShortCode(province,city,district);
            String citycode = BOSUtils.getCityCode(city);


            Region region = new Region(id,province,city,district,postcode,shotcode,citycode,null);
            regionList.add(region);
        }
        regionService.saveRegionList(regionList);

        return NONE;
    }

    /**
     * 分页查询区域
     * @return
     */
    public String pageQuery() throws Exception{

        regionService.pageQuery(pageBean);
        outputJson(pageBean,new String[]{"region","decidedzone"});

        return NONE;
    }

    /**
     *
     * @return
     */
    public String listajax() {

        List<Region> listdata = null;
        if (StringUtils.isNotEmpty(q)) {
            listdata = regionService.findListByKey(q);
        }else {
            listdata = regionService.findAll();
        }
        outputJson(listdata);

        return NONE;
    }


    public File getRegionFile() {
        return regionFile;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
