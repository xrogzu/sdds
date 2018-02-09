package com.risen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenVoteReplyDao;
import com.risen.entity.RisenVoteReply;
@Repository
public class RisenVoteReplyDaoImpl extends HibernateBaseDao<RisenVoteReply, Integer> implements RisenVoteReplyDao{

	@Override
	public RisenVoteReply save(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		getSession().save(risenVoteReply);
		return risenVoteReply;
	}

	@Override
	public RisenVoteReply update(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		getSession().update(risenVoteReply);
		return risenVoteReply;
	}

	@Override
	public void delete(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		getSession().delete(risenVoteReply);
	}

	@Override
	public RisenVoteReply findById(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from risenVoteReply bean");
		if (risenVoteReply.getRisenUuid() != null) {
			f.append(" and bean.RisenUuid="+risenVoteReply.getRisenUuid());
		}
		return (RisenVoteReply) f.createQuery(getSession()).uniqueResult();
	}

	@Override
	protected Class<RisenVoteReply> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteReply.class;
	}

	@Override
	public Pagination findByVoteId(Integer voteId,int pageNo, int pageSize) {
		Finder f = Finder.create(" from RisenVoteReply bean where 1=1 ");
		if(voteId!=null)
			f.append(" and bean.cmsVoteTopic.id="+voteId);
			f.append(" order by bean.risenCdate asc");
			return find(f, pageNo, pageSize);
	}
	
	/**
	 * 通过Finder获得分页数据
	 * 
	 * @param finder
	 *            Finder对象
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	protected Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		List list = query.list();
		p.setList(list);
		return p;
	}
}
