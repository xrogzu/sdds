package com.risen.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.risen.entity.CmsAppealMailReplay;
import com.risen.service.CmsAppealMailMng;
import com.risen.service.CmsAppealMailReplayMng;

@Controller
public class CmsAppealMaiReplayAct {
	private static final Logger log = LoggerFactory.getLogger(CmsAppealMaiReplayAct.class);
	
	@RequiresPermissions("appealMailReplay:v_add.do")
	@RequestMapping("/appealMailReplay/v_add.do")
	public String search(ModelMap model,HttpServletRequest request) {
		String appealId = request.getParameter("id");
		model.addAttribute("appealId", appealId);
		return "development_partymembers/appealMailReplay/add";
	}
	
	@RequiresPermissions("appealMailReplay:edit.do")
	@RequestMapping("/appealMailReplay/edit.do")
	public String edit(ModelMap model,HttpServletRequest request) {
		String appealId = request.getParameter("id");
		CmsAppealMailReplay replay = mailReplayMng.findById(new Integer(appealId));
		model.addAttribute("Replay", replay);
		return "development_partymembers/appealMailReplay/edit";
	}
	
	@RequiresPermissions("appealMailReplay:o_save.do")
	@RequestMapping("/appealMailReplay/o_save.do")
	public String save(CmsAppealMailReplay bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String appealId = request.getParameter("appealId");
		mailMng.update(new Integer(appealId));
		CmsUser user = CmsUtils.getUser(request);
		bean.setCdate(new Date());
		bean.setUsername(user.getUsername());
		cmsAppealMailReplayMng.save(bean);
		log.info("save CmsAppealMailReplay id={}", bean.getReplayId());
		return "redirect:../appealMail/v_list.do";
	}
	
	private WebErrors validateSave(CmsAppealMailReplay bean, HttpServletRequest request) {
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
	private CmsAppealMailMng  cmsAppealMailMng;
	@Autowired
	private com.jeecms.cms.manager.assist.CmsAppealMailMng mailMng;
	@Autowired
	private CmsAppealMailReplayMng  cmsAppealMailReplayMng;
	@Autowired
	private com.jeecms.cms.manager.assist.CmsAppealMailReplayMng mailReplayMng;
}
