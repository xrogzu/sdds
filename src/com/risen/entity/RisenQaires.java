package com.risen.entity;

import java.sql.Timestamp;
import java.util.Date;

public class RisenQaires {
	private Integer id;
	private String title;
	private String description;
	private Date startTime;
	private Date endTime;
	private Integer totalCount;
	private Boolean disabled;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public int getStart() {
		Date now = new Timestamp(System.currentTimeMillis());
		Date startTime=getStartTime();
		Date endTime=getEndTime();
		if (startTime!=null) {
			if(startTime.getTime()>now.getTime())
				return 0;
			else {
				if(endTime!=null){
					//已结束
					if(endTime.getTime()< now.getTime())
						return 2;
				}
				return 1;
			}
		} else {
			if(endTime!=null){
				//已结束
				if(endTime.getTime()< now.getTime())
					return 2;
			}
			return 1;
		}
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
