package com.sc.springmvc.listener;

import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.model.Role;
import com.sc.springmvc.service.ICaseSystemService;
import com.sc.springmvc.service.IRoleService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by Suncy on 2018/4/21 0021.
 */
public class CacheLilter implements ServletContextListener {
    public void contextInitialized(ServletContextEvent se){
        ICaseSystemService systemService = (ICaseSystemService) WebApplicationContextUtils.getWebApplicationContext(se.getServletContext()).getBean(ICaseSystemService.class);

        List<CaseSystem> list =systemService.getAll();
        for(CaseSystem caseSystem:list){
            ObjectCache.addCache(caseSystem.getSystemid(),caseSystem);
        }

        IRoleService roleService = (IRoleService) WebApplicationContextUtils.getWebApplicationContext(se.getServletContext()).getBean(IRoleService.class);
        List<Role> list1 =roleService.getAll();
        for(Role role:list1){
            ObjectCache.addCache(role.getRoleid(),role);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
