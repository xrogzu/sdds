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

import com.risen.entity.RisenJoinorg;
import com.jeecms.core.entity.CmsSite;
import com.risen.service.RisenJoinorgMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class RisenJoinorgAct {
	private static final Logger log = LoggerFactory.getLogger(RisenJoinorgAct.class);

	@RequiresPermissions("risenJoinorg:v_list")
	@RequestMapping("/risenJoinorg/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "development_partymembers/risenJoinorg/list";
	}

	@RequiresPermissions("risenJoinorg:v_add")
	@RequestMapping("/risenJoinorg/v_add.do")
	public String add(ModelMap model) {
		return "development_partymembers/risenJoinorg/add";
	}

	@RequiresPermissions("risenJoinorg:v_edit")
	@RequestMapping("/risenJoinorg/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenJoinorg", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "development_partymembers/risenJoinorg/edit";
	}

	@RequiresPermissions("risenJoinorg:o_save")
	@RequestMapping("/risenJoinorg/o_save.do")
	public String save(RisenJoinorg bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.save(bean);
		log.info("save RisenJoinorg id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("risenJoinorg:o_update")
	@RequestMapping("/risenJoinorg/o_update.do")
	public String update(RisenJoinorg bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update RisenJoinorg id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("risenJoinorg:o_delete")
	@RequestMapping("/risenJoinorg/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		RisenJoinorg[] beans = manager.deleteByIds(ids);
		for (RisenJoinorg bean : beans) {
			log.info("delete RisenJoinorg id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(RisenJoinorg bean, HttpServletRequest request) {
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
		RisenJoinorg entity = manager.findById(id);
		if(errors.ifNotExist(entity, RisenJoinorg.class, id)) {
			return true;
		}
//		if (!entity.getSite().getId().equals(siteId)) {
//			errors.notInSite(RisenJoinorg.class, id);
//			return true;
//		}
		return false;
	}
	
	@Autowired
	private RisenJoinorgMng manager;
}