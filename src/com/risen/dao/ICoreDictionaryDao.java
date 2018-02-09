package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.CoreDictionary;

public interface ICoreDictionaryDao {

	/**
	 * 新增字典数据
	 * @param baseModel
	 * @return
	 */
	CoreDictionary save(CoreDictionary baseModel);
	
	/**
	 * 列表
	 */
	public Pagination getPage(int pageNo, int pageSize , String corecdType);
	
	/**
	 * 字典类型列表
	 */
	List<CoreDictionary> getCorecdTypeList();
	
	/**
	 * 字典键值列表
	 */
	List<CoreDictionary> getCorecdKeyList(String corecdType);
	/**
	 * 根据组织类型获取职位名称
	 * @author slc 2016-12-6 下午4:09:19
	 * @param type
	 * @return
	 */
	public List<CoreDictionary> getJobDictByOrgType(Integer type);
}
