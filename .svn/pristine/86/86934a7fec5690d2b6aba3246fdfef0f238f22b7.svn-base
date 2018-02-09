package com.jeecms.cms.action.admin.main;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeecms.cms.cache.DepartmentCache;
import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.comparator.DeptComparator;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsLogMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.dao.IRisenIntegralDao;
import com.risen.dao.IRisenSignforDao;
import com.risen.entity.RisenChangeremindrecord;
import com.risen.entity.RisenSignfor;
@Controller
public class CmsDepartmentAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsDepartmentAct.class);

	@RequiresPermissions("department:depart_main")
	@RequestMapping("/department/depart_main.do")
	public String departMain(ModelMap model,HttpServletRequest request) {
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment dept = new CmsDepartment();
		if (user!=null) {
			dept = manager.findByName(user.getUsername());
		}
		if (dept!=null && dept.getParent()!=null) {
			model.addAttribute("deptId", dept.getParent().getId());
		}else{
			model.addAttribute("deptId", null);
		}
		return "department/depart_main";
	}
	
	@RequiresPermissions("department:v_left")
	@RequestMapping("/department/v_left.do")
	public String left() {
		return "department/left";
	}

	@RequiresPermissions("department:v_list")
	@RequestMapping("/department/v_list.do")
	public String list(Integer  root, HttpServletRequest request,ModelMap model) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		boolean isadmin = false;
		boolean isZb = manager.findlistByPid(user.getDepartment().getId());
		//boolean isself = false;
		if("admin".equals(user.getUsername())){
			isadmin = true;
		}
		boolean isnullorg = manager.findlistByPid(user.getDepartment().getId());
		if (("admin".equals(user.getUsername()) || "省局机关党委".equals(user.getUsername())) && root==null || (root!=null && Integer.valueOf(0).equals(root))) {
			list = manager.getList(null, false);
			root = null;
		}else{
			if(isnullorg){
				if (user.getDepartment().getSddspoOrgtype().equals("机关党委") || user.getDepartment().getSddspoOrgtype().equals("党总支")) {
					list=manager.getList(user.getDepartment().getParent().getId(), false);
				}else{
					list.add(manager.findById(user.getDepartment().getId()));
				}
			}else {
				list=manager.getList(user.getDepartment().getId(), false);
				if(root!=null){
					list=manager.getList(root, false);
				}
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("parentId", root);
		model.addAttribute("isadmin", isadmin);
		//model.addAttribute("isself", isself);
		model.addAttribute("isZhibu", isZb);
		return "department/list";
	}
	
	@RequiresPermissions("department:v_add")
	@RequestMapping("/department/v_add.do")
	public String add(Integer parentId,ModelMap model, HttpServletRequest request) {
		model.addAttribute("site",CmsUtils.getSite(request));
		CmsUser user = CmsUtils.getUser(request);
		List<CmsSite> siteList = cmsSiteMng.getList();
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		CmsDepartment parent=null;
		if(parentId!=null){
			parent=manager.findById(parentId);
		}else{
			parent = user.getDepartment().getParent();
		}
		List<CmsDepartment> cityList = manager.getCorecdKeyList("地市");
		List<CmsDepartment> orgtypeList = manager.getCorecdKeyList("组织类型");
		List<CmsDepartment> orgLevelList = manager.getCorecdKeyList("组织等级");
		model.addAttribute("siteList", siteList);
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("parent", parent);
		model.addAttribute("cityList", cityList);
		model.addAttribute("orgtypeList", orgtypeList);
		model.addAttribute("orgLevelList", orgLevelList);
		model.addAttribute("pri",manager.getMaxPriority(parentId));
		
		return "department/add";
	}

	@RequiresPermissions("department:o_save")
	@RequestMapping("/department/o_save.do")
	public String save(CmsDepartment bean, Integer pid,Integer[] channelIds,
			Integer[] controlChannelIds,Integer[] ctgIds,
			HttpServletRequest request,ModelMap model,String sddspoIsLianHe) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(pid!=null&&!pid.equals(0)){
			CmsDepartment parent = manager.findById(pid);
			bean.setParent(parent);
		}
		bean = manager.save(bean,channelIds,controlChannelIds,ctgIds);
		DepartmentCache.departentList.add(bean);//将新增党组织add到缓存中去
		log.info("save CmsDepartment id={}", bean.getId());
		cmsLogMng.operating(request, "cmsdepartment.log.save", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return list(pid, request, model);
	}
	@RequiresPermissions("department:v_logincoedvft")
	@RequestMapping("/department/v_logincoedvft.do")
	public void logincodevft(long code, ModelMap model, HttpServletRequest request,HttpServletResponse response){
		boolean msg = userMng.loginCodeExist(code);
		try {
			if(msg){
					response.getWriter().write("true");
				}else{
					response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequiresPermissions("department:v_check_name")
	@RequestMapping("/department/v_check_name.do")
	public String checkName(String name, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(name) || manager.nameExist(name)) {
			ResponseUtils.renderJson(response, "false");
		} else {
			ResponseUtils.renderJson(response, "true");
		}
		return null;
	}

	@RequiresPermissions("department:v_edit")
	@RequestMapping("/department/v_edit.do")
	public String edit(Integer id,Integer parentId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment department= manager.findById(id);
		//List<CmsSite> siteList = cmsSiteMng.getList();
		CmsDepartment parentdepart = department.getParent();
		Integer ppid = null;
		if(parentdepart!=null){
			ppid = parentdepart.getId();
		}
		/*
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		Map<String, Set<Integer>>departChannelIds=new HashMap<String, Set<Integer>>();
		for(CmsSite site:siteList){
			departChannelIds.put(site.getId().toString(), department.getChannelIds(site.getId()));
		}
		*/
		List<CmsDepartment>topDeparts=manager.getList(null, false);
		List<CmsDepartment> cityList = manager.getCorecdKeyList("地市");
		List<CmsDepartment> orgtypeList = manager.getCorecdKeyList("组织类型");
		List<CmsDepartment> orgLevelList = manager.getCorecdKeyList("组织等级");
		boolean isadmin = false;
		if ("admin".equals(user.getRealname())) {
			isadmin = true;
		}
		model.addAttribute("isadmin",isadmin);
		model.addAttribute("department", department);
		//model.addAttribute("siteList", siteList);
		//model.addAttribute("ctgList", ctgList);
		model.addAttribute("departments",topDeparts);
		model.addAttribute("site",CmsUtils.getSite(request));
		//model.addAttribute("departChannelIds", departChannelIds);
		model.addAttribute("ctgIds", department.getGuestBookCtgIds());
		model.addAttribute("parentId", parentId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("orgtypeList", orgtypeList);
		model.addAttribute("orgLevelList", orgLevelList);
		model.addAttribute("pri",manager.getMaxPriority(parentId));
		model.addAttribute("ppid", ppid);
		model.addAttribute("ppName", (parentdepart==null) ? "" : (parentdepart.getName()));
		model.addAttribute("user",user);
		return "department/edit";
	}

	@RequiresPermissions("department:o_update")
	@RequestMapping("/department/o_update.do")
	public String update(CmsDepartment bean, Integer pid,Integer[] channelIds,String departmentName,
			Integer[] controlChannelIds,Integer[] ctgIds,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(pid!=null){
			if(pid.equals(0)){
				bean.setParent(null);
			}else{
				CmsDepartment parent=manager.findById(pid);
				bean.setParent(parent);
			}
		}
		bean = manager.update(bean,channelIds,controlChannelIds,ctgIds);
		if (departmentName!=null && departmentName!="") {
			CmsUser user = userMng.findByUsername(departmentName);
			if (user!=null) {	
				user.setUsername(bean.getName());
				userMng.updateUser(user);
			}
		}
		log.info("update CmsDepartment id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsdepartment.log.update", "id="
				+ bean.getId() + ";name=" + bean.getName());
		return list(pid,request, model);
	}

	@RequiresPermissions("department:o_priority")
	@RequestMapping("/department/o_priority.do")
	public String priority(Integer[] wids, Integer[] priority, Integer parentId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		manager.updatePriority(wids, priority);
		log.info("update department priority");
		return list(parentId, request, model);
	}

	@RequiresPermissions("department:o_delete")
	@RequestMapping("/department/o_delete.do")
	public String delete(Integer[] ids, Integer parentId,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment[] beans = new CmsDepartment[ids.length];
		for (int i = 0; i < ids.length; i++) {
			beans[i] = manager.findById(ids[i]);
		}
		for (CmsDepartment bean : beans) {
			log.info("delete department id={}", bean.getId());
			cmsLogMng.operating(request, "cmsdepartment.log.delete", "id="
					+ bean.getId() + ";name=" + bean.getName());
		}
		manager.deleteByIds(ids);
		
		return list(parentId, request, model);
	}
	
	@RequiresPermissions("department:v_tree")
	@RequestMapping(value = "/department/v_tree.do")
	public String selectParent(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		if(isRoot){
			departList= manager.getList(null,false);
		}else{
			departList = manager.getList(Integer.parseInt(root),false);
		}
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/tree";
	}
	
	@RequiresPermissions("department:v_tree")
	@RequestMapping(value = "/department/v_childtree.do")
	public String getChild(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		CmsUser user=CmsUtils.getUser(request);
			departList= manager.getList(user.getDepartment().getId(),false);
		model.addAttribute("dept", user.getDepartment());
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "partybuild/tree";
	}
	
	@RequiresPermissions("department:v_tree")
	@RequestMapping(value = "/department/s_tree.do")
	public String getStree(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		CmsUser user=CmsUtils.getUser(request);
			departList= manager.getList(user.getDepartment().getId(),false);
		model.addAttribute("dept", user.getDepartment());
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "partybuild/shareContent/tree";
	}
	
	@RequiresPermissions("department:v_channels_list")
	@RequestMapping(value = "/department/v_channels_list.do")
	public String channelsAdd(Integer siteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return channelsListJson(siteId,request, response, model);
	}
	
	@RequiresPermissions("department:v_site_list")
	@RequestMapping(value = "/department/v_site_list.do")
	public String siteAddTree(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		List<CmsSite> siteList= cmsSiteMng.getTopList();
		model.addAttribute("siteList", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/sites";
	}
	
	@RequiresPermissions("department:v_control_site_list")
	@RequestMapping(value = "/department/v_control_site_list.do")
	public String siteControlTree(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		List<CmsSite> siteList= cmsSiteMng.getTopList();
		model.addAttribute("siteList", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/control_sites";
	}
	
	@RequiresPermissions("department:v_list_members")
	@RequestMapping("/department/v_list_members.do")
	public String list_members(Integer departId,Integer root, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = userMng.getAdminsByDepartId(departId,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("departId", departId);
		model.addAttribute("root", root);
		return "department/members";
	}
	

	private String channelsListJson(Integer siteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Channel> channelList = channelMng.getTopList(siteId, false);
		model.addAttribute("channelList", channelList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/channels";
	}
	/**
	 * 加载树（点击显示对应组织下党员）
	 * @param root
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/tree.do")
	public String departTreeShowParty(String root,String sddscpAssess,String sddscpChanges,String sddscpIsinjob, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer iszb ){
		log.debug("tree path={}", root);
		CmsUser user = CmsUtils.getUser(request);
		boolean isZb = manager.findlistByPid(user.getDepartment().getId());
		boolean isRoot;
		boolean isJgdw = false;
		/*boolean isZb = false;
		if(iszb!=null){
			if(iszb==1){
				isZb=true;
			}
		}*/
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList = new ArrayList<CmsDepartment>();
		CmsUser self=CmsUtils.getUser(request);
		CmsDepartment parent = new CmsDepartment();
		boolean isadmin = false;
	
		if("admin".equals(self.getUsername()) || "省局机关党委".equals(self.getUsername())){
			if(isRoot){
				departList= manager.getList(null,false);
			}else{
				departList = manager.getList(Integer.parseInt(root),false);
			}
			isadmin = true;
		}else {
			parent = manager.findById(self.getDepartment().getParent().getId());
			if(!self.getDepartment().getSddspoOrgtype().equals("支部")){
				isJgdw = true;
				if(isRoot){
					departList= manager.getList(self.getDepartment().getParent().getId(),false);
				}else{
					departList = manager.getList(Integer.parseInt(root),false);
				}
			}else{
				if(isZb){
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						CmsDepartment depr = manager.findById(Integer.parseInt(root));
						departList.add(depr) ;
					}
				}else{
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						departList = manager.getList(Integer.parseInt(root),false);
					}
				}
			}
		}
		if(departList.size()<=0){
			isZb = true;
			List<CmsDepartment> list = manager.getList(self.getDepartment().getParent().getId(),false);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getId().equals(self.getDepartment().getId())){
					departList.add(list.get(i));
				}
			}
		}
		if(departList.size()>0){
			for (int i = 0; i < departList.size(); i++) {
				departList.get(i).setSddscpAssess(sddscpAssess);
				departList.get(i).setSddscpChanges(sddscpChanges);
				departList.get(i).setSddscpIsinjob(sddscpIsinjob);
			}
		}
		self.getDepartment().setSddscpAssess(sddscpAssess);
		self.getDepartment().setSddscpChanges(sddscpChanges);
		self.getDepartment().setSddscpIsinjob(sddscpIsinjob);
		parent.setSddscpAssess(sddscpAssess);
		parent.setSddscpChanges(sddscpChanges);
		parent.setSddscpIsinjob(sddscpIsinjob);
		String sddscpAll = request.getParameter("sddscpAll");
		model.addAttribute("isadmin", isadmin);
		model.addAttribute("sddscpAll", sddscpAll);
		model.addAttribute("self", self.getDepartment());
		model.addAttribute("list", departList);
		model.addAttribute("parentdepart",parent);
		model.addAttribute("isJgdw",isJgdw);
		model.addAttribute("isZb",isZb);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		if(isadmin){
			return "member/tree";
		}else {
			if(isZb){
				return "member/zbTree";
			}
			return "member/newtree";
		}
	}
	/**
	 * 显示侧边树(党员页)
	 * @return
	 */
	@RequestMapping(value = "/member/showtree.do")
	public String memberLeftDepartTree(HttpServletRequest request,String sddscpIsinjob,String sddscpAssess,String sddscpChanges,ModelMap model){
		String sddscpIsAll = request.getParameter("sddscpAll");
		model.addAttribute("sddscpIsinjob", sddscpIsinjob);
		model.addAttribute("sddscpAssess", sddscpAssess);
		model.addAttribute("sddscpChanges", sddscpChanges);
		model.addAttribute("sddscpAll", sddscpIsAll);
		return "member/showTree";
	}
	/**
	 * 换届提醒页面
	 */
	@RequestMapping("/changeremind/changeRemind_main.do")
	public String changeRemindMain(){
		return "changeremind/changeRemind_main";
	}
	/**
	 * 换届提醒list
	 */
	@RequiresPermissions("changeremind:changeRemind_list")
	@RequestMapping("/changeremind/changeRemind_list.do")
	public String changeRemindList(ModelMap cmsDepartment,String departmentName,String huanjie,Integer departid,Integer pid,Integer pageNo,HttpServletRequest request){
		CmsUser self = CmsUtils.getUser(request);
		if(pid==null){
			pid = 0;
		}
		boolean isPorg = false;
		CmsDepartment depart = self.getDepartment();
		Integer parentId = null;
		if((pid==0) && (departid==null)){
			if("省局机关党委,admin".indexOf(self.getUsername())>-1){
				isPorg = true;
			}else{
				if(depart.getSddspoOrgtype().equals("机关党委")||(depart.getSddspoOrgtype().equals("党总支"))){
					parentId = self.getDepartment().getParent().getId();
				}else{
					parentId = self.getDepartment().getId();
				}
				if(manager.findlistByPid(parentId)){
					departid = self.getDepartment().getId();
					pid = 0;
				}else {
					pid = parentId;
					isPorg = true;
				}
			}
			
		}else if(departid==null){
			if(pid!=0){
				if(manager.findlistByPid(pid)){
					departid = pid;
					pid = 0;
				}else{
					isPorg = true;
					depart = manager.findById(pid);
					if("机关党委,党总支".indexOf(depart.getSddspoOrgtype())>-1){
						pid = pid;
					}
				}
			}
		}else{
			if(departid!=0){
				if(manager.findlistByPid(departid)){
					pid = 0;
				}else{
					isPorg = true;
					depart = manager.findById(departid);
					if("机关党委,党总支".indexOf(depart.getSddspoOrgtype())>-1){
						pid = depart.getParent().getId();
						departid=null;
					}
				}
			}
		}
		try{
			List<CmsDepartment> departments = manager.changeRemindList(new Date(),departmentName,departid,pid,huanjie);
			List<CmsDepartment> returnDepts = new ArrayList<CmsDepartment>();
			Integer pages = departments.size()%20;
			Integer totalPages = departments.size() / 20;
			totalPages = (pages==0) ? totalPages : (totalPages+1);
			if( (pageNo==null) || (pageNo<=1)){
				pageNo = 1;
			}
			if(totalPages==0){
				pageNo = 1;
			}
			if((totalPages>0) && (pageNo>=totalPages)){
				pageNo = totalPages;
			}
			Integer startPosition = (pageNo==1)?0:((pageNo-1)*20);
			Integer endPosition = ((pageNo*20)>=departments.size()) ? (departments.size()) :((pageNo*20));
			if((departments!=null) && (departments.size()>0)){
				for ( int i = startPosition;i<endPosition;i++) {
					returnDepts.add(departments.get(i));
				}
			}
			
			Pagination pagination = new Pagination(cpn(pageNo),20,departments.size(),returnDepts);
			cmsDepartment.addAttribute("pagination",pagination);
			cmsDepartment.addAttribute("slef", self);
			cmsDepartment.addAttribute("isPorg",isPorg);
			cmsDepartment.addAttribute("huanjie",huanjie);
			cmsDepartment.addAttribute("pid",pid);
			cmsDepartment.addAttribute("deptName", departmentName);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "changeremind/changeRemind_list";
	}
	
	/**
	 * 侧边树请求(党员页)
	 * @return
	 */
	@RequestMapping(value = "/changeremind/departTree_changeremind.do")
	public String departTreeshowChange(String root,Integer groupId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		if(isRoot){
			departList= manager.getList(null,false);
		}else{
			departList = manager.getList(Integer.parseInt(root),false);
		}
		if(departList.size()>0){
			for (int i = 0; i < departList.size(); i++) {
				departList.get(i).setGroupid(groupId);
			}
		}
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "changeremind/departTree_changeremind";
	}
	/**
	 * 确认换届
	 * dialog数据
	 */
	@RequestMapping("/changeremind/v_confirm.do")
	@ResponseBody
	public Object confirm(Integer id){
		CmsDepartment depart = manager.findById(id);
		RisenChangeremindrecord crc = new RisenChangeremindrecord();
		if(depart!=null){
			if(depart.getName().contains("（")){
				String [] names = depart.getName().split("（");
				crc.setSddsccOrgname(names[0].trim());
			}else {
				crc.setSddsccOrgname(depart.getName());
			}
			crc.setSddsccOrgid(depart.getId());
			crc.setSddsccOsecretaryname(depart.getSddspoSecretary());
			crc.setSddsccOsecretaryid(depart.getSddspoSecretaryid());
			crc.setSddsccOsecretaryidc(depart.getSddspoSecretaryidc());
		}
		return crc;
	}
	
	
	@RequestMapping(value = "/changeremind/changeRemindLeft_departTree.do")
	public String changeRemindLeftDepartTree(){
		return "changeremind/changeRemindLeft_departTree";
	}
	
	@RequestMapping("/department/test.do")
	public String functionTest(Integer pid){
		manager.findlistByPid(pid);
		return "department/depart_main";
	}

	private WebErrors validatePriority(Integer[] ids, Integer[] priorities,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateSave(CmsDepartment bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsDepartment entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsDepartment.class, id)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 组织权限管理LIST
	 */
	@RequestMapping("/department/powerlist.do")
	public String powerlist(Integer  root, HttpServletRequest request,ModelMap model) {
		List<CmsDepartment>list=manager.getList(root, false);
		model.addAttribute("list", list);
		model.addAttribute("parentId", root);
		return "department/power";
	}
	/**
	 * 组织栏目权限管理from
	 */
	@RequestMapping("/department/channelpower.do")
	public String channelPower(Integer id,Integer parentId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment department= manager.findById(id);
		//List<CmsSite> siteList = cmsSiteMng.getList();
		List<CmsSite> siteList = new ArrayList<CmsSite>();
		CmsSite sitemodel = CmsUtils.getSite(request);
		siteList.add(sitemodel);
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		Map<String, Set<Integer>>departChannelIds=new HashMap<String, Set<Integer>>();
		for(CmsSite site:siteList){
			departChannelIds.put(site.getId().toString(), department.getChannelIds(site.getId()));
		}
		List<CmsDepartment>topDeparts=manager.getList(parentId, false);
		List<CmsDepartment> cityList = manager.getCorecdKeyList("地市");
		List<CmsDepartment> orgtypeList = manager.getCorecdKeyList("组织类型");
		model.addAttribute("department", department);
		model.addAttribute("siteList", siteList);
		model.addAttribute("ctgList", ctgList);
		Long t1 = System.currentTimeMillis();
		model.addAttribute("departments", CmsDepartment.getListForSelect(topDeparts));
		System.out.println("这一段运行了："+(System.currentTimeMillis()-t1));
		model.addAttribute("site",CmsUtils.getSite(request));
		model.addAttribute("departChannelIds", departChannelIds);
		model.addAttribute("ctgIds", department.getGuestBookCtgIds());
		model.addAttribute("parentId", parentId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("orgtypeList", orgtypeList);
		return "department/channel_power";
	}
	
	/**
	 * 新版侧边树（根据登录组织显示对应数据）
	 * @return
	 */
	@RequestMapping("/department/newtree.do")
	public String newDepartTree(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer iszb ) {
		CmsUser user = CmsUtils.getUser(request);
		boolean isZb = manager.findlistByPid(user.getDepartment().getId());
		log.debug("tree path={}", root);
		boolean isRoot;
		boolean isJgdw = false;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList = new ArrayList<CmsDepartment>();
		CmsUser self=CmsUtils.getUser(request);
		CmsDepartment parent = new CmsDepartment();
		boolean isadmin = false;
		if("admin".equals(self.getUsername()) || "省局机关党委".equals(self.getUsername())){
			if(isRoot){
				List<CmsDepartment> deptList = manager.getList(null,false);
				Set<CmsDepartment> childList = new HashSet<CmsDepartment>();
				parent.setId(000);
				parent.setName("山东省地方税务局党组党建工作指导组");
				for (CmsDepartment cmsDepartment : deptList) {
					CmsDepartment cdt = new CmsDepartment();
					cdt.setId(cmsDepartment.getId());
					cdt.setName(cmsDepartment.getName());
					cdt.setChild(cmsDepartment.getChild());
					cdt.setParent(parent);
					departList.add(cdt);
					childList.add(cdt);
				}
				parent.setChild(childList);
			}else{
				departList = manager.getList(Integer.parseInt(root),false);
			}
			isadmin = true;
		}else {
			CmsDepartment dept = user.getDepartment();
			if(dept.getSddspoOrgtype().equals("机关党委")||(dept.getSddspoOrgtype().equals("党总支"))){
				parent = manager.findById(self.getDepartment().getParent().getId());
				isJgdw = true;
				isZb = false;
				if(isRoot){
					departList= manager.getList(parent.getId(),false);
				}else{
					departList = manager.getList(Integer.parseInt(root),false);
				}
			}else{
				parent = manager.findById(self.getDepartment().getId());
				if(isZb){
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						CmsDepartment depr = manager.findById(Integer.parseInt(root));
						departList.add(depr) ;
					}
				}else{
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						departList = manager.getList(Integer.parseInt(root),false);
					}
				}
			}
		}
		if(departList.size()<=0){
			isZb = true;
		}
		model.addAttribute("isadmin", isadmin);
		model.addAttribute("self", self.getDepartment());
		model.addAttribute("list", departList);
		model.addAttribute("parentdepart",parent);
		model.addAttribute("isJgdw",isJgdw);
		model.addAttribute("isZb",isZb);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		if(isadmin){
			return "department/tree";
		}else {
			if(isZb){
				return "department/zbTree";
			}
			return "department/newtree";
		}
	}
	
	/**
	 * 换届提醒tree
	 */
	@RequestMapping("/department/changeRemindTree.do")
	public String changeRemindTree(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer iszb ) {
		log.debug("tree path={}", root);
		boolean isRoot;
		boolean isJgdw = false;
		boolean isZb = false;
		if(iszb!=null){
			if(iszb==1){
				isZb=true;
			}
		}
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList = new ArrayList<CmsDepartment>();
		CmsUser self=CmsUtils.getUser(request);
		CmsDepartment parent = new CmsDepartment();
		boolean isadmin = false;
		if("admin".equals(self.getUsername()) || "省局机关党委".equals(self.getUsername())){
			if(isRoot){
				List<CmsDepartment> deptList = manager.getList(null,false);
				Set<CmsDepartment> childList = new HashSet<CmsDepartment>();
				parent.setId(000);
				parent.setName("山东省地方税务局党组党建工作指导组");
				for (CmsDepartment cmsDepartment : deptList) {
					CmsDepartment cdt = new CmsDepartment();
					cdt.setId(cmsDepartment.getId());
					cdt.setName(cmsDepartment.getName());
					cdt.setChild(cmsDepartment.getChild());
					cdt.setParent(parent);
					departList.add(cdt);
					childList.add(cdt);
				}
				parent.setChild(childList);
			}else{
				departList = manager.getList(Integer.parseInt(root),false);
			}
			isadmin = true;
		}else {
			parent = manager.findById(self.getDepartment().getParent().getId());
			if(self.getDepartment().getSddspoOrgtype().equals("机关党委")||(self.getDepartment().getSddspoOrgtype().equals("党总支"))){
				isJgdw = true;
				if(isRoot){
					departList= manager.getList(self.getDepartment().getParent().getId(),false);
				}else{
					departList = manager.getList(Integer.parseInt(root),false);
				}
			}else{
				if(isZb){
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						CmsDepartment depr = manager.findById(Integer.parseInt(root));
						departList.add(depr) ;
					}
				}else{
					if(isRoot){
						departList= manager.getList(self.getDepartment().getId(),false);
					}else{
						departList = manager.getList(Integer.parseInt(root),false);
					}
				}
			}
		}
		if(departList.size()<=0){
			isZb = true;
			List<CmsDepartment> list = manager.getList(self.getDepartment().getParent().getId(),false);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getId().equals(self.getDepartment().getId())){
					departList.add(list.get(i));
				}
			}
		}
		model.addAttribute("isadmin", isadmin);
		model.addAttribute("self", self.getDepartment());
		model.addAttribute("list", departList);
		model.addAttribute("parentdepart",parent);
		model.addAttribute("isJgdw",isJgdw);
		model.addAttribute("isZb",isZb);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		if(isadmin){
			return "changeremind/tree";
		}else {
			if(isZb){
				return "changeremind/zbTree";
			}
			return "changeremind/departTree_changeremind";
		}
	}
	/**
	 * 导入组织小工具
	 */
	@RequestMapping("/department/imptools.do")
	public String impOrgToUser(){
		manager.impOrgToUser();
		return "";
	}
	
	/**
	 * 栏目权限框架页
	 */
	@RequestMapping("/department/departrole_main.do")
	public String todepartmain(){
		return "department/departrole_main";
	}
	
	/**
	 * 站点栏目权限页
	 */
	@RequestMapping("/department/editrole.do")
	public String editrole(Integer id,Integer parentId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsDepartment department= manager.findById(id);
		List<CmsSite> siteList = cmsSiteMng.getList();
		List<CmsGuestbookCtg>ctgList=guestBookCtgMng.getList(CmsUtils.getSiteId(request));
		Map<String, Set<Integer>>departChannelIds=new HashMap<String, Set<Integer>>();
		for(CmsSite site:siteList){
			departChannelIds.put(site.getId().toString(), department.getChannelIds(site.getId()));
		}
		List<CmsDepartment>topDeparts=manager.getList(null, false);
		
		model.addAttribute("department", department);
		model.addAttribute("siteList", siteList);
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("departments", CmsDepartment.getListForSelect(topDeparts));
		model.addAttribute("site",CmsUtils.getSite(request));
		model.addAttribute("departChannelIds", departChannelIds);
		model.addAttribute("ctgIds", department.getGuestBookCtgIds());
		model.addAttribute("parentId", parentId);
		return "department/editrole";
	}
	
	/**
	 * 显示权限控制树
	 */
	@RequestMapping("/department/powertreeleft.do")
	public String powertreeleft(){
		return "department/powertreeleft";
	}
	
	/**
	 * 权限控制树
	 */
	@RequestMapping("/department/powertree.do")
	public String powertree(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<CmsDepartment> departList;
		if(isRoot){
			departList= manager.getList(null,false);
		}else{
			departList = manager.getList(Integer.parseInt(root),false);
		}
		model.addAttribute("list", departList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "department/powertree";
	}
	@RequestMapping("/department/getData.do")
	@ResponseBody
	public List<HashMap<String,String>> getDate(String userName){
		List<CmsUser>list=userMng.queryMeberByName(userName);
		List<HashMap<String,String>> datas=new ArrayList<HashMap<String,String>>();
		
		for (CmsUser u : list) {
			HashMap<String,String> m=new HashMap<String, String>();
			m.put("id", u.getId().toString());
			m.put("name", u.getUsername());
			m.put("idc",u.getSddscpIdnumber());
			datas.add(m);
		}
		return datas;
	}
	
	@RequiresPermissions("department:v_list_xt")
	@RequestMapping("/department/v_list_xt.do")
	public String xtlist(Integer root, HttpServletRequest request,ModelMap model,String channelId, String startDate, String endDate,String chaN ) {
		List<CmsDepartment> list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = user.getDepartment();
		boolean isnullorg = manager.findlistByPid(user.getDepartment().getId());
		//如果不是市局或省局 admin则将isnullorg设置为true
		if(cmsDepartment.getId()!=1 && cmsDepartment.getName().indexOf("市局机关党委")==-1){
			isnullorg  =true;
		}
		if(isnullorg){
			list = manager.getList(user.getDepartment().getParent().getId(), false);
			if("机关党委".equals(list.get(0).getSddspoOrgtype()) || "党总支".equals(list.get(0).getSddspoOrgtype())){
				list.remove(cmsDepartment);
			}
		}else {
			if(root == null || "".equals(root)){
				if(user.getDepartment().getId() == 1){
					list = manager.getTypeList(1, "机关党委");
				}else{
					list = manager.getTypeList(user.getDepartment().getParent().getId(), "机关党委");
				}
			}else{
				if(cmsDepartment.getName().indexOf("市局机关党委")==-1 && cmsDepartment.getName().indexOf("省局机关党委")==-1){
					list=manager.getList(user.getDepartment().getParent().getId(), false);
				}else{
					list=manager.getList(user.getDepartment().getId(), false);
				}
			}
		}
		model.addAttribute("ischildren",isnullorg);
		model.addAttribute("list", list);
		model.addAttribute("user",user);
		//组装strIds
		String strIds = "(";
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setScore(0.0);
			if(i==list.size()-1){
				strIds+=("'"+list.get(i).getId()+"'");
			}else {
				strIds+=("'"+list.get(i).getId()+"',");
			}	
		}
		strIds=strIds+")";
		List<Object[]> scoreList = risenDao.getListChannelScore(strIds, startDate, endDate);
		List<Object[]> monthAndTotalScoreList = risenDao.getListChannelMonthAndTotalScore(strIds, startDate, endDate);
		for(int i = 0; i <list.size(); i++) {	
			for (int j = 0; j < scoreList.size(); j++) {
				if(list.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
					//设置各个栏目的分数
					list.get(i).setTpxw(scoreList.get(j)[2].toString()!=null?Double.parseDouble(scoreList.get(j)[2].toString()):0);
					list.get(i).setDjdt(scoreList.get(j)[1].toString()!=null?Double.parseDouble(scoreList.get(j)[1].toString()):0);
					list.get(i).setJyjl(Double.parseDouble(scoreList.get(j)[3].toString()));
					list.get(i).setMtxc(Double.parseDouble(scoreList.get(j)[4].toString()));
					list.get(i).setLlyt(Double.parseDouble(scoreList.get(j)[5].toString()));
					list.get(i).setWsrys(Double.parseDouble(scoreList.get(j)[6].toString()));
				}
			}
		}
		//设置月总分和总分
		for(int i = 0; i <list.size(); i++) {	
			for (int j = 0; j < monthAndTotalScoreList.size(); j++) {
				if(list.get(i).getId().intValue() == Integer.parseInt((monthAndTotalScoreList.get(j)[0]).toString())){
					//设置各个栏目的分数
					list.get(i).setScore(Double.parseDouble(monthAndTotalScoreList.get(j)[2].toString()));
					list.get(i).setPid(Double.parseDouble(monthAndTotalScoreList.get(j)[1].toString()));
				}
			}
		}
		
		//设置List排序
		Collections.sort(list, new DeptComparator());
		
		for(int i = 0; i <list.size(); i++) {
			list.get(i).setGroupid(i+1);
		}
		model.addAttribute("parentId", root);
		//list2方便页面echarts显示图start
		String xs="[";
		String datas="[";
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				xs+=("'"+list.get(i).getName()+"'");
				datas+=(list.get(i).getScore());
			}else{
				xs+=("'"+list.get(i).getName()+"',");
				datas+=(list.get(i).getScore()+",");
			}	
		}
		xs=xs+"]";
		datas=datas+"]"; 
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("xs",xs );
		model.addAttribute("datas", datas);
		String type = "支部";
		if(!StringUtils.isBlank(user.getUsername()) && "admin,省局机关党委".indexOf(user.getUsername())>-1){
			type="省局";
		}else if(!StringUtils.isBlank(user.getUsername()) && user.getUsername().indexOf("市局机关党委")>-1){
			type="市局";
		}
		model.addAttribute("type", type);
		return "pbd/integrallist";
	}
	
	/**
	 * 
	 */
	@RequiresPermissions("department:exportData")
	@RequestMapping("/department/exportData.do")
	public void exportData(Integer root, HttpServletRequest request,HttpServletResponse response,String startDate, String endDate,Integer departId ) {
		CmsUser user = CmsUtils.getUser(request);
		if(departId==null){
			departId = user.getDepartment().getId();
		}
		List<CmsDepartment> depts = getDeptsData(root,startDate,endDate,departId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = format.format(new Date().getTime())+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		OutputStream out=null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
			.concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			 out= response.getOutputStream();
			 // 创建Excel工作薄
			 WritableWorkbook  wwb=Workbook.createWorkbook(out);
			 // 取得到文件的输出流
			 response.reset(); // 清空输出流
			 response.setContentType("application/vnd.ms-excel"); // 定义输出类型
			 // 设置字体
			 WritableFont wfont = new WritableFont(WritableFont.ARIAL,18,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK); 
			 WritableCellFormat font = new WritableCellFormat(wfont);
			 font.setAlignment(Alignment.CENTRE);
			 WritableSheet sheet = wwb.createSheet("党建绩效积分统计表",0);
			 //第一行
			 Label label = null;
			 if(root==null){
				 sheet.mergeCells(0,0,10,0);//合并单元格
			 }else{
				 sheet.mergeCells(0,0,9,0);//合并单元格
			 }
			label = new Label(0, 0,"党建绩效积分", font);
			sheet.addCell(label);
			wfont = new WritableFont(WritableFont.createFont("宋体"),14,WritableFont.BOLD);
			font = new WritableCellFormat(wfont);
			font.setWrap(true);//自动换行
			font.setAlignment(Alignment.CENTRE);//居中
			font.setVerticalAlignment(VerticalAlignment.CENTRE);
			addShell(root, label, font, sheet);
				
				//数据
				wfont = new WritableFont(WritableFont.createFont("宋体"),12);
				font = new WritableCellFormat(wfont); 
				font.setWrap(true);//自动换行
				font.setAlignment(Alignment.CENTRE);//居中
				font.setVerticalAlignment(VerticalAlignment.CENTRE);
				addExcelData(root, depts,label,font,sheet);
				//设置打印区域
				sheet.getSettings().setOrientation(PageOrientation.PORTRAIT) ;// 设置为竖向打印
				sheet.getSettings().setPaperSize(PaperSize.A4) ; // 设置纸张
				sheet.getSettings().setFitHeight(297) ; // 打印区高度
				//设置边距
				sheet.getSettings().setTopMargin(0.5) ;
				sheet.getSettings().setBottomMargin(0.3) ;
				sheet.getSettings().setLeftMargin(0.8);
				sheet.getSettings().setRightMargin(0.1) ;
				//设置页脚
				sheet.getSettings().setFooterMargin(0.07) ;   // 设置页脚边距（下）
				sheet.getSettings().setScaleFactor(85);       //打印缩放比例
				// 导出表格至Response流
				// 导出表格至Response流
				filename = java.net.URLEncoder.encode(filename, "UTF-8");
				response.setHeader("Content-Disposition", "attachment;filename="+ filename + ".xls"); // 设定输出文件
				wwb.write();
				//关闭文件
				wwb.close();
				out.flush();
				out.close();  
				response.flushBuffer();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	/*
	 * 后台首页展示栏目得分信息
	 */
	@RequiresPermissions("department:v_list_channelScore")
	@RequestMapping("/department/v_list_channelScore.do")
	public String getChannelScoreInfo(Integer root, HttpServletRequest request,ModelMap model,String startDate, String endDate,Integer departId){
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = user.getDepartment();
		if(departId!=null && departId!=0){
			cmsDepartment = manager.findById(departId);
		}else{
			departId = user.getDepartment().getId();
		}
		boolean isnullorg = manager.findlistByPid(departId);
		if(isnullorg){
			list = manager.getList(cmsDepartment.getParent().getId(),false);
		}else {
			if(root == null || "".equals(root)){
				if(cmsDepartment.getId()==1){
					list=manager.getTypeList(1, "机关党委");
				}else{
					Integer parentId = cmsDepartment.getParent().getId();
					list = manager.getTypeList(parentId,"机关党委");
				}
			}else{
				if(cmsDepartment.getId()==1){
					list=manager.getList(1, false);
				}else{
					Integer zbParentId = cmsDepartment.getId();
					boolean isShiju = true;
					if(cmsDepartment.getParent().getParent()!=null){
						isShiju = false;
					}
					if(isShiju){
						list = manager.getList(zbParentId,false);
					}else{
						Integer parentId = cmsDepartment.getParent().getId();
						list = manager.getTypeList(parentId,"支部");
					}
				}
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("user",user);
		//获取党组织下所有组织列表
		String strIds = "(";
		if(list.size()<0){
			strIds += String.valueOf(departId);
		}else{
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setScore(0.0);
				if(i==list.size()-1){
					strIds+=("'"+list.get(i).getId()+"'");
				}else {
					strIds+=("'"+list.get(i).getId()+"',");
				}	
			}
		}
		strIds=strIds+")";
		List<Object[]> scoreList = risenDao.getListChannelScore(strIds, startDate, endDate);
		List<Object[]> monthAndTotalScoreList = risenDao.getListChannelMonthAndTotalScore(strIds, startDate, endDate);
		if(scoreList!=null && scoreList.size()>0){
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < scoreList.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
						//设置各个栏目的分数
						list.get(i).setTpxw(Double.parseDouble(scoreList.get(j)[2].toString()));
						list.get(i).setDjdt(Double.parseDouble(scoreList.get(j)[1].toString()));
						list.get(i).setJyjl(Double.parseDouble(scoreList.get(j)[3].toString()));
						list.get(i).setMtxc(Double.parseDouble(scoreList.get(j)[4].toString()));
						list.get(i).setLlyt(Double.parseDouble(scoreList.get(j)[5].toString()));
						list.get(i).setWsrys(Double.parseDouble(scoreList.get(j)[6].toString()));
					}
				}
			}
		}
		if(monthAndTotalScoreList!=null && monthAndTotalScoreList.size()>0){
			//设置月总分和总分
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < monthAndTotalScoreList.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((monthAndTotalScoreList.get(j)[0]).toString())){
						//设置各个栏目的分数
						list.get(i).setScore(Double.parseDouble(monthAndTotalScoreList.get(j)[2].toString()));
						list.get(i).setPid(Double.parseDouble(monthAndTotalScoreList.get(j)[1].toString()));
					}
				}
			}
		}
		//设置List排序
		Collections.sort(list, new DeptComparator());
		
		for(int i = 0; i <list.size(); i++) {
			list.get(i).setGroupid(i+1);
		}
		
		model.addAttribute("parentId", root);
		//list2方便页面echarts显示图start
		String xs="[";
		String datas="[";
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				xs+=("'"+list.get(i).getName()+"'");
				datas+=(list.get(i).getScore());
			}else{
				xs+=("'"+list.get(i).getName()+"',");
				datas+=(list.get(i).getScore()+",");
			}	
		}
		xs=xs+"]";
		datas=datas+"]"; 
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("xs",xs );
		model.addAttribute("datas", datas);
		model.addAttribute("departId", departId);
		String type = "支部";
		if(!StringUtils.isBlank(user.getUsername()) && "admin,省局机关党委".indexOf(user.getUsername())>-1){
			type="省局";
		}else if(!StringUtils.isBlank(user.getUsername()) && user.getUsername().indexOf("市局机关党委")>-1){
			type="市局";
		}
		model.addAttribute("type", type);
		return "pbd/integrallistIndex";
	}
	
	/**
	 * 后台首页展示列表
	 * @param root
	 * @param request
	 * @param model
	 * @param channelId
	 * @return
	 */
	@RequiresPermissions("department:v_list_xtIndex")
	@RequestMapping("/department/v_list_xtIndex.do")
	public String xtlistForIndex(Integer root, HttpServletRequest request,ModelMap model,String channelId,String startDate, String endDate,String chaN,Integer departId ) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		List<CmsDepartment>listBack=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = user.getDepartment();
		if(departId!=null && departId!=0){
			cmsDepartment = manager.findById(departId);
		}else{
			departId = user.getDepartment().getId();
		}
		boolean isnullorg = manager.findlistByPid(departId);
		if(isnullorg){
			list = manager.getAllZhiBuById(cmsDepartment.getParent().getId());
		}else {
			if(root == null || "".equals(root)){
				list=manager.getList(departId, false);
				int[] pid = new int[list.size()];
				for(int i=0; i<list.size(); i++){
					pid[i] = list.get(i).getId();
				}
				boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
				if(!ischa && cmsDepartment.getPriority() != 1){
					isnullorg = true;
					list=manager.getList(departId, false);
				}else if(cmsDepartment.getParent() == null && departId != 1){
					
				}else if(departId == 1){
					for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
						if(DepartmentCache.departentList.get(i).getParent()==null){
							listBack.add(DepartmentCache.departentList.get(i));
						}
					}
					for (int i = 0; i < listBack.size(); i++) {
						for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
							if(listBack.get(i)==DepartmentCache.departentList.get(j).getParent()){
								if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") && DepartmentCache.departentList.get(j).getPriority().intValue()==1){
									list.add(DepartmentCache.departentList.get(j));
								}
							}
						}
					}
				}else{
					for (int i = 0; i < DepartmentCache.departentList.size(); i++) {
						if(DepartmentCache.departentList.get(i).getParent() != null && DepartmentCache.departentList.get(i).getParent().getId().intValue()==cmsDepartment.getParent().getId().intValue()){
							listBack.add(DepartmentCache.departentList.get(i));
						}
					}
					for (int i = 0; i < listBack.size(); i++) {
						for (int j = 0; j < DepartmentCache.departentList.size(); j++) {
							if(listBack.get(i)==DepartmentCache.departentList.get(j).getParent()){
								if(DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("机关党委") || DepartmentCache.departentList.get(j).getSddspoOrgtype().equals("党总支")){
									list.add(DepartmentCache.departentList.get(j));
								}
							}
						}
					}
					//list=manager.getList(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():null, false);
					//list=manager.findXtForJGDWByDeptId(cmsDepartment.getParent()!=null?cmsDepartment.getParent().getId():null);
				}
			}else{
				if(cmsDepartment.getParent() == null && departId != 1){
					int deptidd = cmsDepartmentDao.findChildrenFirstDeptidByPid(departId);
					list = manager.getList(deptidd, false);
				}else{
					list=manager.getList(departId, false);
				}
			}
		}
		model.addAttribute("ischildren",isnullorg);
		model.addAttribute("list", list);
		model.addAttribute("user",user);
		
		try{
			//改造开始
			String channelName = null;
			//组装strIds
			String strIds = "(";
			if(list.size()<0){
				strIds += String.valueOf(departId);
			}else{
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setScore(0.0);
					if(i==list.size()-1){
						strIds+=("'"+list.get(i).getId()+"'");
					}else {
						strIds+=("'"+list.get(i).getId()+"',");
					}	
				}
			}
			strIds=strIds+")";
			model.addAttribute("strIds",strIds );
			//根据idList查询对应总分记录 -》scoreList
			List<Object[]> scoreList=risenDao.getListse(strIds, channelName, startDate, endDate);
			List<Object[]> scoreListMonth=risenDao.getListseMonth(strIds, channelName);
			/*
			if(root!=null &&root.intValue()==1){
				//机关栏目
				String tpxw="图片新闻";
				String zbdt="支部动态";
				String dfjl="党费缴纳";
				String dwgk="党务公开";
				String tsgz="特色工作";
				//下面list是为了取各个栏目的分数《取机关》
				List<Object[]> ListChTp =risenDao.getListCha(strIds, tpxw, startDate, endDate);
				List<Object[]> ListChZ =risenDao.getListCha(strIds, zbdt, startDate, endDate);
				List<Object[]> ListChDF=risenDao.getListCha(strIds, dfjl, startDate, endDate);
				List<Object[]> ListChDW=risenDao.getListCha(strIds, dwgk, startDate, endDate);
				List<Object[]> ListChT =risenDao.getListCha(strIds, tsgz, startDate, endDate);
				//机关取图片新闻
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChTp.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChTp.get(j)[0]).toString())){
							list.get(i).setTpxw(Double.parseDouble(ListChTp.get(j)[1].toString()));
						}
					}
				}
				//机关取支部动态
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChZ.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChZ.get(j)[0]).toString())){
							list.get(i).setZbdt(Double.parseDouble(ListChZ.get(j)[1].toString()));
						}
					}
				}
				//机关取党费缴纳
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDF.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDF.get(j)[0]).toString())){
							list.get(i).setDfjl(Double.parseDouble(ListChDF.get(j)[1].toString()));
						}
					}
				}
				//机关取党务公开
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDW.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDW.get(j)[0]).toString())){
							list.get(i).setDwgk(Double.parseDouble(ListChDW.get(j)[1].toString()));
						}
					}
				}
				//机关取特色工作
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChT.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChT.get(j)[0]).toString())){
							list.get(i).setTsgz(Double.parseDouble(ListChT.get(j)[1].toString()));
						}
					}
				}
		  }else{
		  */
			String tpxw="图片新闻";
			String djdt="党建动态";
			String jyjl="经验交流";
			String mtxc="媒体视点";
			String llyt="理论研讨";
			String wsrys="网上荣誉室";
			//下面list是为了取各个栏目的分数《取系统》
			//需求发生变更    之前是按照文章原始栏目得分  现在变更为 按照共享给的栏目得分
			/*
			List<Object[]> ListCha =risenDao.getListCha(strIds, tpxw, startDate, endDate);			
			List<Object[]> ListChaD=risenDao.getListCha(strIds, djdt, startDate, endDate);
			List<Object[]> ListChaJ=risenDao.getListCha(strIds, jyjl, startDate, endDate);
			List<Object[]> ListChM =risenDao.getListCha(strIds, mtxc, startDate, endDate);
			List<Object[]> ListChaL=risenDao.getListCha(strIds, llyt, startDate, endDate);
			List<Object[]> ListChW =risenDao.getListCha(strIds, wsrys, startDate, endDate);
			*/
			List<Object[]> ListCha =risenDao.getListChaNow(strIds, String.valueOf(departId),tpxw, startDate, endDate);			
			List<Object[]> ListChaD=risenDao.getListChaNow(strIds, String.valueOf(departId),djdt, startDate, endDate);
			List<Object[]> ListChaJ=risenDao.getListChaNow(strIds, String.valueOf(departId),jyjl, startDate, endDate);
			List<Object[]> ListChM =risenDao.getListChaNow(strIds, String.valueOf(departId),mtxc, startDate, endDate);
			List<Object[]> ListChaL=risenDao.getListChaNow(strIds, String.valueOf(departId),llyt, startDate, endDate);
			List<Object[]> ListChW =risenDao.getListChaNow(strIds, String.valueOf(departId),wsrys, startDate, endDate);
			//取出图片新闻栏目分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListCha.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListCha.get(j)[0]).toString())){
						list.get(i).setTpxw(Double.parseDouble(ListCha.get(j)[1].toString()));
					}
				}
			}
			//取出党建动态
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setDjdt(0.0);
				for (int j = 0; j < ListChaD.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaD.get(j)[0]).toString())){
						list.get(i).setDjdt(Double.parseDouble(ListChaD.get(j)[1].toString()));
					}
				}
			}
			//	取出经验交流分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChaJ.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaJ.get(j)[0]).toString())){
						list.get(i).setJyjl(Double.parseDouble(ListChaJ.get(j)[1].toString()));
					}
				}
			}
