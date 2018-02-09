package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenDiscussion;

public interface IRisenDiscussionService {
	//保存
	public RisenDiscussion save(RisenDiscussion bean);
	//更新
	public RisenDiscussion update(RisenDiscussion bean);
	//删除
	public void delete(Integer id);
	//验证是否已添加
	public boolean exist(Integer userId, Integer departId, Integer year);
	//得到指定对象
	public RisenDiscussion findById(Integer id);
	//展示一个用户的所有年度评议
	public Pagination getMyDiscussion(int pageNo,int pageSize,Integer userId, Integer departId);
}
