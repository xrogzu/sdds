package com.jeecms.core.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

import com.jeecms.core.entity.CmsDepartment;

public class DeptComparator implements Comparator<CmsDepartment>{

	@Override
	public int compare(CmsDepartment o1, CmsDepartment o2) {
		// TODO Auto-generated method stub
		double deptScore = 0;
		double compareDeptScore = 0;
		deptScore = o1.getScore()==null?0:o1.getScore().doubleValue();
		compareDeptScore = o2.getScore()==null?0:o2.getScore().doubleValue();
		//用于精准计算采用 BigDecimal类
		BigDecimal b1 = new BigDecimal(deptScore);
		BigDecimal b2 = new BigDecimal(compareDeptScore);
		double distance = b2.subtract(b1).doubleValue();
		return (int)distance;
	}
	
}
