package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.RisenPublicnotify;
import com.risen.service.IRisenPublicnotifyService;

@Controller
public class RisenPublicnotifyAct {
	private static final Logger log = LoggerFactory
			.getLogger(RisenPublicnotifyAct.class);

	@RequiresPermissions("risenPublicnotify:v_list")
	@RequestMapping("/risenPublicnotify/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = manager.getPage(cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "publicnotify/list";
	}

	@RequiresPermissions("risenPublicnotify:v_add")
	@RequestMapping("/risenPublicnotify/v_add.do")
	public String add(ModelMap model) {
		return "publicnotify/add";
	}

	@RequiresPermissions("risenPublicnotify:v_edit")
	@RequestMapping("/risenPublicnotify/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenPublicnotify", manager.findById(id));
		model.addAttribute("pageNo", pageNo);
		return "publicnotify/edit";
	}

	@RequiresPermissions("risenPublicnotify:o_save")
	@RequestMapping("/risenPublicnotify/o_save.do")
	public String save(RisenPublicnotify bean, HttpServletRequest request,
			ModelMap model) {
		// 获取当前登录用户信息
		CmsUser user = CmsUtils.getUser(request);
		// 设置创建用户id
		bean.setRisenpnCusr(user.getId());
		// 设置创建用户姓名
		bean.setRisenpnCusrname(user.getUsername());
		if (!StringUtils.isEmpty(user.getDepartment())) {
			if (!StringUtils.isEmpty(user.getDepartment().getId())) {
				// 设置创建用户的所属组织id
				bean.setRisenpnCusrdept(user.getDepartment().getId());
			}
			// 设置创建用户的所属组织名称
			if (!StringUtils.isEmpty(user.getDepartment().getId())) {
				bean.setRisenpnCusrdeptname(user.getDepartment().getName());
			}
		}
		/**
		 * 状态:0_无效,1_有效,2_假删除（放入回收站）,3_真删除 这里新增时前台没有这项属性显示 这里给0默认值 在修改里有体现
		 */
		bean.setRisenpnState("0");
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.save(bean);
		log.info("save risenPublicnotify id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("risenPublicnotify:o_update")
	@RequestMapping("/risenPublicnotify/o_update.do")
	public String update(RisenPublicnotify bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		//设置修改日期
		bean.setRisenpnUdate(new Date());
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update risenPublicnotify id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("risenPublicnotify:o_delete")
	@RequestMapping("/risenPublicnotify/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		RisenPublicnotify[] beans = manager.deleteByIds(ids);
		for (RisenPublicnotify bean : beans) {
			log.info("delete risenPublicnotify id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(RisenPublicnotify bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		// bean.setSite(site);
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
		RisenPublicnotify entity = manager.findById(id);
		if (errors.ifNotExist(entity, RisenPublicnotify.class, id)) {
			return true;
		}
		// if (!entity.getSite().getId().equals(siteId)) {
		// errors.notInSite(risenPublicnotify.class, id);
		// return true;
		// }
		return false;
	}

	@Autowired
	private IRisenPublicnotifyService manager;
}