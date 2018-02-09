package com.risen.entity;

import java.util.Comparator;

public class RisenTargetCompare implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		RisenTarget model = (RisenTarget)o1;
		RisenTarget compareModel = (RisenTarget)o2;
		if((model.getRisentgStatus()==null) && (compareModel.getRisentgStatus()==null)){
			return model.getId().compareTo(compareModel.getId());
		}else if((model.getRisentgStatus()==null) && (compareModel.getRisentgStatus()!=null)){
			return 1;
		}else if((model.getRisentgStatus()!=null) && (compareModel.getRisentgStatus()==null)){
			return -1;
		}else{
			return model.getId().compareTo(compareModel.getId());
		}
	}

}
