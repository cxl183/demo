package com.htsoft.szq.common.excelutil.excelutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.UUIDGenerator;
import com.htsoft.szq.common.excelutil.excelutil.excel.ExcelProcessor;
import com.htsoft.szq.common.excelutil.excelutil.excel.XRow;
import com.htsoft.szq.common.excelutil.excelutil.util.DateUtil;
import com.htsoft.szq.common.excelutil.xmlutils.Node;
import com.htsoft.szq.common.excelutil.xmlutils.TableInfo;
import com.htsoft.szq.common.parainit.CSParaMap;
import com.htsoft.szq.dao.ImportDataSqlMapper;
import com.htsoft.szq.model.CsOpMain;
import com.htsoft.szq.service.importdata.IImportDataLogService;

/**
 * 解析 用户Excel模板 类
 * 
 * @author Administrator
 * 
 */
public class MMS_PresourcFExcelToTable extends ExcelProcessor {
	public static int exceptionFlag=0;   //异常标志
	protected final Log log = LogFactory.getLog(getClass());
	private ImportDataSqlMapper importDataMapper;
	private IImportDataLogService importLogService;  //日志记录
	private List<TableInfo> nodexmlList; // 需要导入的表及列名字段
	private String operateNo; // 记录开始操作的时间做为操作号
	Map<String, Map> ttMap = new LinkedHashMap<String, Map>(); // 表及字段集合
	private int rowNum = 0; // 导入行数
	public static boolean formatFlag=false;   //文件合法标志
	private List eTitleList;   //Excel列头
	public MMS_PresourcFExcelToTable(String fileName) throws Exception {
		super(fileName);
	}

	DataImportTableRule ditr = new DataImportTableRule(-1, "GUID","GUID", ""); //主建规则
	DataImportTableRule ditr1 = new DataImportTableRule(-2, "OperateNo",
			"OperateNo", this.getOperateNo()); // 操作号规则
	
	DataImportTableRule importFromtRule = new DataImportTableRule(-3,
			"ImporFromt", "ImporFromt", "'Excel'"); // 导入BOM单来源

	DataImportTableRule importStatusRule = new DataImportTableRule(-4,
			"ImportStatus", "ImportStatus", "'New'"); // 导入BOM单状态

