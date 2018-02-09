package com.jeecms.core.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate4.PriorityInterface;
import com.jeecms.core.entity.base.BaseCmsDepartment;
import com.risen.entity.RisenScore;



public class CmsDepartment extends BaseCmsDepartment  implements PriorityInterface {
	private static final long serialVersionUID = 1L;
	
	private Set<CmsBallot> ballot;
	private Set<RisenScore> scores;
	//临时字段
	private Double tpxw;//系统栏目 ,图片新闻
	private Double djdt;//系统栏目 党建动态
	private Double jyjl;//系统栏目 经验交流
	private Double mtxc;// 系统栏目 媒体视点
	private Double llyt;//系统栏目 理论研讨
	private Double wsrys;//系统栏目 网上荣誉室
	
    private Double zbdt;//机关栏目，支部动态
    public String getJxpg() {
		return jxpg;
	}

	public void setJxpg(String jxpg) {
		this.jxpg = jxpg;
	}

	private Double dfjl;//机关栏目，党费缴纳
    private Double dwgk;//机关栏目，党务公开
    private Double tsgz;//机关栏目，特色工作
    private String jxpg;//绩效评估
	



	public Double getZbdt() {
		return zbdt;
	}

	public void setZbdt(Double zbdt) {
		this.zbdt = zbdt;
	}

	public Double getDfjl() {
		return dfjl;
	}

	public void setDfjl(Double dfjl) {
		this.dfjl = dfjl;
	}

	public Double getDwgk() {
		return dwgk;
	}

	public void setDwgk(Double dwgk) {
		this.dwgk = dwgk;
	}

	public Double getTsgz() {
		return tsgz;
	}

	public void setTsgz(Double tsgz) {
		this.tsgz = tsgz;
	}

	public Double getLlyt() {
		return llyt;
	}

	public void setLlyt(Double llyt) {
		this.llyt = llyt;
	}

	public Double getWsrys() {
		return wsrys;
	}

	public void setWsrys(Double wsrys) {
		this.wsrys = wsrys;
	}

	public Double getTpxw() {
		return tpxw;
	}

	public void setTpxw(Double tpxw) {
		this.tpxw = tpxw;
	}

	public Double getDjdt() {
		return djdt;
	}

	public void setDjdt(Double djdt) {
		this.djdt = djdt;
	}

	public Double getJyjl() {
		return jyjl;
	}

	public void setJyjl(Double jyjl) {
		this.jyjl = jyjl;
	}

	public Double getMtxc() {
		return mtxc;
	}

	public void setMtxc(Double mtxc) {
		this.mtxc = mtxc;
	}

	public Set<RisenScore> getScores() {
		return scores;
	}

	public void setScores(Set<RisenScore> scores) {
		this.scores = scores;
	}

	public Set<CmsBallot> getBallot() {
		return ballot;
	}

	public void setBallot(Set<CmsBallot> ballot) {
		this.ballot = ballot;
	}
	
	/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsDepartment () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsDepartment (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsDepartment (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Integer weights) {

		super (
			id,
			name,
			priority,
			weights);
	}

/*[CONSTRUCTOR MARKER END]*/

	/**
	 * 获得深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		CmsDepartment parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}
	
	public  static List<CmsDepartment> getListForSelect(List<CmsDepartment> topList) {
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		for (CmsDepartment c : topList) {
			addChildToList(list, c);
		}
		return list;
	}
	
	/**
	 * 递归将子部门加入列表
	 * 
	 * @param list
	 *            部门容器
	 * @param depart
	 *            待添加的部门，且递归添加子部门
	 */
	private static void addChildToList(List<CmsDepartment> list, CmsDepartment depart) {
		list.add(depart);
		Set<CmsDepartment> child = depart.getChild();
		for (CmsDepartment c : child) {
			addChildToList(list, c);
		}
	}
	
	public Integer[] getChannelIds() {
		Set<Channel> channels = getChannels();
		return Channel.fetchIds(channels);
	}
	public Integer[] getGuestBookCtgIds() {
		Set<CmsGuestbookCtg> channels = getGuestBookCtgs();
		return CmsGuestbookCtg.fetchIds(channels);
	}
	public Set<Integer> getChannelIds(Integer siteId) {
		Set<Channel> channels = getChannels();
		Set<Integer> ids = new HashSet<Integer>();
		for (Channel c : channels) {
			if (c.getSite().getId().equals(siteId)) {
				ids.add(c.getId());
			}
		}
		return ids;
	}
	public Set<Integer> getControlChannelIds(Integer siteId) {
		Set<Channel> channels = getControlChannels();
		Set<Integer> ids = new HashSet<Integer>();
		for (Channel c : channels) {
			if (c.getSite().getId().equals(siteId)) {
				ids.add(c.getId());
			}
		}
		return ids;
	}
	public void addToChannels(Channel channel) {
		if (channel == null) {
			return;
		}
		Set<Channel> set = getChannels();
		if (set == null) {
			set = new HashSet<Channel>();
			setChannels(set);
		}
		set.add(channel);
	}
	public void addToControlChannels(Channel channel) {
		if (channel == null) {
			return;
		}
		Set<Channel> set = getControlChannels();
		if (set == null) {
			set = new HashSet<Channel>();
			setControlChannels(set);
		}
		set.add(channel);
	}
	public void addToGuestBookCtgs(CmsGuestbookCtg ctg) {
		if (ctg == null) {
			return;
		}
		Set<CmsGuestbookCtg> set =getGuestBookCtgs();
		if (set == null) {
			set = new HashSet<CmsGuestbookCtg>();
			setGuestBookCtgs(set);
		}
		set.add(ctg);
	}
	
	public static Integer[] fetchIds(Collection<CmsDepartment> departments) {
		if (departments == null) {
			return null;
		}
		Integer[] ids = new Integer[departments.size()];
		int i = 0;
		for (CmsDepartment r : departments) {
			ids[i++] = r.getId();
		}
		return ids;
	}
	
}