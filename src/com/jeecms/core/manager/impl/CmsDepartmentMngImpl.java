package com.jeecms.core.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jeecms.cms.cache.DepartmentCache;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsUserMng;
import com.risen.entity.CoreDictionary;
import com.risen.service.ICoreDictionaryService;

@Service
@Transactional
public class CmsDepartmentMngImpl implements CmsDepartmentMng {
	
	@Transactional(readOnly = true)
	public List<CmsDepartment> getList(Integer parentId,boolean all){
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		if (DepartmentCache.departentList.size()>0) {
			for (CmsDepartment cmsDepartment : DepartmentCache.departentList) {
				if (all) {
					return DepartmentCache.departentList;
				}else if (parentId==null) {
					if (cmsDepartment.getParent()==null) {
						list.add(cmsDepartment);
					}
				}else if (cmsDepartment.getParent()!=null) {
					if (cmsDepartment.getParent().getId().equals(parentId)) {
						list.add(cmsDepartment);
					}
				}
			}
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<CmsDepartment> getTypeList(Integer parentId,String type) {
		// TODO Auto-generated method stub
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		if (DepartmentCache.departentList.size()>0) {
			for (CmsDepartment cmsDepartment : DepartmentCache.departentList) {
				if (parentId==null) {
					return DepartmentCache.departentList;
				}else if (parentId == 1) {
					if(cmsDepartment.getParent()!= null && cmsDepartment.getParent().getParent()==null){
						if(StringUtils.hasText(type)){
							if("机关党委".indexOf(type)>-1){
								if("机关党委".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
									list.add(cmsDepartment);
								}
							}
						}
					}
				}else if (cmsDepartment.getParent()!=null && cmsDepartment.getParent().getParent()!=null) {
					if (cmsDepartment.getParent().getParent().getId().equals(parentId)) {
						if(StringUtils.hasText(type)){
							if("机关党委,党总支".indexOf(type)>-1){
								if("机关党委,党总支".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
									list.add(cmsDepartment);
								}
							}else{
								if("支部".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
									list.add(cmsDepartment);
								}
							}
						}
					}else if(cmsDepartment.getParent().getId().equals(parentId) && cmsDepartment.getName().indexOf("市局机关党委")<0){
						if("机关党委,党总支".indexOf(type)>-1){
							if("机关党委,党总支".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
								list.add(cmsDepartment);
							}
						}else{
							if("支部".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
								list.add(cmsDepartment);
							}
						}
					}
				}
			}
		}
		return list;
	};
	
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo,
			int pageSize) {
		return dao.getPage( name, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public CmsDepartment findById(Integer id) {
		CmsDepartment entity = dao.findById(id);
		return entity;
	}

	public CmsDepartment save(CmsDepartment bean) {
		dao.save(bean);
		return bean;
	}

	public CmsDepartment save(CmsDepartment bean,Integer channelIds[],Integer[] controlChannelIds,Integer[]ctgIds) {
		CmsDepartment model = dao.save(bean);
		if (channelIds != null) {
			Channel channel;
			for (Integer cid : channelIds) {
				channel = channelMng.findById(cid);
				bean.addToChannels(channel);
			}
		}
		if (controlChannelIds != null) {
			Channel channel;
			for (Integer cid : controlChannelIds) {
				channel = channelMng.findById(cid);
				bean.addToControlChannels(channel);
			}
		}
		if(ctgIds!=null){
			CmsGuestbookCtg ctg;
			for(Integer cid:ctgIds){
				ctg=guestBookCtgMng.findById(cid);
				bean.addToGuestBookCtgs(ctg);
			}
		}
		cmsUserMng.registerMember(bean.getName().trim(), null, "123456", "127.0.0.1", 1, 0, null, null,
				null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, 
				null, null, null, null, null, null, model.getId(), null, null, null,null, null, null, null,"2",
				bean.getName(),bean.getSddspoLogincode() ,1,null,null,"委员",null,null,"1","1","1",bean.getName(),null,null,false, null, null,false,null);
		return bean;
	}

	public CmsDepartment update(CmsDepartment bean) {
		Updater<CmsDepartment> updater = new Updater<CmsDepartment>(bean);
		CmsDepartment entity = dao.updateByUpdater(updater);
		return entity;
	}
	
	public CmsDepartment update(CmsDepartment bean,Integer channelIds[],Integer[] controlChannelIds,Integer[]ctgIds) {
		CmsDepartment entity = findById(bean.getId());
		Updater<CmsDepartment> updater = new Updater<CmsDepartment>(bean);
		updater.include("parent");
		entity = dao.updateByUpdater(updater);
		if (channelIds != null) {
			Channel channel;
			for (Integer cid : channelIds) {
				channel = channelMng.findById(cid);
				entity.addToChannels(channel);
			}
		}
		if (controlChannelIds != null) {
			Channel channel;
			for (Integer cid : controlChannelIds) {
				channel = channelMng.findById(cid);
				entity.addToControlChannels(channel);
			}
		}
		if(ctgIds!=null){
			entity.getGuestBookCtgs().clear();
			CmsGuestbookCtg ctg;
			for(Integer cid:ctgIds){
				ctg=guestBookCtgMng.findById(cid);
				entity.addToGuestBookCtgs(ctg);
			}
		}
		return entity;
	}

	public void updatePriority(Integer[] ids, Integer[] priorities) {
		if (ids == null || priorities == null || ids.length <= 0
				|| ids.length != priorities.length) {
			return;
		}
		CmsDepartment department;
		for (int i = 0, len = ids.length; i < len; i++) {
			department = findById(ids[i]);
			department.setPriority(priorities[i]);
		}
	}

	public void deleteById(Integer id) {
		CmsDepartment bean = new CmsDepartment();
		if(findlistByPid(id)){
			cmsUserMng.deleteUserBydepartid(id);
			bean = dao.deleteById(id);
			DepartmentCache.departentList.remove(bean);//删除该组织
		}else{
			//先删支部
			List<CmsDepartment> departs = dao.getAllTypeDeptById(id,"支部");
			for (CmsDepartment cmsDepartment : departs) {
				cmsUserMng.deleteUserBydepartid(cmsDepartment.getId());
				bean = dao.deleteById(cmsDepartment.getId());
				DepartmentCache.departentList.remove(bean);//删除该组织
			}
			departs = dao.getAllTypeDeptById(id,"机关党委");
			if(departs!=null && departs.size()>0){
				for (CmsDepartment cmsDepartment : departs) {
					if("机关党委,党总支".indexOf(cmsDepartment.getSddspoOrgtype())>-1){
						cmsUserMng.deleteUserBydepartid(cmsDepartment.getId());
						bean = dao.deleteById(cmsDepartment.getId());
						DepartmentCache.departentList.remove(bean);//删除该组织
					}
				}
			}
		}
		//return null;
	}

	public CmsDepartment[] deleteByIds(Integer[] ids) {
		CmsDepartment[] beans = new CmsDepartment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			//beans[i] = deleteById(ids[i]);
			deleteById(ids[i]);
		}
		return beans;
	}
	public boolean nameExist(String name) {
		return dao.findByName(name)!=null;
	}
	
	public List<CmsDepartment> findAll(){
		return dao.findAll();
	}

	/**
	 * 换届提醒
	 */
	@Transactional (readOnly = true)
	public List<CmsDepartment> changeRemindList(Date today,String departmentName,Integer departid,Integer pid,String huanjie){
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		List<CmsDepartment> departments = dao.changeRemindList(departmentName,departid,pid);
			if(departments.size()>0){
			for (int i = 0; i < departments.size(); i++) {
				if(departments.get(i).getSddspoChangelasttime()!=null){
					Long time = ((departments.get(i).getSddspoChangelasttime().getTime())-(today.getTime()));
					//departments.get(i).setOverplus(String.valueOf((time+86400000*2)/86400000));
					departments.get(i).setOverplus(String.valueOf((time/86400000)));
					if(huanjie!=null && huanjie.equals("1")){
						if((time/86400000)>=0){
							departments.get(i).setIsplus("p");	//正数
							if((time/86400000)>365){
								departments.get(i).setOverplus(">1年");
							}
							if(departments.get(i).getName().indexOf("工作指导组")<0){
								list.add(departments.get(i));
							}
						}
					}else if(huanjie!=null && huanjie.equals("2")){
						if(!((time/86400000)>=0)){
							departments.get(i).setOverplus(String.valueOf((time/86400000)-(2*(time/86400000))));
							departments.get(i).setIsplus("m");
							if((time/86400000)>365){
								departments.get(i).setOverplus(">1年");
							}
							if(departments.get(i).getName().indexOf("工作指导组")<0){
								list.add(departments.get(i));
							}
						}
					}else{
						if((time/86400000)>=0){
							departments.get(i).setIsplus("p");	//正数
						}else {
							departments.get(i).setOverplus(String.valueOf((time/86400000)-(2*(time/86400000))));
							departments.get(i).setIsplus("m");
						}
						if((time/86400000)>365){
							departments.get(i).setOverplus(">1年");
						}
						if(departments.get(i).getName().indexOf("工作指导组")<0){
							list.add(departments.get(i));
						}
					}
					
					
				}
			}
		}
		
		return list;
	}
	/**
     * 获取字典键值列表
     * @param corecdType
     * @return
     */
	@Transactional(readOnly = true)
    public List<CmsDepartment> getCorecdKeyList(String corecdType){
		List<CoreDictionary> corecdKeyList = coreDictionaryService.getCorecdKeyList(corecdType);
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		if(corecdKeyList.size()>0){
			for (int i = 0; i < corecdKeyList.size(); i++) {
				CmsDepartment model = new CmsDepartment();
				if(StringUtils.hasText((corecdKeyList.get(i).getCorecdKey()))){
					model.setKey(corecdKeyList.get(i).getCorecdKey());
				}
				if(StringUtils.hasText(corecdKeyList.get(i).getCorecdVal())){
					model.setValue(corecdKeyList.get(i).getCorecdVal());
				}
				list.add(model);
			}
		}
		return list;
	}

	/**
     * 根据父级id查询组织列表(判断删除)
     * true 底级组织，无子级节点
     * false 有子级节点
     */
	@Override
    public boolean findlistByPid(Integer pid){
    	boolean b = false;
    	//此方法存在问题 --> 如果某个工作指导组下只存在一个组织则删除存在问题
    	/*
    	List<Object []> list = dao.findlistByPid(pid);
    	if(list.size()<=1){
    		b = true;
    	}
    	*/
    	CmsDepartment depart = dao.findById(pid);
    	if(("支部".equals(depart.getSddspoOrgtype()))){
    		b = true;
    	}
    	return b;
    }
    /**
     * 导入组织小程序
     */
	public void impOrgToUser(){
		List<CmsDepartment> list = dao.findNotInUser();
		String sddspoLogincode = "";
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if (StringUtils.hasText(list.get(i).getSddspoLogincode())) {
					sddspoLogincode = list.get(i).getSddspoLogincode();
				}
				if(!("xxxxxxx".equals(list.get(i).getName().trim()))){
					cmsUserMng.registerMember(list.get(i).getName().trim(), null, "123456", "127.0.0.1", 1, 0, null, null,
					null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, 
					null, null, null, null, null, null, list.get(i).getId(), null, null, null,null, null, null, null,"2",
					list.get(i).getName(),sddspoLogincode  ,1,null,null,"委员",null,null,"1","1","1",list.get(i).getName().trim(),null,null,false, null, null,false,null);
				}
			System.out.println(" ID : " + list.get(i).getId());
			System.out.println(" name : " + list.get(i).getName().trim());
			System.out.println(" logincode : " + sddspoLogincode);
			System.out.println("已成功导入 ： " + (i+1) + "条数据!");
			}
		}
	}
	/**
     * 替换书记
     */
	@Override
    public CmsDepartment changeSecretaryByDid(String sddspoSecretary,String sddspoSecretaryidc,Integer sddspoSecretaryid,Integer departid,Date sddspoChangelasttime){
    	CmsDepartment model = new CmsDepartment();
//    	model.setSddspoSecretary(sddspoSecretary);
//    	model.setSddspoSecretaryid(sddspoSecretaryid);
//    	model.setSddspoSecretaryidc(sddspoSecretaryidc);
    	model.setId(departid);
    	model.setSddspoChangelasttime(sddspoChangelasttime);
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	try {
    		model.setSddspoChangecdate(sd.parse(sd.format(date)));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return dao.changeSecretaryByDid(model);
    }
	/**
     * 查看换届
     */
    public List<CmsDepartment> checkChangelist (String departName){
    	return dao.checkChangelist(departName);
    }
    public List<CmsDepartment> findNopidList(){
    	return getList(null,false);
    	//return dao.findNopidList();
    }
	public List<CmsDepartment> findZbList(Integer id,Integer pid){
		return dao.findZbList(id, pid);
	}
	
	private CmsDepartmentDao dao;
	
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private CmsGuestbookCtgMng guestBookCtgMng;
	@Autowired
	private ChannelDao channelDao;

	@Autowired
	public void setDao(CmsDepartmentDao dao) {
		this.dao = dao;
	}

	
	public List<CmsDepartment> baseScore(Integer parentId) {
		
		return dao.baseScore(parentId);
	}


	@Autowired
	public ICoreDictionaryService coreDictionaryService;
	@Autowired
	public CmsUserMng cmsUserMng;

	public List<CmsDepartment> getDeptByType(String type) {
		
		return dao.getDeptByType(type);
	}

	@Override
	public int getMaxPriority(Integer parentId) {
		
		return  dao.getMaxPriority(parentId);
	}
    public List<CmsDepartment> getDeptByTypeAndLoginId(String type,Integer loginId){
    	return dao.getDeptByTypeAndLoginId(type,loginId);
    }
    public CmsDepartment findByName(String name){
    	return dao.findByName(name);
    }

    public Map<String, String> statisticsDeptSum(Integer i, Integer deptid){
    	List<Object[]> obj = new ArrayList<Object[]>();
    	if(i == 1){
    		obj = dao.statisticsDeptSum(null);
    	}else {
    		obj = dao.statisticsDeptSum(deptid);
    	}
    	int deptsum = 0;
    	int zdzDept = 0;
    	int jgdwDept = 0;
    	int dzzDept = 0;
    	int zbDept = 0;
    	int deptsumJc = 0;
    	int zdzDeptJc = 0;
    	int jgdwDeptJc = 0;
    	int dzzDeptJc = 0;
    	int zbDeptJc = 0;
    	int deptsumX = 0;
    	int zdzDeptX = 0;
    	int jgdwDeptX = 0;
    	int dzzDeptX = 0;
    	int zbDeptX = 0;
    	int deptsumShi = 0;
    	int zdzDeptShi = 0;
    	int jgdwDeptShi = 0;
    	int dzzDeptShi = 0;
    	int zbDeptShi = 0;
    	int deptsumSe = 0;
    	int zdzDeptSe = 0;
    	int jgdwDeptSe = 0;
    	int dzzDeptSe = 0;
    	int zbDeptSe = 0;
    	int zdzDeptSe1 = 0;
    	int zdzDeptShi1 = 0;
    	int zdzDeptX1 = 0;
    	int zdzDeptJc1 = 0;
    	int lhdzzDept = 0;
    	int lhdzzDeptSe = 0;
    	int lhdzzDeptShi = 0;
    	int lhdzzDeptX = 0;
    	int lhdzzDeptJc = 0;
    	
    	if(obj != null && obj.size()>0){
    		for (Object[] objects : obj) {
    			int num = 0;
    			try{
    				num = Integer.parseInt(objects[0].toString());
    			}catch (Exception e) {
    				num = 0;
				}
    			if(!"工作指导组".equals(objects[1].toString())){
    				deptsum = deptsum + num;
    			}
    			  //1.区县2.基层3.市级4.省级
    			if(objects[1] != null){
        			if("工作指导组".equals(objects[1].toString())){
        				zdzDept = zdzDept + num;
        				if(objects[2] != null){
        					if("4".equals(objects[2].toString())){
        						zdzDeptSe1 = zdzDeptSe1 + num;
        						if(zdzDeptSe1==0){
        							zdzDeptSe1=1;
        						}
        					}else if("3".equals(objects[2].toString())){
        						zdzDeptShi1 = zdzDeptShi1 + num;
        					}else if("1".equals(objects[2].toString())){
        						zdzDeptX1 = zdzDeptX1 + num;
        					}else if("2".equals(objects[2].toString())){
        						zdzDeptJc1 = zdzDeptJc1 + num;
        					}
        				}
        			}else if("党总支".equals(objects[1].toString())){
        				dzzDept = dzzDept + num;
        				if(objects[2] != null){
        					if("4".equals(objects[2].toString())){
        						dzzDeptSe = dzzDeptSe + num;
        					}else if("3".equals(objects[2].toString())){
        						dzzDeptShi = dzzDeptShi + num;
        					}else if("1".equals(objects[2].toString())){
        						dzzDeptX = dzzDeptX + num;
        					}else if("2".equals(objects[2].toString())){
        						dzzDeptJc = dzzDeptJc + num;
        					}
        				}
        			}else if("机关党委".equals(objects[1].toString())){
        				jgdwDept = jgdwDept + num;
        				if(objects[2] != null){
        					if("4".equals(objects[2].toString())){
        						jgdwDeptSe = jgdwDeptSe + num;
        					}else if("3".equals(objects[2].toString())){
        						jgdwDeptShi = jgdwDeptShi + num;
        					}else if("1".equals(objects[2].toString())){
        						jgdwDeptX = jgdwDeptX + num;
        					}else if("2".equals(objects[2].toString())){
        						jgdwDeptJc = jgdwDeptJc + num;
        					}
        				}
        			}else if("支部".equals(objects[1].toString())){
        				zbDept = zbDept + num;
        				if(objects[2] != null){
        					if("4".equals(objects[2].toString())){
        						zbDeptSe = zbDeptSe + num;
        					}else if("3".equals(objects[2].toString())){
        						zbDeptShi = zbDeptShi + num;
        					}else if("1".equals(objects[2].toString())){
        						zbDeptX = zbDeptX + num;
        					}else if("2".equals(objects[2].toString())){
        						zbDeptJc = zbDeptJc + num;
        					}
        				}
        			}else if("联合党支部".equals(objects[1].toString())){
        				lhdzzDept = lhdzzDept +num;
        				if(objects[2]!=null){
        					if("4".equals(objects[2].toString())){
        						lhdzzDeptSe = lhdzzDeptSe + num;
        					}else if("3".equals(objects[2].toString())){
        						lhdzzDeptShi = lhdzzDeptShi + num;
        					}else if("1".equals(objects[2].toString())){
        						lhdzzDeptX = lhdzzDeptX + num;
        					}else if("2".equals(objects[2].toString())){
        						lhdzzDeptJc = lhdzzDeptJc + num;
        					}
        				}
        			}
    			}
			}
    	}
    	if(deptid==null){
    		deptid=1;
    	}
    	if(deptid==1){
    		zdzDeptSe1=1;
        	zdzDept=zdzDept+1;
    	}
    	deptsumJc = zbDeptJc + jgdwDeptJc + dzzDeptJc + zdzDeptJc+lhdzzDeptJc;
    	deptsumX = zbDeptX + jgdwDeptX + dzzDeptX + zdzDeptX+lhdzzDeptX;
    	deptsumShi = zbDeptShi + jgdwDeptShi + dzzDeptShi + zdzDeptShi+lhdzzDeptShi;
    	deptsumSe = zbDeptSe + jgdwDeptSe + dzzDeptShi + zdzDeptSe+lhdzzDeptSe;
    	Map<String,String> tjMap = new HashMap<String, String>();
    	tjMap.put("deptsum", deptsum + "");
    	tjMap.put("zdzDept", zdzDept + "");
    	tjMap.put("jgdwDept", jgdwDept + "");// 机关党委
    	tjMap.put("lhdzzDept", lhdzzDept+"");// 联合党支部竖列总和
    	tjMap.put("dzzDept", dzzDept + "");// 党总支
    	tjMap.put("zbDept", zbDept + ""); // 支部
    	tjMap.put("deptsumJc", deptsumJc + "");
    	tjMap.put("zdzDeptJc", zdzDeptJc1 + "");
    	tjMap.put("jgdwDeptJc", jgdwDeptJc + "");
    	tjMap.put("dzzDeptJc", dzzDeptJc + "");
    	tjMap.put("zbDeptJc", zbDeptJc + "");
    	tjMap.put("deptsumX", deptsumX + "");// 支部总和县
    	tjMap.put("zdzDeptX", zdzDeptX1 + "");
    	tjMap.put("jgdwDeptX", jgdwDeptX + "");// 机关党委总和  县
    	tjMap.put("dzzDeptX", dzzDeptX + "");// 党总支总和 县
    	tjMap.put("zbDeptX", zbDeptX + "");// 支部   县
    	tjMap.put("deptsumShi", deptsumShi + "");// 支部 总和 市
    	tjMap.put("zdzDeptShi", zdzDeptShi1 + "");// 
    	tjMap.put("jgdwDeptShi", jgdwDeptShi + ""); // 机关党委  市
    	tjMap.put("dzzDeptShi", dzzDeptShi + "");  // 党总支 市
    	tjMap.put("zbDeptShi", zbDeptShi + "");// 支部  市
    	tjMap.put("deptsumSe", deptsumSe + "");// 支部 总和  省
    	tjMap.put("zdzDeptSe", zdzDeptSe1 + "");// 指导组 
    	tjMap.put("jgdwDeptSe", jgdwDeptSe + "");// 机关党委  省
    	tjMap.put("dzzDeptSe", dzzDeptSe + "");// 党总支 省
    	tjMap.put("zbDeptSe", zbDeptSe + "");// 支部  省
    	tjMap.put("lhdzzDeptSe",lhdzzDeptSe+"");// 联合党支部   省
        tjMap.put("lhdzzDeptShi", lhdzzDeptShi+"");// 联合党支部  市
        tjMap.put("lhdzzDeptX", lhdzzDeptX+"");// 联合党支部  县
        tjMap.put("lhdzzDeptJc", lhdzzDeptJc+"");// 联合党支部 基层
        
    	
    	
    	return tjMap;
    }
    public Map<String, String> statisticsDeptQmhjSum(Integer i, Integer deptid, String sdate, String edate){
    	List<Object[]> obj = new ArrayList<Object[]>();
    	if(i == 1){
    		obj = dao.statisticsDeptQmhjSum(null, sdate, edate);
    	}else {
    		obj = dao.statisticsDeptQmhjSum(deptid, sdate, edate);
    	}
    	int rjqmsum = 0;
    	int rjqmZdz = 0;
    	int rjqmDzz = 0;
    	int rjqmJgdw = 0;
    	int rjqmZb = 0;
    	if(obj != null && obj.size()>0){
    		for (Object[] objects : obj) {
    			int num = 0;
    			try{
    				num = Integer.parseInt(objects[0].toString());
    			}catch (Exception e) {
    				num = 0;
				}
    			if(!"工作指导组".equals(objects[1])){
    				rjqmsum = rjqmsum + num;
    			}
    			if(objects[1] != null){
    				if("工作指导组".equals(objects[1])){
    					rjqmZdz = rjqmZdz + num;
    				}else if("党总支".equals(objects[1])){
    					rjqmDzz = rjqmDzz + num;
    				}else if("机关党委".equals(objects[1])){
    					rjqmJgdw = rjqmJgdw + num;
    				}else if("支部".equals(objects[1])){
    					rjqmZb = rjqmZb + num;
    				}
    			}
			}
    	}
    	Map<String, String> rjqmMap = new HashMap<String, String>();
    	rjqmMap.put("rjqmsum", rjqmsum + "");
    	rjqmMap.put("rjqmZdz", rjqmZdz + "");
    	rjqmMap.put("rjqmDzz", rjqmDzz + "");
    	rjqmMap.put("rjqmJgdw", rjqmJgdw + "");
    	rjqmMap.put("rjqmZb", rjqmZb + "");
    	return rjqmMap;
    }

    public Map<String, String> statisticsDeptYhjSum(Integer i, Integer deptid, String sdate, String edate){
    	List<Object[]> obj = new ArrayList<Object[]>();
    	if(i == 1){
    		obj = dao.statisticsDeptYhjSum(null, sdate, edate);
    	}else {
    		obj = dao.statisticsDeptYhjSum(deptid, sdate, edate);
    	}
    	int yhjsum = 0;
    	int yhjZdz = 0;
    	int yhjDzz = 0;
    	int yhjJgdw = 0;
    	int yhjZb = 0;
    	if(obj != null && obj.size()>0){
    		for (Object[] objects : obj) {
    			int num = 0;
    			try{
    				num = Integer.parseInt(objects[0].toString());
    			}catch (Exception e) {
    				num = 0;
				}
    			if(!"工作指导组".equals(objects[1])){
    				yhjsum = yhjsum + num;
				}
    			if(objects[1] != null){
    				/*
    				if("工作指导组".equals(objects[1])){
    					yhjZdz = yhjZdz + num;
    				}else if("党总支".equals(objects[1])){
    				*/
    				if("党总支".equals(objects[1])){
    					yhjDzz = yhjDzz + num;
    				}else if("机关党委".equals(objects[1])){
    					yhjJgdw = yhjJgdw + num;
    				}else if("支部".equals(objects[1])){
    					yhjZb = yhjZb + num;
    				}
    			}
			}
    	}
    	Map<String, String> yhjMap = new HashMap<String, String>();
    	yhjMap.put("yhjsum", yhjsum + "");
    	yhjMap.put("yhjZdz", yhjZdz + "");
    	yhjMap.put("yhjDzz", yhjDzz + "");
    	yhjMap.put("yhjJgdw", yhjJgdw + "");
    	yhjMap.put("yhjZb", yhjZb + "");
    	return yhjMap;
    }

    public List<CmsDepartment> findXtForJGDWByDeptId(Integer detpid){
    	if(detpid == null || detpid==0){
    		return null;
    	}
    	List<CmsDepartment> list = dao.findXtForJGDWByDeptId(detpid);
    	return list;
    }
    public List<CmsDepartment> findXtForJGDWByDeptIdse(Integer dds){
    	if(dds == null || dds==0){
    		return null;
    	}
    	List<CmsDepartment> list = dao.findXtForJGDWByDeptIdse(dds);
    	return list;
    }
    
   // public List<Integer> ids = new ArrayList<Integer>();
    public List<Integer> findDpetNameByLoginDept(Integer detpId,List<Integer> ids){
    	if(detpId == null || detpId<=0){
    		return null;
    	}
    	if (dao.findCountByPid(detpId)>0) {
			List<Object[]> list = dao.findDeptByPid(detpId);
			for (Object[] objects : list) {
				findDpetNameByLoginDept(Integer.valueOf(objects[0].toString()),ids);
			}
		}else{
			ids.add(detpId);
		}
    	return ids;
    }

	@Override
	public List<CmsDepartment> getAllDeptByIdAndName(String deptId,
			String deptName) {
		// TODO Auto-generated method stub
		return dao.getAllDeptByIdAndName(deptId,deptName);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CmsDepartment> getAllJgdwDept() {
		//bean.name like '%市局机关党委%' and bean.priority = 1 and bean.id !=1"
		List<CmsDepartment> resList = new ArrayList<CmsDepartment>();
		for (CmsDepartment dept : DepartmentCache.departentList) {
			if (StringUtils.hasText(dept.getName()) && dept.getName().contains("市局机关党委") && dept.getPriority() != null && dept.getPriority().intValue() == 1 && dept.getId().intValue() != 1) {
				resList.add(dept);
			}
		}
		return resList;
//		return dao.getAllJgdwDept();
	}

	@Override
	public List<CmsDepartment> getAllJgdwDeptById(Integer deptId,boolean isShiju) {
		// TODO Auto-generated method stub
		return dao.getAllJgdwDeptById(deptId,isShiju);
	}

	@Override
	public List<CmsDepartment> getAllZhiBuById(Integer deptId) {
		// TODO Auto-generated method stub
		return dao.getAllZhiBuById(deptId);
	}

	@Override
	public Map<String, String> statisticsDeptYqhjSum(Integer i, Integer deptid,
			String nowTime) {
		// TODO Auto-generated method stub
		List<Object[]> obj = new ArrayList<Object[]>();
    	if(i == 1){
    		obj = dao.statisticsDeptYqhjSum(null, nowTime);
    	}else {
    		obj = dao.statisticsDeptYqhjSum(deptid, nowTime);
    	}
    	int deptYqhjSum = 0;
    	int jgdwYqhjDept = 0;
    	int dzzYqhjDept = 0;
    	int zbYqhjDept = 0;
    	
    	if(obj != null && obj.size()>0){
    		for (Object[] objects : obj) {
    			int num = 0;
    			try{
    				num = Integer.parseInt(objects[0].toString());
    			}catch (Exception e) {
    				num = 0;
				}
    			if(!"工作指导组".equals(objects[1].toString())){
    				deptYqhjSum = deptYqhjSum + num;
    			}
    			  //1.区县2.基层3.市级4.省级
    			if(objects[1] != null){
    				if("党总支".equals(objects[1].toString())){
        				dzzYqhjDept = dzzYqhjDept + num;
        			}else if("机关党委".equals(objects[1].toString())){
        				jgdwYqhjDept = jgdwYqhjDept + num;
        			}else if("支部".equals(objects[1].toString())){
        				zbYqhjDept = zbYqhjDept + num;
        			}
    			}
			}
    	}
    	Map<String,String> tjMap = new HashMap<String, String>();
    	tjMap.put("deptYqhjSum", deptYqhjSum + "");
    	tjMap.put("jgdwYqhjDept", jgdwYqhjDept + "");
    	tjMap.put("dzzYqhjDept", dzzYqhjDept + "");
    	tjMap.put("zbYqhjDept", zbYqhjDept + "");
    	return tjMap;
	}

	@Override
	public Map<String, String> statisticsDeptAqhjSum(Integer i, Integer deptid,
			String nowTime) {
		// TODO Auto-generated method stub
		List<Object[]> obj = new ArrayList<Object[]>();
    	if(i == 1){
    		obj = dao.statisticsDeptAqhjSum(null, nowTime);
    	}else {
    		obj = dao.statisticsDeptAqhjSum(deptid, nowTime);
    	}
    	int deptAqhjSum = 0;
    	int jgdwAqhjDept = 0;
    	int zbAqhjDept = 0;
    	int dzzAqhjDept = 0;
    	if(obj != null && obj.size()>0){
    		for (Object[] objects : obj) {
    			int num = 0;
    			try{
    				num = Integer.parseInt(objects[0].toString());
    			}catch (Exception e) {
    				num = 0;
				}
    			if(!"工作指导组".equals(objects[1].toString())){
    				deptAqhjSum = deptAqhjSum + num;
    			}
    			if(objects[1] != null){
    				if("党总支".equals(objects[1].toString())){
        				dzzAqhjDept = dzzAqhjDept + num;
        			}else if("机关党委".equals(objects[1].toString())){
        				jgdwAqhjDept = jgdwAqhjDept + num;
        			}else if("支部".equals(objects[1].toString())){
        				zbAqhjDept = zbAqhjDept + num;
        			}
    			}
			}
    	}
    	Map<String,String> tjMap = new HashMap<String, String>();
    	tjMap.put("deptAqhjSum", deptAqhjSum + "");
    	tjMap.put("dzzAqhjDept", dzzAqhjDept + "");
    	tjMap.put("jgdwAqhjDept", jgdwAqhjDept + "");
    	tjMap.put("zbAqhjDept", zbAqhjDept + "");
    	return tjMap;
	}

	@Override
	public List<CmsDepartment> getAllDeptById(Integer deptId, boolean isShiju) {
		// TODO Auto-generated method stub
		return dao.getAllDeptById(deptId,isShiju);
	}

	@Override
	public String getTwoDeptsLevel(Integer departId, Integer targetDeptId) {
		// TODO Auto-generated method stub
		Channel chanelDepart = channelDao.getChannelPathByDids(String.valueOf(departId)).get(0);
		Channel chanelTarget = channelDao.getChannelPathByDids(String.valueOf(targetDeptId)).get(0);
		if(chanelDepart.getSite().getId().equals(chanelTarget.getSite().getId())){
			CmsDepartment depart = dao.findById(departId);
			CmsDepartment target = dao.findById(targetDeptId);
			if(depart == target.getParent()){
				return "same";
			}else if(depart.getParent() == target){
				return "same";
			}else if(depart.getParent() == target.getParent()){
				return "same";
			}else{
				boolean exists = dao.oneIsInDepts(departId, targetDeptId);
				boolean existsReverse = dao.oneIsInDepts( targetDeptId,departId);
				String lastResult = "Up";
				if(exists){
					lastResult = "Down";
				}
				if(!exists && !existsReverse){
					lastResult = "SameLevel";
				}
				return lastResult;
			}
		}else{
			return "noRelative";  //没有关系
		}
	}

	@Override
	public String getSsxByDepartId(Integer departId) {
		// TODO Auto-generated method stub
		String inType = "";
		CmsDepartment d = dao.findById(departId);
		if(d.getId()==1){
			inType = "sheng";
		}else if(d.getParent().getId()==1){
			inType = "sheng";
		}else if(d.getParent().getName().endsWith("市局机关党委")){
			inType = "shi";
		}else if(d.getParent().getParent()==null){
			inType = "shi";
		}else{
			inType = "xian";
		}
		return inType;
	}
	
	public CmsDepartment findByJgdw(Integer pid){
		return dao.findByJgdw(pid);
	};
	public CmsDepartment findByPid(Integer pid){
		return dao.findByPid(pid);
	}
}