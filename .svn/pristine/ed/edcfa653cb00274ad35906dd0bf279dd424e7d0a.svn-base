package com.jeecms.cms.action.front;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.qrcode.decoder.Mode;
import com.jeecms.cms.entity.assist.CmsAppealMail;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.manager.assist.CmsAppealMailMng;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.assist.CmsGuestbookMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class AppealMailAct {
	private static final Logger log = LoggerFactory
			.getLogger(AppealMailAct.class);

	public static final String APPEALMAIL_DETAIL = "tpl.appealMailDetail";
	public static final String APPEALMAIL_LIST = "tpl.appealMailList";
	public static final String APPEALMAIL_ADD = "tpl.appealMailAdd";
	public static final String APPEALMAIL_INDEX = "tpl.index";
	public static final String DIR = "appealMail";
	public static final String DIRINDEX = "index";
	/**
	 * 我的留言
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/appealmail/list.jspx", method = RequestMethod.GET)
	public String mymailbox(Integer pageNo, Integer ctgId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String deptId = request.getParameter("departId");
		Pagination pagination = cmsAppealMailMng.getPage(new Integer(deptId),cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("deptId", deptId);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAIL_LIST);
	}
	@RequestMapping(value = "/appealmail/detail.jspx", method = RequestMethod.GET)
	public String detail(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsAppealMail mail = null;
		CmsSite site = CmsUtils.getSite(request);
		if(id!=null){
			mail = cmsAppealMailMng.findById(id);
		}
		model.addAttribute("appealMail", mail);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAIL_DETAIL);

	}
	/**
	 * 提交留言。ajax提交。
	 * 
	 * @param contentId
	 * @param pageNo
	 * @param request
	 * @param response
	 * @param model
	 * @throws JSONException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/appealmail/save.jspx", method = RequestMethod.POST)
	public void submit(HttpServletRequest request,HttpServletResponse response) {
		CmsAppealMail mail = new CmsAppealMail();
		String deptIds = request.getParameter("partId");
		Integer deptId = new Integer(deptIds);
		mail.setDepartId(deptId);
		mail.setReplay("0");
		mail.setCdate(new Date());
		mail.setContent(request.getParameter("content"));
		mail.setEmail(request.getParameter("email"));
		mail.setName(request.getParameter("name"));
		mail.setTitle(request.getParameter("title"));
		mail.setIsopen(request.getParameter("isOpen"));
		mail.setPhone(request.getParameter("phone"));
		mail.setType("0");
		cmsAppealMailMng.save(mail);
		try {
			response.getWriter().write(mail.getCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/appealmail/add.jspx", method = RequestMethod.GET)
	public String add(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String partId = request.getParameter("partId");
		model.addAttribute("partId", partId);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAIL_ADD);
	}
	@Autowired
	private CmsAppealMailMng cmsAppealMailMng;
	
}
