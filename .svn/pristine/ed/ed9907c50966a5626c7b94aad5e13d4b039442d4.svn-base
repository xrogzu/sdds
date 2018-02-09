package com.risen.service;

import java.util.List;

import com.jeecms.core.entity.CmsDepartment;
import com.risen.entity.RisenChangeremindrecord;

public interface IRisenChangeremindrecordService {

	public RisenChangeremindrecord save(String sddsccOrgname,Integer sddsccOrgid,String sddsccOsecretaryname,Integer sddsccOsecretaryid,
			String sddsccOsecretaryidc,String sddsccNsecretaryname,Integer sddsccNsecretaryid,String sddsccNsecretaryidc,String sddsccRemark,String sddsccFile);
	public String getFilePath(Integer orgid);
	
	//删除操作记录
	public void delete(Integer id);
	//通过Id得到被操作的组织名称
	String getOrgNameById(Integer id);
	
}
