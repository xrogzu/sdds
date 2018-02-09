package com.jeecms.core.dao;

import java.util.List;
import java.util.Map;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;

/**
 * 用户DAO接口
 */
public interface CmsUserDao{
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

	public int countByUsername(String username);
	
	public int countMemberByUsername(String username);

	public int countByEmail(String email);

	public CmsUser save(CmsUser bean);

	public CmsUser updateByUpdater(Updater<CmsUser> updater);

	public CmsUser deleteById(Integer id);
	
	public boolean loginCodeExist(long codeId);
	/**
	 * @author:dongliang
	 * @date:2016/10/11
	 * 功能:根据身份证号查询人员的信息
	 */
	public CmsUser findByUserCardId(String userCardId);
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
	public Pagination getMemberByDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize);
    /**
	 * 根据组织ID权限查人员
	 * @param departId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getMemberQXByDepartId(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize);
	public Pagination getMemberQXByDepartIdAndYear(Integer departId,Integer newDeptId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String risendsYear,String sddscpHistorydy);
	public List<CmsUser> getMemberByQXandDepartIdAndYids(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String ids,String username);
	public List<CmsUser> getMemberQXByDepartIdExport(Integer departId,String sddscpAssess,String sddscpIsinjob,String sddscpChanges,int pageNo,int pageSize,String ids);
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
	  * 撤职
	  */
	 public CmsUser changeSecretaryczByUid(CmsUser user,String orgtype);
	 /**
	  * 支部换届
	  * 任职
	  */
	 public CmsUser changeSecretaryrzByUid(CmsUser user,String orgtype);

	/**
	 * 修改user数据工具
	 * @param departid
	 * @param userid
	 */
    public void changeuserdatatool(Integer departid,Integer userid);
    /**
	 * 修改user数据工具
	 */
	public List<CmsUser> findalllistuser(boolean all);
	/**
	  * 评分列表
	  */
	 public List<CmsUser> scoreUserList(Integer departid,String orgtype, Map<String, String> m);
	 /**
	  * 更新分数
	  */
	 public CmsUser updateScore(CmsUser model);
	 
	 public Pagination memberList(CmsUser user,int pageNo, int pageSize,boolean isadmin);
	 public List<CmsUser> exportMemberList(CmsUser user,int pageNo, int pageSize,boolean isadmin,String ids);
	 /**
	  * 
	  * @param user
	  * @param pageNo
	  * @param pageSize
	  * @return
	  */
	 public List<CmsUser> memberListexcel(CmsUser user, String PageNo,String ids,String pageSize);

	 public Pagination assess(String orgtype,Integer departid,CmsUser user,int pageNo, int pageSize);
	 
	 public CmsUser updateAssess(CmsUser user);
	 public CmsUser recovery(CmsUser model);
	 public List<CmsUser> memberListAndids(CmsUser user, int pageNo, int pageSize,boolean isadmin, String ids);
	 public Pagination memberListAndYear(CmsUser user, int pageNo, int pageSize,boolean isadmin, String risenYear);
	 //判断身份证号是否已存在
	 public List<CmsUser> getUserByIdNumber(String idNumber);
	 //根据姓名和身份证号来得到唯一的用户
	 public List<CmsUser> getUserByIdNumberAndUsername(String idNumber,String username);
	 //修改党员信息
	 public CmsUser updateUserInfo(CmsUser user);
	 //获取转入人员
	 public Pagination getInPartyUser(int pageNo,int pageSize,String inType,String deptIds);
	 //获取转出人员
	 public Pagination getOutPartyUser(int pageNo,int pageSize,String outType,String deptIds);
	 //根据组织获取人员
	 public CmsUser findByDpId(Integer depid);
}