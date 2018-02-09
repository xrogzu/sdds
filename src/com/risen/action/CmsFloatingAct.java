package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.CmsFloating;
import com.risen.service.CmsFloatingMng;

@Controller
public class CmsFloatingAct {
	private static final Logger log = LoggerFactory.getLogger(CmsFloatingAct.class);
	
	@RequiresPermissions("cmsFloatingact:v_list")
	@RequestMapping("/cmsFloatingact/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "floating/list";
	}

	@RequiresPermissions("cmsFloatingact:v_add")
	@RequestMapping("/cmsFloatingact/v_add.do")
	public String add(ModelMap model) {
		return "floating/add";
	}

	@RequiresPermissions("cmsFloatingact:v_edit")
	@RequestMapping("/cmsFloatingact/v_edit.do")
	public String edit(Integer sddsfiId, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(sddsfiId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("cmsFloatingact", manager.findById(sddsfiId));
		model.addAttribute("pageNo",pageNo);
		return "floating/edit";
	}

	@RequiresPermissions("cmsFloatingact:o_save")
	@RequestMapping("/cmsFloatingact/o_save.do")
	public String save(CmsFloating bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.save(bean);
		log.info("save cmsFloatingact id={}", bean.getSddsfiId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("cmsFloatingact:o_update")
	@RequestMapping("/cmsFloatingact/o_update.do")
	public String update(CmsFloating bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getSddsfiId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update cmsFloatingact id={}.", bean.getSddsfiId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("cmsFloatingact:o_delete")
	@RequestMapping("/cmsFloatingact/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsFloating[] beans = manager.deleteByIds(ids);
		for (CmsFloating bean : beans) {
			log.info("delete CmsFloating id={}", bean.getSddsfiId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(CmsFloating bean, HttpServletRequest request) {
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
		CmsFloating entity = manager.findById(id);
		if(errors.ifNotExist(entity, CmsFloating.class, id)) {
			return true;
		}
//		if (!entity.getSite().getId().equals(siteId)) {
//			errors.notInSite(cmsFloatingact.class, id);
//			return true;
//		}
		return false;
	}
	
	@Autowired
	private CmsFloatingMng manager;
}
