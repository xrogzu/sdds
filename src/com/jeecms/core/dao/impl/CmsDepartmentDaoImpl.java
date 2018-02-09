package com.jeecms.core.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;

@Repository
public class CmsDepartmentDaoImpl extends
		HibernateBaseDao<CmsDepartment, Integer> implements CmsDepartmentDao {
	@SuppressWarnings("unchecked")
	public List<CmsDepartment> getList(Integer parentId,boolean all){
		Finder f = Finder.create("from CmsDepartment bean");
		if(!all){
			if(parentId!=null){
				f.append(" where bean.parent.id=:parentId").setParam("parentId", parentId);
			}else{
				f.append(" where bean.parent is null ");
			}
		}
		f.append(" order by bean.priority asc ");
		return find(f);
	}
	public Pagination getPage( String name, int pageNo,int pageSize) {
		Finder f = Finder.create("from CmsDepartment bean where 1=1");
		if (StringUtils.isNotBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.priority asc,weights desc");
		return find(f, pageNo, pageSize);
	}

	public CmsDepartment findById(Integer id) {
		CmsDepartment entity = get(id);
		return entity;
	}
	
	public CmsDepartment findByName(String name) {
		return findUniqueByProperty("name", name);
	}

	public CmsDepartment save(CmsDepartment bean) {
		getSession().save(bean);
		return bean;
	}


	public CmsDepartment deleteById(Integer id) {
		CmsDepartment entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	/**
	 *  模糊查询组织名
	 */
	public List<CmsDepartment> likeByName(String name) {
		return likeUniqueByProperty("name", "%" + name + "%");
	}
	/**
	 * 换届提醒List
	 */
	@SuppressWarnings("unchecked")
	public List<CmsDepartment> changeRemindList(String departmentName,Integer departid,Integer pid){
		String mainsql = "select t.depart_id,t.depart_name,t.sddspo_secretary,t.parent_id,t.sddspo_changelasttime,t.priority,round(to_number(t.sddspo_changelasttime+0-sysdate)) as overplus from jc_department t where 1=1";
		String condition1 = "";
		String condition2 = "";
		String pidsql = "";
		if(pid != null && pid != 0){
			pidsql = "start with depart_id="+pid+" connect by prior t.depart_id=t.parent_id ";
		}
		if(departid != null){
			condition1 = " and t.depart_id="+departid;
		}
		if(!StringUtils.isBlank(departmentName)){
			condition2 = " and t.depart_name like '%" + departmentName + "%'";
		} 
		
		String orderby = "order by priority,depart_id";
		String sql = mainsql + pidsql + condition1 + condition2 + orderby;
		List<Object[]> list = getSession().createSQLQuery(sql).list();
		List<CmsDepartment> list2 = new ArrayList<CmsDepartment>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] objects : list) {
			CmsDepartment model = new CmsDepartment();
			model.setId(Integer.valueOf(String.valueOf(objects[0])));
			model.setName(String.valueOf(objects[1]));
			model.setSddspoSecretary(String.valueOf(objects[2]));
			if(objects[3]!=null && objects[3]!=""){
				model.setPid(Double.valueOf(String.valueOf(objects[3])));
			}else {
				model.setPid(0.0);
			}
			try {
				if(objects[4]!=null){
					model.setSddspoChangelasttime(sdf.parse(String.valueOf(objects[4])));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setOverplus(String.valueOf(objects[5]));
			list2.add(model);
		}
		return list2;
	}
	/**
     * 根据父级id查询组织列表
     */
	@SuppressWarnings("unchecked")
    public List<Object[]> findlistByPid(Integer pid){
    	String sql = " select * from JC_DEPARTMENT where PARENT_ID = '" + pid + "'";
    	List<Object[]> list = getSession().createSQLQuery(sql).list();
    	return list;
    }
    
    
	protected Class<CmsDepartment> getEntityClass() {
		return CmsDepartment.class;
	}
	@Override
	public List<CmsDepartment> baseScore(Integer parentId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select depart_id as id, depart_name as name,");
		sql.append(" (select count(*) from jc_user t ");
		sql.append(" where t.depart_id=b.depart_id)as meberCount ,");
		sql.append(" case when c.risenit_base is null or c.risenit_base='' then 1 ");
		sql.append(" else c.risenit_base end as score ");
		sql.append(" from  jc_department b ");
		sql.append("left join risen_integral c on b.depart_id=c.risenit_orgid ");
		sql.append(" where parent_id="+parentId);
		sql.append(" order by depart_id");
   
		/*String sql="select depart_id as id,depart_name as name,(select count(*) from jc_user t" +
				" where t.depart_id=b.depart_id)as meberCount from jc_department b where parent_id="+parentId;*/
		List<Object[]>list=null;
		list=getSession().createSQLQuery(sql.toString()).list();
		List<CmsDepartment>depts=new ArrayList<CmsDepartment>();
		for (Object[] o : list) {
			CmsDepartment dept=new CmsDepartment();
			dept.setId(Integer.valueOf(o[0].toString()));
			dept.setName(o[1].toString());
			dept.setMeberCount(Integer.valueOf(o[2].toString()));
			dept.setScore(Double.valueOf(o[3].toString()));
			depts.add(dept);
		}
		return depts;
	}
	
	/**
     * 导入组织小程序
     */
	@SuppressWarnings("unchecked")
	public List<CmsDepartment> findNotInUser(){
		String hql = " from CmsDepartment d where d.name not in (select u.username from CmsUser u where u.sddscpIsperororg = '2')";
		Finder f = Finder.create(hql);
		return find(f);
	}
	@Override
	public List<CmsDepartment> getDeptByType(String type) {
		Finder f = Finder.create("select bean.id,bean.name from CmsDepartment bean ");
		f.append(" where bean.sddspoOrgtype=:type").setParam("type", type);
		f.append(" order by bean.id");
		return find(f);
	}
	@Override
	public int getMaxPriority(Integer parentId) {
		String sql=null;
		if(parentId!=null){
		  sql="select max(PRIORITY) from jc_department where parent_id="+parentId;
		}else{
			sql="select max(PRIORITY) from jc_department where parent_id is null";
		}
		
		int pri=Integer.valueOf(getSession().createSQLQuery(sql).uniqueResult().toString()) ;
		return pri;
	}

    /**
     * 替换书记
     */
    public CmsDepartment changeSecretaryByDid(CmsDepartment model){
    	/*String sql = "update jc_department set sddspo_secretary = '"
    		+ sddspoSecretary + "' , sddspo_secretaryidc = '" 
    		+ sddspoSecretaryidc + "' , sddspo_secretaryid = "
    		+ sddspoSecretaryid + " where depart_id = " + departid;*/
//    	String hql = "update CmsDepartment bean set bean.sddspoSecretary=:sddspoSecretary,bean.sddspoSecretaryidc=:sddspoSecretaryidc,bean.sddspoSecretaryid=:sddspoSecretaryid,"
//    		+ "bean.sddspoChangelasttime=:sddspoChangelasttime where bean.id=:departid";
    	String hql = "update CmsDepartment bean set bean.sddspoChangelasttime=:sddspoChangelasttime,bean.sddspoChangecdate=:sddspoChangecdate where bean.id=:departid ";
    	Query query = getSession().createQuery(hql);
//    	query.setParameter("sddspoSecretary", model.getSddspoSecretary());
//    	query.setParameter("sddspoSecretaryidc", model.getSddspoSecretaryidc());
//    	query.setParameter("sddspoSecretaryid", model.getSddspoSecretaryid());
    	query.setParameter("sddspoChangelasttime", model.getSddspoChangelasttime());
    	query.setParameter("sddspoChangecdate", model.getSddspoChangecdate());
    	query.setParameter("departid", model.getId());
    	query.executeUpdate();
    	return model;
    }


    /**
     * 新增党员职务关联
     */
    public CmsDepartment updatejob(CmsDepartment model){
    	String hql = "update CmsDepartment bean set bean.sddspoSecretary=:sddspoSecretary,bean.sddspoSecretaryidc=:sddspoSecretaryidc,bean.sddspoSecretaryid=:sddspoSecretaryid "+
    	"where bean.id=:departid";
    	Query query = getSession().createQuery(hql);
    	query.setParameter("sddspoSecretary", model.getSddspoSecretary());
    	query.setParameter("sddspoSecretaryidc", model.getSddspoSecretaryidc());
    	query.setParameter("sddspoSecretaryid", model.getSddspoSecretaryid());
    	query.setParameter("departid", model.getId());
    	query.executeUpdate();
    	return model;
    }
    
    /**
     * 修改user数据工具
     */
    public List<CmsDepartment> findalllistdepart(){
    	Finder finder = Finder.create(" from CmsDepartment");
    	return find(finder);
    }
	@Override
	public List<CmsDepartment> getDeptByTypeAndLoginId(String type,Integer loginId) {
		Finder f = Finder.create("select bean.id,bean.name from CmsDepartment bean ");
		f.append(" where bean.sddspoOrgtype=:type and bean.parent.id=:loginId" ).setParam("type", type).setParam("loginId", loginId);
		f.append(" order by bean.id");
		return find(f);
	}
	public List<CmsDepartment> findZbList(Integer id,Integer pid){
		Finder f = Finder.create("from CmsDepartment bean where bean.parent.id=:parentId and bean.id=:id or bean.id=:parentId2");
		f.setParam("parentId", pid);
		f.setParam("parentId2", pid);
		f.setParam("id", id);
		f.append(" order by bean.priority asc ");
		return find(f);
	}
	/**
     * 查看换届
     */
	@SuppressWarnings("unchecked")
    public List<CmsDepartment> checkChangelist (String departName){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
    	String hqlmain = "";
    	String hql2 = "";
    	String param = sd.format(date)+"-01-01";
    	if(!StringUtils.isBlank(departName)){
    		hql2= " and sddscc_orgname = '" + departName + "'";
    	}
    	hqlmain = " select * from risen_changeremindrecord t where (t.sddscc_recoddate between to_date('" + param + "','yyyy-MM-dd') and sysdate) ";
    	Query query = getSession().createSQLQuery(hqlmain+hql2);
    	List<Object[]> objList = query.list();
    	List<CmsDepartment> list = new ArrayList<CmsDepartment>();
    	
    	for (Object[] objects : objList) {
    		CmsDepartment dpa = new CmsDepartment();
    		dpa.setId(Integer.parseInt(objects[0].toString()));
    		dpa.setName(objects[1].toString());
    		//用于保存上次换届的附件
    		dpa.setSddspoRemark((objects[11]==null) ? null : (objects[11].toString()));
    		try {
        		dpa.setSddspoChangecdate(sd2.parse(sd2.format(objects[8])));
			} catch (Exception e) {
				// TODO: handle exception
			}
    		list.add(dpa);
		}
    	return list;
    }

    public Integer findChildrenFirstDeptidByPid(Integer pid){
    	StringBuffer sb = new StringBuffer();
    	sb.append("select DEPART_ID from JC_DEPARTMENT where PRIORITY=1 and PARENT_ID=").append(pid);
    	Object obj = getSession().createSQLQuery(sb.toString()).uniqueResult();
    	return Integer.parseInt(obj.toString());
    }

    public Boolean findChildrenIsBoolean(int[]pid){
    	if(pid==null || pid.length==0){
    		return false;
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append("select DEPART_ID from JC_DEPARTMENT where PARENT_ID in (");
    	for(int i=0; i<pid.length; i++){
    		if(i != pid.length-1){
    			sb.append(pid[i]).append(",");
    		}else{
    			sb.append(pid[i]).append(")");
    		}
    	}
    	List obj = getSession().createSQLQuery(sb.toString()).list();
    	Boolean p = false;
    	if(obj != null && obj.size() >0){
    		p = true;
    	}
    	return p;
    }
    
    public List<CmsDepartment> findAdminDept(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("select * from jc_department a where a.parent_id in(");
    	sb.append(" select a.depart_id from jc_department a where a.parent_id is null");
    	sb.append(") and a.priority=1 and a.sddspo_orgtype='机关党委'");
    	List<CmsDepartment> list = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class).list();
    	return list;
    }
	@SuppressWarnings("unchecked")
	public List<CmsDepartment> findNopidList(){
		Finder finder = Finder.create(" from CmsDepartment bean where bean.parent.id is null order by bean.priority");
		return find(finder);
	}

    public List<CmsDepartment> findListByIp(String ip){
    	Finder finder = Finder.create(" from CmsDepartment bean where bean.sddspoIp like '%" + ip + "%'");
    	List<CmsDepartment> list = find(finder);
    	return list;
    }

    public List<Object[]> statisticsDeptSum(Integer deptid){
    	StringBuffer sb = new StringBuffer();
    	sb.append("select count(*), t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL from (select * from jc_department t start with ");
    	if(deptid == null || deptid == 0){
    		sb.append(" t.parent_id is null or t.parent_id='' ");
    	}else{
    		sb.append(" t.depart_id=").append(deptid);
    	}
    	sb.append(" connect by prior t.depart_id=t.parent_id) t1 group by t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL");
    	List<Object[]> obj = getSession().createSQLQuery(sb.toString()).list();
    	return obj;
    }
    public List<Object[]> statisticsDeptQmhjSum(Integer deptid, String sdate, String edate){
    	StringBuffer sb = new StringBuffer();
    	sb.append("select count(*), t1.sddspo_orgtype from (select * from jc_department t where t.SDDSPO_CHANGECDATE between to_date(");
    	sb.append("'").append(sdate).append("'");
    	sb.append(",'yyyy/mm/dd') and to_date(").append("'").append(edate).append("'");
    	sb.append(",'yyyy/mm/dd')  start with ");
    	if(deptid == null || deptid == 0){
    		sb.append(" t.parent_id is null or t.parent_id='' ");
    	}else{
    		sb.append(" t.depart_id=").append(deptid);
    	}
    	sb.append(" connect by prior t.depart_id=t.parent_id) t1 group by t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL");
    	System.out.println(sb.toString());
    	List<Object[]> obj = getSession().createSQLQuery(sb.toString()).list();
    	return obj;
    }

    public List<Object[]> statisticsDeptYhjSum(Integer deptid, String sdate, String edate){
    	StringBuffer sb = new StringBuffer();
    	sb.append("select count(*),t3.sddspo_orgtype from risen_changeremindrecord t2,(select * from jc_department t start with ");
    	if(deptid == null || deptid == 0){
    		sb.append(" t.parent_id is null or t.parent_id='' ");
    	}else{
    		sb.append(" t.depart_id=").append(deptid);
    	}
    	sb.append(" connect by prior t.depart_id=t.parent_id) t3 where t2.sddscc_orgid=t3.depart_id and t2.sddscc_recoddate between to_date(");
    	sb.append("'").append(sdate).append("'");
    	sb.append(",'yyyy/mm/dd') and to_date(").append("'").append(edate).append("'");
    	sb.append(",'yyyy/mm/dd') group by t3.sddspo_orgtype");
    	System.out.println(sb.toString());
    	List<Object[]> obj = getSession().createSQLQuery(sb.toString()).list();
    	return obj;
    }
    
    public List<CmsDepartment> findAll(){
    	Finder f = Finder.create("from CmsDepartment bean order by bean.priority asc");
		return find(f);
    }

    public List<CmsDepartment> findXtForJGDWByDeptId(Integer detpid){
    	StringBuffer sb = new StringBuffer();
    	sb.append("from CmsDepartment bean where bean.parent.id in").append("(select bean2.id from CmsDepartment bean2 where bean2.parent.id=").append(detpid).append(")");
    	sb.append(" and ( bean.sddspoOrgtype='机关党委' or bean.sddspoOrgtype='党总支')");
    	return getSession().createQuery(sb.toString()).list();
    }
    public List<CmsDepartment> findXtForJGDWByDeptIdse(Integer dds){
    	StringBuffer sb = new StringBuffer();
    	sb.append("from CmsDepartment bean where bean.parent.id in").append("(select bean2.id from CmsDepartment bean2 where bean2.parent.id=").append(dds).append(")");
    	sb.append(" and ( bean.sddspoOrgtype='机关党委' or bean.sddspoOrgtype='党总支')");
    	return getSession().createQuery(sb.toString()).list();
    }
    
    public Integer findCountByPid(Integer Pid){
    	String sql=null;
		if(Pid!=null && Pid>=0){
			sql="select count(jc_department.depart_id) from jc_department where parent_id="+Pid;
		}else{
			sql="select count(jc_department.depart_id) from jc_department where 1<>1";
		}
		return Integer.valueOf(getSession().createSQLQuery(sql).uniqueResult().toString()) ;
    }
    
    public List<Object[]> findDeptByPid(Integer Pid){
    	String sql=null;
		if(Pid!=null && Pid>=0){
			sql="select t.depart_id,t.parent_id,t.depart_name from jc_department t where parent_id="+Pid;
		}else{
			sql="select t.depart_id,t.parent_id,t.depart_name from jc_department t where 1<>1";
		}
    	return getSession().createSQLQuery(sql).list();
    }
    @Override
	public List<CmsDepartment> getAllDeptByIdAndName(String deptId,
			String deptName) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(" from CmsDepartment bean where bean.parent = "+deptId);
		sb.append(" and bean.name like '%"+deptName+"%'");
		List<CmsDepartment> depts = getSession().createQuery(sb.toString()).list();
    	return depts;
	}
	@Override
	public List<CmsDepartment> getAllJgdwDept() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(" from CmsDepartment bean where bean.name like '%市局机关党委%' and bean.priority = 1 and bean.id !=1");
		sb.append(" order by bean.parent asc");
		List<CmsDepartment> depts = getSession().createQuery(sb.toString()).list();
		return depts;
	}
	@Override
	public List<CmsDepartment> getAllJgdwDeptById(Integer deptId,boolean isShiju) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(isShiju){
			sb.append("select * from jc_department bean where bean.parent_id in  (select depart_id from jc_department  where priority != 1 and parent_id = "+deptId);
		}else{
			sb.append("select * from jc_department bean where bean.depart_id in  (select depart_id from jc_department  where priority != 1 and parent_id = "+deptId);
		}
		sb.append(" ) and (bean.sddspo_orgtype= '机关党委' or bean.sddspo_orgtype='党总支')");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts;
	}
	@Override
	public List<CmsDepartment> getAllZhiBuById(Integer departId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(departId!=null){
			if(departId==1){
				sb.append("select * from jc_department bean where bean.parent_id in  (select depart_id from jc_department where depart_id = "+departId);
			}else{
				sb.append("select * from jc_department bean where bean.depart_id in  (select depart_id from jc_department where parent_id = "+departId);
			}
		}else{
			sb.append("select * from jc_department bean where bean.parent_id in  (select depart_id from jc_department where depart_id = 1");
		}
		sb.append(" ) and bean.sddspo_orgtype= '支部' ");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts;
	}
	@Override
	public List<CmsDepartment> findByParentIds(Integer parentid, boolean ist) {
		 String sql="select * from jc_department  where 1=1";
		    if(parentid!=null){
		    	if(parentid==1){
		    		sql=sql+" and depart_id=1";
		    	}else{
		    		sql=sql+" and PARENT_ID="+parentid+" and (sddspo_orgtype='机关党委'or sddspo_orgtype='党总支') ";
		    	}
		   }
		    if(ist){
		      sql=sql+" and priority='1'";
		    }
		    SQLQuery sqll=getSession().createSQLQuery(sql).addEntity(CmsDepartment.class);
		return sqll.list();
	}
	@Override
	public List<Object[]> statisticsDeptYqhjSum(Integer parentId,String nowTime) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
    	sb.append("select count(*), t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL from (select * from jc_department t start with ");
    	if(parentId == null || parentId == 0){
    		sb.append(" t.parent_id is null or t.parent_id='' ");
    	}else{
    		sb.append(" t.depart_id=").append(parentId);
    	}
    	sb.append(" connect by prior t.depart_id=t.parent_id) t1 where 1=1");
    	if(nowTime!=null && nowTime!=""){
    		sb.append(" and t1.sddspo_changelasttime < to_date('"+nowTime+"','yyyy/MM/dd')");
    	}
    	sb.append(" group by t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL");
    	List<Object[]> obj = getSession().createSQLQuery(sb.toString()).list();
    	return obj;
	}
	@Override
	public List<Object[]> statisticsDeptAqhjSum(Integer parentId, String nowTime) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*), t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL from (select * from jc_department t start with ");
		if (parentId == null || parentId == 0) {
			sb.append(" t.parent_id is null or t.parent_id='' ");
		} else {
			sb.append(" t.depart_id=").append(parentId);
		}
		sb.append(" connect by prior t.depart_id=t.parent_id) t1 where 1=1");
		if (nowTime != null && nowTime != "") {
			sb.append(" and t1.sddspo_changelasttime >= to_date('" + nowTime
					+ "','yyyy/MM/dd')");
		}
		sb.append(" group by t1.sddspo_orgtype,t1.SDDSPO_ORGLEVEL");
		System.out.println(sb.toString());
		List<Object[]> obj = getSession().createSQLQuery(sb.toString()).list();
		return obj;
	}
	@Override
	public Integer getMyActualParent(Integer departId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
    	sb.append("select DEPART_ID from JC_DEPARTMENT where SDDSPO_ORGTYPE in ('机关党委','党总支') and PARENT_ID=").append(departId);
    	Object obj = getSession().createSQLQuery(sb.toString()).uniqueResult();
    	return Integer.parseInt(obj.toString());
	}
	@Override
	public List<CmsDepartment> getAllDeptById(Integer deptId, boolean isShiju) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("select * from jc_department bean where bean.depart_id in");
		sb.append(" (select depart_id from jc_department t start with ");
		if (deptId == null || deptId == 1) {
			sb.append(" t.parent_id is null or t.parent_id='' ");
		} else {
			sb.append(" t.depart_id=").append(deptId);
		}
		sb.append(" connect by prior t.depart_id=t.parent_id) ");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts;
	}
	@Override
	public boolean oneIsInDepts(Integer departId, Integer targetDeptId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(" select * from ");
		sb.append(" (select depart_id from jc_department t start with ");
		if (departId == null || departId == 1) {
			sb.append(" t.parent_id is null or t.parent_id='' ");
		} else {
			sb.append(" t.depart_id=").append(departId);
		}
		sb.append(" connect by prior t.depart_id=t.parent_id) t1 where 1=1");
		if(targetDeptId!=null){
			sb.append(" and t1.depart_id = "+targetDeptId);
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		if(depts==null){
			return false;
		}else{
			return (depts.size()==0) ? false : true;
		}
	}
	public CmsDepartment findByJgdw(Integer pid){
		String sql =" Select * from jc_department where parent_id="+pid+" and SDDSPO_ORGTYPE='机关党委'";
		SQLQuery query =getSession().createSQLQuery(sql).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts.get(0);
	}
	public CmsDepartment findByPid(Integer pid){
		String sql =" Select * from jc_department where parent_id="+pid+" and (SDDSPO_ORGTYPE='机关党委' or SDDSPO_ORGTYPE='党总支')";
		SQLQuery query =getSession().createSQLQuery(sql).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts.get(0);
	}
	@Override
	public List<CmsDepartment> getAllTypeDeptById(Integer deptId, String type) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("select * from jc_department bean where bean.depart_id in");
		sb.append(" (select depart_id from jc_department t start with ");
		if (deptId == null || deptId == 1) {
			sb.append(" t.parent_id is null or t.parent_id='' ");
		} else {
			sb.append(" t.depart_id=").append(deptId);
		}
		sb.append(" connect by prior t.depart_id=t.parent_id) ");
		if(!StringUtils.isBlank(type)){
			if("支部".equals(type)){
				sb.append(" and sddspo_orgtype = '支部'");
			}else if("机关党委".equals(type)){
				sb.append(" and sddspo_orgtype in ('机关党委','党总支')");
			}else if("工作指导组".equals(type)){
				sb.append(" and sddspo_orgtype = '工作指导组' ");
			}
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addEntity(CmsDepartment.class);
		List<CmsDepartment> depts = query.list();
		return depts;
	}
}