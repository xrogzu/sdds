package com.risen.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.IRisenSignforDao;
import com.risen.entity.RisenSignfor;
import com.risen.service.IRisenSignforService;

@Service
@Transactional
public class RisenSignforService implements IRisenSignforService {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenSignfor findById(Integer id) {
		RisenSignfor entity = dao.findById(id);
		return entity;
	}

	public RisenSignfor save(RisenSignfor bean) {
		dao.save(bean);
		return bean;
	}

	public RisenSignfor update(RisenSignfor bean) {
		Updater<RisenSignfor> updater = new Updater<RisenSignfor>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenSignfor deleteById(Integer id) {
		RisenSignfor bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenSignfor[] deleteByIds(Integer[] ids) {
		RisenSignfor[] beans = new RisenSignfor[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public Boolean savaForIpAndContent(String ip, String contentid){
		int i=0;
		List<CmsDepartment> list = cmsDepartmentDao.findListByIp(ip);
		if(list!=null && list.size()>0){
			for (CmsDepartment cmsDepartment : list) {
				int deptid = list.get(i).getId();
				Boolean isPresence  =dao.findModelBy(deptid, contentid);
				if(isPresence){
					continue;
				}
				RisenSignfor model = new RisenSignfor();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				model.setRisensfDept(cmsDepartment);
				Content content = new Content();
				content.setId(Integer.parseInt(contentid));
				model.setRisensfContent(content);
				model.setRisensfIssign("1");
				model.setRisensfIp(ip);
				model.setRisensfDate(format.format(new Date()));
				try {
					dao.save(model);
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return i>0?true:false;
	}
	
	public Boolean checkIsSign(String contentid,String ip){
		List<CmsDepartment> list = cmsDepartmentDao.findListByIp(ip);
		boolean isSign = false;
		if(list!=null && list.size()>0){
			int deptid = list.get(0).getId();
			isSign  =dao.findModelBy(deptid, contentid);
		}
		return isSign;
	}
	
	@Override
	public List<RisenSignfor> getAllInfoByContentId(String contentid) {
		// TODO Auto-generated method stub
		return dao.findModelByContentId(contentid);
	}
	
	@Autowired
	private CmsDepartmentDao cmsDepartmentDao;
	private IRisenSignforDao dao;

	@Autowired
	public void setDao(IRisenSignforDao dao) {
		this.dao = dao;
	}

}