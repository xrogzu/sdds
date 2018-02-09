package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenScore;

public interface IRisenScoreDao {
	//查询指定党员的分数
	public Pagination getPage(int pageNo, int pageSize,Integer userId);
	//查询指定党员在党支部下的分数
	public Pagination getPage(int pageNo, int pageSize,Integer userId,Integer departId);
	//查询指定党员在党支部下的分数,添加年份
	public Pagination getPage(int pageNo, int pageSize,Integer userId,Integer departId,String risenscYear);
	public RisenScore save(RisenScore score);
	public RisenScore findById(Integer id);
	public void delete(Integer id);
	public RisenScore update(RisenScore bean,Integer idms);
	//判断是否已添加某指标的分数
	public boolean existScore(Integer userId,Integer departId,Integer quotaId,String risenqtYear);
}
