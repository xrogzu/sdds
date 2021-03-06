package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;

public interface ChannelDao {
	public List<Channel> getTopList(Integer siteId, boolean hasContentOnly,boolean displayOnly, boolean cacheable);

	public Pagination getTopPage(Integer siteId, boolean hasContentOnly,boolean displayOnly, boolean cacheable, int pageNo, int pageSize);

	public List<Channel> getTopListByRigth(Integer userId, Integer siteId,boolean hasContentOnly);
	
	public List<Channel> getChildList(Integer parentId, boolean hasContentOnly,boolean displayOnly, boolean cacheable);
	
	public List<Channel> getBottomList(Integer siteId,boolean hasContentOnly);

	public Pagination getChildPage(Integer parentId, boolean hasContentOnly,boolean displayOnly, boolean cacheable, int pageNo, int pageSize);

	public List<Channel> getChildListByRight(Integer userId, Integer parentId,boolean hasContentOnly);

	public List<Channel> getTopListForDepartId(Integer departId,Integer siteId,boolean hasContentOnly);
	
	public List<Channel> getControlTopListForDepartId(Integer departId,Integer siteId,boolean hasContentOnly);
	
	public List<Channel> getChildListByDepartId(Integer departId,Integer  siteId,Integer parentId, boolean hasContentOnly);
	
	public List<Channel> getControlChildListByDepartId(Integer departId,Integer  siteId,Integer parentId, boolean hasContentOnly);

	public Channel findByPath(String path, Integer siteId, boolean cacheable);

	public Channel findById(Integer id);
	
	public Integer findByIdAndDepartId(Integer id, Integer departId);
	
	public Integer getCountById(Integer cid);

	public Channel save(Channel bean);

	public Channel updateByUpdater(Updater<Channel> updater);

	public Channel deleteById(Integer id);
	
	public void initWorkFlow(Integer workflowId);

	/**
	 * 获取栏目路径
	 */
	public Channel getChannelPathByDid(Integer departId);

	/**
	 * 获取栏目路径in
	 */
	public List<Channel> getChannelPathByDids(String departIds);
	/**
	 * 根据departmentid 去获取某一个 channel
	 */
	public Integer findChannelidByDepartid(Integer departId);
	/**
	 * 根据pid，活动子节点的第一个数据
	 */
	public List<Channel> findChildFirst(Integer pid);
	/**
	 * 根据name查找数据
	 */
	public List<Channel> findByChannelName(String name);
	/**
	 * 根据部门ID查找站点ID数据
	 */
	public String getSiteIdByDids(String departId);
	
	/**
	 * 判断是否存在子集栏目
	 */
	public List<Channel> existChildChannel(Integer parentChildId);
	/**
	 * 根据条件获取下级栏目
	 */
	public Channel getfindBychannel(Integer parentid);
	
	
	/**
	 * 绑定栏目
	 */
	public void savechannel(Integer channelid,Integer depatid);
}