//			取出媒体视点分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChM.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChM.get(j)[0]).toString())){
						list.get(i).setMtxc(Double.parseDouble(ListChM.get(j)[1].toString()));
					}
				}
			}
//			取出理论研讨分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChaL.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaL.get(j)[0]).toString())){
						list.get(i).setLlyt(Double.parseDouble(ListChaL.get(j)[1].toString()));
					}
				}
			}
//			取出网上荣誉室分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChW.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChW.get(j)[0]).toString())){
						list.get(i).setWsrys(Double.parseDouble(ListChW.get(j)[1].toString()));
					}
				}
			}
		  //}	

			//改造结束
			
			//双重for循环 给list里面的每个bean设置积分，从scoreList 
			//（if list.get(i).id=scoreList.get(j).id   list.get(i).setXXX(scoreList.get(j).getScore())）
			//Score -》月度积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setScore(0.0);
				for (int j = 0; j < scoreListMonth.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreListMonth.get(j)[0]).toString())){
						String ss = scoreListMonth.get(j)[1].toString();
						list.get(i).setScore(new Double(ss));
					}
				}
			}
			//pid -> 总积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setPid(0.0);
				for (int j = 0; j < scoreList.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
						list.get(i).setPid(Double.parseDouble(scoreList.get(j)[1].toString()));
					}
				}
			}
			CmsDepartment[]  dms = new CmsDepartment[list.size()];
			for (int i=0; i<list.size(); i++) {
				dms[i] = list.get(i);
			}
			
			//list 排序
			Arrays.sort(dms, new Comparator<CmsDepartment>(){
				@Override
				public int compare(CmsDepartment d1, CmsDepartment d2) {
					double deptScore = 0;
					double compareDeptScore = 0;
					deptScore = d1.getScore()==null?0:d1.getScore().doubleValue();
					compareDeptScore = d2.getScore()==null?0:d2.getScore().doubleValue();
					double distance = (compareDeptScore-deptScore)*10;
					return (int)distance;
				}
			});
			
			list.clear();
			for (int i=0; i<dms.length; i++) {
				list.add(dms[i]);
			}
			
			for(int i = 0; i <list.size(); i++) {
				list.get(i).setGroupid(i+1);
			}
			
			model.addAttribute("parentId", root);
			//list2方便页面echarts显示图start
			String xs="[";
			String datas="[";
			for (int i = 0; i < list.size(); i++) {
				if(i==list.size()-1){
					xs+=("'"+list.get(i).getName()+"'");
					datas+=(list.get(i).getScore());
				}else{
					xs+=("'"+list.get(i).getName()+"',");
					datas+=(list.get(i).getScore()+",");
				}	
			}
			xs=xs+"]";
			datas=datas+"]"; 
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("xs",xs );
			model.addAttribute("datas", datas);
			model.addAttribute("channelId", channelId);
			model.addAttribute("departId", departId);
			//list2方便页面echarts显示图end
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pbd/integrallistIndex";
	}
	
	@RequiresPermissions("department:xtlistLevelForIndex")
	@RequestMapping("/department/xtlistLevelForIndex")
	public String xtlistLevelForIndex(Integer root, HttpServletRequest request,ModelMap model,String startDate, String endDate,Integer departId ) {
		List<CmsDepartment> list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = user.getDepartment();
		if(departId==null){
			departId = cmsDepartment.getId();
		}
		cmsDepartment = cmsDepartmentDao.findById(departId);
		if(cmsDepartment.getSddspoOrgtype().equals("支部")){
			list.add(cmsDepartment);
		}else{
			if(root == null || "".equals(root)){
				if(departId==1){
					list = manager.getAllJgdwDept();
				}else{
					boolean isShiju = cmsDepartment.getName().endsWith("市局机关党委")?true:false;
					list = manager.getAllJgdwDeptById(departId, isShiju);
				}
			}else{
				list = manager.getAllZhiBuById(departId);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("user",user);
		try{
			//组装strIds
			String strIds = "(";
			if(list.size()<0){
				strIds += String.valueOf(departId);
			}else{
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setScore(0.0);
					if(i==list.size()-1){
						strIds+=("'"+list.get(i).getId()+"'");
					}else {
						strIds+=("'"+list.get(i).getId()+"',");
					}	
				}
			}
			strIds=strIds+")";
			model.addAttribute("strIds",strIds );
			//根据idList查询对应总分记录 -》scoreList
			List<Object[]> scoreList=risenDao.getListse(strIds, "", startDate, endDate);
			List<Object[]> scoreListMonth=risenDao.getListseMonth(strIds, "");
			if(root!=null &&root.intValue()==1){
				//机关栏目
				String zbdt="支部动态";
				String dfjl="党费缴纳";
				String dwgk="党务公开";
				String tsgz="特色工作";
				//下面list是为了取各个栏目的分数《取机关》
				List<Object[]> ListChZ =risenDao.getListCha(strIds, zbdt, startDate, endDate);
				List<Object[]> ListChDF=risenDao.getListCha(strIds, dfjl, startDate, endDate);
				List<Object[]> ListChDW=risenDao.getListCha(strIds, dwgk, startDate, endDate);
				List<Object[]> ListChT =risenDao.getListCha(strIds, tsgz, startDate, endDate);
				//机关取
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChZ.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChZ.get(j)[0]).toString())){
							list.get(i).setZbdt(Double.parseDouble(ListChZ.get(j)[1].toString()));
						}
					}
				}
				//机关取党费缴纳
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDF.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDF.get(j)[0]).toString())){
							list.get(i).setDfjl(Double.parseDouble(ListChDF.get(j)[1].toString()));
						}
					}
				}
				//机关取党务公开
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDW.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDW.get(j)[0]).toString())){
							list.get(i).setDwgk(Double.parseDouble(ListChDW.get(j)[1].toString()));
						}
					}
				}
				//机关取特色工作
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChT.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChT.get(j)[0]).toString())){
							list.get(i).setTsgz(Double.parseDouble(ListChT.get(j)[1].toString()));
						}
					}
				}
		  }else{
			String tpxw="图片新闻";
			String djdt="党建动态";
			String jyjl="经验交流";
			String mtxc="媒体视点";
			String llyt="理论研讨";
			String wsrys="网上荣誉室";
			//下面list是为了取各个栏目的分数《取系统》
			List<Object[]> ListCha =risenDao.getListCha(strIds, tpxw, startDate, endDate);			
			List<Object[]> ListChaD=risenDao.getListCha(strIds, djdt, startDate, endDate);
			List<Object[]> ListChaJ=risenDao.getListCha(strIds, jyjl, startDate, endDate);
			List<Object[]> ListChM =risenDao.getListCha(strIds, mtxc, startDate, endDate);
			List<Object[]> ListChaL=risenDao.getListCha(strIds, llyt, startDate, endDate);
			List<Object[]> ListChW =risenDao.getListCha(strIds, wsrys, startDate, endDate);
			//取出图片新闻栏目分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListCha.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListCha.get(j)[0]).toString())){
						list.get(i).setTpxw(Double.parseDouble(ListCha.get(j)[1].toString()));
					}
				}
			}
			//取出党建动态
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setDjdt(0.0);
				for (int j = 0; j < ListChaD.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaD.get(j)[0]).toString())){
						list.get(i).setDjdt(Double.parseDouble(ListChaD.get(j)[1].toString()));
					}
				}
			}
			//	取出经验交流分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChaJ.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaJ.get(j)[0]).toString())){
						list.get(i).setJyjl(Double.parseDouble(ListChaJ.get(j)[1].toString()));
					}
				}
			}
