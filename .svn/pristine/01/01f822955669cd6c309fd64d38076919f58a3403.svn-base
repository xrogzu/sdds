package com.jeecms.core.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;

public interface CmsDepartmentMng {

	/**
	 * 
	 * @param parentId 父层部门id
	 * @param all 是否查询所有部门
	 * @return
	 */
	public List<CmsDepartment> getList(Integer parentId,boolean all);
	/*
	 * 根据上级组织ID 查询指定类型的组织
	 */
	public List<CmsDepartment> getTypeList(Integer parentId,String type);
	
	public Pagination getPage( String name, int pageNo,int pageSize);

	public CmsDepartment findById(Integer id);

	public CmsDepartment save(CmsDepartment bean);
	
	public CmsDepartment save(CmsDepartment bean,Integer channelIds[],Integer[] controlChannelIds,Integer[]ctgIds);

	public CmsDepartment update(CmsDepartment bean);
	
	public CmsDepartment update(CmsDepartment bean,Integer channelIds[],Integer[] controlChannelIds,Integer[]ctgIds);

	public void updatePriority(Integer[] ids, Integer[] priorities);

	//public CmsDepartment deleteById(Integer id);
	public void deleteById(Integer id);
	
	public CmsDepartment[] deleteByIds(Integer ids[]);

	public boolean nameExist(String name);
	
	public List<CmsDepartment> findAll();

	/**
	 * 换届提醒
	 */
	public List<CmsDepartment> changeRemindList(Date today,String departmentName,Integer departid,Integer pid,String huanjie);
	/**
	 * 设置加分基数
	 * @param parentId
	 * @return
	 */
	public List<CmsDepartment>baseScore(Integer parentId);
	/**
     * 获取字典键值列表
     * @param corecdType
     * @return
     */
    public List<CmsDepartment> getCorecdKeyList(String corecdType);
    /**
     * 根据父级id查询组织列表(判断删除)
     * true 底级组织，无子级节点
     * false 有子级节点
     */
    public boolean findlistByPid(Integer pid);
    /**
     * 导入组织小程序
     */
	public void impOrgToUser();
	 /**
     *根据类型获取组织名称
     * @author slc 2016-12-6 下午5:02:03
     * @return
     */
    public List<CmsDepartment> getDeptByType(String type);
    /**
     * 获取最大排序值
     * @return
     */
    public int getMaxPriority (Integer parentId);
    /**
     * 替换书记
     */
    public CmsDepartment changeSecretaryByDid(String sddspoSecretary,String sddspoSecretaryidc,Integer sddspoSecretaryid,Integer departid,Date sddspoChangelasttime);
	 /**
     *根据类型、登录者ID获取机构列表
     * @author slc 2016-12-6 下午5:02:03
     * @return
     */
    public List<CmsDepartment> getDeptByTypeAndLoginId(String type,Integer loginId);
    public List<CmsDepartment> findZbList(Integer id,Integer pid);
    /**
     * 查看换届
     */
    public List<CmsDepartment> checkChangelist (String departName);
    public List<CmsDepartment> findNopidList();
    public CmsDepartment findByName(String name);
    /**
     * 统计当前登录组织下的组织等级 及对应的数量
     */
    public Map<String, String> statisticsDeptSum(Integer i, Integer deptid);
    /**
     * 统计当前登录组织下的期满换届
     */
    public Map<String, String> statisticsDeptQmhjSum(Integer i, Integer deptid, String sdate, String edate);
    /**
     * 统计当前登录组织已换届
     */
    public Map<String, String> statisticsDeptYhjSum(Integer i, Integer deptid, String sdate, String edate);
    /**
     * 根据党组织id 查询系统的机关党委
     */
    public List<CmsDepartment> findXtForJGDWByDeptId(Integer detpid);
    public List<CmsDepartment> findXtForJGDWByDeptIdse(Integer dds);
    /**
     * 根据党组织ID，查询该组织下所有子集机构下的党组织
     * @param detpId
     * @return
     */
    public List<Integer> findDpetNameByLoginDept(Integer detpId,List<Integer> ids);
    //获取指定组织下指定名称的组织集合
    public List<CmsDepartment> getAllDeptByIdAndName(String deptId,String deptName);
    //获取17个地市的机关党委
    public List<CmsDepartment> getAllJgdwDept();
    //获取指定市局下的机关党委
    public List<CmsDepartment> getAllJgdwDeptById(Integer deptId,boolean isShiju);
    //获取指定机关党委的下所部门
    public List<CmsDepartment> getAllDeptById(Integer deptId,boolean isShiju);
    //获取指定市局下的支部
    public List<CmsDepartment> getAllZhiBuById(Integer deptId);
    //获取制定部门下的逾期换届数量
    public Map<String, String> statisticsDeptYqhjSum(Integer i, Integer deptid,String nowTime);
    //获取制定部门下的按期换届数量
    public Map<String, String> statisticsDeptAqhjSum(Integer i, Integer deptid,String nowTime);
    //判断两个组织的等级(同一级/上下级/没有任何关系)
    public String getTwoDeptsLevel(Integer departId,Integer targetDeptId);
    //得到某个组织是市局下  还是县局下  还是省局下的
    public String getSsxByDepartId(Integer departId);
    //查询当前组织下的机关党委
    public CmsDepartment findByJgdw(Integer pid);
    //查询当前组织下的机关党委
    public CmsDepartment findByPid(Integer pid);
}