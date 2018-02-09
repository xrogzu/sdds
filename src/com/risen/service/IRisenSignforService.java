package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenSignfor;

public interface IRisenSignforService {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenSignfor findById(Integer id);

	public RisenSignfor save(RisenSignfor bean);

	public RisenSignfor update(RisenSignfor bean);

	public RisenSignfor deleteById(Integer id);
	
	public RisenSignfor[] deleteByIds(Integer[] ids);
	/**
	 * 根据ip去判断department是否存在，如果存在就储存和content完成签收，并返回true
	 * @create Jan 19, 2017 5:44:02 PM
	 * @author 李兴邈
	 */
	public Boolean savaForIpAndContent(String ip, String contentid);
	//根据ip去判断department是否存在，如果存在就储存和content完成签收，并返回true
	public Boolean checkIsSign(String ip, String contentid);
	//获取指定内容ID的签收情况
	public List<RisenSignfor> getAllInfoByContentId(String contentid);
}