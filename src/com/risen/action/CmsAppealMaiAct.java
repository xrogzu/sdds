package com.risen.action;


import static com.jeecms.common.page.SimplePage.cpn;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.manager.assist.CmsAppealMailReplayMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.service.CmsAppealMailMng;

@Controller
public class CmsAppealMaiAct {
	private static final Logger log = LoggerFactory.getLogger(CmsAppealMaiAct.class);
	
	@RequiresPermissions("appealMail:v_list")
	@RequestMapping("/appealMail/v_list.do")
	public String detail(HttpServletResponse response, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment department  = user.getDepartment();
		Integer departId = department.getId();
		if("机关党委,党总支".indexOf(department.getSddspoOrgtype())>-1 && !(departId.equals(1))){
			departId = department.getParent().getId();
		}
		Pagination pagination=cmsAppealMailMng.getPage(cpn(pageNo), CookieUtils.getPageSize(request),departId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "development_partymembers/appealMail/list";

	}
	@Autowired
	private CmsAppealMailMng  cmsAppealMailMng;
}
