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

import com.risen.entity.RisenVoteQues;

public class OrgUtilRecord {
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
	
	
	public String getString(Object object){
		if(object==null){
			return "";
		}
		return object.toString();
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public List<String> getHead(List<RisenVoteQues> quesList){
		List<String> list=new ArrayList<String>();
		list.add("序号");
		list.add("投票IP");
		list.add("投票时间");
		list.add("手机号码");
		list.add("网友留言");
		for (RisenVoteQues ques:quesList) {
			list.add(ques.getQuesTitle());
			list.add("意见建议");
		}
		return list;
	}
}
