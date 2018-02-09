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

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.risen.entity.RisenSignfor;
import com.risen.service.IRisenSignforService;

@Controller
public class RisenSignforAct {
	private static final Logger log = LoggerFactory.getLogger(RisenSignforAct.class);

	@RequiresPermissions("risenSignfor:v_list")
	@RequestMapping("/risenSignfor/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "risenSignfor/list";
	}

	@RequiresPermissions("risenSignfor:v_add")
	@RequestMapping("/risenSignfor/v_add.do")
	public String add(ModelMap model) {
		return "risenSignfor/add";
	}

	@RequiresPermissions("risenSignfor:v_edit")
	@RequestMapping("/risenSignfor/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenSignfor", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "risenSignfor/edit";
	}

	@RequiresPermissions("risenSignfor:o_save")
	@RequestMapping("/risenSignfor/o_save.do")
	public String save(RisenSignfor bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.save(bean);
		log.info("save risenSignfor id={}", bean.getRisensfUuid());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("risenSignfor:o_update")
	@RequestMapping("/risenSignfor/o_update.do")
	public String update(RisenSignfor bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getRisensfUuid(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update risenSignfor id={}.", bean.getRisensfUuid());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("risenSignfor:o_delete")
	@RequestMapping("/risenSignfor/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		RisenSignfor[] beans = manager.deleteByIds(ids);
		for (RisenSignfor bean : beans) {
			log.info("delete risenSignfor id={}", bean.getRisensfUuid());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(RisenSignfor bean, HttpServletRequest request) {
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
		RisenSignfor entity = manager.findById(id);
		if(errors.ifNotExist(entity, RisenSignfor.class, id)) {
			return true;
		}
//		if (!entity.getSite().getId().equals(siteId)) {
//			errors.notInSite(RisenSignfor.class, id);
//			return true;
//		}
		return false;
	}

	@Autowired
	private IRisenSignforService manager;
}