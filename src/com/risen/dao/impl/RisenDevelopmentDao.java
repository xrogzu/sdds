package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.risen.dao.IRisenDevelopmentDao;
import com.risen.entity.RisenDevelopment;

@Repository
public class RisenDevelopmentDao extends HibernateBaseDao<RisenDevelopment,Integer> implements IRisenDevelopmentDao {

	/**
	 * 保存
	 */
	@SuppressWarnings("unchecked")
	public RisenDevelopment save(RisenDevelopment baseModel) {
		// TODO Auto-generated method stub
		getSession().save(baseModel);
		return baseModel;
	}
	
	/**
	 * 流程展示列表
	 */
	@SuppressWarnings("unchecked")
	public List<RisenDevelopment> risenDevelopmentList(
			RisenDevelopment baseModel) {
		// TODO Auto-generated method stub
		Finder finder = Finder.create(" from RisenDevelopment bean where 1=1 ");
		if(StringUtils.hasText(baseModel.getRisendeDevelopname())){
			finder.append(" and bean.risendeDevelopname like :name");
			finder.setParam("name", "%" + baseModel.getRisendeDevelopname() + "%");
		}
		finder.append(" order by bean.risendeDevelopnumber ");
		return find(finder);
	}
	
	/**
	 * 删除
	 */
	@SuppressWarnings("unchecked")
	public RisenDevelopment deleteData(String uuid){
		RisenDevelopment bean = this.getModel(uuid);
		if(bean!=null){
			getSession().delete(bean);
		}
		return bean;
	}
	
	/**
	 * 获得单个对象
	 */
	@SuppressWarnings("unchecked")
	public RisenDevelopment getModel(String uuid) {
		// TODO Auto-generated method stub
		Finder finder = Finder.create(" from RisenDevelopment t1 where t1.risendeUuid = :uuid ");
		finder.setParam("uuid", uuid);
		List<RisenDevelopment> list = find(finder);
		RisenDevelopment model = new RisenDevelopment();
		if(list.size()>0){
			model = list.get(0);
		}
		return model;
	}
	
	@Override
	protected Class<RisenDevelopment> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenDevelopment.class;
	}

	

	
}
