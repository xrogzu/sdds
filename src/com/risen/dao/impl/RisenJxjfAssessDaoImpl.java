package com.risen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.risen.dao.IRisenJxjfAssessDao;
import com.risen.entity.RisenJxjfAssess;

@Repository
public class RisenJxjfAssessDaoImpl extends HibernateBaseDao<RisenJxjfAssess, Integer>  implements  IRisenJxjfAssessDao{

	@Override
	public List<RisenJxjfAssess> findbydepartpg(Integer departid) {
		// TODO Auto-generated method stub
		String sql = "select * from risen_jxjfAssess where departid="+departid;
		List<Object[]> obj=getSession().createSQLQuery(sql).list();
		List<RisenJxjfAssess> jxpglist = new ArrayList<RisenJxjfAssess>();
		try {
			for (Object[] objects : obj) {
				RisenJxjfAssess jxpg = new RisenJxjfAssess();
				jxpg.setChannelname(objects[1].toString());
				jxpg.setDepartid(Integer.parseInt(objects[7].toString()));
				jxpg.setDisqualificationevalua(objects[6].toString());
				jxpg.setExcellentscore(Double.parseDouble(objects[3].toString()));
				jxpg.setPassingevaluate(objects[4].toString());
				jxpg.setPassingscore(Double.parseDouble(objects[2].toString()));
				jxpg.setExcellentevaluate(objects[5].toString());
				jxpg.setId(Integer.parseInt(objects[0].toString()));
				jxpglist.add(jxpg);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jxpglist;
	}

	@Override
	protected Class<RisenJxjfAssess> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenJxjfAssess.class;
	}

	@Override
	public String save(RisenJxjfAssess jsxp) {
		// TODO Auto-generated method stub
		Object obj=getSession().save(jsxp);
		if(obj!=null){
			return "chenggong" ;
		}else{
			return "shibai" ;
		}
		
	}

	@Override
	public RisenJxjfAssess findbyid(Integer id) {
		// TODO Auto-generated method stub
		String sql="select * from risen_jxjfAssess where id="+id;
		return (RisenJxjfAssess) getSession().createSQLQuery(sql).addEntity(RisenJxjfAssess.class).uniqueResult();
	}

	@Override
	public String update(RisenJxjfAssess jsxp) {
		// TODO Auto-generated method stub
		try {
			getSession().update(getSession().merge(jsxp));
			return "chenggong";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "shibai";
		}
		
		
	}
	public String findbychannelname(Integer departid,String channelname){
		String flag = null;
		if(departid!=null && channelname!=null){
		String sql="select * from risen_jxjfAssess where departid="+departid+"and channelname='"+channelname+"'";
		List obj=getSession().createSQLQuery(sql).list();
		if(obj!=null && obj.size()>0){
			flag= "false";
		}else{
		flag= "true";
		}
	}
		return flag;
}
}
