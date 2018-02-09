package com.jeecms.cms.action.front;

import static com.jeecms.common.page.SimplePage.cpn;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.cms.entity.assist.CmsBallotItem;
import com.jeecms.cms.entity.assist.CmsBallotRecord;
import com.jeecms.cms.manager.CmsBallotRecordMng;
import com.jeecms.cms.manager.assist.CmsBallotItemMng;
import com.jeecms.cms.manager.assist.CmsBallotMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;


@Controller
public class BallotItemAct {
	private static final Logger log = LoggerFactory.getLogger(BallotItemAct.class);
	public static final String DIR = "ballotItem";
	public static final String BALLOTITEM_LIST = "tpl.ballotItemList";
	
	@RequestMapping(value = "/ballotItem/vote.jspx", method = RequestMethod.POST)
	public void vote(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException{
		//CmsSite site = CmsUtils.getSite(request);
		String ids = request.getParameter("ids");
		String ballotId = request.getParameter("ballotId");

		String[] idArray = ids.split(",");
		String ip = RequestUtils.getIpAddr(request);
		int voteCount=0;
		CmsBallot ballot = ballotManager.findById(new Integer(ballotId));
		Date endDate = DateUtils.getFinallyDate(ballot.getEndTime());
	    if(new Date().after(endDate)){
			response.getWriter().write("error此活动已过期");
		}
		boolean result = recordManager.existGive(ip, ballotId);
		if(result){
			try {
				response.getWriter().write("failure"+voteCount);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			for(int i=0;i<idArray.length;i++){
				CmsBallotItem item = manager.findById(new Integer(idArray[i]));
				    voteCount = item.getVoteCount().intValue();
					voteCount = voteCount+1;
					item.setVoteCount(new Integer(voteCount));
					try{
						manager.update(item);
					}catch(Exception e){
						System.out.println("item 表"+e.getMessage());
					}
					CmsBallotRecord record = new CmsBallotRecord();
					record.setItem(item);
					record.setVoteTime(new Date());
					record.setVoteCookie("");
					record.setVoteIp(RequestUtils.getIpAddr(request));
					record.setWxopenId("");
					try{
						recordManager.save(record);	
					}catch(Exception e){
						System.out.println("record表"+e.getMessage());
					}	
			}
			response.getWriter().write("success"+voteCount);
		}
	}
	
	@RequestMapping(value = "/ballotItem/list.jspx", method = RequestMethod.GET)
	public String list(Integer pageNo, Integer ctgId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		//CmsSite site = CmsUtils.getSite(request);
		String ballotId = request.getParameter("ballotId");
		CmsBallot ballot = ballotManager.findById(new Integer(ballotId));
		CmsSite site = siteMng.findById(ballot.getSiteId());
		FrontUtils.frontData(request, model, site);
		//Pagination pagination = manager.getPage(new Integer(ballotId),cpn(pageNo), CookieUtils.getPageSize(request));
		Pagination pagination = manager.getPage(new Integer(ballotId),cpn(pageNo), 35);
		model.addAttribute("pagination", pagination);
		model.addAttribute("ballotId", ballotId);
		model.addAttribute("ballot", ballot);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),DIR, BALLOTITEM_LIST);
	}
	@Autowired
	private CmsBallotItemMng manager;
	@Autowired
	private CmsBallotMng ballotManager;
	@Autowired
	private CmsBallotRecordMng recordManager;
	@Autowired
	private CmsDepartmentMng dpmanager;
	@Autowired
	private CmsUserMng umanager;
	@Autowired
	private CmsSiteMng siteMng;
}
