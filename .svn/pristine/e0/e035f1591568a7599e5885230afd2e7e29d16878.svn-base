package com.risen.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.risen.action.font.ExcelUtil;
import com.risen.action.font.OrgUtilVote;
import com.risen.action.font.OrgUtilVote2;
import com.risen.entity.RisenVote;
import com.risen.entity.RisenVoteItem;
import com.risen.entity.RisenVoteQues;
import com.risen.service.IRisenVoteItemService;
import com.risen.service.IRisenVoteQuesContentService;
import com.risen.service.IRisenVoteQuesService;
import com.risen.service.IRisenVoteService;
@Controller
public class RisenVoteItemAct {
	//选项列表展示的页面
	@RequiresPermissions("vote:item:list")
	@RequestMapping("/vote/item/list.do")
	public String findRsVoteItem(HttpServletRequest request,ModelMap model,Integer pageNo,
			Integer voteId,Integer quesId){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		RisenVote vote=new RisenVote();
		if(voteId!=null) vote=voteService.findById(voteId);
		Pagination pagination=itemService.getPage(voteId,quesId,pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("voteId", voteId);
		model.addAttribute("vote", vote);
		model.addAttribute("quesId", quesId);
		model.addAttribute("pageNo", pageNo);
		
		return "vote/item/list";
	}
	
	//选项列表添加页面
	@RequiresPermissions("vote:item:add")
	@RequestMapping("/vote/item/add.do")
	public String addVote(Integer voteId,ModelMap model) {
		model.addAttribute("voteId", voteId);
		return "vote/item/add";
	}
	//选项列表保存页面
	@RequiresPermissions("vote:item:save")
	@RequestMapping("/vote/item/save.do")
	public String saveVote(RisenVoteItem item,Integer voteId,ModelMap model) {
		RisenVote vote=voteService.findById(voteId);
		item.setVote(vote);
		item.setItemCount(0);
		itemService.save(item);
		return "redirect:list.do?voteId="+voteId;
	}
	//选项列表修改页面
	@RequiresPermissions("vote:item:edit")
	@RequestMapping("/vote/item/edit.do")
	public String editVoteItem(Integer id,Integer pageNo,ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		RisenVoteItem item=itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("pageNo", pageNo);
		return "vote/item/edit";
	}
	//选项列表修改保存的方法
	@RequiresPermissions("vote:item:updateVoteItem")
	@RequestMapping("/vote/item/updateVoteItem.do")
	public String updateVoteItem(Integer voteId,RisenVoteItem bean,Integer pageNo,HttpServletRequest request,ModelMap model) {
		RisenVote vote=voteService.findById(voteId);
		bean.setVote(vote);
		itemService.update(bean);
		return findRsVoteItem(request,model,pageNo,bean.getVote().getId(),null);
	}
	//选项列表删除
	@RequiresPermissions("vote:item:delete")
	@RequestMapping("/vote/item/delete.do")
	public String delteVoteItem(Integer[] ids,Integer pageNo,Integer voteId,HttpServletRequest request,ModelMap model) {
		for(int i=0;i<ids.length;i++){
			itemService.delete(ids[i]);
		}
		return findRsVoteItem(request,model,pageNo,voteId,null);
	}
	//选项列表排序
	@RequiresPermissions("vote:item:sort")
	@RequestMapping("/vote/item/sort.do")
	public String itemSort(Integer voteId,Integer pageNo,Integer itemids[],Integer sorts[],ModelMap model,HttpServletRequest request){
		if(itemids!=null){
		for (int i = 0; i < itemids.length; i++) {
			if(sorts[i]==null)
				sorts[i]=1;
			itemService.sort(itemids[i],sorts[i]);
		}
		}
		return findRsVoteItem(request,model,pageNo,voteId,null);
	}
	//模板1图标统计
	@RequiresPermissions("vote:item:itemPic")
	@RequestMapping("/vote/item/itemPic.do")
	public String itemPic(Integer voteId,ModelMap model,HttpServletRequest request){
		RisenVote vote=voteService.findById(voteId);
		if(vote!=null){
			List<RisenVoteItem> itemList=itemService.findListByVote(voteId);
			String dataXml="<chart palette='1'  caption='"+vote.getVoteTitle()+"' outCnvBaseFontSize='14' baseFontSize='14'> ";
			for(RisenVoteItem item:itemList){
				dataXml+="<set label='"+item.getItemTitle()+"' value='"+item.getItemCount()+"'/>";
			}
			dataXml+="</chart>";
			model.addAttribute("xml", dataXml);
		}
		model.addAttribute("vote", vote);
		return "vote/item/item1Picture";
	}
	//模板1导出
	@RequiresPermissions("vote:item:excelMb1")
	@RequestMapping("/vote/item/excelMb1.do")
	public void excelMb1(Integer voteId,ModelMap model,HttpServletResponse response,HttpServletRequest request){
		RisenVote vote=voteService.findById(voteId);
		List<RisenVoteItem> itemList=itemService.findListByVote(voteId);
		List<Object> recordList = new ArrayList<Object>();
		for(int i=0;i<itemList.size();i++){
			Object object = new Object();
			String[] temp = new String[3];
			temp[0]=String.valueOf(i+1);
			temp[1]=itemList.get(i).getItemTitle();
			temp[2]=String.valueOf(itemList.get(i).getItemCount());
			object = temp;
			recordList.add(temp);
		}
		File basePath = new File(request.getRealPath(""));
		System.out.println(basePath);
		File uploadPath = new File(basePath.getAbsoluteFile() + "/uploadFile");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		String fileName="voteExport.xls";
		String filepath=basePath.getAbsoluteFile()+"/uploadFile/"+fileName;
		System.out.println("filePath:"+filepath);
		OrgUtilVote util=new OrgUtilVote();
		List<List<String>> excelData=util.getExcelList(recordList,vote);
		ExcelUtil.writeExcel(filepath, excelData);
		util.download(filepath, response);
	}
	//模板2.3导出
	@RequiresPermissions("vote:item:excelMb23")
	@RequestMapping("/vote/item/excelMb23.do")
	public void excelMb23(Integer voteId,ModelMap model,HttpServletResponse response,HttpServletRequest request){
		RisenVote vote=voteService.findById(voteId);
		List<RisenVoteQues> quesList=quesService.findListByVote(voteId,null);
		List<Object> recordList = new ArrayList<Object>();
		for(int i=0;i<quesList.size();i++){
			Object object = new Object();
			String[] temp = new String[9];
			temp[0]=String.valueOf(i+1);
			temp[1]=quesList.get(i).getQuesTitle();
			if(quesList.get(i).getQuesType()!=3){
				List<RisenVoteItem> items=itemService.findListByQues(quesList.get(i).getId());
				Integer totalCount=0;
				Integer j=2;
				for(RisenVoteItem item:items){
				temp[j]=String.valueOf(item.getItemCount());
				j++;
				
				totalCount+=item.getItemCount();
				}
				temp[8]=String.valueOf(totalCount);
			}
			
			object = temp;
			recordList.add(temp);
			
		}
		File basePath = new File(request.getRealPath(""));
		System.out.println(basePath);
		File uploadPath = new File(basePath.getAbsoluteFile() + "/uploadFile");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		String fileName="voteExport.xls";
		String filepath=basePath.getAbsoluteFile()+"/uploadFile/"+fileName;
		System.out.println("filePath:"+filepath);
		OrgUtilVote2 util=new OrgUtilVote2();
		List<List<String>> excelData=util.getExcelList(recordList,vote);
		ExcelUtil.writeExcel(filepath, excelData);
		util.download(filepath, response);
	}
	
	//模板2,3非问答题图标统计
	@RequiresPermissions("vote:item:itemDetail")
	@RequestMapping("/vote/item/itemDetail.do")
	public String itemDetail(Integer quesId,Integer pageNo,ModelMap model,HttpServletRequest request){
		RisenVoteQues ques=quesService.findById(quesId);
		List<RisenVoteItem> itemList=itemService.findListByQues(quesId);
		String caption="投票详细";
		if(ques.getVote().getVoteType()==3)
			caption=ques.getQuesTitle();
		String xml=" <chart palette='0' caption='"+caption+"' xAxisName='选项' yAxisName='票数' showValues='1' decimals='1' baseFontSize='12'" +
		" outCnvBaseFontSize='14' shownames='1' plotSpacePercent='75' rotateYAxisName='0'  formatNumberScale='0' useRoundEdges='1'> ";
		for(RisenVoteItem item:itemList){
			xml+=" <set label='"+item.getItemTitle()+"' value='"+item.getItemCount()+"'/> ";
		}
		xml+=" </chart>";
		model.addAttribute("xml", xml);
		model.addAttribute("ques", ques);
		model.addAttribute("pageNo", pageNo);
		return "vote/item/item23Picture";
	}
	//模板2,3问答题详细
	@RequiresPermissions("vote:item:itemContDetail")
	@RequestMapping("/vote/item/itemContDetail.do")
	public String itemContDetail(Integer quesId,Integer pageNoQues,Integer pageNo,ModelMap model,HttpServletRequest request){
		RisenVoteQues ques=quesService.findById(quesId);
		if(pageNo==null)
			pageNo=1;
		Pagination pagination=contentService.getPage(pageNo, 20, quesId);
		model.addAttribute("ques", ques);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageNoQues", pageNoQues);
		return "vote/content/list";
	}
	@Autowired
	private IRisenVoteItemService itemService;
	@Autowired
	private IRisenVoteService voteService;
	@Autowired 
	private IRisenVoteQuesService quesService;
	@Autowired
	private IRisenVoteQuesContentService contentService;

}
