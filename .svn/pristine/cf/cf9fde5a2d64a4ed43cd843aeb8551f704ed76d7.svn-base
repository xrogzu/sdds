package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenSignforDao;
import com.risen.entity.RisenSignfor;

@Repository
public class RisenSignforDaoImpl extends HibernateBaseDao<RisenSignfor, Integer> implements IRisenSignforDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenSignfor findById(Integer id) {
		RisenSignfor entity = get(id);
		return entity;
	}

	public RisenSignfor save(RisenSignfor bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenSignfor deleteById(Integer id) {
		RisenSignfor entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public Boolean findModelBy(Integer deptid, String contentid){
		Integer contid = null;
		try {
			contid = Integer.parseInt(contentid);
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
		Finder f = Finder.create("from RisenSignfor bean where bean.risensfDept.id=:deptid and bean.risensfContent.id=:contid");
		f.setParam("deptid", deptid).setParam("contid", contid);
		List<RisenSignfor> list = find(f);
		return list==null || list.size()<1?false:true;
	}
	

	public List<RisenSignfor> findModelByDeptListAndContent(Integer[]deptlist, String contentid){
		Integer contid = null;
		try {
			contid = Integer.parseInt(contentid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Finder f = Finder.create("from RisenSignfor bean where bean.risensfDept.id in (:deptlist) and bean.risensfContent.id=:contentid");
		f.setParamList("deptlist", deptlist);
		f.setParam("contentid", contid);
		List<RisenSignfor> list = find(f);
		return list;
	}
	@Override
	protected Class<RisenSignfor> getEntityClass() {
		return RisenSignfor.class;
	}

	@Override
	public List<RisenSignfor> findModelByContentId(String contentId) {
		// TODO Auto-generated method stub
		String sql = "from RisenSignfor bean where bean.risensfContent.id = :contentId";
		Query query = getSession().createQuery(sql).setParameter("contentId", new Integer(contentId));
		List<RisenSignfor> list = query.list();
		return list;
	}
}