package com.risen.action;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenDevelopment;
import com.risen.service.IRisenDevelopmentService;

@Controller
public class RisenDevelopmentAct {
	
	@Autowired IRisenDevelopmentService risenDevelopmentService;
	
	/**
	 * 进入添加页面
	 */
	@RequiresPermissions("development_partymembers:development_add")
	@RequestMapping("/development_partymembers/development_add.do")
	public String toadd(){
		return "development_partymembers/development_add";
	}
	
	/**
	 * 生成UUID工具方法
	 */
	public String getUuid(){
		UUID uuid = UUID.randomUUID();
		String [] uuidArrays = uuid.toString().split("-");
		String uuidstr = "";
		for (int i = 0; i < uuidArrays.length; i++) {
			uuidstr += uuidArrays[i];
		}
		return uuidstr;
	}
	
	/**
	 * 保存方法
	 * @param bean
	 * @param model
	 * @return
	 */
	@RequiresPermissions("development_partymembers:save")
	@RequestMapping("/development_partymembers/save.do")
	public String save(RisenDevelopment bean,ModelMap model) {
		String uuid = this.getUuid();
		bean.setRisendeUuid(uuid);
		bean = risenDevelopmentService.save(bean);
		return "redirect:list.do";
	}
	
	/**
	 * 流程展示列表
	 */
	@RequiresPermissions("development_partymembers:list")
	@RequestMapping("/development_partymembers/list.do")
	public String risenDevelopmentList(RisenDevelopment bean,ModelMap model){
		Pagination pagination = new Pagination();
		List<RisenDevelopment> list = risenDevelopmentService.risenDevelopmentList(bean);
		if(list.size()>0){
			pagination.setList(list);
		}else {
			pagination.setList(new ArrayList<RisenDevelopment>());
		}
		model.addAttribute("pagination", pagination);
		return "development_partymembers/development_manage";
	}
	
	/**
	 * 删除流程
	 */
	@RequiresPermissions("development_partymembers:delete")
	@RequestMapping("/development_partymembers/delete.do")
	public String deleteById(String uuid){
		risenDevelopmentService.deleteData(uuid);
		return "redirect:list.do";
	}
	
	/**
	 * 查看详情
	 */
	@RequiresPermissions("development_partymembers:edit")
	@RequestMapping("/development_partymembers/edit.do")
	public String toEdit(ModelMap model,String uuid){
		RisenDevelopment risenDevelopment = risenDevelopmentService.getModel(uuid);
		model.addAttribute("model",risenDevelopment);
		return "development_partymembers/edit";
	}
}
