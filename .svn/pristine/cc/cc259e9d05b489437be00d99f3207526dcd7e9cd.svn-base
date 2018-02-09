package com.jeecms.cms.manager.main;

import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.page.Pagination;

/**
 * 栏目管理接口
 * 
 */
public interface ChannelMng {
	/**
	 * 获得顶级栏目
	 * 
	 * @param siteId
	 *            站点ID
	 * @param hasContentOnly
	 *            是否只获得有内容的栏目
	 * @return
	 */
	public List<Channel> getTopList(Integer siteId, boolean hasContentOnly);

	public List<Channel> getTopListByRigth(Integer userId, Integer siteId,boolean hasContentOnly);

	public List<Channel> getTopListForTag(Integer siteId, boolean hasContentOnly);

	public Pagination getTopPageForTag(Integer siteId, boolean hasContentOnly,int pageNo, int pageSize);

	public List<Channel> getChildList(Integer parentId, boolean hasContentOnly);

	public List<Channel> getChildListByRight(Integer userId, Integer siteId,Integer parentId, boolean hasContentOnly);

	public List<Channel> getChildListForTag(Integer parentId,boolean hasContentOnly);
	
	public List<Channel> getBottomList(Integer siteId,boolean hasContentOnly);

	public Pagination getChildPageForTag(Integer parentId,boolean hasContentOnly, int pageNo, int pageSize);

	/**
	 * 获取部门 顶层栏目内容权限
	 * @param departId
	 * @param userId
	 * @param siteId
	 * @param hasContentOnly
	 * @return
	 */
	public List<Channel> getTopListForDepartId(Integer departId,Integer userId,Integer siteId,boolean hasContentOnly);
	/**
	 * 获取部门 顶层栏目控制权限
	 * @param departId
	 * @param userId
	 * @param siteId
	 * @param hasContentOnly
	 * @return
	 */
	public List<Channel> getControlTopListForDepartId(Integer departId,Integer userId,Integer siteId,boolean hasContentOnly);
	
	/**
	 * 获取下级 栏目内容权限
	 * @param departId
	 * @param siteId
	 * @param parentId
	 * @param hasContentOnly
	 * @return
	 */
	public List<Channel> getChildListByDepartId(Integer departId,Integer siteId,Integer parentId, boolean hasContentOnly);
	
	/**
	 * 获取下级栏目控制权限
	 * @param departId
	 * @param siteId
	 * @param parentId
	 * @param hasContentOnly
	 * @return
	 */
	public List<Channel> getControlChildListByDepartId(Integer departId,Integer siteId,Integer parentId, boolean hasContentOnly);

	public Channel findByPath(String path, Integer siteId);

	public Channel findByPathForTag(String path, Integer siteId);

	public Channel findById(Integer id);
	
	public Integer findByIdAndDepartId(Integer id, Integer departId);
	
	public Integer getCountById(Integer cid);

	public Channel save(Channel bean, ChannelExt ext, ChannelTxt txt,
			Integer[] viewGroupIds, Integer[] contriGroupIds,
			Integer[] userIds, Integer siteId, Integer parentId, 
			Integer modelId,Integer workflowId,Integer[]modelIds,String[] tpls,String[] mtpls);

	public Channel update(Channel bean, ChannelExt ext, ChannelTxt txt,
			Integer[] viewGroupIds, Integer[] contriGroupIds,
			Integer[] userIds, Integer parentId, Map<String, String> attr, Integer modelId,
			Integer workflowId,Integer[]modelIds,String[] tpls,String[] mtpls);

	public Channel deleteById(Integer id);

	public Channel[] deleteByIds(Integer[] ids);

	public Channel[] updatePriority(Integer[] ids, Integer[] priority);
	
	public void initWorkFlow(Integer workflowId);

	public String checkDelete(Integer id);
	
	/**
	 * 获取栏目路径
	 */
	public String getChannelPathByDid(Integer departId);
	/**
	 * 获取栏目路径in
	 */
	public String getChannelPathByDids(Integer departId);
	
	/**
	 * 获取栏目站点ID
	 */
	public String getSiteIdByDids(String departId);
	
	/**
	 * 根据部门获取栏目
	 */
	public Channel getChannelByDepts(Integer departId);
	
	/**
	 * 判断是否存在子集栏目
	 */
	public boolean existChildChannel(Integer parentChild);
	
	/**
	 * 根据条件获取下级栏目
	 */
	public Channel getfindBychannel(Integer parentid);
	
	/**
	 * 绑定栏目
	 */
	public void savechannel(Integer channelid,Integer depatid);
}