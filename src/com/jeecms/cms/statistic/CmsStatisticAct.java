package com.jeecms.cms.statistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.cache.DepartmentCache;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ContentCheck;
import com.jeecms.cms.manager.assist.CmsSiteAccessCountMng;
import com.jeecms.cms.manager.assist.CmsSiteAccessMng;
import com.jeecms.cms.manager.assist.CmsSiteAccessPagesMng;
import com.jeecms.cms.manager.assist.CmsSiteAccessStatisticMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.statistic.CmsStatistic.CmsStatisticModel;
import com.jeecms.cms.statistic.CmsStatistic.TimeRange;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.springmvc.MessageResolver;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.dao.IRisenIntegralDao;
import com.risen.entity.RisenIntegralRecord;
import com.utility.date.DateUtil;

import freemarker.template.utility.StringUtil;

import static com.jeecms.cms.statistic.CmsStatistic.MEMBER;
import static com.jeecms.cms.statistic.CmsStatistic.CONTENT;
import static com.jeecms.cms.statistic.CmsStatistic.COMMENT;
import static com.jeecms.cms.statistic.CmsStatistic.GUESTBOOK;
import static com.jeecms.cms.statistic.CmsStatistic.SITEID;
import static com.jeecms.cms.statistic.CmsStatistic.STATUS;
import static com.jeecms.cms.statistic.CmsStatistic.ISREPLYED;
import static com.jeecms.cms.statistic.CmsStatistic.USERID;
import static com.jeecms.cms.statistic.CmsStatistic.CHANNELID;

import static com.jeecms.cms.statistic.CmsStatistic.STATISTIC_BY_DAY;
import static com.jeecms.cms.statistic.CmsStatistic.STATISTIC_BY_MONTH;
import static com.jeecms.cms.statistic.CmsStatistic.STATISTIC_BY_YEAR;
import static com.jeecms.cms.statistic.CmsStatistic.STATISTIC_BY_YEARS;
import static com.jeecms.common.page.SimplePage.cpn;

import static com.jeecms.cms.entity.assist.CmsSiteAccessStatistic.STATISTIC_ALL;
import static com.jeecms.cms.entity.assist.CmsSiteAccessStatistic.STATISTIC_SOURCE;
import static com.jeecms.cms.entity.assist.CmsSiteAccessStatistic.STATISTIC_LINK;
import static com.jeecms.cms.entity.assist.CmsSiteAccessStatistic.STATISTIC_AREA;

@Controller
public class CmsStatisticAct {
	
