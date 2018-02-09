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
import org.springframework.web.servlet.ModelAndView;

import com.jeecms.common.page.Pagination;
import com.risen.action.font.ExcelUtil;
import com.risen.action.font.OrgUtilRecord;
import com.risen.action.font.OrgUtilVote;
import com.risen.entity.RisenVote;
import com.risen.entity.RisenVoteQues;
import com.risen.entity.RisenVoteRecord;
import com.risen.entity.RisenVoteRecordDetail;
import com.risen.service.IRisenVoteQuesService;
import com.risen.service.IRisenVoteRecordDetailService;
import com.risen.service.IRisenVoteRecordService;
import com.risen.service.IRisenVoteService;

@Controller
public class RisenRecordAct {
	//投票记录展示的方法
	@RequiresPermissions("vote:record:v_list")
	@RequestMapping("/vote/record/v_list.do")
	public String findRsVoteRecord(HttpServletRequest request,ModelMap model,Integer pageNo,Integer voteId){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		RisenVote vote=voteService.findById(voteId);
		Pagination pagination=recordService.getPage(voteId,pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("vote", vote);
		return "vote/record/recordList";
	}
	
	//投票详细
	@RequiresPermissions("vote:record:detail:v_list")
	@RequestMapping("/vote/record/detail/v_list.do")
	public String findRsVoteRecordDetail(Integer voteId,HttpServletRequest request,ModelMap model,Integer pageNo,Integer recordId){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		RisenVoteRecord record=recordService.findById(recordId);
		Pagination pagination=recordDetailService.getPage(recordId,pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("record", record);
		model.addAttribute("voteId", voteId);
		return "vote/record/detail/detailList";
	}
	
	//投票记录删除
	@RequiresPermissions("vote:record:deleteRecord")
	@RequestMapping("/vote/record/deleteRecord.do")
	public String delteVoteItem(Integer[] ids,Integer pageNo,Integer voteId,HttpServletRequest request,ModelMap model) {
		for(int i=0;i<ids.length;i++){
			recordDetailService.deleteByRecord(ids[i]);
			recordService.delete(ids[i]);
		}
		return findRsVoteRecord(request,model,pageNo,voteId);
	}
	
	//模板3 记录导出
	@RequiresPermissions("vote:record:excelRecord")
	@RequestMapping("/vote/record/excelRecord.do")
	public void excelRecord(Integer voteId,ModelMap model,HttpServletResponse response,HttpServletRequest request){
		RisenVote vote=voteService.findById(voteId);
		//List<RsVoteRecord> rdList=recordMng.findListByVote(voteId,null);
		Pagination pagination=recordService.getPage(voteId,1, 500);
		List<Object> recordList = new ArrayList<Object>();
		List<RisenVoteQues> quesList = quesService.findListByVote(voteId, null);
		//for(int k=3;k<=4;k++){
			Pagination p1=recordService.getPage(voteId,18, 500);
			//System.out.println("k:"+k);
			List<RisenVoteRecord> rdList=(List<RisenVoteRecord>)p1.getList();
			for(int i=0;i<rdList.size();i++){
				RisenVoteRecord record=rdList.get(i);
				Object object = new Object();
				List<RisenVoteRecordDetail> detailList=recordDetailService.findListByRecord(record.getId());
				String[] temp = new String[5+quesList.size()*2];
				temp[0]=String.valueOf(i+1);//序号
				temp[1]=record.getRecordIp();//ip
				temp[2]=String.valueOf(record.getRecordTime());//时间
				temp[3]=record.getRecordPhone();//手机号
				temp[4]=record.getRecordYjzj();//网友留言
				//选项
				int j=0;
				for (RisenVoteRecordDetail detail:detailList) {
					temp[5+j]=detail.getRecordAnswer();//
					temp[6+j]=detail.getItemYjjy();//
					j+=2;
				}
				object = temp;
				recordList.add(temp);
				
			}
		//}
		
		File basePath = new File(request.getRealPath(""));
		System.out.println(basePath);
		File uploadPath = new File(basePath.getAbsoluteFile() + "/uploadFile");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		String fileName="recordExport.xls";
		String filepath=basePath.getAbsoluteFile()+"/uploadFile/"+fileName;
		System.out.println("filePath:"+filepath);
		OrgUtilVote util=new OrgUtilVote();
		List<List<String>> excelData=getExcelList(recordList,quesList);
		ExcelUtil.writeExcel(filepath, excelData);
		util.download(filepath, response);
	}
	
	
	public List<List<String>> getExcelList(List<Object> eList,List<RisenVoteQues> quesList){
		OrgUtilRecord util=new OrgUtilRecord();
		List<List<String>> list=new ArrayList<List<String>>();
		List<String> head=util.getHead(quesList);
		list.add(head);
		for(int i=0;i<eList.size();i++){
			Object object=eList.get(i);
			Object[] objects=(Object[])object;
			List<String> l=new ArrayList<String>();
			
			l.add(util.getString(objects[0]));
			l.add(util.getString(objects[1]));
			l.add(util.getString(objects[2]));
			l.add(util.getString(objects[3]));
			l.add(util.getString(objects[4]));
			int j=0;
			for(RisenVoteQues ques:quesList){
				l.add(util.getString(objects[5+j]));
				l.add(util.getString(objects[6+j]));
				j+=2;
			}
			list.add(l);
		}
		return list;
	}
	@Autowired
	private IRisenVoteRecordService recordService;
	@Autowired
	private IRisenVoteService voteService;
	@Autowired
	private IRisenVoteRecordDetailService recordDetailService;
	@Autowired
	private IRisenVoteQuesService quesService;
}
