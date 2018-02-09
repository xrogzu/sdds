package com.risen.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risen.entity.RisenActive;
import com.risen.service.IRisenCheckIdentityIdService;

@Controller
public class RisenCheckIdentityIdAct {
    
	@Autowired
	private IRisenCheckIdentityIdService risenCheckIdentityIdService;
	@RequiresPermissions("development_partymembers:submitpartyapplication:checkInentityId_form")
	@RequestMapping("/development_partymembers/submitpartyapplication/checkIdentityId.do")
	
	//development_partymembers/submitpartyapplication/checkIdentityId.do
	public String checkInentityId(RisenActive bean,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 检查身份证号是否存在
		
		String risenActiveCardId = request.getParameter("cardId");;
		boolean boo = risenCheckIdentityIdService.checkIsExist(risenActiveCardId);
		String returnjson = "{\"result\":"+boo+"}";
        PrintWriter out = response.getWriter();
        out.println(returnjson);
		if(!boo){
			model.addAttribute("ifExitCardId",returnjson);
		}
		return "development_partymembers/submitpartyapplication/ensureactive_form";

    }
}
