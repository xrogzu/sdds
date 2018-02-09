package com.jeecms.cms.dao.assist.impl;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.CmsBallotItemDao;
import com.jeecms.cms.dao.assist.CmsBallotRecordDao;
import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.cms.entity.assist.CmsBallotItem;
import com.jeecms.cms.entity.assist.CmsBallotRecord;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class CmsBallotRecordDaoImpl extends HibernateBaseDao<CmsBallotRecord, Integer> implements CmsBallotRecordDao{

	@Override
	protected Class<CmsBallotRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return CmsBallotRecord.class;
	} 

	@Override
	public Pagination getPage(Integer itemId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from CmsBallotRecord where 1=1");
		if(StringUtils.isEmpty(itemId)){
			f.append(" and item_id = "+itemId);
		}
		return find(f,pageNo,pageSize);
	}

	@Override
	public CmsBallotRecord findById(Integer id) {
		// TODO Auto-generated method stub
		CmsBallotRecord bean = get(id);
		return bean;
	}

	@Override
	public CmsBallotRecord save(CmsBallotRecord bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public CmsBallotRecord update(CmsBallotRecord bean) {
		// TODO Auto-generated method stub
		getSession().update(bean);
		return bean;
	}

	@Override
	public CmsBallotRecord deleteById(Integer id) {
		// TODO Auto-generated method stub
		CmsBallotRecord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public boolean existGive(String ip, String itemId) {
		// TODO Auto-generated method stub
		String sql="select * from jc_ballot_record r,jc_ballot_item t where t.item_id = r.item_id and r.vote_ip='"+ip+"'" +"and t.ballot_id='"+new Integer(itemId)+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(CmsBallotRecord.class);
		List<CmsBallotRecord> records = query.list();
		//获取允许的最大票数
		String sqlgetMax = "select * from jc_ballot b where b.ballot_id =  " + new Integer(itemId);
		SQLQuery queryGetMax = getSession().createSQLQuery(sqlgetMax);
		queryGetMax.addEntity(CmsBallot.class);
		List<CmsBallot> ballots = queryGetMax.list();
		int max = Integer.parseInt(ballots.get(0).getMultiSelect());
		if(records==null){
			return true;
		}else{
			if(records.size()>=max){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public void deleteByItemId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from CmsBallotRecord bean where bean.item=:item";
		Query query = getSession().createQuery(hql);
		query.setParameter("item", id);
		query.executeUpdate();
	}
}
