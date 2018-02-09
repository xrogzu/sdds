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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.action.admin.main.ContentAct;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.CmsModel;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentShareCheck;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsModelItemMng;
import com.jeecms.cms.manager.main.CmsTopicMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.ContentShareCheckMng;
import com.jeecms.cms.manager.main.ContentTypeMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.tpl.TplManager;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.CoreUtils;
import com.risen.dao.IRisenIntegralRecordDao;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;
import com.risen.entity.RisenJxjfAssess;
import com.risen.entity.RisenReject;
import com.risen.service.IRisenIntegralRecordService;
import com.risen.service.IRisenIntegralService;
import com.risen.service.IRisenRejectService;
import com.risen.service.RisenJxjfAssessMng;

@Controller
public class RisenPartyBuildPerformanceAct {
	private static final Logger log = LoggerFactory.getLogger(RisenPartyBuildPerformanceAct.class);

	@RequiresPermissions("partyBuild:v_list")
	@RequestMapping("/partyBuild/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean) {
		//CmsUser user = CmsUtils.getUser(request);
		bean.setRisenirTargetorgid(CmsUtils.getUser(request).getDepartment().getId());
		bean.setRisenirResult(1);
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
					.getPageSize(request),bean);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "pbd/list";
	} 
	//<积分记录>查看各栏目共享后产生的分数
	@RequiresPermissions("partyBuild:v_list")
	@RequestMapping("/partyBuild/list/v_list.do")
	public String scoreList(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean) {
		//CmsUser user = CmsUtils.getUser(request);
		bean.setRisenirTargetorgid(CmsUtils.getUser(request).getDepartment().getId());
		bean.setRisenirResult(1);
		try{
			List<RisenIntegralRecord> list = manager.getPageNum(cpn(pageNo), request, CookieUtils
				.getPageSize(request), pageNo, bean);
		model.addAttribute("list",list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pbd/score_list";
	}
	@RequiresPermissions("partyBuild:shareContent")
	@RequestMapping("/partyBuild/shareContent.do")
	public String shareContent(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean){
		Integer deptId = null;
		if(org.apache.commons.lang.StringUtils.isBlank(request.getParameter("deptId"))){
			deptId = CmsUtils.getUser(request).getDepartment().getId();
		}else{
			deptId = new Integer(request.getParameter("deptId"));
		}
		bean.setRisenirTargetorgid(deptId);
		bean.setRisenirResult(0);
		Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request),bean);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("deptId",deptId);
		return "shareContent/list";
	}
	//已共享内容
	@RequiresPermissions("partyBuild:shareContent")
	@RequestMapping("/partyBuild/shareContenths.do")
	public String shareContenths(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean){
		Integer deptId = null;
		if(org.apache.commons.lang.StringUtils.isBlank(request.getParameter("deptId"))){
			deptId = CmsUtils.getUser(request).getDepartment().getId();
		}else{
			deptId = new Integer(request.getParameter("deptId"));
		}
		bean.setRisenirTargetorgid(deptId);
		bean.setRisenirResult(1);
		try{
			Pagination pagination = manager.getPagehs(cpn(pageNo), request, CookieUtils
					.getPageSize(request),deptId, bean);
			model.addAttribute("pagination",pagination);
			model.addAttribute("pageNo",pagination.getPageNo());
			model.addAttribute("deptId",deptId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "shareContent/hshared_list";
	}
	//页面查看所有积分（积分记录调此方法）
	@RequiresPermissions("partyBuild:shareContent")
	@RequestMapping("/partyBuild/shareContenthsMonth.do")
	public String shareContenthsMonth(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean,Integer status){
		Integer deptId = null;
		if(org.apache.commons.lang.StringUtils.isBlank(request.getParameter("deptId"))){
			deptId = CmsUtils.getUser(request).getDepartment().getId();
		}else{
			deptId = new Integer(request.getParameter("deptId"));
		}
		String startDate = "";
		String endDate = "";
		if(StringUtils.hasText(request.getParameter("startDate"))){
			startDate = request.getParameter("startDate");
		}
		if(StringUtils.hasText(request.getParameter("endDate"))){
			endDate = request.getParameter("endDate");
		}
		try{
			Pagination pagination = manager.getScoresByDeptIdAndDate(cpn(pageNo), request, CookieUtils
					.getPageSize(request),deptId, startDate,endDate,status);
			model.addAttribute("pagination",pagination);
			model.addAttribute("pageNo",pagination.getPageNo());
			model.addAttribute("deptId",deptId);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
			model.addAttribute("status",status);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "shareContent/hshared_list";
	}

	// 页面查看所有积分（积分记录调此方法）
	@RequiresPermissions("partyBuild:shareFrontContent")
	@RequestMapping("/partyBuild/shareFrontContenthsMonth.do")
	public String shareFrontContenthsMonth(Integer pageNo,
			HttpServletRequest request, ModelMap model,
			RisenIntegralRecord bean, Integer status) {
		Integer deptId = null;
		if (org.apache.commons.lang.StringUtils.isBlank(request
				.getParameter("deptId"))) {
			deptId = CmsUtils.getUser(request).getDepartment().getId();
		} else {
			deptId = new Integer(request.getParameter("deptId"));
		}
		String startDate = "";
		String endDate = "";
		if (StringUtils.hasText(request.getParameter("startDate"))) {
			startDate = request.getParameter("startDate");
		}
		if (StringUtils.hasText(request.getParameter("endDate"))) {
			endDate = request.getParameter("endDate");
		}
		try {
			Pagination pagination = manager.getScoresFrontByDeptIdAndDate(
					cpn(pageNo), request, CookieUtils.getPageSize(request),
					deptId, startDate, endDate,status);
			model.addAttribute("pagination", pagination);
			model.addAttribute("pageNo", pagination.getPageNo());
			model.addAttribute("deptId", deptId);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shareContent/myShared_list";
	}

	//页面查看所有积分（积分记录调此方法）
		@RequiresPermissions("partyBuild:getShareContents")
		@RequestMapping("/partyBuild/getShareContents.do")
		public String getShareContents(Integer pageNo, HttpServletRequest request, ModelMap model,RisenIntegralRecord bean){
			Integer deptId = CmsUtils.getUser(request).getDepartment().getId();
			if(StringUtils.hasText(request.getParameter("deptId"))){
				deptId = new Integer(request.getParameter("deptId"));
			}
			String startDate = "";
			String endDate = "";
			if(StringUtils.hasText(request.getParameter("startDate"))){
				startDate = request.getParameter("startDate");
			}
			if(StringUtils.hasText(request.getParameter("endDate"))){
				endDate = request.getParameter("endDate");
			}
			bean.setRisenirOrgid(deptId);
			try{
				Pagination pagination = manager.getScoresByDeptIdAndDate(cpn(pageNo), request, CookieUtils
						.getPageSize(request),deptId,startDate,endDate,1 );
				model.addAttribute("pagination",pagination);
				model.addAttribute("pageNo",pagination.getPageNo());
				model.addAttribute("deptId",deptId);
				model.addAttribute("startDate",startDate);
				model.addAttribute("endDate",endDate);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "shareContent/myShared_list";
		}

	// 页面查看所有积分（积分记录调此方法）
	@RequiresPermissions("partyBuild:getShareContentsFront")
	@RequestMapping("/partyBuild/getFrontShareContents.do")
	public String getShareFrontContents(Integer pageNo, HttpServletRequest request,
			ModelMap model, RisenIntegralRecord bean) {
		Integer deptId = CmsUtils.getUser(request).getDepartment().getId();
		if (StringUtils.hasText(request.getParameter("deptId"))) {
			deptId = new Integer(request.getParameter("deptId"));
		}
		String startDate = "";
		String endDate = "";
		if (StringUtils.hasText(request.getParameter("startDate"))) {
			startDate = request.getParameter("startDate");
		}
		if (StringUtils.hasText(request.getParameter("endDate"))) {
			endDate = request.getParameter("endDate");
		}
		bean.setRisenirOrgid(deptId);
		try {
			Pagination pagination = manager.getScoresFrontByDeptIdAndDate(
					cpn(pageNo), request, CookieUtils.getPageSize(request),
					deptId, startDate, endDate, 1);
			model.addAttribute("pagination", pagination);
			model.addAttribute("pageNo", pagination.getPageNo());
			model.addAttribute("deptId", deptId);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shareContent/myShared_list";
	}

	@RequiresPermissions("partyBuild:confirmShare")
	@RequestMapping("/partyBuild/confirmShare.do")
	@ResponseBody
	public Object confirmShare(Integer[] ids,Integer type,Double score, HttpServletRequest request){
		ContentShareCheck shareCheck;
		if(ids!=null&&ids.length>0){
			for(Integer pid:ids){
				RisenIntegralRecord model = manager.findById(pid);
				//先让驳回表的数据通过
				Integer contentId = model.getRisenirContentid();
				Integer departId = model.getRisenirOrgid();
				List<RisenReject> rejects = rejectService.getListByDepartIdAndContentId(departId, contentId);
				if(rejects!=null && rejects.size()>0){
					int updateRecord = rejectService.changeStatus(departId, contentId);
					if(updateRecord<1){
						return 0;
					}
				}
				int id = model.getRisenirSharecheck();
				shareCheck=contentShareCheckMng.findById(id);
				//非本站源内容 而且是待审核的共享信息
				if(shareCheck.getCheckStatus()==ContentShareCheck.CHECKING){
					shareCheck.setCheckStatus(ContentShareCheck.CHECKED);
					
					shareCheck.setShareValid(true);
					contentShareCheckMng.update(shareCheck);
				}
				Content content = shareCheck.getContent();
				if(content != null && content.getType().getId() == 4){
					content.setType(contentTypeMng.findById(1));
					//content.setTopLevel((byte)0);// 将固顶级别置为0，目的是为了防止上级共享之后，固顶很高。
					//content.set ;// 将文章类型置为普通，防止上级共享后，内容是焦点或头条或图文。
					contentMng.update(content);
				}
			}
			return manager.confirmShare(ids,type,score);
		}else{
			return 0;
		}
	}
	//前台修改积分后进此方法
	@RequiresPermissions("partyBuild:confirmSharehs")
	@RequestMapping("/partyBuild/confirmSharehs.do")
	@ResponseBody
	public Object confirmSharehs(Integer[]ids,Integer type,Double score, HttpServletRequest request){
		ContentShareCheck shareCheck;
		if(ids!=null&&ids.length>0){
			for(Integer pid:ids){
				RisenIntegralRecord model = manager.findById(pid);
				int id = model.getRisenirSharecheck();
				try{
					shareCheck=contentShareCheckMng.findById(id);
					byte status = 1;
					if(shareCheck==null){
						ContentShareCheck check = new ContentShareCheck();
						Content content = contentMng.findById(new Integer(model.getRisenirContentid()));
						check.setChannel(content.getChannel());
						check.setContent(content);
						check.setCheckStatus(status);
						check.setShareValid(true);
						contentShareCheckMng.save(check);
						//重新赋值ShareCheckID
						model.setRisenirSharecheck(check.getId());
						manager.update(model);
					}else{
						shareCheck.setCheckStatus(status);
						shareCheck.setShareValid(true);
						contentShareCheckMng.update(shareCheck);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return manager.confirmShare(ids,type,score);
	}
	@RequiresPermissions("partyBuild:partybuild_main")
	@RequestMapping("/partyBuild/report.do")
	public String report(ModelMap model, HttpServletRequest request, String root){
		CmsUser user = CmsUtils.getUser(request);
	//	List<CmsDepartment>list=new ArrayList<CmsDepartment>();
	//	boolean isnullorg = deptMng.findlistByPid(user.getDepartment().getId());
		if(StringUtils.hasText(root)){
			model.addAttribute("root", root);
		}
	/*	list=deptMng.getList(user.getDepartment().getId(), false);
		int[] pid = new int[list.size()];
		for(int i=0; i<list.size(); i++){
			pid[i] = list.get(i).getId();
		}
		boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
		if(!ischa && user.getDepartment().getPriority() != 1){
			isnullorg = true;
		}
		model.addAttribute("ischildren",isnullorg);*/
		return "report/list";
	}
	//支部进入后台查看首页掉此方法
	@RequiresPermissions("partyBuild:partybuild_main")
	@RequestMapping("/partyBuild/reportzhibu.do")
	public String reportZB(ModelMap model, HttpServletRequest request, String root){
		CmsUser user = CmsUtils.getUser(request);
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		boolean isnullorg = deptMng.findlistByPid(user.getDepartment().getId());
		if(StringUtils.hasText(root)){
			model.addAttribute("root", root);
		}
		list=deptMng.getList(user.getDepartment().getId(), false);
		int[] pid = new int[list.size()];
		for(int i=0; i<list.size(); i++){
			pid[i] = list.get(i).getId();
		}
		boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
		if(!ischa && user.getDepartment().getPriority() != 1){
			isnullorg = true;
		}
		model.addAttribute("ischildren",isnullorg);
		return "report/listzhibu";
	}
	@RequiresPermissions("partyBuild:partybuild_main")
	@RequestMapping("/partyBuild/partybuild_main.do")
	public String partybuild_main(){
		return "pbd/partybuild_main";
	}
	
	@RequiresPermissions("partyBuild:v_list_share")
	@RequestMapping("/partyBuild/v_list_share.do")
	public String v_list_share(){
		return "shareContent/partybuild_main";
	}
	
	@RequiresPermissions("partyBuild:v_left")
	@RequestMapping("/partyBuild/v_left.do")
	public String left(){
		return "pbd/left";
	}
	
	@RequiresPermissions("partyBuild:share_left")
	@RequestMapping("/partyBuild/share_left.do")
	public String share_left(){
		return "shareContent/left";
	}
	
	@RequiresPermissions("partyBuild:v_add")
	@RequestMapping("/partyBuild/v_add.do")
	public String add(ModelMap model) {
		return "pbd/add";
	}
	@RequiresPermissions("partyBuild:baseScore")
	@RequestMapping("/partyBuild/baseScore.do")
	public String baseScore(String root, ModelMap model,HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		CmsUser user = CmsUtils.getUser(request);
		CmsDepartment depart = user.getDepartment();
		List<CmsDepartment> xtDept = new ArrayList<CmsDepartment>();
		List<CmsDepartment> jgDept = new ArrayList<CmsDepartment>();
		String type=request.getParameter("type");
		if("admin".equals(user.getUsername()) || ("省局机关党委".equals(user.getUsername()))){
			xtDept = deptMng.getTypeList(1,"机关党委");
			jgDept = deptMng.getList(1,false);
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
			xtDept = deptMng.getTypeList(parentId,"机关党委");
			if(isShiju){
				jgDept = deptMng.getList(zbParentId,false);
			}else{
				jgDept = deptMng.getTypeList(parentId,"支部");
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
			list = integralManager.getYearInfo(year,xtDepartId);
		}else{
			list = integralManager.getYearInfo(year,zbDepartId);
		}
		model.addAttribute("list", list);
		model.addAttribute("year", year);
		return "pbd/baseScore";
	}
	@RequiresPermissions("partyBuild:jxjfassess")
	@RequestMapping("/partyBuild/jxjfassess.do")
	public String jxjfassess(HttpServletRequest request,ModelMap model){
		try {
			CmsUser user = CmsUtils.getUser(request);
			List<RisenJxjfAssess> list=jxpgMng.findbydepartpg(user.getDepartment().getId());
			model.addAttribute("list",list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "partybuild/jxjfassess";
	}
	@RequiresPermissions("partyBuild:jxjfpgyz")
	@RequestMapping("/partyBuild/jxjfpgyz.do")				
	public void jxjfpgyz(HttpServletResponse response, HttpServletRequest request ,String channelname){
		CmsUser user = CmsUtils.getUser(request);
		String flag=jxpgMng.findbychannelname(user.getDepartment().getId(), channelname);
		try {
			response.getWriter().write(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	@RequiresPermissions("partyBuild:jxpgeitd")
	@RequestMapping("/partyBuild/jxpgeitd.do")
	public Object jxpgeitd(HttpServletRequest request,Integer id,ModelMap model){
		RisenJxjfAssess jxpg=jxpgMng.findbyid(id);
		model.addAttribute("risenJxjfAssess",jxpg);
		return "partybuild/assess/eitd";
	}
	@RequiresPermissions("partyBuild:jxpgupdate")
	@RequestMapping("/partyBuild/jxpgupdate.do")
	public Object jxpgupdate(HttpServletRequest request,ModelMap model,RisenJxjfAssess rjxjf){
		CmsUser user = CmsUtils.getUser(request);
		jxpgMng.update(rjxjf);
		List<RisenJxjfAssess> list=jxpgMng.findbydepartpg(user.getDepartment().getId());
		model.addAttribute("list",list);
		return "partybuild/jxjfassess";
	}
	@RequiresPermissions("partyBuild:jxpgadd")
	@RequestMapping("/partyBuild/jxpgadd.do")
	public Object jxpgadd(HttpServletRequest request){
		return "partybuild/assess/add";
	}
	@RequiresPermissions("partyBuild:jxpgSave")
	@RequestMapping("/partyBuild/jxpgSave.do")
	public Object jxpgSave(HttpServletRequest request,RisenJxjfAssess rjxjf,ModelMap model){
		CmsUser user = CmsUtils.getUser(request);
		/*RisenJxjfAssess rjxjf = new RisenJxjfAssess();
		rjxjf.setCHANNELNAME(CHANNELNAME);
		rjxjf.setDISQUALIFICATIONEVALUATE(DISQUALIFICATIONEVALUATE);
		rjxjf.setEXCELLENTEVALUATE(EXCELLENTEVALUATE);
		rjxjf.setEXCELLENTSCORE(EXCELLENTSCORE);
		rjxjf.setPASSINGEVALUATE(PASSINGEVALUATE);
		rjxjf.setPASSINGSCORE(PASSINGSCORE);*/
		rjxjf.setDepartid(user.getDepartment().getId());
		jxpgMng.save(rjxjf);
		List<RisenJxjfAssess> list=jxpgMng.findbydepartpg(user.getDepartment().getId());
		model.addAttribute("list",list);
		return "partybuild/jxjfassess";
	}
	@RequestMapping(value="/partyBuild/getData.do")
	@ResponseBody
	public Object getReportDate(HttpServletRequest request, String root) {
		List<CmsDepartment>list=new ArrayList<CmsDepartment>();
		CmsUser user = CmsUtils.getUser(request);
		boolean isnullorg = deptMng.findlistByPid(user.getDepartment().getId());
		if(isnullorg){
			list.add(deptMng.findById(user.getDepartment().getId()));
			if("机关党委".equals(list.get(0).getSddspoOrgtype()) || "党总支".equals(list.get(0).getSddspoOrgtype())){
				list = deptMng.getList(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():0, false);
			}
		}else {
			if(root == null || "".equals(root)){
				list=deptMng.getList(user.getDepartment().getId(), false);
				int[] pid = new int[list.size()];
				for(int i=0; i<list.size(); i++){
					pid[i] = list.get(i).getId();
				}
				boolean ischa = cmsDepartmentDao.findChildrenIsBoolean(pid);
				if(!ischa && user.getDepartment().getPriority() != 1){
					isnullorg = true;
					list=deptMng.getList(user.getDepartment().getId(), false);
					list.add(0, user.getDepartment());
				}else if(user.getDepartment().getParent() == null && user.getDepartment().getId() != 1){
					
				}else if(user.getDepartment().getId() == 1){
					List<CmsDepartment> cdtList =deptMng.getList(null, false);
					List<CmsDepartment> returnList = new ArrayList<CmsDepartment>();
					for (CmsDepartment cmsDepartment : cdtList) {
						for (CmsDepartment child : cmsDepartment.getChild()) {
							if (child.getName().endsWith("局机关党委")) {
								returnList.add(child);
							}
						}
					}
					for (CmsDepartment de : returnList) {
						if(de.getId()==2637){
							returnList.remove(de);
							break;
						}
					}
					list = returnList;
				}else{
					//list=deptMng.getList(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():null, false);
					list=deptMng.findXtForJGDWByDeptId(user.getDepartment().getParent()!=null?user.getDepartment().getParent().getId():null);
					list.add(0, user.getDepartment());
				}
			}else{
				if(user.getDepartment().getParent() == null && user.getDepartment().getId() != 1){
					int deptidd = cmsDepartmentDao.findChildrenFirstDeptidByPid(user.getDepartment().getId());
					list = deptMng.getList(deptidd, false);
				}else{
					list=deptMng.getList(user.getDepartment().getId(), false);
					/**
					for (CmsDepartment cmsDepartment : list) {
						String deptName = cmsDepartment.getName();
						deptName = deptName.replace("党支部", "");
						cmsDepartment.setName(deptName);
					}
					*/
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		List<RisenIntegral> li = new ArrayList<RisenIntegral>();
			for(int i=0; i<list.size();i++){
				RisenIntegral ri = list.get(i).getRisenintegral();
				if(ri == null){
					RisenIntegral it = new RisenIntegral();
					
					it.setRisenitBase(1);
					it.setRisenitOrgid(list.get(i).getId());
					it.setRisenitOrgname(list.get(i).getName());
					it.setRisenitScore(new Double(0));
					it.setRisenitYear(new Integer(sdf.format(new Date())));
					it.setRisenitDesc("");
					it = itManager.save(it);
					CmsDepartment dept = list.get(i);
					dept.setRisenintegral(it);
					deptMng.update(dept);
					li.add(it);
				}else{
					RisenIntegral ril = new RisenIntegral();
					ril.setRisenitBase(ri.getRisenitBase());
					ril.setRisenitOrgname(ri.getRisenitOrgname());
					ril.setRisenitScore(ri.getRisenitScore());
					ril.setRisenitYear(new Integer(sdf.format(new Date())));
					ril.setRisenitDesc("");
					li.add(ril);
				}
				//System.out.println(ri.getRisenitOrgname()+"--"+ri.getRisenitScore());
			}
		return li;
	}
	@RequiresPermissions("partyBuild:v_edit")
	@RequestMapping("/partyBuild/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		RisenIntegralRecord bean=new RisenIntegralRecord();
		bean.setRisenirOrgid(id);
		//CmsUser user = CmsUtils.getUser(request);
				Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
							.getPageSize(request),bean);
				model.addAttribute("pagination",pagination);
				model.addAttribute("pageNo",pagination.getPageNo());
				
				return "pbd/list";
	}

	@RequiresPermissions("partyBuild:o_save")
	@RequestMapping("/partyBuild/o_save.do")
	public String save(RisenIntegralRecord bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		//CmsUser user = CmsUtils.getUser(request);
		
		bean = manager.save(bean);
		log.info("save partyBuild id={}", bean.getRisenirUuid());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("partyBuild:o_update")
	@RequestMapping("/partyBuild/o_update.do")
	public String update(RisenIntegralRecord bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getRisenirUuid(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = manager.update(bean);
		log.info("update partyBuild id={}.", bean.getRisenirUuid());
		return list(pageNo, request, model,null);
	}
	

	@RequiresPermissions("partyBuild:o_delete")
	@RequestMapping("/partyBuild/o_delete.do")
	public String delete(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		
		 manager.delete(id);
		 
			log.info("delete partyBuild id={}",id);
		
		return list(pageNo, request, model,null);
	}
	
	@Transactional
	@RequiresPermissions("partyBuild:o_deletenew")
	@RequestMapping("/partyBuild/o_deletenew.do")
	@ResponseBody
	public Object deleteNew(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<RisenIntegralRecord> list= risenIntegralRecordDao.findByIds(ids);
		Integer[]scids = new Integer[list.size()];
		for(int i=0; i<list.size(); i++){
			scids[i] = list.get(i).getRisenirSharecheck();
		}
		contentShareCheckMng.deleteByIds(scids);
		for(int i=0; i<ids.length;i++){
			manager.delete(ids[i]);
			//RisenIntegralRecord record =  manager.findById(ids[i]);
		}
		return 1;
	}
	
	private WebErrors validateSave(RisenIntegralRecord bean, HttpServletRequest request) {
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

	

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		RisenIntegralRecord entity = manager.findById(id);
		if(errors.ifNotExist(entity, RisenIntegralRecord.class, id)) {
			return true;
		}
//		if (!entity.getSite().getId().equals(siteId)) {
//			errors.notInSite(RisenIntegralRecord.class, id);
//			return true;
//		}
		return false;
	}
	@RequiresPermissions("partyBuild:updScore")
	@RequestMapping("/partyBuild/updScore.do")
	public void updateBaseScore(ModelMap model,HttpServletRequest request, HttpServletResponse response,Integer orgId,Integer score){
		RisenIntegral bean=itManager.findByOrgId(orgId);
		CmsDepartment dept = deptMng.findById(orgId);
		if(!StringUtils.isEmpty(bean)){//有记录
			itManager.updateBaseScore(orgId, score);
		}else{//没有记录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			bean=new RisenIntegral();
			bean.setRisenitOrgid(orgId);
			bean.setRisenitScore(new Double(0));
			bean.setRisenitBase(score);
			bean.setRisenitOrgname(dept.getName());
			bean.setRisenitYear(new Integer(sdf.format(new Date())));
			bean.setRisenitDesc("");
			itManager.save(bean);
		}
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequiresPermissions("partyBuild:updScore")
	@RequestMapping("/partyBuild/batchUpdScore.do")
	public String BatchUpdBaseScore(ModelMap model,HttpServletRequest request,String ids,Integer score){
		RisenIntegral bean=null;
		String [] id=ids.split(",");
		Integer[] orgids=new Integer[id.length];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		String type = "xt";
		CmsDepartment depart = cmsDepartmentDao.findById(new Integer(id[0]));
		if(depart.getSddspoOrgtype().equals("支部")){
			type = "jiguan";
		}
		for (int i = 0; i < id.length; i++) {
			orgids[i]=Integer.valueOf(id[i]);
			bean=itManager.findByOrgId(Integer.valueOf(Integer.valueOf(id[i])));
			if(StringUtils.isEmpty(bean)){//没有记录
				CmsDepartment dept=deptMng.findById(Integer.valueOf(id[i]));
				bean=new RisenIntegral();
				bean.setRisenitOrgid(Integer.valueOf(id[i]));
				bean.setRisenitOrgname(dept.getName());
				bean.setRisenitScore(new Double(0));
				bean.setRisenitBase(score);
				bean.setRisenitYear(new Integer(year));
				bean.setRisenitDesc("");
				itManager.save(bean);
			}
		}
		itManager.batchUpdBaseScore(orgids, score);
		return "redirect:../partyBuild/baseScore.do?type="+type;
		//return baseScore("",model,request);
	}
	
	private List<String> getTplContent(CmsSite site, CmsModel model, String tpl) {
		String sol = site.getSolutionPath();
		String tplPath = site.getTplPath();
		List<String> tplList = tplManager.getNameListByPrefix(model
				.getTplContent(sol, false));
		tplList = CoreUtils.tplTrim(tplList, tplPath, tpl);
		return tplList;
	}
	
	private List<String> getTplMobileContent(CmsSite site, CmsModel model, String tpl) {
		String sol = site.getMobileSolutionPath();
		String tplPath = site.getTplPath();
		List<String> tplList = tplManager.getNameListByPrefix(model
				.getTplContent(sol, false));
		tplList = CoreUtils.tplTrim(tplList, tplPath, tpl);
		return tplList;
	}
	
	private void addAttibuteForQuery(ModelMap model, String queryTitle,
			String queryInputUsername, String queryStatus, Integer queryTypeId,
			Boolean queryTopLevel, Boolean queryRecommend,
			Integer queryOrderBy, Integer pageNo) {
		if (!org.apache.commons.lang.StringUtils.isBlank(queryTitle)) {
			model.addAttribute("queryTitle", queryTitle);
		}
		if (!org.apache.commons.lang.StringUtils.isBlank(queryInputUsername)) {
			model.addAttribute("queryInputUsername", queryInputUsername);
		}
		if (queryTypeId != null) {
			model.addAttribute("queryTypeId", queryTypeId);
		}
		if (queryStatus != null) {
			model.addAttribute("queryStatus", queryStatus);
		}
		if (queryTopLevel != null) {
			model.addAttribute("queryTopLevel", queryTopLevel);
		}
		if (queryRecommend != null) {
			model.addAttribute("queryRecommend", queryRecommend);
		}
		if (queryOrderBy != null) {
			model.addAttribute("queryOrderBy", queryOrderBy);
		}
		if (pageNo != null) {
			model.addAttribute("pageNo", pageNo);
		}
	}
	
	
	@Autowired
	private IRisenIntegralService integralManager;
	@Autowired
	private IRisenIntegralRecordService manager;
	@Autowired
	private IRisenIntegralRecordDao risenIntegralRecordDao;
	@Autowired
	private IRisenIntegralService itManager;
	@Autowired
	private CmsDepartmentMng deptMng;
	@Autowired
	private ContentShareCheckMng contentShareCheckMng;
	@Autowired
	private CmsDepartmentDao cmsDepartmentDao;
	@Autowired
	private ContentTypeMng contentTypeMng;
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private TplManager tplManager;
	@Autowired
	private RisenJxjfAssessMng jxpgMng;
	@Autowired
	private IRisenRejectService rejectService;

}