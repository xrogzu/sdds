package com.risen.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenIntegralRecord;

public interface IRisenIntegralRecordDao {
	public Pagination getPage(int pageNo, int pageSize);
	public RisenIntegralRecord save(RisenIntegralRecord bean);
	public RisenIntegralRecord findById(Integer id);
	public void delete(Integer id);
	public RisenIntegralRecord update(RisenIntegralRecord bean);
	public Pagination getPage(int pageNo, int pageSize,RisenIntegralRecord bean);
	public Pagination getPagehs(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean);
	public Pagination getPagehsMonth(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean);
	//查看栏目汇总
	public List<RisenIntegralRecord> getPageNum(int pageNo,HttpServletRequest request, int pageSize, Integer deptId,RisenIntegralRecord bean);
	public int confirmShare(Integer ids,Double score);
	public RisenIntegralRecord getByid(Integer id);
	public List<RisenIntegralRecord> findByContentId(Integer id);
	/**
	 * 根据ContentId 和 SHARE_CHECK_ID 查询一个对象
	 */
	public RisenIntegralRecord findByContentIdAndCheckId(Integer id, Integer checkId);
	public List<RisenIntegralRecord> findByIds(Integer[] ids);
	
	public List<RisenIntegralRecord> findByParentId(Integer deptId,Integer contentId);
	public Pagination getScoresByDeptIdAndDate(int pageNo,
			HttpServletRequest request, int pageSize, Integer deptId,
			String startDate, String endDate,Integer status);
	public List<RisenIntegralRecord> findByContentIdAndDeptId(Integer departId,
			Integer contentId);
	Pagination getScoresFrontByDeptIdAndDate(int pageNo,
			HttpServletRequest request, int pageSize, Integer deptId,
			String startDate, String endDate, Integer status);
}
