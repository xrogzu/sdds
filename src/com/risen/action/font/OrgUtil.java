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

public class OrgUtil {
private DateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void download(String path, HttpServletResponse response) {
        try {
        	String root = getClass().getResource("/").getPath().replace("/WEB-INF/classes/", "");
			String systemName = System.getProperty("os.name");
	        if (systemName != null) {
	        	systemName = systemName.toLowerCase();  
	        }
	        String fileRealPath = root.substring(1)+path;
            // path是指欲下载的文件的路径。
            File file = new File(fileRealPath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(fileRealPath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+filename);
            response.addHeader("Content-Length", "" + fileRealPath.length());
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
	public List<List<String>> getExcelList(List<Object> eList){
		List<List<String>> list=new ArrayList<List<String>>();
		List<String> head=getHead();
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
			l.add(getString(objects[5]));
			l.add(getString(objects[6]));
			l.add(getString(objects[7]));
			l.add(getString(objects[8]));	
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
	private List<String> getHead(){
		List<String> list=new ArrayList<String>();
		list.add("序号");
		list.add("姓名");
		list.add("身份证");
		list.add("电话");
		list.add("考试时间");
		list.add("答题数");
		list.add("正确题数");
		list.add("错误题数");
		list.add("未答题数");
		list.add("分数");
		return list;
	}
}
