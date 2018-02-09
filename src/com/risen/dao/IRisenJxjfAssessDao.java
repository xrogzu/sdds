package com.risen.dao;

import java.util.List;
import com.risen.entity.RisenJxjfAssess;

public interface IRisenJxjfAssessDao {
	public List<RisenJxjfAssess> findbydepartpg(Integer departid);
	public RisenJxjfAssess findbyid(Integer id);
	public String save(RisenJxjfAssess jsxp);
	public String update(RisenJxjfAssess jsxp);
	public String findbychannelname(Integer departid,String channelname);
}
