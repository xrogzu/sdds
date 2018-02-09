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
import com.risen.entity.RisenQaires;
import com.risen.entity.RisenQairesItem;
import com.risen.entity.RisenQairesTopic;
import com.risen.service.IRisenQairesDetailService;
import com.risen.service.IRisenQairesItemService;
import com.risen.service.IRisenQairesService;
import com.risen.service.IRisenQairesTopicService;

@Controller
public class RisenQairesTopicAct {
	//问卷题目
	@RequiresPermissions("qairesTopic:v_list")
	@RequestMapping("/qairesTopic/v_list.do")
	public String topicList(Integer qairesId,Integer pageNo,ModelMap model){
		if(pageNo==null)
			pageNo=1;
		Integer pageSize=20;
		RisenQaires qaires=qairesService.findById(qairesId);
		Pagination pagination=topicService.getPage(qairesId,pageNo,pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("qairesId", qairesId);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("qaires", qaires);
		String url="qairesTopic/topicList";
		if(qaires.getStatus()==1)
			url="qairesTopic/topicWjList";
		return url;
	}
	//添加页面
	@RequiresPermissions("qairesTopic:addTopic")
	@RequestMapping("/qairesTopic/addTopic.do")
	public String addTopic(Integer qairesId,ModelMap model) {
		RisenQaires qaires=qairesService.findById(qairesId);
		model.put("qairesId", qairesId);
		String url="qairesTopic/addTopic";
		if(qaires.getStatus()==1)
			url="qairesTopic/addTopicWj";
		return url;
	}
	
	@RequiresPermissions("qairesTopic:saveTopic")
	@RequestMapping("/qairesTopic/saveTopic.do")
	public String saveQaires(Integer qairesId,RisenQairesTopic topic,String[] itemTitle,
			 Integer[] priority,String[] itemOption,ModelMap model) {
		List<RisenQairesItem> items = getItems(topic,itemTitle,priority,itemOption,null,null);
		RisenQaires qaires=qairesService.findById(qairesId);
		Integer multiSelect=1;
		if(topic.getType()==2)
			multiSelect=items.size();
		topic.setMultiSelect(multiSelect);
		topic.setQaires(qaires);
		topic.setTotalCount(0);
		topic=topicService.save(topic);
		
		for(RisenQairesItem item:items){
			qairesItemService.save(item);
		}
		return topicList(qairesId, 1, model);
	}
	@RequiresPermissions("qairesTopic:editTopic")
	@RequestMapping("/qairesTopic/editTopic.do")
	public String editTopic(Integer id,Integer qairesId,Integer pageNo,ModelMap model) {
		if(pageNo==null)
			pageNo=1;
		RisenQairesTopic topic=topicService.findById(id);
		List<RisenQairesItem>itemList=qairesItemService.findList(id);
		model.addAttribute("itemList",itemList);
		model.addAttribute("topic", topic);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("qairesId", qairesId);
		String url="qairesTopic/editTopic";
		if(qairesService.findById(qairesId).getStatus()==1)
			url="qairesTopic/editTopicWj";
		return url;
	}
	@RequiresPermissions("qairesTopic:topicSort")
	@RequestMapping("/qairesTopic/topicSort.do")
	public String topicSort(Integer qairesId,Integer pageNo,Integer sortIds[],Integer torderBy[],ModelMap model){
		for (int i = 0; i < sortIds.length; i++) {
			if(torderBy[i]==null)
				torderBy[i]=1;
			topicService.sort(sortIds[i],torderBy[i]);
		}
		return topicList(qairesId,pageNo,model);
	}
	@RequiresPermissions("qairesTopic:updateTopic")
	@RequestMapping("/qairesTopic/updateTopic.do")
	public String updateTopic(Integer qairesId,RisenQairesTopic bean,Integer pageNo,String[] itemTitle,
			 Integer[] priority,Integer[] itemCount,Integer[] itemId,String[] itemOption,HttpServletRequest request,ModelMap model) {
		List<RisenQairesItem> items = getItems(bean,itemTitle,priority,itemOption,itemCount,itemId);
		if(items.size()!=0){
		bean.setQaires(qairesService.findById(qairesId));
		Integer multiSelect=1;
		if(bean.getType()==2)
			multiSelect=items.size();
		bean.setMultiSelect(multiSelect);
		bean=topicService.update(bean);
		//
		List<RisenQairesItem> set=qairesItemService.findList(bean.getId());
		// 先删除
		for (RisenQairesItem item : set) {
			if (!items.contains(item)) {
				qairesItemService.delete(item.getId());
			}
		}
		// 再更新和增加
		for (RisenQairesItem item : items) {
			item.setQairesTopic(bean);
			if (set.contains(item)) {
				// 更新
				qairesItemService.updater(item);
			} else {
				// 增加
				qairesItemService.save(item);
			}
		}
		}
		return topicList(bean.getQaires().getId(), pageNo, model);
	}
	@RequiresPermissions("qairesTopic:deleteTopic")
	@RequestMapping("/qairesTopic/deleteTopic.do")
	public String delete(Integer qairesId,Integer[] ids,Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		for (int i = 0, len = ids.length; i < len; i++) {
			qairesItemService.deleteByTopic(ids[i]);
			topicService.delete(ids[i]);
			qairesDetailService.deleteByTopic(ids[i]);
		}
		return topicList(qairesId, pageNo, model);
	}
	
	@RequiresPermissions("qairesTopic:wjPicture")
	@RequestMapping("/qairesTopic/wjPicture.do")
	public String wjPicture(Integer id,Integer qairesId,HttpServletRequest request,ModelMap model){
		RisenQairesTopic topic=topicService.findById(id);
		String xml=" <chart palette='0' caption='"+topic.getTitle()+"' xAxisName='选项' yAxisName='票数' showValues='1' decimals='1' baseFontSize='12'" +
				" outCnvBaseFontSize='14' shownames='1' plotSpacePercent='75' rotateYAxisName='0'  formatNumberScale='0' useRoundEdges='1'> ";
		String dataXml="<chart palette='0' caption='首页板块访问情况' rotateYAxisName='0' outCnvBaseFontSize='14' shownames='1' showvalues='1' " +
		"decimals='1' baseFontSize='12' yAxisName='点击量' plotSpacePercent='50' useRoundEdges='1'>";
		List<RisenQairesItem> itemList= qairesItemService.findList(id);
		for(RisenQairesItem item:itemList){
			xml+=" <set label='"+item.getItemTitle()+"' value='"+item.getItemCount()+"'/> ";
		}
		xml+=" </chart>";
		model.addAttribute("xml", xml);
		model.addAttribute("topic", topic);
		model.addAttribute("qairesId", qairesId);
		return "qairesTopic/topicWjPicture";
		
	}
	@RequiresPermissions("qairesTopic:itemCount")
	@RequestMapping("/qairesTopic/itemCount.do")
	public String itemCount(Integer topicId,ModelMap model){
		RisenQairesTopic topic=topicService.findById(topicId);
		Pagination p=qairesItemService.getPage(topicId,1,20);
		model.addAttribute("pagination", p);
		model.addAttribute("topicId", topicId);
		model.addAttribute("qairesId", topic.getQaires().getId());
		return "qairesTopic/itemList";
	}
	
	private List<RisenQairesItem> getItems(RisenQairesTopic topic,String[] itemTitle,
			 Integer[] priority,String[] itemOption,Integer[] itemCount,Integer[] itemId) {
		List<RisenQairesItem> items = new ArrayList<RisenQairesItem>();
		RisenQairesItem item;
		for (int i = 0, len = itemTitle.length; i < len; i++) {
			if (!StringUtils.isBlank(itemTitle[i])) {
				item = new RisenQairesItem();
				//判断该选项是否为答案
				if (itemId != null && itemId[i] != null) {
					item.setId(itemId[i]);
				}
				item.setItemTitle(itemTitle[i]);
				item.setItemCount(0);
				
				item.setItemOption(itemOption[i]);
				item.setPriority(priority[i]);
				item.setQairesTopic(topic);
				items.add(item);
			}
		}
		return items;
	}
	@Autowired
	private IRisenQairesService qairesService;
	@Autowired
	private IRisenQairesTopicService topicService;
	@Autowired
	private IRisenQairesItemService qairesItemService;
	@Autowired
	private IRisenQairesDetailService qairesDetailService;
}
