package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenIntegral;

public interface IRisenIntegralService {
	public Pagination getPage(int pageNo, int pageSize);
	//获取排行
	public List<RisenIntegral> getPage(String departId);
	public RisenIntegral save(RisenIntegral voteQues);
	public RisenIntegral findById(Integer id);
	public void delete(Integer id);
	public RisenIntegral update(RisenIntegral bean);
	public RisenIntegral findByOrgId(Integer id);
	/**
	 * 修改基础分
	 */
	public int changeBaseById(Integer id,Integer base);
	/**
	 * 获取某一年的绩效规则信息
	 */
	public List<RisenIntegral> getYearInfo(String year,String deptId);
	/**
	 * 获取报表信息
	 * @author slc 2016-11-28 下午11:23:16
	 * @return
	 */
	public List<RisenIntegral>getReportData(Integer orgId);
	/**
	 * 修改加分基数
	 * @author slc 2016-12-3 下午9:02:32
	 * @return
	 */
	public void updateBaseScore(Integer orgId,Integer score);
	/**
	 * 批量修改加分基数
	 * @author slc 2016-12-3 下午9:04:48
	 * @param orgId
	 * @param score
	 * @return
	 */
	public void batchUpdBaseScore(Integer[] orgId,Integer score);
	//查询所有总分记录
	public List<Object[]> getListse(String strIds, String channelName,String startDate, String endDate);
	public int batchUpBaseScore(Integer[] orgId,Integer score);
	/**
	 * 验证某一年是否存在某组织的记录
	 */
	public boolean existRecord(String year,String deptId);
	/**
	 * 修改某个部门的规则说明
	 */
	public int changeRuleById(String id,String desc);
}
