package com.risen.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenIntegralRecordDao;
import com.risen.entity.RisenIntegralRecord;

public class RisenIntegralRecordDaoImpl extends HibernateBaseDao<RisenIntegralRecord, Integer> implements
		IRisenIntegralRecordDao {

	 
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = getSession().createCriteria(RisenIntegralRecord.class);
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	 
	public RisenIntegralRecord save(RisenIntegralRecord bean) {
		getSession().save(bean); 
		return bean;
	}

	 
	public RisenIntegralRecord findById(Integer id) {
		RisenIntegralRecord bean=getByid(id);
		return bean;
	}

	 
	public void delete(Integer id) {
		RisenIntegralRecord entity = findById(id);
		getSession().delete(entity);
	}

	 
	public RisenIntegralRecord update(RisenIntegralRecord bean) {
		getSession().update(getSession().merge(bean));
		return bean;
	}

	 
	protected Class<RisenIntegralRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenIntegralRecord.class;
	}

	 
	public Pagination getPage(int pageNo, int pageSize, RisenIntegralRecord bean) {
		Criteria crit = getSession().createCriteria(RisenIntegralRecord.class);
		if(bean!=null){
			if(!StringUtils.isEmpty(bean.getRisenirOrgid())){
				crit.add(Restrictions.eq("risenirOrgid", bean.getRisenirOrgid()));
			}
			if(!StringUtils.isEmpty(bean.getRisenirResult())){
				crit.add(Restrictions.eq("risenirResult", bean.getRisenirResult()));
			}
			if(!StringUtils.isEmpty(bean.getRisenirTargetorgid())){
				crit.add(Restrictions.eq("risenirTargetorgid", bean.getRisenirTargetorgid()));
			}
		}
		crit.addOrder(Order.desc("risenirHandledate"));
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	//获取已共享内容
	 
	public Pagination getPagehs(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean) {
		StringBuffer sc= new StringBuffer(" from RisenIntegralRecord bean where 1=1 ");
		Finder finder = Finder.create(sc.toString());
		if(bean!=null){
			if(!StringUtils.isEmpty(bean.getRisenirResult())){
				finder.append(" and bean.risenirResult = 1");
			}
			if(!StringUtils.isEmpty(bean.getRisenirTargetorgid())){
				finder.append(" and bean.risenirTargetorgid ="+bean.getRisenirTargetorgid());
			}
		}
		finder.append(" and bean.risenirScore <> 0 ");
		finder.append(" and bean.risenirScore is not null ");
		finder.append(" order by risenirHandledate desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}

	 
	public Pagination getPagehsMonth(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean) {
		StringBuffer sc= new StringBuffer(" from RisenIntegralRecord bean where 1=1 ");
		Finder finder = Finder.create(sc.toString());
		if(bean!=null){
			if(!StringUtils.isEmpty(bean.getRisenirOrgid())){
				finder.append(" and bean.risenirOrgid = "+bean.getRisenirOrgid());
			}
			if(!StringUtils.isEmpty(bean.getRisenirResult())){
				finder.append(" and bean.risenirResult ="+bean.getRisenirResult());
			}
			/*
			if (!StringUtils.isEmpty(bean.getRisenirTargetchannel())) {
				finder.append(" and bean.risenirTargetchannel='"+bean.getRisenirTargetchannel()+"'");
			}
			finder.append(" and bean.risenirTargetchannel in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室','市局','县局','基层所','支部动态','党费缴纳','党务公开','特色工作')");
			finder.append(" and bean.risenirScore is not null and bean.risenirHandledate between to_date(to_char(trunc(sysdate,'mm'),'yyyy-MM-dd')||' 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				" and to_date(to_char(last_day(sysdate),'yyyy-MM-dd') || ' 23:59:59', 'yyyy-MM-dd HH24:mi:ss')");
			*/
			finder.append(" and bean.risenirScore is not null ");
		}
		finder.append(" order by risenirHandledate desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}
	//查看所有分数
	 
	public List<RisenIntegralRecord> getPageNum(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean) {
		return null;
	}
	 
	public int confirmShare(Integer ids,Double score) {
		StringBuffer sql=new StringBuffer();
		int i = 0;
		if(ids!=null && score!=null){
			sql.append("update risen_integralrecord set risenir_result=1,risenir_score ="+score+" where risenir_uuid="+ids);
			i = getSession().createSQLQuery(sql.toString()).executeUpdate();
		}else{
			if(ids==null){
				System.out.println("ids为空");
			}
			if(score==null){
				System.out.println("score为空");
			}
		}
		return i;
	}

	 
	public RisenIntegralRecord getByid(Integer id) {
		String sqlString="select * from risen_integralrecord where risenir_uuid="+id;
		RisenIntegralRecord bean=(RisenIntegralRecord) getSession().createSQLQuery(sqlString).addEntity(RisenIntegralRecord.class).uniqueResult();
		return bean;
	}

	
	 
	public List<RisenIntegralRecord> findByContentId(Integer id) {
		String sqlString="select * from risen_integralrecord where risenir_contentid="+id;
		List<RisenIntegralRecord> beanList=getSession().createSQLQuery(sqlString).addEntity(RisenIntegralRecord.class).list();
		return beanList;
	}

	 
	public RisenIntegralRecord findByContentIdAndCheckId(Integer contentId, Integer checkId){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from risen_integralrecord where risenir_contentid=").append(contentId).append("and RISENIR_SHARECHECK=").append(checkId);
		RisenIntegralRecord bean=(RisenIntegralRecord) getSession().createSQLQuery(sql.toString()).addEntity(RisenIntegralRecord.class).uniqueResult();
		return bean;
	}
	
	public List<RisenIntegralRecord> findByIds(Integer[] ids) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<ids.length; i++){
			if(i==0){
				sb.append("(");
			}
			sb.append(ids[i]);
			if(i == (ids.length-1)){
				sb.append(")");
			}else{
				sb.append(",");
			}
		}
		String sqlString="select * from risen_integralrecord where risenir_uuid in"+sb.toString();
		List<RisenIntegralRecord> list=getSession().createSQLQuery(sqlString).addEntity(RisenIntegralRecord.class).list();
		return list;
	}
	 
	public List<RisenIntegralRecord> findByParentId(Integer deptId,
			Integer contentId) {
		String sql = " select * from risen_integralrecord left join jc_content_share_check on risenir_contentid = jc_content_share_check.content_id ";
		sql=sql+" where 1=1 and check_Status = 1";
		if(deptId!=null){
			sql = sql +" and RISENIR_TARGETORGID = "+deptId;
		}
		if(contentId!=null){
			sql=sql+" and RISENIR_CONTENTID="+contentId;
		}
		SQLQuery sqlQ=getSession().createSQLQuery(sql).addEntity(RisenIntegralRecord.class);
		return sqlQ.list();
	}
	@Override
	public Pagination getScoresByDeptIdAndDate(int pageNo,
			HttpServletRequest request, int pageSize, Integer deptId,
			String startDate, String endDate,Integer status) {
		// TODO Auto-generated method stub
		StringBuffer sc= new StringBuffer(" from RisenIntegralRecord bean where 1=1 ");
		Finder finder = Finder.create(sc.toString());
		if(deptId!=null){
			finder.append(" and bean.risenirTargetorgid = "+deptId);
		}
		if(status!=null){
			finder.append(" and bean.risenirResult = "+status);
		}
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			startDate = startDate + " 00:00:00";
			endDate = endDate + " 23:59:59";
			finder.append(" and bean.risenirHandledate between to_date('"+ startDate +"','yyyy-MM-dd HH24:mi:ss')");
			finder.append(" and to_date('"+ endDate +"','yyyy-MM-dd HH24:mi:ss')");
		}
		finder.append(" and bean.risenirScore <> 0 ");
		finder.append(" and bean.risenirScore is not null ");
		finder.append(" order by risenirHandledate desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}
	
	@Override
	public Pagination getScoresFrontByDeptIdAndDate(int pageNo,
			HttpServletRequest request, int pageSize, Integer deptId,
			String startDate, String endDate,Integer status) {
		// TODO Auto-generated method stub
		StringBuffer sc= new StringBuffer(" from RisenIntegralRecord bean where 1=1 ");
		Finder finder = Finder.create(sc.toString());
		if(deptId!=null){
			finder.append(" and bean.risenirOrgid = "+deptId);
		}
		if(status!=null){
			finder.append(" and bean.risenirResult = "+status);
		}
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			startDate = startDate + " 00:00:00";
			endDate = endDate + " 23:59:59";
			finder.append(" and bean.risenirHandledate between to_date('"+ startDate +"','yyyy-MM-dd HH24:mi:ss')");
			finder.append(" and to_date('"+ endDate +"','yyyy-MM-dd HH24:mi:ss')");
		}
		finder.append(" and bean.risenirScore <> 0 ");
		finder.append(" and bean.risenirScore is not null ");
		finder.append(" order by risenirHandledate desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}

	@Override
	public List<RisenIntegralRecord> findByContentIdAndDeptId(Integer departId,
			Integer contentId) {
		// TODO Auto-generated method stub
		String sqlString="select * from risen_integralrecord where 1=1";
		if(departId!=null){
			sqlString = sqlString + " and risenir_targetorgid = " + departId;
		}
		if(contentId!=null){
			sqlString = sqlString + " and risenir_contentid = " + contentId;
		}
		List<RisenIntegralRecord> beanList=getSession().createSQLQuery(sqlString).addEntity(RisenIntegralRecord.class).list();
		return beanList;
	}
	
}
