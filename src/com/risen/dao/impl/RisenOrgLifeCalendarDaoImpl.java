package com.risen.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.IRisenOrgLifeCalendarDao;
import com.risen.entity.RisenChangeremindrecord;
import com.risen.entity.RisenOrgLifeCalendar;

public class RisenOrgLifeCalendarDaoImpl extends HibernateBaseDao<RisenOrgLifeCalendar, Integer> implements IRisenOrgLifeCalendarDao {
	@Override
	public Pagination getPage(int pageNo, int pageSize,RisenOrgLifeCalendar bean) {
		Criteria crit = createCriteria();
		if(!StringUtils.isEmpty(bean.getRisenlcOdate())){
			crit.add(Restrictions.between("risenlcOdate", bean.getStartDate(),bean.getEndDate()));
		}
		/*if(!StringUtils.isEmpty(bean.getRisenlcReminddate())){
			crit.add(Restrictions.between("risenlcReminddate", bean.getStartDate1(),bean.getEndDate1()));
		}*/
		crit.add(Restrictions.eq("risenlcOrgid", bean.getRisenlcOrgid()));
		crit.add(Restrictions.eq("risenlcMeetingtype", bean.getRisenlcMeetingtype()));
		crit.addOrder(Order.asc("risenlcOdate"));
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();

		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	@Override
	public RisenOrgLifeCalendar findById(Integer id) {
		RisenOrgLifeCalendar entity = get(id);
		return entity;
	}
	@Override
	public RisenOrgLifeCalendar save(RisenOrgLifeCalendar bean) {
		getSession().save(bean); 
		return bean;
	}
	public RisenOrgLifeCalendar deleteById(Integer id) {
		RisenOrgLifeCalendar entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenOrgLifeCalendar> getEntityClass() {
		return RisenOrgLifeCalendar.class;
	}
	@Override
	public void delete(Integer id) {
		RisenOrgLifeCalendar entity = super.get(id);
		getSession().delete(entity);
	}
	@Override
	public RisenOrgLifeCalendar update(RisenOrgLifeCalendar bean) {
		bean = (RisenOrgLifeCalendar)getSession().merge(bean);
		getSession().update(bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RisenOrgLifeCalendar> ActivitiesToday(RisenOrgLifeCalendar bean) {
		StringBuffer sql=new StringBuffer();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		List<RisenOrgLifeCalendar> list=new ArrayList<RisenOrgLifeCalendar>();
		sql.append("select * from risen_orglifecalendar t ");
		sql.append(" where t.risenlc_odate between");
		sql.append(" to_date('"+format.format(bean.getStartDate())+"','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and to_date('"+format.format(bean.getEndDate())+"','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" ");
		sql.append(" and t.risenlc_orgid="+bean.getRisenlcOrgid());
		sql.append(" order by t.risenlc_odate asc");
		list=getSession().createSQLQuery(sql.toString()).addEntity(RisenOrgLifeCalendar.class).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RisenOrgLifeCalendar> ChanToday(RisenOrgLifeCalendar bean) {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		StringBuffer sql=new StringBuffer();
		List<RisenOrgLifeCalendar>  list = new ArrayList<RisenOrgLifeCalendar>();
		sql.append("select t.depart_id,t.depart_name,t.sddspo_secretary,t.parent_id,t.sddspo_changelasttime  from jc_department  t ");
		 sql.append("where t.sddspo_changelasttime between ");
		 sql.append("to_date('"+format.format(bean.getStartDate())+"','yyyy-mm-dd hh24:mi:ss')");
		 sql.append(" and to_date('"+format.format(bean.getEndDate())+"','yyyy-mm-dd hh24:mi:ss')");
		 sql.append(" ");
		 sql.append("and   t.depart_id="+bean.getRisenlcOrgid());
		 sql.append(" order by t.sddspo_changelasttime asc");
		List<Object[]> list2=getSession().createSQLQuery(sql.toString()).list();
		for (Object[] objects : list2) {
			RisenOrgLifeCalendar model = new RisenOrgLifeCalendar();
			model.setRisenlcComment("换届");
			//换届标示
			model.setRisenlcMeetingtype("8");
			model.setRisenlcOrgid(Integer.valueOf(String.valueOf(objects[0])));
			model.setRisenlcOrgname(String.valueOf(objects[1]));
			try {
				if(objects[4]!=null){
					String convertDate = "";
					if(String.valueOf(objects[4]).length()==10){
						convertDate = String.valueOf(objects[4])+" 00:00:00";
					}else{
						convertDate = String.valueOf(objects[4]);
					}
					model.setRisenlcOdate(format.parse(convertDate));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.add(model);
		}
		return list;
	}

	@Override
	public RisenOrgLifeCalendar TodayRemind(RisenOrgLifeCalendar bean) {
		StringBuffer sql=new StringBuffer();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		RisenOrgLifeCalendar olc=null;
		sql.append("select * from (select * from risen_orglifecalendar t");
		sql.append(" where t.risenlc_odate  between");
		sql.append(" to_date('"+format.format(bean.getStartDate())+"','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and to_date('"+format.format(bean.getEndDate())+"','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" and t.risenlc_orgid="+bean.getRisenlcOrgid());
		sql.append(" order by t.risenlc_odate asc)t1");
		sql.append("  where rownum=1");
		olc=(RisenOrgLifeCalendar) getSession().createSQLQuery(sql.toString()).addEntity(RisenOrgLifeCalendar.class).uniqueResult();
		return olc;
		 
	}
	
	@Override
	public RisenOrgLifeCalendar TodayRemindChan(RisenOrgLifeCalendar bean) {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		StringBuffer sql=new StringBuffer();
		RisenOrgLifeCalendar ssmodel=null;
		sql.append("select * from (select t.depart_id,t.depart_name,t.sddspo_secretary,t.parent_id,t.sddspo_changelasttime  from jc_department  t ");
		 sql.append("where t.sddspo_changelasttime between ");
		 sql.append("to_date('"+format.format(bean.getStartDate())+"','yyyy-mm-dd hh24:mi:ss')");
		 sql.append(" and to_date('"+format.format(bean.getEndDate())+"','yyyy-mm-dd hh24:mi:ss')");
		 sql.append("and  t.depart_id="+bean.getRisenlcOrgid());
		 sql.append(" order by t.sddspo_changelasttime asc)t1");
		 sql.append("  where rownum=1");
		 List<Object[]> list2=getSession().createSQLQuery(sql.toString()).list();
			for (Object[] objects : list2) {
				RisenOrgLifeCalendar model = new RisenOrgLifeCalendar();
				model.setRisenlcComment("换届");
				//换届标示
				model.setRisenlcMeetingtype("8");
				model.setRisenlcOrgid(Integer.valueOf(String.valueOf(objects[0])));
				model.setRisenlcOrgname(String.valueOf(objects[1]));
				try {
					if(objects[4]!=null){
						String convertDate = String.valueOf(objects[4]);
						if(convertDate.length()==10){
							convertDate = convertDate + " 00:00:00";
						}
						model.setRisenlcOdate(format.parse(convertDate));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ssmodel=model;
			}
		
		 return ssmodel;
	}

		 
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEvent(Integer id) {
		String sql="select TRUNC(t.risenlc_odate)from risen_orglifecalendar t where t.risenlc_odate>=sysdate and t.risenlc_orgid="+id;
		
		List<String>dates=getSession().createSQLQuery(sql).list();
		return dates;
	}
	


	

}