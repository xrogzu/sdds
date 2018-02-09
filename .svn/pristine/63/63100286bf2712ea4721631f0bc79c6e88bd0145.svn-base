package com.risen.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVote;
import com.risen.service.IRisenVoteService;

@Controller
public class RisenVoteAct {
	
	//在线投票展示页面
	@RequiresPermissions("vote:v_list")
	@RequestMapping("/vote/v_list.do")
	public String findRsVote(HttpServletRequest request,ModelMap model,Integer pageNo,
			String startDate,String endDate,Boolean status){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		Pagination pagination=voteService.getPage(startDate,endDate,pageNo, pageSize,status);
		model.addAttribute("pagination", pagination);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("status", status);
		return "vote/list";
	}
	
	//在线投票添加页面
	@RequiresPermissions("vote:v_add")
	@RequestMapping("/vote/v_add.do")
	public String addVote(ModelMap model) {
		return "vote/add";
	}
	//在线投票添加保存的方法
	@RequiresPermissions("vote:save")
	@RequestMapping("/vote/save.do")
	public String saveVote(RisenVote vote,ModelMap model) {
		voteService.save(vote);
		return "redirect:v_list.do";
	}
	//在线投票修改的页面
	@RequiresPermissions("vote:edit")
	@RequestMapping("/vote/edit.do")
	public String editVote(Integer id,Integer pageNo,String startDate,String endDate,
			ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		RisenVote vote=voteService.findById(id);
		model.addAttribute("vote", vote);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "vote/edit";
	}
	//修改保存的方法
	@RequiresPermissions("vote:update")
	@RequestMapping("/vote/update.do")
	public String updateVote(RisenVote bean,Integer pageNo,String startDate,String endDate,
			HttpServletRequest request,ModelMap model) {
		voteService.update(bean);
		return findRsVote(request,model,pageNo,startDate,endDate,null);
	}
	//禁用启用disabled.do
	@RequiresPermissions("vote:disabled")
	@RequestMapping("/vote/disabled.do")
	public String disabled(HttpServletRequest request,Integer id,Integer pageNo,String startDate,String endDate,Integer status,ModelMap model) {
		RisenVote vote=voteService.findById(id);
		if(status==1) vote.setStatus(true);
		else vote.setStatus(false);
		voteService.update(vote);
		return findRsVote( request, model, pageNo,startDate, endDate,null);
	}
	
	//在线投票删除的方法
	@RequiresPermissions("vote:delete")
	@RequestMapping("/vote/delete.do")
	public String delte(Integer[] ids,Integer pageNo,String startDate,String endDate,
			HttpServletRequest request,ModelMap model) {
		for (int i = 0; i < ids.length; i++) {
			voteService.delete(ids[i]);
		}
		return findRsVote(request,model,pageNo,startDate,endDate,null);
	}
	@Autowired
	private IRisenVoteService voteService;
}
