package com.jeecms.cms.entity.assist;

import java.util.Date;

public class CmsAppealMailReplay {
	private static final long serialVersionUID = 2L;
	
	private Integer replayId;
	private Integer appealId;
	private String username;
	private String replay;
	private Date cdate;
	
	public Integer getReplayId() {
		return replayId;
	}
	public void setReplayId(Integer replayId) {
		this.replayId = replayId;
	}
	public Integer getAppealId() {
		return appealId;
	}
	public void setAppealId(Integer appealId) {
		this.appealId = appealId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	
}
