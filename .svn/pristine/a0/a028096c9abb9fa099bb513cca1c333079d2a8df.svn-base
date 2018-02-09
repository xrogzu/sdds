package com.risen.action;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.action.admin.main.CmsMemberAct;
import com.jeecms.cms.manager.assist.CmsFileMng;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.Ftp;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.DbFileMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.risen.action.font.OrgUtil;
import com.risen.entity.RisenChangeremindrecord;
import com.risen.service.IRisenChangeremindrecordService;

@Controller
public class RisenChangeremindrecordAct {
	private static final Logger log = LoggerFactory.getLogger(RisenTargetDetailAct.class);
	/**
	 * 确认换届
	 * 提交记录
	 */
	@RequestMapping("/changeremind/o_confirm.do")
	@ResponseBody
	public Object oconfirm(String sddsccOrgname,Integer sddsccOrgid,String sddsccOsecretaryname,Integer sddsccOsecretaryid,
			String sddsccOsecretaryidc,String sddsccNsecretaryname,Integer sddsccNsecretaryid,String sddsccNsecretaryidc,String sddsccRemark,Date sddspoChangelasttime,String sddsccFile){
		risenChangeremindrecordService.save(sddsccOrgname, sddsccOrgid, sddsccOsecretaryname, 
				sddsccOsecretaryid, sddsccOsecretaryidc, sddsccNsecretaryname, sddsccNsecretaryid, sddsccNsecretaryidc, sddsccRemark,sddsccFile);	//插入换届记录
		departmentMng.changeSecretaryByDid(sddsccNsecretaryname, sddsccNsecretaryidc, sddsccNsecretaryid, sddsccOrgid, sddspoChangelasttime);	//更新department
//		CmsUser modelCmsUser = cmsUserMng.changeSecretaryByUid(sddsccOsecretaryid, sddsccNsecretaryid);
		return 1;
	}
	//上传
	@RequiresPermissions("changeremind:delete")
	@RequestMapping("/changeremind/delete.do")
	public String delete(Integer id,HttpServletRequest request, ModelMap model){
		//删除之前先获取组织Name
		String orgName = risenChangeremindrecordService.getOrgNameById(id);
		Integer departId = departmentMng.findByName(orgName).getId();
		risenChangeremindrecordService.delete(id);
		return "redirect:../checkChangelist/checkChangelist.do?departId="+departId;
	} 
	// 上传
	@RequiresPermissions("changeremind:o_upload_doc")
	@RequestMapping("/changeremind/o_upload_doc.do")
	public String uploadDoc(
			@RequestParam(value = "docFile", required = false) MultipartFile file,
			String filename, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		String origName = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		WebErrors errors = validateUpload(file, request);
		if (errors.hasErrors()) {
			model.addAttribute("error", errors.getErrors().get(0));
			return "changeremind/wenku_iframe";
		}
		// TODO 检查允许上传的后缀
		try {
			String fileUrl;
			if (site.getConfig().getUploadToDb()) {
				String dbFilePath = site.getConfig().getDbFileUri();
				if (!StringUtils.isBlank(filename)
						&& FilenameUtils.getExtension(filename).equals(ext)) {
					filename = filename.substring(dbFilePath.length());
					fileUrl = dbFileMng.storeByFilename(filename,
							file.getInputStream());
				} else {
					fileUrl = dbFileMng.storeByExt(site.getUploadPath(), ext,
							file.getInputStream());
					// 加上访问地址
					fileUrl = request.getContextPath() + dbFilePath + fileUrl;
				}
			} else if (site.getUploadFtp() != null) {
				Ftp ftp = site.getUploadFtp();
				String ftpUrl = ftp.getUrl();
				if (!StringUtils.isBlank(filename)
						&& FilenameUtils.getExtension(filename).equals(ext)) {
					filename = filename.substring(ftpUrl.length());
					fileUrl = ftp.storeByFilename(filename,
							file.getInputStream());
				} else {
					fileUrl = ftp.storeByExt(site.getUploadPath(), ext,
							file.getInputStream());
					// 加上url前缀
					fileUrl = ftpUrl + fileUrl;
				}
			} else {
				String ctx = request.getContextPath();
				if (!StringUtils.isBlank(filename)
						&& FilenameUtils.getExtension(filename).equals(ext)) {
					filename = filename.substring(ctx.length());
					fileUrl = fileRepository.storeByFilename(filename, file);
				} else {
					fileUrl = fileRepository.storeByExt(site.getUploadPath(),
							ext, file);
				}
				// 加上部署路径
				fileUrl = ctx + fileUrl;
			}
			cmsUserMng.updateUploadSize(user.getId(),
					Integer.parseInt(String.valueOf(file.getSize() / 1024)));
			if (fileMng.findByPath(fileUrl) != null) {
				fileMng.saveFileByPath(fileUrl, origName, false);
			}
			model.addAttribute("docPath", fileUrl);
			model.addAttribute("docExt", ext);
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		}
		return "changeremind/wenku_iframe";
	}

	private WebErrors validateUpload(MultipartFile file,
			HttpServletRequest request) {
		String origName = file.getOriginalFilename();
		CmsUser user = CmsUtils.getUser(request);
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		int fileSize = (int) (file.getSize() / 1024);
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(file, "file")) {
			return errors;
		}
		if (origName != null
				&& (origName.contains("/") || origName.contains("\\") || origName
						.indexOf("\0") != -1)) {
			errors.addErrorCode("upload.error.filename", origName);
		}
		// 非允许的后缀
		if (!user.isAllowSuffix(ext)) {
			errors.addErrorCode("upload.error.invalidsuffix", ext);
			return errors;
		}
		// 超过附件大小限制
		if (!user.isAllowMaxFile((int) (file.getSize() / 1024))) {
			errors.addErrorCode("upload.error.toolarge", origName, user
					.getGroup().getAllowMaxFile());
			return errors;
		}
		// 超过每日上传限制
		if (!user.isAllowPerDay(fileSize)) {
			long laveSize = user.getGroup().getAllowPerDay()
					- user.getUploadSize();
			if (laveSize < 0) {
				laveSize = 0;
			}
			errors.addErrorCode("upload.error.dailylimit", laveSize);
		}
		return errors;
	}

	@RequestMapping("/getFilePath.do")
	@ResponseBody
	public void getFilePath(HttpServletResponse response, Integer orgid) {
		String filePath = risenChangeremindrecordService.getFilePath(orgid);
		//OrgUtil util = new OrgUtil();
		//util.download(filePath, response);
		try {
			response.getWriter().write(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	private IRisenChangeremindrecordService risenChangeremindrecordService;
	@Autowired
	private CmsDepartmentMng departmentMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsFileMng fileMng;
	@Autowired
	private DbFileMng dbFileMng;
	@Autowired
	private FileRepository fileRepository;
}
