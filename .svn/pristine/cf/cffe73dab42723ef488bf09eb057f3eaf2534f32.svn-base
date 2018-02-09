package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.RisenPartyChange;
import com.risen.service.IRisenPartyChangeService;
import com.risen.service.RisenDevpartyMng;

@Controller
public class RisenPartyChangeAct {
	private static final Logger log = LoggerFactory.getLogger(RisenPartyChangeAct.class);
	@RequiresPermissions("RisenPartyChange:v_list")
	@RequestMapping("/RisenPartyChange/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model,String changeType) {
		CmsUser user = CmsUtils.getUser(request);
		Integer departId = user.getDepartment().getId();
		if(user.getDepartment().getSddspoOrgtype().equals("支部")){
			departId = user.getDepartment().getParent().getId();
		}
		Pagination pagination = manager.getAllInfoByDepartId(cpn(pageNo), CookieUtils.getPageSize(request), String.valueOf(departId), changeType);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "";
	}
	
	@RequiresPermissions("RisenPartyChange:getAll")
	@RequestMapping("/RisenPartyChange/getAll.do")
	public String getAll(Integer pageNo, HttpServletRequest request, ModelMap model,Integer departId) {
		CmsUser user = CmsUtils.getUser(request);
		String addDeparts = "";
		boolean isShiju = false;
		if(user.getDepartment().getId()!=1){
			if(departId==null){
				if(user.getDepartment().getSddspoOrgtype().equals("支部")){
					departId = user.getDepartment().getId();
				}else{
					departId = user.getDepartment().getParent().getId();
					if(user.getDepartment().getParent().getParent()==null){
						isShiju = true;
					}
				}
			}
			addDeparts = addDeparts + "(";
			List<CmsDepartment> depts = departMng.getAllDeptById(departId, isShiju);
			for (CmsDepartment cmsDepartment : depts) {
				addDeparts = addDeparts + "'" + cmsDepartment.getId()+"',";
			}
			addDeparts = addDeparts.substring(0, addDeparts.length()-1)+")";
		}else{
			departId = 1;
		}
		
		Pagination pagination = manager.getAllInfoByDepartId(cpn(pageNo), CookieUtils.getPageSize(request), addDeparts, "1");
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("deptId",departId);
		return "risenPartyChange/getAll";
	}
	
	@RequiresPermissions("RisenPartyChange:edit")
	@RequestMapping("/RisenPartyChange/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenPartyChange", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "risenPartyChange/edit";
	}
	
	@RequiresPermissions("RisenPartyChange:viewInfo")
	@RequestMapping("/RisenPartyChange/v_viewInfo.do")
	public String viewInfo(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenPartyChange", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "risenPartyChange/showInfo";
	}
	
	@RequiresPermissions("RisenPartyChange:update")
	@RequestMapping("/RisenPartyChange/update.do")
	public String update(RisenPartyChange bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getRisenpcId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.updeteDevparty(bean);
		Integer userId = new Integer(request.getParameter("userId"));
		bean.setRisenpcCuserid(userDao.findById(userId));
		partyMng.updateWithRisenPartyChange(bean);
		log.info("update RisenPartyChange id={}.", bean.getRisenpcId());
		return getAll(pageNo, request, model,null);
	}
	
	private WebErrors validateSave(RisenPartyChange bean, HttpServletRequest request) {
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
	private IRisenPartyChangeService manager;
	@Autowired
	private CmsDepartmentMng departMng;
	@Autowired
	private RisenDevpartyMng partyMng;
	@Autowired
	private CmsUserDao userDao;
}
