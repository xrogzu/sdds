package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_INDEX;
import static com.jeecms.cms.statistic.CmsStatistic.SITEID;
import static com.jeecms.common.page.SimplePage.cpn;
import static com.jeecms.common.web.Constants.INDEX;
import static com.jeecms.common.web.Constants.INDEX_HTML;
import static com.jeecms.common.web.Constants.INDEX_HTML_MOBILE;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentCheck;
import com.jeecms.cms.manager.assist.CmsKeywordMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.ContentBuyMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.statistic.CmsStatistic;
import com.jeecms.cms.statistic.CmsStatisticSvc;
import com.jeecms.cms.statistic.CmsStatistic.CmsStatisticModel;
import com.jeecms.cms.statistic.CmsStatistic.TimeRange;
import com.jeecms.common.page.Paginable;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.common.web.springmvc.RealPathResolver;
import com.jeecms.core.comparator.DeptComparator;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsConfigMng;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.jeecms.core.web.util.URLHelper;
import com.jeecms.core.web.util.URLHelper.PageInfo;
import com.risen.dao.IRisenIntegralDao;
import com.risen.dao.IRisenIntegralRecordDao;
import com.risen.entity.RisenChangeremindrecord;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;
import com.risen.entity.RisenJxjfAssess;
import com.risen.entity.RisenOrgLifeCalendar;
import com.risen.entity.RisenQuota;
import com.risen.entity.RisenScore;
import com.risen.entity.RisenSignfor;
import com.risen.service.IRisenIntegralService;
import com.risen.service.IRisenOrgLifeCalendarService;
import com.risen.service.IRisenQuotaService;
import com.risen.service.IRisenScoreService;
import com.risen.service.IRisenSignforService;
import com.risen.service.RisenJxjfAssessMng;
import com.risen.service.impl.IRisenJxjfAssessMngImp;
import com.jeecms.cms.cache.DepartmentCache;
@Controller
public class DynamicPageAct {
	private static final Logger log = LoggerFactory
			.getLogger(DynamicPageAct.class);
	/**
	 * 首页模板名称
	 */
	public static final String TPL_INDEX = "tpl.index";
	public static final String GROUP_FORBIDDEN = "login.groupAccessForbidden";
	public static final String CONTENT_STATUS_FORBIDDEN ="content.notChecked";
	public static final String DIR = "print";
	public static final String PRINT = "tpl.print";
	
	/**
	 * 本日活动 查询所有活动
	 * @author slc 2016-11-19 下午2:36:30
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/**/getData.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object getData(String id){
		RisenOrgLifeCalendar bean=olcmanager.findById(Integer.valueOf(id));
		
		return bean;
	}
	/**
	 * 本日活动 查询所有活动
	 */
	@RequestMapping(value = "/print/getBaseOneData.jspx", method = RequestMethod.GET)
	public String getOneData(HttpServletRequest request,HttpServletResponse response, String id,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		try{
			RisenOrgLifeCalendar bean=olcmanager.findById(Integer.valueOf(id));
			if(StringUtils.isBlank(bean.getRisenlcComment())){
				model.addAttribute("Content","未填写");
			}else{
				String content = bean.getRisenlcComment().replace("<p>", "");
				String content1 = content.replace("</p>", "<br>");
				model.addAttribute("Content",content1);
			}
			
			model.addAttribute("RisenOrgLifeCalendar",bean);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, PRINT);
	}
	@RequestMapping(value = "/**/getEvent.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object getEvent(Integer id){
		List<String> dates=olcmanager.getEvent(id);
		return dates;
	}
	
