package com.risen.entity;


public class RisenVoteItem{

	private Integer id;
	private String itemTitle;
	private String itemImg;
	private String itemDes;
	private Integer itemCount;
	private Integer itemState;
	private RisenVote vote;
	private RisenVoteQues ques;
	
	public RisenVoteQues getQues() {
		return ques;
	}

	public void setQues(RisenVoteQues ques) {
		this.ques = ques;
	}

	public RisenVote getVote() {
		return vote;
	}

	public void setVote(RisenVote vote) {
		this.vote = vote;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}

	public String getItemDes() {
		return itemDes;
	}

	public void setItemDes(String itemDes) {
		this.itemDes = itemDes;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getItemState() {
		return itemState;
	}

	public void setItemState(Integer itemState) {
		this.itemState = itemState;
	}

	public Integer getId() {
		return id;
	}
	
	
}
