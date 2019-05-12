package com.htsoft.szq.service.importdata.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htsoft.szq.common.excelutil.MyCond;
import com.htsoft.szq.common.excelutil.excelutil.MMS_PresourcFExcelToTable;
import com.htsoft.szq.common.excelutil.xmlutils.Node;
import com.htsoft.szq.common.excelutil.xmlutils.TableInfo;
import com.htsoft.szq.common.excelutil.xmlutils.XmlParser;
import com.htsoft.szq.common.parainit.CSParaMap;
import com.htsoft.szq.dao.CsOpMainMapper;
import com.htsoft.szq.dao.ImportDataSqlMapper;
import com.htsoft.szq.model.CsOpMain;
import com.htsoft.szq.model.CsRefPara;
import com.htsoft.szq.service.importdata.IImportDataService;

@Service(value="importDataService")
public class ImportDataServiceImpl implements IImportDataService {
	@Resource
	private ImportDataSqlMapper importDataMapper;
	@Resource
	private CsOpMainMapper csOpMainMapper;	
	// 日志
	private static Logger log = Logger.getLogger(ImportDataServiceImpl.class);

	/**
	 * 导入数据信息到临时表
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void importExcelToTable(MMS_PresourcFExcelToTable excelData,String operateNo,String xmlFName) throws Exception {
			//try {
				MMS_PresourcFExcelToTable.exceptionFlag = 0;
				XmlParser s = new XmlParser(); // 读取XML文件
				String filePath = ServletActionContext.getServletContext().getRealPath("xml");
				filePath =filePath +"\\"+xmlFName;
				List nodexmlList = s.readXml(filePath);
				if (nodexmlList == null && nodexmlList.size() <= 0) {
					System.out.println(filePath+"文件中未找到任何导入节点数据");
					throw new Exception(filePath+"文件中未找到任何导入节点数据");
				}
				excelData.setNodexmlList(nodexmlList); // 设置导入表及字段信息
				List<String> eTitleList = new ArrayList<String>();
		        for(int i = 0;i<nodexmlList.size();i++){
					TableInfo tableInfo = (TableInfo) nodexmlList.get(i);   //由于一个EXCEL对应多个表的情况,反向操作才会可以删除
					//String tableName = tableInfo.getTableName();
					List nodeList = tableInfo.getList();
					for (Iterator iterator = nodeList.iterator(); iterator.hasNext();) {
						Node object = (Node) iterator.next();						
						String ename = object.getExcelColumnName();						
						eTitleList.add(ename);	            
					}
		        }
		        this.clearTbByMap(operateNo);  //根据操作号清除临时表中数据
				excelData.seteTitleList(eTitleList);
				// String operateNo = DateUtil.getCurDateTime(); // 得到当前时间
				excelData.setOperateNo(operateNo); // 设置操作号
				excelData.setImportDataMapper(importDataMapper);
				excelData.processByRow(1);// 处理第一个sheet，sheet索引从1开始
				if(MMS_PresourcFExcelToTable.exceptionFlag==1){
					throw new Exception("上传的Excel文件格式不合法");
				}else if(MMS_PresourcFExcelToTable.exceptionFlag==-1){
					throw new Exception("事务操作号"+operateNo+"与Excel文档操作号不同，请核对后再上传");
				}
				//excelData.getRowCount();
				
//				//文件移动到历史文件夹
//				String descFileName = TConstants.HISTORYMATPATH + operateNo
//						+ "_" + fNameList.get(i).toString();
//				FileUtils.moveFile(fileName, descFileName);
//			} catch (Exception e) {
//				log.error(tableMap1);
//				CsOpMain bomImportDataLog = importLogService.getCsOpMainByOptNo(operateNo);
//		        String operator = ContextUtil.getCurrentUser().getUsername(); // 取得当前用户名
//		        bomImportDataLog.setOperator(operator);
//		        bomImportDataLog.setCreateDT(DateUtil.getCurrentDateTime());
//		        bomImportDataLog.setOpStatus(CSParaMap.getParaValueByCode("OpStauts.06")); // 转临时表失败
//		        bomImportDataLog.setMemo(DateUtil.getCurrentDateTime()+" 操作号"+operateNo+"转临时表失败\\n"+tableMap1);				
//		        importLogService.update(bomImportDataLog); // 更新日志
//				e.printStackTrace();  //添加日志处理
//			}
//			return true;
	}
	@Override
	public boolean clearTb(String operateNo) {					
		CsOpMain csOpMain = csOpMainMapper.getCsOpMainByOptNo(operateNo);
		if (csOpMain != null) {
			CsRefPara csRefPara = CSParaMap.getParaVOByCode(csOpMain.getOperateType());
			String xmlFName = csRefPara.getRemark();
			
			XmlParser s = new XmlParser(); // 读取XML文件
			String filePath = ServletActionContext.getServletContext().getRealPath("xml");
			filePath =filePath +"\\"+xmlFName;
			List nodexmlList = s.readXml(filePath);
			if (nodexmlList == null && nodexmlList.size() <= 0) {
				System.out.println("未找到任何导入节点数据");
				return false;
			}
	        for(int i = nodexmlList.size()-1;i>=0;i--){
				TableInfo tableInfo = (TableInfo) nodexmlList.get(i);   //由于一个EXCEL对应多个表的情况,反向操作才会可以删除
				String tableName = tableInfo.getTableName();
				importDataMapper.clearTb(tableName);	            
	        }
			return true;
		}
		return false;
	}
	/**
	 * 动态查询
	 * @param map
	 * @return
	 */
	public List getDateByMap(Map<String, Object> map){
		List list = importDataMapper.getDateByMap(map);
		return list;
	}
	@Override
	public boolean clearTbByMap(String operateNo) {
		CsOpMain csOpMain = csOpMainMapper.getCsOpMainByOptNo(operateNo);
		if (csOpMain != null) {
			CsRefPara csRefPara = CSParaMap.getParaVOByCode(csOpMain.getOperateType());
			String xmlFName = csRefPara.getRemark();
			
			XmlParser s = new XmlParser(); // 读取XML文件
			String filePath = ServletActionContext.getServletContext().getRealPath("xml");
			filePath =filePath +"\\"+xmlFName;
			List nodexmlList = s.readXml(filePath);
			if (nodexmlList == null && nodexmlList.size() <= 0) {
				System.out.println("未找到任何导入节点数据");
				return false;
			}
	        for(int i = nodexmlList.size()-1;i>=0;i--){
				TableInfo tableInfo = (TableInfo) nodexmlList.get(i);   //由于一个EXCEL对应多个表的情况,反向操作才会可以删除
				String tableName = tableInfo.getTableName();
				Map map = new HashMap();
				map.put("tableName", tableName);
				MyCond mycond = new MyCond();
				mycond.setFlied("operateNo");
				mycond.setValue("'"+operateNo+"'");
				List cond = new ArrayList();
				cond.add(mycond);
				map.put("cond", cond);
				importDataMapper.clearTbByMap(map);	            
	        }
			return true;
		}
		return false;
	}
}
