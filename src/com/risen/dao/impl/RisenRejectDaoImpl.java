package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenRejectDao;
import com.risen.entity.RisenReject;

public class RisenRejectDaoImpl extends HibernateBaseDao<RisenReject, Integer> implements
		IRisenRejectDao {
	/**
	 * status = 1 被驳回  表示自己共享的文章被驳回
	 * status = 2 已驳回  表示自己驳回别人的文章
	 */
	@Override
	public Pagination getPage(Integer departId, int pageNo, int pageSize,int status) {
		// TODO Auto-generated method stub
		Finder finder = Finder.create(" from RisenReject bean where 1=1 ");
		if(1==status){
			finder.append(" and bean.risenrjDepartId = "+departId);
		}else if(2 == status){
			finder.append(" and bean.risenrjbhDepartId = "+departId);
		}else{
			finder.append(" and bean.risenrjDepartId = "+departId);
		}
		finder.append(" order by bean.risenrjStatus,bean.risenrjUnid desc");
		return find(finder,pageNo,pageSize);
	}
	
	@Override
	public RisenReject save(RisenReject reject) {
		// TODO Auto-generated method stub
		getSession().save(reject);
		return reject;
	}

	@Override
	public RisenReject findById(Integer rejectId) {
		// TODO Auto-generated method stub
		RisenReject reject = super.get(rejectId);
		return reject;
	}

	@Override
	public Integer changeStatus(Integer departId, Integer contentId) {
		// TODO Auto-generated method stub
		String hql = "update RisenReject bean set bean.risenrjStatus=:status where bean.risenrjDepartId=:departId and bean.risenrjContentId=:contentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("status", 0);
		query.setParameter("departId", departId);
		query.setParameter("contentId", contentDao.findById(new Integer(contentId)));
		return query.executeUpdate();
	}

	@Override
	protected Class<RisenReject> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenReject.class;
	}
	
	@Override
	public List<RisenReject> getListByDepartIdAndContentId(Integer departId,
			Integer contentId) {
		// TODO Auto-generated method stub
		String hql = "from RisenReject bean where bean.risenrjDepartId=:departId and bean.risenrjContentId=:contentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("departId", departId);
		query.setParameter("contentId", contentDao.findById(new Integer(contentId)));
		List<RisenReject> rejects = query.list();
		return rejects;
	}
	
	@Override
	public Integer changeReason(Integer risenrjUnid, String reason) {
		// TODO Auto-generated method stub
		String hql = "update RisenReject bean set bean.risenrjReason=:resaon where bean.risenrjUnid=:risenrjUnid";
		Query query = getSession().createQuery(hql);
		query.setParameter("resaon", reason);
		query.setParameter("risenrjUnid", risenrjUnid);
		return query.executeUpdate();
	}
	
	@Autowired
	private ContentDao contentDao;
	
}
