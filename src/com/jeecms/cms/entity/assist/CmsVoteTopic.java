package com.jeecms.cms.entity.assist;

import java.util.HashSet;
import java.util.Set;

import com.jeecms.cms.entity.assist.base.BaseCmsVoteTopic;

public class CmsVoteTopic extends BaseCmsVoteTopic {
	private static final long serialVersionUID = 1L;

	private Integer departId;
	
	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public void init() {
		if (getTotalCount() == null) {
			setTotalCount(0);
		}
		if (getMultiSelect() == null) {
			setMultiSelect(1);
		}
		if (getDef() == null) {
			setDef(false);
		}
		if (getDisabled() == null) {
			setDisabled(false);
		}
		if (getRestrictMember() == null) {
			setRestrictMember(false);
		}
		if (getRestrictIp() == null) {
			setRestrictIp(false);
		}
		if (getRestrictCookie() == null) {
			setRestrictCookie(true);
		}
		if (getDepartId() == null) {
			setDepartId(1);
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsVoteTopic () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsVoteTopic (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsVoteTopic (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		java.lang.String title,
		java.lang.Integer totalCount,
		java.lang.Integer multiSelect,
		java.lang.Boolean restrictMember,
		java.lang.Boolean restrictIp,
		java.lang.Boolean restrictCookie,
		java.lang.Boolean disabled,
		java.lang.Boolean def,
		java.lang.Integer departId) {

		super (
			id,
			site,
			title,
			totalCount,
			multiSelect,
			restrictMember,
			restrictIp,
			restrictCookie,
			disabled,
			def);
		this.departId = departId;
	}
	public void addToSubTopics(CmsVoteSubTopic subTopic){
		Set<CmsVoteSubTopic>subTopics=getSubtopics();
		if(subTopics==null){
			subTopics=new HashSet<CmsVoteSubTopic>();
			setSubtopics(subTopics);
		}
		subTopics.add(subTopic);
	}

	/* [CONSTRUCTOR MARKER END] */

}