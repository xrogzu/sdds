package com.jeecms.cms.action.front;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.assist.CmsAppealMail;
import com.jeecms.cms.manager.assist.CmsAppealMailMng;
import com.jeecms.cms.manager.assist.CmsAppealMailReplayMng;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.risen.entity.CmsAppealMailReplay;

@Controller
public class AppealMailReplayAct {
	private static final Logger log = LoggerFactory.getLogger(AppealMailReplayAct.class);
	
	public static final String APPEALMAILREPLAY_ADD = "tpl.appealMailReplayAdd";
	public static final String APPEALMAILREPLAY_SEARCH = "tpl.appealMailReplaySearch";
	public static final String APPEALMAILREPLAY_REPLAY = "tpl.appealMailReplayReplay";
	public static final String APPEALMAIL_DETAIL = "tpl.appealMailReplayDetail";
	public static final String APPEALMAILREPLAY_INDEX = "tpl.appealMailList";
	public static final String DIR = "appealMailReplay";
	public static final String DIRINDEX = "appealMail";
	
	@RequestMapping(value = "/appealmailreplay/replay.jspx", method = RequestMethod.GET)
	public String replay(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("First", "YES");
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAILREPLAY_REPLAY);
	}
	
	@RequestMapping(value = "/appealmailreplay/search.jspx", method = RequestMethod.POST)
	public String search(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String code = request.getParameter("appealMailCode");
		CmsSite site = CmsUtils.getSite(request);
		CmsAppealMail mail = cmsAppealMailMng.fingByCode(code);
		if(mail!=null){
			CmsAppealMailReplay replay = cmsAppealMailReplayMng.findById(mail.getAppealId());
			if(replay!=null){
				model.addAttribute("replay", replay);
			}else{
				model.addAttribute("replay", null);
			}
		}else{
			model.addAttribute("replay", null);
		}
		model.addAttribute("mail", mail);
		model.addAttribute("First", "NO");
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAILREPLAY_REPLAY);
	}
	@RequestMapping(value = "/appealmailreplay/add.jspx", method = RequestMethod.GET)
	public String add(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String addpealId = request.getParameter("id");
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("appealId", addpealId);
		CmsAppealMail mail = cmsAppealMailMng.findById(new Integer(addpealId));
		mail.setReplay("1");
		cmsAppealMailMng.save(mail);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAILREPLAY_ADD);
	}
	@RequestMapping(value = "/appealmailreplay/save.jspx", method = RequestMethod.POST)
	public String submit(CmsAppealMailReplay replay,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException, IOException {
		JSONObject json = new JSONObject();
		String addpealId = request.getParameter("appealId");
		replay.setReplayId(Integer.parseInt(addpealId));
		replay.setCdate(new Date());
		try{
			cmsAppealMailReplayMng.save(replay);
		}catch(Exception e){
			e.printStackTrace();
		}
		json.put("success", true);
		json.put("status", 0);
		//ResponseUtils.renderJson(response, json.toString());
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),"index", "tpl.index");
	}
	@RequestMapping(value = "/appealmailreplay/detail.jspx", method = RequestMethod.GET)
	public String detail(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsAppealMailReplay replay = null;
		CmsSite site = CmsUtils.getSite(request);
		if(id!=null){
			replay = cmsAppealMailReplayMng.findById(id);
		}
		model.addAttribute("appealMailReplay", replay);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, APPEALMAIL_DETAIL);

	}
	@Autowired
	private CmsAppealMailReplayMng cmsAppealMailReplayMng;
	@Autowired
	private CmsAppealMailMng cmsAppealMailMng;
}
