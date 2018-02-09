package com.jeecms.core.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsLog;


/**
 * This is an object that contains data related to the jc_user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_user"
 */

public abstract class BaseCmsUser  implements Serializable {

	public static String REF = "CmsUser";
	public static String PROP_REGISTER_TIME = "registerTime";
	public static String PROP_LOGIN_COUNT = "loginCount";
	public static String PROP_SELF_ADMIN = "selfAdmin";
	public static String PROP_UPLOAD_TOTAL = "uploadTotal";
	public static String PROP_LAST_LOGIN_IP = "lastLoginIp";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_LAST_LOGIN_TIME = "lastLoginTime";
	public static String PROP_UPLOAD_DATE = "uploadDate";
	public static String PROP_GROUP = "group";
	public static String PROP_EMAIL = "email";
	public static String PROP_UPLOAD_SIZE = "uploadSize";
	public static String PROP_RANK = "rank";
	public static String PROP_VIEWONLY_ADMIN = "viewonlyAdmin";
	public static String PROP_ADMIN = "admin";
	public static String PROP_ID = "id";
	public static String PROP_REGISTER_IP = "registerIp";
	public static String PROP_USERNAME = "username";


	// constructors
	public BaseCmsUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsUser (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsUser (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsGroup group,
		java.lang.String username,
		java.util.Date registerTime,
		java.lang.String registerIp,
		java.lang.Integer loginCount,
		java.lang.Integer rank,
		java.lang.Long uploadTotal,
		java.lang.Integer uploadSize,
		java.lang.Boolean admin,
		java.lang.Boolean viewonlyAdmin,
		java.lang.Boolean selfAdmin,
		java.lang.Boolean disabled) {

		this.setId(id);
		this.setGroup(group);
		this.setUsername(username);
		this.setRegisterTime(registerTime);
		this.setRegisterIp(registerIp);
		this.setLoginCount(loginCount);
		this.setRank(rank);
		this.setUploadTotal(uploadTotal);
		this.setUploadSize(uploadSize);
		this.setAdmin(admin);
		this.setViewonlyAdmin(viewonlyAdmin);
		this.setSelfAdmin(selfAdmin);
		this.setDisabled(disabled);
		initialize();
	}

	public BaseCmsUser (
			java.lang.Integer id,
			com.jeecms.core.entity.CmsGroup group,
			java.lang.String username,
			java.util.Date registerTime,
			java.lang.String registerIp,
			java.lang.Integer loginCount,
			java.lang.Integer rank,
			java.lang.Long uploadTotal,
			java.lang.Integer uploadSize,
			java.lang.Boolean admin,
			java.lang.Boolean viewonlyAdmin,
			java.lang.Boolean selfAdmin,
			java.lang.Boolean disabled,
			String sddscpSex,String sddscpIdnumber,Date sddscpBirthday,String sddscpNational,String sddscpPoliticaltype,String sddscpNative,String sddscpEducation,
			String sddscpWorkplace,String sddscpPartyposition,String sddscpJobposition,String sddscpLevel,String sddscpPhone,String sddscpMobile,String sddscpOtherphone,
			String sddscpAddress,String sddscpWorkstatue,String sddscpCitynumber,String sddscpUsertype,String sddscpDealtype,String sddscpBasescore,Integer sddscpXfscore,
			String sddscpKfscore,String sddscpSumscore,String sddscpDevelopmentnum,String sddscpResidence,String sddscpMatrimonial,Date sddscpEbranchdate,String sddscpPaydues,
			String sddscpGraduate,Date sddscpJoinworktime,String sddscpOrgname,String sddscpDegree,String sddscpPmisleaveout,String sddscpNamespell,Date sddscpJoinpartydate,
			String sddscpTypeid,String sddscpHistory,String sddscpHistorytype,Date sddscpKcdjsj,String sddscpKcdyyy,Date sddscpSwsj,String sddscpZcd,String sddscpZczzorg,
			String sddscpZcdate,String sddscpInorn,String sddscpExcellentcause, String sddscpUnqualifiedcause,String sddscpOutpartytype,String sddscpJobstatus,String sddscpIsinjob,
			String sddscpOutpartycause,String sddscpIsperororg,String sddscpOrgloginname,String sddscpOrglogincode,Integer sddscpJgdw,Integer sddscpDzz,Integer sddscpZb,String sddscpJgdwjob,
			String sddscpDzzjob,String sddscpZbjob,String sddscpAssess,String sddscpChanges,String sddscpChangestype,String sddscpJgdwname,String sddscpDzzname,String sddscpZbname) {

			this.setId(id);
			this.setGroup(group);
			this.setUsername(username);
			this.setRegisterTime(registerTime);
			this.setRegisterIp(registerIp);
			this.setLoginCount(loginCount);
			this.setRank(rank);
			this.setUploadTotal(uploadTotal);
			this.setUploadSize(uploadSize);
			this.setAdmin(admin);
			this.setViewonlyAdmin(viewonlyAdmin);
			this.setSelfAdmin(selfAdmin);
			this.setDisabled(disabled);
			this.setSddscpSex(sddscpSex);
			this.setSddscpIdnumber(sddscpIdnumber);
			this.setSddscpBirthday(sddscpBirthday);
			this.setSddscpNational(sddscpNational);
			this.setSddscpPoliticaltype(sddscpPoliticaltype);
			this.setSddscpNative(sddscpNative);
			this.setSddscpEducation(sddscpEducation);
			this.setSddscpWorkplace(sddscpWorkplace);
			this.setSddscpPartyposition(sddscpPartyposition);
			this.setSddscpJobposition(sddscpJobposition);
			this.setSddscpLevel(sddscpLevel);
			this.setSddscpPhone(sddscpPhone);
			this.setSddscpMobile(sddscpMobile);
			this.setSddscpOtherphone(sddscpOtherphone);
			this.setSddscpAddress(sddscpAddress);
			this.setSddscpWorkstatue(sddscpWorkstatue);
			this.setSddscpCitynumber(sddscpCitynumber);
			this.setSddscpUsertype(sddscpUsertype);
			this.setSddscpDealtype(sddscpDealtype);
			this.setSddscpBasescore(sddscpBasescore);
			this.setSddscpXfscore(sddscpXfscore);
			this.setSddscpKfscore(sddscpKfscore);
			this.setSddscpSumscore(sddscpSumscore);
			this.setSddscpDevelopmentnum(sddscpDevelopmentnum);
			this.setSddscpResidence(sddscpResidence);
			this.setSddscpMatrimonial(sddscpMatrimonial);
			this.setSddscpEbranchdate(sddscpEbranchdate);
			this.setSddscpPaydues(sddscpPaydues);
			this.setSddscpGraduate(sddscpGraduate);
			this.setSddscpJoinworktime(sddscpJoinworktime);
			this.setSddscpOrgname(sddscpOrgname);
			this.setSddscpDegree(sddscpDegree);
			this.setSddscpPmisleaveout(sddscpPmisleaveout);
			this.setSddscpNamespell(sddscpNamespell);
			this.setSddscpJoinpartydate(sddscpJoinpartydate);
			this.setSddscpTypeid(sddscpTypeid);
			this.setSddscpHistory(sddscpHistory);
			this.setSddscpHistorytype(sddscpHistorytype);
			this.setSddscpKcdjsj(sddscpKcdjsj);
			this.setSddscpKcdyyy(sddscpKcdyyy);
			this.setSddscpSwsj(sddscpSwsj);
			this.setSddscpZcd(sddscpZcd);
			this.setSddscpZczzorg(sddscpZczzorg);
			this.setSddscpZcdate(sddscpZcdate);
			this.setSddscpInorn(sddscpInorn);
			this.setSddscpExcellentcause(sddscpExcellentcause);
			this.setSddscpUnqualifiedcause(sddscpUnqualifiedcause);
			this.setSddscpOutpartytype(sddscpOutpartytype);
			this.setSddscpJobstatus(sddscpJobstatus);
			this.setSddscpIsinjob(sddscpIsinjob);
			this.setSddscpOutpartycause(sddscpOutpartycause);
			this.setSddscpIsperororg(sddscpIsperororg);
			this.setSddscpOrgloginname(sddscpOrgloginname);
			this.setSddscpOrglogincode(sddscpOrglogincode);
			this.setSddscpJgdw(sddscpJgdw);
			this.setSddscpDzz(sddscpDzz);
			this.setSddscpZb(sddscpZb);
			this.setSddscpJgdwjob(sddscpJgdwjob);
			this.setSddscpDzzjob(sddscpDzzjob);
			this.setSddscpZbjob(sddscpZbjob);
			this.setSddscpAssess(sddscpAssess);
			this.setSddscpChanges(sddscpChanges);
			this.setSddscpChangestype(sddscpChangestype);
			this.setSddscpJgdwname(sddscpJgdwname);
			this.setSddscpDzzname(sddscpDzzname);
			this.setSddscpZbname(sddscpZbname);
			initialize();
		}
	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String username;
	private java.lang.String email;
	private java.util.Date registerTime;
	private java.lang.String registerIp;
	private java.util.Date lastLoginTime;
	private java.lang.String lastLoginIp;
	private java.lang.Integer loginCount;
	private java.lang.Integer rank;
	private java.lang.Long uploadTotal;
	private java.lang.Integer uploadSize;
	private java.sql.Date uploadDate;
	private java.lang.Boolean admin;
	private java.lang.Boolean viewonlyAdmin;
	private java.lang.Boolean selfAdmin;
	private java.lang.Boolean disabled;
	private java.lang.Integer fileTotal;
	private java.lang.Integer grain;
	private java.lang.String sessionId;
	
	private String sddscpSex;
	private String sddscpIdnumber;
	private Date sddscpBirthday;
	private String sddscpNational;
	private String sddscpPoliticaltype;
	private String sddscpNative;
	private String sddscpEducation;
	private String sddscpWorkplace;
	private String sddscpPartyposition;
	private String sddscpJobposition;
	private String sddscpLevel;
	private String sddscpPhone;
	private String sddscpMobile;
	private String sddscpOtherphone;
	private String sddscpAddress;
	private String sddscpWorkstatue;
	private String sddscpCitynumber;
	private String sddscpUsertype;
	private String sddscpDealtype;
	private String sddscpBasescore;
	private Integer sddscpXfscore;
	private String sddscpKfscore;
	private String sddscpSumscore;
	private String sddscpDevelopmentnum;
	private String sddscpResidence;
	private String sddscpMatrimonial;
	private Date sddscpEbranchdate;
	private String sddscpPaydues;
	private String sddscpGraduate;
	private Date sddscpJoinworktime;
	private String sddscpOrgname;
	private String sddscpDegree;
	private String sddscpPmisleaveout;
	private String sddscpNamespell;
	private Date sddscpJoinpartydate;
	private String sddscpTypeid;
	private String sddscpHistory;
	private String sddscpHistorytype;
	private Date sddscpKcdjsj;
	private String sddscpKcdyyy;
	private Date sddscpSwsj;
	private String sddscpZcd;
	private String sddscpZczzorg;
	private String sddscpZcdate;
	private String sddscpInorn;
	private String sddscpExcellentcause;
	private String sddscpUnqualifiedcause;
	private String sddscpOutpartytype;
	private String sddscpJobstatus;
	private String sddscpIsinjob;
	
	private String sddscpOutpartycause;
	private String sddscpIsperororg;
	private String sddscpOrgloginname;
	private String sddscpOrglogincode;
	/**
	 * SDDSCP_JGDW
	 * SDDSCP_DZZ
	 * SDDSCP_ZB
	 * SDDSCP_JGDWJOB
	 * SDDSCP_DZZJOB
	 * SDDSCP_ZBJOB
	 */
	private Integer sddscpJgdw;//所属机关党委
	private Integer sddscpDzz;//所属党总支
	private Integer sddscpZb;//所属支部
	private String sddscpJgdwjob;//所在机关党委职位
	private String sddscpDzzjob;//所在党总支职位
	private String sddscpZbjob;//所在支部职位
	private String sddscpAssess;//民主评议
	private String sddscpChanges;//人员变动值
	private String sddscpChangestype;//人员变动属性
	private String sddscpJgdwname;//所在机关党委名称
	private String sddscpDzzname;//所在机关党总支名称
	private String sddscpZbname;//所在机关党委名称
	private String sddscpPfsmjc;
	private String sddscpPfsmfj;
	private Integer sddscpJcf;
	private Integer sddscpFjf;
	private String sddscpAssessyear;
	private String sddscpHistorydy;//党员历史组织
	private String sddscpNewaddyd;//新增党员组织
	private Integer sddscpTxTargetLifeOrg;//是否属于退休转入目标生活地方其他部门组织

	public Integer getSddscpTxTargetLifeOrg() {
		return sddscpTxTargetLifeOrg;
	}

	public void setSddscpTxTargetLifeOrg(Integer sddscpTxTargetLifeOrg) {
		this.sddscpTxTargetLifeOrg = sddscpTxTargetLifeOrg;
	}

	public String getSddscpHistorydy() {
		return sddscpHistorydy;
	}

	public void setSddscpHistorydy(String sddscpHistorydy) {
		this.sddscpHistorydy = sddscpHistorydy;
	}

	public String getSddscpNewaddyd() {
		return sddscpNewaddyd;
	}

	public void setSddscpNewaddyd(String sddscpNewaddyd) {
		this.sddscpNewaddyd = sddscpNewaddyd;
	}
    
	
	//SddscpIsinjob


	/**============================          临时字段列     begin      ===============================**/
	private String key;			//数据字典的键
	private String value;		//数据字典的值
	
	
	
	/**============================          临时字段列     end        ===============================**/

	// many to one
	private com.jeecms.core.entity.CmsGroup group;
	private com.jeecms.core.entity.CmsDepartment department;

	// collections
	private java.util.Map<java.lang.String, java.lang.String> attr;
	private java.util.Set<com.jeecms.core.entity.CmsUserExt> userExtSet;
	private java.util.Set<com.jeecms.core.entity.CmsUserAccount> userAccountSet;
	private java.util.Set<com.jeecms.core.entity.CmsUserSite> userSites;
	private java.util.Set<com.jeecms.core.entity.CmsRole> roles;
	private java.util.Set<com.jeecms.cms.entity.main.Channel> channels;
	private java.util.Set<com.jeecms.cms.entity.main.Content> collectContents;
	private java.util.Set<com.jeecms.cms.entity.main.ContentBuy> buyContentSet;
	
	private java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> sendMessages;
	private java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> receivMessages;
	private java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> sendReceiverMessages;
	private java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> receivReceiverMessages;
	
	private java.util.Set<com.jeecms.cms.entity.assist.CmsJobApply> jobApplys;
	private java.util.Set<com.jeecms.core.entity.CmsUserResume> userResumeSet;
	private java.util.Set<CmsLog> logs;
	private java.util.Set<com.jeecms.cms.entity.assist.CmsUserMenu> menus;
	private java.util.Set<com.jeecms.core.entity.CmsWorkflowEventUser> eventUsers;
	private java.util.Set<com.jeecms.core.entity.CmsWorkflowRecord> checkRecords;
	private java.util.Set<com.jeecms.cms.entity.main.ContentRecord> contentRecordSet;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="user_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}


	/**
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}


	/**
	 * Return the value associated with the column: register_time
	 */
	public java.util.Date getRegisterTime () {
		return registerTime;
	}

	/**
	 * Set the value related to the column: register_time
	 * @param registerTime the register_time value
	 */
	public void setRegisterTime (java.util.Date registerTime) {
		this.registerTime = registerTime;
	}


	/**
	 * Return the value associated with the column: register_ip
	 */
	public java.lang.String getRegisterIp () {
		return registerIp;
	}

	/**
	 * Set the value related to the column: register_ip
	 * @param registerIp the register_ip value
	 */
	public void setRegisterIp (java.lang.String registerIp) {
		this.registerIp = registerIp;
	}


	/**
	 * Return the value associated with the column: last_login_time
	 */
	public java.util.Date getLastLoginTime () {
		return lastLoginTime;
	}

	/**
	 * Set the value related to the column: last_login_time
	 * @param lastLoginTime the last_login_time value
	 */
	public void setLastLoginTime (java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	/**
	 * Return the value associated with the column: last_login_ip
	 */
	public java.lang.String getLastLoginIp () {
		return lastLoginIp;
	}

	/**
	 * Set the value related to the column: last_login_ip
	 * @param lastLoginIp the last_login_ip value
	 */
	public void setLastLoginIp (java.lang.String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}


	/**
	 * Return the value associated with the column: login_count
	 */
	public java.lang.Integer getLoginCount () {
		return loginCount;
	}

	/**
	 * Set the value related to the column: login_count
	 * @param loginCount the login_count value
	 */
	public void setLoginCount (java.lang.Integer loginCount) {
		this.loginCount = loginCount;
	}


	/**
	 * Return the value associated with the column: rank
	 */
	public java.lang.Integer getRank () {
		return rank;
	}

	/**
	 * Set the value related to the column: rank
	 * @param rank the rank value
	 */
	public void setRank (java.lang.Integer rank) {
		this.rank = rank;
	}


	/**
	 * Return the value associated with the column: upload_total
	 */
	public java.lang.Long getUploadTotal () {
		return uploadTotal;
	}

	/**
	 * Set the value related to the column: upload_total
	 * @param uploadTotal the upload_total value
	 */
	public void setUploadTotal (java.lang.Long uploadTotal) {
		this.uploadTotal = uploadTotal;
	}


	/**
	 * Return the value associated with the column: upload_size
	 */
	public java.lang.Integer getUploadSize () {
		return uploadSize;
	}

	/**
	 * Set the value related to the column: upload_size
	 * @param uploadSize the upload_size value
	 */
	public void setUploadSize (java.lang.Integer uploadSize) {
		this.uploadSize = uploadSize;
	}


	/**
	 * Return the value associated with the column: upload_date
	 */
	public java.sql.Date getUploadDate () {
		return uploadDate;
	}

	/**
	 * Set the value related to the column: upload_date
	 * @param uploadDate the upload_date value
	 */
	public void setUploadDate (java.sql.Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	/**
	 * Return the value associated with the column: is_admin
	 */
	public java.lang.Boolean getAdmin () {
		return admin;
	}

	/**
	 * Set the value related to the column: is_admin
	 * @param admin the is_admin value
	 */
	public void setAdmin (java.lang.Boolean admin) {
		this.admin = admin;
	}


	/**
	 * Return the value associated with the column: is_viewonly_admin
	 */
	public java.lang.Boolean getViewonlyAdmin () {
		return viewonlyAdmin;
	}

	/**
	 * Set the value related to the column: is_viewonly_admin
	 * @param viewonlyAdmin the is_viewonly_admin value
	 */
	public void setViewonlyAdmin (java.lang.Boolean viewonlyAdmin) {
		this.viewonlyAdmin = viewonlyAdmin;
	}


	/**
	 * Return the value associated with the column: is_self_admin
	 */
	public java.lang.Boolean getSelfAdmin () {
		return selfAdmin;
	}

	/**
	 * Set the value related to the column: is_self_admin
	 * @param selfAdmin the is_self_admin value
	 */
	public void setSelfAdmin (java.lang.Boolean selfAdmin) {
		this.selfAdmin = selfAdmin;
	}


	/**
	 * Return the value associated with the column: is_disabled
	 */
	public java.lang.Boolean getDisabled () {
		return disabled;
	}

	/**
	 * Set the value related to the column: is_disabled
	 * @param disabled the is_disabled value
	 */
	public void setDisabled (java.lang.Boolean disabled) {
		this.disabled = disabled;
	}
	

	public java.lang.Integer getFileTotal() {
		return fileTotal;
	}

	public void setFileTotal(java.lang.Integer fileTotal) {
		this.fileTotal = fileTotal;
	}

	public java.lang.Integer getGrain() {
		return grain;
	}

	public void setGrain(java.lang.Integer grain) {
		this.grain = grain;
	}
	
	
	public java.lang.String getSessionId() {
		return sessionId;
	}

	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSddscpSex() {
		return sddscpSex;
	}

	public void setSddscpSex(String sddscpSex) {
		this.sddscpSex = sddscpSex;
	}

	public String getSddscpIdnumber() {
		return sddscpIdnumber;
	}

	public void setSddscpIdnumber(String sddscpIdnumber) {
		this.sddscpIdnumber = sddscpIdnumber;
	}

	public Date getSddscpBirthday() {
		return sddscpBirthday;
	}

	public void setSddscpBirthday(Date sddscpBirthday) {
		this.sddscpBirthday = sddscpBirthday;
	}

	public String getSddscpNational() {
		return sddscpNational;
	}

	public void setSddscpNational(String sddscpNational) {
		this.sddscpNational = sddscpNational;
	}

	public String getSddscpPoliticaltype() {
		return sddscpPoliticaltype;
	}

	public void setSddscpPoliticaltype(String sddscpPoliticaltype) {
		this.sddscpPoliticaltype = sddscpPoliticaltype;
	}

	public String getSddscpNative() {
		return sddscpNative;
	}

	public void setSddscpNative(String sddscpNative) {
		this.sddscpNative = sddscpNative;
	}

	public String getSddscpEducation() {
		return sddscpEducation;
	}

	public void setSddscpEducation(String sddscpEducation) {
		this.sddscpEducation = sddscpEducation;
	}

	public String getSddscpWorkplace() {
		return sddscpWorkplace;
	}

	public void setSddscpWorkplace(String sddscpWorkplace) {
		this.sddscpWorkplace = sddscpWorkplace;
	}

	public String getSddscpPartyposition() {
		return sddscpPartyposition;
	}

	public void setSddscpPartyposition(String sddscpPartyposition) {
		this.sddscpPartyposition = sddscpPartyposition;
	}

	public String getSddscpJobposition() {
		return sddscpJobposition;
	}

	public void setSddscpJobposition(String sddscpJobposition) {
		this.sddscpJobposition = sddscpJobposition;
	}

	public String getSddscpLevel() {
		return sddscpLevel;
	}

	public void setSddscpLevel(String sddscpLevel) {
		this.sddscpLevel = sddscpLevel;
	}

	public String getSddscpPhone() {
		return sddscpPhone;
	}

	public void setSddscpPhone(String sddscpPhone) {
		this.sddscpPhone = sddscpPhone;
	}

	public String getSddscpMobile() {
		return sddscpMobile;
	}

	public void setSddscpMobile(String sddscpMobile) {
		this.sddscpMobile = sddscpMobile;
	}

	public String getSddscpOtherphone() {
		return sddscpOtherphone;
	}

	public void setSddscpOtherphone(String sddscpOtherphone) {
		this.sddscpOtherphone = sddscpOtherphone;
	}

	public String getSddscpAddress() {
		return sddscpAddress;
	}

	public void setSddscpAddress(String sddscpAddress) {
		this.sddscpAddress = sddscpAddress;
	}

	public String getSddscpWorkstatue() {
		return sddscpWorkstatue;
	}

	public void setSddscpWorkstatue(String sddscpWorkstatue) {
		this.sddscpWorkstatue = sddscpWorkstatue;
	}

	public String getSddscpCitynumber() {
		return sddscpCitynumber;
	}

	public void setSddscpCitynumber(String sddscpCitynumber) {
		this.sddscpCitynumber = sddscpCitynumber;
	}

	public String getSddscpUsertype() {
		return sddscpUsertype;
	}

	public void setSddscpUsertype(String sddscpUsertype) {
		this.sddscpUsertype = sddscpUsertype;
	}

	public String getSddscpDealtype() {
		return sddscpDealtype;
	}

	public void setSddscpDealtype(String sddscpDealtype) {
		this.sddscpDealtype = sddscpDealtype;
	}

	public String getSddscpBasescore() {
		return sddscpBasescore;
	}

	public void setSddscpBasescore(String sddscpBasescore) {
		this.sddscpBasescore = sddscpBasescore;
	}


	public Integer getSddscpXfscore() {
		return sddscpXfscore;
	}

	public void setSddscpXfscore(Integer sddscpXfscore) {
		this.sddscpXfscore = sddscpXfscore;
	}

	public String getSddscpKfscore() {
		return sddscpKfscore;
	}

	public void setSddscpKfscore(String sddscpKfscore) {
		this.sddscpKfscore = sddscpKfscore;
	}

	public String getSddscpSumscore() {
		return sddscpSumscore;
	}

	public void setSddscpSumscore(String sddscpSumscore) {
		this.sddscpSumscore = sddscpSumscore;
	}

	public String getSddscpDevelopmentnum() {
		return sddscpDevelopmentnum;
	}

	public void setSddscpDevelopmentnum(String sddscpDevelopmentnum) {
		this.sddscpDevelopmentnum = sddscpDevelopmentnum;
	}

	public String getSddscpResidence() {
		return sddscpResidence;
	}

	public void setSddscpResidence(String sddscpResidence) {
		this.sddscpResidence = sddscpResidence;
	}

	public String getSddscpMatrimonial() {
		return sddscpMatrimonial;
	}

	public void setSddscpMatrimonial(String sddscpMatrimonial) {
		this.sddscpMatrimonial = sddscpMatrimonial;
	}

	public Date getSddscpEbranchdate() {
		return sddscpEbranchdate;
	}

	public void setSddscpEbranchdate(Date sddscpEbranchdate) {
		this.sddscpEbranchdate = sddscpEbranchdate;
	}

	public String getSddscpPaydues() {
		return sddscpPaydues;
	}

	public void setSddscpPaydues(String sddscpPaydues) {
		this.sddscpPaydues = sddscpPaydues;
	}

	public String getSddscpGraduate() {
		return sddscpGraduate;
	}

	public void setSddscpGraduate(String sddscpGraduate) {
		this.sddscpGraduate = sddscpGraduate;
	}

	public Date getSddscpJoinworktime() {
		return sddscpJoinworktime;
	}

	public void setSddscpJoinworktime(Date sddscpJoinworktime) {
		this.sddscpJoinworktime = sddscpJoinworktime;
	}

	public String getSddscpOrgname() {
		return sddscpOrgname;
	}

	public void setSddscpOrgname(String sddscpOrgname) {
		this.sddscpOrgname = sddscpOrgname;
	}

	public String getSddscpDegree() {
		return sddscpDegree;
	}

	public void setSddscpDegree(String sddscpDegree) {
		this.sddscpDegree = sddscpDegree;
	}

	public String getSddscpPmisleaveout() {
		return sddscpPmisleaveout;
	}

	public void setSddscpPmisleaveout(String sddscpPmisleaveout) {
		this.sddscpPmisleaveout = sddscpPmisleaveout;
	}

	public String getSddscpNamespell() {
		return sddscpNamespell;
	}

	public void setSddscpNamespell(String sddscpNamespell) {
		this.sddscpNamespell = sddscpNamespell;
	}

	public Date getSddscpJoinpartydate() {
		return sddscpJoinpartydate;
	}

	public void setSddscpJoinpartydate(Date sddscpJoinpartydate) {
		this.sddscpJoinpartydate = sddscpJoinpartydate;
	}

	public String getSddscpTypeid() {
		return sddscpTypeid;
	}

	public void setSddscpTypeid(String sddscpTypeid) {
		this.sddscpTypeid = sddscpTypeid;
	}

	public String getSddscpHistory() {
		return sddscpHistory;
	}

	public void setSddscpHistory(String sddscpHistory) {
		this.sddscpHistory = sddscpHistory;
	}

	public String getSddscpHistorytype() {
		return sddscpHistorytype;
	}

	public void setSddscpHistorytype(String sddscpHistorytype) {
		this.sddscpHistorytype = sddscpHistorytype;
	}

	public Date getSddscpKcdjsj() {
		return sddscpKcdjsj;
	}

	public void setSddscpKcdjsj(Date sddscpKcdjsj) {
		this.sddscpKcdjsj = sddscpKcdjsj;
	}

	public String getSddscpKcdyyy() {
		return sddscpKcdyyy;
	}

	public void setSddscpKcdyyy(String sddscpKcdyyy) {
		this.sddscpKcdyyy = sddscpKcdyyy;
	}

	public Date getSddscpSwsj() {
		return sddscpSwsj;
	}

	public void setSddscpSwsj(Date sddscpSwsj) {
		this.sddscpSwsj = sddscpSwsj;
	}

	public String getSddscpZcd() {
		return sddscpZcd;
	}

	public void setSddscpZcd(String sddscpZcd) {
		this.sddscpZcd = sddscpZcd;
	}

	public String getSddscpZczzorg() {
		return sddscpZczzorg;
	}

	public void setSddscpZczzorg(String sddscpZczzorg) {
		this.sddscpZczzorg = sddscpZczzorg;
	}

	public String getSddscpZcdate() {
		return sddscpZcdate;
	}

	public void setSddscpZcdate(String sddscpZcdate) {
		this.sddscpZcdate = sddscpZcdate;
	}

	public String getSddscpInorn() {
		return sddscpInorn;
	}

	public void setSddscpInorn(String sddscpInorn) {
		this.sddscpInorn = sddscpInorn;
	}

	public String getSddscpExcellentcause() {
		return sddscpExcellentcause;
	}

	public void setSddscpExcellentcause(String sddscpExcellentcause) {
		this.sddscpExcellentcause = sddscpExcellentcause;
	}

	public String getSddscpUnqualifiedcause() {
		return sddscpUnqualifiedcause;
	}

	public void setSddscpUnqualifiedcause(String sddscpUnqualifiedcause) {
		this.sddscpUnqualifiedcause = sddscpUnqualifiedcause;
	}

	public String getSddscpIsperororg() {
		return sddscpIsperororg;
	}

	public void setSddscpIsperororg(String sddscpIsperororg) {
		this.sddscpIsperororg = sddscpIsperororg;
	}

	public String getSddscpOrgloginname() {
		return sddscpOrgloginname;
	}

	public void setSddscpOrgloginname(String sddscpOrgloginname) {
		this.sddscpOrgloginname = sddscpOrgloginname;
	}

	public String getSddscpOrglogincode() {
		return sddscpOrglogincode;
	}

	public void setSddscpOrglogincode(String sddscpOrglogincode) {
		this.sddscpOrglogincode = sddscpOrglogincode;
	}

	/**
	 * Return the value associated with the column: group_id
	 */
	public com.jeecms.core.entity.CmsGroup getGroup () {
		return group;
	}

	/**
	 * Set the value related to the column: group_id
	 * @param group the group_id value
	 */
	public void setGroup (com.jeecms.core.entity.CmsGroup group) {
		this.group = group;
	}

	public com.jeecms.core.entity.CmsDepartment getDepartment() {
		return department;
	}

	public void setDepartment(com.jeecms.core.entity.CmsDepartment department) {
		this.department = department;
	}
	
	public java.util.Map<java.lang.String, java.lang.String> getAttr() {
		return attr;
	}

	public void setAttr(java.util.Map<java.lang.String, java.lang.String> attr) {
		this.attr = attr;
	}

	/**
	 * Return the value associated with the column: userExtSet
	 */
	public java.util.Set<com.jeecms.core.entity.CmsUserExt> getUserExtSet () {
		return userExtSet;
	}

	/**
	 * Set the value related to the column: userExtSet
	 * @param userExtSet the userExtSet value
	 */
	public void setUserExtSet (java.util.Set<com.jeecms.core.entity.CmsUserExt> userExtSet) {
		this.userExtSet = userExtSet;
	}
	
	public java.util.Set<com.jeecms.core.entity.CmsUserAccount> getUserAccountSet() {
		return userAccountSet;
	}

	public void setUserAccountSet(java.util.Set<com.jeecms.core.entity.CmsUserAccount> userAccountSet) {
		this.userAccountSet = userAccountSet;
	}

	/**
	 * Return the value associated with the column: userSites
	 */
	public java.util.Set<com.jeecms.core.entity.CmsUserSite> getUserSites () {
		return userSites;
	}

	/**
	 * Set the value related to the column: userSites
	 * @param userSites the userSites value
	 */
	public void setUserSites (java.util.Set<com.jeecms.core.entity.CmsUserSite> userSites) {
		this.userSites = userSites;
	}


	/**
	 * Return the value associated with the column: roles
	 */
	public java.util.Set<com.jeecms.core.entity.CmsRole> getRoles () {
		return roles;
	}

	/**
	 * Set the value related to the column: roles
	 * @param roles the roles value
	 */
	public void setRoles (java.util.Set<com.jeecms.core.entity.CmsRole> roles) {
		this.roles = roles;
	}

	/**
	 * Return the value associated with the column: channels
	 */
	public java.util.Set<com.jeecms.cms.entity.main.Channel> getChannels () {
		return channels;
	}

	/**
	 * Set the value related to the column: channels
	 * @param channels the channels value
	 */
	public void setChannels (java.util.Set<com.jeecms.cms.entity.main.Channel> channels) {
		this.channels = channels;
	}
	

	public java.util.Set<com.jeecms.cms.entity.main.Content> getCollectContents() {
		return collectContents;
	}

	public void setCollectContents(
			java.util.Set<com.jeecms.cms.entity.main.Content> collectContents) {
		this.collectContents = collectContents;
	}
	
	public java.util.Set<com.jeecms.cms.entity.main.ContentBuy> getBuyContentSet() {
		return buyContentSet;
	}

	public void setBuyContentSet(java.util.Set<com.jeecms.cms.entity.main.ContentBuy> buyContentSet) {
		this.buyContentSet = buyContentSet;
	}

	public java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> getSendMessages() {
		return sendMessages;
	}

	public void setSendMessages(
			java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> sendMessages) {
		this.sendMessages = sendMessages;
	}

	public java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> getReceivMessages() {
		return receivMessages;
	}

	public void setReceivMessages(
			java.util.Set<com.jeecms.cms.entity.assist.CmsMessage> receivMessages) {
		this.receivMessages = receivMessages;
	}

	public java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> getSendReceiverMessages() {
		return sendReceiverMessages;
	}

	public void setSendReceiverMessages(
			java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> sendReceiverMessages) {
		this.sendReceiverMessages = sendReceiverMessages;
	}

	public java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> getReceivReceiverMessages() {
		return receivReceiverMessages;
	}

	public void setReceivReceiverMessages(
			java.util.Set<com.jeecms.cms.entity.assist.CmsReceiverMessage> receivReceiverMessages) {
		this.receivReceiverMessages = receivReceiverMessages;
	}
	
	public java.util.Set<com.jeecms.cms.entity.assist.CmsJobApply> getJobApplys() {
		return jobApplys;
	}

	public void setJobApplys(
				java.util.Set<com.jeecms.cms.entity.assist.CmsJobApply> jobApplys) {
			this.jobApplys = jobApplys;
		}
		
	public java.util.Set<com.jeecms.core.entity.CmsUserResume> getUserResumeSet() {
			return userResumeSet;
		}
	
	public void setUserResumeSet(
				java.util.Set<com.jeecms.core.entity.CmsUserResume> userResumeSet) {
			this.userResumeSet = userResumeSet;
	}

	public java.util.Set<CmsLog> getLogs() {
		return logs;
	}

	public void setLogs(java.util.Set<CmsLog> logs) {
		this.logs = logs;
	}

	public java.util.Set<com.jeecms.cms.entity.assist.CmsUserMenu> getMenus() {
		return menus;
	}

	public void setMenus(
			java.util.Set<com.jeecms.cms.entity.assist.CmsUserMenu> menus) {
		this.menus = menus;
	}

	public java.util.Set<com.jeecms.core.entity.CmsWorkflowEventUser> getEventUsers() {
		return eventUsers;
	}

	public void setEventUsers(
			java.util.Set<com.jeecms.core.entity.CmsWorkflowEventUser> eventUsers) {
		this.eventUsers = eventUsers;
	}

	public java.util.Set<com.jeecms.core.entity.CmsWorkflowRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCheckRecords(
			java.util.Set<com.jeecms.core.entity.CmsWorkflowRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}
	
	public java.util.Set<com.jeecms.cms.entity.main.ContentRecord> getContentRecordSet() {
		return contentRecordSet;
	}

	public void setContentRecordSet(java.util.Set<com.jeecms.cms.entity.main.ContentRecord> contentRecordSet) {
		this.contentRecordSet = contentRecordSet;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.core.entity.CmsUser)) return false;
		else {
			com.jeecms.core.entity.CmsUser cmsUser = (com.jeecms.core.entity.CmsUser) obj;
			if (null == this.getId() || null == cmsUser.getId()) return false;
			else return (this.getId().equals(cmsUser.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSddscpOutpartytype() {
		return sddscpOutpartytype;
	}

	public void setSddscpOutpartytype(String sddscpOutpartytype) {
		this.sddscpOutpartytype = sddscpOutpartytype;
	}

	public String getSddscpJobstatus() {
		return sddscpJobstatus;
	}

	public void setSddscpJobstatus(String sddscpJobstatus) {
		this.sddscpJobstatus = sddscpJobstatus;
	}

	// 1是在职，0是离退休，2是历史党员
	public void setSddscpIsinjob(String sddscpIsinjob) {
		this.sddscpIsinjob = sddscpIsinjob;
	}
	public String getSddscpIsinjob() {
		return sddscpIsinjob;
	}

	
	public String getSddscpOutpartycause() {
		return sddscpOutpartycause;
	}

	public void setSddscpOutpartycause(String sddscpOutpartycause) {
		this.sddscpOutpartycause = sddscpOutpartycause;
	}

	public Integer getSddscpJgdw() {
		return sddscpJgdw;
	}

	public void setSddscpJgdw(Integer sddscpJgdw) {
		this.sddscpJgdw = sddscpJgdw;
	}

	public Integer getSddscpDzz() {
		return sddscpDzz;
	}

	public void setSddscpDzz(Integer sddscpDzz) {
		this.sddscpDzz = sddscpDzz;
	}

	public Integer getSddscpZb() {
		return sddscpZb;
	}

	public void setSddscpZb(Integer sddscpZb) {
		this.sddscpZb = sddscpZb;
	}

	public String getSddscpJgdwjob() {
		return sddscpJgdwjob;
	}

	public void setSddscpJgdwjob(String sddscpJgdwjob) {
		this.sddscpJgdwjob = sddscpJgdwjob;
	}

	public String getSddscpDzzjob() {
		return sddscpDzzjob;
	}

	public void setSddscpDzzjob(String sddscpDzzjob) {
		this.sddscpDzzjob = sddscpDzzjob;
	}

	public String getSddscpZbjob() {
		return sddscpZbjob;
	}

	public void setSddscpZbjob(String sddscpZbjob) {
		this.sddscpZbjob = sddscpZbjob;
	}

	public String getSddscpAssess() {
		return sddscpAssess;
	}

	public void setSddscpAssess(String sddscpAssess) {
		this.sddscpAssess = sddscpAssess;
	}

	public String getSddscpChanges() {
		return sddscpChanges;
	}

	public void setSddscpChanges(String sddscpChanges) {
		this.sddscpChanges = sddscpChanges;
	}

	public String getSddscpChangestype() {
		return sddscpChangestype;
	}

	public void setSddscpChangestype(String sddscpChangestype) {
		this.sddscpChangestype = sddscpChangestype;
	}

	public String getSddscpJgdwname() {
		return sddscpJgdwname;
	}

	public void setSddscpJgdwname(String sddscpJgdwname) {
		this.sddscpJgdwname = sddscpJgdwname;
	}

	public String getSddscpDzzname() {
		return sddscpDzzname;
	}

	public void setSddscpDzzname(String sddscpDzzname) {
		this.sddscpDzzname = sddscpDzzname;
	}

	public String getSddscpZbname() {
		return sddscpZbname;
	}

	public void setSddscpZbname(String sddscpZbname) {
		this.sddscpZbname = sddscpZbname;
	}

	public String getSddscpPfsmjc() {
		return sddscpPfsmjc;
	}

	public void setSddscpPfsmjc(String sddscpPfsmjc) {
		this.sddscpPfsmjc = sddscpPfsmjc;
	}

	public String getSddscpPfsmfj() {
		return sddscpPfsmfj;
	}

	public void setSddscpPfsmfj(String sddscpPfsmfj) {
		this.sddscpPfsmfj = sddscpPfsmfj;
	}

	public Integer getSddscpJcf() {
		return sddscpJcf;
	}

	public void setSddscpJcf(Integer sddscpJcf) {
		this.sddscpJcf = sddscpJcf;
	}

	public Integer getSddscpFjf() {
		return sddscpFjf;
	}

	public void setSddscpFjf(Integer sddscpFjf) {
		this.sddscpFjf = sddscpFjf;
	}

	public String getSddscpAssessyear() {
		return sddscpAssessyear;
	}

	public void setSddscpAssessyear(String sddscpAssessyear) {
		this.sddscpAssessyear = sddscpAssessyear;
	}


}