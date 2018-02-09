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
import com.jeecms.core.entity.CmsUser;
import com.risen.action.font.ExcelUtil;
import com.risen.action.font.OrgUtil;
import com.risen.action.font.OrgUtilWj;
import com.risen.entity.RisenQaires;
import com.risen.entity.RisenQairesDetail;
import com.risen.entity.RisenQairesRecord;
import com.risen.entity.RisenQairesTopic;
import com.risen.service.IRisenQairesContentService;
import com.risen.service.IRisenQairesDetailService;
import com.risen.service.IRisenQairesItemService;
import com.risen.service.IRisenQairesRecordService;
import com.risen.service.IRisenQairesService;
import com.risen.service.IRisenQairesTopicService;

@Controller
public class RisenQairesAct {
	//问卷展示
	@RequiresPermissions("qaires:v_list")
	@RequestMapping("/qaires/v_list.do")
	public String findRsQaires(HttpServletRequest request,ModelMap model,Integer pageNo,
			String startDate,String endDate,Integer status){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		Pagination pagination=qairesService.getPage(startDate,endDate,pageNo, pageSize,null,status);
		model.addAttribute("pagination", pagination);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("status", status);
		String url="qaires/qairesList";
		if(status==1)
			url="qaires/qairesWjList";
		return url;
	}	
	//添加页面
	@RequiresPermissions("qaires:addQairesWj")
	@RequestMapping("/qaires/addQairesWj.do")
	public String addQairesWj(ModelMap model) {
		return "qaires/addQairesWj";
	}
	//保存的方法
	@RequiresPermissions("qaires:saveQaires")
	@RequestMapping("/qaires/saveQaires.do")
	public String saveQaires(RisenQaires bean,ModelMap model) {
		bean.setTotalCount(0);
		bean=qairesService.save(bean);
		return "redirect:v_list.do?status="+bean.getStatus();
	}
	//编辑的方法
	@RequiresPermissions("qaires:editQaires")
	@RequestMapping("/qaires/editQaires.do")
	public String editQaires(Integer id,Integer pageNo,String startDate,String endDate,
			ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		RisenQaires qaires=qairesService.findById(id);
		model.addAttribute("qaires", qaires);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		String url="qaires/editQaires";
		if(qaires.getStatus()==1)
			url="qaires/editQairesWj";
		return url;
	}
	//修改的方法
	@RequiresPermissions("qaires:updateQaires")
	@RequestMapping("/qaires/updateQaires.do")
	public String updateQaires(RisenQaires bean,Integer pageNo,String startDate,String endDate,
			HttpServletRequest request,ModelMap model,Integer status) {
		qairesService.update(bean);
		return findRsQaires(request,model,pageNo,startDate,endDate,status);
	}
	@RequiresPermissions("qaires:deleteQaires")
	@RequestMapping("/qaires/deleteQaires.do")
	public String delete(Integer[] ids,Integer pageNo, HttpServletRequest request,
			ModelMap model,String startDate,String endDate,Integer status) {
		for (int i = 0, len = ids.length; i < len; i++) {
			List<RisenQairesTopic> topicList=qairestopicService.finList(ids[i]);
			for(RisenQairesTopic topic:topicList){
				//删除题目和答案
				qairesitemService.deleteByTopic(topic.getId());
				qairestopicService.delete(topic.getId());
			}
			//删除问卷
			qairesService.delete(ids[i]);
			//删除记录
			qairesrecordService.deleteByQaires(ids[i]);
		}
		return findRsQaires(request,model,pageNo,startDate,endDate,status);
	}
	@RequiresPermissions("qaires:deleteRecord")
	@RequestMapping("/qaires/deleteRecord.do")
	public String deleteRecord(Integer qairesId,Integer[] ids,Integer pageNo, HttpServletRequest request,
			ModelMap model,String startDate,String endDate,String ip){
		for(int i=0;i<ids.length;i++){
			qairesrecordService.delete(ids[i]);
			qairesDetailService.deleteByRecord(ids[i]);
			qairescontentService.deleteByRecord(ids[i]);
		}
		return "redirect:qairesRecord/v_list.do?qairesId="+qairesId+"&pageNo="+pageNo+"&startDate="+
		     startDate+"&endDate="+endDate+"&ip="+ip;
	}
	
