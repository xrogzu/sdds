package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.IOException;
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

import com.risen.dao.IRisenIntegralDao;
import com.risen.entity.CmsAppealMail;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenTarget;
import com.risen.entity.RisenTargetDetail;
import com.ibm.db2.jcc.am.de;
import com.ibm.db2.jcc.t4.e;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.risen.service.IRisenIntegralService;
import com.risen.service.IRisenTargetDetailService;
import com.risen.service.IRisenTargetService;
import com.sun.star.i18n.reservedWords;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class RisenIntegralAction {
	private static final Logger log = LoggerFactory.getLogger(RisenIntegralAction.class);

	@RequiresPermissions("risenIntegral:range")
	@RequestMapping("/risenIntegral/range.do")
	public String list(String year,Integer pageNo, HttpServletRequest request, ModelMap model) {
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		List<CmsDepartment> xtDept = new ArrayList<CmsDepartment>();
		List<CmsDepartment> jgDept = new ArrayList<CmsDepartment>();
		if("admin".equals(user.getUsername()) || ("省局机关党委".equals(user.getUsername()))){
			xtDept = departmentService.getAllJgdwDept();
			jgDept = departmentService.getAllZhiBuById(1);
		}else{
			Integer parentId = depart.getParent().getId();
			Integer zbParentId = depart.getParent().getId();
			boolean isShiJu = true;
			if(depart.getParent().getParent()!=null){
				isShiJu = false;
			}
			if(depart.getParent().getParent()==null){
				zbParentId = depart.getId();
			}
			xtDept = departmentService.getAllJgdwDeptById(parentId,isShiJu);
			jgDept = departmentService.getAllZhiBuById(zbParentId);
		}
		String departId = "";
		if(xtDept!=null && (xtDept.size()>0)){
			for (CmsDepartment cmsDepartment : xtDept) {
				departId = departId + String.valueOf(cmsDepartment.getId())+",";
			}
			departId = departId.substring(0,departId.length()-1);
		}else{
			departId = String.valueOf(depart.getId());
		}
		if(jgDept!=null && (jgDept.size()>0)){
			for (CmsDepartment cmsDepartment : jgDept) {
				departId = departId + String.valueOf(cmsDepartment.getId())+",";
			}
			departId = departId.substring(0,departId.length()-1);
		}else{
			departId = String.valueOf(depart.getId());
		}
		List<RisenIntegral> pagination = manager.getPage(departId);
		model.addAttribute("pagination",pagination);
		return "risenIntegral/list";
	}
	
	@RequiresPermissions("risenIntegral:batchUpdScore")
	@RequestMapping("/risenIntegral/batchUpdScore.do")
	public String batchUpdScore(ModelMap model,HttpServletRequest request,String ids,Integer score,String year){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String[] id = ids.split(",");
		Integer[] orgids=new Integer[id.length];
		for (int i = 0; i < id.length; i++) {
			orgids[i]=Integer.valueOf(id[i]);
		}
		if(year==null||year==""){
			year = sdf.format(new Date());
		}
		manager.batchUpBaseScore(orgids, score);
		return "redirect:getOtherRule.do?year="+year;
	}
	
	@RequiresPermissions("risenIntegral:batchSave")
	@RequestMapping("/risenIntegral/batchSave.do")
	public void batchSave(HttpServletRequest request,HttpServletResponse response, ModelMap model,String year,String desc,String base){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if(year==null || year==""){
			year = sdf.format(new Date());
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		List<CmsDepartment> xtDept = new ArrayList<CmsDepartment>();
		List<CmsDepartment> jgDept = new ArrayList<CmsDepartment>();
		if("admin".equals(user.getUsername()) || ("省局机关党委".equals(user.getUsername()))){
			xtDept = departmentService.getAllJgdwDept();
			jgDept = departmentService.getAllZhiBuById(1);
		}else{
			Integer parentId = depart.getParent().getId();
			Integer zbParentId = parentId;
			boolean isShiju = true;
			if(depart.getParent().getParent()!=null){
				isShiju = false;
			}
			if(depart.getParent().getParent()==null){
				zbParentId = depart.getId();
			}
			xtDept = departmentService.getAllJgdwDeptById(parentId,isShiju);
			jgDept = departmentService.getAllZhiBuById(zbParentId);
		}
		saveOrUpdate(year, xtDept, desc, base);
		saveOrUpdate(year, jgDept, desc, base);
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequiresPermissions("risenIntegral:getRule")
	@RequestMapping("/risenIntegral/getRule.do")
	public void getRule(HttpServletRequest request,HttpServletResponse response,String uuid){
		if((uuid!=null) && (uuid!="")){
			RisenIntegral integral = manager.findById(new Integer(uuid));
			try{
				if(integral!=null){
					String desc = integral.getRisenitDesc();
					response.setContentType("text/html;charset=UTF-8"); 
					response.getWriter().write(desc);
				}else{
					response.getWriter().write("");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@RequiresPermissions("risenIntegral:getOtherRule")
	@RequestMapping("/risenIntegral/getOtherRule.do")
	public String getOtherRule(String year,String type,HttpServletRequest request,ModelMap model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if(year==null || (year=="")){
			year = sdf.format(new Date());
		}
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		List<CmsDepartment> xtDept = new ArrayList<CmsDepartment>();
		List<CmsDepartment> jgDept = new ArrayList<CmsDepartment>();
		if("admin".equals(user.getUsername()) || ("省局机关党委".equals(user.getUsername()))){
			xtDept = departmentService.getTypeList(1, "机关党委");
			jgDept = departmentService.getList(1,false);
		}else{
			Integer parentId = depart.getParent().getId();
			Integer zbParentId = parentId;
			boolean isShiju = true;
			if(depart.getParent().getParent()!=null){
				isShiju = false;
			}
			if(depart.getParent().getParent()==null){
				zbParentId = depart.getId();
			}
			xtDept = departmentService.getTypeList(parentId,"机关党委");
			if(isShiju){
				jgDept = departmentService.getList(zbParentId,false);
			}else{
				jgDept = departmentService.getTypeList(parentId,"支部");
			}
		}
		String xtDepartId = "";
		String zbDepartId = "";
		if(type==null || type==""){
			type="xt";
		}
		if(type.equals("xt")){
			if(xtDept!=null && (xtDept.size()>0)){
				for (CmsDepartment cmsDepartment : xtDept) {
					xtDepartId = xtDepartId + String.valueOf(cmsDepartment.getId())+",";
				}
				xtDepartId = xtDepartId.substring(0,xtDepartId.length()-1);
			}else{
				xtDepartId = String.valueOf(depart.getId());
			}
		}else{
			if(jgDept!=null && (jgDept.size()>0)){
				for (CmsDepartment cmsDepartment : jgDept) {
					zbDepartId = zbDepartId + String.valueOf(cmsDepartment.getId())+",";
				}
				zbDepartId = zbDepartId.substring(0,zbDepartId.length()-1);
			}else{
				zbDepartId = String.valueOf(depart.getId());
			}
		}
		List<RisenIntegral> list = new ArrayList<RisenIntegral>();
		if(type.equals("xt")){
			list = manager.getYearInfo(year,xtDepartId);
		}else{
			list = manager.getYearInfo(year,zbDepartId);
		}
		model.addAttribute("list", list);
		model.addAttribute("year", year);
		model.addAttribute("type", type);
		return "risenIntegral/otherYearInfo";
	}
	
	@RequiresPermissions("risenIntegral:updScore")
	@RequestMapping("/risenIntegral/updScore.do")
	public String updateBase(ModelMap model,HttpServletRequest request,Integer changeUuid,Integer base,String year){
		manager.changeBaseById(changeUuid, base);
		return "redirect:getOtherRule.do?year="+year;
	}
	
	@RequiresPermissions("risenIntegral:changeRule")
	@RequestMapping("/risenIntegral/changeRule.do")
	public void changeRules(String changeUuid,String ruleDesc,HttpServletResponse response){
		int changeResult = manager.changeRuleById(changeUuid,ruleDesc);
		try{
			if(changeResult>0){
				response.getWriter().write("success");
			}else{
				response.getWriter().write("failure");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveOrUpdate(String year,List<CmsDepartment> departs,String desc,String base){
		if(departs!=null && (departs.size()>0)){
			for (CmsDepartment cmsDepartment : departs) {
				boolean result = manager.existRecord(year, String.valueOf(cmsDepartment.getId()));
				RisenIntegral integral = null;
				if(result){
					integral = dao.getYearInfo(year, String.valueOf(cmsDepartment.getId())).get(0);
				}else{
					integral = new RisenIntegral();
				}
				integral.setRisenitOrgid(cmsDepartment.getId());
				integral.setRisenitOrgname(cmsDepartment.getName());
				integral.setRisenitScore(new Double(0));
				integral.setRisenitDesc(desc);
				integral.setRisenitBase(new Integer(base));
				integral.setRisenitYear(new Integer(year));
				if(result){
					manager.update(integral);
				}else{
					manager.save(integral);
				}
			}
		}
	}
	@Autowired
	private IRisenIntegralService manager;
	@Autowired
	private IRisenIntegralDao dao;
	@Autowired
	private CmsDepartmentMng departmentService;
}