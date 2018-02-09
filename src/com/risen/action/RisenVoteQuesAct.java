package com.risen.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVote;
import com.risen.entity.RisenVoteItem;
import com.risen.entity.RisenVoteQues;
import com.risen.service.IRisenVoteItemService;
import com.risen.service.IRisenVoteQuesService;
import com.risen.service.IRisenVoteService;
@Controller
public class RisenVoteQuesAct {
	//展示的方法
	@RequiresPermissions("vote:ques:list")
	@RequestMapping("/vote/ques/list.do")
	public String findRsVoteQues(HttpServletRequest request,ModelMap model,Integer pageNo,
			Integer voteId){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		Pagination pagination=quesService.getPage(pageNo, pageSize,voteId);
		RisenVote vote=voteService.findById(voteId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("voteId", voteId);
		model.addAttribute("vote", vote);
		return "vote/ques/list";
	}
	//添加页面
	@RequiresPermissions("vote:ques:add")
	@RequestMapping("/vote/ques/add.do")
	public String addVoteQues(Integer voteId,ModelMap model) {
		model.addAttribute("voteId", voteId);
		RisenVote vote = voteService.findById(voteId);
		model.addAttribute("vote", vote);
		return "vote/ques/add";
	}
	private List<RisenVoteItem> getItems(RisenVoteQues ques,String[] itemTitle,
			 Integer[] itemState,Integer[] itemCount,Integer[] itemId) {
		List<RisenVoteItem> items = new ArrayList<RisenVoteItem>();
		RisenVoteItem item;
		for (int i = 0, len = itemTitle.length; i < len; i++) {
			if (!StringUtils.isBlank(itemTitle[i])) {
				item = new RisenVoteItem();
				//判断该选项是否为答案
				if (itemId != null && itemId[i] != null) {
					item.setId(itemId[i]);
				}
				item.setItemTitle(itemTitle[i]);
				item.setItemCount(itemCount[i]);
				item.setItemState(itemState[i]);
				item.setQues(ques);
				items.add(item);
			}
		}
		return items;
	}
	
	//保存
	@RequiresPermissions("vote:ques:save")
	@RequestMapping("/vote/ques/save.do")
	public String saveVoteQues(String quesContentJj,String quesContentLink,Integer voteId,RisenVoteQues ques,String[] itemTitle,Integer[] itemState,Integer[] itemCount,ModelMap model) {
		List<RisenVoteItem> items = getItems(ques, itemTitle, itemState, itemCount, null);
		RisenVote vote=voteService.findById(voteId);
		ques.setVote(vote);
		if(quesContentJj!=null&&quesContentLink!=null){
			if(ques.getQuesStatus()==1&&!StringUtils.isBlank(quesContentLink))
			ques.setQuesContent(quesContentLink);
			else if(ques.getQuesStatus()==2&&!StringUtils.isBlank(quesContentJj)){
			ques.setQuesContent(quesContentJj);
		}
		}
		quesService.save(ques);
		for(RisenVoteItem item:items){
			itemService.save(item);
		}
		return "redirect:list.do?voteId="+voteId;
	}
	@RequiresPermissions("vote:ques:edit")
	@RequestMapping("/vote/ques/edit.do")
	public String editVoteQues(Integer id,Integer pageNo,Integer voteId,
			ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		RisenVote vote =voteService.findById(voteId);
		model.addAttribute("vote", vote);
		RisenVoteQues ques=quesService.findById(id);
		List<RisenVoteItem> itemList=itemService.findListByQues(id);
		model.addAttribute("ques", ques);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("voteId", voteId);
		return "vote/ques/edit";
	}
	@RequiresPermissions("vote:ques:update")
	@RequestMapping("/vote/ques/update.do")
	public String updateVoteQues(String quesContentJj,String quesContentLink,RisenVoteQues ques,Integer pageNo,Integer voteId,String[] itemTitle,
			 Integer[] itemState,Integer[] itemCount,Integer[] itemId,HttpServletRequest request,ModelMap model) {
		if(ques.getQuesStatus()!=null){
		if(itemTitle!=null){
			List<RisenVoteItem> items = getItems(ques, itemTitle, itemState, itemCount, itemId);
			for(RisenVoteItem item:items){
				itemService.save(item);
			}
		}
		RisenVote vote=voteService.findById(voteId);
		ques.setVote(vote);
		if(ques.getQuesStatus()==1&&!StringUtils.isBlank(quesContentLink))
			ques.setQuesContent(quesContentLink);
		else if(ques.getQuesStatus()==2&&!StringUtils.isBlank(quesContentJj)){
			ques.setQuesContent(quesContentJj);
		}
		quesService.update(ques);
		}
		return findRsVoteQues(request,model,pageNo,voteId);
	}
	
	//删除
	@RequiresPermissions("vote:ques:delete")
	@RequestMapping("/vote/ques/delete.do")
	public String delteVoteQues(Integer[] ids,Integer pageNo,Integer voteId,
			HttpServletRequest request,ModelMap model) {
		for(int i=0;i<ids.length;i++){
			itemService.deleteByQues(ids[i]);
			quesService.delete(ids[i]);
		}
		return findRsVoteQues(request,model,pageNo,voteId);
	}
	
	//排序
	@RequiresPermissions("vote:ques:sort")
	@RequestMapping("/vote/ques/sort.do")
	public String itemSort(Integer voteId,Integer pageNo,Integer sortIds[],Integer sorts[],ModelMap model,HttpServletRequest request){
		for (int i = 0; i < sortIds.length; i++) {
			if(sorts[i]==null)
				sorts[i]=1;
			quesService.sort(sortIds[i],sorts[i]);
		}
		return findRsVoteQues(request,model,pageNo,voteId);
	}
	

	//类似创建
	@RequiresPermissions("vote:ques:copy")
	@RequestMapping("/vote/ques/copy.do")
	public String copyVoteQues(Integer voteId,Integer pageNo,Integer[] ids,ModelMap model,HttpServletRequest request){
		for (int i = 0; i < ids.length; i++) {
			RisenVoteQues ques=quesService.findById(ids[i]);
			//复制ques
			RisenVoteQues newQues=new RisenVoteQues();
			newQues.setQuesSort(ques.getQuesSort());
			newQues.setQuesTitle(ques.getQuesTitle());
			newQues.setQuesType(ques.getQuesType());
			if(ques.getQuesNature()!=null)
				newQues.setQuesNature(ques.getQuesNature());
			if(ques.getQuesStatus()!=null)
				newQues.setQuesStatus(ques.getQuesStatus());
			newQues.setVote(ques.getVote());
			newQues=quesService.save(newQues);
			//复制选项
			for (RisenVoteItem item:itemService.findListByQues(ques.getId())) {
				RisenVoteItem newItem=new RisenVoteItem();
				newItem.setItemCount(0);
				newItem.setItemState(item.getItemState());
				newItem.setItemTitle(item.getItemTitle());
				newItem.setQues(newQues);
				itemService.save(newItem);
			}
		}
		return findRsVoteQues(request,model,pageNo,voteId);
	}
	@Autowired
	private IRisenVoteItemService itemService;
	@Autowired
	private IRisenVoteQuesService quesService;
	@Autowired
	private IRisenVoteService voteService;
}