	@Override
	public void processRow(XRow row) {
		if(exceptionFlag==1){
			return;
		}
		Map tableMap = null;
		List<String> fieldName = null; // 表中字段名
		List<Integer> columnIndeList = null; // 列索引
		if(row.getRowIndex() == 1){
			ttMap.clear();
			//操作号与文件操作号是否相同，如果不同，不能导入
			if(!this.operateNo.trim().equals(row.getCell(1).getValue())){
				exceptionFlag=-1;
				formatFlag=true;
				System.out.println("事务操作号与文档操作号不同，请核对后再上传");
				return ;
			}
		}else if(row.getRowIndex() == 2){
//			for (int i = 0; i < row.getCellsSize(); i++) {  //设置操作号
//				if (2 == i) {
//					//System.out.println(row.getCell(2).getValue());
//					String str = row.getCell(i).getValue();
//					this.operateNo = str+"";
//					ditr1.setRuleValue(operateNo);
//					break;
//				}
//			}
		}else if (row.getRowIndex() == 3) { // 对比xml列头和Excel列头是否相匹配
	firstFor:for (int i = 0; i < row.getCellsSize(); i++) {
				String eTitle = row.getCell(i).getValue();
				for(int j = 0;j<eTitleList.size();j++){
					String xtitle = (String) eTitleList.get(i);
					if(xtitle.equals(eTitle)){
						break;
					}
					if((i==eTitleList.size()-1) &&!xtitle.equals(eTitle)){  //找到不一样的列头
						exceptionFlag=1;
						formatFlag=true;
						CsOpMain csOpMain = importLogService.getCsOpMainByOptNo(operateNo);
				        String operator = ContextUtil.getCurrentUser().getUsername(); // 取得当前用户名
				        csOpMain.setOperator(operator);
				        csOpMain.setCreateDT(DateUtil.getCurrentDateTime());
				        csOpMain.setOpStatus(CSParaMap.getParaValueByCode("OpStauts.06")); // 转临时表失败
				        csOpMain.setMemo(DateUtil.getCurrentDateTime()+" 操作号"+operateNo+"文件格式不合法\\n");				
				        importLogService.update(csOpMain); // 更新日志
						break firstFor;
					}
				}
		}
		if(formatFlag==false){	
			for (int j = 0; j < nodexmlList.size(); j++) {
				TableInfo tableInfo = (TableInfo) nodexmlList.get(j);
				String tableName = tableInfo.getTableName();
				if (tableName == null || "".equals(tableName)) {
					System.out.println("请检查XmL文件，tableName中的name表名为空");
					return;
				} else {
					tableMap = new HashMap();
					tableMap.put("tableName", tableName); // 装入表名
				}
				List nodelist = tableInfo.getList();
				if (nodelist == null || nodelist.size() <= 0) {
					System.out.println("请检查XmL文件，无列名");
					return;
				} else {
					fieldName = new ArrayList(); // 表中字段名List
					columnIndeList = new ArrayList();
				}
				for (int t = 0; t < nodelist.size(); t++) {
					Node node = (Node) nodelist.get(t);
					String ename = node.getExcelColumnName();
					String tname = node.getTableColumnName();
					String fieldFlag = node.getTypeValue();
					//主键编号
					if (ditr.getDirField().equals(fieldFlag)) {
						fieldName.add(tname);
						columnIndeList.add(ditr.getRuleNumber());
					}
					//操作编号
					if (ditr1.getDirField().equals(tname)) {
						fieldName.add(tname);
						columnIndeList.add(ditr1.getRuleNumber());
						ditr1.setRuleValue(this.getOperateNo());
					}
					for (int i = 0; i < row.getCellsSize(); i++) {
						String eTitle = row.getCell(i).getValue();
						if (ename.equals(eTitle)) {
							int columnIndex = row.getCell(i).getColumnIndex();
							fieldName.add(tname);
							columnIndeList.add(columnIndex - 65);
							break;
						}
					}
				}
				tableMap.put("fieldName", fieldName);
				tableMap.put("tcolumns", columnIndeList);
				String table = ("table" + j);
				ttMap.put(table, tableMap);
			}
		}
	} else {
		if (ttMap != null && formatFlag==false) {
			Set<String> key = ttMap.keySet();
			for (Iterator it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				Map tableMap1 = (Map) ttMap.get(s);
				List colIndexList = (List) tableMap1.get("tcolumns");
				List fieldValue = null;
				fieldValue = new ArrayList();
				if (colIndexList != null) {
					for (int b = 0; b < colIndexList.size(); b++) {
						Integer colIndex = (Integer) colIndexList.get(b);
						int cIndex = colIndex.intValue();
						if (cIndex == ditr.getRuleNumber()) {
							fieldValue.add(UUIDGenerator.getUUID());
							continue;
						}
						if (cIndex == ditr1.getRuleNumber()) {
							fieldValue.add(ditr1.getRuleValue());
							continue;
						}
						for (int i = 0; i < row.getCellsSize(); i++) {
							if (cIndex == i) {
								//System.out.println(row.getCell(cIndex).getValue());
								String str = row.getCell(cIndex).getValue();
								//if(str.indexOf("'")!=-1){
								str = str.replace("'", "");//替换‘为空字符
									//fieldValue.add(row.getCell(cIndex).getValue()+ "'");
								//}
								str = str.replace("\r","");//替换\r字符为空白字符
								//else{										
								//fieldValue.add("'"+ str+ "'");
								if("".equals(str.trim())){
									str = "";
								}
								fieldValue.add(str);
								//}
								break;
							}
						}
					}
				}
				tableMap1.put("fieldValue", fieldValue);
				tableMap1.remove("tcolumns");

				Map querymap = new HashMap();
				querymap.put("tableName", tableMap1.get("tableName").toString());
				List fieldNames = (List) tableMap1.get("fieldName");
				List fieldValues = (List) tableMap1.get("fieldValue");
				//导入EXCEL的API有BUG，用现在方法进行补丁来解决（最后一列为null时，在list中少元素），此处手动补上
				if(fieldNames!=null && fieldNames.size()>0 &&fieldValue!=null&&fieldValue.size()>0 ){
					int l = fieldNames.size()- fieldValue.size();  
					if(l>0){
						for(int i = 0;i<l;i++){
							fieldValue.add("");	
						}
					}
				}

//				System.out.println(tableMap1);
				importDataMapper.insertData(tableMap1);  //插入表
				tableMap1.put("tcolumns", colIndexList);
				tableMap1.remove("fieldValue");
			}
			rowNum++;
		}
	}
	}

	/**
	 * 得到操作Excel的行数
	 */
	public int getRowCount() {
		System.out.println("---------共计导入" + rowNum + "行---------");
		return rowNum;
	}

	public ImportDataSqlMapper getImportDataMapper() {
		return importDataMapper;
	}

	public void setImportDataMapper(ImportDataSqlMapper importDataMapper) {
		this.importDataMapper = importDataMapper;
	}

	public List getNodexmlList() {
		return nodexmlList;
	}

	public void setNodexmlList(List nodexmlList) {
		this.nodexmlList = nodexmlList;
	}

	public String getOperateNo() {
		return operateNo;
	}

	public void setOperateNo(String operateNo) {
		this.operateNo = operateNo;
	}

	public List geteTitleList() {
		return eTitleList;
	}

	public void seteTitleList(List eTitleList) {
		this.eTitleList = eTitleList;
	}

	public IImportDataLogService getImportLogService() {
		return importLogService;
	}

	public void setImportLogService(IImportDataLogService importLogService) {
		this.importLogService = importLogService;
	}

	/**
	 * 测试代码
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println(DateUtil.getCurrentDate());
		//MatterReadExcel reader = new MatterReadExcel("D:\\bom\\item\\item.xls");
		// reader.processByRow();//处理所有的sheet
		// reader.stop();//运行一半需要停止调用此方法，释放文件流，正常运行完毕会自动释放
		//reader.processByRow(1);// 处理第一个sheet，sheet索引从1开始
		System.out.println(DateUtil.getCurrentDate());
		long endTime = System.currentTimeMillis();
		long useTime = endTime - startTime;
		System.out.println("共用时:" + useTime + "毫秒");
	}
}
