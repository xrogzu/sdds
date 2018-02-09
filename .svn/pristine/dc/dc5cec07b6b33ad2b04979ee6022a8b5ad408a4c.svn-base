package com.jeecms.core.manager;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;

public interface CmsUserMng {
	public Pagination getPage(String username, String sddscpIdnumber, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
			String realName,Integer departId,Integer roleId,
			Boolean allChannel,Boolean allControlChannel,
			int pageNo, int pageSize,String sddscpUsertype,String isadminlist);
	
	public List<CmsUser> getList(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank);

	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank);
	
	public Pagination getAdminsByDepartId(Integer id, int pageNo,int pageSize);
	
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize);

	public CmsUser findById(Integer id);

	public CmsUser findByUsername(String username);

/**
 * 
 *	user.setSddscpJgdw(sddscpJgdw);
		user.setSddscpDzz(sddscpDzz);
		user.setSddscpZb(sddscpZb);
		user.setSddscpAssess(sddscpAssess);
		user.setSddscpDzzjob(sddscpDzzjob);
		user.setSddscpJgdwjob(sddscpJgdwjob);
		user.setSddscpZbjob(sddscpZbjob);
 */
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId,Integer grain, String sddscpIdnumber,String sddscpNational,String sddscpAddress,String sddscpPoliticaltype,
			String sddscpPartyposition,String sddscpBasescore,Integer sddscpXfscore,String sddscpKfscore,
			String sddscpSumscore,String sddscpOrgname,Date sddscpJoinpartydate,Date sddscpEbranchdate,
			Date sddscpJoinworktime,String sddscpEducation,String sddscpGraduate,String sddscpDegree,
			String sddscpMatrimonial,String sddscpWorkplace,String sddscpJobposition,String sddscpNative,
			String sddscpResidence,String sddscpOtherphone,String sddscpUsertype,Integer departmentId,String departName,
			String sddscpExcellentcause,String sddscpUnqualifiedcause,String sddscpIsinjob,String sddscpOutpartytype,String sddscpOutpartycause,String sddscpJobstatus,
			String sddscpIsperororg,String sddscpOrgloginname,String sddscpOrglogincode,
			Integer sddscpJgdw,Integer sddscpDzz,Integer sddscpZb,String sddscpJgdwjob,String sddscpDzzjob,String sddscpZbjob,String sddscpAssess,
			String sddscpChanges,String sddscpChangestype,String sddscpJgdwname,String sddscpDzzname,String sddscpZbname,
			boolean disabled,CmsUserExt userExt,Map<String,String>attr,boolean isPage,Integer sddspoIsLianHe);
	
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId, boolean disabled,CmsUserExt userExt,Map<String,String>attr, Boolean activation , EmailSender sender, MessageTemplate msgTpl)throws UnsupportedEncodingException, MessagingException ;

	public void updateLoginInfo(Integer userId, String ip,Date loginTime,String sessionId);

	public void updateUploadSize(Integer userId, Integer size);
	
	public void updateUser(CmsUser user);

	public void updatePwdEmail(Integer id, String password, String email);

	public boolean isPasswordValid(Integer id, String password);

	public CmsUser saveAdmin(String username, String email, String password,
			String ip, boolean viewOnly, boolean selfAdmin, int rank,
			Integer groupId, Integer departmentId,Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,Boolean[] allControlChannels,
			CmsUserExt userExt);

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId,Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,Boolean[] allControlChannels);

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId, Integer[] roleIds, Integer[] channelIds,
			Integer siteId, Byte step, Boolean allChannel,Boolean allControlChannel);

	public CmsUser updateMember(Integer id, String email, String password,
			Boolean isDisabled, CmsUserExt ext, Integer groupId,Integer grain,String sddscpIdnumber,String sddscpNational,String sddscpAddress,String sddscpPoliticaltype,
			String sddscpPartyposition,String sddscpBasescore,Integer sddscpXfscore,String sddscpKfscore,
			String sddscpSumscore,String sddscpOrgname,Date sddscpJoinpartydate,Date sddscpEbranchdate,
			Date sddscpJoinworktime,String sddscpEducation,String sddscpGraduate,String sddscpDegree,
			String sddscpMatrimonial,String sddscpWorkplace,String sddscpJobposition,String sddscpNative,
			String sddscpResidence,String sddscpOtherphone,String sddscpUsertype,Integer departmentId,String departName,
			String sddscpExcellentcause,String sddscpUnqualifiedcause,String sddscpIsinjob,String sddscpOutpartytype,String sddscpOutpartycause,
			String sddscpJobstatus,
			Integer sddscpJgdw,Integer sddscpDzz,Integer sddscpZb,String sddscpJgdwjob,String sddscpDzzjob,String sddscpZbjob,String sddscpAssess,
			String sddscpChanges,String sddscpChangestype,String sddscpJgdwname,String sddscpDzzname,String sddscpZbname,
			Map<String,String>attr, HttpServletRequest request,String targetDeptName);
	
	public CmsUser updateMember(Integer id, String email, String password,Integer groupId,String realname,String mobile,Boolean sex);
	
	public CmsUser updateUserConllection(CmsUser user,Integer cid,Integer operate);

	public void addSiteToUser(CmsUser user, CmsSite site, Byte checkStep);

	public CmsUser deleteById(Integer id);

	public CmsUser[] deleteByIds(Integer[] ids);

	public boolean usernameNotExist(String username);
	
	public boolean usernameNotExistInMember(String username);

	public boolean emailNotExist(String email);
	
	public boolean loginCodeExist(long codeId);
	/**
	 * 根据身份证来查询人员信息
	 * date:2016/10/11
	 * author:dongliang
	 */
    public CmsUser findByUserCardId(String userCardId);
    
    /**
     * 获取字典键值列表
     * @param corecdType
     * @return
     */
    public List<CmsUser> getCorecdKeyList(String corecdType);

    /**
     * 根据组织ID删除数据
     * 删除组织时，级联删除user表数据(仅限删除组织时使用)
     */
    public Integer deleteUserBydepartid(Integer departId);
    /**
	 * 根据组织ID查人员
	 * @param departId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberByDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String risendsYear);

    /**
	 * 根据组织ID权限查人员
	 * @param departId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberByQXandDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize);
	public Pagination getMemberByQXandDepartIdAndYear(Integer departId,Integer newDeptId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String risendsYear,String sddscpHistorydy);
	public List<CmsUser> getMemberByQXandDepartIdAndYids(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String ids,String username);
	public List<CmsUser> getMemberByQXandDepartIdExport(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String ids);
	/**
	 * 重写登录方法
	 */
	public CmsUser findByLogincode(String logincode);
	/**
	 *  模糊查询
	 * @author slc 2016-12-21 下午9:10:43
	 * @param name
	 * @return
	 */
	 public List<CmsUser> queryMeberByName(String name);
	 /**
	  * 支部换届
	  */
	 public CmsUser changeSecretaryByUid(Integer oid,Integer nid);
	 /**
	  * 修改user数据工具
	  */
	 public void finduserdatetool(boolean all);
	 /**
	  * 评分列表
	  */
	 public List<CmsUser> scoreUserList(Integer departid, Map<String, String> m);

	 /**
	  * 更新分数
	  */
	 public CmsUser updateScore(CmsUser model);
	 
	 public Pagination memberList(CmsUser user,int pageNo, int pageSize,boolean isadmin);
	 public Pagination memberListAndYear(CmsUser user,int pageNo, int pageSize,boolean isadmin,String risendsYear);
	 public List<CmsUser>  memberListAndids(CmsUser user,int pageNo, int pageSize,boolean isadmin,String ids);
	 public List<CmsUser> exportMemberList(CmsUser user,int pageNo, int pageSize,boolean isadmin,String ids);
	 public Pagination assess(String orgtype,Integer departid,CmsUser user,int pageNo, int pageSize);
	 public List<CmsUser> memberListexcel(CmsUser user, String PageNo,String ids,String pageSize);
	 public CmsUser updateAssess(CmsUser user);
	 public CmsUser recovery(Integer id);
	 public boolean existUserByIdnumber(String idNumber);
	 //获取转入人员
	 public Pagination getInPartyUser(int pageNo,int pageSize,String inType,String deptIds);
	 //获取转出人员
	 public Pagination getOutPartyUser(int pageNo,int pageSize,String outType,String deptIds);
	 //根据组织获取人员
	 public CmsUser findByDpId(Integer depid);
}