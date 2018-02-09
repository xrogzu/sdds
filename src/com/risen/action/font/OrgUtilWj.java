package com.risen.action.font;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.risen.entity.RisenQairesTopic;


public class OrgUtilWj {
private DateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+filename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	/**
	 * 申贷
	 * @param eList
	 * @return
	 */
	public List<List<String>> getExcelList(List<Object> eList,List<RisenQairesTopic> topicList){
		List<List<String>> list=new ArrayList<List<String>>();
		List<String> head=getHead(topicList);
		list.add(head);
		for(int i=0;i<eList.size();i++){
			Object object=eList.get(i);
			Object[] objects=(Object[])object;
			List<String> l=new ArrayList<String>();
			
			l.add((i+1)+"");
			l.add(getString(objects[0]));
			l.add(getString(objects[1]));
			l.add(getString(objects[2]));
			l.add(getString(objects[3]));
			l.add(getString(objects[4]));
			for (int j=0;j<topicList.size();j++) {
				l.add(getString(objects[j+5]));
			}
			list.add(l);
		}
		return list;
	}
	
	private String getString(Object object){
		if(object==null){
			return "";
		}
		return object.toString();
	}
	
	
	
	/**
	 * 申贷
	 * @return
	 */
	private List<String> getHead(List<RisenQairesTopic> topicList){
		List<String> list=new ArrayList<String>();
		list.add("序号");
		list.add("编号");
		list.add("姓名");
		list.add("身份证");
		list.add("电话");
		list.add("问卷时间");
		for (RisenQairesTopic topic:topicList) {
		  list.add(topic.getTitle());
		}
		return list;
	}
}
