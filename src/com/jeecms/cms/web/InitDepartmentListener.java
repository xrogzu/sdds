package com.jeecms.cms.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jeecms.cms.cache.DepartmentCache;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.manager.CmsDepartmentMng;

public class InitDepartmentListener implements ServletContextListener{

	private ApplicationContext context;
	private Timer timer1 = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(timer1!=null){
			timer1.cancel();
		}	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		final CmsDepartmentMng cmsDepartmentMng = (CmsDepartmentMng) context.getBean("cmsDepartmentMng");
		
		timer1 = new Timer(true);
		timer1.schedule(new TimerTask() {
			@Override
			public void run() {
				Calendar cTime = Calendar.getInstance();
				if (((cTime.get(Calendar.HOUR_OF_DAY) == 12 && cTime.get(Calendar.MINUTE) == 30) ||
						(cTime.get(Calendar.HOUR_OF_DAY) == 19 && cTime.get(Calendar.MINUTE) == 30) ||
						(cTime.get(Calendar.HOUR_OF_DAY) == 23 && cTime.get(Calendar.MINUTE) == 30) ||
						(cTime.get(Calendar.HOUR_OF_DAY) == 7 && cTime.get(Calendar.MINUTE) == 30)) || 
						(DepartmentCache.departentList != null && DepartmentCache.departentList.size() == 0)) {
					long startM = System.currentTimeMillis();
					System.out.println("==========党组织缓存加载Start=========");
					DepartmentCache dc = new DepartmentCache();
					List<CmsDepartment> dmList = cmsDepartmentMng.findAll();
					/*List<CmsDepartment> newList = new ArrayList<CmsDepartment>();
					for (CmsDepartment dm : dmList) {
						CmsDepartment temp = new CmsDepartment();
						temp.setId(dm.getId());
						temp.setPid(dm.getPid());
						temp.setPriority(dm.getPriority());
						temp.setName(dm.getName());
						temp.setChild(dm.getChild());
						temp.setParent(dm.getParent());
						newList.add(temp);
					}*/
					dc.refreshDepartentList(dmList);
					System.out.println("==========党组织缓存加载End; [cost "+(System.currentTimeMillis() - startM)+"ms]=========");
				}
			}
		}, 10 * 1000, 60 * 1000); //10秒启动第一次，1分钟循环一次
	}

}
