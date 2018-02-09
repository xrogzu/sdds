package com.risen.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.metamodel.relational.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jeecms.common.page.SimplePage.cpn;

import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.ContentShareCheckMng;
import com.jeecms.common.page.Paginable;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.RisenIntegralRecord;
import com.risen.entity.RisenReject;
import com.risen.service.IRisenIntegralRecordService;
import com.risen.service.IRisenRejectService;

@Controller
public class RisenRejectAction {
	private static final Logger log = LoggerFactory.getLogger(RisenRejectAction.class);
	
	@RequiresPermissions("risenReject:v_list")
	@RequestMapping("/risenReject/v_list.do")
	public String getList(ModelMap model,HttpServletRequest request,Integer pageNo){
		CmsUser user = CmsUtils.getUser(request);
		Integer departId = (user != null) ? user.getDepartment().getId() : 1;
		
		Pagination pagination = service.getPage(departId, cpn(pageNo), CookieUtils.getPageSize(request),1);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "reject/list";
	}
	@RequiresPermissions("risenReject:v_ylist")
	@RequestMapping("/risenReject/v_ylist.do")
	public String getyList(ModelMap model,HttpServletRequest request,Integer pageNo){
		CmsUser user = CmsUtils.getUser(request);
		Integer departId = (user != null) ? user.getDepartment().getId() : 1;
		
		Pagination pagination = service.getPage(departId, cpn(pageNo), CookieUtils.getPageSize(request),2);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("status", "2");
		return "reject/list";
	}
	
	@RequiresPermissions("risenReject:o_update")
	@RequestMapping("/risenReject/o_update.do")
	public String update(ModelMap model,HttpServletRequest request,String contentId,String departId){
		Integer updateRecord = 0;
		if(StringUtils.hasText(contentId) && StringUtils.hasText(departId)){
			updateRecord = service.changeStatus(new Integer(departId), new Integer(contentId));
		}
		model.addAttribute("updateRecord", updateRecord.intValue());
		return "redirect:v_list.do";
	}
	
	@RequiresPermissions("risenReject:o_save")
	@RequestMapping("/risenReject/o_save.do")
	public void save(HttpServletRequest request,HttpServletResponse response,String contentId,String departId,
			String recordId,String risenrjReason){
		CmsUser user = CmsUtils.getUser(request);
		int saveRecord = 0;
		if(StringUtils.hasText(recordId)){
			//向驳回记录表添加记录
			if(StringUtils.hasText(departId) && StringUtils.hasText(contentId) && StringUtils.hasText(risenrjReason)){
				//先判断数据库里是否存在此组织ID和文章列表ID的数据
				List<RisenReject> rejects = service.getListByDepartIdAndContentId(new Integer(departId), new Integer(contentId));
				if(rejects!=null && rejects.size()>0){
					for (RisenReject risenReject : rejects) {
						Integer risenrjUnid = risenReject.getRisenrjUnid();
						service.changeReason(risenrjUnid, risenrjReason);
					}
				}else{
					RisenReject reject = new RisenReject();
					reject.setRisenrjCdate(new Date());
					reject.setRisenrjContentId(contentMng.findById(new Integer(contentId)));
					reject.setRisenrjDepartId(new Integer(departId));
					reject.setRisenrjStatus(1);
					reject.setRisenrjReason(risenrjReason);
					reject.setRisenrjbhDepartId(user.getDepartment().getId());
					service.save(reject);
				}
				//先删除共享信息 以及 采纳得分
				RisenIntegralRecord record = recordService.findById(new Integer(recordId));
				contentShareCheckMng.deleteByIds(new Integer[]{record.getRisenirSharecheck()});
				recordService.delete(record.getRisenirUuid());
				saveRecord = 1;
			}
		}
		try{
			response.getWriter().write((saveRecord==1) ? "success" : "failure");
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("删除失败");
			e.printStackTrace();
		}
	}
	
	
	private WebErrors validateSave(RisenRejectAction bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
//		bean.setSite(site);
		return errors;
	}
	
	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getId(), errors)) {
			return errors;
		}
		return errors;
	}
	
	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, site.getId(), errors);
		}
		return errors;
	}
	
	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		return false;
	}
	@Autowired
	private IRisenRejectService service;
	@Autowired
	private IRisenIntegralRecordService recordService;
	@Autowired
	private ContentShareCheckMng contentShareCheckMng;
	@Autowired
	private ContentMng contentMng;
}
