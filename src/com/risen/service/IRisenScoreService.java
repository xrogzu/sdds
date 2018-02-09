package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenScore;

public interface IRisenScoreService {
	//查询指定党员的分数
	public Pagination getPage(int pageNo, int pageSize,Integer userId);
	//查询指定党员在党支部下的分数
	public Pagination getPage(int pageNo, int pageSize,Integer userId,Integer departId);
	//查询指定党员在党支部下的分数，查询年度
	public Pagination getPage(int pageNo, int pageSize,Integer userId,Integer departId,String risenscYear);
	public RisenScore save(RisenScore score);
	public RisenScore findById(Integer id);
	public void delete(Integer id);
	public RisenScore update(RisenScore bean,Integer idms);
	//得到总分
	public List<RisenScore> getTotalScore(Integer depart);
	//判断某指标是否已添加得分
	public boolean existScore(Integer userId,Integer departId,Integer quotaId,String risenqtYear);
}
