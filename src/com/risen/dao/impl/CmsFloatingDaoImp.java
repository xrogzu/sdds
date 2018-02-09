package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.CmsFloatingDao;
import com.risen.entity.CmsFloating;
@Repository
public class CmsFloatingDaoImp extends HibernateBaseDao<CmsFloating, Integer> implements CmsFloatingDao{
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public CmsFloating findById(Integer sddsfiId) {
		CmsFloating entity = get(sddsfiId);
		return entity;
	}

	public CmsFloating save(CmsFloating bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsFloating deleteById(Integer sddsfiId) {
		CmsFloating entity = super.get(sddsfiId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<CmsFloating> getEntityClass() {
		return CmsFloating.class;
	}

	@Override
	public List<CmsFloating> getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(" from CmsFloating bean where 1=1");
		if(!StringUtils.isEmpty(userId)){
			sb.append(" and bean.sddsfiUserid = "+userId);
		}
		List<CmsFloating> list = getSession().createQuery(sb.toString()).list();
		return list;
	}

	@Override
	public List<CmsFloating> getUserByType(String type) {
		// TODO Auto-generated method stub
		String hql = "from CmsFloating bean where 1=1";
		if(StringUtils.hasText(type)){
			if("2".equals(type)){
				hql = hql + " and bean.sddsfiInprovince = '1'";
			}else if("3".equals(type)){
				hql = hql + " and bean.sddsfiIncity = '1'";
			}else if("4".equals(type)){
					hql = hql + " and bean.sddsfiIncounty = '1'";
			}else if("6".equals(type)){
				hql = hql + " and bean.sddsfiOutprovince = '1'";
			}else if("7".equals(type)){
				hql = hql + " and bean.sddsfiOutcity = '1'";
			}else if("8".equals(type)){
				hql = hql + " and bean.sddsfiOutcounty = '1'";
			}
		}
		List<CmsFloating> list = getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public void updateFloating(Integer userId, String changeType,
			CmsDepartment depart) {
		// TODO Auto-generated method stub
		String sql = "update CmsFloating bean set";
		if(depart==null){
			if("2".equals(changeType)){
				sql += " bean.sddsfiInprovince = '1',bean.sddsfiIncity='0',bean.sddsfiIncounty='0' ";
			}else if("3".equals(changeType)){
				sql += " bean.sddsfiInprovince = '0',bean.sddsfiIncity='1',bean.sddsfiIncounty='0' ";
			}else if("4".equals(changeType)){
				sql += " bean.sddsfiInprovince = '0',bean.sddsfiIncity='0',bean.sddsfiIncounty='1' ";
			}
			sql += (" where bean.sddsfiUserid = '"+String.valueOf(userId)+"'");
		}else{
			if("2".equals(changeType)){
				sql += " bean.sddsfiInprovince = '1',bean.sddsfiIncity='0',bean.sddsfiIncounty='0' ";
			}else if("3".equals(changeType)){
				sql += " bean.sddsfiInprovince = '0',bean.sddsfiIncity='1',bean.sddsfiIncounty='0' ";
			}else if("4".equals(changeType)){
				sql += " bean.sddsfiInprovince = '0',bean.sddsfiIncity='0',bean.sddsfiIncounty='1' ";
			}
			if("6".equals(changeType)){
				sql += " bean.sddsfiOutprovince = '1',bean.sddsfiOutcity='0',bean.sddsfiOutcounty='0', ";
			}else if("7".equals(changeType)){
				sql += " bean.sddsfiOutprovince = '0',bean.sddsfiOutcity='1',bean.sddsfiOutcounty='0', ";
			}else if("8".equals(changeType)){
				sql += " bean.sddsfOutprovince = '0',bean.sddsfiOutcity='0',bean.sddsfiOutcounty='1', ";
			}
			sql += ("bean.sddsfiOrgid="+depart.getId());
			sql += (",bean.sddsfiOrgname='"+depart.getName()+"'");
			sql += (" where bean.sddsfiUserid = '"+String.valueOf(userId)+"'");
		}
		getSession().createQuery(sql).executeUpdate();
	}
}