	@SuppressWarnings("static-access")
	@RequiresPermissions("statistic:member:v_list")
	@RequestMapping("/statistic/member/v_list.do")
	public String memberList(String queryModel, Integer year, Integer month,
			Integer day,Date begin,Date end,HttpServletRequest request, ModelMap model,Integer deptId) {
		//一、上年底总数 由于现阶段没有 整建制转入、整建制转出 标识，所以此处只统计总数
		CmsStatisticModel statisticModel = getStatisticModel(queryModel);
		Map<String, Object> restrictions = new HashMap<String, Object>();
		Integer siteId = CmsUtils.getSiteId(request);
		CmsUser loginUser = CmsUtils.getUser(request);	
		restrictions.put(SITEID, siteId);
		Integer loginUserDeptId = null;
		if(loginUser.getDepartment().getId().equals(1)){
			loginUserDeptId = 1;
		}else{
			loginUserDeptId = loginUser.getDepartment().getId();
		}
		CmsDepartment dept =null;
		if(deptId == null){
			System.out.println(loginUser.getDepartment().getId().toString()+":"+loginUser.getDepartment().getId().equals(1));
			if (!loginUser.getDepartment().getId().equals(1)) {
				dept= cmsDepartmentMng.findById(loginUser.getDepartment().getId());
				if (dept.getParent()!=null) {
					deptId = dept.getParent().getId();
				}
			}else{
				dept=loginUser.getDepartment();
			}
		}else{
			if(deptId.intValue()!=1){
				dept=cmsDepartmentMng.findByJgdw(deptId);
			}else{
				dept=loginUser.getDepartment();
			}
		}
		restrictions.put("deptId",deptId);
		restrictions.put("deptType", loginUser.getDepartment().getSddspoOrglevel());
		Date now=Calendar.getInstance().getTime();
		Date monthBegin=DateUtils.getSpecficMonthStart(now, 0);
		Date yearBegin=DateUtils.getSpecficYearStart(now, 0);
		Date yearNext=DateUtils.getSpecficYearStart(now, 1);
		TimeRange yearTimeRange=TimeRange.getInstance(yearBegin, now);
		if(begin==null){
			begin=DateUtils.getStartDate(monthBegin);
		}else{
			begin=DateUtils.getStartDate(begin);
		}
		if(end==null){
			end=DateUtils.getFinallyDate(now);
		}else{
			end=DateUtils.getFinallyDate(end);
		}
		long lastYearCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.lastYear.toString(), yearTimeRange, restrictions,dept);
		long thisYearAddCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.thisYearAdd.toString(), yearTimeRange, restrictions,dept);
		long thisYearDecreaseCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.thisYearDecrease.toString(), yearTimeRange, restrictions,dept);
		long endOfTheYearCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.endOfTheYear.toString(), yearTimeRange.getInstance(yearNext, now), restrictions,dept)+thisYearDecreaseCount;
		long atTheEndOfTheYearCount = endOfTheYearCount-thisYearDecreaseCount;
		
		List<Object[]> lastYearList, thisYearAddList_fazhan,thisYearAddList_zhuanru,thisYearDecreaseList;
		if(statisticModel.name().equals(CmsStatisticModel.lastYear.toString())){
			lastYearList=cmsStatisticSvc.statisticMemberByTarget(null,CmsStatisticModel.lastYear.toString(), yearBegin, null,restrictions);
			model.addAttribute("lastYearList", lastYearList);
		}else if(statisticModel.name().equals(CmsStatisticModel.thisYearAdd.toString())){
			thisYearAddList_fazhan=cmsStatisticSvc.statisticMemberByTarget(1,CmsStatisticModel.thisYearAdd.toString(), yearBegin, null,restrictions);
			//thisYearAddList_zhuanru=cmsStatisticSvc.statisticMemberByTarget(2,CmsStatisticModel.thisYearAdd.toString(), yearBegin, null,restrictions);
			model.addAttribute("thisYearAddList_fazhan", thisYearAddList_fazhan);
			model.addAttribute("thisYearAddList_zhuanru", thisYearAddCount);
		}else if(statisticModel.name().equals(CmsStatisticModel.thisYearDecrease.toString())){
			thisYearDecreaseList=cmsStatisticSvc.statisticMemberByTarget(null,CmsStatisticModel.thisYearDecrease.toString(), yearBegin, null,restrictions);
			Integer outParty = cmsStatisticSvc.getOutOrStopPartyUserNum(deptId, "出党");
			Integer stopParty = cmsStatisticSvc.getOutOrStopPartyUserNum(deptId, "停止党籍");
			Integer deathParty = cmsStatisticSvc.getOutOrStopPartyUserNum(deptId, "死亡");
			model.addAttribute("thisYearDecreaseList", thisYearDecreaseList);
			model.addAttribute("outParty", outParty);
			model.addAttribute("stopParty", stopParty);
			model.addAttribute("deathParty", deathParty);
		}
		
		String isTop = "Yes";    //判断是不是省局机关党委或者admin
		if(loginUserDeptId!=1){
			isTop = "No";
		}
		model.addAttribute("isTop",isTop);
		model.addAttribute("deptType",loginUser.getDepartment().getSddspoOrglevel());
		model.addAttribute("lastYearCount", lastYearCount);
		model.addAttribute("thisYearAddCount", thisYearAddCount);
		model.addAttribute("thisYearDecreaseCount", thisYearDecreaseCount);
		model.addAttribute("endOfTheYearCount", endOfTheYearCount);
		model.addAttribute("atTheEndOfTheYearCount", atTheEndOfTheYearCount);
		model.addAttribute("differCount",endOfTheYearCount-atTheEndOfTheYearCount);
		
		model.addAttribute("statisticModel", statisticModel.name());
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "statistic/member/list";
	}

	@RequiresPermissions("statistic:content:v_list")
	@RequestMapping("/statistic/content/v_list.do")
	public String contentList(HttpServletRequest request, String queryModel,
			Integer channelId, Integer year, Integer month, Integer day,
			ModelMap model) {
		String queryInputUsername = RequestUtils.getQueryParam(request,
				"queryInputUsername");
		Integer queryInputUserId = null;
		if (!StringUtils.isBlank(queryInputUsername)) {
			CmsUser u = cmsUserMng.findByUsername(queryInputUsername);
			if (u != null) {
				queryInputUserId = u.getId();
			} else {
				// 用户名不存在，清空。
				queryInputUsername = null;
			}
		}
		Map<String, Object> restrictions = new HashMap<String, Object>();
		Integer siteId = CmsUtils.getSiteId(request);
		restrictions.put(SITEID, siteId);
		restrictions.put(USERID, queryInputUserId);
		restrictions.put(CHANNELID, channelId);
		restrictions.put(STATUS, ContentCheck.CHECKED);
		CmsStatisticModel statisticModel = getStatisticModel(queryModel);
		List<CmsStatistic> list = cmsStatisticSvc.statisticByModel(CONTENT,
				statisticModel, year, month, day,null,null, restrictions);
		List<Channel> topList = channelMng.getTopList(siteId, true);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				true);
		putCommonData(statisticModel, list, year, month, day, model);
		Date now=Calendar.getInstance().getTime();
		Date dayBegin=DateUtils.getStartDate(now);
		Date weekBegin=DateUtils.getSpecficWeekStart(now, 0);
		Date monthBegin=DateUtils.getSpecficMonthStart(now, 0);
		Date yearBegin=DateUtils.getSpecficYearStart(now, 0);
		TimeRange dayTimeRange=TimeRange.getInstance(dayBegin, now);
		TimeRange weekTimeRange=TimeRange.getInstance(weekBegin, now);
		TimeRange monthTimeRange=TimeRange.getInstance(monthBegin, now);
		TimeRange totalTimeRange=TimeRange.getInstance(null, now);
		TimeRange yearTimeRange=TimeRange.getInstance(yearBegin, now);
		long releaseDayCount=cmsStatisticSvc.statistic(CmsStatistic.CONTENT, dayTimeRange, restrictions);
		long releaseWeekCount=cmsStatisticSvc.statistic(CmsStatistic.CONTENT, weekTimeRange, restrictions);
		long releaseMonthCount=cmsStatisticSvc.statistic(CmsStatistic.CONTENT, monthTimeRange, restrictions);
		long releaseYearCount=cmsStatisticSvc.statistic(CmsStatistic.CONTENT, yearTimeRange, restrictions);
		long releaseTotalCount=cmsStatisticSvc.statistic(CmsStatistic.CONTENT, totalTimeRange, restrictions);
		List<Object[]> dayList,monthList,yearList;
		if(statisticModel.equals(CmsStatisticModel.month)){
			monthList=cmsStatisticSvc.statisticContentByTarget(STATISTIC_BY_MONTH, monthBegin, now,restrictions);
			model.addAttribute("monthList", monthList);
		}else if(statisticModel.equals(CmsStatisticModel.day)){
			dayList=cmsStatisticSvc.statisticContentByTarget(STATISTIC_BY_DAY, dayBegin, now,restrictions);
			model.addAttribute("dayList", dayList);
		}else if(statisticModel.equals(CmsStatisticModel.year)){
			yearList=cmsStatisticSvc.statisticContentByTarget(STATISTIC_BY_YEAR, yearBegin, now,restrictions);
			model.addAttribute("yearList", yearList);
		}
		model.addAttribute("releaseDayCount", releaseDayCount);
		model.addAttribute("releaseWeekCount", releaseWeekCount);
		model.addAttribute("releaseMonthCount", releaseMonthCount);
		model.addAttribute("releaseYearCount", releaseYearCount);
		model.addAttribute("releaseTotalCount", releaseTotalCount);
		model.addAttribute("queryInputUsername", queryInputUsername);
		model.addAttribute("channelList", channelList);
		model.addAttribute("channelId", channelId);
		return "statistic/content/list";
	}
	
	/**
	 * 党员基本情况分析
	 * @param queryModel
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("statistic:member:basicInfo_list.do")
	@RequestMapping("/statistic/member/basicInfo_list.do")
	public String basicInfoList(String queryModel, HttpServletRequest request, ModelMap model,Integer deptId){
		//CmsSite site = CmsUtils.getSite(request);
		CmsUser loginUser = CmsUtils.getUser(request);	
		List<Object[]> list = new ArrayList<Object[]>();
		Integer loginUserDeptId = null;
		int id = loginUser.getDepartment().getId();
		if(id == 1){
			loginUserDeptId = 1;
		}else{
			loginUserDeptId = loginUser.getDepartment().getParent().getId();
		}
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
		}
		if(deptId==null){
			if (id!=1) {
				CmsDepartment dept = cmsDepartmentMng.findById(id);
				if (dept.getParent()!=null) {
					deptId = dept.getParent().getId();
				}
			}else{
				CmsDepartment dept = loginUser.getDepartment();
			}
		}
		//list = cmsStatisticSvc.basicInfoList(queryModel, deptId, "6");
		model.addAttribute("statisticModel", queryModel);
		//model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.basicInfoList(queryModel, deptId, "6");
		model.addAttribute("listAll", listAll);
		String isTop = "Yes";
		if(loginUserDeptId!=1){
			isTop = "No";
		}
		model.addAttribute("isTop", isTop);
		return "statistic/member/basicInfo_list";
	}
	
	@RequiresPermissions("statistic:member:partyTime_list.do")
	@RequestMapping("/statistic/member/partyTime_list.do")
	public String partyTimeList(String queryModel, HttpServletRequest request, ModelMap model,Integer deptId){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser loginUser = CmsUtils.getUser(request);
		Integer loginUserDeptId = loginUser.getDepartment().getId();
		List<Object[]> list = new ArrayList<Object[]>();
		
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
		}
		if(deptId == null){
			if (!loginUser.getDepartment().getId().equals(1)) {
				CmsDepartment dept = cmsDepartmentMng.findById(loginUser.getDepartment().getId());
				if (dept.getParent()!=null) {
					deptId = dept.getParent().getId();
				}
			}
		}
		
		list = cmsStatisticSvc.partyTimeList(queryModel, deptId, null);
		model.addAttribute("statisticModel", queryModel);
		String isTop = "Yes"; 
		if(loginUserDeptId != 1 ){
			isTop = "No"; 
		}
		model.addAttribute("list",list);
		model.addAttribute("isTop",isTop);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.partyTimeList(null, deptId, "6");
		model.addAttribute("listAll", listAll);
		return "statistic/member/partyTime_list";
	}
	
	@RequiresPermissions("statistic:member:educationAnalysis_list.do")
	@RequestMapping("/statistic/member/educationAnalysis_list.do")
	public String educationAnalysisList(String queryModel, HttpServletRequest request, ModelMap model,Integer deptId){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser loginUser = CmsUtils.getUser(request);
		List<Object[]> list = new ArrayList<Object[]>();
		Integer loginUserDeptId = loginUser.getDepartment().getId();
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
		}
		if(deptId == null){
			if (!loginUser.getDepartment().getId().equals(1)) {
				CmsDepartment dept = cmsDepartmentMng.findById(loginUser.getDepartment().getId());
				if (dept.getParent()!=null) {
					deptId = dept.getParent().getId();
				}
			}
		}
		list = cmsStatisticSvc.educationAnalysisList(queryModel, deptId, null);
		model.addAttribute("statisticModel", queryModel);
		model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.educationAnalysisList(null, deptId, null);
		model.addAttribute("listAll", listAll);
		String isTop = "Yes";
		if(loginUserDeptId != 1){
			isTop = "No";
		}
		model.addAttribute("isTop", isTop);
		return "statistic/member/educationAnalysis_list";
	}
	/*
	 * 统计党员年度变化情况
	 */
	@RequiresPermissions("statistic:member:memberYearChange_list.do")
	@RequestMapping("/statistic/member/memberYearChange_list.do")
	public String memberYearChangeList(HttpServletRequest request, ModelMap model){
		//获取近五年的年份
		int nowYear = DateUtils.getDateField(new Date(), Calendar.YEAR);//今年
		int lastYear = nowYear - 1;//去年
		int beforeLastYear = nowYear - 2;//前年
		int nextYear = nowYear + 1;//明年
		int AfterNextYear = nowYear + 2;//后年
		//获取某一年的党员数量
		List<String> yearList = new ArrayList<String>();
		yearList.add(beforeLastYear+"");
		yearList.add(lastYear+"");
		yearList.add(nowYear+"");
		yearList.add(nextYear+"");
		yearList.add(AfterNextYear+"");
		List<Object[]> list = cmsStatisticSvc.getAllMembersNotLeave();
		model.addAttribute("list", list);
		model.addAttribute("yearlist", yearList);
		return "statistic/member/memberYearChange_list";
	}
	@RequiresPermissions("statistic:member:threeLesson_list.do")
	@RequestMapping("/statistic/member/threeLesson_list.do")
	public String threeLessonList(String queryModel, HttpServletRequest request, ModelMap model){
		if(StringUtils.isBlank(queryModel)){
			model.addAttribute("statisticModel", "all");
		}else{
			model.addAttribute("statisticModel", queryModel);
		}
		return "statistic/member/threeLesson_list";
	}
	
	@RequiresPermissions("statistic:member:outTheParty_list.do")
	@RequestMapping("/statistic/member/outTheParty_list.do")
	public String outThePartyList(String queryModel, HttpServletRequest request, ModelMap model){
		if(StringUtils.isBlank(queryModel)){
			model.addAttribute("statisticModel", "all");
		}else{
			model.addAttribute("statisticModel", queryModel);
		}
		return "statistic/member/outTheParty_list";
	}
	
	@RequiresPermissions("statistic:member:orgChange_list.do")
	@RequestMapping("/statistic/member/orgChange_list.do")
	public String orgChangeList(String queryModel, HttpServletRequest request, ModelMap model,Integer deptId){
		if(StringUtils.isBlank(queryModel)){
			model.addAttribute("statisticModel", "all");
		}else{
			model.addAttribute("statisticModel", queryModel);
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment dept = null;
		if(deptId == null){
			dept = user.getDepartment();
		}else{
			dept = cmsDepartmentMng.findById(deptId);
		}
		
		Integer loginUserDeptId = user.getDepartment().getId();
		Map<String, String> tjMap = new HashMap<String, String>();
		String userType = "";
		if(dept.getId() == 1){
			userType = "Province";
		}else if((dept.getParent().getParent()==null) && dept.getPriority() == 1){
			userType = "City";
		}else if(dept.getParent().getParent().getParent()==null){
			userType = "Area";
		}
		if(dept.getId() == 1){ 
			tjMap = cmsDepartmentMng.statisticsDeptSum(1,null);
		}else if(dept.getParent() == null){
			tjMap = cmsDepartmentMng.statisticsDeptSum(2,dept.getId());
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap = cmsDepartmentMng.statisticsDeptSum(3,dept.getParent().getId());
		}else{
			tjMap = cmsDepartmentMng.statisticsDeptSum(4,dept.getParent().getId());
		}
		model.addAttribute("deptname", user.getDepartment().getName());
		model.addAttribute("tjMap", tjMap);
		model.addAttribute("userType", userType);
		String isTop = "Yes";
		if(loginUserDeptId != 1){
			isTop = "No";
		}
		model.addAttribute("isTop", isTop);
		return "statistic/member/orgChange_list";
	}
	
	@RequiresPermissions("statistic:member:generalInfo_list.do")
	@RequestMapping("/statistic/member/generalInfo_list.do")
	public String generalInfoList(String queryModel, HttpServletRequest request, ModelMap model, Date staDate, Date endDate){
		if(StringUtils.isBlank(queryModel)){
			model.addAttribute("statisticModel", "all");
		}else{
			model.addAttribute("statisticModel", queryModel);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
		SimpleDateFormat sdf4 = new SimpleDateFormat("dd");
		String sdate = "";
		String edate = "";
		Date dqDate = new Date();
		if(staDate == null || endDate == null){
			int nian = Integer.parseInt(sdf2.format(dqDate));
			int yue = Integer.parseInt(sdf3.format(dqDate));
			int ri = Integer.parseInt(sdf4.format(dqDate));
			sdate = nian + "/1/1";
			if(yue + 2 > 12){
				nian = nian +1;
				yue = yue +2 - 12;
			}else{
				yue = yue + 2;
			} 
			edate = nian + "/" + yue + "/" + ri;
		}else{
			sdate = sdf.format(staDate);
			edate = sdf.format(endDate);
		}
		
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment dept = user.getDepartment();
		Map<String, String> tjMap = new HashMap<String, String>();
		Map<String, String> rjqmMap = new HashMap<String, String>();
		Map<String, String> yhjMap = new HashMap<String, String>();
		//Map<String, String> yhjMap = new HashMap<String, String>();
		//Map<String, String> yhjMap = new HashMap<String, String>();
		if(dept.getId() == 1){ 
			tjMap = cmsDepartmentMng.statisticsDeptSum(1,null);
			rjqmMap = cmsDepartmentMng.statisticsDeptQmhjSum(1, null, sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = cmsDepartmentMng.statisticsDeptYhjSum(1, null, sdate, edate);
		}else if(dept.getParent() == null){
			tjMap = cmsDepartmentMng.statisticsDeptSum(2,dept.getId());
			rjqmMap = cmsDepartmentMng.statisticsDeptQmhjSum(2, dept.getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = cmsDepartmentMng.statisticsDeptYhjSum(2, dept.getId(), sdate, edate);
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap = cmsDepartmentMng.statisticsDeptSum(3,dept.getParent().getId());
			rjqmMap = cmsDepartmentMng.statisticsDeptQmhjSum(3, dept.getParent().getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = cmsDepartmentMng.statisticsDeptYhjSum(3, dept.getParent().getId(), sdate, edate);
		}else{
			tjMap = cmsDepartmentMng.statisticsDeptSum(4,dept.getId());
			rjqmMap = cmsDepartmentMng.statisticsDeptQmhjSum(4, dept.getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = cmsDepartmentMng.statisticsDeptYhjSum(4, dept.getId(), sdate, edate);
		}
		int rjqmsum = Integer.parseInt(rjqmMap.get("rjqmsum"))+Integer.parseInt(yhjMap.get("yhjsum"));
		int rjqmZdz = Integer.parseInt(rjqmMap.get("rjqmZdz"))+Integer.parseInt(yhjMap.get("yhjZdz"));
		int rjqmDzz = Integer.parseInt(rjqmMap.get("rjqmDzz"))+Integer.parseInt(yhjMap.get("yhjDzz"));
		int rjqmJgdw = Integer.parseInt(rjqmMap.get("rjqmJgdw"))+Integer.parseInt(yhjMap.get("yhjJgdw"));
		int rjqmZb = Integer.parseInt(rjqmMap.get("rjqmZb"))+Integer.parseInt(yhjMap.get("yhjZb"));
		rjqmMap.put("rjqmsum", rjqmsum+"");
		rjqmMap.put("rjqmZdz", rjqmZdz+"");
		rjqmMap.put("rjqmDzz", rjqmDzz+"");
		rjqmMap.put("rjqmJgdw", rjqmJgdw+"");
		rjqmMap.put("rjqmZb", rjqmZb+"");
		tjMap.putAll(rjqmMap);
		tjMap.putAll(yhjMap);
		model.addAttribute("deptname", user.getDepartment().getName());
		model.addAttribute("tjMap", tjMap);
		return "statistic/member/generalInfo_list";
	}
	
	@RequiresPermissions("statistic:member:general_HuanJieInfoList.do")
	@RequestMapping("/statistic/member/general_HuanJieInfoList.do")
	public String generalHuanJieInfoList(String queryModel, HttpServletRequest request, ModelMap model, Date staDate, Date endDate,Integer deptId){
		if(StringUtils.isBlank(queryModel)){
			model.addAttribute("statisticModel", "all");
		}else{
			model.addAttribute("statisticModel", queryModel);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String sdate = sdf.format(new Date());
		CmsUser user = CmsUtils.getUser(request);
		Integer loginUserDeptId = user.getDepartment().getId();
		CmsDepartment dept = user.getDepartment();
		if(deptId != null){
			dept = cmsDepartmentMng.findById(deptId);
		}
		
		Map<String, String> tjMap = new HashMap<String, String>();
		Map<String, String> yqhjMap = new HashMap<String, String>();
		Map<String, String> aqhjMap = new HashMap<String, String>();
		if(dept.getId() == 1){ 
			tjMap = cmsDepartmentMng.statisticsDeptSum(1,null);
			yqhjMap = cmsDepartmentMng.statisticsDeptYqhjSum(1, null, sdate);
			aqhjMap = cmsDepartmentMng.statisticsDeptAqhjSum(1, null, sdate);
		}else if(dept.getParent() == null){
			tjMap = cmsDepartmentMng.statisticsDeptSum(2,dept.getId());
			yqhjMap = cmsDepartmentMng.statisticsDeptYqhjSum(2, dept.getId(), sdate);
			aqhjMap = cmsDepartmentMng.statisticsDeptAqhjSum(2, dept.getId(), sdate);
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap = cmsDepartmentMng.statisticsDeptSum(3,dept.getParent().getId());
			yqhjMap = cmsDepartmentMng.statisticsDeptYqhjSum(3, dept.getParent().getId(), sdate);
			aqhjMap = cmsDepartmentMng.statisticsDeptAqhjSum(3, dept.getParent().getId(), sdate);
		}else{
			tjMap = cmsDepartmentMng.statisticsDeptSum(4,dept.getParent().getId());
			yqhjMap = cmsDepartmentMng.statisticsDeptYqhjSum(4, dept.getParent().getId(), sdate);
			aqhjMap = cmsDepartmentMng.statisticsDeptAqhjSum(4, dept.getParent().getId(), sdate);
		}
		tjMap.putAll(yqhjMap);
		tjMap.putAll(aqhjMap);
		model.addAttribute("deptname", user.getDepartment().getName());
		model.addAttribute("tjMap", tjMap);
		String isTop = "Yes";
		if(loginUserDeptId != 1){
			isTop = "No";
		}
		model.addAttribute("isTop", isTop);
		return "statistic/member/generalHuanJieInfo_list";
	}
	
	@RequiresPermissions("statistic:member:duties_list.do")
	@RequestMapping("/statistic/member/duties_list.do")
	public String dutiesList(String queryModel, HttpServletRequest request, ModelMap model){
		try{
		CmsSite site = CmsUtils.getSite(request);
		CmsUser loginUser = CmsUtils.getUser(request);
		Integer deptId = null;
		CmsDepartment dept = cmsDepartmentMng.findById(loginUser.getDepartment().getId());
		List<Object[]> list = new ArrayList<Object[]>();
		
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
		}
		if (!loginUser.getDepartment().getId().equals(1)) {
			if (dept.getParent()!=null) {
				deptId = dept.getParent().getId();
			}
		}
		Integer orgLevel = dept.getSddspoOrglevel();
		model.addAttribute("orgLevel", orgLevel);
		
		list = cmsStatisticSvc.dutiesList(queryModel, deptId);
		model.addAttribute("statisticModel", queryModel);
		model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.dutiesList(null, deptId);
		//用来统计该机构下所有委员 书记的总数
		//省局总人数
		int sjTotal = 0;
		int shjTotal = 0;
		int qxTotal = 0;
		int jcsTotal = 0;
		for (Object[] objects : listAll) {
			for (int i = 0; i < objects.length; i++) {
				if(i%4==0){
					sjTotal += Integer.parseInt((objects[i] == null ? "0" : objects[i].toString()));
				}
				if(i%4==1){
					shjTotal += Integer.parseInt((objects[i] == null ? "0" : objects[i].toString()));
				}
				if(i%4==2){
					qxTotal += Integer.parseInt((objects[i] == null ? "0" : objects[i].toString()));
				}
				if(i%4==3){
					jcsTotal += Integer.parseInt((objects[i] == null ? "0" : objects[i].toString()));
				}
			}
		}
		model.addAttribute("listAll", listAll);
		model.addAttribute("sjTotal", sjTotal+"");
		model.addAttribute("shjTotal", shjTotal+"");
		model.addAttribute("qxTotal", qxTotal+"");
		model.addAttribute("jcsTotal", jcsTotal+"");
		} catch(Exception e){
			e.printStackTrace();
		}
		return "statistic/member/duties_list";
	}
	
	@RequiresPermissions("statistic:score:yearintegralanalyze")
	@RequestMapping("/statistic/score/yearintegralanalyze.do")
	public String yearintegral(HttpServletRequest request, String queryModel,
			Integer year, Integer month, Integer day, Boolean isReplyed,
			ModelMap model) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		List<CmsDepartment>listBack=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = user.getDepartment();
		if(cmsDepartment.getId() == 1){
			for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
				if(DepartmentCache.departentList.get(i).getParent()==null){
					listBack.add(DepartmentCache.departentList.get(i));
				}
			}
			for (int i = 0; i < listBack.size(); i++) {
				for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
					if(listBack.get(i)==DepartmentCache.departentList.get(j).getParent()){
						if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") && DepartmentCache.departentList.get(j).getPriority().intValue()==1){
							list.add(DepartmentCache.departentList.get(j));
						}
					}
				}
			}
		}else{
			for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
				if(DepartmentCache.departentList.get(i).getParent() != null && DepartmentCache.departentList.get(i).getParent().getId().intValue()==cmsDepartment.getParent().getId().intValue()){
					listBack.add(DepartmentCache.departentList.get(i));
				}
			}
			for (int i = 0; i < listBack.size(); i++) {
				for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
					if(listBack.get(i)==DepartmentCache.departentList.get(j).getParent()){
						if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") || DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("党总支")){
							list.add(DepartmentCache.departentList.get(j));
						}
					}
				}
			}
		}
		String strIds = "(";
		try{
			//组装strIds
	
			if(list.size()<0){
				strIds += String.valueOf(cmsDepartment.getId());
			}else{
				for (int i = 0; i < list.size(); i++) {
					if(i==list.size()-1){
						strIds+=("'"+list.get(i).getId()+"'");
					}else {
						strIds+=("'"+list.get(i).getId()+"',");
					}	
				}
			}
			strIds=strIds+")";
		}catch (Exception e) {
			e.printStackTrace();
		}
		List<Object[]> list1 =risenDao.getYearScore("2015,2016,2017,2018",strIds);
		model.addAttribute("list",list1);
		return "statistic/score/yearintegralanalyze";
	}
	
	@RequiresPermissions("statistic:comment:v_list")
	@RequestMapping("/statistic/comment/v_list.do")
	public String commentList(HttpServletRequest request, String queryModel,
			Integer year, Integer month, Integer day, Boolean isReplyed,
			ModelMap model) {
		Map<String, Object> restrictions = new HashMap<String, Object>();
		Integer siteId = CmsUtils.getSiteId(request);
		restrictions.put(SITEID, siteId);
		restrictions.put(ISREPLYED, isReplyed);
		CmsStatisticModel statisticModel = getStatisticModel(queryModel);
		List<CmsStatistic> list = cmsStatisticSvc.statisticByModel(COMMENT,
				statisticModel, year, month, day, null,null,restrictions);
		putCommonData(statisticModel, list, year, month, day, model);
		Date now=Calendar.getInstance().getTime();
		Date dayBegin=DateUtils.getStartDate(now);
		Date weekBegin=DateUtils.getSpecficWeekStart(now, 0);
		Date monthBegin=DateUtils.getSpecficMonthStart(now, 0);
		Date yearBegin=DateUtils.getSpecficYearStart(now, 0);
		TimeRange dayTimeRange=TimeRange.getInstance(dayBegin, now);
		TimeRange weekTimeRange=TimeRange.getInstance(weekBegin, now);
		TimeRange monthTimeRange=TimeRange.getInstance(monthBegin, now);
		TimeRange totalTimeRange=TimeRange.getInstance(null, now);
		TimeRange yearTimeRange=TimeRange.getInstance(yearBegin, now);
		long dayCount=cmsStatisticSvc.statistic(CmsStatistic.COMMENT, dayTimeRange, restrictions);
		long weekCount=cmsStatisticSvc.statistic(CmsStatistic.COMMENT, weekTimeRange, restrictions);
		long monthCount=cmsStatisticSvc.statistic(CmsStatistic.COMMENT, monthTimeRange, restrictions);
		long yearCount=cmsStatisticSvc.statistic(CmsStatistic.COMMENT, yearTimeRange, restrictions);
		long totalCount=cmsStatisticSvc.statistic(CmsStatistic.COMMENT, totalTimeRange, restrictions);
		List<Object[]> dayList,monthList,yearList;
		if(statisticModel.equals(CmsStatisticModel.month)){
			monthList=cmsStatisticSvc.statisticCommentByTarget(STATISTIC_BY_MONTH,
					siteId,isReplyed,monthBegin, now);
			model.addAttribute("monthList", monthList);
		}else if(statisticModel.equals(CmsStatisticModel.day)){
			dayList=cmsStatisticSvc.statisticCommentByTarget(STATISTIC_BY_DAY,
					siteId,isReplyed,dayBegin, now);
			model.addAttribute("dayList", dayList);
		}else if(statisticModel.equals(CmsStatisticModel.year)){
			yearList=cmsStatisticSvc.statisticCommentByTarget(STATISTIC_BY_YEAR,
					siteId,isReplyed,yearBegin, now);
			model.addAttribute("yearList", yearList);
		}
		model.addAttribute("isReplyed", isReplyed);
		model.addAttribute("dayCount", dayCount);
		model.addAttribute("weekCount", weekCount);
		model.addAttribute("monthCount", monthCount);
		model.addAttribute("yearCount", yearCount);
		model.addAttribute("totalCount", totalCount);
		return "statistic/comment/list";
	}

	@RequiresPermissions("statistic:guestbook:v_list")
	@RequestMapping("/statistic/guestbook/v_list.do")
	public String guestbookList(HttpServletRequest request, String queryModel,
			Integer year, Integer month, Integer day, Boolean isReplyed,
			ModelMap model) {
		Map<String, Object> restrictions = new HashMap<String, Object>();
		Integer siteId = CmsUtils.getSiteId(request);
		restrictions.put(SITEID, siteId);
		restrictions.put(ISREPLYED, isReplyed);
		CmsStatisticModel statisticModel = getStatisticModel(queryModel);
		List<CmsStatistic> list = cmsStatisticSvc.statisticByModel(GUESTBOOK,
				statisticModel, year, month, day,null,null, restrictions);
		putCommonData(statisticModel, list, year, month, day, model);
		Date now=Calendar.getInstance().getTime();
		Date dayBegin=DateUtils.getStartDate(now);
		Date weekBegin=DateUtils.getSpecficWeekStart(now, 0);
		Date monthBegin=DateUtils.getSpecficMonthStart(now, 0);
		Date yearBegin=DateUtils.getSpecficYearStart(now, 0);
		TimeRange dayTimeRange=TimeRange.getInstance(dayBegin, now);
		TimeRange weekTimeRange=TimeRange.getInstance(weekBegin, now);
		TimeRange monthTimeRange=TimeRange.getInstance(monthBegin, now);
		TimeRange totalTimeRange=TimeRange.getInstance(null, now);
		TimeRange yearTimeRange=TimeRange.getInstance(yearBegin, now);
		long dayCount=cmsStatisticSvc.statistic(CmsStatistic.GUESTBOOK, dayTimeRange, restrictions);
		long weekCount=cmsStatisticSvc.statistic(CmsStatistic.GUESTBOOK, weekTimeRange, restrictions);
		long monthCount=cmsStatisticSvc.statistic(CmsStatistic.GUESTBOOK, monthTimeRange, restrictions);
		long yearCount=cmsStatisticSvc.statistic(CmsStatistic.GUESTBOOK, yearTimeRange, restrictions);
		long totalCount=cmsStatisticSvc.statistic(CmsStatistic.GUESTBOOK, totalTimeRange, restrictions);
		List<Object[]> dayList,monthList,yearList;
		if(statisticModel.equals(CmsStatisticModel.month)){
			monthList=cmsStatisticSvc.statisticGuestbookByTarget(STATISTIC_BY_MONTH,
					siteId,isReplyed,monthBegin, now);
			model.addAttribute("monthList", monthList);
		}else if(statisticModel.equals(CmsStatisticModel.day)){
			dayList=cmsStatisticSvc.statisticGuestbookByTarget(STATISTIC_BY_DAY,
					siteId,isReplyed,dayBegin, now);
			model.addAttribute("dayList", dayList);
		}else if(statisticModel.equals(CmsStatisticModel.year)){
			yearList=cmsStatisticSvc.statisticGuestbookByTarget(STATISTIC_BY_YEAR,
					siteId,isReplyed,yearBegin, now);
			model.addAttribute("yearList", yearList);
		}
		model.addAttribute("dayCount", dayCount);
		model.addAttribute("weekCount", weekCount);
		model.addAttribute("monthCount", monthCount);
		model.addAttribute("yearCount", yearCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("isReplyed", isReplyed);
		return "statistic/guestbook/list";
	}
	
	@RequiresPermissions("statistic:channel:v_list")
	@RequestMapping("/statistic/channel/v_list.do")
	public String channelList(Integer channelLevel,String view, 
			HttpServletRequest request,ModelMap model) {
		Integer siteId=CmsUtils.getSiteId(request);
		List<Channel>list;
		if(channelLevel==null){
			channelLevel=1;
		}
		if(StringUtils.isBlank(view)){
			view="view";
		}
		if(channelLevel.equals(1)){
			//顶层栏目
			list=channelMng.getTopList(siteId, false);
		}else{
			//底层栏目
			list=channelMng.getBottomList(siteId, false);
		}
		//view比较的列
		Collections.sort(list, new ListChannelComparator(view));
		model.addAttribute("list", list);
		model.addAttribute("channelLevel", channelLevel);
		model.addAttribute("view", view);
		return "statistic/channel/list";
	}

	@RequiresPermissions("flow:pv:v_list")
	@RequestMapping("/flow/pv/v_list.do")
	public String pageViewList(Integer flag,Date year,Date begin,Date end
			,HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Calendar calendar=Calendar.getInstance();
		//flag 1 按本月统计 2年度统计 3区间统计 4当前日小时统计
		if(flag==null){
			flag=4;
		}
		//默认一个月
		if(begin==null&&end==null){
			end=calendar.getTime();
			begin=DateUtils.getSpecficMonthStart(end, 0);
		}
		List<Object[]> list;
		if(flag==1){
			//本月
			list=cmsAccessStatisticMng.statistic(begin, end, siteId, STATISTIC_ALL,null);
		}if(flag==3){
			//区间统计
			if(begin!=null){
				begin=DateUtils.getStartDate(begin);
			}
			if(end!=null){
				end=DateUtils.getFinallyDate(end);
			}
			list=cmsAccessStatisticMng.statistic(begin, end, siteId, STATISTIC_ALL,null);
		}else if(flag==2){
			//选择年度统计
			if(year==null){
				year=calendar.getTime();
			}
			calendar.setTime(year);
			list=cmsAccessStatisticMng.statisticByYear(calendar.get(Calendar.YEAR), siteId,STATISTIC_ALL,null,true,null);
		}else{
			//今日数据统计(按小时)
			list=cmsAccessMng.statisticToday(siteId,null);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("list", list);
		return "statistic/pv/list";
	}
	
	@RequiresPermissions("flow:source:v_list")
	@RequestMapping("/flow/source/v_list.do")
	public String sourceList(String type,Integer flag,Integer target,
			Date year,Date begin,Date end,
			HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Calendar calendar=Calendar.getInstance();
		List<String>columnValues;
		if(StringUtils.isBlank(type)){
			type=STATISTIC_SOURCE;
		}
		if(flag==null){
			flag=2;
		}
		//展示指标(0-pv,1-IP,2-访客数 3-访问时长)
		if(target==null){
			target=0;
		}
		String property=type;
		if(flag==0){
			if(type.equals(STATISTIC_SOURCE)){
				property="accessSource";
			}else if(type.equals(STATISTIC_LINK)){
				property="externalLink";
			}
			columnValues=cmsAccessMng.findPropertyValues(property, siteId);
		}else{
			columnValues=cmsAccessStatisticMng.findStatisticColumnValues(begin, end, siteId, property);
		}
		//默认一个月
		if(begin==null&&end==null){
			end=calendar.getTime();
			begin=DateUtils.getSpecficMonthStart(end, 0);
		}
		Map<String,List<Object[]>>resultMap=new HashMap<String, List<Object[]>>();
		Map<String,Long>totalMap=new HashMap<String, Long>();
		//flag 1 按本月统计 2年度统计 3区间统计 4当前日小时统计
		if(flag==1){
			//选择日期统计
			for(String v:columnValues){
				resultMap.put(v, cmsAccessStatisticMng.statisticByTarget(begin, end, siteId,target,type,v));
			}
		}else if(flag==2){
			//选择年度统计
			if(year==null){
				year=calendar.getTime();
			}
			calendar.setTime(year);
			for(String v:columnValues){
				resultMap.put(v, cmsAccessStatisticMng.statisticByYearByTarget(calendar.get(Calendar.YEAR), siteId,target,type,v));
			}
		}else if(flag==3){
			//区间统计
			if(begin!=null){
				begin=DateUtils.getStartDate(begin);
			}
			if(end!=null){
				end=DateUtils.getFinallyDate(end);
			}
			for(String v:columnValues){
				resultMap.put(v, cmsAccessStatisticMng.statisticByTarget(begin, end, siteId,target,type,v));
			}
		}else{
			//今日数据统计(按小时)
			for(String v:columnValues){
				resultMap.put(v, cmsAccessMng.statisticTodayByTarget(siteId, target, type, v));
			}
		}
		for(String columnValue:columnValues){
			List<Object[]> li=resultMap.get(columnValue);
			Long total=0l;
			for(Object[]array:li){
				total+=(Long)array[0];
			}
			totalMap.put(columnValue, total);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("type", type);
		model.addAttribute("target", target);
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("keys", columnValues);
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("totalMap", totalMap);
		return "statistic/source";
	}
	
	@RequiresPermissions("flow:area:v_list")
	@RequestMapping("/flow/area/v_list.do")
	public String areaList(Integer flag, Integer target,Date year,
			Date begin,Date end,HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Calendar calendar=Calendar.getInstance();
		List<String>areas;
		if(flag==null){
			flag=4;
		}
		//展示指标(0-pv,1-IP,2-访客数 3-访问时长)
		if(target==null){
			target=0;
		}
		if(flag==0){
			areas=cmsAccessMng.findPropertyValues(STATISTIC_AREA, siteId);
		}else{
			areas=cmsAccessStatisticMng.findStatisticColumnValues(begin, end, siteId, STATISTIC_AREA);
		}
		//默认一个月
		if(begin==null&&end==null){
			end=calendar.getTime();
			begin=DateUtils.getSpecficMonthStart(end, 0);
		}
		Map<String,Object[]>areaCounts=new LinkedHashMap<String, Object[]>();
		//列表数据排序后结果
		Map<String,Object[]>areaCountMap=new LinkedHashMap<String, Object[]>();
		//饼图数据map
		Map<String,Long>totalMap=new LinkedHashMap<String, Long>();
		//flag 1 按本月统计 2年度统计 3区间统计 4当前日小时统计
		for(String area:areas){
			List<Object[]>li;
			if(flag==1){
				//选择当月统计
				li=cmsAccessStatisticMng.statisticTotal(begin, end, siteId, STATISTIC_AREA, area, target);
			}else if(flag==2){
				//选择今年统计
				if(year==null){
					year=calendar.getTime();
				}
				calendar.setTime(year);
				li=cmsAccessStatisticMng.statisticByYear(calendar.get(Calendar.YEAR), siteId, STATISTIC_AREA, area, false, target);
			}else if(flag==3){
				//区间统计
				if(begin!=null){
					begin=DateUtils.getStartDate(begin);
				}
				if(end!=null){
					end=DateUtils.getFinallyDate(end);
				}
				li=cmsAccessStatisticMng.statisticTotal(begin, end, siteId, STATISTIC_AREA, area, target);
			}else{
				//今日数据统计(按小时)
				li=cmsAccessMng.statisticToday(siteId, area);
			}
			if(li.size()>0){
				areaCounts.put(area, li.get(0));
			}
		}
		ArrayList<Entry<String,Object[]>> l = new ArrayList<Entry<String,Object[]>>(areaCounts.entrySet());  
		Collections.sort(l, new MapComparator(target));
		Long otherTotal=0l;
		for(int i=0;i<l.size();i++){
			Entry<String,Object[]> e=l.get(i);
			Object[]array=e.getValue();
			Long targetValue=0l;
			if(target==0){
				targetValue=(Long) array[0];
			}else if(target==1){
				targetValue=(Long) array[1];
			}else if(target==2){
				targetValue=(Long) array[2]; 
			}else{
				targetValue=(Long) array[3];  
			}
			if(targetValue==null){
				targetValue=0l;
			}
			//饼图只留十条数据
			if(i<9){
				totalMap.put(e.getKey(),targetValue);
			}else{
				otherTotal+=targetValue;
				totalMap.put(getMessage(request, "cmsAccess.area.other"), otherTotal);
			}
			areaCountMap.put(e.getKey(), array);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("target", target);
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("areaCountMap", areaCountMap);
		model.addAttribute("totalMap", totalMap);
		return "statistic/area";
	}
	
	@RequiresPermissions("flow:visitor:v_list")
	@RequestMapping("/flow/visitor/v_list.do")
	public String visitorsGroupByPage(Integer flag,Date year,
			Date begin,Date end,HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Calendar calendar=Calendar.getInstance();
		if(flag==null){
			flag=4;
		}
		//默认一个月
		if(begin==null&&end==null){
			end=calendar.getTime();
			begin=DateUtils.getSpecficMonthStart(end, 0);
		}
		List<Object[]> li;
		//flag 1 按本月统计 2年度统计 3区间统计 4当前日小时统计
		if(flag==1){
			//选择当月统计
			li=cmsAccessCountMng.statisticVisitorCountByDate(siteId, begin, end);
		}else if(flag==2){
			//选择年度统计
			if(year==null){
				year=calendar.getTime();
			}
			calendar.setTime(year);
			li=cmsAccessCountMng.statisticVisitorCountByYear(siteId, calendar.get(Calendar.YEAR));
		}if(flag==3){
			//区间统计
			if(begin!=null){
				begin=DateUtils.getStartDate(begin);
			}
			if(end!=null){
				end=DateUtils.getFinallyDate(end);
			}
			li=cmsAccessCountMng.statisticVisitorCountByDate(siteId, begin, end);
		}else{
			//今日数据统计
			li=cmsAccessMng.statisticVisitorCount(DateUtils.getStartDate(new Date()), siteId);
		}
		List<Object[]>result=listOrder(li);
		Collections.sort(result, new ListComparator());
		model.addAttribute("flag", flag);
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("result",result );
		return "statistic/visitor";
	}
	
	@RequiresPermissions("flow:pages:v_list")
	@RequestMapping("/flow/pages/v_list.do")
	public String pages(Integer orderBy,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Pagination pagination=cmsAccessPagesMng.findPages(siteId, orderBy, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("pagination",pagination);
		return "statistic/pages";
	}
	
	@RequiresPermissions("flow:enterpage:v_list")
	@RequestMapping("/flow/enterpage/v_list.do")
	public String enterPages(Integer orderBy,Integer pageNo,HttpServletRequest request, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		Pagination pagination=cmsAccessMng.findEnterPages(siteId, orderBy, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("pagination",pagination);
		return "statistic/enterpages";
	}
	
	private List<Object[]>listOrder(List<Object[]>li){
		List<Object[]> result=new ArrayList<Object[]>();
		Long fiveAbove=0l,tenAbove=0l,twentyabove=0l,fifty=0l;
		for(Object[]o:li){
			Long visitor=(Long) o[0];
			Integer pageCount=(Integer) o[1];
			if(pageCount<5){
				result.add(o);
			}else if(pageCount>=5&&pageCount<=10){
				fiveAbove+=visitor;
			}else if(pageCount>10&&pageCount<=20){
				tenAbove+=visitor;
			}else if(pageCount>20&&pageCount<=50){
				twentyabove+=visitor;
			}else if(pageCount<50){
				fifty+=visitor;
			}
		}
		if(fiveAbove>0){
			Object[]o=new Object[2];
			o[0]=fiveAbove;
			o[1]="5-10";
			result.add(o);
		}
		if(tenAbove>0){
			Object[]o=new Object[2];
			o[0]=tenAbove;
			o[1]="11-20";
			result.add(o);
		}
		if(twentyabove>0){
			Object[]o=new Object[2];
			o[0]=twentyabove;
			o[1]="21-50";
			result.add(o);
		}
		if(fifty>0){
			Object[]o=new Object[2];
			o[0]=fifty;
			o[1]="50+";
			result.add(o);
		}
		return result;
	}
	
	private class MapComparator implements Comparator<Map.Entry<String, Object[]>> {
		private Integer target;
		public MapComparator(Integer target) {
			this.target = target;
		}
		public int compare(Map.Entry<String, Object[]> o1, Map.Entry<String, Object[]> o2) {
			Object[]o1Value=o1.getValue();
			Object[]o2Value=o2.getValue();
			if(o2Value!=null&&o1Value!=null){
				if(target==0){
					if(o2Value[0]!=null&&o1Value[0]!=null){
						Long a=(Long)o2Value[0]-(Long)o1Value[0];
						return a.intValue(); 
					}
				}else if(target==1){
					if(o2Value[1]!=null&&o1Value[1]!=null){
						Long a=(Long)o2Value[1]-(Long)o1Value[1];
						return a.intValue();  
					}
				}else if(target==2){
					if(o2Value[2]!=null&&o1Value[2]!=null){
						Long a=(Long)o2Value[2]-(Long)o1Value[2];
						return a.intValue(); 
					}
				}else{
					if(o2Value[3]!=null&&o1Value[3]!=null){
						Long a=(Long)o2Value[3]-(Long)o1Value[3];
						return a.intValue();
					}
				}
			}
			return 0;
		}  
	}
	
	private class ListComparator implements Comparator<Object[]> {
		public int compare(Object[] o1, Object[] o2) {
			Long a=(Long)o2[0]-(Long)o1[0];
			return a.intValue();  
		}  
	}
	
	private class ListChannelComparator implements Comparator<Channel> {
		private String comparaField;
		public ListChannelComparator(String comparaField) {
			super();
			this.comparaField = comparaField;
		}
		public int compare(Channel c1, Channel c2) {
			Integer a=0;
			if(comparaField.equals("view")){
				a=c2.getViewTotal()-c1.getViewTotal();
			}else if(comparaField.equals("viewDay")){
				a=c2.getViewsDayTotal()-c1.getViewsDayTotal();
			}else if(comparaField.equals("viewMonth")){
				a=c2.getViewsMonthTotal()-c1.getViewsMonthTotal();
			}else if(comparaField.equals("viewWeek")){
				a=c2.getViewsWeekTotal()-c1.getViewsWeekTotal();
			}
			return a;  
		}  
	}


	private CmsStatisticModel getStatisticModel(String queryModel) {
		if (!StringUtils.isBlank(queryModel)) {
			return CmsStatisticModel.valueOf(queryModel);
		}
		//return CmsStatisticModel.year;
		return CmsStatisticModel.lastYear;
	}

	private void putCommonData(CmsStatisticModel statisticModel,
			List<CmsStatistic> list, Integer year, Integer month, Integer day,
			ModelMap model) {
		model.addAttribute("list", list);
		model.addAttribute("total", getTotal(list));
		model.addAttribute("statisticModel", statisticModel.name());
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
	}

	private Long getTotal(List<CmsStatistic> list) {
		return list.size() > 0 ? list.iterator().next().getTotal() : 0L;
	}
	
	private  String getMessage(HttpServletRequest request, String key,
			Object... args) {
		return MessageResolver.getMessage(request, key, args);
	}

	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsStatisticSvc cmsStatisticSvc;
	@Autowired
	private CmsSiteAccessMng cmsAccessMng;
	@Autowired
	private CmsSiteAccessPagesMng cmsAccessPagesMng;
	@Autowired
	private CmsSiteAccessCountMng cmsAccessCountMng;
	@Autowired
	private CmsSiteAccessStatisticMng cmsAccessStatisticMng;
	@Autowired
	private CmsDepartmentMng cmsDepartmentMng;
	@Autowired
	private IRisenIntegralDao risenDao;
}
