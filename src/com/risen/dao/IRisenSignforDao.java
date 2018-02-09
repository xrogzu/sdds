package com.risen.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenSignfor;

public interface IRisenSignforDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenSignfor findById(Integer id);

	public RisenSignfor save(RisenSignfor bean);

	public RisenSignfor updateByUpdater(Updater<RisenSignfor> updater);

	public RisenSignfor deleteById(Integer id);
	/**
	 * 根据deptid 和contentid进行查询，存在返回true，不存在返回false
	 * @create Jan 20, 2017 2:10:53 PM
	 * @author 李兴邈
	 */
	public Boolean findModelBy(Integer deptid, String contentid);
	/**
	 * 根据department的list 和 contentid 看来查询 list
	 * @create Jan 20, 2017 2:34:56 PM
	 * @author 李兴邈
	 */
	public List<RisenSignfor> findModelByDeptListAndContent(Integer[]deptlist, String contentid);

	public List<RisenSignfor> findModelByContentId(String contentId);
}