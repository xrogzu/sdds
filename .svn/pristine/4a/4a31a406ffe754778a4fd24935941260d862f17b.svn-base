package com.jeecms.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsUser;

public interface CmsDepartmentDao {

	public List<CmsDepartment> getList(Integer parentId,boolean all);

	public Pagination getPage(String name, int pageNo,int pageSize);

	public CmsDepartment findById(Integer id);

	public CmsDepartment findByName(String name);

	public CmsDepartment save(CmsDepartment bean);

	public CmsDepartment deleteById(Integer id);

	public CmsDepartment updateByUpdater(Updater<CmsDepartment> updater);

	public List<CmsDepartment> findAll();
	
	/**
	 * 换届提醒List
	 */
	public List<CmsDepartment> changeRemindList(String departmentName,Integer departid,Integer pid);
	/**
	 * 设置加分基数
	 * @param parentId
	 * @return
	 */
	public List<CmsDepartment>baseScore(Integer parentId);
	/**
     * 根据父级id查询组织列表
     */
    public List<Object[]> findlistByPid(Integer pid);
  
    /**
     * 导入组织小程序
     */
	public List<CmsDepartment> findNotInUser();
    /**
     * 根据类型获取组织
     * @author slc 2016-12-6 下午5:07:28
     * @param type
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
    public CmsDepartment changeSecretaryByDid(CmsDepartment model);
    /**
     * 新增党员职务关联
     */
    public CmsDepartment updatejob(CmsDepartment model);
    /**
     * 修改user数据工具
     * @return
     */
    List<CmsDepartment> findalllistdepart();
    /**
     * 根据类型,当前登陆者获取组织列表
     * @author slc 2016-12-6 下午5:07:28
     * @param type
     * @return
     */
    public List<CmsDepartment> getDeptByTypeAndLoginId(String type,Integer loginId);
    public List<CmsDepartment> findZbList(Integer id,Integer pid);
    /**
     * 查看换届
     */
    public List<CmsDepartment> checkChangelist (String departName);
    /**
     * 查询children的排序号为1 的deptid
     */
    public Integer findChildrenFirstDeptidByPid(Integer pid);
    /**
     * 查询 通过一个int数组和pid 相等，是否有值
     */
    public Boolean findChildrenIsBoolean(int[]pid);
    /**
     * 查询admin和省委下面的各市的机关党委
     */
    public List<CmsDepartment> findAdminDept();
    public List<CmsDepartment> findNopidList();
    /**
     * 根据IP查询组织
     * @create Jan 19, 2017 5:37:32 PM
     * @author 李兴邈
     */
    public List<CmsDepartment> findListByIp(String ip);
    /**
     *统计当前登录组织下的组织等级 及对应的数量
     */
    public List<Object[]> statisticsDeptSum(Integer deptid);
    /**
     * 统计当前登录组织下的期满换届
     */
    public List<Object[]> statisticsDeptQmhjSum(Integer deptid, String sdate, String edate);
    /**
     * 统计当前登录组织下的已换届
     */
    public List<Object[]> statisticsDeptYhjSum(Integer deptid, String sdate, String edate);
    /**
     * 根据党组织id 查询系统的机关党委
     * @create Feb 19, 2017 3:41:47 PM
     * @author 李兴邈
     */
    public List<CmsDepartment> findXtForJGDWByDeptId(Integer detpid);
    public List<CmsDepartment> findXtForJGDWByDeptIdse(Integer dds);
    /**
     * 根据父级ID，查询是否存在子级节点
     * @return
     */
    public Integer findCountByPid(Integer Pid);
    
    /**
     * 根据父级ID，查询得到子集记录
     * @param Pid
     * @return
     */
    public List<Object[]> findDeptByPid(Integer Pid);
    
    //判断指定组织下指定名称的组织集合
    public List<CmsDepartment> getAllDeptByIdAndName(String deptId,String deptName);
    //获取所有市级下的所有机关党委
    public List<CmsDepartment> getAllJgdwDept();
    //获取指定市局下的部门
    public List<CmsDepartment> getAllDeptById(Integer deptId,boolean isShiju);
    //获取指定市局下的部门
    public List<CmsDepartment> getAllTypeDeptById(Integer deptId,String type);
    //获取指定市局下的机关党委
    public List<CmsDepartment> getAllJgdwDeptById(Integer deptId,boolean isShiju);
    
    public List<CmsDepartment> findByParentIds(Integer parentid,boolean  ist);
    //获取指定市局下的支部
    public List<CmsDepartment> getAllZhiBuById(Integer departId);
    //获取某个部门下的逾期换届数量
    public List<Object[]> statisticsDeptYqhjSum(Integer parentId,String nowTime);
    //获取某个部门下的逾期换届数量
    public List<Object[]> statisticsDeptAqhjSum(Integer parentId,String nowTime);
    //获取当前登录组织的上级
    public Integer getMyActualParent(Integer departId);
    public boolean oneIsInDepts(Integer departId, Integer targetDeptId);
    //查询当前组织下的机关党委
    public CmsDepartment findByJgdw(Integer pid);
    //查询当前组织下的机关党委
    public CmsDepartment findByPid(Integer pid);
}