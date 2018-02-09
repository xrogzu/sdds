package com.jeecms.cms.action.front;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.manager.assist.CmsBallotMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.risen.entity.RisenDevparty;
import com.risen.service.RisenDevpartyMng;

@Controller
public class RisenDevpartyAct {
	private static final Logger log = LoggerFactory.getLogger(RisenDevpartyAct.class);
	
	
	@RequestMapping(value = "/risenDevparty/save.jspx", method = RequestMethod.POST)
	public void save(Integer pageNo, Integer ctgId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		RisenDevparty party = new RisenDevparty();
		party.setRisendpBirth(request.getParameter("risendpBirth"));
		party.setRisendpName(request.getParameter("risendpName"));
		party.setRisendpIdnumber(request.getParameter("risendpIdnumber"));
		party.setRisendpSex(request.getParameter("risendpSex"));
		party.setRisendpBranch(request.getParameter("risendpBranch"));
		party.setRisendpNation(request.getParameter("risendpNation"));
		party.setRisendpNative(request.getParameter("risendpNative"));
		party.setRisendpMarriage(request.getParameter("risendpMarriage"));
		party.setRisendpExpands1(request.getParameter("risendpExpands1"));
		party.setRisendpExpands3(request.getParameter("risendpExpands3"));
		manager.save(party);
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Autowired
	private RisenDevpartyMng manager;
}