	@RequestMapping("/changeStyle.jspx")
	@ResponseBody
	public Object changeStyle(String year,String month,Integer departId){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("mm");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat returnFormat = new SimpleDateFormat("yyyy/MM/dd"); 
		if(StringUtils.isBlank(year)){
			year = sdfYear.format(new Date());
		}
		if(StringUtils.isBlank(month)){
			month = sdfMonth.format(new Date());
		}
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		int lastDay = cal.get(Calendar.DAY_OF_MONTH);
		String startDate = year + "-" + month + "-01 0:00:00";
		String endDate = year + "-" + month + "-" + lastDay + " 23:59:59";
		//得到本月所有活动
		RisenOrgLifeCalendar rolcByMonth=new RisenOrgLifeCalendar();
		try {
			rolcByMonth.setStartDate(format.parse(startDate));
			rolcByMonth.setEndDate(format.parse(endDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		rolcByMonth.setRisenlcOrgid(departId);
		List<RisenOrgLifeCalendar> rolcByMonthList = olcmanager.ActivitiesToday(rolcByMonth);
		List<String> list2 = new ArrayList<String>();
		if(rolcByMonthList.size()>0){
			for (int i = 0; i < rolcByMonthList.size(); i++) {
				if(!StringUtils.isBlank(rolcByMonthList.get(i).getRisenlcMeetingtype())){
					String date = returnFormat.format(rolcByMonthList.get(i).getRisenlcOdate());
					String day = date.split("/")[2];
					String type = rolcByMonthList.get(i).getRisenlcMeetingtype();
					String calendar = day+","+type;
					list2.add(calendar);
				}
			}
		}
		return list2;
	}
	
	/**
	 * TOMCAT的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		
		FrontUtils.frontData(request, model, site);
		//判断是否是支部 --LQ
			Integer departId=null;
			if(request.getParameter("departId")!= null){
				departId=Integer.valueOf(request.getParameter("departId"));
			}
			//找到错误的Site
			Channel channel = channelMng.getChannelByDepts(departId);
			if(channel!=null){
				site = channel.getSite();
			}
			//得到本月所有活动
			RisenOrgLifeCalendar rolcByMonth=new RisenOrgLifeCalendar();
			//获取当月第一天
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
		    String first = sdf.format(new Date())+"-01 00:00:00";
			try {
				rolcByMonth.setStartDate(format.parse(first));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			rolcByMonth.setRisenlcOrgid(departId);
			List<RisenOrgLifeCalendar> rolcByMonthList = olcmanager.ActivitiesMonth(rolcByMonth);
			List<RisenOrgLifeCalendar> rolcByMonthList_hj = olcmanager.ChanMonth(rolcByMonth);
			if (rolcByMonthList_hj!=null && rolcByMonthList_hj.size()>0) {
				rolcByMonthList.addAll(rolcByMonthList_hj);
			}
			if (rolcByMonthList==null && rolcByMonthList.size()<0) {
				model.addAttribute("rolcByMonthList",null);
			}else{
				model.addAttribute("rolcByMonthList",rolcByMonthList);
			}
			
			//活动日历
			RisenOrgLifeCalendar baen=new RisenOrgLifeCalendar();
			baen.setStartDate(new Date());
			baen.setRisenlcOrgid(departId);
			List<RisenOrgLifeCalendar>list=olcmanager.ActivitiesMonth(baen);
			List<RisenOrgLifeCalendar>list2=olcmanager.ChanMonth(baen);
			if(list2!=null&&list2.size()>0){
				list.addAll(list2);
			}
			if(list.size()<1){
				model.addAttribute("month",null);
			}else{
				model.addAttribute("month",list);
			}
			
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDate = new Date();
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);		
			date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
			
			try {
				baen.setStartDate(dft.parse(dft.format(date.getTime())));
				String str=dft.format(date.getTime());
				dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				baen.setEndDate(dft.parse(str+" 23:59:59"));	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<RisenOrgLifeCalendar> dayList=olcmanager.ActivitiesToday(baen);
			
			if(dayList==null || dayList.size()<0){
				model.addAttribute("dayList","1");
			}else{
				model.addAttribute("dayList",dayList);
			}
	//	return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_INDEX, TPL_INDEX);
		//带有其他路径则是非法请求(非内网)
		String uri=URLHelper.getURI(request);
		if(StringUtils.isNotBlank(uri)&&(!(uri.equals("/")||uri.equals("/index.jhtml")))){
			CmsConfig config=configMng.get();
			if(!config.getInsideSite()){
				return FrontUtils.pageNotFound(request, response, model);
			}
		}
		//使用静态首页而且静态首页存在
		if(existIndexPage(site)){
			return goToIndexPage(request, response, site);
		}else{
			String tpl = site.getTplIndex();
			if (!StringUtils.isBlank(tpl)) {
				return tpl;
			} else {
				return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_INDEX, TPL_INDEX);
			}
		}
	}
	

	/**
	 * WEBLOGIC的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		return index(request, response,model);
	}

	/**
	 * 动态页入口
	 */
	@RequestMapping(value = "/**/*.*", method = RequestMethod.GET)
	public String dynamic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// 尽量不要携带太多参数，多使用标签获取数据。
		// 目前已知的需要携带翻页信息。
		// 获得页号和翻页信息吧。
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		int pageNo = URLHelper.getPageNo(request);
		String[] params = URLHelper.getParams(request);
		PageInfo info = URLHelper.getPageInfo(request);
		String[] paths = URLHelper.getPaths(request);
		CmsConfig config=configMng.get();
		
		Integer departId =null;
		Channel channel = channelMng.findByPathForTag(paths[1], site.getId());
		if(channel!=null){
			CmsDepartment cmsdepartment = channel.getDepart();
			if(cmsdepartment != null){
				departId = cmsdepartment.getId();
			}
		}

		//得到本月所有活动
		RisenOrgLifeCalendar rolcByMonth=new RisenOrgLifeCalendar();
		//获取当月第一天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
	    String first = sdf.format(new Date())+"-01 00:00:00";
		try {
			rolcByMonth.setStartDate(format.parse(first));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		rolcByMonth.setRisenlcOrgid(departId);
		List<RisenOrgLifeCalendar> rolcByMonthList = olcmanager.ActivitiesMonth(rolcByMonth);
		List<RisenOrgLifeCalendar> rolcByMonthList_hj = olcmanager.ChanMonth(rolcByMonth);
		if (rolcByMonthList_hj!=null && rolcByMonthList_hj.size()>0) {
			rolcByMonthList.addAll(rolcByMonthList_hj);
		}
		if (rolcByMonthList==null && rolcByMonthList.size()<0) {
			model.addAttribute("rolcByMonthList",null);
		}else{
			model.addAttribute("rolcByMonthList",rolcByMonthList);
		}
		
		//活动日历
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String string=dft.format(new Date());
		RisenOrgLifeCalendar baen=new RisenOrgLifeCalendar();
		try {
			baen.setStartDate(dft.parse(string+" 00:00:00"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		baen.setRisenlcOrgid(departId);
		
		List<RisenOrgLifeCalendar>list=olcmanager.ActivitiesMonth(baen);
		List<RisenOrgLifeCalendar>list2=olcmanager.ChanMonth(baen);
		if(list2!=null&&list2.size()>0){
			//list.addAll(list2);
		}
		if(list.size()<1){
			model.addAttribute("month",null);
		}else{
			model.addAttribute("month",list);
		}
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
		try {
			baen.setStartDate(dft.parse(string+" 00:00:00"));
			String str=dft.format(date.getTime());
			dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			baen.setEndDate(dft.parse(str+" 23:59:59"));	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RisenOrgLifeCalendar> dayList=olcmanager.ActivitiesToday(baen);
		//今日提醒的换届
		RisenOrgLifeCalendar  olc2=olcmanager.TodayRemindChan(baen);
		if(olc2!=null){
			dayList.add(olc2);
		}
		if(dayList==null || dayList.size()<0){
			model.addAttribute("dayList","1");
		}else{
			model.addAttribute("dayList",dayList);
		}
		
		if(config.getInsideSite()){
			return network(paths, params, info, pageNo, request, response, model);
		}else{
			return extranet(paths, params, info, pageNo, request, response, model);
		}
	}
	
	private String network(String[] paths,String[] params,PageInfo info,Integer pageNo,HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		int len = paths.length;
		if (len == 2) {
			//首页
			if(paths[1].equals(INDEX)){
				return index(request,response, model);
			}else{
				// 单页
				return channel(paths[1],true, pageNo, params, info, request, response,
						model);
			}
		} else if (len == 3) {
			if (paths[2].equals(INDEX)) {
				// 栏目页
				return channel(paths[1],false, pageNo, params, info, request,
						response, model);
			} else {
				// 内容页
				try {
					Integer id = Integer.parseInt(paths[2]);
					return content(id, pageNo, params, info, request, response,
							model);
				} catch (NumberFormatException e) {
					log.debug("Content id must String: {}", paths[1]);
					return FrontUtils.pageNotFound(request, response, model);
				}
			}
		} else {
			log.debug("Illegal path length: {}, paths: {}", len, paths);
			return FrontUtils.pageNotFound(request, response, model);
		}
	}
	
	private String extranet(String[] paths,String[] params,PageInfo info,Integer pageNo,HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		int len = paths.length;
		if (len == 1) {
			// 单页
			return channel(paths[0],true, pageNo, params, info, request, response,
					model);
		} else if (len == 2) {
			if (paths[1].equals(INDEX)) {
				// 栏目页
				return channel(paths[0],false, pageNo, params, info, request,
						response, model);
			} else {
				// 内容页
				try {
					Integer id = Integer.parseInt(paths[1]);
					return content(id, pageNo, params, info, request, response,
							model);
				} catch (NumberFormatException e) {
					log.debug("Content id must String: {}", paths[1]);
					return FrontUtils.pageNotFound(request, response, model);
				}
			}
		} else {
			log.debug("Illegal path length: {}, paths: {}", len, paths);
			return FrontUtils.pageNotFound(request, response, model);
		}
	}

	public String channel(String path,boolean checkAlone, int pageNo, String[] params,
			PageInfo info, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Channel channel = channelMng.findByPathForTag(path, site.getId());
		if (channel == null) {
			log.debug("Channel path not found: {}", path);
			return FrontUtils.pageNotFound(request, response, model);
		}
		//检查是否单页
		if(checkAlone){
			if(channel.getHasContent()){
				return FrontUtils.pageNotFound(request, response, model);
			}
		}
		model.addAttribute("channel", channel);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		String equipment=(String) request.getAttribute("ua");
		if(StringUtils.isNotBlank(equipment)&&equipment.equals("mobile")){
			return channel.getMobileTplChannelOrDef();
		}
		return channel.getTplChannelOrDef();
	}

	public String content(Integer id, int pageNo, String[] params,
			PageInfo info, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Content content = contentMng.findById(id);
		if (content == null) {
			log.debug("Content id not found: {}", id);
			return FrontUtils.pageNotFound(request, response, model);
		}
		Integer pageCount=content.getPageCount();
		if(pageNo>pageCount||pageNo<0){
			return FrontUtils.pageNotFound(request, response, model);
		}
		//非终审文章
		CmsConfig config=CmsUtils.getSite(request).getConfig();
		Boolean preview=config.getConfigAttr().getPreview();
		if(config.getViewOnlyChecked()&&!content.getStatus().equals(ContentCheck.CHECKED)){
			return FrontUtils.showMessage(request, model, CONTENT_STATUS_FORBIDDEN);
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsSite site = content.getSite();
		Set<CmsGroup> groups = content.getViewGroupsExt();
		int len = groups.size();
		// 需要浏览权限
		if (len != 0) {
			// 没有登录
			if (user == null) {
				session.setAttribute(request, response, "loginSource", "needPerm");
				return FrontUtils.showLogin(request, model, site);
			}
			// 已经登录但没有权限
			Integer gid = user.getGroup().getId();
			boolean right = false;
			for (CmsGroup group : groups) {
				if (group.getId().equals(gid)) {
					right = true;
					break;
				}
			}
			//无权限且不支持预览
			if (!right&&!preview) {
				String gname = user.getGroup().getName();
				return FrontUtils.showMessage(request, model, GROUP_FORBIDDEN,
						gname);
			}
			//无权限支持预览
			if(!right&&preview){
				model.addAttribute("preview", preview);
				model.addAttribute("groups", groups);
			}
		}
		//收费模式
		if(content.getCharge()){
			if(user==null){
				session.setAttribute(request, response, "loginSource", "charge");
				return FrontUtils.showLogin(request, model, site);
			}else{
				//非作者且未购买
				if(!content.getUser().equals(user)){
					//用户已登录判断是否已经购买
					boolean hasBuy=contentBuyMng.hasBuyContent(user.getId(), content.getId());
					if(!hasBuy){
						try {
							String rediretUrl="/content/buy.jspx?contentId="+content.getId();
							if(StringUtils.isNotBlank(site.getContextPath())){
								rediretUrl=site.getContextPath()+rediretUrl;
							}
							response.sendRedirect(rediretUrl);
						} catch (IOException e) {
							//e.printStackTrace();
						}
					}
				}
			}
		}
		String txt = content.getTxtByNo(pageNo);
		// 内容加上关键字
		txt = cmsKeywordMng.attachKeyword(site.getId(), txt);
		Paginable pagination = new SimplePage(pageNo, 1, content.getPageCount());
		model.addAttribute("pagination", pagination);
		FrontUtils.frontPageData(request, model);
		model.addAttribute("content", content);
		model.addAttribute("channel", content.getChannel());
		model.addAttribute("title", content.getTitleByNo(pageNo));
		model.addAttribute("txt", txt);
		model.addAttribute("pic", content.getPictureByNo(pageNo));
		FrontUtils.frontData(request, model, site);
		String equipment=(String) request.getAttribute("ua");
		if(StringUtils.isNotBlank(equipment)&&equipment.equals("mobile")){
			return content.getMobileTplContentOrDef(content.getModel());
		}
		return content.getTplContentOrDef(content.getModel());
	}
	

	private boolean existIndexPage(CmsSite site){
		boolean exist=false;
		if(site.getStaticIndex()){
			File indexPage;
			if(site.getIndexToRoot()){
				indexPage=new File(realPathResolver.get(INDEX_HTML));
			}else{
				indexPage=new File(realPathResolver.get(site.getStaticDir()+INDEX_HTML));
			}
			if(indexPage.exists()){
				exist=true; 
			}
		}
		return exist;
	}
	
	private String goToIndexPage(HttpServletRequest request,HttpServletResponse response,CmsSite site){
		String equipment=(String) request.getAttribute("ua");
		try {
			String ctx="";
			if(StringUtils.isNotBlank(site.getContextPath())){
				ctx=site.getContextPath();
			}
			if(site.getIndexToRoot()){
				
				if(StringUtils.isNotBlank(equipment)&&equipment.equals("mobile")){
					response.sendRedirect(ctx+INDEX_HTML_MOBILE);
				}else{
					response.sendRedirect(ctx+INDEX_HTML);
				}
			}else{
				response.sendRedirect(ctx+site.getStaticDir()+INDEX_HTML);
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_INDEX, TPL_INDEX); 
	}
	/**
	 * 本日活动 查询所有活动
	 * @author slc 2016-11-19 下午2:36:30
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/**/getAll.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object ActivitiesToday(String date,Integer id){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RisenOrgLifeCalendar bean=new RisenOrgLifeCalendar();
		bean.setRisenlcOrgid(id);
		try {
			bean.setStartDate(format.parse(date+" 00:00:00"));
			bean.setEndDate(format.parse(date+" 23:59:59"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RisenOrgLifeCalendar>  list=olcmanager.ActivitiesToday(bean);
		
		//判断是否今日有换届
		List<RisenOrgLifeCalendar>  list1=olcmanager.ChanToday(bean);
		if(list1!=null&&list1.size()>0){
			list.addAll(list1);
		}
		return list;
	}
	
	
	/**
	 * 站群导航
	 */
	@RequestMapping(value = "/getSites.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object sitesNav(Integer pid){
		List<CmsDepartment> list = departmentMng.getList(pid, false);
		CmsDepartment pcms = departmentMng.findById(pid);
		List<CmsDepartment> list2 = new ArrayList<CmsDepartment>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CmsDepartment model = new CmsDepartment();
				String departname = list.get(i).getName().trim();
				if(departname.contains("（")){
					String [] departnames = departname.split("（");
					departname = departnames[0];
				}
				String path = channelMng.getChannelPathByDid(list.get(i).getId());
				model.setName(departname);
				model.setId(list.get(i).getId());
				if(list.get(i).getSddspoOrgtype().equals("支部")){
					model.setChannelPath(path);
				}else{
					if(pcms.getSddspoOrglevel()!=1){
						path = channelMng.getChannelPathByDid(list.get(i).getParent().getId());
						model.setChannelPath(path);
					}
				}
				list2.add(model);
			}
		}
		return list2;
	}
	/**
	 * 获取栏目路径
	 */
	@RequestMapping("/getChannelPaths.jspx")
	@ResponseBody
	public Object getChannelPaths(Integer departId){
		String deptName = cmsDepartmentMng.findById(departId).getName();
		if(deptName.indexOf("市局机关党委")>-1){
			String dsname = "jnds";
			if(departId==2143){
	        	dsname = "hzds";
	        }else if(departId==1353){
	        	dsname = "lcds";
	        }else if(departId==1042){
	        	dsname = "jiningds";
	        }else if(departId==1845){
	        	dsname = "zzsd";
	        }else if(departId==1776){
	        	dsname = "lyds";
	        }else if(departId==1406){
	        	dsname = "tads";
	        }else if(departId==2759){
	        	dsname = "rzds";
	        }else if(departId==567){
	        	dsname = "qdds";
	        }else if(departId==1601){
	        	dsname = "wfds";
	        }else if(departId==1286){
	        	dsname = "lwds";
	        }else if(departId==2424){
	        	dsname = "zbds";
	        }else if(departId==68){
	        	dsname = "jnds";
	        }else if(departId==1467){
	        	dsname = "dzds";
	        }else if(departId==1022){
	        	dsname = "bzds";
	        }else if(departId==1821){
	        	dsname = "dyds";
	        }else if(departId==2201){
	        	dsname = "ytds";
	        }else if(departId==1081){
	        	dsname = "whds";
	        }else{
	        }
			return dsname;
		}else{
			return channelMng.getChannelPathByDid(departId);
		}
	}
	
	/**
	 * 获取各机关党委
	 */
	public Object getJgdw(Integer departid){
		return null;
	}
	/**
	 * 先锋排序
	 */
	@RequestMapping("/findXfpx.jspx")
	@ResponseBody
	public Object findXfpx(Integer departid){
		/**/
		List<RisenScore> list = (List<RisenScore>) risenScoreService.getTotalScore(departid);
		List<String> list2 = new ArrayList<String>();
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getTotalScore()!=null){
					String str = "";
					String departName = cmsDepartmentMng.findById(list.get(i).getDepartId()).getName();
					str += list.get(i).getUser().getUsername()+","
					+departName+","
					+list.get(i).getTotalScore();
					list2.add(str);
				}
			}
		}
		return list2;
		/*List<CmsUser> list = cmsUserMng.scoreUserList(departid);
		List<CmsUser> list2 = new ArrayList<CmsUser>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getSddscpXfscore()!=null && !("".equals(list.get(i).getSddscpXfscore()))){
				list2.add(list.get(i));
			}
		}
		return list2;*/
	}
	/**
	 * 组织人员分析  From LQ
	 * @param bean
	 * @param request
	 * @return
	 */
	@RequestMapping("/getdybhqk.jspx")
	public String getdybh(String queryModel,Integer deptId, Integer year, Integer month,
			Integer day,Date begin,Date end,HttpServletRequest request, ModelMap model){
		CmsStatisticModel statisticModel = getStatisticModel(queryModel);
		Map<String, Object> restrictions = new HashMap<String, Object>();
		Integer returnDeptId = deptId.intValue();
		CmsSite site = CmsUtils.getSite(request);
		CmsDepartment dept = new CmsDepartment();
		//找到错误的Site
		if (deptId!=null && deptId>0) {
			if(deptId!=null){
				Channel channel = channelMng.getChannelByDepts(deptId);
				if(channel!=null){
					site = channel.getSite();
				}
			}	
			restrictions.put(SITEID, site.getId());
			dept = cmsDepartmentMng.findById(deptId);
			if(deptId.equals(1)){
				deptId = null;
			}else if("机关党委,党总支".indexOf(dept.getSddspoOrgtype())>-1){
				deptId = dept.getParent().getId();
			}
			restrictions.put("deptId",deptId);
		    if (dept!=null) {
		    	restrictions.put("deptType", dept.getSddspoOrglevel());
			}
		}
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
		//List<CmsStatistic> list = cmsStatisticSvc.statisticByModel(MEMBER, statisticModel, year, month, day,begin,end, null);
		//putCommonData(statisticModel, list, year, month, day, model);
		
		long lastYearCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.lastYear.toString(), yearTimeRange, restrictions,dept);
		long thisYearAddCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.thisYearAdd.toString(), yearTimeRange, restrictions,dept);
		long thisYearDecreaseCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.thisYearDecrease.toString(), yearTimeRange, restrictions,dept);
		long endOfTheYearCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.endOfTheYear.toString(), yearTimeRange.getInstance(yearNext, now), restrictions,dept)+thisYearDecreaseCount;
		//long atTheEndOfTheYearCount = cmsStatisticSvc.statistic(CmsStatistic.MEMBER, CmsStatisticModel.atTheEndOfTheYear.toString(), yearTimeRange.getInstance(yearNext, now), restrictions,dept);
		long atTheEndOfTheYearCount = endOfTheYearCount - thisYearDecreaseCount;
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
		model.addAttribute("lastYearCount", lastYearCount);
		model.addAttribute("thisYearAddCount", thisYearAddCount);
		model.addAttribute("thisYearDecreaseCount", thisYearDecreaseCount);
		model.addAttribute("endOfTheYearCount", endOfTheYearCount);
		model.addAttribute("atTheEndOfTheYearCount", atTheEndOfTheYearCount);
		model.addAttribute("differCount",endOfTheYearCount-atTheEndOfTheYearCount);
		
		model.addAttribute("statisticModel", statisticModel.name());
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("deptid",returnDeptId);
		if (dept!=null && dept.getSddspoOrglevel()!=null) {
			model.addAttribute("deptType",dept.getSddspoOrglevel());
		}else{
			model.addAttribute("deptType",0);
		}
		if((deptId==null) || (deptId==1)){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dybh.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dybh.html";
		}
	}
	@RequestMapping("/getdyjbqk.jspx")
	public String getdyjbqk(String queryModel,Integer deptId, HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		Integer returnDeptId = deptId.intValue();
		if (deptId!=null && deptId>0) {
			if (deptId.equals(1)) {
				deptId = null;
			}else{
				CmsDepartment dept = new CmsDepartment();
				dept = cmsDepartmentMng.findById(deptId);
				if ("机关党委,党总支".indexOf(dept.getSddspoOrgtype())>-1) {
					deptId = dept.getParent().getId();
				}
			}
		}
		
		List<Object[]> list = new ArrayList<Object[]>();
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
			list = cmsStatisticSvc.basicInfoList(queryModel, deptId, "6");
		}else{
			list = cmsStatisticSvc.basicInfoList(queryModel, deptId, "6");
		}
		model.addAttribute("statisticModel", queryModel);
		model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.basicInfoList(null, deptId, "6");
		model.addAttribute("listAll", listAll);
		
		//List<Object[]> dzjgCount = cmsStatisticSvc.basicInfoListCount("DZJG",deptId, "6");
		//List<Object[]> sydwCount = cmsStatisticSvc.basicInfoListCount("SYDW",deptId, "6");
		//List<Object[]> gqCount = cmsStatisticSvc.basicInfoListCount("GQ",deptId, "6");
		//List<Object[]> ltxCount = cmsStatisticSvc.basicInfoListCount("LTX",deptId, "6");
		
		//model.addAttribute("dzjgCount",dzjgCount);
		//model.addAttribute("sydwCount",sydwCount);
		//model.addAttribute("gqCount",gqCount);
		//model.addAttribute("ltxCount",ltxCount);
		model.addAttribute("deptid",returnDeptId);
		if(deptId==null){
			deptId=1;
		}
		if(deptId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dyjb.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dyjb.html";
		}
	}
	
	@RequestMapping("/getdyrdsj.jspx")
	public String getdyrdsj(String queryModel,Integer deptId, HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		Integer returnDeptId = deptId.intValue();
		if (deptId!=null && deptId>0) {
			if (deptId.equals(1)) {
				deptId = null;
			}else{
				CmsDepartment dept = new CmsDepartment();
				dept = cmsDepartmentMng.findById(deptId);
				if ("机关党委,党总支".indexOf(dept.getSddspoOrgtype())>-1) {
					deptId = dept.getParent().getId();
				}
			}
		}
		List<Object[]> list = new ArrayList<Object[]>();
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
			list = cmsStatisticSvc.partyTimeList(queryModel, deptId, null);
		}else{
			list = cmsStatisticSvc.partyTimeList(queryModel, deptId, null);
		}
		model.addAttribute("statisticModel", queryModel);
		model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.partyTimeList(null, deptId, "6");
		model.addAttribute("listAll", listAll);
				
		
		/*List<Object[]> dzjgCount = cmsStatisticSvc.partyTimeListCount("DZJG",deptId, null);
		List<Object[]> sydwCount = cmsStatisticSvc.partyTimeListCount("SYDW",deptId, null);
		List<Object[]> gqCount = cmsStatisticSvc.partyTimeListCount("GQ",deptId, null);
		List<Object[]> ltxCount = cmsStatisticSvc.partyTimeListCount("LTX",deptId, null);
		
		model.addAttribute("dzjgCount",dzjgCount);
		model.addAttribute("sydwCount",sydwCount);
		model.addAttribute("gqCount",gqCount);
		model.addAttribute("ltxCount",ltxCount);
		model.addAttribute("deptid",deptId);*/
		model.addAttribute("deptid",returnDeptId);
		if(deptId==null){
			deptId=1;
		}
		if(deptId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dyrd.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dyrd.html";
		}
		
	}
	

	@RequestMapping("/getdzzbhqk.jspx")
	public String getdzzbhqk(String queryModel,Integer deptId, HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		Integer returnDeptId = deptId.intValue();
		CmsUser loginUser = CmsUtils.getUser(request);
		if (loginUser!=null) {
			deptId = loginUser.getDepartment().getId();
			returnDeptId = deptId.intValue();
		}
		Map<String, String> tjMap = new HashMap<String, String>();
		CmsDepartment dept = departmentMng.findById(deptId);
		if(deptId == 1){ 
			tjMap = departmentMng.statisticsDeptSum(1,null);
		}else if(dept.getParent() == null){
			tjMap = departmentMng.statisticsDeptSum(2,dept.getId());
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap = departmentMng.statisticsDeptSum(3,dept.getParent().getId());
		}else{
			tjMap = departmentMng.statisticsDeptSum(4,dept.getId());
		}
		model.addAttribute("deptname", dept.getName());
		model.addAttribute("tjMap", tjMap);
		model.addAttribute("deptid",returnDeptId);
		if(loginUser!=null){
			if (loginUser.getDepartment()!= null) {
				if (loginUser.getDepartment().getSddspoOrglevel().equals(1) || loginUser.getDepartment().getSddspoOrglevel().equals(2)) {
					return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/channel/dybh.html";
				}
			}
		}
		if(deptId==null){
			deptId=1;
		}
		if(deptId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dzzbhqk.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dzzbhqk.html";
		}
		
	}
	
	@RequestMapping("/getdzzhjqk.jspx")
	public String getdzzhjqk(String queryModel, Integer deptId, Date staDate,
			Date endDate, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		Integer returnDeptId = deptId.intValue();
		CmsUser loginUser = CmsUtils.getUser(request);
		if (loginUser!=null) {
			deptId = loginUser.getDepartment().getId();
			returnDeptId = deptId.intValue();
		}
		Map<String, String> tjMap = new HashMap<String, String>();
		CmsDepartment dept = departmentMng.findById(deptId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		/*
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
		*/
		String sdate = sdf.format(new Date());
		CmsUser user = CmsUtils.getUser(request);
		Map<String, String> yqhjMap = new HashMap<String, String>();
		Map<String, String> aqhjMap = new HashMap<String, String>();
		/*
		Map<String, String> rjqmMap = new HashMap<String, String>();
		Map<String, String> yhjMap = new HashMap<String, String>();
		if(dept.getId() == 1){ 
			tjMap1 = departmentMng.statisticsDeptSum(1,null);
			rjqmMap = departmentMng.statisticsDeptQmhjSum(1, null, sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = departmentMng.statisticsDeptYhjSum(1, null, sdate, edate);
		}else if(dept.getParent() == null){
			tjMap1 = departmentMng.statisticsDeptSum(2,dept.getId());
			rjqmMap = departmentMng.statisticsDeptQmhjSum(2, dept.getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = departmentMng.statisticsDeptYhjSum(2, dept.getId(), sdate, edate);
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap1 = departmentMng.statisticsDeptSum(3,dept.getParent().getId());
			rjqmMap = departmentMng.statisticsDeptQmhjSum(3, dept.getParent().getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = departmentMng.statisticsDeptYhjSum(3, dept.getParent().getId(), sdate, edate);
		}else{
			tjMap1 = departmentMng.statisticsDeptSum(4,dept.getId());
			rjqmMap = departmentMng.statisticsDeptQmhjSum(4, dept.getId(), sdate, edate);
			edate = sdf.format(dqDate);
			yhjMap = departmentMng.statisticsDeptYhjSum(4, dept.getId(), sdate, edate);
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
		tjMap1.putAll(rjqmMap);
		tjMap1.putAll(yhjMap);
		*/
		if(dept.getId() == 1){ 
			tjMap = departmentMng.statisticsDeptSum(1,null);
			yqhjMap = departmentMng.statisticsDeptYqhjSum(1, null, sdate);
			aqhjMap = departmentMng.statisticsDeptAqhjSum(1, null, sdate);
		}else if(dept.getParent() == null){
			tjMap = departmentMng.statisticsDeptSum(2,dept.getId());
			yqhjMap = departmentMng.statisticsDeptYqhjSum(2, dept.getId(), sdate);
			aqhjMap = departmentMng.statisticsDeptAqhjSum(2, dept.getId(), sdate);
		}else if(dept.getParent().getParent() == null && dept.getPriority() == 1){
			tjMap = departmentMng.statisticsDeptSum(3,dept.getParent().getId());
			yqhjMap = departmentMng.statisticsDeptYqhjSum(3, dept.getParent().getId(), sdate);
			aqhjMap = departmentMng.statisticsDeptAqhjSum(3, dept.getParent().getId(), sdate);
		}else{
			tjMap = departmentMng.statisticsDeptSum(4,dept.getId());
			yqhjMap = departmentMng.statisticsDeptYqhjSum(4, dept.getId(), sdate);
			aqhjMap = departmentMng.statisticsDeptAqhjSum(4, dept.getId(), sdate);
		}
		tjMap.putAll(yqhjMap);
		tjMap.putAll(aqhjMap);
		model.addAttribute("deptname", dept.getName());
		model.addAttribute("tjMap", tjMap);
		model.addAttribute("deptid",returnDeptId);
		if(loginUser!=null){
			if (loginUser.getDepartment()!= null) {
				if (loginUser.getDepartment().getSddspoOrglevel().equals(1) || loginUser.getDepartment().getSddspoOrglevel().equals(2)) {
					return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/channel/dybh.html";
				}
			}
		}
		if(deptId==null){
			deptId=1;
		}
		if(deptId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dzzhjqk.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dzzhjqk.html";
		}
	}
	
	@RequestMapping("/getdyxlqk.jspx")
	public String getxlqk(String queryModel,Integer deptId, HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		Integer returnDeptId = deptId.intValue();
		if (deptId != null && deptId > 0) {
			if (deptId.equals(1)) {
				deptId = null;
			}else{
				CmsDepartment dept = new CmsDepartment();
				dept = cmsDepartmentMng.findById(deptId);
				if ("机关党委,党总支".indexOf(dept.getSddspoOrgtype())>-1) {
					deptId = dept.getParent().getId();
				}
			}
		}
		List<Object[]> list = new ArrayList<Object[]>();
		if(StringUtils.isBlank(queryModel)){
			queryModel = "ALL";
			list = cmsStatisticSvc.educationAnalysisList(queryModel, deptId, null);
		}else{
			list = cmsStatisticSvc.educationAnalysisList(queryModel, deptId, null);
		}
		model.addAttribute("statisticModel", queryModel);
		model.addAttribute("list",list);
		
		//该机构下总数
		List<Object[]> listAll = cmsStatisticSvc.educationAnalysisList(null, deptId, null);
		model.addAttribute("listAll", listAll);
		
		/*List<Object[]> dzjgCount = cmsStatisticSvc.educationAnalysisListCount("DZJG",deptId, null);
		List<Object[]> sydwCount = cmsStatisticSvc.educationAnalysisListCount("SYDW",deptId, null);
		List<Object[]> gqCount = cmsStatisticSvc.educationAnalysisListCount("GQ",deptId, null);
		List<Object[]> ltxCount = cmsStatisticSvc.educationAnalysisListCount("LTX",deptId, null);
		
		model.addAttribute("dzjgCount",dzjgCount);
		model.addAttribute("sydwCount",sydwCount);
		model.addAttribute("gqCount",gqCount);
		model.addAttribute("ltxCount",ltxCount);*/
		model.addAttribute("deptid",returnDeptId);
		if(deptId==null){
			deptId=1;
		}
		if((deptId==null) || (deptId==1)){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/dyxl.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/dyxl.html";
		}
		
	}
	
	
	private CmsStatisticModel getStatisticModel(String queryModel) {
		if (!StringUtils.isBlank(queryModel)) {
			return CmsStatisticModel.valueOf(queryModel);
		}
		return CmsStatisticModel.lastYear;
	}
	
	@RequestMapping("/checkIsSign.jspx")
	@ResponseBody
	public void checkIsSign(String contentid, HttpServletRequest request,HttpServletResponse response,
			ModelMap model){
		//获取本机的ip地址
		String remoteAddr = request.getRemoteAddr();  
		String forwarded = request.getHeader("X-Forwarded-For");  
		String realIp = request.getHeader("X-Real-IP");  
		String addip = null;  
		if (realIp == null) {  
		     if (forwarded == null) {  
		    	 addip = remoteAddr;  
		     } else {  
		    	 addip = remoteAddr + "/" + forwarded.split(",")[0];  
		     }  
		 } else {  
		     if (realIp.equals(forwarded)) {  
		    	 addip = realIp;  
		     } else {  
		         if(forwarded != null){  
		             forwarded = forwarded.split(",")[0];  
		        }  
		         addip = realIp + "/" + forwarded;  
		     }  
		}
		/*
		 //获取服务器的ip地址
		InetAddress address = null;
		try {
			 address = InetAddress.getLocalHost();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		String addip = address==null?null:address.getHostAddress();
		*/
		String tips = "";
		Boolean boo = false;
		if(org.springframework.util.StringUtils.hasText(addip)){
			List<CmsDepartment> list = cmsDepartmentDao.findListByIp(addip);
			if(list!=null && list.size()==1){
				Content content = contentDao.findById(new Integer(contentid));
				Set<CmsDepartment> depts = content.getChannel().getDepartments();
				Integer sonDeptId = list.get(0).getId();
				if(depts!=null && depts.size()==1){
					Iterator<CmsDepartment> it=depts.iterator();
					Integer deptid = it.next().getId();
					//省局的文章
					if(deptid==1){
						if(list.get(0).getName().endsWith("市局机关党委")){
							boo = risenSignforService.checkIsSign(contentid,addip);
							if(boo){
								tips="true";
							}else{
								tips= "false";
							}
							/*
							if(content.getPassDept()==null || (content.getPassDept()=="")){
								boo = risenSignforService.checkIsSign(contentid,addip);
								if(boo){
									tips="true";
								}else{
									tips= "false";
								}
							}else{
								if(content.getPassDept().indexOf(String.valueOf(sonDeptId))>-1){
									boo = risenSignforService.checkIsSign(contentid,addip);
									if(boo){
										tips="true";
									}else{
										tips= "false";
									}
								}else{
									tips="你没有权限";
								}
							}
							*/
						}else if((list.get(0).getParent()!=null) && (list.get(0).getParent().getId()==deptid)){
							boo = risenSignforService.checkIsSign(contentid,addip);
							if(boo){
								tips="true";
							}else{
								tips= "false";
							}
						}else if((list.get(0).getName().equals("省局机关党委")) || (list.get(0).getName().equals("admin"))){
							tips = "本级不需要签收";
						}else{
							tips="你没有权限";
						}
					}else{
						//其他区县级或市级的文章
						CmsDepartment depart = cmsDepartmentDao.findById(deptid);
						if(deptid==sonDeptId){
							tips = "本级不需要签收";
						}else{
							//市局下的支部
							if(list.get(0).getParent().getId()==deptid){
								boo = risenSignforService.checkIsSign(contentid,addip);
								if(boo){
									tips="true";
								}else{
									tips= "false";
								}
							}else{
								if(list.get(0).getSddspoOrgtype().equals("支部")){
									//区县级的支部
									if(list.get(0).getParent()==depart.getParent()){
										boo = risenSignforService.checkIsSign(contentid,addip);
										if(boo){
											tips = "true";
										}else{
											tips = "false";
										}
									}else{
										tips="你没有权限";
									}
								}else{
									//判断是否是市局的组织
									if(list.get(0).getParent().getParent()!=null){
										//判断是不是同级的其他区县局
										if(list.get(0).getParent().getParent()==depart.getParent()){
											boo = risenSignforService.checkIsSign(contentid,addip);
											if(boo){
												tips="true";
											}else{
												tips= "false";
											}
										}else{
											tips="你没有权限";
										}
									}else{
										tips="不能跨区签收";
									}
								}
							}
						}
					}
				}else{
					tips = "栏目未绑定";
				}
			}else{
				tips = "组织存在多个ip";
			}
		}else{
			tips = "暂未获取到ip地址";
		}
		try{
			if(tips.equals("true")){
				response.getWriter().write("exists");
			}else if(tips.equals("false")){
				response.getWriter().write("noexists");
			}else if(tips.equals("暂未获取到ip地址")){
				response.getWriter().write("noIpAddress");
			}else if(tips.equals("组织存在多个ip")){
				response.getWriter().write("OverOneIpAddress");
			}else if(tips.equals("栏目未绑定")){
				response.getWriter().write("NoBind");
			}else if(tips.equals("你没有权限")){
				response.getWriter().write("NoPower");
			}else if(tips.equals("本级不需要签收")){
				response.getWriter().write("SameLevel");
			}else if(tips.equals("不能跨区签收")){
				response.getWriter().write("CrossArea");
			}else{
				response.getWriter().write("Wait");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/getDetail.jspx")
	public String getDetailScore(Integer departId,Integer root,HttpServletRequest request,
			ModelMap model,String endDate,String startDate){
		CmsSite site = CmsUtils.getSite(request);
		//找到错误的Site
		Channel channel = channelMng.getChannelByDepts(departId);
		if(channel!=null){
			site = channel.getSite();
		}
		List<CmsDepartment> deptss = new ArrayList<CmsDepartment>();
		List<CmsDepartment> depts = new ArrayList<CmsDepartment>();
		CmsDepartment dept = null;
		Integer firstChild = null;
		if (departId!=null && departId>0) {
			for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
				if(departId.intValue()==DepartmentCache.departentList.get(i).getId().intValue()){
					dept=DepartmentCache.departentList.get(i);
				}
			}
			//dept = cmsDepartmentMng.findById(departId);
			if(departId.equals(1) || dept.getParent()==null){
				if (departId.equals(1)) {
					firstChild = departId;
				}else{
					firstChild = cmsDepartmentDao.findChildrenFirstDeptidByPid(departId);
				}
			}
			
			if(root==null || "".equals(root)){
				if (departId.equals(1)) {
					for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
						if(DepartmentCache.departentList.get(i).getParent()==null){
							deptss.add(DepartmentCache.departentList.get(i));
						}
					}
					for (int i = 0; i < deptss.size(); i++) {
						for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
							if(deptss.get(i)==DepartmentCache.departentList.get(j).getParent()){
								if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") && DepartmentCache.departentList.get(j).getPriority().intValue()==1){
									depts.add(DepartmentCache.departentList.get(j));
								}
							}
						}
					}
				}else{
					for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
						if(DepartmentCache.departentList.get(i).getParent() != null && DepartmentCache.departentList.get(i).getParent().getId().intValue()==departId.intValue()){
							deptss.add(DepartmentCache.departentList.get(i));
						}
					}
					for (int i = 0; i < deptss.size(); i++) {
						for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
							if(deptss.get(i)==DepartmentCache.departentList.get(j).getParent()){
								if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") || DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("党总支")){
									depts.add(DepartmentCache.departentList.get(j));
								}
							}
						}
					}
				}
			}else{
				firstChild=departId;
				depts = cmsDepartmentDao.getAllZhiBuById(departId);
				departId=dept.getParent()!=null?dept.getParent().getId():dept.getId();
			}
		}
		// 组装strIds
		String strIds = "(";
		if (depts.size() > 0) {
			for (int i = 0; i < depts.size(); i++) {
				depts.get(i).setScore(0.0);
				if (i == depts.size() - 1) {
					strIds += ("'" + depts.get(i).getId() + "'");
				} else {
					strIds += ("'" + depts.get(i).getId() + "',");
				}
			}
		} else {
			strIds += ("'" + departId + "'");
		}
		strIds = strIds + ")";
		List<Object[]> scoreList = risenDao.getListChannelScore(strIds, startDate, endDate);
		List<Object[]> monthAndTotalScoreList = risenDao.getListChannelMonthAndTotalScore(strIds, startDate, endDate);
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < scoreList.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
					//设置各个栏目的分数
					depts.get(i).setTpxw(Double.parseDouble(scoreList.get(j)[2].toString()));
					depts.get(i).setDjdt(Double.parseDouble(scoreList.get(j)[1].toString()));
					depts.get(i).setJyjl(Double.parseDouble(scoreList.get(j)[3].toString()));
					depts.get(i).setMtxc(Double.parseDouble(scoreList.get(j)[4].toString()));
					depts.get(i).setLlyt(Double.parseDouble(scoreList.get(j)[5].toString()));
					depts.get(i).setWsrys(Double.parseDouble(scoreList.get(j)[6].toString()));
				}
			}
		}
		//设置月总分和总分
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < monthAndTotalScoreList.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((monthAndTotalScoreList.get(j)[0]).toString())){
					//设置各个栏目的分数
					depts.get(i).setScore(Double.parseDouble(monthAndTotalScoreList.get(j)[2].toString()));
					depts.get(i).setPid(Double.parseDouble(monthAndTotalScoreList.get(j)[1].toString()));
				}
			}
		}
		
		//设置List排序
		Collections.sort(depts, new DeptComparator());
		
		for(int i = 0; i <depts.size(); i++) {
			depts.get(i).setGroupid(i+1);
		}
		model.addAttribute("parentId", root);
		//list2方便页面echarts显示图start
		String xs="[";
		String datas="[";
		for (int i = 0; i < depts.size(); i++) {
			if(i==depts.size()-1){
				xs+=("'"+depts.get(i).getName()+"'");
				datas+=(depts.get(i).getScore());
			}else{
				xs+=("'"+depts.get(i).getName()+"',");
				datas+=(depts.get(i).getScore()+",");
			}	
		}
		xs=xs+"]";
		datas=datas+"]"; 
		
		String type = "支部";
		if(!StringUtils.isBlank(dept.getName()) && "省局机关党委".indexOf(dept.getName())>-1){
			type="省局";
		}else if(!StringUtils.isBlank(dept.getName()) && dept.getName().indexOf("市局党组党建工作指导组")>-1){
			type="市局";
		}else if(!StringUtils.isBlank(dept.getName()) && dept.getName().indexOf("市局机关党委")>-1){
			type="市局";
		}
		List<RisenJxjfAssess> jxpg= jxjfpg.findbydepartpg(departId);
		if(jxpg!=null && jxpg.size()>0){
			for (int i = 0; i< depts.size(); i++) {
				String pg="";
				for (int j = 0; j < jxpg.size(); j++) {
					if(!StringUtils.isBlank(jxpg.get(j).getChannelname())){
						if(jxpg.get(j).getChannelname().equals("tpxw")){
							if(depts.get(i).getTpxw()==null){
								depts.get(i).setTpxw(0.0);
							}
							if(depts.get(i).getTpxw()>=jxpg.get(j).getExcellentscore()){
								pg+=("图片新闻栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getTpxw()>jxpg.get(j).getPassingscore()){
								pg+=("图片新闻栏目:"+jxpg.get(j).getPassingevaluate()+"<br/>");
							}else{
								pg+=("图片新闻栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
						if(jxpg.get(j).getChannelname().equals("djdt")){
							if(depts.get(i).getDjdt()==null){
								depts.get(i).setDjdt(0.0);
							}
							if(depts.get(i).getDjdt()>=jxpg.get(j).getExcellentscore()){
								pg+=("党建动态栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getDjdt()>jxpg.get(j).getPassingscore()){
								pg+=("党建动态栏目:"+jxpg.get(j).getPassingevaluate()+"<br/>");
							}else{
								pg+=("党建动态栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
						if(jxpg.get(j).getChannelname().equals("jyjl")){
							if(depts.get(i).getJyjl()==null){
								depts.get(i).setJyjl(0.0);
							}
							if(depts.get(i).getJyjl()>=jxpg.get(j).getExcellentscore()){
								pg+=("经验交流栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getJyjl()>jxpg.get(j).getPassingscore()){
								pg+=("经验交流栏目:"+jxpg.get(j).getPassingscore()+"<br/>");
							}else{
								pg+=("经验交流栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
						if(jxpg.get(j).getChannelname().equals("mtsd")){
							if(depts.get(i).getMtxc()==null){
								depts.get(i).setMtxc(0.0);
							}
							if(depts.get(i).getMtxc()>=jxpg.get(j).getExcellentscore()){
								pg+=("媒体视点栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getMtxc()>jxpg.get(j).getPassingscore()){
								pg+=("媒体视点栏目:"+jxpg.get(j).getPassingevaluate()+"<br/>");
							}else{
								pg+=("媒体视点栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
						if(jxpg.get(j).getChannelname().equals("llyt")){
							if(depts.get(i).getLlyt()==null){
								depts.get(i).setLlyt(0.0);
							}
							if(depts.get(i).getLlyt()>=jxpg.get(j).getExcellentscore()){
								pg+=("理论研讨栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getLlyt()>jxpg.get(j).getPassingscore()){
								pg+=("理论研讨栏目:"+jxpg.get(j).getPassingevaluate()+"<br/>");
							}else{
								pg+=("理论研讨栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
						if(jxpg.get(j).getChannelname().equals("wsrys")){
							if(depts.get(i).getWsrys()==null){
								depts.get(i).setWsrys(0.0);
							}
							if(depts.get(i).getWsrys()>=jxpg.get(j).getExcellentscore()){
								pg+=("网上荣誉室栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else if(depts.get(i).getWsrys()>jxpg.get(j).getPassingscore()){
								pg+=("网上荣誉室栏目:"+jxpg.get(j).getExcellentevaluate()+"<br/>");
							}else{
								pg+=("网上荣誉室栏目:"+jxpg.get(j).getDisqualificationevalua()+"<br/>");
							}
						}
					}else{
						pg="上级暂未设置评估规则";
					}
					depts.get(i).setJxpg(pg);
				}
			}
		}
		model.addAttribute("endDate", endDate);
		model.addAttribute("startDate", startDate);
		model.addAttribute("xs",xs );
		model.addAttribute("datas", datas);
		model.addAttribute("firstChild",firstChild);
		model.addAttribute("departId", departId);
		model.addAttribute("type", type);
		model.addAttribute("list", depts);
		if(departId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/detailScore.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/detailScore.html";
		}
	}
	/*
	public String getDetailScore(Integer departId,Integer root,HttpServletRequest request,
			ModelMap model,String endDate,String startDate){
		CmsSite site = CmsUtils.getSite(request);
		//找到错误的Site
		Channel channel = channelMng.getChannelByDepts(departId);
		if(channel!=null){
			site = channel.getSite();
		}
		String existsXtAndJg = "";        //是否存在系统和机关
		List<CmsDepartment> depts = new ArrayList<CmsDepartment>();
		CmsDepartment dept = null;
		Integer firstChild = null;
		if (departId!=null && departId>0) {
			dept = cmsDepartmentMng.findById(departId);
			if(departId.equals(1) || dept.getParent()==null){
				existsXtAndJg = "have";
				if (departId.equals(1)) {
					firstChild = departId;
				}else{
					firstChild = cmsDepartmentDao.findChildrenFirstDeptidByPid(departId);
				}
			}
			if(root==null || "".equals(root)){
				if (departId.equals(1)) {
					depts=cmsDepartmentDao.findAdminDept();
				}else{
					depts = cmsDepartmentDao.findXtForJGDWByDeptId(departId);
				}
			}else{
				depts = cmsDepartmentDao.getAllZhiBuById(departId);
			}
		}
		try {
			String channelName = null;
			// 组装strIds
			String strIds = "(";
			if(depts.size()>0){
				for (int i = 0; i < depts.size(); i++) {
					depts.get(i).setScore(0.0);
					if (i == depts.size() - 1) {
						strIds += ("'" + depts.get(i).getId() + "'");
					} else {
						strIds += ("'" + depts.get(i).getId() + "',");
					}
				}
			}else{
				strIds += ("'" + departId + "'");
			}
			strIds = strIds + ")";
			model.addAttribute("strIds", strIds);
			model.addAttribute("endDate", endDate);
			model.addAttribute("startDate", startDate);
			//获取当月的第一天和最后一天
			// 根据idList查询对应总分记录 -》scoreList
			List<Object[]> scoreList = risenDao.getListse(strIds, channelName,
					startDate, endDate);
			List<Object[]> scoreListMonth = risenDao.getListseMonth(strIds,
					channelName);
			
			if (root != null && root.intValue() == 1) {
				// 机关栏目
				String zbdt = "支部动态";
				String dfjl = "党费缴纳";
				String dwgk = "党务公开";
				String tsgz = "特色工作";
				// 下面list是为了取各个栏目的分数《取机关》
				List<Object[]> ListChZ = risenDao.getListCha(strIds, zbdt,
						startDate, endDate);
				List<Object[]> ListChDF = risenDao.getListCha(strIds, dfjl,
						startDate, endDate);
				List<Object[]> ListChDW = risenDao.getListCha(strIds, dwgk,
						startDate, endDate);
				List<Object[]> ListChT = risenDao.getListCha(strIds, tsgz,
						startDate, endDate);
				// 机关取
				for (int i = 0; i < depts.size(); i++) {
					depts.get(i).setZbdt(0.0);
					for (int j = 0; j < ListChZ.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChZ.get(j)[0]).toString())) {
							depts.get(i).setZbdt(
									Double.parseDouble(ListChZ.get(j)[1]
											.toString()));
						}
					}
				}
				// 机关取党费缴纳
				for (int i = 0; i < depts.size(); i++) {
					depts.get(i).setDfjl(0.0);
					for (int j = 0; j < ListChDF.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChDF.get(j)[0]).toString())) {
							depts.get(i).setDfjl(
									Double.parseDouble(ListChDF.get(j)[1]
											.toString()));
						}
					}
				}
				// 机关取党务公开
				for (int i = 0; i < depts.size(); i++) {
					depts.get(i).setDwgk(0.0);
					for (int j = 0; j < ListChDW.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChDW.get(j)[0]).toString())) {
							depts.get(i).setDwgk(
									Double.parseDouble(ListChDW.get(j)[1]
											.toString()));
						}
					}
				}
				// 机关取特色工作
				for (int i = 0; i < depts.size(); i++) {
					depts.get(i).setTsgz(0.0);
					for (int j = 0; j < ListChT.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChT.get(j)[0]).toString())) {
							depts.get(i).setTsgz(
									Double.parseDouble(ListChT.get(j)[1]
											.toString()));
						}
					}
				}
			} else {
				String tpxw = "图片新闻";
				String djdt = "党建动态";
				String jyjl = "经验交流";
				String mtxc = "媒体视点";
				String llyt = "理论研讨";
				String wsrys = "网上荣誉室";
				// 下面list是为了取各个栏目的分数《取系统》
				List<Object[]> ListCha = risenDao.getListCha(strIds, tpxw,
						startDate, endDate);
				List<Object[]> ListChaD = risenDao.getListCha(strIds, djdt,
						startDate, endDate);
				List<Object[]> ListChaJ = risenDao.getListCha(strIds, jyjl,
						startDate, endDate);
				List<Object[]> ListChM = risenDao.getListCha(strIds, mtxc,
						startDate, endDate);
				List<Object[]> ListChaL = risenDao.getListCha(strIds, llyt,
						startDate, endDate);
				List<Object[]> ListChW = risenDao.getListCha(strIds, wsrys,
						startDate, endDate);
				// 取出图片新闻栏目分数
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListCha.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListCha.get(j)[0]).toString())) {
							depts.get(i).setTpxw(
									Double.parseDouble(ListCha.get(j)[1]
											.toString()));
						}
					}
				}
				// 取出党建动态
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListChaD.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChaD.get(j)[0]).toString())) {
							depts.get(i).setDjdt(
									Double.parseDouble(ListChaD.get(j)[1]
											.toString()));
						}
					}
				}
				// 取出经验交流分数
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListChaJ.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChaJ.get(j)[0]).toString())) {
							depts.get(i).setJyjl(
									Double.parseDouble(ListChaJ.get(j)[1]
											.toString()));
						}
					}
				}
				// 取出媒体视点分数
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListChM.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChM.get(j)[0]).toString())) {
							depts.get(i).setMtxc(
									Double.parseDouble(ListChM.get(j)[1]
											.toString()));
						}
					}
				}
				// 取出理论研讨分数
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListChaL.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChaL.get(j)[0]).toString())) {
							depts.get(i).setLlyt(
									Double.parseDouble(ListChaL.get(j)[1]
											.toString()));
						}
					}
				}
				// 取出网上荣誉室分数
				for (int i = 0; i < depts.size(); i++) {
					for (int j = 0; j < ListChW.size(); j++) {
						if (depts.get(i).getId().intValue() == Integer
								.parseInt((ListChW.get(j)[0]).toString())) {
							depts.get(i).setWsrys(
									Double.parseDouble(ListChW.get(j)[1]
											.toString()));
						}
					}
				}
			}
			// 双重for循环 给list里面的每个bean设置积分，从scoreList
			// （if list.get(i).id=scoreList.get(j).id
			// list.get(i).setXXX(scoreList.get(j).getScore())）
			// Score -》月度积分
			for (int i = 0; i < depts.size(); i++) {
				depts.get(i).setScore(0.0);
				for (int j = 0; j < scoreListMonth.size(); j++) {
					if (depts.get(i).getId().intValue() == Integer
							.parseInt((scoreListMonth.get(j)[0]).toString())) {
						depts.get(i).setScore(
								Double.valueOf(scoreListMonth.get(j)[1]
										.toString()));
					}
				}
			}
			// pid -> 总积分
			for (int i = 0; i < depts.size(); i++) {
				depts.get(i).setPid(0.0);
				for (int j = 0; j < scoreList.size(); j++) {
					if (depts.get(i).getId().intValue() == Integer
							.parseInt((scoreList.get(j)[0]).toString())) {
						depts.get(i).setPid(
								Double.parseDouble(scoreList.get(j)[1]
										.toString()));
					}
				}
			}
			CmsDepartment[] dms = new CmsDepartment[depts.size()];
			for (int i = 0; i < depts.size(); i++) {
				dms[i] = depts.get(i);
			}

			// list 排序
			Arrays.sort(dms, new Comparator<CmsDepartment>() {
				@Override
				public int compare(CmsDepartment d1, CmsDepartment d2) {
					double deptScore = 0;
					double compareDeptScore = 0;
					deptScore = d1.getScore() == null ? 0 : d1.getScore()
							.doubleValue();
					compareDeptScore = d2.getScore() == null ? 0 : d2
							.getScore().doubleValue();
					double distance = (compareDeptScore - deptScore) * 10;
					return (int) distance;
				}
			});

			depts.clear();
			for (int i = 0; i < dms.length; i++) {
				depts.add(dms[i]);
			}

			for (int i = 0; i < depts.size(); i++) {
				depts.get(i).setGroupid(i + 1);
			}

			model.addAttribute("parentId", root);
			// list2方便页面echarts显示图start
			String xs = "[";
			String datas = "[";
			for (int i = 0; i < depts.size(); i++) {
				if (i == depts.size() - 1) {
					xs += ("'" + depts.get(i).getName() + "'");
					datas += (depts.get(i).getScore());
				} else {
					xs += ("'" + depts.get(i).getName() + "',");
					datas += (depts.get(i).getScore() + ",");
				}
			}
			xs = xs + "]";
			datas = datas + "]";
			model.addAttribute("xs", xs);
			model.addAttribute("datas", datas);
			// list2方便页面echarts显示图end

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("deptid",departId);
		model.addAttribute("firstChild",firstChild);
		model.addAttribute("HaveXtAndJg",existsXtAndJg);
		
		List<RisenJxjfAssess> jxpg= jxjfpg.findbydepartpg(1);
		for (int i = 0; i< depts.size(); i++) {
			String pg="";
			for (int j = 0; j < jxpg.size(); j++) {
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("tpxw")){
					if(depts.get(i).getTpxw()!=null && depts.get(i).getTpxw()>jxpg.get(j).getPassingscore()  && depts.get(i).getTpxw()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getTpxw()!=null && depts.get(i).getTpxw()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("djdt")){
					if(depts.get(i).getDjdt()!=null && depts.get(i).getDjdt()>jxpg.get(j).getPassingscore()  && depts.get(i).getDjdt()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getDjdt()!=null && depts.get(i).getDjdt()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("jyjl")){
					if(depts.get(i).getJyjl()!=null && depts.get(i).getJyjl()>jxpg.get(j).getPassingscore()  && depts.get(i).getJyjl()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getJyjl()!=null && depts.get(i).getJyjl()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("mtsd")){
					if(depts.get(i).getMtxc()!=null && depts.get(i).getMtxc()>jxpg.get(j).getPassingscore()  && depts.get(i).getMtxc()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getMtxc()!=null && depts.get(i).getMtxc()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("llyt")){
					if(depts.get(i).getLlyt()!=null && depts.get(i).getLlyt()>jxpg.get(j).getPassingscore()  && depts.get(i).getLlyt()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getLlyt()!=null && depts.get(i).getLlyt()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("wsrys")){
					if(depts.get(i).getWsrys()!=null && depts.get(i).getWsrys()>jxpg.get(j).getPassingscore()  && depts.get(i).getWsrys()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getWsrys()!=null && depts.get(i).getWsrys()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("zbdt")){
					if(depts.get(i).getZbdt()!=null && depts.get(i).getZbdt()>jxpg.get(j).getPassingscore()  && depts.get(i).getZbdt()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getZbdt()!=null && depts.get(i).getZbdt()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("dfjl")){
					if(depts.get(i).getDfjl()!=null && depts.get(i).getDfjl()>jxpg.get(j).getPassingscore()  && depts.get(i).getDfjl()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getDfjl()!=null && depts.get(i).getDfjl()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("dwgk")){
					if(depts.get(i).getDwgk()!=null && depts.get(i).getDwgk()>jxpg.get(j).getPassingscore()  && depts.get(i).getDwgk()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getDwgk()!=null && depts.get(i).getDwgk()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("tsgz")){
					if(depts.get(i).getTsgz()!=null && depts.get(i).getTsgz()>jxpg.get(j).getPassingscore()  && depts.get(i).getTsgz()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getTsgz()!=null && depts.get(i).getTsgz()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("score")){
					if(depts.get(i).getScore()!=null && depts.get(i).getScore()>jxpg.get(j).getPassingscore()  && depts.get(i).getScore()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getScore()!=null && depts.get(i).getScore()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
				if(jxpg.get(j).getChannelname()!=null && jxpg.get(j).getChannelname().equals("Pid")){
					if(depts.get(i).getPid()!=null && depts.get(i).getPid()>jxpg.get(j).getPassingscore()  && depts.get(i).getPid()<jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getPassingevaluate()+"<br/>";
					}else if(depts.get(i).getPid()!=null && depts.get(i).getPid()>=jxpg.get(j).getExcellentscore()){
						pg+=jxpg.get(j).getExcellentevaluate()+"<br/>";
					}else{
						pg+=jxpg.get(j).getDisqualificationevalua()+"<br/>";
					}
				}
			}
			depts.get(i).setJxpg(pg);
		}
		model.addAttribute("list", depts);
		if(departId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/detailScore.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/detailScore.html";
		}
	}
	*/
	
	
	@RequestMapping("/getFrontShareContentScore.jspx")
	public String getShareContentsScore(Integer deptId,HttpServletRequest request,Integer pageNo,
			ModelMap model,Integer status,String startDate,String endDate){
		CmsSite site = CmsUtils.getSite(request);
		// 找到错误的Site
		if (deptId != null) {
			Channel channel = channelMng.getChannelByDepts(deptId);
			if (channel != null) {
				site = channel.getSite();
			}
		}
		try {
			Pagination pagination = manager.getScoresFrontByDeptIdAndDate(cpn(pageNo), request, CookieUtils
					.getPageSize(request),deptId,startDate,endDate,status);
			model.addAttribute("pagination",pagination);
			model.addAttribute("pageNo",pagination.getPageNo());
			model.addAttribute("deptId",deptId);
			model.addAttribute("status",status);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Integer siteId = site.getId();
		if(siteId==1){
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+"default"+"/alone/myShared_lists.html";
		}else{
			return "/WEB-INF/t/cms/"+site.getPath()+"/"+site.getAccessPath()+"/alone/myShared_lists.html";
		}
	}
	
	@RequestMapping("/o_signfortzgg.jspx")
	@ResponseBody
	public Object signfortzgg(String contentid, HttpServletRequest request,
			ModelMap model) {
		/*
		//获取服务器ip地址
		InetAddress address = null;
		try {
			 address = InetAddress.getLocalHost();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		String remoteAddr = request.getRemoteAddr();  
		String forwarded = request.getHeader("X-Forwarded-For");  
		String realIp = request.getHeader("X-Real-IP");  
		String addip = null;  
		if (realIp == null) {  
		     if (forwarded == null) {  
		    	 addip = remoteAddr;  
		     } else {  
		    	 addip = remoteAddr + "/" + forwarded.split(",")[0];  
		     }  
		 } else {  
		     if (realIp.equals(forwarded)) {  
		    	 addip = realIp;  
		     } else {  
		         if(forwarded != null){  
		             forwarded = forwarded.split(",")[0];  
		        }  
		         addip = realIp + "/" + forwarded;  
		     }  
		}
		Boolean boo = null;
		if(org.springframework.util.StringUtils.hasText(addip)){
			boo = risenSignforService.savaForIpAndContent(addip, contentid);
		}
		return boo;
	}
	
	@RequestMapping(value = "/getAllSignInfoByContentId.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllSignInfoByContentId(String contentid, HttpServletRequest request,
			ModelMap model){
		List<RisenSignfor> fors = risenSignforService.getAllInfoByContentId(contentid);
		List<String> returnFors = new ArrayList<String>();
		for (RisenSignfor risenSignfor : fors) {
			String str = "";
			String departName = risenSignfor.getRisensfDept().getName();
			str += departName+","
			+risenSignfor.getRisensfDate()+","
			+risenSignfor.getRisensfIp();
			returnFors.add(str);
		}
		return returnFors;
	}
	
	@RequestMapping(value = "/getjxtj.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Object getjxtj(String queryModel,Integer deptId, HttpServletRequest request, ModelMap model, String root){
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		List<CmsDepartment>listBack=new ArrayList<CmsDepartment>();
		if(deptId==null){
			CmsUser user = CmsUtils.getUser(request);
			CmsDepartment cmsDepartment = new CmsDepartment();
			cmsDepartment = user.getDepartment();
			deptId = cmsDepartment.getId();
		}
		CmsDepartment pDept = departmentMng.findById(deptId);
		if(root == null || "".equals(root)){
			if(pDept.getParent()==null){
				for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
					if(DepartmentCache.departentList.get(i).getParent() != null && DepartmentCache.departentList.get(i).getParent().getId().intValue()==deptId.intValue()){
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
			}else{
				list=departmentMng.getList(deptId, false);
			}
			if(deptId == 1){
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
			}
		}else{
			if(pDept.getParent() == null && deptId != 1){
				int deptidd = cmsDepartmentDao.findChildrenFirstDeptidByPid(deptId);
				list = departmentMng.getList(deptidd, false);
			}else{
				list=departmentMng.getList(deptId, false);
			}
			list.remove(pDept);
		}
		
		List<RisenIntegral> li = new ArrayList<RisenIntegral>();
		
		try{
			String channelName = null;
			//组装strIds
			String strIds = "(";
			for (int i = 0; i < list.size(); i++) {
				if(i==list.size()-1){
					strIds+=("'"+list.get(i).getId()+"'");
				}else {
					strIds+=("'"+list.get(i).getId()+"',");
				}	
			}
			strIds=strIds+")";
			model.addAttribute("strIds",strIds );
			//根据idList查询对应总分记录 -》scoreList
			List<Object[]> scoreListMonth=risenDao.getListseMonth(strIds, channelName);
			for(int i = 0; i <list.size(); i++) {			
				for (int j = 0; j < scoreListMonth.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreListMonth.get(j)[0]).toString())){
						list.get(i).setScore(Double.valueOf(scoreListMonth.get(j)[1].toString()));
					}
				}
			}
			Collections.sort(list,new DeptComparator());
			/*
			CmsDepartment[]  dms = new CmsDepartment[list.size()];
			for (int i=0; i<list.size(); i++) {
				dms[i] = list.get(i);
			}
			
			//list 排序
			Arrays.sort(dms, new Comparator<CmsDepartment>(){
				@Override
				public int compare(CmsDepartment d1, CmsDepartment d2) {
					return (d2.getScore()==null?0:d2.getScore().intValue()) - (d1.getScore()==null?0:d1.getScore().intValue());
				}
			});
			list.clear();
			for (int i=0; i<dms.length; i++) {
				list.add(dms[i]);
			}
			*/
			for(int i = 0; i <list.size(); i++) {
				list.get(i).setGroupid(i+1);
			}
			model.addAttribute("parentId", root);
			for (int i = 0; i < list.size(); i++) {
				RisenIntegral ri = new RisenIntegral();
				ri.setRisenitOrgname(list.get(i).getName());
				if(list.get(i).getScore()==null){
					ri.setRisenitScore(new Double(0));
				}else{
					ri.setRisenitScore(new Double(list.get(i).getScore()));
				}
				li.add(ri);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	/*
	 * 将UEditor里面保存的内容去掉标签
	 */
	public String html2Text(String inputString) {
		String htmlStr = "";
		if (inputString != null) {
			htmlStr = inputString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&ldquo;", " ").replaceAll("&rdquo;", " ").replaceAll("&lsquo;", " ").replaceAll("&rsquo;", " "); // 含html标签的字符串
		}
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
	
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private CmsKeywordMng cmsKeywordMng;
	@Autowired
	private CmsConfigMng configMng;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private ContentBuyMng contentBuyMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private IRisenOrgLifeCalendarService olcmanager;
	@Autowired
	private CmsDepartmentMng departmentMng;
	@Autowired
	private CmsStatisticSvc cmsStatisticSvc;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private IRisenSignforService risenSignforService;
	@Autowired
	private IRisenScoreService risenScoreService;
	@Autowired
	private CmsDepartmentDao cmsDepartmentDao;
	@Autowired
	private IRisenIntegralService itManager;
	@Autowired
	private CmsDepartmentMng cmsDepartmentMng;
	@Autowired
	private IRisenIntegralDao risenDao;
	@Autowired
	private ContentDao contentDao;
	@Autowired
	private IRisenIntegralRecordDao manager;
	@Autowired
	private RisenJxjfAssessMng  jxjfpg;
	
}
