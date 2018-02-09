package com.jeecms.cms.action.admin.main;

import static com.jeecms.common.page.SimplePage.cpn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentShareCheck;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.ContentShareCheckMng;
import com.jeecms.cms.manager.main.ContentTypeMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.dao.CmsSiteDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.dao.IRisenIntegralRecordDao;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;
import com.risen.service.IRisenIntegralRecordService;
import com.risen.service.IRisenIntegralService;

@Controller
public class CmsContentShareAct {
	@RequiresPermissions("content:v_tree_share")
	@RequestMapping(value = "/content/v_tree_share.do")
	public String treeShare(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		boolean isRoot;
		Integer cid = null;
		Integer sid = null;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
			// 站点id以s_开头
			if (root.startsWith("s_")) {
				sid = Integer.parseInt(root.split("s_")[1]);
			} else {
				cid = Integer.parseInt(root);
			}
		}
		CmsSite site1=CmsUtils.getSite(request);
		model.addAttribute("isRoot", isRoot);
		WebErrors errors = validateTree(root, request);
		if (errors.hasErrors()) {
			ResponseUtils.renderJson(response, "[]");
			return null;
		}
		List<CmsSite> siteList = cmsSiteMng.getList();
		// 共享针对的是将本站信息共享到其他站点
		siteList.remove(CmsUtils.getSite(request));
		List<Channel> list = null;
		if (isRoot) {
			model.addAttribute("list", siteList);
		} else {
			if (sid != null) {
				list = channelMng.getTopList(sid, true);
			} else {
				list = channelMng.getChildList(cid, true);
			}
			model.addAttribute("list", list);
		}

		model.addAttribute("siteList", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "content/tree_share";
	}

