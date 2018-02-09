package com.jeecms.cms.dao.main.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ContentShareCheck;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class ChannelDaoImpl extends HibernateBaseDao<Channel, Integer>
		implements ChannelDao {
	@SuppressWarnings("unchecked")
	public List<Channel> getTopList(Integer siteId,boolean hasContentOnly,
			boolean displayOnly, boolean cacheable) {
		Finder f = getTopFinder(siteId, hasContentOnly, displayOnly, cacheable);
		return find(f);
	}

	public Pagination getTopPage(Integer siteId, boolean hasContentOnly,
			boolean displayOnly, boolean cacheable, int pageNo, int pageSize) {
		Finder f = getTopFinder(siteId, hasContentOnly, displayOnly, cacheable);
		return find(f, pageNo, pageSize);
	}

	private Finder getTopFinder(Integer siteId, boolean hasContentOnly,
			boolean displayOnly, boolean cacheable) {
		Finder f = Finder.create("from Channel bean");
		f.append(" where bean.site.id=:siteId and bean.parent.id is null");
		f.setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		if (displayOnly) {
			f.append(" and bean.display=true");
		}
		f.append(" order by bean.priority asc,bean.id asc");
		f.setCacheable(cacheable);
		return f;
	}

	@SuppressWarnings("unchecked")
	public List<Channel> getTopListByRigth(Integer userId, Integer siteId,
			boolean hasContentOnly) {
		Finder f = Finder.create("select bean from Channel bean");
		f.append(" join bean.users user");
		f.append(" where user.id=:userId and bean.site.id=:siteId");
		f.setParam("userId", userId).setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" and bean.parent.id is null");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Channel> getTopListForDepartId(Integer departId,Integer siteId,boolean hasContentOnly){
		Finder f = Finder.create("select distinct bean from Channel bean");
		f.append(" left join bean.departments depart");
		f.append(" where 1=1 ");
		if(departId!=null){
			f.append(" and depart.id =:departId");
			f.setParam("departId", departId);
		}
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" and bean.parent.id is null");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Channel> getControlTopListForDepartId(Integer departId,Integer siteId,boolean hasContentOnly){
		Finder f = Finder.create("select distinct bean from Channel bean");
		f.append(" left join bean.controlDeparts depart");
		f.append(" where 1=1 ");
		if(departId!=null){
			f.append(" and depart.id =:departId");
			f.setParam("departId", departId);
		}
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" and bean.parent.id is null");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<Channel> getChildList(Integer parentId, boolean hasContentOnly,
			boolean displayOnly, boolean cacheable) {
		Finder f = getChildFinder(parentId, hasContentOnly, displayOnly,
				cacheable);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Channel> getBottomList(Integer siteId,boolean hasContentOnly){
		Finder f = Finder.create("select  bean from Channel bean");
		f.append(" where bean.site.id=:siteId").setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" and size(bean.child) <= 0");
	//	f.append(" and bean.id not in(select channel.parent.id from Channel channel where channel.parent.id is not null)");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	public Pagination getChildPage(Integer parentId, boolean hasContentOnly,
			boolean displayOnly, boolean cacheable, int pageNo, int pageSize) {
		Finder f = getChildFinder(parentId, hasContentOnly, displayOnly,
				cacheable);
		return find(f, pageNo, pageSize);
	}

	private Finder getChildFinder(Integer parentId, boolean hasContentOnly,
			boolean displayOnly, boolean cacheable) {
		Finder f = Finder.create("from Channel bean");
		f.append(" where bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		if (displayOnly) {
			f.append(" and bean.display=true");
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return f;
	}

	@SuppressWarnings("unchecked")
	public List<Channel> getChildListByRight(Integer userId, Integer parentId,
			boolean hasContentOnly) {
		Finder f = Finder.create("select bean from Channel bean");
		f.append(" join bean.users user");
		f.append(" where user.id=:userId and bean.parent.id=:parentId");
		f.setParam("userId", userId).setParam("parentId", parentId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Channel> getChildListByDepartId(Integer departId,Integer siteId,
			Integer parentId, boolean hasContentOnly){
		Finder f = Finder.create("select distinct bean from Channel bean");
		f.append(" left join bean.departments depart");
		f.append(" where  bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		if(departId!=null){
			f.append(" and depart.id =:departId ");
			f.setParam("departId", departId);
		}
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Channel> getControlChildListByDepartId(Integer departId,Integer siteId,
			Integer parentId, boolean hasContentOnly){
		Finder f = Finder.create("select distinct bean from Channel bean");
		f.append(" left join bean.controlDeparts depart");
		f.append(" where  bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		if(departId!=null){
			f.append(" and depart.id =:departId ");
			f.setParam("departId", departId);
		}
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (hasContentOnly) {
			f.append(" and bean.hasContent=true");
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	public Channel findById(Integer id) {
		Channel entity = get(id);
		return entity;
	}
	
	public Integer findByIdAndDepartId(Integer channelId, Integer departmentId) {
		String sql = "select count(channel_id) from jc_channel_department where channel_id="+channelId+"and department_id="+departmentId;
		Object obj=getSession().createSQLQuery(sql).uniqueResult();
		return obj==null?0:Integer.parseInt(obj.toString());
	}
	
	public Integer getCountById(Integer channelId){
		String sql = "select count(channel_id) from jc_channel where parent_id="+channelId;
		Object obj=getSession().createSQLQuery(sql).uniqueResult();
		return obj==null?0:Integer.parseInt(obj.toString());
	}

	public Channel findByPath(String path, Integer siteId, boolean cacheable) {
		String hql = "from Channel bean where bean.path=? and bean.site.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, path).setParameter(1, siteId);
		// 做一些容错处理，因为毕竟没有在数据库中限定path是唯一的。
		query.setMaxResults(1);
		return (Channel) query.setCacheable(cacheable).uniqueResult();
	}

	public Channel save(Channel bean) {
		getSession().save(bean);
		return bean;
	}

	public Channel deleteById(Integer id) {
		Channel entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	public void initWorkFlow(Integer workflowId){
		String hql = "update Channel bean set bean.workflow.id=null  where bean.workflow.id=:workflowId";
		Query query = getSession().createQuery(hql).setParameter("workflowId", workflowId);
		query.executeUpdate();
	}

	@Override
	protected Class<Channel> getEntityClass() {
		return Channel.class;
	}

	/**
	 * 获取栏目路径
	 */
	public Channel getChannelPathByDid(Integer departId){
		Query query = getSession().createQuery(" from Channel bean where bean.departId =:departId");
		query.setParameter("departId", departId);
		Object object = query.uniqueResult();
		return (Channel)object;
	}
	

	/**
	 * 获取栏目路径in
	 */
	@SuppressWarnings("unchecked")
	public List<Channel> getChannelPathByDids(String departIds){
		String[] departids = departIds.split(",");
		Integer[] deps = new Integer[departids.length];
		for (int i = 0; i < deps.length; i++) {
			deps[i] = Integer.parseInt(departids[i]);
		}
		Finder f = Finder.create(" from Channel bean where bean.departId in (:deps)");
		f.setParamList("deps", deps);
		return find(f);
	}
	
	public Integer findChannelidByDepartid(Integer departId){
		String sql = "select channel_id from jc_channel where DEPART_ID="+departId;
		Object obj=getSession().createSQLQuery(sql).uniqueResult();
		return obj==null?0:Integer.parseInt(obj.toString());
	}
	
	public List<Channel> findChildFirst(Integer pid){
		StringBuffer sb = new StringBuffer();
		sb.append("from Channel bean where bean.parent.id=");
		sb.append(pid).append(" and bean.priority=1");
		List<Channel> list =getSession().createQuery(sb.toString()).list();
		return list;
	}

	public List<Channel> findByChannelName(String name){
		StringBuffer sb = new StringBuffer();
		sb.append("from Channel bean where bean.id in (select bean2.id from ChannelExt bean2 where bean2.name='");
		sb.append(name).append("')");
		List<Channel> list =getSession().createQuery(sb.toString()).list();
		return list;
	}

	@Override
	public String getSiteIdByDids(String departId) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(departId)){
			return null;
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("from Channel bean where bean.departId="+departId);
			List<Channel> list =getSession().createQuery(sb.toString()).list();
			if((list==null)||(list.size()==0)){
				return null;
			}else{
				Integer siteIds = list.get(0).getSite().getId();
				String siteId = String.valueOf(siteIds);
				return siteId;
			}
		}
	}

	@Override
	public List<Channel> existChildChannel(Integer parentChildId) {
		// TODO Auto-generated method stub
		String hql = "from Channel bean where 1 = 1 ";
		if(parentChildId!=null){
			hql = hql + " and bean.parent.id = " + parentChildId;
		}
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	/**
	 * 根据条件获取下级栏目
	 */
	public Channel getfindBychannel(Integer parentid){
		String hql = "from Channel bean where bean.parent="+parentid+"and bean.path like '%yxdwgze'";
		List query = getSession().createQuery(hql).list();
		return (Channel) query.get(0);
	}
	
	/**
	 * 绑定栏目
	 */
	public void savechannel(Integer channelid,Integer depatid){
		String sql="insert into jc_channel_department values("+channelid+","+depatid+")";
		int i=getSession().createSQLQuery(sql).executeUpdate();
	}
}