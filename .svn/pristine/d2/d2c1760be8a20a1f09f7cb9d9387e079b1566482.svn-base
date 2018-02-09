package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.entity.RisenIntegral;
import com.risen.entity.RisenIntegralRecord;

public interface IRisenIntegralDao {
	public Pagination getPage(int pageNo, int pageSize);
	public List<RisenIntegral> getPage(String departId);
	public RisenIntegral save(RisenIntegral voteQues);
	public RisenIntegral findById(Integer id);
	public void delete(Integer id);
	public RisenIntegral update(RisenIntegral bean);
	public RisenIntegral findByOrgId(Integer id);
	public int changeBaseById(Integer id,Integer base);
	/**
	 * 获取某一年的绩效规则信息
	 */
	public List<RisenIntegral> getYearInfo(String year,String departId);
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
	public int batchUpBaseScore(Integer[] orgId,Integer score);
	/**
	 * 批量修改加分基数
	 * @author slc 2016-12-3 下午9:04:48
	 * @param orgId
	 * @param score
	 * @return
	 */
	public void batchUpdBaseScore(Integer[] orgId,Integer score);
	public int confirmShare(Integer orgId,Double score);
	//查询所有总分记录《查看所有机关党委产生的分数总和》
	public List<Object[]> getListse(String strIds, String channelName, String startDate, String endDate);
	//查询所有总分记录<按月份取>
	public List<Object[]> getListseMonth(String strIds, String channelName);
	//查看图片新闻等分数(原始)
	/*
	 * 由于按照文章原始栏目得分 故而党建动态算的是下属组织的栏目
	 */
	public List<Object[]> getListCha(String strIds, String channelName, String startDate, String endDate);
	//查看图片新闻等分数(现在)
	/*
	 * 变更需求
	 * 由于按照文章共享给的栏目得分 故而需提供一个当前登录组织(用于获取该组织的党建动态栏目 以及他的下属栏目)
	 */
	public List<Object[]> getListChaNow(String strIds, String departId ,String channelName, String startDate, String endDate);
	/*
	 * 增强版统计栏目的分数
	 */
	public List<Object[]> getListChannelScore(String strIds,String startDate,String endDate);
	/*
	 * 获取栏目月总分和一段时间内的栏目总分
	 */
	public List<Object[]> getListChannelMonthAndTotalScore(String strIds,String startDate,String endDate);
	/**
	 * 修改某条记录的说明
	 */
	public int changeRuleById(String id,String desc);
	public List<Object[]> getListds(String strIds, String channelName, String startDate, String endDate);
	public List<Object[]> getListseMonthds(String strIds, String channelName);
	public List<Object[]> getListMonthmds (String strIds, String channelName);
	//前台首页获取每月的月积分排行
	public List<Object[]> getListMonthTotal(String strIds);
	
	/**
	 * 获取年度积分数据
	 */
	public List<Object[]> getYearScore(String year,String orgid);
}