//			取出媒体视点分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChM.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChM.get(j)[0]).toString())){
						list.get(i).setMtxc(Double.parseDouble(ListChM.get(j)[1].toString()));
					}
				}
			}
//			取出理论研讨分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChaL.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChaL.get(j)[0]).toString())){
						list.get(i).setLlyt(Double.parseDouble(ListChaL.get(j)[1].toString()));
					}
				}
			}
//			取出网上荣誉室分数
			for(int i = 0; i <list.size(); i++) {	
				for (int j = 0; j < ListChW.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((ListChW.get(j)[0]).toString())){
						list.get(i).setWsrys(Double.parseDouble(ListChW.get(j)[1].toString()));
					}
				}
			}
		  }	
			//改造结束
			
			//双重for循环 给list里面的每个bean设置积分，从scoreList 
			//（if list.get(i).id=scoreList.get(j).id   list.get(i).setXXX(scoreList.get(j).getScore())）
			//Score -》月度积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setScore(0.0);
				for (int j = 0; j < scoreListMonth.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreListMonth.get(j)[0]).toString())){
						String ss = scoreListMonth.get(j)[1].toString();
						list.get(i).setScore(new Double(ss));
					}
				}
			}
			//pid -> 总积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setPid(0.0);
				for (int j = 0; j < scoreList.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
						list.get(i).setPid(Double.parseDouble(scoreList.get(j)[1].toString()));
					}
				}
			}
			CmsDepartment[]  dms = new CmsDepartment[list.size()];
			for (int i=0; i<list.size(); i++) {
				dms[i] = list.get(i);
			}
			
			//list 排序
			Arrays.sort(dms, new Comparator<CmsDepartment>(){
				@Override
				public int compare(CmsDepartment d1, CmsDepartment d2) {
					double deptScore = 0;
					double compareDeptScore = 0;
					deptScore = d1.getScore()==null?0:d1.getScore().doubleValue();
					compareDeptScore = d2.getScore()==null?0:d2.getScore().doubleValue();
					double distance = (compareDeptScore-deptScore)*10;
					return (int)distance;
				}
			});
			
			list.clear();
			for (int i=0; i<dms.length; i++) {
				list.add(dms[i]);
			}
			
			for(int i = 0; i <list.size(); i++) {
				list.get(i).setGroupid(i+1);
			}
			int startPosition = 0;              //倒数第五位开始的位置
			if(list.size()>5){
				int firstPosition = list.size()-5;  //开始位置
				int lastPosition = list.size()-1;   //结束位置
				if(list.get(firstPosition)!=null && (list.get(lastPosition)!=null)){
					if(list.get(firstPosition).equals(list.get(lastPosition))){
						for (int j = 0; j < list.size(); j++) {
							if(list.get(j).equals(list.get(firstPosition))){
								startPosition = j;
								break;
							}
						}
					}else{
						startPosition = firstPosition;
					}
				}else if(list.get(firstPosition)!=null && (list.get(lastPosition)==null)){
					startPosition = firstPosition;
				}else if(list.get(firstPosition)==null){
					for (int j = 0; j < list.size(); j++) {
						if(list.get(j).equals(list.get(firstPosition))){
							startPosition = j;
							break;
						}
					}
				}
			}
			Double backFiveScore = 0.0;
			if(list.get(startPosition)!=null){
				backFiveScore = new Double(list.get(startPosition).getScore());
			}
			
			model.addAttribute("parentId", root);
			//list2方便页面echarts显示图start
			String xs="[";
			String datas="[";
			for (int i = 0; i < list.size(); i++) {
				if(i==list.size()-1){
					xs+=("'"+list.get(i).getName()+"'");
					datas+=(list.get(i).getScore());
				}else{
					xs+=("'"+list.get(i).getName()+"',");
					datas+=(list.get(i).getScore()+",");
				}	
			}
			xs=xs+"]";
			datas=datas+"]"; 
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("xs",xs );
			model.addAttribute("datas", datas);
			model.addAttribute("departId", departId);
			//list2方便页面echarts显示图end
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pbd/integrallistIndex";
	}
	
	/**
	 * 支部后台首页加载此方法
	 * @param root
	 * @param contentid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("department:v_list_xtIndexzhibu")
	@RequestMapping("/department/v_list_xtIndexzhibu.do")
	public String xtlistForIndexzhibu(Integer root, HttpServletRequest request,ModelMap model,String channelId,String startDate, String endDate,String chaN ) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment cmsDepartment = new CmsDepartment();
		cmsDepartment = user.getDepartment().getParent();
		boolean isnullorg = manager.findlistByPid(cmsDepartment.getId());
		if(isnullorg){
			if("机关党委".equals(list.get(0).getSddspoOrgtype()) || "党总支".equals(list.get(0).getSddspoOrgtype())){
				list = manager.getList(cmsDepartment.getParent()!=null?cmsDepartment.getParent().getId():0, false);
			}
		}else {
			if(root == null || "".equals(root)){
				list=manager.getList(cmsDepartment.getId(), false);
				int[] pid = new int[list.size()];
				for(int i=0; i<list.size(); i++){
					pid[i] = list.get(i).getId();
				}
				boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
				if(!ischa && cmsDepartment.getPriority() != 1){
					isnullorg = true;
					list=manager.getList(cmsDepartment.getId(), false);
					list.add(0, cmsDepartment);
				}else if(cmsDepartment.getParent() == null && cmsDepartment.getId() != 1){
					
				}else if(cmsDepartment.getId() == 1){
					list=cmsDepartmentDao.findAdminDept();
				}else{
					//list=manager.getList(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():null, false);
					list=manager.findXtForJGDWByDeptId(cmsDepartment.getParent()!=null?cmsDepartment.getParent().getId():null);
					list.add(0, cmsDepartment);
				}
			}else{
				if(cmsDepartment.getParent() == null && cmsDepartment.getId() != 1){
					int deptidd = cmsDepartmentDao.findChildrenFirstDeptidByPid(cmsDepartment.getId());
					list = manager.getList(deptidd, false);
				}else{
					list=manager.getList(cmsDepartment.getId(), false);
				}
			}
			for(int i=0;i<list.size();i++){
				if(cmsDepartment.getId().intValue() == list.get(i).getId().intValue()){
					list.remove(i);
					break;
				}
			}
		}
		model.addAttribute("ischildren",isnullorg);
		model.addAttribute("list", list);
		model.addAttribute("user",user);
		
		try{
			
			//改造开始
			String channelName = null;
			/*前台掉此栏目，找到对应栏目细分数据（此功能屏蔽）
			//如果前台传入的channelId为null，channelName就为null，否则channelName换成中文
			if(channelId==null){
				channelName=null;
			}else if ("1".equals(channelId)) {
				channelName="图片新闻";
			}else if ("2".equals(channelId)) {
				channelName="党建动态";
			}else if ("3".equals(channelId)) {
				channelName="经验交流";
			}else if ("4".equals(channelId)) {
				channelName="媒体宣传";
			}else if ("5".equals(channelId)) {
				channelName="理论研讨";
			}else if ("6".equals(channelId)) {
				channelName="网上荣誉室";
			}else if ("7".equals(channelId)&& root==1){
				channelName="支部动态";
			}else if ("8".equals(channelId)&& root==1){
				channelName="党费缴纳";
			}else if ("9".equals(channelId)&& root==1){
				channelName="党务公开";
			}else if ("10".equals(channelId)&& root==1){
				channelName="特色工作";
			}
			*/
			//组装strIds
			String strIds = "(";
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setScore(0.0);
				if(i==list.size()-1){
					strIds+=("'"+list.get(i).getId()+"'");
				}else {
					strIds+=("'"+list.get(i).getId()+"',");
				}	
			}
			strIds=strIds+")";
			model.addAttribute("strIds",strIds );
			//根据idList查询对应总分记录 -》scoreList
			List<Object[]> scoreList=risenDao.getListse(strIds, channelName, startDate, endDate);
			List<Object[]> scoreListMonth=risenDao.getListseMonth(strIds, channelName);
			if(root!=null &&root.intValue()==1){
				//机关栏目
				String tpxw="图片新闻";
				String zbdt="支部动态";
				String dfjl="党费缴纳";
				String dwgk="党务公开";
				String tsgz="特色工作";
				//下面list是为了取各个栏目的分数《取机关》
				List<Object[]> ListChTp =risenDao.getListCha(strIds, tpxw, startDate, endDate);
				List<Object[]> ListChZ =risenDao.getListCha(strIds, zbdt, startDate, endDate );
				List<Object[]> ListChDF=risenDao.getListCha(strIds, dfjl, startDate, endDate );
				List<Object[]> ListChDW=risenDao.getListCha(strIds, dwgk, startDate, endDate );
				List<Object[]> ListChT =risenDao.getListCha(strIds, tsgz, startDate, endDate );
				//机关取图片新闻
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChTp.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChTp.get(j)[0]).toString())){
							list.get(i).setTpxw(Double.parseDouble(ListChTp.get(j)[1].toString()));
						}
					}
				}
				//机关取支部动态
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChZ.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChZ.get(j)[0]).toString())){
							list.get(i).setZbdt(Double.parseDouble(ListChZ.get(j)[1].toString()));
						}
					}
				}
				//机关取党费缴纳
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDF.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDF.get(j)[0]).toString())){
							list.get(i).setDfjl(Double.parseDouble(ListChDF.get(j)[1].toString()));
						}
					}
				}
				//机关取党务公开
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChDW.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChDW.get(j)[0]).toString())){
							list.get(i).setDwgk(Double.parseDouble(ListChDW.get(j)[1].toString()));
						}
					}
				}
				//机关取特色工作
				for(int i = 0; i <list.size(); i++) {	
					for (int j = 0; j < ListChT.size(); j++) {
						if(list.get(i).getId().intValue() == Integer.parseInt((ListChT.get(j)[0]).toString())){
							list.get(i).setTsgz(Double.parseDouble(ListChT.get(j)[1].toString()));
						}
					}
				}
		  }
			//Score -》月度积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setScore(0.0);
				for (int j = 0; j < scoreListMonth.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreListMonth.get(j)[0]).toString())){
						String ss = scoreListMonth.get(j)[1].toString();
						list.get(i).setScore(new Double(ss));
					}
				}
			}
			//pid -> 总积分
			for(int i = 0; i <list.size(); i++) {	
				list.get(i).setPid(0.0);
				for (int j = 0; j < scoreList.size(); j++) {
					if(list.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
						list.get(i).setPid(Double.parseDouble(scoreList.get(j)[1].toString()));
					}
				}
			}
			CmsDepartment[]  dms = new CmsDepartment[list.size()];
			for (int i=0; i<list.size(); i++) {
				dms[i] = list.get(i);
			}
			
			//list 排序
			Arrays.sort(dms, new Comparator<CmsDepartment>(){
				@Override
				public int compare(CmsDepartment d1, CmsDepartment d2) {
					double deptScore = 0;
					double compareDeptScore = 0;
					deptScore = d1.getScore()==null?0:d1.getScore().doubleValue();
					compareDeptScore = d2.getScore()==null?0:d2.getScore().doubleValue();
					double distance = (compareDeptScore-deptScore)*10;
					return (int)distance;
				}
			});
			
			list.clear();
			for (int i=0; i<dms.length; i++) {
				list.add(dms[i]);
			}
			
			for(int i = 0; i <list.size(); i++) {
				list.get(i).setGroupid(i+1);
			}
			
			model.addAttribute("parentId", root);
			//list2方便页面echarts显示图start
			String xs="[";
			String datas="[";
			for (int i = 0; i < list.size(); i++) {
				if(i==list.size()-1){
					xs+=("'"+list.get(i).getName()+"'");
					datas+=(list.get(i).getScore());
				}else{
					xs+=("'"+list.get(i).getName()+"',");
					datas+=(list.get(i).getScore()+",");
				}	
			}
			xs=xs+"]";
			datas=datas+"]"; 
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("xs",xs );
			model.addAttribute("datas", datas);
			model.addAttribute("channelId", channelId);
			//list2方便页面echarts显示图end
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pbd/integrallistIndexzhibu";
	}
	@RequiresPermissions("department:v_list_qc")
	@RequestMapping("/department/v_list_qc.do")
	public String qdlist(Integer root,String contentid, HttpServletRequest request,ModelMap model) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		boolean isnullorg = manager.findlistByPid(user.getDepartment().getId());
		//此判断会把党组党建工作指导组添加进来 然而 工作指导组不能登录
		if(isnullorg){
			list.add(manager.findById(user.getDepartment().getId()));
		}else{
			if(root == null || "".equals(root)){
				if(depart.getId()==1){
					list=manager.getTypeList(1, "机关党委");
				}else{
					Integer parentId = depart.getParent().getId();
					list = manager.getTypeList(parentId,"机关党委");
				}
			}else{
				if(depart.getId()==1){
					list=manager.getList(1, false);
				}else{
					Integer zbParentId = depart.getId();
					boolean isShiju = true;
					if(depart.getParent().getParent()!=null){
						isShiju = false;
					}
					if(isShiju){
						list = manager.getList(zbParentId,false);
					}else{
						Integer parentId = depart.getParent().getId();
						list = manager.getTypeList(parentId,"支部");
					}
				}
			}
		}
		/*
		if(isnullorg){
			list.add(manager.findById(user.getDepartment().getId()));
		}else {
			if(root == null || "".equals(root)){
				list=manager.getList(user.getDepartment().getId(), false);
				int[] pid = new int[list.size()];
				for(int i=0; i<list.size(); i++){
					pid[i] = list.get(i).getId();
				}
				boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
				if(!ischa && user.getDepartment().getPriority() != 1){
					isnullorg = true;
					list=manager.getList(user.getDepartment().getId(), false);
					list.add(0, user.getDepartment());
				}else if(user.getDepartment().getParent() == null && user.getDepartment().getId() != 1){
					
				}else if(user.getDepartment().getId() == 1){
					list=cmsDepartmentDao.findAdminDept();
				}else{
					list=manager.getList(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():null, false);
				}
			}else{
				if(user.getDepartment().getParent() == null && user.getDepartment().getId() != 1){
					int deptidd = cmsDepartmentDao.findChildrenFirstDeptidByPid(user.getDepartment().getId());
					list = manager.getList(deptidd, false);
				}else{
					list=manager.getList(user.getDepartment().getId(), false);
				}
			}
		}
		*/
		Integer [] listid = new Integer[list.size()];
		for(int i=0;i<list.size();i++){
			listid[i] = list.get(i).getId();
		}
		List<RisenSignfor> listSignfor = risenSignfordDao.findModelByDeptListAndContent(listid, contentid);
		Content cont = contentDao.findById(Integer.parseInt(contentid));
		for(int i=0;i<list.size();i++){
			list.get(i).setTempContentname(cont.getContentExt().getTitle());
			for(int b=0;b<listSignfor.size();b++){
				if(list.get(i).getId().equals(listSignfor.get(b).getRisensfDept().getId())){
					list.get(i).setTempQd("已经签收");
				}
			}
		}
		model.addAttribute("contentid",contentid);
		model.addAttribute("ischildren",isnullorg);
		model.addAttribute("list", list);
		model.addAttribute("parentId", root);
		return "signfor/list";
	}
	/**
     * 查看换届
     */
	
	@RequestMapping("/checkChangelist/checkChangelist.do")
    public String checkChangelist (HttpServletRequest request,ModelMap model,Integer departId){
		CmsDepartment depart = null;
		if(departId!=null){
			depart = manager.findById(departId);
		}else{
			depart = CmsUtils.getUser(request).getDepartment();
		}
		String departName = depart.getName();
    	List<CmsDepartment> list = manager.checkChangelist(departName);
    	model.addAttribute("list", list);
    	return "changeremind/checkChange";
    }
	@RequestMapping("/department/findNopidList.do")
	public String findNopidList(HttpServletRequest request,ModelMap model){
		CmsUser user = CmsUtils.getUser(request);
		boolean isadmin = false;
		//boolean isself = false;
		if("admin".equals(user.getUsername())){
			isadmin = true;
		}
		List<CmsDepartment> list = manager.findNopidList();
		model.addAttribute("list", list);
		model.addAttribute("isadmin", isadmin);
		//model.addAttribute("isself", isself);
		return "department/list";
	}
	/**
     * 查看制定组织下是否存在指定名称的组织
     */
	@RequestMapping("/checkChangelist/checkExistDept.do")
	public void checkExist(HttpServletRequest request,HttpServletResponse response,String deptId,String deptName){
		List<CmsDepartment> depts = manager.getAllDeptByIdAndName(deptId,deptName);
		try{
			if(depts.size()==0){
				response.getWriter().write("noexist");
			}else{
				response.getWriter().write("exist");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//List排序
	public List<Object[]> sortList(List<Object[]> list,List<CmsDepartment> departs){
		String departIds = "";
		for (int i = 0; i < departs.size(); i++) {
			departIds += (departs.get(i)+",");
		}
		departIds = departIds.substring(0,departIds.length()-1);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i)[0].toString().indexOf(departIds)!=-1){
					if(list.get(i)[0].toString().indexOf(departIds)==0){
						departIds.replace(list.get(i)[0].toString(), "");
					}else{
						departIds.replace(","+list.get(i)[0].toString(), "");
					}
				}
			}
		}
		if(departIds.length()!=0){
			for(int i = 0,j=departIds.split(",").length;i<j;i++){
				list.add(new Object[]{departIds.split(",")[i],0.0});
			}
		}
		return list;
	}
	//得到添加的表头
	public void addShell(Integer root,Label label,WritableCellFormat font,WritableSheet sheet) throws RowsExceededException, WriteException{
		if(root==null){
			label = new Label(0, 1, "序号", font);
			sheet.setColumnView(0,10);
			sheet.addCell(label);
			label = new Label(1, 1, "组织名称", font);
			sheet.setColumnView(1,40);
			sheet.addCell(label);
			label = new Label(2, 1, "图片新闻", font);
			sheet.setColumnView(2,15);
			sheet.addCell(label);
			label = new Label(3, 1, "党建动态", font);
			sheet.setColumnView(3,15);
			sheet.addCell(label);
			label = new Label(4, 1, "经验交流", font);
			sheet.setColumnView(4,15);
			sheet.addCell(label);
			label = new Label(5, 1, "媒体视点", font);
			sheet.setColumnView(5,15);
			sheet.addCell(label);
			label = new Label(6, 1, "理论研讨", font);
			sheet.setColumnView(6,15);
			sheet.addCell(label);
			label = new Label(7, 1, "网上荣誉室", font);
			sheet.setColumnView(7,15);
			sheet.addCell(label);
			label = new Label(8, 1, "其他", font);
			sheet.setColumnView(8,15);
			sheet.addCell(label);
			label = new Label(9, 1, "本月积分", font);
			sheet.setColumnView(9,15);
			sheet.addCell(label);
			label = new Label(10, 1, "总分", font);
			sheet.setColumnView(10,15);
			sheet.addCell(label);
		}else{
			label = new Label(0, 1, "序号", font);
			sheet.setColumnView(0,10);
			sheet.addCell(label);
			label = new Label(1, 1, "组织名称", font);
			sheet.setColumnView(1,40);
			sheet.addCell(label);
			label = new Label(2, 1, "图片新闻", font);
			sheet.setColumnView(2,15);
			sheet.addCell(label);
			label = new Label(3, 1, "支部动态", font);
			sheet.setColumnView(3,15);
			sheet.addCell(label);
			label = new Label(4, 1, "党费缴纳", font);
			sheet.setColumnView(4,15);
			sheet.addCell(label);
			label = new Label(5, 1, "党务公开", font);
			sheet.setColumnView(5,15);
			sheet.addCell(label);
			label = new Label(6, 1, "特色工作", font);
			sheet.setColumnView(6,15);
			sheet.addCell(label);
			label = new Label(7, 1, "其他", font);
			sheet.setColumnView(7,15);
			sheet.addCell(label);
			label = new Label(8, 1, "本月积分", font);
			sheet.setColumnView(8,15);
			sheet.addCell(label);
			label = new Label(9, 1, "总分", font);
			sheet.setColumnView(9,15);
			sheet.addCell(label);
		}
	}
	//添加 表中数据
	public void addExcelData(Integer root,List<CmsDepartment> depts,Label label,WritableCellFormat font,WritableSheet sheet) throws RowsExceededException, WriteException{
		if(root==null){
			for(int i=0; i < depts.size(); i++){
				CmsDepartment dept = depts.get(i);
				label = new Label(0,(i+2),(i+1)+"",font);
				sheet.addCell(label);
				label = new Label(1,(i+2),dept.getName(),font);
				sheet.addCell(label);
				double totalScore = (dept.getPid()==null)? 0 : dept.getPid().doubleValue();
				double tpxw = (dept.getTpxw()==null)? 0 : dept.getTpxw().doubleValue();
				double djdt = (dept.getDjdt()==null) ? 0:dept.getDjdt().doubleValue();
				double jyjl = (dept.getJyjl()==null) ? 0:dept.getJyjl().doubleValue();
				double mtsd = (dept.getMtxc()==null) ? 0 : dept.getMtxc().doubleValue();
				double wsrys = (dept.getWsrys()==null) ? 0 : dept.getWsrys().doubleValue();
				double llyt = (dept.getLlyt()==null)? 0 : dept.getLlyt().doubleValue();
				double score = (dept.getScore()==null)? 0 : dept.getScore().doubleValue();
				label = new Label(2,(i+2),String.valueOf(tpxw),font);
				sheet.addCell(label);
				label = new Label(3,(i+2),String.valueOf(djdt),font);
				sheet.addCell(label);
				label = new Label(4,(i+2),String.valueOf(jyjl),font);
				sheet.addCell(label);
				label = new Label(5,(i+2),String.valueOf(mtsd),font);
				sheet.addCell(label);
				label = new Label(6,(i+2),String.valueOf(llyt),font);
				sheet.addCell(label);
				label = new Label(7,(i+2),String.valueOf(wsrys),font);
				sheet.addCell(label);
				String otherScore = ""+(totalScore - tpxw - djdt - jyjl - mtsd - wsrys - llyt);
				label = new Label(8,(i+2),otherScore,font);
				sheet.addCell(label);
				label = new Label(9,(i+2),String.valueOf(score),font);
				sheet.addCell(label);
				label = new Label(10,(i+2),String.valueOf(totalScore),font);
				sheet.addCell(label);
			}
		}else{
			for(int i=0; i < depts.size(); i++){
				CmsDepartment dept = depts.get(i);
				label = new Label(0,(i+2),(i+1)+"",font);
				sheet.addCell(label);
				label = new Label(1,(i+2),dept.getName(),font);
				sheet.addCell(label);
				double totalScore = dept.getPid()==null ? 0 : dept.getPid().doubleValue();
				double tpxw = dept.getTpxw()==null ? 0 : dept.getTpxw().doubleValue();
				double zbdt = dept.getZbdt()==null ? 0 : dept.getZbdt().doubleValue();
				double dfjn = dept.getDfjl()==null ? 0 : dept.getDfjl().doubleValue();
				double tsgz = dept.getTsgz()==null ? 0 : dept.getTsgz().doubleValue();
				double gwgk = dept.getDwgk()==null ? 0 : dept.getDwgk().doubleValue();
				double score = dept.getScore()==null ? 0 : dept.getScore().doubleValue();
				label = new Label(2,(i+2),String.valueOf(tpxw),font);
				sheet.addCell(label);
				label = new Label(3,(i+2),String.valueOf(zbdt),font);
				sheet.addCell(label);
				label = new Label(4,(i+2),String.valueOf(dfjn),font);
				sheet.addCell(label);
				label = new Label(5,(i+2),String.valueOf(gwgk),font);
				sheet.addCell(label);
				label = new Label(6,(i+2),String.valueOf(tsgz),font);
				sheet.addCell(label);
				
				String otherScore = ""+(totalScore - tpxw - zbdt - dfjn - tsgz - gwgk);
				label = new Label(7,(i+2),otherScore.substring(0,3),font);
				sheet.addCell(label);
				label = new Label(8,(i+2),String.valueOf(score),font);
				sheet.addCell(label);
				label = new Label(9,(i+2),String.valueOf(totalScore),font);
				sheet.addCell(label);
			}
		}
	}
	//得到列表展示的数据
	public List<CmsDepartment> getDeptsData(Integer root,String startDate,String endDate,Integer deptId){
		List<CmsDepartment> depts = new ArrayList<CmsDepartment>();
		if(root==null){
			if(deptId==1){
				depts = manager.getAllJgdwDept();
			}else{
				CmsDepartment dept = manager.findById(deptId);
				CmsDepartment pdept = dept.getParent();
				Integer pDeptId = pdept.getId();
				String deptName = dept.getName();
				if(deptName.endsWith("市局机关党委")){
					depts = cmsDepartmentDao.getAllJgdwDeptById(pDeptId,true);
				}else{
					depts = cmsDepartmentDao.getAllJgdwDeptById(pDeptId,false);
				}
			}
		}else{
			CmsDepartment dept = manager.findById(deptId);
			Integer pDeptId = deptId;
			if(deptId!=1 && !dept.getName().endsWith("市局机关党委")){
				CmsDepartment pdept = dept.getParent();
				pDeptId = pdept.getId();
			}
			depts = cmsDepartmentDao.getAllZhiBuById(pDeptId);
		}
		//组装strIds
		String strIds = "(";
		for (int i = 0; i < depts.size(); i++) {
			depts.get(i).setScore(0.0);
			if(i==depts.size()-1){
				strIds+=("'"+depts.get(i).getId()+"'");
			}else {
				strIds+=("'"+depts.get(i).getId()+"',");
			}	
		}
		strIds=strIds+")";
		List<Object[]> scoreList=risenDao.getListse(strIds, "", startDate, endDate);
		List<Object[]> scoreListMonth=risenDao.getListseMonth(strIds, "");
		if(root!=null &&root.intValue()==1){
			//机关栏目
			String zbdt="支部动态";
			String dfjl="党费缴纳";
			String dwgk="党务公开";
			String tsgz="特色工作";
			//下面list是为了取各个栏目的分数《取机关》
			List<Object[]> ListChZ =risenDao.getListCha(strIds, zbdt, startDate, endDate);
			List<Object[]> ListChDF=risenDao.getListCha(strIds, dfjl, startDate, endDate);
			List<Object[]> ListChDW=risenDao.getListCha(strIds, dwgk, startDate, endDate);
			List<Object[]> ListChT =risenDao.getListCha(strIds, tsgz, startDate, endDate);
			//机关取
			for(int i = 0; i <depts.size(); i++) {
				depts.get(i).setZbdt(0.0);
				for (int j = 0; j < ListChZ.size(); j++) {
					if(depts.get(i).getId().intValue() == Integer.parseInt((ListChZ.get(j)[0]).toString())){
						depts.get(i).setZbdt(Double.parseDouble(ListChZ.get(j)[1].toString()));
					}
				}
			}
			//机关取党费缴纳
			for(int i = 0; i <depts.size(); i++) {	
				depts.get(i).setDfjl(0.0);
				for (int j = 0; j < ListChDF.size(); j++) {
					if(depts.get(i).getId().intValue() == Integer.parseInt((ListChDF.get(j)[0]).toString())){
						depts.get(i).setDfjl(Double.parseDouble(ListChDF.get(j)[1].toString()));
					}
				}
			}
			//机关取党务公开
			for(int i = 0; i <depts.size(); i++) {
				depts.get(i).setDwgk(0.0);
				for (int j = 0; j < ListChDW.size(); j++) {
					if(depts.get(i).getId().intValue() == Integer.parseInt((ListChDW.get(j)[0]).toString())){
						depts.get(i).setDwgk(Double.parseDouble(ListChDW.get(j)[1].toString()));
					}
				}
			}
			//机关取特色工作
			for(int i = 0; i <depts.size(); i++) {	
				depts.get(i).setTsgz(0.0);
				for (int j = 0; j < ListChT.size(); j++) {
					if(depts.get(i).getId().intValue() == Integer.parseInt((ListChT.get(j)[0]).toString())){
						depts.get(i).setTsgz(Double.parseDouble(ListChT.get(j)[1].toString()));
					}
				}
			}
	  }else{
		String tpxw="图片新闻";
		String djdt="党建动态";
		String jyjl="经验交流";
		String mtxc="媒体视点";
		String llyt="理论研讨";
		String wsrys="网上荣誉室";
		//下面list是为了取各个栏目的分数《取系统》
		List<Object[]> ListCha =risenDao.getListCha(strIds, tpxw, startDate, endDate);			
		List<Object[]> ListChaD=risenDao.getListCha(strIds, djdt, startDate, endDate);
		List<Object[]> ListChaJ=risenDao.getListCha(strIds, jyjl, startDate, endDate);
		List<Object[]> ListChM =risenDao.getListCha(strIds, mtxc, startDate, endDate);
		List<Object[]> ListChaL=risenDao.getListCha(strIds, llyt, startDate, endDate);
		List<Object[]> ListChW =risenDao.getListCha(strIds, wsrys, startDate, endDate);
		//取出图片新闻栏目分数
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListCha.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListCha.get(j)[0]).toString())){
					depts.get(i).setTpxw(Double.parseDouble(ListCha.get(j)[1].toString()));
				}
			}
		}
		//取出党建动态
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListChaD.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListChaD.get(j)[0]).toString())){
					depts.get(i).setDjdt(Double.parseDouble(ListChaD.get(j)[1].toString()));
				}
			}
		}
		//	取出经验交流分数
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListChaJ.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListChaJ.get(j)[0]).toString())){
					depts.get(i).setJyjl(Double.parseDouble(ListChaJ.get(j)[1].toString()));
				}
			}
		}
