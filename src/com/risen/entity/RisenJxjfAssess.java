package com.risen.entity;

public class RisenJxjfAssess {
	private Integer id;//编号
	private Integer departid;//组织id
	private String channelname;//栏目名称
	private Double passingscore;//合格分数
	private Double excellentscore;//优秀分数
	private String passingevaluate;//合格评价
	private String excellentevaluate;//优秀评价
	private String disqualificationevalua;//不合格评价
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDepartid() {
		return departid;
	}
	public void setDepartid(Integer departid) {
		this.departid = departid;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public Double getPassingscore() {
		return passingscore;
	}
	public void setPassingscore(Double passingscore) {
		this.passingscore = passingscore;
	}
	public Double getExcellentscore() {
		return excellentscore;
	}
	public void setExcellentscore(Double excellentscore) {
		this.excellentscore = excellentscore;
	}
	public String getPassingevaluate() {
		return passingevaluate;
	}
	public void setPassingevaluate(String passingevaluate) {
		this.passingevaluate = passingevaluate;
	}
	public String getExcellentevaluate() {
		return excellentevaluate;
	}
	public void setExcellentevaluate(String excellentevaluate) {
		this.excellentevaluate = excellentevaluate;
	}
	public String getDisqualificationevalua() {
		return disqualificationevalua;
	}
	public void setDisqualificationevalua(String disqualificationevalua) {
		this.disqualificationevalua = disqualificationevalua;
	}
	
}
