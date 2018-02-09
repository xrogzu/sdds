package com.risen.action;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.risen.entity.CoreDictionary;
import com.risen.service.ICoreDictionaryService;

@Controller
public class CoreDictionaryAct {

	@Autowired
	private ICoreDictionaryService coreDictionaryService;
	
	/**
	 * 进入列表页面
	 * @return
	 */
	@RequestMapping("/coredictionary/list.do")
	public String toList(Integer pageNo,HttpServletRequest request, Model model,String corecdType){
		Pagination pagination = this.getCoreDictionaryService().getPage(cpn(pageNo), CookieUtils
				.getPageSize(request),corecdType);
		List<CoreDictionary> corecdTypeList = this.getCoreDictionaryService().getCorecdTypeList();
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("corecdType", corecdType);
		model.addAttribute("corecdTypeList", corecdTypeList);
		return "coredictionary/list";
	}
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping("/coredictionary/edit.do")
	public String toEdit(){
		return "coredictionary/edit";
	}
	/**
	 * 进入添加页面
	 * @return
	 */
	@RequestMapping("/coredictionary/add.do")
	public String toAdd(){
		return "coredictionary/add";
	}
	/**
	 * 新增字典
	 */
	@RequestMapping("/coredictionary/save.do")
	public String save(String corecdType,String corecdKey,String corecdVal,Model model,HttpServletRequest request,Integer pageNo){
		this.getCoreDictionaryService().save(corecdType, corecdKey, corecdVal);
		return toList(pageNo, request, model,corecdType);
	}
	
	
	public ICoreDictionaryService getCoreDictionaryService() {
		return coreDictionaryService;
	}
	
}
