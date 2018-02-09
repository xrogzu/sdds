package com.risen.service;
import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenReject;

public interface IRisenRejectService {

	public Pagination getPage(Integer departId,int pageNo,int pageSize,int status);
	//保存
	public RisenReject save(RisenReject reject);
	public RisenReject findById(Integer reject);
	//修改状态
	public Integer changeStatus(Integer departId,Integer contentId);
	//根据组织ID和文章编号查询是否存在驳回列表
	public List<RisenReject> getListByDepartIdAndContentId(Integer departId,Integer contentId);
	//更改驳回意见
	public Integer changeReason(Integer risenrjUnid,String reason);
}
