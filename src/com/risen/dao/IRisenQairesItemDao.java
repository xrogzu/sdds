package com.risen.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQairesItem;

public interface IRisenQairesItemDao {
	public List<RisenQairesItem> findList(Integer topicId);
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize);
	public RisenQairesItem save(RisenQairesItem bean);
	public RisenQairesItem updateByUpdater(Updater<RisenQairesItem> updater);
	public void deleteByTopic(Integer topicId);
	public RisenQairesItem findById(Integer id);
	public void delete(Integer id);
	public RisenQairesItem update(RisenQairesItem bean);
}
