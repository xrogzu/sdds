package com.jeecms.cms.staticpage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentAttachment;
import com.jeecms.cms.entity.main.ContentDoc;
import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.cms.entity.main.ContentShareCheck;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.ContentShareCheckMng;
import com.jeecms.common.office.FileUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.entity.RisenIntegralRecord;
import com.risen.service.IRisenIntegralRecordService;

import freemarker.template.TemplateException;

@Controller
public class StaticAct {
	private static final Logger log = LoggerFactory.getLogger(StaticAct.class);

	@RequiresPermissions("static:v_index")
	@RequestMapping(value = "/static/v_index.do")
	public String indexInput(HttpServletRequest request, ModelMap model) {
		return "static/index";
	}
	
	@RequiresPermissions("static:o_sendOut")
	@RequestMapping(value = "/static/o_sendOut.do")
	public void sendToOutWebSite(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String sendContentIds) throws JSONException {
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			if (!StringUtils.isBlank(sendContentIds)) {
				String[] contentIds = sendContentIds.split(",");
				//获取系统
				String systemName = System.getProperty("os.name");
				if (systemName != null) {
					systemName = systemName.toLowerCase();  
				}
				//获取文件真实路径
				String root = getClass().getResource("/").getPath().replace("/WEB-INF/classes/", "");
				if(systemName.indexOf("window")>-1){
					root = root.substring(1, root.length());
				}
				//判断目录是否存在
				File file = new File(root,"sendToOut");
				if(!file.exists()){
					file.mkdirs();
				}
				String year = sdf.format(new Date()).substring(0,4);
				String month = sdf.format(new Date()).substring(5,7);
				String date = sdf.format(new Date()).substring(8,10);
				root += (File.separator+"sendToOut");
				File yearFile = new File(root,year);
				if(!yearFile.exists()){
					yearFile.mkdirs();
				}
				root += (File.separator+year);
				File monthFile = new File(root,month);
				if(!monthFile.exists()){
					monthFile.mkdirs();
				}
				root += (File.separator+month);
				File dateFile = new File(root,date);
				if(!dateFile.exists()){
					dateFile.mkdirs();
				}
				root += (File.separator+date);
				//创建输出后的文件
				String pfilePath = root ;
				//设置文件名
				Date nowDate = new Date();
				long nowSecond = nowDate.getTime();
				String fileName = nowSecond+".xml";
				File outFile = new File(pfilePath+File.separator+fileName);
				//1.创建document对象，代表整个xml文档
		        Document document = DocumentHelper.createDocument();
		        //2.创建根节点list
		        Element lists = document.addElement("lists");
		        //String wantToAdd = "";
				//向文件里写入内容
				for (int i = 0; i < contentIds.length; i++) {
					Integer contentId = new Integer(contentIds[i]);
					Content content = contentMng.findById(contentId);
					Channel channel = content.getChannel();
					if(content.getShared()){
						Integer checkId = null;
						List<RisenIntegralRecord> records = recordServices.findByContentId(contentId);
						for (RisenIntegralRecord risenIntegralRecord : records) {
							if(risenIntegralRecord.getRisenirTargetorgid().equals(1)){
								checkId = risenIntegralRecord.getRisenirSharecheck();
							}
						}
						if(checkId==null){
							json.put("success", false);
							json.put("msg", "找不到文章编号为" + contentId + "的共享信息");
							ResponseUtils.renderJson(response, json.toString());
							return;
						}else{
							ContentShareCheck check = checkMng.findById(checkId);
							channel = channelMng.findById(check.getChannel().getId());
						}
					}
					Integer addType = getType(channel);
					String title = content.getTitle();
					String addUser = content.getAuthor();
					String contents = content.getTxt();
					if(StringUtils.isBlank(addUser)){
						addUser = "admin";
					}
					if (content.getIsSend() == 1) {
						json.put("success", false);
						json.put("msg", "已推送编号为" + contentId + "的文章");
						ResponseUtils.renderJson(response, json.toString());
						return;
					} else {
						content.setIsSend(new Integer(1));
						 //4.生成子节点及节点内容
						byte[] bytes = Base64.encodeBase64(contents.getBytes("utf-8"));
						String jiamiContent = new String(bytes);
						/*
						String addContents = "<list><title>"+title+"</title>";
						addContents +=("<content>"+jiamiContent+"</content>");
						addContents +=("<user>"+addUser+"</user>");
						addContents +=("<date>"+sdf.format(new Date())+"</date>");
						addContents +=("<type>"+addType+"</type>");
						addContents +=("<tag>0</tag></list>");
						wantToAdd += addContents;
						*/
						Element list = lists.addElement("list");
						Element listTitle = list.addElement("title");
						listTitle.setText(title);
				        Element listContent = list.addElement("content");
				        listContent.setText(jiamiContent);
				        Element listAddUser = list.addElement("user");
				        listAddUser.setText(addUser);
				        Element listAddDate = list.addElement("date");
				        listAddDate.setText(sdf.format(new Date()));
				        Element listAddType = list.addElement("type");
				        listAddType.setText(String.valueOf(addType));
				        Element listTag = list.addElement("tag");
				        listTag.setText("0");
				        List<ContentAttachment> attachments = content.getAttachments();
				        if((attachments!=null) && (attachments.size()>0)){
				        	Element listAttachments = list.addElement("attachments");
				        	for (ContentAttachment contentAttachment : attachments) {
				        		Element listAttachment = listAttachments.addElement("attachment");
				        		listAttachment.setText(contentAttachment.getPath());
							}
				        }
				        contentMng.updateContentInfo(content);
					}
				}
				//lists.setText(wantToAdd);
				//5.设置生成xml的格式
		        OutputFormat format = OutputFormat.createPrettyPrint();
		        format.setEncoding("UTF-8");
		        //6.生成xml文件
		        XMLWriter writer = new XMLWriter(new FileOutputStream(outFile), format);
		        //设置是否转义，默认是true，代表转义
		        writer.setEscapeText(false);
		        writer.write(document);
		        writer.close();
				json.put("success", true);
				json.put("msg", "推送成功");
				ResponseUtils.renderJson(response, json.toString());
			} else {
				json.put("success", false);
				json.put("msg", "没有选择文章");
				ResponseUtils.renderJson(response, json.toString());
			}
		}catch(IOException e){
			log.error("产生IO异常了", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
		}catch(Exception e){
			log.error("产生异常了", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
		}
	}

	@RequiresPermissions("static:o_index")
	@RequestMapping(value = "/static/o_index.do")
	public void indexSubmit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			boolean staticRequired=true;
			CmsSite site = CmsUtils.getSite(request);
			if(!site.getStaticIndex()){
				staticRequired=false;
				ResponseUtils.renderJson(response, "{\"success\":false,\"no\":true}");
			}
			if(staticRequired){
				staticPageSvc.index(site);
				ResponseUtils.renderJson(response, "{\"success\":true}");
			}
		} catch (IOException e) {
			log.error("static index error!", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
		} catch (TemplateException e) {
			log.error("static index error!", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
		}
	}

	@RequiresPermissions("static:o_index_remove")
	@RequestMapping(value = "/static/o_index_remove.do")
	public void indexRemove(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		JSONObject json = new JSONObject();
		if (staticPageSvc.deleteIndex(site)) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequiresPermissions("static:v_channel")
	@RequestMapping(value = "/static/v_channel.do")
	public String channelInput(HttpServletRequest request, ModelMap model) {
		// 栏目列表
		CmsSite site = CmsUtils.getSite(request);
		List<Channel> topList = channelMng.getTopList(site.getId(), false);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				null, false);
		model.addAttribute("channelList", channelList);
		return "static/channel";
	}

	@RequiresPermissions("static:o_channel")
	@RequestMapping(value = "/static/o_channel.do")
	public void channelSubmit(Integer channelId, Boolean containChild,
			HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		JSONObject json = new JSONObject();
		if (containChild == null) {
			containChild = true;
		}
		try {
			boolean staticRequired=true;
			if(channelId!=null){
				Channel c=channelMng.findById(channelId);
				if(c!=null&&(!StringUtils.isBlank(c.getLink()) || !c.getStaticChannel())){
					staticRequired=false;
					json.put("success", false);
					json.put("no", true);
				}
			}
			if(staticRequired){
				int count = staticPageSvc.channel(site.getId(), channelId,
						containChild);
				json.put("success", true);
				json.put("count", count);
			}
		} catch (IOException e) {
			log.error("static channel error!", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
		} catch (TemplateException e) {
			log.error("static channel error!", e);
			json.put("success", false);
			json.put("msg", e.getMessage());
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequiresPermissions("static:v_content")
	@RequestMapping(value = "/static/v_content.do")
	public String contentInput(HttpServletRequest request, ModelMap model) {
		// 栏目列表
		CmsSite site = CmsUtils.getSite(request);
		List<Channel> topList = channelMng.getTopList(site.getId(), true);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				null, true);
		model.addAttribute("channelList", channelList);
		return "static/content";
	}

	@RequiresPermissions("static:o_content")
	@RequestMapping(value = "/static/o_content.do")
	public void contentSubmit(Integer channelId, Date startDate,
			HttpServletRequest request, HttpServletResponse response) {
		String msg="";
		try {
			Integer siteId = null;
			boolean staticRequired=true;
			if (channelId == null) {
				// 没有指定栏目，则需指定站点
				siteId = CmsUtils.getSiteId(request);
			}else{
				Channel c=channelMng.findById(channelId);
				if(c!=null&&(!StringUtils.isBlank(c.getLink()) || !c.getStaticContent())){
					staticRequired=false;
					msg = "{\"success\":false,\"no\":true}";
				}
			}
			if(staticRequired){
				int count = staticPageSvc.content(siteId, channelId, startDate);
				msg = "{\"success\":true,\"count\":" + count + "}";
			}
		} catch (IOException e) {
			log.error("static channel error!", e);
			e.printStackTrace();
			msg = "{\"success\":false,\"msg\":'" + e.getMessage() + "'}";
		} catch (TemplateException e) {
			log.error("static channel error!", e);
			e.printStackTrace();
			msg = "{\"success\":false,\"msg\":'" + e.getMessage() + "'}";
		}
		ResponseUtils.renderJson(response, msg);
	}

	@RequiresPermissions("static:v_reset_generate")
	@RequestMapping(value = "/static/v_reset_generate.do")
	public String resetgenerateInput(HttpServletRequest request, ModelMap model) {
		// 栏目列表
		CmsSite site = CmsUtils.getSite(request);
		List<Channel> topList = channelMng.getTopList(site.getId(), false);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				null, false);
		model.addAttribute("channelList", channelList);
		return "static/resetgenerate";
	}

	@RequiresPermissions("static:o_reset_generate")
	@RequestMapping("/static/o_reset_generate.do")
	public void resetGenerate(Integer channelId, HttpServletRequest request,
			HttpServletResponse response){
		String msg;
		List<Content> contents;
		if (channelId == null) {
			Integer siteId = CmsUtils.getSiteId(request);
			Integer[] siteIds = new Integer[] { siteId };
			contents = contentMng.getListBySiteIdsForTag(siteIds, null, null,
					null, null, ContentDoc.ALL,null, 1, 0, Integer.MAX_VALUE);
		} else {
			Channel c = channelMng.findById(channelId);
			if (c == null) {
				msg = "{\"success\":false,\"msg\":'" + "channel is not find "
						+ "'}";
			}
			Integer[] cIds = new Integer[] { channelId };
			contents=contentMng.getListByChannelIdsForTag(cIds, null, null, null, null, ContentDoc.ALL, null, 1, 1, 0, null);
		}
		if (contents != null && contents.size() > 0) {
			for (Content content : contents) {
				content.getContentExt().setNeedRegenerate(true);
				contentMng.update(content);
			}
		}
		msg = "{\"success\":true,\"count\":" + contents.size() + "}";
		ResponseUtils.renderJson(response, msg);
	}
	
	public Integer getType(Channel channel){
		Integer addType = channel.getId();
		String channelName = channel.getName();
		Channel pChannel = channel.getParent();
		if(pChannel!=null && pChannel.getId().equals(290)){
			addType = 1144;
		}
		if("媒体视点".equals(channelName)){
			addType = 1257;
		}
		if("经验交流".equals(channelName)){
			addType = 1206;
		}
		if("理论研讨".equals(channelName)){
			addType = 1258;
		}
		if(pChannel!=null && pChannel.getId().equals(609)){
			addType = 1265;
		}
		if("党建主体责任".equals(channelName)){
			addType = 1259;
		}
		if("党风廉政建设".equals(channelName)){
			addType = 1260;
		}
		if("规范化党支部建设".equals(channelName)){
			addType = 1262;
		}
		if(pChannel!=null && pChannel.getId().equals(600)){
			addType = 1261;
		}
		if("创先争优".equals(channelName)){
			addType = 1153;
		}
		if("税徽闪耀党旗红".equals(channelName)){
			addType = 1154;
		}
		if("先进基层党组织".equals(channelName)){
			addType = 1155;
		}
		if("回眸先锋".equals(channelName)){
			addType = 1156;
		}
		if("优秀党员".equals(channelName)){
			addType = 1158;
		}
		if("地税先锋".equals(channelName)){
			addType = 1157;
		}
		if("网上荣誉室".equals(channelName)){
			addType = 1159;
		}
		return addType;
	}

	@Autowired
	private StaticPageSvc staticPageSvc;
	@Autowired
	private ChannelMng channelMng;
	@Autowired
	private ContentMng contentMng;
	@Autowired
	private IRisenIntegralRecordService recordServices;
	@Autowired
	private ContentShareCheckMng checkMng;
}
