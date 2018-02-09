package com.risen.entity;

import java.util.Set;


public class RisenQairesTopic {
	private Integer id;
	private RisenQaires qaires;
	private String title;
	private String description;
	private Integer multiSelect;
	private Integer totalCount;
	private String answer;
	private Integer type;
	private Integer score;
	private Integer torderBy;
	private Set<RisenQairesItem> items;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RisenQaires getQaires() {
		return qaires;
	}
	public void setQaires(RisenQaires qaires) {
		this.qaires = qaires;
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
	public Integer getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(Integer multiSelect) {
		this.multiSelect = multiSelect;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTorderBy() {
		return torderBy;
	}
	public void setTorderBy(Integer torderBy) {
		this.torderBy = torderBy;
	}
	public Set<RisenQairesItem> getItems() {
		return items;
	}
	public void setItems(Set<RisenQairesItem> items) {
		this.items = items;
	}
	
}
