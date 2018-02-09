package com.risen.action;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.risen.entity.RisenActive;
import com.risen.service.IRisenEnsureActiveService;

@Controller
public class RisenEnsureActiveAct {
	
	@Autowired
	private IRisenEnsureActiveService risenEnsureActiveService;
	
	@Autowired
	private CmsUserMng cmsUserMng;
	
	@RequiresPermissions("development_partymembers:submitpartyapplication:ensureactive_form")
	@RequestMapping("/development_partymembers/submitpartyapplication/ensureactive.do")
	public String toform(String state,ModelMap model){
		String str = "edit";
		if("view".equals(state)){
			str = "view";
		}
		model.addAttribute("state",str);
		return "development_partymembers/submitpartyapplication/ensureactive_form";
	}
	
	/**
	 * 组织机构派人谈话 保存信息的页面
	 */
	@RequiresPermissions("development_partymembers:submitpartyapplication:save_ensureactive")
	@RequestMapping("/development_partymembers/submitpartyapplication/saveActivePro.do")
	public String save(RisenActive bean,ModelMap map){
		
		// 将表单中的数据保存到实体类对应的表里
//		risenEnsureActiveService.toSave(bean);
//		CmsUser user = new CmsUser();
//		String risenCardId = bean.getRisenActiveCardId();
//		
//		// 用身份证号来查询数据库里面是否此人已经接受过组织的谈话
//		CmsUser cmsUser = cmsUserMng.findByUserCardId(risenCardId);
//		if(cmsUser!=null){
//			
//			//设置申请人身份证号
//			user.setSddscpIdnumber(risenCardId);
//        	user.setSddscpDevelopmentnum("C");
//			cmsUserMng.toupdate(user);
//		}	
		return "development_partymembers/development_main";
	}
}
