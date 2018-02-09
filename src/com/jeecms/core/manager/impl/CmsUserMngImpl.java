package com.jeecms.core.manager.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.CmsWorkflowEvent;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsGroupMng;
import com.jeecms.core.manager.CmsRoleMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.CmsUserSiteMng;
import com.jeecms.core.manager.CmsWorkflowEventMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.CmsFloating;
import com.risen.entity.CoreDictionary;
import com.risen.service.CmsFloatingMng;
import com.risen.service.ICoreDictionaryService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
@Transactional
public class CmsUserMngImpl implements CmsUserMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
			String realName,Integer departId,Integer roleId,
			Boolean allChannel,Boolean allControlChannel,
			int pageNo, int pageSize,String sddscpUsertype,String isadminlist) {
		Pagination page = dao.getPage(username, email, siteId, groupId,
				disabled, admin, rank,realName,departId,roleId, 
				allChannel,allControlChannel,pageNo, pageSize,sddscpUsertype,isadminlist);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<CmsUser> getList(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank) {
		List<CmsUser> list = dao.getList(username, email, siteId, groupId,
				disabled, admin, rank);
		return list;
	}

	@Transactional(readOnly = true)
	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank) {
		return dao.getAdminList(siteId, allChannel, disabled, rank);
	}
	
	@Transactional(readOnly = true)
	public Pagination getAdminsByDepartId(Integer id, int pageNo,int pageSize){
		return dao.getAdminsByDepartId(id, pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize){
		return dao.getAdminsByRoleId(roleId, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public CmsUser findById(Integer id) {
		CmsUser entity = dao.findById(id);
		return entity;
	}

	@Transactional(readOnly = true)
	public CmsUser findByUsername(String username) {
		CmsUser entity = dao.findByUsername(username);
		return entity;
	}

	
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId, boolean disabled,CmsUserExt userExt,Map<String,String>attr,
			Boolean activation, EmailSender sender, MessageTemplate msgTpl)throws UnsupportedEncodingException, MessagingException{
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
				password, ip, activation, sender, msgTpl);
		CmsUser user = new CmsUser();
		user.forMember(unifiedUser);
		user.setAttr(attr);
		user.setDisabled(disabled);
		CmsGroup group = null;
		if (groupId != null) {
			group = cmsGroupMng.findById(groupId);
		} else {
			group = cmsGroupMng.getRegDef();
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not found!");
		}
		user.setGroup(group);
		user.init();
		dao.save(user);
		cmsUserExtMng.save(userExt, user);
		return user;
	}

	public void updateLoginInfo(Integer userId, String ip,Date loginTime,String sessionId) {
		CmsUser user = findById(userId);
		if (user != null) {
			user.setLoginCount(user.getLoginCount() + 1);
			if(StringUtils.isNotBlank(ip)){
				user.setLastLoginIp(ip);
			}
			if(loginTime!=null){
				user.setLastLoginTime(loginTime);
			}
			user.setSessionId(sessionId);
		}
	}

	public void updateUploadSize(Integer userId, Integer size) {
		CmsUser user = findById(userId);
		user.setUploadTotal(user.getUploadTotal() + size);
		if (user.getUploadDate() != null) {
			if (CmsUser.isToday(user.getUploadDate())) {
				size += user.getUploadSize();
			}
		}
		user.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
		user.setUploadSize(size);
	}
	
	public void updateUser(CmsUser user){
		Updater<CmsUser>updater=new Updater<CmsUser>(user);
		dao.updateByUpdater(updater);
	}

	public boolean isPasswordValid(Integer id, String password) {
		return unifiedUserMng.isPasswordValid(id, password);
	}

	public void updatePwdEmail(Integer id, String password, String email) {
		CmsUser user = findById(id);
		if (!StringUtils.isBlank(email)) {
			user.setEmail(email);
		} else {
			user.setEmail(null);
		}
		unifiedUserMng.update(id, password, email);
	}

	public CmsUser saveAdmin(String username, String email, String password,
			String ip, boolean viewOnly, boolean selfAdmin, int rank,
			Integer groupId,Integer departmentId, Integer[] roleIds,Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels, Boolean[] allControlChannels,
			CmsUserExt userExt) {
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
				password, ip);
		CmsUser user = new CmsUser();
		user.forAdmin(unifiedUser, viewOnly, selfAdmin, rank);
		CmsGroup group = null;
		CmsDepartment department=null;
		if (groupId != null) {
			group = cmsGroupMng.findById(groupId);
		} else {
			group = cmsGroupMng.getRegDef();
		}
		if(departmentId!=null){
			department=cmsDepartmentMng.findById(departmentId);
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not setted!");
		}
		/*
		if (department == null) {
			throw new RuntimeException(
					"register default admin department not setted!");
		}
		*/
		user.setGroup(group);
		user.setDepartment(department);
		user.init();
		dao.save(user);
		cmsUserExtMng.save(userExt, user);
		if (roleIds != null) {
			for (Integer rid : roleIds) {
				user.addToRoles(cmsRoleMng.findById(rid));
			}
		}
		if (channelIds != null) {
			Channel channel;
			for (Integer cid : channelIds) {
				channel = channelMng.findById(cid);
				channel.addToUsers(user);
			}
		}
		if (siteIds != null) {
			CmsSite site;
			for (int i = 0, len = siteIds.length; i < len; i++) {
				site = cmsSiteMng.findById(siteIds[i]);
				cmsUserSiteMng.save(site, user, steps[i], allChannels[i],allControlChannels[i]);
			}
		}
		return user;
	}

	public void addSiteToUser(CmsUser user, CmsSite site, Byte checkStep) {
		cmsUserSiteMng.save(site, user, checkStep, true,true);
	}

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId, Integer[] roleIds, Integer[] channelIds,
			Integer siteId, Byte step, Boolean allChannel,Boolean allControlChannel) {
		CmsUser user = updateAdmin(bean, ext, password, groupId,departmentId, roleIds,
				channelIds);
		// 更新所属站点
		cmsUserSiteMng.updateByUser(user, siteId, step, allChannel,allControlChannel);
		return user;
	}

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId, Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,Boolean[] allControlChannels) {
		CmsUser user = updateAdmin(bean, ext, password, groupId,departmentId,roleIds,channelIds);
		// 更新所属站点
		cmsUserSiteMng.updateByUser(user, siteIds, steps, allChannels,allControlChannels);
		return user;
	}

	private CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId,Integer[] roleIds, Integer[] channelIds) {
		Updater<CmsUser> updater = new Updater<CmsUser>(bean);
		updater.include("email");
		CmsUser user = dao.updateByUpdater(updater);
		user.setGroup(cmsGroupMng.findById(groupId));
		if(departmentId!=null){
			user.setDepartment(cmsDepartmentMng.findById(departmentId));
		}
		try {
			cmsUserExtMng.update(ext, user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 更新角色
		user.getRoles().clear();
		if (roleIds != null) {
			for (Integer rid : roleIds) {
				user.addToRoles(cmsRoleMng.findById(rid));
			}
		}
		/*
		// 更新栏目权限
		Set<Channel> channels = user.getChannels();
		// 清除
		for (Channel channel : channels) {
			channel.getUsers().remove(user);
		}
		user.getChannels().clear();
		// 添加
		if (channelIds != null) {
			Channel channel;
			for (Integer cid : channelIds) {
				channel = channelMng.findById(cid);
				channel.addToUsers(user);
			}
		}
		*/
		unifiedUserMng.update(bean.getId(), password, bean.getEmail());
		return user;
	}

	public CmsUser updateMember(Integer id, String email, String password,
			Boolean isDisabled, CmsUserExt ext, Integer groupId,Integer grain,String sddscpIdnumber,String sddscpNational,String sddscpAddress,String sddscpPoliticaltype,
			String sddscpPartyposition,String sddscpBasescore,Integer sddscpXfscore,String sddscpKfscore,
			String sddscpSumscore,String sddscpOrgname,Date sddscpJoinpartydate,Date sddscpEbranchdate,
			Date sddscpJoinworktime,String sddscpEducation,String sddscpGraduate,String sddscpDegree,
			String sddscpMatrimonial,String sddscpWorkplace,String sddscpJobposition,String sddscpNative,
			String sddscpResidence,String sddscpOtherphone,String sddscpUsertype,Integer departmentId,String departName,
			String sddscpExcellentcause,String sddscpUnqualifiedcause,String sddscpIsinjob,String sddscpOutpartytype,String sddscpOutpartycause,String sddscpJobstatus,
			Integer sddscpJgdw,Integer sddscpDzz,Integer sddscpZb,String sddscpJgdwjob,String sddscpDzzjob,String sddscpZbjob,String sddscpAssess,
			String sddscpChanges,String sddscpChangestype,String sddscpJgdwname,String sddscpDzzname,String sddscpZbname,
			Map<String,String>attr, HttpServletRequest request,String targetDeptName) {
		CmsUser entity = findById(id);
		
		/*entity.setEmail(email);
		*/
		if (!StringUtils.isBlank(email)) {
			entity.setEmail(email);
		}
		
		if (isDisabled != null) {
			entity.setDisabled(isDisabled);
		}
		if (groupId != null) {
			entity.setGroup(cmsGroupMng.findById(groupId));
		}
		if(grain!=null){
			entity.setGrain(grain);
		}
		if(ext!=null && ext.getGender()!=null){
			if(ext.getGender()){
				entity.setSddscpSex("1");	//男
			}else if(!ext.getGender()){
				entity.setSddscpSex("2"); //女
			}else {
				entity.setSddscpSex("");
			}
		}
		if(ext!=null && ext.getBirthday()!=null){
			entity.setSddscpBirthday(ext.getBirthday());	//生日
		}
		if(ext!=null && !StringUtils.isBlank(ext.getPhone())){
			entity.setSddscpPhone(ext.getPhone());	//电话
		}
		if(ext!=null && !StringUtils.isBlank(ext.getMobile())){
			entity.setSddscpMobile(ext.getMobile());	//手机
		}
		if(departmentId!=null){
			entity.setDepartment(cmsDepartmentMng.findById(departmentId));	//所在支部
			entity.setSddscpOrgname(cmsDepartmentMng.findById(departmentId).getName());
		}
		if (sddscpJgdw!=null && sddscpJgdw>0) {
			entity.setSddscpJgdw(sddscpJgdw);
		}else{
			entity.setSddscpJgdw(null);
		}
		if (sddscpDzz!=null && sddscpDzz>0) {
			entity.setSddscpDzz(sddscpDzz);
		}else{
			entity.setSddscpDzz(null);
		}
		if (sddscpZb!=null && sddscpZb>0) {
			entity.setSddscpZb(sddscpZb);
		}
		
		// 党员的身份证号
		if(StringUtils.isNotBlank(sddscpIdnumber)){
			entity.setSddscpIdnumber(sddscpIdnumber);
		}
		
		// 党员的民族
		if(StringUtils.isNotBlank(sddscpNational)){
			entity.setSddscpNational(sddscpNational);
		}
		
		// 党员的家庭住址
		if(StringUtils.isNotBlank(sddscpAddress)){
			entity.setSddscpAddress(sddscpAddress);
		}
		
		// 政治面貌 (分两种,一种是中共党员，另一种是预备党员)
		if(StringUtils.isNotBlank(sddscpPoliticaltype)){
			entity.setSddscpPoliticaltype(sddscpPoliticaltype);
		}
		
		//党内职务
		if(StringUtils.isNotBlank(sddscpPartyposition)){
			entity.setSddscpPartyposition(sddscpPartyposition);
		}
		
		// 基本分
		if(StringUtils.isNotBlank(sddscpBasescore)){
			entity.setSddscpBasescore(sddscpBasescore);
		}
		
		// 先锋分
		if(sddscpXfscore!=null){
			entity.setSddscpXfscore(sddscpXfscore);
		}
		
		// 扣分
		if(StringUtils.isNotBlank(sddscpKfscore)){
			entity.setSddscpKfscore(sddscpKfscore);
		}
		
		// 总分
		if(StringUtils.isNotBlank(sddscpSumscore)){
			entity.setSddscpSumscore(sddscpSumscore);
		}
		
	    // 入党时间
		if(sddscpJoinpartydate!=null){
			entity.setSddscpJoinpartydate(sddscpJoinpartydate);
		}
		
		// 进入支部日期
		if(sddscpEbranchdate!=null){
			entity.setSddscpEbranchdate(sddscpEbranchdate);
		}
		
		// 参加工作时间
		if(sddscpJoinworktime!=null){
			entity.setSddscpJoinworktime(sddscpJoinworktime);
		}
		
		// 学历
		if(StringUtils.isNotBlank(sddscpEducation)){
			entity.setSddscpEducation(sddscpEducation);
		}
		
		// 毕业院校
		if(StringUtils.isNotBlank(sddscpGraduate)){
			entity.setSddscpGraduate(sddscpGraduate);
		}
		
		// 学位
		if(StringUtils.isNotBlank(sddscpDegree)){
			entity.setSddscpDegree(sddscpDegree);
		}
		
		// 婚姻状况
		if(StringUtils.isNotBlank(sddscpMatrimonial)){
			entity.setSddscpMatrimonial(sddscpMatrimonial);
		}
		if(StringUtils.isNotBlank(sddscpWorkplace)){
			entity.setSddscpWorkplace(sddscpWorkplace);
		}
		if(StringUtils.isNotBlank(sddscpJobposition)){
			entity.setSddscpJobposition(sddscpJobposition);
		}
		if(StringUtils.isNotBlank(sddscpNative)){
			entity.setSddscpNative(sddscpNative);
		}
		if(StringUtils.isNotBlank(sddscpResidence)){
			entity.setSddscpResidence(sddscpResidence);
		}
		if(StringUtils.isNotBlank(sddscpOtherphone)){
			entity.setSddscpOtherphone(sddscpOtherphone);
		}
		
		// 民主评议
		if(StringUtils.isNotBlank(sddscpAssess)){
			/*if("1".equals(sddscpAssess)){
				String typeString = ""; 
				String [] typearray = entity.getSddscpUsertype().split("3");
				for (int i = 0; i < typearray.length; i++) {
					typeString += typearray[i];
				}
				entity.setSddscpUsertype(typeString+"2");
			}else if("2".equals(sddscpAssess)){
				String typeString = ""; 
				String [] typearray = entity.getSddscpUsertype().split("2");
				for (int i = 0; i < typearray.length; i++) {
					typeString += typearray[i];
				}
				entity.setSddscpUsertype(typeString+"3");
			}*/
			entity.setSddscpAssess(sddscpAssess);
		}
		if(StringUtils.isNotBlank(departName)){
			entity.setSddscpOrgname(departName);
		}
		if(StringUtils.isNotBlank(sddscpExcellentcause)){
			entity.setSddscpExcellentcause(sddscpExcellentcause);
		}
		
		// 优秀党员原因
		if(StringUtils.isNotBlank(sddscpUnqualifiedcause)){
			entity.setSddscpUnqualifiedcause(sddscpUnqualifiedcause);
		}
		if(StringUtils.isNotBlank(sddscpIsinjob)){
			entity.setSddscpIsinjob(sddscpIsinjob);
		}
		if(StringUtils.isNotBlank(sddscpOutpartytype)){
			entity.setSddscpOutpartytype(sddscpOutpartytype);
		}
		if(StringUtils.isNotBlank(sddscpOutpartycause)){
			entity.setSddscpOutpartycause(sddscpOutpartycause);
		}
		if(StringUtils.isNotBlank(sddscpAssess)){
			entity.setSddscpAssess(sddscpAssess);
		}
		if(StringUtils.isNotBlank(sddscpDzzjob)){
			entity.setSddscpDzzjob(sddscpDzzjob);
		}
		if(StringUtils.isNotBlank(sddscpJgdwjob)){
			entity.setSddscpJgdwjob(sddscpJgdwjob);
		}
		if(StringUtils.isNotBlank(sddscpZbjob)){
			entity.setSddscpZbjob(sddscpZbjob);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(sddscpChanges)){
			entity.setSddscpChanges(sddscpChanges);
			boolean issave = false;
			/*
			CmsFloating floating = new CmsFloating();
			floating.setSddsfiUserid(entity.getId());
			floating.setSddsfiUsername(entity.getUsername());
			floating.setSddsfiOrgid(entity.getDepartment().getId());
			floating.setSddsfiOrgname(entity.getDepartment().getName());
			floating.setSddsfiIncounty("0");
			floating.setSddsfiInprovince("0");
			floating.setSddsfiIncity("0");
			floating.setSddsfiOutcounty("0");
			floating.setSddsfiOutprovince("0");
			floating.setSddsfiOutcity("0");
			String date = sdf.format(new Date());
			String [] dates = date.split("-");
			floating.setSddsfiYear(dates[0]);
			floating.setSddsfiMonth(dates[1]);
			floating.setSddsfiDay(dates[2]);
			if("4".equals(sddscpChanges)){
				floating.setSddsfiIncounty("1");
				floating.setSddsfiInprovince("1");
				floating.setSddsfiIncity("1");
			}else if("3".equals(sddscpChanges)){
				floating.setSddsfiInprovince("1");
				floating.setSddsfiIncity("1");
			}else if("2".equals(sddscpChanges)){
				floating.setSddsfiInprovince("1");
			}else if("8".equals(sddscpChanges)){
				floating.setSddsfiOutcounty("1");
				floating.setSddsfiOutprovince("1");
				floating.setSddsfiOutcity("1");
			}else if("7".equals(sddscpChanges)){
				floating.setSddsfiOutprovince("1");
				floating.setSddsfiOutcity("1");
			}else if("6".equals(sddscpChanges)){
				floating.setSddsfiOutprovince("1");
			}else {}
			*/
			if(sddscpJgdw!=null && !("0".equals(sddscpJgdw.toString()))){
				if(!sddscpJgdw.toString().equals(entity.getSddscpJgdw().toString())){
					issave = true;
				}
			}else if(sddscpDzz!=null && !("0".equals(sddscpDzz.toString()))){
				if(!sddscpDzz.toString().equals(entity.getSddscpDzz().toString())){
					issave = true;
				}
			}else if(sddscpZb!=null && !("0".equals(sddscpZb.toString()))){
				if(!sddscpZb.toString().equals(entity.getSddscpZb().toString())){
					issave = true;
				}
			}
			//if(issave){
				//cmsFloatingMng.save(floating);
			//}
		}
		
		// 学党员增加或减少类型
		if(StringUtils.isNotBlank(sddscpChangestype)){
			entity.setSddscpChangestype(sddscpChangestype);
			
		}
		if(StringUtils.isNotBlank(sddscpJgdwname)){// 机关党委名称
			entity.setSddscpJgdwname(sddscpJgdwname);
		}else{
			entity.setSddscpJgdwname("");
		}
		if(StringUtils.isNotBlank(sddscpDzzname)){// 机关党总支
			entity.setSddscpDzzname(sddscpDzzname);
		}else{
			entity.setSddscpDzzname("");
		}
		if(StringUtils.isNotBlank(sddscpZbname)){// 支部名称
			entity.setSddscpZbname(sddscpZbname);
		}
		if(StringUtils.isNotBlank(sddscpJobstatus)){ // 工作身份
			entity.setSddscpJobstatus(sddscpJobstatus);
		}
		if(StringUtils.isNotBlank(sddscpChangestype)){
			if("2".equals(sddscpChangestype)){
				entity.setSddscpIsinjob("0");
			}
		}
		// 更新属性表
		if (attr != null) {
			Map<String, String> attrOrig = entity.getAttr();
			attrOrig.clear();
			attrOrig.putAll(attr);
		}
		try{
			cmsUserExtMng.update(ext, entity);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		unifiedUserMng.update(id, password, email);
		return entity;
	}
	
	public CmsUser updateMember(Integer id, String email, String password,Integer groupId,String realname,String mobile,Boolean sex) {
		CmsUser entity = findById(id);
		CmsUserExt ext =entity.getUserExt();
		if (!StringUtils.isBlank(email)) {
			entity.setEmail(email);
		}
		if (groupId != null) {
			entity.setGroup(cmsGroupMng.findById(groupId));
		}
		if (!StringUtils.isBlank(realname)) {
			ext.setRealname(realname);
		}
		if (!StringUtils.isBlank(mobile)) {
			ext.setMobile(mobile);
		}
		if (sex!=null) {
			ext.setGender(sex);
		}
		cmsUserExtMng.update(ext, entity);
		unifiedUserMng.update(id, password, email);
		return entity;
	}
	
	public CmsUser updateUserConllection(CmsUser user,Integer cid,Integer operate){
		Updater<CmsUser> updater = new Updater<CmsUser>(user);
		user = dao.updateByUpdater(updater);
		if (operate.equals(1)) {
			user.addToCollection(contentMng.findById(cid));
		}// 取消收藏
		else if (operate.equals(0)) {
			user.delFromCollection(contentMng.findById(cid));
		}
		return user;
	}

	public CmsUser deleteById(Integer id) {
		//清除流程轨迹
		List<CmsWorkflowEvent>events=workflowEventMng.getListByUserId(id);
		for(CmsWorkflowEvent event:events){
			workflowEventMng.deleteById(event.getId());
		}
		unifiedUserMng.deleteById(id);
		CmsUser bean = dao.deleteById(id);
		//删除收藏信息
		bean.clearCollection();
		return bean;
	}

	public CmsUser[] deleteByIds(Integer[] ids) {
		CmsUser[] beans = new CmsUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public boolean usernameNotExist(String username) {
		return dao.countByUsername(username) <= 0;
	}
	
	public boolean usernameNotExistInMember(String username){
		return dao.countMemberByUsername(username)<= 0;
	}

	public boolean emailNotExist(String email) {
		return dao.countByEmail(email) <= 0;
	}
	
	public boolean loginCodeExist(long codeId){
		return dao.loginCodeExist(codeId);
	};
	/**
	 * 根据身份证来查询人员信息
	 * date:2016/10/11
	 * author:dongliang
	 */
	@Transactional(readOnly = true)
	public CmsUser findByUserCardId(String userCardId) {
		CmsUser entity = dao.findByUserCardId(userCardId);
		return entity;
	}
	
	/**
     * 获取字典键值列表
     * @param corecdType
     * @return
     */
	@Transactional(readOnly = true)
    public List<CmsUser> getCorecdKeyList(String corecdType){
		List<CoreDictionary> corecdKeyList = coreDictionaryService.getCorecdKeyList(corecdType);
		List<CmsUser> list = new ArrayList<CmsUser>();
		if(corecdKeyList.size()>0){
			for (int i = 0; i < corecdKeyList.size(); i++) {
				CmsUser user = new CmsUser();
				if(!StringUtils.isBlank(corecdKeyList.get(i).getCorecdKey())){
					user.setKey(corecdKeyList.get(i).getCorecdKey());
				}
				if(!StringUtils.isBlank(corecdKeyList.get(i).getCorecdVal())){
					user.setValue(corecdKeyList.get(i).getCorecdVal());
				}
				list.add(user);
			}
		}
		return list;
	}
	
	/**
     * 根据组织ID删除数据
     * 删除组织时，级联删除user表数据(仅限删除组织时使用)
     */
    public Integer deleteUserBydepartid(Integer departId){
    	dao.deleteUserBydepartid(departId);
    	return null;
    }
    /**
	 * 根据组织ID查人员
	 * @param departId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Pagination getMemberByDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize){
		return dao.getMemberByDepartId(departId,sddscpAssess,sddscpIsinjob,sddscpChanges, pageNo, pageSize);
	}
	
	public Pagination getMemberByQXandDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize){
		return dao.getMemberQXByDepartId(departId,sddscpAssess,sddscpIsinjob ,sddscpChanges,pageNo, pageSize);
	}
	public Pagination getMemberByQXandDepartIdAndYear(Integer departId,Integer newDeptId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String risendsYear,String sddscpHistorydy){
		return dao.getMemberQXByDepartIdAndYear(departId,newDeptId,sddscpAssess,sddscpIsinjob ,sddscpChanges,pageNo, pageSize,risendsYear,sddscpHistorydy);
	}
	public List<CmsUser> getMemberByQXandDepartIdAndYids(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String ids,String username){
		return dao.getMemberByQXandDepartIdAndYids(departId,sddscpAssess,sddscpIsinjob ,sddscpChanges,pageNo, pageSize,ids,username);
	}
	public Pagination assess(String orgtype,Integer departid,CmsUser user,int pageNo, int pageSize){
		return dao.assess(orgtype, departid, user, pageNo, pageSize);
	}
	private CmsUserSiteMng cmsUserSiteMng;
	private CmsSiteMng cmsSiteMng;
	private ChannelMng channelMng;
	private CmsRoleMng cmsRoleMng;
	private CmsDepartmentMng cmsDepartmentMng;
	private CmsGroupMng cmsGroupMng;
	private UnifiedUserMng unifiedUserMng;
	private CmsUserExtMng cmsUserExtMng;
	private CmsUserDao dao;
	
	@Autowired
	private ICoreDictionaryService coreDictionaryService;
	
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private CmsWorkflowEventMng workflowEventMng;
	@Autowired
	private CmsDepartmentDao departmentDao;
	@Autowired
	private CmsFloatingMng cmsFloatingMng;

	@Autowired
	public void setCmsUserSiteMng(CmsUserSiteMng cmsUserSiteMng) {
		this.cmsUserSiteMng = cmsUserSiteMng;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}

	@Autowired
	public void setChannelMng(ChannelMng channelMng) {
		this.channelMng = channelMng;
	}

	@Autowired
	public void setCmsRoleMng(CmsRoleMng cmsRoleMng) {
		this.cmsRoleMng = cmsRoleMng;
	}
	
	@Autowired
	public void setCmsDepartmentMng(CmsDepartmentMng cmsDepartmentMng) {
		this.cmsDepartmentMng = cmsDepartmentMng;
	}

	@Autowired
	public void setUnifiedUserMng(UnifiedUserMng unifiedUserMng) {
		this.unifiedUserMng = unifiedUserMng;
	}

	@Autowired
	public void setCmsUserExtMng(CmsUserExtMng cmsUserExtMng) {
		this.cmsUserExtMng = cmsUserExtMng;
	}

	@Autowired
	public void setCmsGroupMng(CmsGroupMng cmsGroupMng) {
		this.cmsGroupMng = cmsGroupMng;
	}

	@Autowired
	public void setDao(CmsUserDao dao) {
		this.dao = dao;
	}

	@Override
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId, Integer grain,
			String sddscpIdnumber, String sddscpNational, String sddscpAddress,
			String sddscpPoliticaltype, String sddscpPartyposition,
			String sddscpBasescore, Integer sddscpXfscore, String sddscpKfscore,
			String sddscpSumscore, String sddscpOrgname,
			Date sddscpJoinpartydate, Date sddscpEbranchdate,
			Date sddscpJoinworktime, String sddscpEducation,
			String sddscpGraduate, String sddscpDegree,
			String sddscpMatrimonial, String sddscpWorkplace,
			String sddscpJobposition, String sddscpNative,
			String sddscpResidence, String sddscpOtherphone,
			String sddscpUsertype, Integer departmentId, String departName,
			String sddscpExcellentcause, String sddscpUnqualifiedcause,
			String sddscpIsinjob, String sddscpOutpartytype,
			String sddscpOutpartycause, String sddscpJobstatus,
			String sddscpIsperororg, String sddscpOrgloginname,
			String sddscpOrglogincode, Integer sddscpJgdw, Integer sddscpDzz,
			Integer sddscpZb, String sddscpJgdwjob, String sddscpDzzjob,
			String sddscpZbjob, String sddscpAssess,String sddscpChanges,String sddscpChangestype,
			String sddscpJgdwname,String sddscpDzzname,String sddscpZbname,
			boolean disabled, CmsUserExt userExt,
			Map<String, String> attr,boolean isPage,Integer sddspoIsLianHe) {	//isPage 判断是否从页面调用方法 true从页面 false内部调用

		if(StringUtils.isBlank(password)){
			password = "123";
		}
		UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
				password, ip);
		CmsUser user = new CmsUser();
		user.forMember(unifiedUser);
		user.setGrain(grain);
		user.setAttr(attr);
		user.setDisabled(disabled);
		CmsGroup group = null;
		if (groupId != null) {
			group = cmsGroupMng.findById(groupId);
		} else {
			group = cmsGroupMng.getRegDef();
		}
		if (group == null) {
			throw new RuntimeException(
					"register default member group not found!");
		}
		user.setGroup(group);
		if(userExt != null){
			try {
				if(userExt.getGender()){
					user.setSddscpSex("1");	//男
				}else if(!userExt.getGender()){
					user.setSddscpSex("2"); //女
				}else {
					user.setSddscpSex("");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("性别为保密状态时触发java.lang.NullPointerException");
				e.printStackTrace();
			}
			if(userExt.getBirthday()!=null){
				user.setSddscpBirthday(userExt.getBirthday());	//生日
			}
			if(!StringUtils.isBlank(userExt.getPhone())){
				user.setSddscpPhone(userExt.getPhone());	//电话
			}
			if(!StringUtils.isBlank(userExt.getMobile())){
				user.setSddscpMobile(userExt.getMobile());	//手机
			}
		}
		if(!isPage){
			if(departmentId!=null){
				user.setDepartment(cmsDepartmentMng.findById(departmentId));	//所在支部
			}
		}else {
			/*
			if(sddscpJgdw!=null){
				user.setDepartment(cmsDepartmentMng.findById(sddscpJgdw));
			}else if(sddscpDzz!=null){
				user.setDepartment(cmsDepartmentMng.findById(sddscpDzz));
			}else if(sddscpZb!=null){
				user.setDepartment(cmsDepartmentMng.findById(sddscpZb));
			}else{}
			*/
			user.setDepartment(cmsDepartmentMng.findById(departmentId));
		}
		user.setSddscpIdnumber(sddscpIdnumber);
		user.setSddscpNational(sddscpNational);
		user.setSddscpAddress(sddscpAddress);
		user.setSddscpPoliticaltype(sddscpPoliticaltype);
		user.setSddscpPartyposition(sddscpPartyposition);
		user.setSddscpBasescore(sddscpBasescore);
		//新建人员时默认分数为0
		user.setSddscpXfscore(0);
		user.setSddscpKfscore("0");
		user.setSddscpSumscore("0");
		user.setSddscpJoinpartydate(sddscpJoinpartydate);
		user.setSddscpEbranchdate(sddscpEbranchdate);
		user.setSddscpJoinworktime(sddscpJoinworktime);
		user.setSddscpEducation(sddscpEducation);
		user.setSddscpGraduate(sddscpGraduate);
		user.setSddscpDegree(sddscpDegree);
		user.setSddscpMatrimonial(sddscpMatrimonial);
		user.setSddscpWorkplace(sddscpWorkplace);
		user.setSddscpJobposition(sddscpJobposition);
		user.setSddscpNative(sddscpNative);
		user.setSddscpResidence(sddscpResidence);
		user.setSddscpOtherphone(sddscpOtherphone);
		user.setSddscpUsertype(sddscpUsertype);
		user.setSddscpOrgname(departName);
		user.setSddscpExcellentcause(sddscpExcellentcause);
		user.setSddscpUnqualifiedcause(sddscpUnqualifiedcause);
		user.setSddscpIsinjob(sddscpIsinjob);
		user.setSddscpOutpartytype(sddscpOutpartytype);
		user.setSddscpOutpartycause(sddscpOutpartycause);
		user.setSddscpIsperororg(sddscpIsperororg);	//设置注册者为人员
		user.setSddscpOrgloginname(sddscpOrgloginname);
		user.setSddscpOrglogincode(sddscpOrglogincode);
		user.setSddscpJgdw(sddscpJgdw);
		user.setSddscpDzz(sddscpDzz);
		user.setSddscpZb(sddscpZb);
		user.setSddscpAssess(sddscpAssess);
		user.setSddscpDzzjob(sddscpDzzjob);
		user.setSddscpJgdwjob(sddscpJgdwjob);
		user.setSddscpZbjob(sddscpZbjob);
		user.setSddscpChanges(sddscpChanges);
		user.setSddscpChangestype(sddscpChangestype);
		user.setSddscpJgdwname(sddscpJgdwname);
		user.setSddscpDzzname(sddscpDzzname);
		user.setSddscpZbname(sddscpZbname);
		user.setSddscpJobstatus(sddscpJobstatus);
		if("2".equals(sddscpIsperororg)){	//如果为组织 则：...
			user.setAdmin(true);	//设置为admin
			user.setRank(9);		//等级为9(最高审核级别)
		}
		
		user.init();
		CmsUser model = dao.save(user);
		
		if(userExt != null){
			cmsUserExtMng.save(userExt, user);
		}
		/*CmsDepartment depart = departmentDao.findById(departmentId);
		depart.setSddspoSecretary(username);
		depart.setSddspoSecretaryid(model.getId());
		depart.setSddspoSecretaryidc(sddscpIdnumber);
		departmentDao.updatejob(depart);*/
		if(isPage){
			/*
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			CmsFloating floating = new CmsFloating();
			floating.setSddsfiUserid(model.getId());
			floating.setSddsfiUsername(model.getUsername());
			floating.setSddsfiOrgid(model.getDepartment().getId());
			floating.setSddsfiOrgname(model.getDepartment().getName());
			floating.setSddsfiIncounty("0");
			floating.setSddsfiInprovince("0");
			floating.setSddsfiIncity("0");
			floating.setSddsfiOutcounty("0");
			floating.setSddsfiOutprovince("0");
			floating.setSddsfiOutcity("0");
			String date = sdf.format(new Date());
			String [] dates = date.split("-");
			floating.setSddsfiYear(dates[0]);
			floating.setSddsfiMonth(dates[1]);
			floating.setSddsfiDay(dates[2]);
			if("4".equals(sddscpChanges)){
				floating.setSddsfiIncounty("1");
				floating.setSddsfiInprovince("1");
				floating.setSddsfiIncity("1");
			}else if("3".equals(sddscpChanges)){
				floating.setSddsfiInprovince("1");
				floating.setSddsfiIncity("1");
			}else if("2".equals(sddscpChanges)){
				floating.setSddsfiInprovince("1");
			}else if("8".equals(sddscpChanges)){
				floating.setSddsfiOutcounty("1");
				floating.setSddsfiOutprovince("1");
				floating.setSddsfiOutcity("1");
			}else if("7".equals(sddscpChanges)){
				floating.setSddsfiOutprovince("1");
				floating.setSddsfiOutcity("1");
			}else if("6".equals(sddscpChanges)){
				floating.setSddsfiOutprovince("1");
			}else {}
			cmsFloatingMng.save(floating);
			*/
		}
		return user;
	
	}
	/**
	 * 重写登录方法
	 */
	@Transactional(readOnly = true)
	public CmsUser findByLogincode(String logincode){
		CmsUser user = dao.findByLogincode(logincode);
		return user;
	}

	@Override
	public List<CmsUser> queryMeberByName(String name) {
		
		return dao.queryMeberByName(name);
	}
	
	 /**
	  * 支部换届
	  */
	 public CmsUser changeSecretaryByUid(Integer oid,Integer nid){
		 CmsUser ouser = findById(oid);
		 CmsUser nuser = findById(nid);
		 CmsDepartment ndepartment = departmentDao.findById(nuser.getDepartment().getId());
		 CmsDepartment odepartment = departmentDao.findById(ouser.getDepartment().getId());
		 dao.changeSecretaryczByUid(ouser,odepartment.getSddspoOrgtype());	//撤职
		 dao.changeSecretaryrzByUid(nuser,ndepartment.getSddspoOrgtype());	//任职
		 return nuser;
	 }
	 
	 /**
	  * 批量替换user.departid数据
	  * 备注：
	  * 经239测试数据库测试后，展示以下数据：
	  * 1.表jc_user内，sddscp_isperororg=2（即组织数据）且depart_id=1的数据共147条
	  * 2.经第一次修改后，剩余44条记录（这44条记录即depart_name未trim导致）
	  * 3.经第二次修改后，剩余3条记录（这三条记录即存在于jc_user表，但不存在于jc_department表）
	  * ————其username为：岛市地方税务局崂山分局北宅中心税务所党支部、青岛市地方税务局经济技术开发区局收入核算和财务处党、中共平度市地方税务局云山中心税务所党支部
	  */
	 public void finduserdatetool(boolean all){
		 List<CmsUser> userList = dao.findalllistuser(all);
		 List<CmsDepartment> departList = departmentDao.findalllistdepart();
		 int k = 0;
		 int z = 0;
		 int n = 0;
		 for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < departList.size(); j++) {
				z++;
				System.out.println("已执行："+z+"条记录");
				if(userList.get(i).getUsername().trim().equals(departList.get(j).getName().trim())){
					n++;
					System.out.println("=================比较成功了："+n+"次");
					if(!(userList.get(i).getDepartment().getId().equals(departList.get(j).getId()))){
						dao.changeuserdatatool(departList.get(j).getId(), userList.get(i).getId());
						k++;
						System.out.println("已修改："+k+"条记录；该记录的名称为：   "+ userList.get(i).getUsername() +"   该记录的userid为："+ userList.get(i).getId() +"该记录的组织实际id为："+ departList.get(j).getId());
					}
				}
			}
		}
	 }
	 
	 /**
	  * 评分列表
	  */
	 public List<CmsUser> scoreUserList(Integer departid, Map<String, String> m){
		 CmsDepartment department = cmsDepartmentMng.findById(departid);
		 String orgtype = "jgdw";
		 if("支部".equals(department.getSddspoOrgtype())){
			 orgtype = "zb";
		 }
		 else if("党总支".equals(department.getSddspoOrgtype())){
			 orgtype = "dzz";
		 }else{}
		 return dao.scoreUserList(departid,orgtype, m);
	 }

	 /**
	  * 更新分数
	  */
	 public CmsUser updateScore(CmsUser model){
		 dao.updateScore(model);
		 return model;
	 }
	 public Pagination memberListAndYear(CmsUser user,int pageNo, int pageSize,boolean isadmin,String risenYear){
		 return dao.memberListAndYear(user, pageNo, pageSize,isadmin,risenYear);
	 }
	 public List<CmsUser> memberListAndids(CmsUser user,int pageNo, int pageSize,boolean isadmin,String ids){
		 return dao.memberListAndids(user, pageNo, pageSize,isadmin,ids);
	 }
	 public Pagination memberList(CmsUser user,int pageNo, int pageSize,boolean isadmin){
		 return dao.memberList(user, pageNo, pageSize,isadmin);
	 }

	@Override
	public List<CmsUser> memberListexcel(CmsUser user, String PageNo,String ids,String pageSize) {
		return dao.memberListexcel(user,PageNo,ids,pageSize);
	}
	public CmsUser updateAssess(CmsUser user){
		return dao.updateAssess(user);
	}
	public CmsUser recovery(Integer id){
		CmsUser user = new CmsUser();
		user.setId(id);
		return dao.recovery(user);
	}

	@Override
	public Pagination getMemberByDepartId(Integer departId,
			String sddscpAssess, String sddscpIsinjob, String sddscpChanges,
			int pageNo, int pageSize, String risendsYear) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CmsUser> exportMemberList(CmsUser user, int pageNo,
			int pageSize, boolean isadmin, String ids) {
		
		return dao.exportMemberList(user, pageNo, pageSize, isadmin, ids);
	}

	@Override
	public List<CmsUser> getMemberByQXandDepartIdExport(Integer departId,
			String sddscpAssess, String sddscpIsinjob, String sddscpChanges,
			int pageNo, int pageSize, String ids) {
		
		return dao.getMemberQXByDepartIdExport(departId, sddscpAssess, sddscpIsinjob, sddscpChanges, pageNo, pageSize, ids);
	}

	@Override
	public boolean existUserByIdnumber(String idNumber) {
		// TODO Auto-generated method stub
		List<CmsUser> users = dao.getUserByIdNumber(idNumber);
		if(users!=null){
			if(users.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	@Override
	public Pagination getInPartyUser(int pageNo, int pageSize, String inType,
			String deptIds) {
		// TODO Auto-generated method stub
		return dao.getInPartyUser( pageNo, pageSize,inType,deptIds);
	}

	@Override
	public Pagination getOutPartyUser(int pageNo, int pageSize, String outType,
			String deptIds) {
		// TODO Auto-generated method stub
		return dao.getOutPartyUser( pageNo, pageSize,outType,deptIds);
	}
	
	 //根据组织获取人员
	 public CmsUser findByDpId(Integer depid){
		 return dao.findByDpId(depid);
	 }
}