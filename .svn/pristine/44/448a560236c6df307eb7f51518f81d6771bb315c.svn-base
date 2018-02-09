package com.risen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenIntegralDao;
import com.risen.dao.IRisenIntegralRecordDao;
import com.risen.dao.IRisenOrgLifeCalendarDao;
import com.risen.entity.RisenChangeremindrecord;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;
import com.risen.entity.RisenOrgLifeCalendar;
import com.risen.service.IRisenOrgLifeCalendarService;
import com.utility.date.DateUtil;

@Service
@Transactional
public class RisenOrgLifeCalendarServiceImpl implements IRisenOrgLifeCalendarService {
	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	@Override
	public Pagination getPage(int pageNo, int pageSize,RisenOrgLifeCalendar bean) {
		Pagination page = dao.getPage(pageNo, pageSize,bean);
		return page;
	}
	@Override
	public RisenOrgLifeCalendar findById(Integer id) {
		RisenOrgLifeCalendar entity = dao.findById(id);
		return entity;
	}
	@Override
	public RisenOrgLifeCalendar save(RisenOrgLifeCalendar bean) {
		//加分操作
		//record(bean);
		dao.save(bean);
		return bean;
	}
	
	public void deleteByIds(Integer[] ids) {
		
		for (int i = 0,len = ids.length; i < len; i++) {
			 delete(ids[i]);
		}
		
	}
	@Autowired
	private IRisenOrgLifeCalendarDao dao;

	@Override
	public RisenOrgLifeCalendar update(RisenOrgLifeCalendar bean) {
		return dao.update(bean);
	}
	@Override
	public void delete(Integer id) {
		dao.delete(id);
		
	}
	@Override
	public List<RisenOrgLifeCalendar> ActivitiesToday(RisenOrgLifeCalendar bean) {
		
		return dao.ActivitiesToday(bean);
	}
	
	@Override
	public List<RisenOrgLifeCalendar> ChanToday(RisenOrgLifeCalendar bean){
	
		return dao.ChanToday(bean);
	}
	
	
	
	@Override
	public RisenOrgLifeCalendar TodayRemind(RisenOrgLifeCalendar bean) {
		
		return dao.TodayRemind(bean);
	}
	
	@Override
	public RisenOrgLifeCalendar TodayRemindChan(RisenOrgLifeCalendar bean){
		return dao.TodayRemindChan(bean);
	}
	
	@Override
	public List<RisenOrgLifeCalendar> ActivitiesMonth(RisenOrgLifeCalendar bean) {
		/**
		 * 获取本月最后一天
		 */
        bean.setEndDate(DateUtil.getMonthEndTime());
        
		return dao.ActivitiesToday(bean);
	}
	@Override
	public List<RisenOrgLifeCalendar> ChanMonth(RisenOrgLifeCalendar bean){
		/**
		 * 获取本月最后一天
		 */
		bean.setEndDate(DateUtil.getMonthEndTime());
        
		return dao.ChanToday(bean);
		
	}
	private void record(RisenOrgLifeCalendar bean) {
		String type = "";
		//活动类型 1.支部党员大会 2.支部委员会 3.党小组会 4.党课 5.组织生活会 6.民主评议 7.主题党日
		if (bean.getRisenlcMeetingtype().equals("1")) {
			type = "支部党员大会";
		} else if (bean.getRisenlcMeetingtype().equals("2")) {
			type = "支部委员会";
		}else if (bean.getRisenlcMeetingtype().equals("3")) {
			type = "党小组会";
		}else if (bean.getRisenlcMeetingtype().equals("4")) {
			type = "党课";
		}else if (bean.getRisenlcMeetingtype().equals("5")) {
			type = "组织生活会";
		}else if (bean.getRisenlcMeetingtype().equals("6")) {
			type = "民主评议";
		}else if (bean.getRisenlcMeetingtype().equals("7")) {
			type = "主题党日";
		}
		RisenIntegralRecord recordBean=new RisenIntegralRecord();//积分记录
		RisenIntegral inBean=itDao.findByOrgId(bean.getRisenlcOrgid());//积分
		if(inBean==null){//如果查询对象为空，则为第一次加分，为新增
			inBean=new RisenIntegral();
			inBean.setRisenitOrgid(bean.getRisenlcOrgid());//设置组织编号
			inBean.setRisenitOrgname(bean.getRisenlcOrgname());//设置组织名称
			inBean.setRisenitScore(new Double(0));
			itDao.save(inBean);
		}else{//否则就不是第一次加分 积分需要累加
			inBean.setRisenitScore(inBean.getRisenitScore()+1);
			itDao.update(inBean);
		}
		recordBean.setRisenirOrgid(bean.getRisenlcOrgid());//设置组织编号
		recordBean.setRisenirOrgname(bean.getRisenlcOrgname());//设置组织名称
		recordBean.setRisenirChannel(type);//设置栏目名称
		recordBean.setRisenirHandledate(new Date());//设置操作时间为当前时间
		recordBean.setRisenirScore(new Double(0.1));
		irDao.save(recordBean);
	}
	@Autowired
	private IRisenIntegralDao itDao;
	@Autowired
	private IRisenIntegralRecordDao irDao;
	@Override
	public List<String> getEvent(Integer id) {
		
		return dao.getEvent(id);
	}
	
}