package com.jeecms.cms.manager.assist.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.ContentFrontDao;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.assist.ContentFrontMng;

@Service
@Transactional
public class ContentFrontMngImpl implements ContentFrontMng {

	@Override
	public List<Content> getListByName(String name,Integer pageNo,Integer siteId) {
		// TODO Auto-generated method stub
		List<Content> contents = frontDao.getListByName(name,siteId);
		int totalSize = contents.size();//总条数
		int pageSize = 18;   //每页展示数量
		boolean exactly = (totalSize % pageSize) == 0; //是否有多余
		int totalPage = totalSize / pageSize;//总页数
		totalPage = exactly ? totalPage : (totalPage+1);
		//获取开始位置
		int startPosition = (pageNo.intValue()-1)*pageSize;
		//获取结束位置
		int endPosition = (pageNo.intValue())*pageSize;
		//最后一页的结束位置是否大于总条数
		int maxPosition = (pageNo.intValue())*pageSize;
		if(maxPosition>=totalSize){
			endPosition = totalSize;
		}
		List<Content> returnContents = new ArrayList<Content>();
		for (int i = startPosition; i < endPosition; i++) {
			returnContents.add(contents.get(i));
		}
		return returnContents;
	}
	@Autowired
	private ContentFrontDao frontDao;
}
