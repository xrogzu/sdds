package com.risen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsUser;
import com.risen.dao.CmsFloatingDao;
import com.risen.entity.CmsFloating;
import com.risen.service.CmsFloatingMng;

@Service
@Transactional
public class CmsFloatingMngImpl implements CmsFloatingMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public CmsFloating findById(Integer id) {
		CmsFloating entity = dao.findById(id);
		return entity;
	}

	public CmsFloating save(CmsFloating bean) {
		dao.save(bean);
		return bean;
	}

	public CmsFloating update(CmsFloating bean) {
		Updater<CmsFloating> updater = new Updater<CmsFloating>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsFloating deleteById(Integer id) {
		CmsFloating bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsFloating[] deleteByIds(Integer[] ids) {
		CmsFloating[] beans = new CmsFloating[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsFloatingDao dao;
	private CmsDepartmentDao depdao;

	@Autowired
	public void setDao(CmsFloatingDao dao) {
		this.dao = dao;
	}

	public CmsDepartmentDao getDepdao() {
		return depdao;
	}

	public void setDepdao(CmsDepartmentDao depdao) {
		this.depdao = depdao;
	}

	//@SuppressWarnings("unused")
	@Override
	public void outAndaddWithUser(CmsUser user,boolean isOutCity,CmsDepartment olddepart,CmsDepartment newdepart,String sddscpChanges,String zclx) {
		
		CmsFloating floatingzc = new CmsFloating();
		CmsFloating floatingzr = new CmsFloating();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		String[] yearMonthDate = date.split("-");
		if("2".equals(sddscpChanges)){//转入本系统
			System.out.println("get支部:"+user.getSddscpZb());
					if(user.getSddscpZb()!=null){
						// 如果该帐号的组织等级是4，即省局
						if(newdepart.getSddspoOrglevel().intValue()==4){
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("0");
							floatingzr.setSddsfiInprovince("0");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}else if(newdepart.getSddspoOrglevel().intValue()==3){// 如果该帐号的组织等级是3，即市局
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("0");
							floatingzr.setSddsfiInprovince("1");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}else{
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("1");
							floatingzr.setSddsfiInprovince("1");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}
					}else if(user.getSddscpDzz()!=null && user.getSddscpZb()==null){
						//转入数据开始
						floatingzr.setSddsfiUserid(user.getId());
						floatingzr.setSddsfiUsername(user.getUsername());
						floatingzr.setSddsfiOrgid(newdepart.getId());
						floatingzr.setSddsfiIncity("1");
						floatingzr.setSddsfiIncounty("1");
						floatingzr.setSddsfiInprovince("1");
						floatingzr.setSddsfiYear(yearMonthDate[0]);
						floatingzr.setSddsfiMonth(yearMonthDate[1]);
						floatingzr.setSddsfiDay(yearMonthDate[2]);
						floatingzr.setSddsfiOutprovince("0");
						floatingzr.setSddsfiOutcity("0");
						floatingzr.setSddsfiOutcounty("0");
						floatingzr.setSddsfiOrgname(newdepart.getName());
						 dao.save(floatingzr);
						//转入数据结束
					}else if(user.getSddscpJgdw()!=null && user.getSddscpDzz()==null && user.getSddscpZb()==null){
						if(newdepart.getSddspoOrglevel().intValue()==4){
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("0");
							floatingzr.setSddsfiIncounty("0");
							floatingzr.setSddsfiInprovince("1");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}else if(newdepart.getSddspoOrglevel().intValue()==3){
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("0");
							floatingzr.setSddsfiInprovince("1");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}else{
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("1");
							floatingzr.setSddsfiInprovince("1");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							 dao.save(floatingzr);
							//转入数据结束
						}
					}
		}else {//系统内部流转
		    if(olddepart!=null && newdepart!=null &&  olddepart.getSddspoRelations().equals(newdepart.getSddspoRelations()) && sddscpChanges!="3"){//本市区县转入
			    if(newdepart.getSddspoOrglevel().intValue()==3 && !(olddepart.getSddspoOrglevel().intValue()==4)){//本区县转本市局 市局人数不变
				//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("0");
					floatingzc.setSddsfiOutcounty("1");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					//转出数据结束
					dao.save(floatingzc);
			  }else if(newdepart.getSddspoOrglevel().intValue()==3){//转入省局
				 if(olddepart.getSddspoOrglevel().intValue()==3){//济南市局转入省局
					//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("1");
					floatingzc.setSddsfiOutcounty("0");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					 dao.save(floatingzc);
					//转出数据结束
				}else if(!(olddepart.getSddspoOrglevel().intValue()==4)){//济南区县局 及支部转入省局
					//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("1");
					floatingzc.setSddsfiOutcounty("1");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					 dao.save(floatingzc);
					//转出数据结束
				}
			}else if(olddepart.getSddspoOrglevel().intValue()==4){//省局转入济南
				if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==3){//省局转出至济南市局单位
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("0");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					 dao.save(floatingzr);
					//转入数据结束
				}else{//省局转出至济南县局
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("1");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					dao.save(floatingzr);
					//转入数据结束
				}
			}else{//本区县转本市其他区县 市局人数不变
			//转出数据开始
			floatingzc.setSddsfiUserid(user.getId());
			floatingzc.setSddsfiUsername(user.getUsername());
			floatingzc.setSddsfiOrgid(olddepart.getId());
			floatingzc.setSddsfiIncity("0");
			floatingzc.setSddsfiIncounty("0");
			floatingzc.setSddsfiInprovince("0");
			floatingzc.setSddsfiYear(yearMonthDate[0]);
			floatingzc.setSddsfiMonth(yearMonthDate[1]);
			floatingzc.setSddsfiDay(yearMonthDate[2]);
			floatingzc.setSddsfiOutprovince("0");
			floatingzc.setSddsfiOutcity("0");
			floatingzc.setSddsfiOutcounty("1");
			floatingzc.setSddsfiOrgname(olddepart.getName());
			dao.save(floatingzc);
			//转出数据结束
		//---------------------------------------------------------------------------
			//转入数据开始
			floatingzr.setSddsfiUserid(user.getId());
			floatingzr.setSddsfiUsername(user.getUsername());
			floatingzr.setSddsfiOrgid(newdepart.getId());
			floatingzr.setSddsfiIncity("0");
			floatingzr.setSddsfiIncounty("1");
			floatingzr.setSddsfiInprovince("0");
			floatingzr.setSddsfiYear(yearMonthDate[0]);
			floatingzr.setSddsfiMonth(yearMonthDate[1]);
			floatingzr.setSddsfiDay(yearMonthDate[2]);
			floatingzr.setSddsfiOutprovince("0");
			floatingzr.setSddsfiOutcity("0");
			floatingzr.setSddsfiOutcounty("0");
			floatingzr.setSddsfiOrgname(newdepart.getName());
			dao.save(floatingzr);
			//转入数据结束
			}
		}else{
			if(olddepart!=null && olddepart.getSddspoOrglevel().intValue()==4){//省局党员转除济南其他市局 转入转出
				if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==3){//省局转市局机关
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("0");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					dao.save(floatingzr);
					//转入数据结束
				}else if(newdepart!=null){//省局转入各地市的区县级及以下单位
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("1");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					dao.save(floatingzr);
					//转入数据结束
				}else{
					if(sddscpChanges!=null && "5".indexOf(sddscpChanges)>-1){
						if(olddepart.getSddspoOrglevel().intValue()==4){
							//转出数据开始
							floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("1");
							floatingzc.setSddsfiOutcity("0");
							floatingzc.setSddsfiOutcounty("0");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
							return;
							}else if(olddepart.getSddspoOrglevel().intValue()==3){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
								return;
							}else{
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("1");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
								return;
							}
						}
					if(sddscpChanges!=null && "7".indexOf(sddscpChanges)>-1){
						if(olddepart.getSddspoOrglevel().intValue()==4){
							//转出数据开始
							floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("1");
							floatingzc.setSddsfiOutcity("0");
							floatingzc.setSddsfiOutcounty("0");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
							}else if(olddepart.getSddspoOrglevel().intValue()==3){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
							}else{
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("1");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
							}
						}
					if(sddscpChanges!=null && "9".indexOf(sddscpChanges)>-1){
						if(olddepart.getSddspoOrglevel().intValue()==4){
							//转出数据开始
							floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("1");
							floatingzc.setSddsfiOutcity("0");
							floatingzc.setSddsfiOutcounty("0");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
							}else if(olddepart.getSddspoOrglevel().intValue()==3){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
							}else{
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("1");
								floatingzc.setSddsfiOutcounty("1");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
							}
						}
				}
			}else if(olddepart!=null && olddepart.getSddspoOrglevel().intValue()==3){//各市之间的党员转入转出 与转入省局
				if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==4){//市局转入省局
					//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("1");
					floatingzc.setSddsfiOutcounty("0");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					dao.save(floatingzc);
					//转出数据end
				}else if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==3){//市局转入市局
					//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("1");
					floatingzc.setSddsfiOutcounty("0");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					dao.save(floatingzc);
					//转出数据end
					//---------------------------------------------------------------------------
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("0");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					dao.save(floatingzr);
					//转入数据结束
					}else if(newdepart!=null){//市局转入县局
					//转出数据开始
					floatingzc.setSddsfiUserid(user.getId());
					floatingzc.setSddsfiUsername(user.getUsername());
					floatingzc.setSddsfiOrgid(olddepart.getId());
					floatingzc.setSddsfiIncity("0");
					floatingzc.setSddsfiIncounty("0");
					floatingzc.setSddsfiInprovince("0");
					floatingzc.setSddsfiYear(yearMonthDate[0]);
					floatingzc.setSddsfiMonth(yearMonthDate[1]);
					floatingzc.setSddsfiDay(yearMonthDate[2]);
					floatingzc.setSddsfiOutprovince("0");
					floatingzc.setSddsfiOutcity("1");
					floatingzc.setSddsfiOutcounty("0");
					floatingzc.setSddsfiOrgname(olddepart.getName());
					dao.save(floatingzc);
					//转出数据end
					//---------------------------------------------------------------------------
					//转入数据开始
					floatingzr.setSddsfiUserid(user.getId());
					floatingzr.setSddsfiUsername(user.getUsername());
					floatingzr.setSddsfiOrgid(newdepart.getId());
					floatingzr.setSddsfiIncity("1");
					floatingzr.setSddsfiIncounty("1");
					floatingzr.setSddsfiInprovince("0");
					floatingzr.setSddsfiYear(yearMonthDate[0]);
					floatingzr.setSddsfiMonth(yearMonthDate[1]);
					floatingzr.setSddsfiDay(yearMonthDate[2]);
					floatingzr.setSddsfiOutprovince("0");
					floatingzr.setSddsfiOutcity("0");
					floatingzr.setSddsfiOutcounty("0");
					floatingzr.setSddsfiOrgname(newdepart.getName());
					dao.save(floatingzr);
					//转入数据结束
					}else{
						if(sddscpChanges!=null && "5".indexOf(sddscpChanges)>-1){
							if(olddepart.getSddspoOrglevel().intValue()==4){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("0");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
								return;
								}else if(olddepart.getSddspoOrglevel().intValue()==3){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									return;
								}else{
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("1");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									return;
								}
							}else
						if(sddscpChanges!=null && "7".indexOf(sddscpChanges)>-1){
							if(olddepart.getSddspoOrglevel().intValue()==4){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("0");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
								}else if(olddepart.getSddspoOrglevel().intValue()==3){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
								}else{
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("1");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
								}
							}else
						if(sddscpChanges!=null && "9".indexOf(sddscpChanges)>-1){
							if(olddepart.getSddspoOrglevel().intValue()==4){
								//转出数据开始
								floatingzc.setSddsfiUserid(user.getId());
								floatingzc.setSddsfiUsername(user.getUsername());
								floatingzc.setSddsfiOrgid(olddepart.getId());
								floatingzc.setSddsfiIncity("0");
								floatingzc.setSddsfiIncounty("0");
								floatingzc.setSddsfiInprovince("0");
								floatingzc.setSddsfiYear(yearMonthDate[0]);
								floatingzc.setSddsfiMonth(yearMonthDate[1]);
								floatingzc.setSddsfiDay(yearMonthDate[2]);
								floatingzc.setSddsfiOutprovince("1");
								floatingzc.setSddsfiOutcity("0");
								floatingzc.setSddsfiOutcounty("0");
								floatingzc.setSddsfiOrgname(olddepart.getName());
								dao.save(floatingzc);
								//转出数据end
								}else if(olddepart.getSddspoOrglevel().intValue()==3){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
								}else{
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("1");
									floatingzc.setSddsfiOutcounty("1");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
								}
							}else{
								if(olddepart.getSddspoOrglevel().intValue()==4){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("0");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									}else if(olddepart.getSddspoOrglevel().intValue()==3){
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("0");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}else{
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("1");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}
							}
					}
				}else{
					if(olddepart!=null){//区县级转入外市级、省局、县级
						if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==4){//本县转入省局
							//转出数据开始
							floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("0");
							floatingzc.setSddsfiOutcity("1");
							floatingzc.setSddsfiOutcounty("1");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
						}else if(newdepart!=null && newdepart.getSddspoOrglevel().intValue()==3){
							//转出数据开始
							floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("0");
							floatingzc.setSddsfiOutcity("1");
							floatingzc.setSddsfiOutcounty("1");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
							//---------------------------------------------------------------------------
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("0");
							floatingzr.setSddsfiInprovince("0");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							dao.save(floatingzr);
							//转入数据结束
						}else{
							if(sddscpChanges!=null && "5".indexOf(sddscpChanges)>-1){
								if(olddepart.getSddspoOrglevel().intValue()==4){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("0");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									return;
									}else if(olddepart.getSddspoOrglevel().intValue()==3){
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("0");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
										return;
									}else{
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("1");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
										return;
									}
								}else
							if(sddscpChanges!=null && "7".indexOf(sddscpChanges)>-1){
								if(olddepart.getSddspoOrglevel().intValue()==4){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("0");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									}else if(olddepart.getSddspoOrglevel().intValue()==3){
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("0");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}else{
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("1");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}
								}else
							if(sddscpChanges!=null && "9".indexOf(sddscpChanges)>-1){
								if(olddepart.getSddspoOrglevel().intValue()==4){
									//转出数据开始
									floatingzc.setSddsfiUserid(user.getId());
									floatingzc.setSddsfiUsername(user.getUsername());
									floatingzc.setSddsfiOrgid(olddepart.getId());
									floatingzc.setSddsfiIncity("0");
									floatingzc.setSddsfiIncounty("0");
									floatingzc.setSddsfiInprovince("0");
									floatingzc.setSddsfiYear(yearMonthDate[0]);
									floatingzc.setSddsfiMonth(yearMonthDate[1]);
									floatingzc.setSddsfiDay(yearMonthDate[2]);
									floatingzc.setSddsfiOutprovince("1");
									floatingzc.setSddsfiOutcity("0");
									floatingzc.setSddsfiOutcounty("0");
									floatingzc.setSddsfiOrgname(olddepart.getName());
									dao.save(floatingzc);
									//转出数据end
									}else if(olddepart.getSddspoOrglevel().intValue()==3){
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("0");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}else{
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("1");
										floatingzc.setSddsfiOutcounty("1");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
									}
								}else{
									if(olddepart.getSddspoOrglevel().intValue()==4){
										//转出数据开始
										floatingzc.setSddsfiUserid(user.getId());
										floatingzc.setSddsfiUsername(user.getUsername());
										floatingzc.setSddsfiOrgid(olddepart.getId());
										floatingzc.setSddsfiIncity("0");
										floatingzc.setSddsfiIncounty("0");
										floatingzc.setSddsfiInprovince("0");
										floatingzc.setSddsfiYear(yearMonthDate[0]);
										floatingzc.setSddsfiMonth(yearMonthDate[1]);
										floatingzc.setSddsfiDay(yearMonthDate[2]);
										floatingzc.setSddsfiOutprovince("1");
										floatingzc.setSddsfiOutcity("0");
										floatingzc.setSddsfiOutcounty("0");
										floatingzc.setSddsfiOrgname(olddepart.getName());
										dao.save(floatingzc);
										//转出数据end
										}else if(olddepart.getSddspoOrglevel().intValue()==3){
											//转出数据开始
											floatingzc.setSddsfiUserid(user.getId());
											floatingzc.setSddsfiUsername(user.getUsername());
											floatingzc.setSddsfiOrgid(olddepart.getId());
											floatingzc.setSddsfiIncity("0");
											floatingzc.setSddsfiIncounty("0");
											floatingzc.setSddsfiInprovince("0");
											floatingzc.setSddsfiYear(yearMonthDate[0]);
											floatingzc.setSddsfiMonth(yearMonthDate[1]);
											floatingzc.setSddsfiDay(yearMonthDate[2]);
											floatingzc.setSddsfiOutprovince("1");
											floatingzc.setSddsfiOutcity("1");
											floatingzc.setSddsfiOutcounty("0");
											floatingzc.setSddsfiOrgname(olddepart.getName());
											dao.save(floatingzc);
											//转出数据end
										}else{
											//转出数据开始
											floatingzc.setSddsfiUserid(user.getId());
											floatingzc.setSddsfiUsername(user.getUsername());
											floatingzc.setSddsfiOrgid(olddepart.getId());
											floatingzc.setSddsfiIncity("0");
											floatingzc.setSddsfiIncounty("0");
											floatingzc.setSddsfiInprovince("0");
											floatingzc.setSddsfiYear(yearMonthDate[0]);
											floatingzc.setSddsfiMonth(yearMonthDate[1]);
											floatingzc.setSddsfiDay(yearMonthDate[2]);
											floatingzc.setSddsfiOutprovince("1");
											floatingzc.setSddsfiOutcity("1");
											floatingzc.setSddsfiOutcounty("1");
											floatingzc.setSddsfiOrgname(olddepart.getName());
											dao.save(floatingzc);
											//转出数据end
										}
								}
							//转出数据开始
					/*		floatingzc.setSddsfiUserid(user.getId());
							floatingzc.setSddsfiUsername(user.getUsername());
							floatingzc.setSddsfiOrgid(olddepart.getId());
							floatingzc.setSddsfiIncity("0");
							floatingzc.setSddsfiIncounty("0");
							floatingzc.setSddsfiInprovince("0");
							floatingzc.setSddsfiYear(yearMonthDate[0]);
							floatingzc.setSddsfiMonth(yearMonthDate[1]);
							floatingzc.setSddsfiDay(yearMonthDate[2]);
							floatingzc.setSddsfiOutprovince("0");
							floatingzc.setSddsfiOutcity("1");
							floatingzc.setSddsfiOutcounty("1");
							floatingzc.setSddsfiOrgname(olddepart.getName());
							dao.save(floatingzc);
							//转出数据end
							//---------------------------------------------------------------------------
							//转入数据开始
							floatingzr.setSddsfiUserid(user.getId());
							floatingzr.setSddsfiUsername(user.getUsername());
							floatingzr.setSddsfiOrgid(newdepart.getId());
							floatingzr.setSddsfiIncity("1");
							floatingzr.setSddsfiIncounty("1");
							floatingzr.setSddsfiInprovince("0");
							floatingzr.setSddsfiYear(yearMonthDate[0]);
							floatingzr.setSddsfiMonth(yearMonthDate[1]);
							floatingzr.setSddsfiDay(yearMonthDate[2]);
							floatingzr.setSddsfiOutprovince("0");
							floatingzr.setSddsfiOutcity("0");
							floatingzr.setSddsfiOutcounty("0");
							floatingzr.setSddsfiOrgname(newdepart.getName());
							dao.save(floatingzr);
							//转入数据结束
							return;*/
							}
						}
					}
				}
			}
		}
		
	

	@Override
	public List<CmsFloating> getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return dao.getByUserId(userId);
	}
	@Override
	public void updateWithUser(Integer userId,String changeType,CmsDepartment depart) {
		// TODO Auto-generated method stub
		List<CmsFloating> floatings = dao.getByUserId(userId);
		if(floatings!=null){
			if(floatings.size()==1){
				if("2,3,4".indexOf(changeType)>-1){
					dao.updateFloating(userId,changeType,null);
				}
			}else{
				for (int i = 0; i < floatings.size(); i++) {
					CmsFloating floats = floatings.get(i);
					String inParty = (floats.getSddsfiIncity().equals("1")) ? "1" : "0";
					inParty += ((floats.getSddsfiIncounty().equals("1")) ? "1" : "0");
					inParty += ((floats.getSddsfiInprovince().equals("1")) ? "1" : "0");
					String outParty = (floats.getSddsfiOutcity().equals("1")) ? "1" : "0";
					outParty += ((floats.getSddsfiOutcounty().equals("1")) ? "1" : "0");
					outParty += ((floats.getSddsfiOutprovince().equals("1")) ? "1" : "0");
					if(inParty.indexOf("1")>-1){
						Integer inPartyWay = new Integer(Integer.parseInt(changeType)-4);
						dao.updateFloating(userId,String.valueOf(inPartyWay),depart);
					}
					if(outParty.indexOf("1")>-1){
						dao.updateFloating(userId,changeType,depart);
					}
				}
			}
		}
	}

	@Override
	public void saveOutWithUser(CmsUser user, String type,String outType) {
		// TODO Auto-generated method stub
		CmsFloating floating = new CmsFloating();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		String[] yearMonthDate = date.split("-");
		floating.setSddsfiUserid(user.getId());
		floating.setSddsfiUsername(user.getUsername());
		floating.setSddsfiOrgid(user.getDepartment().getId());
		floating.setSddsfiOrgname(user.getDepartment().getName());
		floating.setSddsfiIncity(("shi".equals(outType))?"1":"0");
		floating.setSddsfiIncounty(("xian".equals(outType))?"1":"0");
		floating.setSddsfiInprovince("0");
		floating.setSddsfiYear(yearMonthDate[0]);
		floating.setSddsfiMonth(yearMonthDate[1]);
		floating.setSddsfiDay(yearMonthDate[2]);
		floating.setSddsfiOutcity(("shi".equals(type))?"1":"0");
		floating.setSddsfiOutcounty(("xian".equals(type))?"1":"0");
		floating.setSddsfiOutprovince("0");
		floating.setSddsfiOrgname(user.getDepartment().getName());
		dao.save(floating);
	}
}