//		取出媒体视点分数
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListChM.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListChM.get(j)[0]).toString())){
					depts.get(i).setMtxc(Double.parseDouble(ListChM.get(j)[1].toString()));
				}
			}
		}
//		取出理论研讨分数
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListChaL.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListChaL.get(j)[0]).toString())){
					depts.get(i).setLlyt(Double.parseDouble(ListChaL.get(j)[1].toString()));
				}
			}
		}
//		取出网上荣誉室分数
		for(int i = 0; i <depts.size(); i++) {	
			for (int j = 0; j < ListChW.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((ListChW.get(j)[0]).toString())){
					depts.get(i).setWsrys(Double.parseDouble(ListChW.get(j)[1].toString()));
				}
			}
		}
	  }	
		//双重for循环 给list里面的每个bean设置积分，从scoreList 
		//（if list.get(i).id=scoreList.get(j).id   list.get(i).setXXX(scoreList.get(j).getScore())）
		//Score -》月度积分
		for(int i = 0; i <depts.size(); i++) {	
			depts.get(i).setScore(0.0);
			for (int j = 0; j < scoreListMonth.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((scoreListMonth.get(j)[0]).toString())){
					depts.get(i).setScore(Double.valueOf(scoreListMonth.get(j)[1].toString()));
				}
			}
		}
		//pid -> 总积分
		for(int i = 0; i <depts.size(); i++) {	
			depts.get(i).setPid(0.0);
			for (int j = 0; j < scoreList.size(); j++) {
				if(depts.get(i).getId().intValue() == Integer.parseInt((scoreList.get(j)[0]).toString())){
					depts.get(i).setPid(Double.parseDouble(scoreList.get(j)[1].toString()));
				}
			}
		}
		CmsDepartment[]  dms = new CmsDepartment[depts.size()];
		for (int i=0; i<depts.size(); i++) {
			dms[i] = depts.get(i);
		}
		
		//list 排序
		Arrays.sort(dms, new Comparator<CmsDepartment>(){
			@Override
			public int compare(CmsDepartment d1, CmsDepartment d2) {
				double deptScore = 0;
				double compareDeptScore = 0;
				deptScore = d1.getScore()==null?0:d1.getScore().doubleValue();
				compareDeptScore = d2.getScore()==null?0:d2.getScore().doubleValue();
				double distance = (compareDeptScore-deptScore)*10;
				return (int)distance;
			}
		});
		
		depts.clear();
		for (int i=0; i<dms.length; i++) {
			depts.add(dms[i]);
		}
		
		for(int i = 0; i <depts.size(); i++) {
			depts.get(i).setGroupid(i+1);
		}
		return depts;
	}
	//找到开始位置
	public int getStartPosition(List<Object[]> list){
		int startPosition = 0;
		//先针对list排序
		Collections.sort(list, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				// TODO Auto-generated method stub
				double score1 = Double.parseDouble(o1[1].toString());
				double score2 = Double.parseDouble(o2[1].toString());
				int distance = (int)(score2 - score1)*10;
				return distance;
			}
		});
		//得到所有的不重复分数
		String scores = "";
		for (int i = 0,j=list.size();i<j;i++) {
			if(list.get(i)[1].toString().indexOf(scores)==-1){
				scores = scores + list.get(i)[1].toString() + ",";
			}
		}
		scores = scores.substring(0,scores.length()-1);
		String[] scoreArray = scores.split(",");
		//得到最后5名分数的开始位置
		if(scoreArray.length<5){
			startPosition = 0;
		}else{
			startPosition = scoreArray.length-5;
			for (int i = 0,j=list.size();i<j;i++) {
				if(list.get(i)[1].toString().equals(scoreArray[startPosition])){
					startPosition = i;
					break;
				}
			}
		}
		return startPosition;
	}
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsDepartmentMng manager;
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private CmsSiteMng cmsSiteMng;
	@Autowired
	private CmsGuestbookCtgMng guestBookCtgMng;
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private CmsDepartmentDao cmsDepartmentDao;
	@Autowired
	private IRisenSignforDao risenSignfordDao;
	@Autowired
	private ContentDao contentDao;
	@Autowired
	private IRisenIntegralDao risenDao;
}