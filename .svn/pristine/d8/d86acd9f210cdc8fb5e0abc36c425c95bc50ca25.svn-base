package com.jeecms.cms.action.front;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.dao.assist.ContentFrontDao;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.assist.CmsBallotMng;
import com.jeecms.cms.manager.assist.ContentFrontMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class ContentFrontAct {
	private static final Logger log = LoggerFactory.getLogger(ContentFrontAct.class);
	public static final String DIR = "contentFront";
	public static final String CONTENT_LIST = "tpl.contentList";
	
	@RequestMapping(value = "/content/search.jspx", method = RequestMethod.POST)
	public String search(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer pageNo){
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getId();
		FrontUtils.frontData(request, model, site);
		if(pageNo==null){
			pageNo = 1;
		}
		String searchName = request.getParameter("q");
		List<Content> totalContents = dao.getListByName(searchName,siteId);
		int totalSize = totalContents.size();//总条数
		int pageSize = 18;   //每页展示数量
		boolean exactly = (totalSize % pageSize) == 0; //是否有多余
		int totalPage = totalSize / pageSize;//总页数
		totalPage = exactly ? totalPage : (totalPage+1);
		
		List<Content> contents = manager.getListByName(searchName,pageNo,siteId);
		model.addAttribute("contents", contents);
		model.addAttribute("wantSearch", searchName);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", totalPage);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, CONTENT_LIST);
	}
	
	@RequestMapping(value = "/content/search.jspx", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer pageNo){
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getId();
		FrontUtils.frontData(request, model, site);
		if(pageNo==null){
			pageNo = 1;
		}
		String searchName = request.getParameter("searchKey");
		try {
			searchName = new String(searchName.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Content> totalContents = dao.getListByName(searchName,siteId);
		int totalSize = totalContents.size();//总条数
		int pageSize = 18;   //每页展示数量
		boolean exactly = (totalSize % pageSize) == 0; //是否有多余
		int totalPage = totalSize / pageSize;//总页数
		totalPage = exactly ? totalPage : (totalPage+1);
		
		List<Content> contents = manager.getListByName(searchName,pageNo,siteId);
		model.addAttribute("contents", contents);
		model.addAttribute("wantSearch", searchName);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", totalPage);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, CONTENT_LIST);
	}
	
	@Autowired
	private ContentFrontMng manager;
	@Autowired
	private ContentFrontDao dao;
}
