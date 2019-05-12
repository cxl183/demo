package com.htsoft.szq.common.excelutil.excelutil;
/**
 * 数据导入表规则VO
 * @author Administrator
 * 
 */
public class DataImportTableRule{
	private int ruleNumber;          //规则编号
	private String field;            //规则字段
	private String dirField;         //规则指向字段
	private String ruleValue;        //规则指向字段值
	
	public DataImportTableRule(int ruleNumber, String field,
			String dirField, String ruleValue) {
		super();
		this.ruleNumber = ruleNumber;
		this.field = field;
		this.dirField = dirField;
		this.ruleValue = ruleValue;
	}

	public int getRuleNumber() {
		return ruleNumber;
	}

	public void setRuleNumber(int ruleNumber) {
		this.ruleNumber = ruleNumber;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDirField() {
		return dirField;
	}
	public void setDirField(String dirField) {
		this.dirField = dirField;
	}
	public String getRuleValue() {
		return ruleValue;
	}
	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
}
