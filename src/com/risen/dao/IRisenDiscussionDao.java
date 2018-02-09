package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenDiscussion;

public interface IRisenDiscussionDao {
	//保存
	public RisenDiscussion save(RisenDiscussion bean);
	//更新
	public RisenDiscussion update(RisenDiscussion bean);
	//删除
	public void delete(Integer id);
	//得到指定的对象
	public RisenDiscussion findById(Integer id);
	//获取列表
	public Pagination getPage(Integer userId, Integer departId, Integer year);
	/**
	 * 展示用户的所有评议
	 */
	public Pagination getPage(int pageNo, int pageSize,Integer userId,Integer departId);
}
