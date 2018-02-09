package com.risen.action;

import java.util.Date;
import static com.jeecms.common.page.SimplePage.cpn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.RisenDiscussion;
import com.risen.entity.RisenTarget;
import com.risen.service.IRisenDiscussionService;


@Controller
public class RisenDiscussionAct {
	private static final Logger log = LoggerFactory.getLogger(RisenDiscussionAct.class);

	@RequiresPermissions("risenDiscussion:mylist")
	@RequestMapping("/risenDiscussion/mylist.do")
	public String list(String userId,Integer pageNo, HttpServletRequest request, ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		Integer departId = user.getDepartment().getId();
		Pagination pagination = manager.getMyDiscussion(cpn(pageNo), CookieUtils.getPageSize(request), new Integer(userId), departId);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("userId", userId);
		model.addAttribute("username", userManager.findById(new Integer(userId)).getUsername());
		
		return "discussion/list";
	}
	
	@RequiresPermissions("risenDiscussion:v_add")
	@RequestMapping("/risenDiscussion/v_add.do")
	public String add(ModelMap model,HttpServletRequest request,String userId) {
		model.addAttribute("userId", userId);
		model.addAttribute("username",userManager.findById(new Integer(userId)).getUsername());
		return "discussion/add";
	}
	
	@RequiresPermissions("risenDiscussion:v_edit")
	@RequestMapping("/risenDiscussion/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model,String userId) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("userId", userId);
		model.addAttribute("username",userManager.findById(new Integer(userId)).getUsername());
		model.addAttribute("RisenDiscussion", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "discussion/edit";
	}
	
	@RequiresPermissions("risenDiscussion:save")
	@RequestMapping("/risenDiscussion/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		RisenDiscussion bean = new RisenDiscussion();
		CmsUser user = CmsUtils.getUser(request);
		Integer risenYear = new Integer(request.getParameter("risendsYear"));
		Integer userId = new Integer(request.getParameter("risendsUserid"));
		bean.setRisendsCdate(new Date());
		bean.setRisendsCuser(user.getUsername());
		bean.setRisendsUserdeptId(user.getDepartment().getId());
		bean.setRisendsRemark(request.getParameter("risendsRemark"));
		bean.setRisendsScore(new Integer(request.getParameter("risendsScore")));
		bean.setRisendsYear(risenYear);
		bean.setRisendsUserid(userManager.findById(userId));
		boolean exists = manager.exist(userId, user.getDepartment().getId(), risenYear);
		if(exists){
			try{
				response.getWriter().write("exists");
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			try{
				manager.save(bean);
				response.getWriter().write("success");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@RequiresPermissions("risenDiscussion:conserve")
	@RequestMapping("/risenDiscussion/conserve.do")
	public String conserve(RisenDiscussion bean, HttpServletRequest request, ModelMap model,String userId) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setRisendsCdate(new Date());
		bean.setRisendsUserid(userManager.findById(new Integer(userId)));
		CmsUser user = CmsUtils.getUser(request);
		bean.setRisendsUserdeptId(user.getDepartment().getId());
		bean.setRisendsCuser(user.getUsername());
		manager.save(bean);
		log.info("save RisenDiscussion id={}", bean.getRisendsId());
		return list(userId,1,request,model);
	}
	
	@RequiresPermissions("risenDiscussion:o_delete")
	@RequestMapping("/risenDiscussion/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model,String userId) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		for (Integer id : ids) {
			manager.delete(id);
		}
		return list(userId,pageNo,request,model);
	}
	
	@RequiresPermissions("risenDiscussion:check")
	@RequestMapping("/risenDiscussion/check.do")
	public void check(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String year = request.getParameter("year");
		Integer departId = CmsUtils.getUser(request).getDepartment().getId();
		boolean exists = manager.exist(new Integer(userId), departId, new Integer(departId));
		if(exists){
			try{
				response.getWriter().write("exists");
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			try{
				response.getWriter().write("noexists");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@RequiresPermissions("risenDiscussion:o_update")
	@RequestMapping("/risenDiscussion/o_update.do")
	public String update(RisenDiscussion bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getRisendsId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		String userId = request.getParameter("userId");
		log.info("update RisenDiscussion id={}.", bean.getRisendsId());
		return list(userId,1,request,model);
	}
	
	private WebErrors validateSave(RisenDiscussion bean, HttpServletRequest request) {
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
	public IRisenDiscussionService manager;
	@Autowired
	public CmsUserMng userManager;
}