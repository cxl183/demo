package com.htsoft.szq.service.importdata;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.htsoft.szq.common.excelutil.excelutil.MMS_PresourcFExcelToTable;

/**
 * 导入数据管理Service
 * @author Administrator
 *
 */
public interface IImportDataService {

	/**
	 * 导入数据信息到临时表
	 * @throws Exception 
	 */
	public void importExcelToTable(MMS_PresourcFExcelToTable excelData,String operateNo,String xmlFName) throws Exception;
	/**
	 * 根据操作号清空临时表
	 * @param operateNo
	 */
	public boolean clearTb(String operateNo);
	/**
	 * 动态查询
	 * @param map
	 * @return
	 */
	public List getDateByMap(Map<String, Object> map);
	/**
	 * 动态删除表名，条件   key ="tableName" value=""
	 *                key = "cond" value="MyCond"
	 * @param map
	 */
	boolean clearTbByMap(String operateNo);
}
