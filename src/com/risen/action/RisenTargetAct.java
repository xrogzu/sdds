package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risen.entity.CmsAppealMail;
import com.risen.entity.RisenTarget;
import com.risen.entity.RisenTargetDetail;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.risen.service.IRisenTargetDetailService;
import com.risen.service.IRisenTargetService;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class RisenTargetAct {
	private static final Logger log = LoggerFactory.getLogger(RisenTargetAct.class);

	@RequiresPermissions("risenTarget:v_list")
	@RequestMapping("/risenTarget/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		try {
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		Integer parentId = null;
		if(depart.getId()!=1){
			parentId = depart.getParent().getId();
		}else{
			parentId = 1;
		}
		List<CmsDepartment> departments = departmentService.getList(parentId, false);
		if(departments.size()>0){
			model.addAttribute("DepartmentSub",departments.size());
		}else{
			model.addAttribute("DepartmentSub",0);
		}
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils.getPageSize(request),String.valueOf(parentId),null);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		request.getSession().setAttribute("deptid", String.valueOf(parentId));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "development_partymembers/risenTarget/list";
	}
	@RequiresPermissions("risenTarget:v_mylist")
	@RequestMapping("/risenTarget/v_mylist.do")
	public String mylist(Integer pageNo, HttpServletRequest request, ModelMap model) {
		try {
		CmsUser user = CmsUtils.getUser(request);
		Integer parentId = user.getDepartment().getId();
		List<CmsDepartment> departments = departmentService.getList(parentId, false);
		if(departments.size()>0){
			model.addAttribute("DepartmentSub",departments.size());
		}else{
			model.addAttribute("DepartmentSub",0);
		}
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils.getPageSize(request),"",parentId);
		
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		request.getSession().setAttribute("deptid", String.valueOf(parentId));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "development_partymembers/risenTarget/mylist";
	}


	@RequiresPermissions("risenTarget:v_add")
	@RequestMapping("/risenTarget/v_add.do")
	public String add(ModelMap model,HttpServletRequest request) {
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		String jiguan = "exists";
		String xitong = "exists";
		if(depart.getSddspoOrgtype().equals("机关党委")||(depart.getSddspoOrgtype().equals("党总支"))){
			if(depart.getId()!=1 && depart.getName().indexOf("市局机关党委")==-1){
				xitong = "noexists";
				jiguan = "exists";
			}
		}else{
			xitong = "noexists";
			jiguan = "noexists";
		}
	    model.addAttribute("jiguan", jiguan);
	    model.addAttribute("xitong", xitong);
		return "development_partymembers/risenTarget/add";
	}

	@RequiresPermissions("risenTarget:v_edit")
	@RequestMapping("/risenTarget/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("risenTarget", manager.findById(id));
		model.addAttribute("pageNo",pageNo);
		return "development_partymembers/risenTarget/edit";
	}

	@RequiresPermissions("risenTarget:o_save")
	@RequestMapping("/risenTarget/o_save.do")
	public String save(RisenTarget bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment dept = user.getDepartment();
		Integer parentId = dept.getId();
		String addWay = request.getParameter("addWay");
		if(!StringUtils.hasText(addWay)){
			addWay="all";
		}
		if(StringUtils.hasText(addWay)){
			List<CmsDepartment> list = new ArrayList<CmsDepartment>();
			List<CmsDepartment> subList = new ArrayList<CmsDepartment>();
			if(parentId==1){
				list = departmentService.getTypeList(1, "机关党委");
				subList = departmentService.getList(1,false);
			}else{
				parentId = dept.getParent().getId();
				Integer zbParentId = parentId;
				boolean isShiju = true;
				if(dept.getParent().getParent()!=null){
					isShiju = false;
				}
				if(dept.getParent().getParent()==null){
					zbParentId = dept.getId();
				}
				if(isShiju){
					subList = departmentService.getTypeList(zbParentId,"支部");
				}else{
					subList = departmentService.getTypeList(parentId,"支部");
				}
				list = departmentService.getTypeList(parentId,"机关党委");
			}
			if (addWay.equals("all")) {
				if(list.size()>0){
					for (CmsDepartment depart : list) {
						RisenTarget baseModel = new RisenTarget();
						baseModel.setRisentgCuserId(String.valueOf(parentId));
						baseModel.setRisentgContent(bean.getRisentgContent());
						baseModel.setRisentgEdate(bean.getRisentgEdate());
						baseModel.setRisentgSdate(bean.getRisentgSdate());
						baseModel.setRisentgGualityPercent(bean
								.getRisentgGualityPercent());
						baseModel.setRisentgNumPercent(bean.getRisentgNumPercent());
						baseModel.setRisentgTitle(bean.getRisentgTitle());
						baseModel.setRisentgTotalScore(bean.getRisentgTotalScore());
						baseModel.setRisentgCuserName(user.getDepartment()
								.getName());
						baseModel.setRisentgCdate(new Date());
						baseModel.setRisentgStatus("0");
						baseModel.setRisentgCorgId(String.valueOf(depart.getId()));
						baseModel.setRisentgCorgName(depart.getName());
						manager.save(baseModel);
						RisenTargetDetail detail = new RisenTargetDetail();
						detail.setRisentgdPid(baseModel.getId());
						detail.setRisentgdParentId(String.valueOf(parentId));
						detail.setRisentgdOrgid(String.valueOf(depart.getId()));
						detail.setRisentgdQualityscore(0);
						detail.setRisentgdNumscore(0);
						detail.setRisentgdStatus("0");
						detail.setRisentgdOrgname(depart.getName());
						detailManager.save(detail);
					}
				}
			} else {
				if(subList.size()>0){
					for (CmsDepartment depart : subList) {
						RisenTarget baseModel = new RisenTarget();
						baseModel.setRisentgCuserId(String.valueOf(parentId));
						baseModel.setRisentgContent(bean.getRisentgContent());
						baseModel.setRisentgEdate(bean.getRisentgEdate());
						baseModel.setRisentgSdate(bean.getRisentgSdate());
						baseModel.setRisentgGualityPercent(bean
								.getRisentgGualityPercent());
						baseModel.setRisentgNumPercent(bean.getRisentgNumPercent());
						baseModel.setRisentgTitle(bean.getRisentgTitle());
						baseModel.setRisentgTotalScore(bean.getRisentgTotalScore());
						baseModel.setRisentgCuserName(user.getDepartment()
								.getName());
						baseModel.setRisentgCdate(new Date());
						baseModel.setRisentgStatus("0");
						if (!String.valueOf(depart.getPriority()).equals("1")) {
							baseModel.setRisentgCorgId(String.valueOf(depart
									.getId()));
							baseModel.setRisentgCorgName(depart.getName());
							manager.save(baseModel);
							RisenTargetDetail detail = new RisenTargetDetail();
							detail.setRisentgdPid(baseModel.getId());
							detail.setRisentgdParentId(String.valueOf(parentId));
							detail.setRisentgdOrgid(String.valueOf(depart.getId()));
							detail.setRisentgdQualityscore(0);
							detail.setRisentgdNumscore(0);
							detail.setRisentgdStatus("0");
							detail.setRisentgdOrgname(depart.getName());
							detailManager.save(detail);
						}
					}
				}
			}
		}
		log.info("save RisenTarget id={}", bean.getId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("risenTarget:o_update")
	@RequestMapping("/risenTarget/o_update.do")
	public String update(RisenTarget bean, Integer pageNo, HttpServletRequest request,
			ModelMap model,Integer departId) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		Integer parentId = null;
		if(depart.getId()==1){
			parentId = 1;
		}else{
			if(depart.getSddspoOrgtype().equals("机关党委")||(depart.getSddspoOrgtype().equals("党总支"))){
				if(depart.getName().indexOf("市局机关党委")==-1){
					parentId = depart.getParent().getId();
				}else{
					parentId = depart.getId();
				}
			}else{
				parentId = depart.getId();
			}
		}
		bean.setRisentgCuserId(String.valueOf(parentId));
		bean.setRisentgCuserName(user.getDepartment().getName());
		bean.setRisentgCdate(new Date());
		bean = manager.update(bean);
		//修改已完成目标的数量分数以及质量分数
		List<RisenTargetDetail> details = detailManager.getAllUnfinishedList(bean.getId(),new Integer(bean.getRisentgCorgId()));
		if(details!=null){
			int totalScore = bean.getRisentgTotalScore().intValue();
			int percent = bean.getRisentgNumPercent().intValue();
			int score = totalScore * percent/100;
			for(RisenTargetDetail detail : details){
				detail.setRisentgdNumscore(new Integer(score));
				Integer quascores = detail.getRisentgdQualityscore();
				if(quascores!=null){
					int quascore = quascores.intValue();
					if(quascore+score>totalScore){
						detail.setRisentgdQualityscore(new Integer(totalScore-score));
					}
				}
				detailManager.update(detail);
			}
			
		}
		log.info("update RisenTarget id={}.", bean.getId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("risenTarget:o_delete")
	@RequestMapping("/risenTarget/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		for (Integer id : ids) {
			detailManager.deleteByTargetId(id);
			manager.delete(id);
			log.info("delete RisenTarget id={}", id);
		}
		return list(pageNo, request, model);
	}
	
	@RequiresPermissions("risenTarget:report")
	@RequestMapping("/risenTarget/report.do")
	public String report(HttpServletRequest request,ModelMap model) {
		String type=request.getParameter("type");
		CmsUser user = CmsUtils.getUser(request);
		Integer parentId = user.getDepartment().getId();
		List<CmsDepartment> depts = null;
		String isHaveXj = "exists";
		if(user.getDepartment().getId()!=1 && user.getDepartment().getName().indexOf("市局机关党委")==-1){
			type = "jiguan";
			isHaveXj = "noexists";
		}
		if((type==null)||type.equals("xitong")){
			if(String.valueOf(parentId).equals("1")){
				depts = dept.getTypeList(1, "机关党委");
			}else{
				depts = dept.getAllJgdwDeptById(user.getDepartment().getParent().getId(),false);
			}
		}else{
			if(String.valueOf(parentId).equals("1")){
				depts = dept.getList(1, false);
			}else{
				boolean isShiJu = true;
				if(user.getDepartment().getParent().getParent()!=null){
					isShiJu = false;
				}
				if(isShiJu){
					depts = dept.getList(parentId, false);
				}else{
					depts = dept.getList(user.getDepartment().getParent().getId(), false);
				}
			}
		}
		StringBuffer orgIds = new StringBuffer(1000);
		
		for (CmsDepartment cmsDepartment : depts) {
			orgIds.append("'"+cmsDepartment.getId()+"',");
		}
		
		if(StringUtils.hasText(orgIds.toString())){
			model.addAttribute("list", manager.getReportList(orgIds.substring(0,orgIds.length()-1)));
		} else {
			model.addAttribute("list",  new ArrayList<RisenTarget>());
		}
		model.addAttribute("isHaveXj",  isHaveXj);
		return "development_partymembers/risenTarget/report";
	}

	private WebErrors validateSave(RisenTarget bean, HttpServletRequest request) {
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
	private IRisenTargetService manager;
	@Autowired
	private CmsDepartmentMng dept;
	@Autowired
	private IRisenTargetDetailService detailManager;
	@Autowired
	private CmsDepartmentMng departmentService;
}