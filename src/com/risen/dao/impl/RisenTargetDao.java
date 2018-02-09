package com.risen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenTargetDao;
import com.risen.dao.IRisenTargetDetailDao;
import com.risen.entity.RisenTarget;

public class RisenTargetDao  extends HibernateBaseDao<RisenTarget, Integer> implements IRisenTargetDao{
	@Autowired
	private IRisenTargetDetailDao detailDao;
	/***
	 * 统计每个党组织的总分
	 */
	public List<RisenTarget> getReportList(String orgIds ){
		SQLQuery query = getSession().createSQLQuery(
				"select  risentgd_orgname as risentgCorgName ,risentgd_numscore as risentgTotalScore from (" +
				"    select risentgd_orgname, (sum(nvl(risentgd_numscore,0)) + sum(nvl(risentgd_qualityscore,0))) as risentgd_numscore " +
				"		from risen_targetdetail  where risentgd_orgid in ("+orgIds+") group by risentgd_orgid,risentgd_orgname " +
				" ) t order by risentgd_numscore desc");
		query.addScalar("risentgCorgName", StringType.INSTANCE);
		query.addScalar("risentgTotalScore", IntegerType.INSTANCE);
		List<RisenTarget> list = query.setResultTransformer(Transformers.aliasToBean(RisenTarget.class)).list();
		return list;
	}
	
	@Override
	protected Class<RisenTarget> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenTarget.class;
	}

	@Override
	public void batchUpdBaseScore(Integer[] orgId, Integer score) {
		// TODO Auto-generated method stub

	}

	@Override
	public int confirmShare(Integer orgId, Integer score) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenTarget entity=super.get(id);
		if(entity!=null){
			getSession().delete(entity);
			detailDao.deleteByPid(id);
		}
	}

	@Override
	public RisenTarget findById(Integer id) {
		// TODO Auto-generated method stub
		RisenTarget entity=get(id);
		return entity;
	}

	@Override
	public Pagination findByOrgId(int pageNo, int pageSize,Integer id) {
		// TODO Auto-generated method stub
		//Finder finder = Finder.create(" from risen_target target where 1=1 ");
		String sql = " from risen_target target where 1=1";
		Finder finder = Finder.create(sql);
		if(StringUtils.hasText(String.valueOf(id))){
			finder.append(" and target.risentg_corgid ="+id);
		}
		finder.append(" order by risentg_corgId asc,id asc");
		return find(finder, pageNo, pageSize);
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize,String id,Integer deptId) {
		// TODO Auto-generated method stub
		String sql = " from RisenTarget target where 1=1";
		Finder finder = Finder.create(sql);
		if(!StringUtils.isEmpty(deptId)){
			finder.append(" and risentg_corgid = "+deptId);
		}
		if(!StringUtils.isEmpty(id)){
			finder.append(" and risentg_cuserid = "+id);
		}
		finder.append(" order by id asc");
		Pagination pagination = find(finder, pageNo, pageSize);
		return pagination;
	}

	@Override
	public List<RisenTarget> getReportData(Integer orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RisenTarget save(RisenTarget voteQues) {
		// TODO Auto-generated method stub
		getSession().save(voteQues);
		return voteQues;
	}

	@Override
	public RisenTarget update(RisenTarget bean) {
		// TODO Auto-generated method stub
		getSession().update(bean);
		return bean;
	}

	@Override
	public void updateBaseScore(Integer orgId, Integer score) {
		// TODO Auto-generated method stub
		
	}
}