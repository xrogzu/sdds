package com.jeecms.cms.cache;

import java.util.ArrayList;
import java.util.List;

import com.jeecms.core.entity.CmsDepartment;

public class DepartmentCache {
	public static List<CmsDepartment> departentList = new ArrayList<CmsDepartment>();
	
	public void refreshDepartentList(List<CmsDepartment> list){
		long start = System.currentTimeMillis();
		departentList.clear();
		departentList.addAll(list);
		System.out.println("refresh departent list costs:" + (System.currentTimeMillis() - start) + "ms");
	}
}