	//禁用(启用)方法
	@RequiresPermissions("qaires:disabled")
	@RequestMapping("/qaires/disabled.do")
	public String disabled(Integer disable,Integer id,Integer pageNo, HttpServletRequest request,
			ModelMap model,String startDate,String endDate) {
		RisenQaires qaires=qairesService.findById(id);
		if(disable==0){
			qaires.setDisabled(false);
		}else if(disable==1){
			qaires.setDisabled(true);
		}
		qairesService.update(qaires);
		model.addAttribute("pageNo", pageNo);
		return findRsQaires(request,model,pageNo,startDate,endDate,qaires.getStatus());
	}
	@RequiresPermissions("qaires:qairesRecord:v_list")
	@RequestMapping("/qaires/qairesRecord/v_list.do")
	public String recordList(Integer qairesId,String userName,Integer pageNo,String startDate,String endDate,
			HttpServletRequest request,ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		RisenQaires qaires=qairesService.findById(qairesId);
		Pagination pagination=qairesrecordService.getPage(qairesId,userName,startDate,endDate,pageNo,pageSize);
		model.put("pagination", pagination);
		model.put("startDate", startDate);
		model.put("endDate", endDate);
		model.put("userName", userName);
		model.put("qairesId", qairesId);
		model.put("pageNo", pageNo);
		String url="qaires/qairesRecord/recordList";
		if(qaires.getStatus()==1)
			url="qaires/qairesRecord/recordWjList";
		return url;
	}
	@RequiresPermissions("qairesTopic:qairesContent:v_list")
	@RequestMapping("/qairesTopic/qairesContent/v_list.do")
	public String wjEssay(HttpServletRequest request,ModelMap model,Integer pageNo,Integer topicId,Integer qairesId){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		Pagination pagination=qairescontentService.getPage(topicId, pageNo, pageSize);
		model.addAttribute("topic", qairestopicService.findById(topicId));
		model.addAttribute("qairesId", qairesId);
		model.addAttribute("topicId", topicId);
		model.addAttribute("pagination", pagination);
		return "qaires/qairesContent/contentList";
	}
	@RequiresPermissions("qaires:qairesDetail:v_list")
	@RequestMapping("/qaires/qairesDetail/v_list.do")
	public String recordDetail(Integer recordId,Integer pageNo,
			HttpServletRequest request,ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		CmsUser user=qairesrecordService.findById(recordId).getUser();
		RisenQairesRecord record=qairesrecordService.findById(recordId);
		Pagination pagination=qairesDetailService.getPage(recordId,pageNo,pageSize);
		model.put("user", user);
		model.put("recordId", recordId);
		model.put("qairesId", qairesrecordService.findById(recordId).getQaires().getId());
		model.put("pagination", pagination);
		String url="qaires/qairesDetail/recordDetailList";
		if(record.getQaires().getStatus()==1)
			url="qaires/qairesDetail/recordDetailWjList";
		return url;
	}
	//导出excel
	@RequiresPermissions("qaires:exportExcel")
	@RequestMapping("qaires/exportExcel.do")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,
			Integer qairesId,String userName,String startDate,String endDate){
		List<RisenQairesRecord> list = qairesrecordService.getList(qairesId, userName, startDate, endDate);
		List<Object> recordList = new ArrayList<Object>();
		for(RisenQairesRecord record:list){
			Object object = new Object();
			String[] temp = new String[9];
			temp[0]="";
			temp[1]="";
			temp[2]="";
			if(record.getUser()!=null){
				temp[0]=record.getUser().getRealname();
				temp[1]=record.getUser().getIntro();
				temp[2]=record.getUser().getMobile();
			}
			temp[3]=String.valueOf(record.getRecordTime());
			temp[4]=String.valueOf(record.getRightCount()+record.getWrongCount());
			temp[5]=String.valueOf(record.getRightCount());
			temp[6]=String.valueOf(record.getWrongCount());
			temp[7]=String.valueOf(record.getUnwrite());
			temp[8]=String.valueOf(record.getTotalScore());
			object = temp;
			recordList.add(temp);
		}
		for(Object object:recordList){
			Object[] objects=(Object[])object;
			for(Object o:objects){
				System.out.print(o+" ");
			}
		}
		
		File basePath = new File(request.getRealPath(""));
		File uploadPath = new File(basePath.getAbsoluteFile() + "/uploadFile");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		String fileName="orderExport.xls";
		String filepath=basePath.getAbsoluteFile()+"/uploadFile/"+fileName;
		System.out.println("filePath:"+filepath);
		OrgUtil util=new OrgUtil();
		List<List<String>> excelData=util.getExcelList(recordList);
		System.out.println("list size:"+list.size());
		System.out.println("excel size:"+excelData.size());
		ExcelUtil.writeExcel(filepath, excelData);
		util.download(filepath, response);
	}
	
	//导出excel
	@RequiresPermissions("qaires:exportExcelWj")
	@RequestMapping("qaires/exportExcelWj.do")
	public void exportExcelWj(HttpServletRequest request,HttpServletResponse response,
			Integer qairesId,String userName,String startDate,String endDate){
		List<RisenQairesRecord> list = qairesrecordService.getList(qairesId, userName, startDate, endDate);
		List<Object> recordList = new ArrayList<Object>();
		List<RisenQairesTopic> topicList=qairestopicService.finList(qairesId);
		for(RisenQairesRecord record:list){
			Object object = new Object();
			List<RisenQairesDetail> detaiList=(List<RisenQairesDetail>)qairesDetailService.getPage(record.getId(), 1, 1000).getList();
			String[] temp = new String[topicList.size()+5];
			temp[0]=String.valueOf(record.getId());
			temp[1]="";
			temp[2]="";
			temp[3]="";
			if(record.getUser()!=null){
				temp[1]=record.getUser().getRealname();
				temp[2]=record.getUser().getIntro();
				temp[3]=record.getUser().getMobile();
			}
			temp[4]=String.valueOf(record.getRecordTime());
			//问卷详细
			for (int i = 0; i < topicList.size(); i++) {
				for(RisenQairesDetail detail:detaiList){
					if(topicList.get(i).getId()==detail.getTopic().getId()){
						if (detail.getTopic().getType()==4) {
							temp[5+i]=detail.getDetailContent();
						}else
							temp[5+i]=detail.getAnswer();
					}
				}
			}
			
			object = temp;
			recordList.add(temp);
		}
		for(Object object:recordList){
			Object[] objects=(Object[])object;
			for(Object o:objects){
				System.out.print(o+" ");
			}
			System.out.println();
		}
		
		File basePath = new File(request.getRealPath(""));
		System.out.println(basePath);
		File uploadPath = new File(basePath.getAbsoluteFile() + "/uploadFile");
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		String fileName="orderExport.xls";
		String filepath=basePath.getAbsoluteFile()+"/uploadFile/"+fileName;
		System.out.println("filePath:"+filepath);
		OrgUtilWj util=new OrgUtilWj();
		List<List<String>> excelData=util.getExcelList(recordList,topicList);
		System.out.println("list size:"+list.size());
		System.out.println("excel size:"+excelData.size());
		ExcelUtil.writeExcel(filepath, excelData);
		util.download(filepath, response);
	}
	
	@Autowired
	private IRisenQairesService qairesService;
	@Autowired 
	private IRisenQairesRecordService qairesrecordService;
	@Autowired 
	private IRisenQairesTopicService qairestopicService;
	@Autowired
	private IRisenQairesItemService qairesitemService;
	@Autowired
	private IRisenQairesContentService qairescontentService;
	@Autowired
	private IRisenQairesDetailService qairesDetailService;
}
