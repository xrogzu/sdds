package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risen.entity.RisenPartyapplication;
import com.jeecms.core.entity.CmsSite;
import com.risen.service.RisenPartyapplicationMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class RisenPartyapplicationAct {
	private static final Logger log = LoggerFactory.getLogger(RisenPartyapplicationAct.class);

	@RequiresPermissions("risenPartyapplication:v_list")
	@RequestMapping("/risenPartyapplication/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "development_partymembers/risenPartyapplication/list";
	}

	@RequiresPermissions("risenPartyapplication:v_add")
	@RequestMapping("/risenPartyapplication/v_add.do")
	public String add(ModelMap model,String risenpaFiletype) {
		//材料标识位 判断材料是那种类型文书  1 入党申请书 2 入党志愿书 3 入党转正书     
		model.addAttribute("risenpaFiletype",risenpaFiletype);
		return "development_partymembers/risenPartyapplication/add";
	}

	@RequiresPermissions("risenPartyapplication:v_edit")
	@RequestMapping("/risenPartyapplication/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenPartyapplication", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "development_partymembers/risenPartyapplication/edit";
	}

	@RequiresPermissions("risenPartyapplication:o_save")
	@RequestMapping("/risenPartyapplication/o_save.do")
	public String save(RisenPartyapplication bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
	
		bean = manager.save(bean);
		log.info("save RisenPartyapplication id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("risenPartyapplication:o_update")
	@RequestMapping("/risenPartyapplication/o_update.do")
	public String update(RisenPartyapplication bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update RisenPartyapplication id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("risenPartyapplication:o_delete")
	@RequestMapping("/risenPartyapplication/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		RisenPartyapplication[] beans = manager.deleteByIds(ids);
		for (RisenPartyapplication bean : beans) {
			log.info("delete RisenPartyapplication id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(RisenPartyapplication bean, HttpServletRequest request) {
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
		if (errors.ifNull(id, "id")) {
			return true;
		}
		RisenPartyapplication entity = manager.findById(id);
		if(errors.ifNotExist(entity, RisenPartyapplication.class, id)) {
			return true;
		}
//		if (!entity.getSite().getId().equals(siteId)) {
//			errors.notInSite(RisenPartyapplication.class, id);
//			return true;
//		}
		return false;
	}
	
	@Autowired
	private RisenPartyapplicationMng manager;
}