		@RequiresPermissions("content:v_tree_share_new")
		@RequestMapping(value = "/content/v_tree_share_new.do")
		public String treeShareNew(String root, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			boolean isRoot;
			Integer cid = null;
			if (StringUtils.isBlank(root) || "source".equals(root)) {
				isRoot = true;
			} else {
				isRoot = false;
				if (root.startsWith("s_")) {
					cid = Integer.parseInt(root.split("s_")[1]);
				} else {
					cid = Integer.parseInt(root);
				}
			}
			model.addAttribute("isRoot", isRoot);

			CmsUser user=CmsUtils.getUser(request);

			// 共享针对的是将本站信息共享到其他站点
			List<Channel> list = new ArrayList<Channel>();
			CmsDepartment dept = user.getDepartment();
			if(isRoot){
				//Integer deptpid= dept.getParent()==null?null:dept.getParent().getId();
				Integer deptpid =null;
				if(dept.getParent()!=null){
					deptpid = dept.getParent().getId();// 获取当前登录帐号的上级的党组织号码
				}
				//CmsDepartment ppdept = deptpid==null?null:cmsDepartmentMng.findById(deptpid);
				CmsDepartment ppdept = null;
				if(deptpid!=null){
					ppdept = cmsDepartmentMng.findById(deptpid);
				}
				//Integer ppdeptid = ppdept==null?null:ppdept.getParent()==null?null:ppdept.getParent().getId();
				Integer ppdeptid = null;
				if(ppdept!=null){
                    if(ppdept.getParent()!=null){
                        ppdeptid = ppdept.getParent().getId();
                    }
				}
				int dqchanid = channelDao.findChannelidByDepartid(dept.getId());// 根据当前登录帐号的id查栏目id
				Channel dqchane = channelDao.findById(dqchanid);
				boolean isfrist = dqchane==null?false:dqchane.getParent()==null?false:dqchane.getName().equals(dqchane.getParent().getName())?true:false;
				if(ppdeptid == null && (dept.getPriority()==1||deptpid==null)){
					Channel chan = channelDao.findById(95);
					Channel chan2 = channelDao.findById(96);
					Channel chan3 = channelDao.findById(97);
					Channel chan4 = channelDao.findById(98);
					Channel chan5 = channelDao.findById(99);
					Channel chan6 = channelDao.findById(100);
					list.add(chan);
					list.add(chan2);
					list.add(chan3);
					list.add(chan4);
					list.add(chan5);
					list.add(chan6);
				}else if(ppdeptid == null){
					if(deptpid==1){
						Channel chan = channelDao.findById(95);
						Channel chan2 = channelDao.findById(96);
						Channel chan3 = channelDao.findById(97);
						Channel chan4 = channelDao.findById(98);
						Channel chan5 = channelDao.findById(99);
						Channel chan6 = channelDao.findById(100);
						list.add(chan);
						list.add(chan2);
						list.add(chan3);
						list.add(chan4);
						list.add(chan5);
						list.add(chan6);
					}else{
						Integer deptone = cmsDepartmentDao.findChildrenFirstDeptidByPid(deptpid);
						int chanid = channelDao.findChannelidByDepartid(deptone);
						Channel cha = channelDao.findById(chanid);
						list.add(cha);
					}
				}else if(isfrist){
					Integer depttwo = cmsDepartmentDao.findChildrenFirstDeptidByPid(dept.getParent().getParent().getId());
					int chanid = channelDao.findChannelidByDepartid(depttwo);
					String channelName = channelDao.findById(chanid).getName();
					List<Channel> chanList = channelDao.findByChannelName(channelName);
					boolean p = false;
					for (Channel channe1 : chanList) {
						for (Channel channe2 : chanList) {
							if(channe1.getId() == (channe2.getParent()==null?0:channe2.getParent().getId())){
								list.add(channe2);
								p = true;
								break;
							}
						}
						if(p){
							break; 
						}
					}
				}else{
					int chanid = channelDao.findChannelidByDepartid(dept.getParent().getId());
					String channelName = channelDao.findById(chanid).getName();
					List<Channel> chanList = channelDao.findByChannelName(channelName);
					boolean p = false;
					for (Channel channe1 : chanList) {
						for (Channel channe2 : chanList) {
							if(channe1.getId() == (channe2.getParent()==null?0:channe2.getParent().getId())){
								list.add(channe2);
								p = true;
								break;
							}
						}
						if(p){
							break; 
						}
					}
				}
			}else{
				list = channelMng.getChildList(cid, true);
			}

			model.addAttribute("list", list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json;charset=UTF-8");
			return "content/tree_share";
		}
	
	@RequiresPermissions("content:o_share")
	@RequestMapping("/content/o_share.do")
	public void share(Integer contentIds[], Integer channelIds[],
			HttpServletRequest request,HttpServletResponse response) throws JSONException {
		JSONObject json = new JSONObject();
		Boolean pass = true;
		if (contentIds != null && channelIds != null) {
			ContentShareCheck shareCheck;
			Content content;
			for (Integer contentId : contentIds) {
				content = manager.findById(contentId);
				for (Integer channelId : channelIds) {
					List<ContentShareCheck> li = contentShareCheckMng.getList(contentId, channelId);
					shareCheck = new ContentShareCheck();
					//共享待审
					shareCheck.setCheckStatus(ContentShareCheck.CHECKING);
					shareCheck.setShareValid(true);
					if (li == null || li.size() <= 0) {
						contentShareCheckMng.save(shareCheck, content,channelMng.findById(channelId),CmsUtils.getUser(request));
					}
				}
			}
		}
		json.put("pass", pass);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@RequiresPermissions("content:o_push")
	@RequestMapping("/content/o_push.do")
	public void push(Integer contentIds[], Integer channelIds[],
			HttpServletRequest request,HttpServletResponse response) throws JSONException {
		JSONObject json = new JSONObject();
		Boolean pass = true;
		if (contentIds != null && channelIds != null) {
			ContentShareCheck shareCheck;
			Content content;
			for (Integer contentId : contentIds) {
				content = manager.findById(contentId);
				for (Integer channelId : channelIds) {
					List<ContentShareCheck> li = contentShareCheckMng
							.getList(contentId, channelId);
					shareCheck = new ContentShareCheck();
					shareCheck.setCheckStatus(ContentShareCheck.PUSHED);
					shareCheck.setShareValid(true);
					if (li == null || li.size() <= 0) {
						contentShareCheckMng.save(shareCheck, content,channelMng.findById(channelId),CmsUtils.getUser(request));
					}
				}
			}
		}
		json.put("pass", pass);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@RequiresPermissions("content:o_pushnew")
	@RequestMapping("/content/o_pushnew.do")
	public void pushnew(Integer contentIds[], Integer channelIds[],
			HttpServletRequest request,HttpServletResponse response) throws JSONException {
		List<ContentShareCheck> checklist = new ArrayList<ContentShareCheck>();
		ContentShareCheck check = new ContentShareCheck();
		JSONObject json = new JSONObject();
		Boolean pass = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if (contentIds != null && channelIds != null) {
			ContentShareCheck shareCheck;
			Content content;
			for (Integer contentId : contentIds) {
				content = manager.findById(contentId);
				for (Integer channelId : channelIds) {
					List<ContentShareCheck> li = contentShareCheckMng
							.getList(contentId, channelId);
					shareCheck = new ContentShareCheck();
					shareCheck.setCheckStatus(ContentShareCheck.PUSHED);
					shareCheck.setShareValid(true);
					if (li == null || li.size() <= 0) {
						check = contentShareCheckMng.save(shareCheck, content,channelMng.findById(channelId),CmsUtils.getUser(request));
						checklist.add(check);
					}
				}
			}
			
			for (ContentShareCheck i : checklist) {
				Content cot=manager.findById(i.getContent().getId());
				List<RisenIntegralRecord> c=irService.findByContentId(cot.getId());
				/*if(!org.springframework.util.StringUtils.isEmpty(c)){
					continue;
				}*/
				if(c.size() == 0){
					continue;
				}
				CmsUser user=CmsUtils.getUser(request);
				RisenIntegral it=integralService.findByOrgId(user.getDepartment().getId());
				if(org.springframework.util.StringUtils.isEmpty(it)){//如果积分表没有记录就新增一条
						it=new RisenIntegral();
						it.setRisenitBase(1);
						it.setRisenitOrgid(user.getDepartment().getId());
						it.setRisenitOrgname(user.getDepartment().getName());
						it.setRisenitScore(new Double(0));
						it.setRisenitYear(new Integer(sdf.format(new Date())));
						it.setRisenitDesc("");
						integralService.save(it);
				}
				
				RisenIntegralRecord record=new RisenIntegralRecord();
				record.setRisenirChannel(cot.getChannel().getName());
				record.setRisenirContentid(cot.getId());
				record.setRisenirContent(cot.getContentExt().getTitle());
				record.setRisenirContenturl(cot.getUrl());
				record.setRisenirOrgid(user.getDepartment().getId());
				record.setRisenirOrgname(user.getDepartment().getName());
				record.setRisenirTargetorgid(i.getChannel().getDepart().getId());
				record.setRisenirResult(0);
				record.setRisenirScore(new Double(0));
				record.setRisenirHandledate(new Date());
				record.setRisenirSharecheck(check.getId());
				record.setRisenirContenttype(cot.getType().getName());
				try {
					irService.save(record);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		json.put("pass", pass);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@Transactional
	@RequiresPermissions("content:o_sharenew")
	@RequestMapping("/content/o_sharenew.do")
	public void sharenew(Integer contentIds[], Integer channelIds[],
		HttpServletRequest request,HttpServletResponse response) throws JSONException {
		List<ContentShareCheck> checklist = new ArrayList<ContentShareCheck>();
		ContentShareCheck checkbean = new ContentShareCheck();;
		JSONObject json = new JSONObject();
		Boolean pass = true;
		CmsUser user=CmsUtils.getUser(request);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String loginUser = user.getUsername();
		
		if (contentIds != null && channelIds != null) {
			ContentShareCheck shareCheck;
			Content content;
			for (Integer contentId : contentIds) {
				content = manager.findById(contentId);
				for (Integer channelId : channelIds) {
					Channel ch = new Channel();
					ch = channelDao.findById(channelId);
					Set<CmsDepartment> setdp = new HashSet<CmsDepartment>();
					setdp = ch.getDepartments();
					int deptid=0;
					if(setdp != null && setdp.size()>0){
						 Iterator<CmsDepartment> it=setdp.iterator();
						 deptid = it.next().getId();
					}
					//获取当前组织的上一级
					Integer parentId = 1;
					if(!loginUser.endsWith("市局机关党委")){
						if(user.getDepartment().getParent().getId()==1){
							parentId = 1;
						}else{
							parentId = cmsDepartmentDao.getMyActualParent(user.getDepartment().getParent().getParent().getId());
						}
					}
					
					if(deptid == user.getDepartment().getId()){
						json.put("erro", "不能给自己共享！");
						json.put("pass", false);
						ResponseUtils.renderJson(response, json.toString());
						return;
					}
					//验证文章是否被共享过
					List<RisenIntegralRecord> records = risenIntegralRecordDao.findByContentIdAndDeptId(parentId, contentId);
					if(records.size()>0){
						json.put("erro", "此文章已经共享过一次!");
						json.put("pass", false);
						ResponseUtils.renderJson(response, json.toString());
						return;
					}
					
					List<ContentShareCheck> li = contentShareCheckMng.getList(contentId, channelId);
					shareCheck = new ContentShareCheck();
					//共享待审
					shareCheck.setCheckStatus(ContentShareCheck.CHECKING);
					shareCheck.setShareValid(true);
					if (li == null || li.size() <= 0) {
						checkbean.init();
						/*
						Content newContent = content;
						Content buildNew = dao.save(newContent);
						if(buildNew.getType().getId()==3 || (buildNew.getType().getId()==4)){
							ContentType type = typeMng.findById(1);
							buildNew.setType(type);
						}
						buildNew.setShowInFront("show");
						*/
						checkbean=contentShareCheckMng.save(shareCheck, content,channelMng.findById(channelId),CmsUtils.getUser(request));
						checklist.add(checkbean);
					}
				}
			}
			if(checklist != null && checklist.size()>0){
				for (ContentShareCheck i : checklist) {
					Content cot=manager.findById(i.getContent().getId());
					Set<CmsDepartment> setdp = new HashSet<CmsDepartment>();
					setdp = i.getChannel().getDepartments();
					int deptid=1;
					int js=0;
					if(setdp != null && setdp.size()>0){
						 Iterator<CmsDepartment> it=setdp.iterator();
						 deptid = it.next().getId();
						 js++;
					}else{
						json.put("erro", "共享失败！");
						json.put("pass", false);
						ResponseUtils.renderJson(response, json.toString());
						return;
					}
					
					RisenIntegralRecord c=risenIntegralRecordDao.findByContentIdAndCheckId(cot.getId(), i.getId());
					if(!org.springframework.util.StringUtils.isEmpty(c)){
						continue;
					}
					RisenIntegral it=integralService.findByOrgId(user.getDepartment().getId());
					if(org.springframework.util.StringUtils.isEmpty(it)){//如果积分表没有记录就新增一条
							it=new RisenIntegral();
							it.setRisenitBase(1);
							it.setRisenitOrgid(user.getDepartment().getId());
							it.setRisenitOrgname(user.getDepartment().getName());
							it.setRisenitScore(new Double(0));
							it.setRisenitDesc("");
							it.setRisenitYear(new Integer(sdf.format(new Date())));
							it = integralService.save(it);
							CmsDepartment logindept = user.getDepartment();
							logindept.setRisenintegral(it);
							cmsDepartmentMng.update(logindept);
					}
					
					RisenIntegralRecord record=new RisenIntegralRecord();
					record.setRisenirChannel(cot.getChannel().getName());
					record.setRisenirContentid(cot.getId());
					record.setRisenirContent(cot.getContentExt().getTitle());
					record.setRisenirContenturl(cot.getUrl());
					record.setRisenirOrgid(user.getDepartment().getId());
					record.setRisenirOrgname(user.getDepartment().getName());
					record.setRisenirTargetorgid(deptid);
					record.setRisenirResult(0);
					record.setRisenirHandledate(new Date());
					record.setRisenirSharecheck(i.getId());
					record.setRisenirScore(new Double(0));
					record.setRisenirTargetchannel(i.getChannel().getName());
					record.setRisenirContenttype(cot.getType().getName());
					try {
						irService.save(record);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		json.put("pass", pass);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@RequiresPermissions("content:o_delete_share")
	@RequestMapping("/content/o_delete_share.do")
	public String delete_share(Integer[] ids,String title, Byte status, Integer siteId,
			Integer targetSiteId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		contentShareCheckMng.deleteByIds(ids);
		return list(title, status, siteId, targetSiteId, pageNo, request, model);
	}
	
	@RequiresPermissions("content:v_view_share")
	@RequestMapping("/content/v_view_share.do")
	public String view_share(Integer id,String title, Byte status, Integer siteId,
			Integer targetSiteId,  Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		/*
		WebErrors errors = validateView(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		*/
		CmsSite site = CmsUtils.getSite(request);
		Content content = manager.findById(id);
		model.addAttribute("content", content);
		model.addAttribute("site", site);
		model.addAttribute("title", title);
		model.addAttribute("status",status);
		model.addAttribute("siteId", siteId);
		model.addAttribute("targetSiteId", targetSiteId);
		model.addAttribute("pageNo", pageNo);
		return "content/view_share";
	}
	
	@RequiresPermissions("content:o_check_share")
	@RequestMapping("/content/o_check_share.do")
	public String check_share(Integer[] ids,String title, Byte status, Integer siteId,
			Integer targetSiteId,  Integer pageNo, HttpServletRequest request, ModelMap model) {
		ContentShareCheck shareCheck;
		if(ids!=null&&ids.length>0){
			for(Integer id:ids){
				shareCheck=contentShareCheckMng.findById(id);
				//非本站源内容 而且是待审核的共享信息
				if(shareCheck.getCheckStatus()==ContentShareCheck.CHECKING){
					shareCheck.setCheckStatus(ContentShareCheck.CHECKED);
					shareCheck.setShareValid(true);
				}
				contentShareCheckMng.update(shareCheck);
			}
		}
		return list(title, status, siteId, targetSiteId, pageNo, request, model);
	}

	@RequiresPermissions("content:share_list")
	@RequestMapping("/content/v_list_share.do")
	public String list(String title, Byte status, Integer siteId,
			Integer targetSiteId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site=CmsUtils.getSite(request);
		if(siteId!=null&&siteId.equals(0)){
			siteId=null;
		}
		if(targetSiteId!=null&&targetSiteId.equals(0)){
			targetSiteId=null;
		}
		if(status!=null&&status==-1){
			status=null;
		}
		Pagination p = contentShareCheckMng.getPageForShared(title, status, siteId,
				targetSiteId, site.getId(),cpn(pageNo), CookieUtils.getPageSize(request));
		List<CmsSite>siteList=cmsSiteMng.getList();
		model.addAttribute("pagination", p);
		model.addAttribute("siteList", siteList);
		model.addAttribute("site", site);
		model.addAttribute("title", title);
		model.addAttribute("status", status);
		model.addAttribute("siteId", siteId);
		model.addAttribute("targetSiteId", targetSiteId);
		return "content/list_share";
	}

	private WebErrors validateTree(String path, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		// if (errors.ifBlank(path, "path", 255)) {
		// return errors;
		// }
		return errors;
	}

	@Autowired
	private CmsSiteMng cmsSiteMng;
	@Autowired
	private CmsSiteDao cmsSiteDao;
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private ContentMng manager;
	@Autowired
	private ContentDao dao;
	@Autowired
	private ContentShareCheckMng contentShareCheckMng;
	@Autowired
	private IRisenIntegralRecordService irService;
	@Autowired
	private IRisenIntegralService integralService;
	@Autowired
	private IRisenIntegralRecordDao risenIntegralRecordDao;
	@Autowired
	private CmsDepartmentMng cmsDepartmentMng;
	@Autowired
	private CmsDepartmentDao cmsDepartmentDao;
	@Autowired
	private ContentTypeMng typeMng;
}
