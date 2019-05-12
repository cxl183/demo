package com.htsoft.szq.service.importdata.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.UUIDGenerator;
import com.htsoft.szq.common.excelutil.excelutil.util.DateUtil;
import com.htsoft.szq.dao.CsOpMainMapper;
import com.htsoft.szq.model.CsOpMain;
import com.htsoft.szq.service.importdata.IImportDataLogService;

@Service(value="importDataLogService")
public class ImportDataLogServiceImpl implements IImportDataLogService {
	@Resource
	private CsOpMainMapper csOpMainMapper;	
	/**
	 * 根据操作号删除日志记录
	 * @param operateNo
	 * @return
	 * @throws SQLException 
	 */
	public void delete(String operateNo) throws SQLException {
		csOpMainMapper.delete(operateNo);
	}

	public List<CsOpMain> queryAllImportDataLog(Map<String,Object> map) {
		return csOpMainMapper.queryAll(map);
	}
	public void insert(CsOpMain csOpMain){
		csOpMainMapper.insert(csOpMain);
	}
	public void update(CsOpMain csOpMain) {
		csOpMainMapper.update(csOpMain);
	}

	public CsOpMain getCsOpMainByOptNo(String operateNo) {
		return csOpMainMapper.getCsOpMainByOptNo(operateNo);
	}

	@Override
	public Integer getRecCount(Map map) {
		return csOpMainMapper.getRecCount(map);
	}

	@Override
	public void insertOpt(String operateNo) {
		CsOpMain csOpMain =new CsOpMain();
		csOpMain.setIdLRecID(UUIDGenerator.getUUID());
		csOpMain.setOperateNo(operateNo);
		csOpMain.setOperator(ContextUtil.getCurrentUser().getUsername());
		csOpMain.setCreateDT(DateUtil.getCurrentDateTime());
		csOpMainMapper.insertOpt(csOpMain);
	}
}
