package com.htsoft.szq.service.importdata;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.htsoft.szq.model.CsOpMain;

/**
 * 日志记录管理Service
 * @author Administrator
 *
 */
public interface IImportDataLogService {
	/**
	 * 根据操作号删除日志记录
	 * @param operateNo
	 * @return
	 * @throws SQLException 
	 */
	public void delete(String operateNo) throws SQLException;
	/**
	 * 查询记录总数
	 * @param map
	 * @return
	 */
	public Integer getRecCount(Map map);
	/**
	 * 查询所有日志记录
	 * @return
	 */
	public List<CsOpMain> queryAllImportDataLog(Map<String,Object> map);
	/**
	 * 根据操作号查询操作日志
	 * @param operateNo  操作号
	 * @return  一条操作日志记录
	 */
	public CsOpMain getCsOpMainByOptNo(String operateNo);
//	/**
//	 * 根据主键编号查询日志记录
//	 * @param idLRecID
//	 * @return
//	 */
//	public CsOpMain queryBomImportLog(String idLRecID);
	/**
	 * 更新日志记录
	 * @param csOpMain
	 */
	public void update(CsOpMain csOpMain);
//	/**
//	 * 根据操作类型和操作状态查询操作号List
//	 * @param map
//	 * @return
//	 */
//	public List<String> selectOperateNoList(Map<String,String> map);
	/**
	 * 插入一条日志记录
	 */
	public void insert(CsOpMain csOpMain);
	/**
	 * 插入一条日志记录，只有操作号的主键
	 * @param csOpMain
	 */
	public void insertOpt(String operateNo);
}
