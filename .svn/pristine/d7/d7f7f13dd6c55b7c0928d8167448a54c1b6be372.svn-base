package com.risen.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ibm.db2.jcc.am.l;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.IRisenIntegralDao;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;
import com.risen.entity.RisenOrgLifeCalendar;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class RisenIntegralDaoImpl extends HibernateBaseDao<RisenIntegral, Integer> implements
		IRisenIntegralDao {

	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@Override
	public RisenIntegral save(RisenIntegral bean) {
		getSession().save(bean); 
		return bean;
	}

	@Override
	public RisenIntegral findById(Integer id) {
		RisenIntegral bean=get(id);
		return bean;
	}

	@Override
	public void delete(Integer id) {
		RisenIntegral entity = super.get(id);
		getSession().delete(entity);
	}

	@Override
	public RisenIntegral update(RisenIntegral bean) {
		getSession().update(getSession().merge(bean));
		return bean;
	}

	@Override
	protected Class<RisenIntegral> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenIntegral.class;
	}

	@Override
	public RisenIntegral findByOrgId(Integer id) {
		//String sql="from RisenIntegral r where r.risenitOrgid=:id";
		String sql="select * from risen_integral where RISENIT_ORGID="+id;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		sql = sql + " and risenit_year = '" + year + "'";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(RisenIntegral.class);
		RisenIntegral bean = (RisenIntegral) query.list().get(0);
		return bean;
	}

	@Override
	public List<RisenIntegral> getReportData(Integer orgId) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select  t.depart_id, t.depart_name,case when  ");
		sql.append(" t1.risenit_score is null then 0 else  t1.risenit_score end as risenit_score ");
		sql.append(" from jc_department t left join Risen_Integral t1 on t.depart_id = t1.risenit_orgid ");
		if(orgId != null){
			sql.append(" where t.parent_id="+orgId);
		}else{
			sql.append(" where t.parent_id is null");
		}
		sql.append(" order by t.depart_id");
		List<RisenIntegral>list=new ArrayList<RisenIntegral>();
		List<Object[]> obj=null;
		try {
			obj=getSession().createSQLQuery(sql.toString()).list();
			for (Object[] o : obj) {
				RisenIntegral bean=new RisenIntegral();
				bean.setRisenitOrgid(Integer.valueOf(o[0].toString()));
				bean.setRisenitOrgname(o[1].toString());
				bean.setRisenitScore(Double.valueOf(o[2].toString()));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void updateBaseScore(Integer orgId, Integer score) {
		String sql=" update risen_integral set risenit_base="+score+" where risenit_orgid="+orgId;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		sql = sql + " and risenit_year = '" + sdf.format(new Date()) + "'";
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void batchUpdBaseScore(Integer[] orgId, Integer score) {
		StringBuffer ids=new StringBuffer();
		for (Integer i : orgId) {
			ids.append(i+",");
		}
		ids.deleteCharAt(ids.lastIndexOf(","));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String sql=" update risen_integral set risenit_base="+score+" where risenit_orgid in ("+ids.toString()+")";
		sql = sql + " and risenit_year = '"+sdf.format(new Date())+"'";
		getSession().createSQLQuery(sql).executeUpdate();
		
	}

	@Override
	public int confirmShare(Integer orgId, Double score) {
		if(orgId!=null && score!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String year = sdf.format(new Date());
			String sql="update risen_integral set risenit_score=risenit_score+"+score+" where risenit_orgid="+orgId+" and risenit_year='"+year+"'";
			return getSession().createSQLQuery(sql).executeUpdate();
		}else{
			return 0;
		}
	}
	//查询所有总分记录《查看所有机关党委产生的分数》
	@Override
	public List<Object[]> getListse(String strIds, String channelName, String startDate, String endDate) {
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				"(select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			strSql+= ("risenir_channel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
					"and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in "+strIds+" ) "+
					"and channel_name in ('"+channelName+"')) ))) and ");
		}
		if (!StringUtils.hasText(startDate)) {
			startDate = "1980-01-01";
		}
		if (!StringUtils.hasText(endDate)) {
			endDate = "2050-01-01";
		}
		/*
		strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+") "+
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) ))) and ");
		*/
		strSql+= ("risenir_orgid in "+strIds+" and RISENIR_HANDLEDATE" +
				"  between to_date('"+startDate+" 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +
				") aa group by  risenir_orgid");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	@Override
	public List<Object[]> getListds(String strIds, String channelName, String startDate, String endDate) {
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				" (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
					" and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in "+strIds+" ) "+
					" and channel_name in ('"+channelName+"')) ))) and ");
		}
		/*
		strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+") "+
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) ))) and ");
		*/
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			strSql+= ("risenir_orgid in "+strIds+" and RISENIR_HANDLEDATE" +
					"  between to_date('"+startDate+" 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
					"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +
					") aa group by  risenir_orgid ");
		}else{
			strSql+= ("risenir_orgid in "+strIds+") aa group by  risenir_orgid ");
		}
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	//查看支部动态，图片新闻等分数
	@Override
	public List<Object[]> getListCha(String strIds, String channelName, String startDate, String endDate) {		
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				" (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			if(channelName.equals("党建动态")){
				strSql+= (" risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
						" and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in "+strIds+" ) "+
						" and channel_name in ('"+channelName+"')) ))) and ");
			}else{
				strSql+= (" risenir_targetchannel in ('"+channelName+"') and ");
			}
		}
		if (!StringUtils.hasText(startDate)) {
			startDate = "1980-01-01";
		}
		if (!StringUtils.hasText(endDate)) {
			endDate = "2050-01-01";
		}
		strSql+= ("risenir_orgid in "+strIds+" and RISENIR_HANDLEDATE" +
				"  between to_date('"+startDate+" 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				//"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +" and risenir_channel='"+channelName+
				//"' ) aa group by  risenir_orgid ");
				"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')"+
				" ) aa group by risenir_orgid");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	//查询所有总分记录《按月份取出》
	@Override
	public List<Object[]> getListseMonth(String strIds, String channelName) {
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				" (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			if(!"()".equals(strIds)){
				strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
						" and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in "+strIds+" ) "+
						" and channel_name in ('"+channelName+"')) ))) and ");
				strSql += ("risenir_orgid in "+strIds+" and ");
			}
		}else{
			if(!"()".equals(strIds)){
				strSql += ("risenir_orgid in "+strIds+" and ");
			}
		}
		strSql+= (" RISENIR_HANDLEDATE  " +
				" between to_date(to_char(sysdate,'yyyy-MM')||'-01 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				" and to_date(to_char(last_day(sysdate),'yyyy-MM-dd') || ' 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +
				" ) aa group by  risenir_orgid ");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	@Override
	public List<Object[]> getListseMonthds(String strIds, String channelName) {
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				" (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
					" and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in "+strIds+" ) "+
					" and channel_name in ('"+channelName+"')) ))) and ");
		}
		if (!"()".equals(strIds)) {
			strSql += ("risenir_orgid in "+strIds+" and ");
		}
		/*
		strSql+= ("risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+" )"+ 
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in "+strIds+") "+
				" and channel_name in ('图片新闻','党建动态','经验交流','媒体视点','理论研讨','网上荣誉室')) ))) and ");
		*/
		strSql+= (" RISENIR_HANDLEDATE  " +
				"  between to_date(to_char(trunc(sysdate,'mm'),'yyyy-MM-dd')||' 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				"  and to_date(to_char(last_day(sysdate),'yyyy-MM-dd') || ' 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +
				" ) aa group by  risenir_orgid ");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	@Override
	public List<Object[]> getListMonthmds(String strIds, String channelName) {
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				"(select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			strSql+= ("risenir_targetchannel='"+channelName+"' and ");
		}
		strSql+= ("risenir_orgid in "+strIds+" and RISENIR_HANDLEDATE  " +
				"between to_date(to_char(trunc(sysdate,'mm'),'yyyy-MM-dd')||' 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				" and to_date(to_char(last_day(sysdate),'yyyy-MM-dd') || ' 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +
				") aa group by  risenir_orgid ");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}
	
	@Override
	public List<RisenIntegral> getPage(String departId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(" select t.* from (");
		StringBuffer sc = new StringBuffer(" select rownum rn,bean.* from risen_integral bean where 1=1");
		if(departId!=null){
			sc.append(" and risenit_Orgid in ("+departId+ ")");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		sc.append(" and risenit_Year = '"+sdf.format(new Date()) + "'");
		sc.append(" order by risenit_Score desc");
		sb.append(sc.toString());
		sb.append(" ) t where rn <=10");
		sb.append(" order by risenit_Score desc,rn asc");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString()).addEntity(RisenIntegral.class);
		List<RisenIntegral> integrals = sqlQuery.list();
		return integrals;
	}

	@Override
	public List<RisenIntegral> getYearInfo(String year, String departId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from Risen_Integral bean where 1=1");
		if(year!=null){
			sb.append(" and risenit_Year = "+year);
		}
		if(departId!=null){
			sb.append(" and risenit_Orgid in (" + departId + ")");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addEntity(RisenIntegral.class);
		return query.list();
	}

	@Override
	public int changeRuleById(String id, String desc) {
		// TODO Auto-generated method stub
		if(id==null || id==""){
			return 0;
		}else{
			String sql="update risen_integral set risenit_desc= '"+desc+"' where risenit_Uuid="+new Integer(id);
			return getSession().createSQLQuery(sql).executeUpdate();
		}
	}

	@Override
	public int changeBaseById(Integer id, Integer base) {
		// TODO Auto-generated method stub
		if(id==null){
			return 0;
		}else{
			String sql="update risen_integral set risenit_base="+base+" where risenit_Uuid="+id;
			return getSession().createSQLQuery(sql).executeUpdate();
		}
	}

	@Override
	public int batchUpBaseScore(Integer[] ids, Integer score) {
		// TODO Auto-generated method stub
		String orgId = "";
		if(ids!=null){
			for (Integer integer : ids) {
				orgId = orgId + String.valueOf(integer)+",";
			}
			orgId  = orgId.substring(0,orgId.length()-1);
			String sql=" update risen_integral set risenit_base="+score+" where risenit_uuid in ("+orgId+")";
			return getSession().createSQLQuery(sql).executeUpdate();
		}else{
			return 0;
		}
	}

	@Override
	public List<Object[]> getListMonthTotal(String strIds) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(" select rownum,depart_id,depart_name,nvl(score,0) as score from jc_department d ");
		sb.append(" left join (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where risenir_orgid in ");
		if(!"()".equals(strIds)){
			sb.append(strIds);
		}
		sb.append(" and  RISENIR_HANDLEDATE   between to_date(to_char(sysdate,'yyyy-MM') || '-01 00:00:01', 'yyyy-MM-dd HH24:mi:ss') and to_date(to_char(last_day(sysdate),'yyyy-MM-dd') || ' 23:59:59', 'yyyy-MM-dd HH24:mi:ss')");
		sb.append(" ) deptScore on d.depart_id = deptScore.risenir_orgid where d.depart_id in ");
		if(!"()".equals(strIds)){
			sb.append(strIds);
		}
		sb.append(" order by score desc,rownum");
		SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
		return sqlQuery.list();
	}

	@Override
	public List<Object[]> getListChaNow(String strIds, String departId,
			String channelName, String startDate, String endDate) {
		// TODO Auto-generated method stub
		String strSql = "select risenir_orgid as risenitOrgid,sum(score) as risenitScore from " +
				" (select risenir_orgid,nvl(risenir_score,0) as score  from RISEN_INTEGRALRECORD where ";
		if (StringUtils.hasText(channelName)) {
			if(channelName.equals("党建动态")){
				strSql+= (" risenir_targetchannel in (select channel_name from jc_channel_ext where channel_id in (select channel_id from jc_channel where parent_id in (select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id in ('"+departId+"') )"+ 
						" and channel_name in ('"+channelName+"')) or channel_id in ((select channel_id from jc_channel_ext where channel_id in (select channel_id from jc_channel_department where department_id  in ('"+departId+"') ) "+
						" and channel_name in ('"+channelName+"')) ))) and ");
			}else{
				strSql+= (" risenir_targetchannel in ('"+channelName+"') and ");
			}
		}
		if (!StringUtils.hasText(startDate)) {
			startDate = "1980-01-01";
		}
		if (!StringUtils.hasText(endDate)) {
			endDate = "2050-01-01";
		}
		strSql+= ("risenir_orgid in "+strIds+" and RISENIR_HANDLEDATE" +
				"  between to_date('"+startDate+" 00:00:01', 'yyyy-MM-dd HH24:mi:ss')" +
				//"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')" +" and risenir_channel='"+channelName+
				//"' ) aa group by  risenir_orgid ");
				"  and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')"+
				" ) aa group by risenir_orgid");
		SQLQuery sqlQuery = getSession().createSQLQuery(strSql);
		return sqlQuery.list();
	}

	@Override
	public List<Object[]> getListChannelScore(String strIds, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		String outerSql = "select dept.depart_id,nvl(DJDT,0) dtdt,nvl(TPXW,0) tpxw,nvl(JYJL,0) jyjl,nvl(MTSD,0) mtsd,nvl(LLYT,0) llyt,nvl(WSRYS,0) wsrys";
		outerSql += (" from jc_department dept left join (");
		//运用下面sql语句会出现数据问题  故而在外面套一个sql
		String sql = "select distinct risenir_orgid as risenitOrgid, sum(";
		sql += ("case when risenir_targetchannel in ('党建动态','省局','市局','县局','基层所','订阅号') then score  else 0 end) as djdt,"); 
		sql += ("sum(case when risenir_targetchannel='图片新闻' then score else 0 end) as tpxw,");
		sql += ("sum(case when risenir_targetchannel='经验交流' then score else 0 end) as jyjl,");
		sql += ("sum(case when risenir_targetchannel='媒体视点' then score else 0 end) as mtsd,");
		sql += ("sum(case when risenir_targetchannel='理论研讨' then score else 0 end) as llyt,");
		sql += ("sum(case when risenir_targetchannel='网上荣誉室' then score else 0 end) as wsrys ");
		sql += ("from (select risenir_orgid, nvl(risenir_score, 0) as score ,risenir_targetchannel");
		sql += (" from RISEN_INTEGRALRECORD where risenir_targetchannel in ('党建动态', '省局', '市局', '县局', '基层所', '订阅号','图片新闻','经验交流','媒体视点','理论研讨','网上荣誉室')");
		sql += (" and risenir_orgid in"+strIds);
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			sql += ("and RISENIR_HANDLEDATE between to_date('"+startDate+" 00:00:00', 'yyyy-MM-dd HH24:mi:ss') and");
			sql += (" to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss')");
		}else{
			sql += ("and to_char(RISENIR_HANDLEDATE,'yyyy')='sysdate'");
		}
        sql += (" ) aa group by risenir_orgid");
        outerSql += sql;
        outerSql += (" ) scoreInfo on dept.depart_id = scoreInfo.risenitOrgid where dept.depart_id in "+ strIds);
        SQLQuery query = getSession().createSQLQuery(outerSql);
		return query.list();
	}

	@Override
	public List<Object[]> getListChannelMonthAndTotalScore(String strIds,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		
		String outerSql = "select dept.depart_id,nvl(allScore,0) allScore,nvl(monthScore,0) monthScore from jc_department dept left join (";
		//运用下面sql语句会出现数据问题  故而在外面套一个sql
		String sql = "select allScore.risenir_orgid as orgId,allScore.allScore,nvl(monthScore.monthScore,0) as monthScore from"; 
		sql +=("(select risenir_orgid,sum(nvl(risenir_score,0)) as allScore from risen_integralrecord where ");
		sql +=(" risenir_orgid in "+strIds);
		if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
			sql +=(" and RISENIR_HANDLEDATE between to_date('"+startDate+" 00:00:00', 'yyyy-MM-dd HH24:mi:ss')");
			sql +=(" and to_date('"+endDate+" 23:59:59', 'yyyy-MM-dd HH24:mi:ss') ");
		}
		sql+=(" group by risenir_orgid) allScore");
        sql+=(" left join (select risenir_orgid,sum(nvl(risenir_score,0)) as monthScore from risen_integralrecord where ");  
        sql+=(" risenir_orgid in" +strIds +" and RISENIR_HANDLEDATE between to_date(to_char(sysdate,'yyyy-MM')|| '-01 00:00:00', 'yyyy-MM-dd HH24:mi:ss') and sysdate");
        sql+=(" group by risenir_orgid) monthScore on allScore.risenir_orgid = monthScore.risenir_orgid order by nvl(monthScore.monthScore,0) desc");
        
        outerSql += sql;
        outerSql += (" ) monthTotalScore on dept.depart_id = monthTotalScore.orgid where dept.depart_id in "+ strIds);
        SQLQuery query = getSession().createSQLQuery(outerSql);
		return query.list();
	}

	@Override
	public List<Object[]> getYearScore(String year,String orgid) {
		List<List<RisenIntegralRecord>> list = new ArrayList<List<RisenIntegralRecord>>();
		String yearlist[] = year.split(",");
		String sql = " ";
		sql+=" select t.risenir_orgname, ";
		sql+=" sum(case ";
		sql+=" when to_char(t.risenir_handledate, 'yyyy') = 2015 then ";
		sql+=" t.risenir_score ";
		sql+=" else ";
		sql+=" 0 ";
		sql+=" end) as aa, ";
		sql+=" sum(case ";
		sql+=" when to_char(t.risenir_handledate, 'yyyy') = 2016 then ";
		sql+=" t.risenir_score ";
		sql+=" else ";
		sql+=" 0 ";
		sql+=" end) as bb, ";
		sql+=" sum(case ";
		sql+=" when to_char(t.risenir_handledate, 'yyyy') = 2017 then ";
		sql+=" t.risenir_score ";
		sql+=" else ";
		sql+=" 0 ";
		sql+=" end) as cc, ";
		sql+=" sum(case ";
		sql+=" when to_char(t.risenir_handledate, 'yyyy') = 2018 then ";
		sql+=" t.risenir_score ";
		sql+=" else ";
		sql+=" 0 ";
		sql+=" end) as dd, ";
		sql+=" sum(case ";
		sql+=" when to_char(t.risenir_handledate, 'yyyy') = 2019 then ";
		sql+=" t.risenir_score ";
		sql+=" else ";
		sql+=" 0 ";
		sql+=" end) as ee ";
		sql+=" from risen_integralrecord t ";
		sql+=" where t.risenir_orgid in "+orgid;
		sql+=" group by t.risenir_orgname ";
		 SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}
	
}
