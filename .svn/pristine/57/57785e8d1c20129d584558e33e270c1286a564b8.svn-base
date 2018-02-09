package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenTarget;

public interface IRisenTargetDao {
	public Pagination getPage(int pageNo, int pageSize,String id,Integer deptId);
	public RisenTarget save(RisenTarget voteQues);
	public RisenTarget findById(Integer id);
	public void delete(Integer id);
	public RisenTarget update(RisenTarget bean);
	public Pagination findByOrgId(int pageNo, int pageSize,Integer id);

	/**
	 * 获取报表信息
	 * @author slc 2016-11-28 下午11:23:16
	 * @return
	 */
	public List<RisenTarget>getReportData(Integer orgId);
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

	/***
	 * 统计每个党组织的总分
	 */
	public List<RisenTarget> getReportList(String orgIds);
	
	public int confirmShare(Integer orgId,Integer score);
}
