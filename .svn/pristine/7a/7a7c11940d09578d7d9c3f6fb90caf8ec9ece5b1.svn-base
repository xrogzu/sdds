package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.ICoreDictionaryDao;
import com.risen.entity.CoreDictionary;
import com.risen.service.ICoreDictionaryService;
@Service
@Transactional
public class CoreDictionaryServiceImpl implements ICoreDictionaryService {

	@Autowired
	private ICoreDictionaryDao coreDictionaryDao;
	
	/**
	 * 新增字典数据
	 */
	@Override
	public CoreDictionary save(String corecdType,String corecdKey,String corecdVal) {
		// TODO Auto-generated method stub
		CoreDictionary baseModel = new CoreDictionary();
		baseModel.setCorecdType(corecdType);
		baseModel.setCorecdKey(corecdKey);
		baseModel.setCorecdVal(corecdVal);
		return this.getCoreDictionaryDao().save(baseModel);
	}
	/**
	 * 列表
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize , String corecdType){
		Pagination page = this.getCoreDictionaryDao().getPage(pageNo, pageSize ,corecdType);
		return page;
	}
	
	/**
	 * 字典类型列表
	 */
	@Override
	public List<CoreDictionary> getCorecdTypeList(){
		return this.getCoreDictionaryDao().getCorecdTypeList();
	}
	
	/**
	 * 字典键值列表
	 */
	public List<CoreDictionary> getCorecdKeyList(String corecdType){
		return this.getCoreDictionaryDao().getCorecdKeyList(corecdType);
	}
	
	public ICoreDictionaryDao getCoreDictionaryDao() {
		return coreDictionaryDao;
	}
	@Override
	public List<CoreDictionary> getJobDictByOrgType(Integer type) {
		
		return this.getCoreDictionaryDao().getJobDictByOrgType(type);
	}
	
}
