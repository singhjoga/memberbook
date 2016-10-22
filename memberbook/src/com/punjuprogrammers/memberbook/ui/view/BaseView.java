package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.punjuprogrammers.memberbook.bl.cache.ApplicationCache;
import com.punjuprogrammers.memberbook.ui.util.UserSessionContext;

public abstract class BaseView implements Serializable{
	
	private static final long serialVersionUID = -8988234954827325106L;
	private static final Logger LOG = LoggerFactory.getLogger(BaseView.class);
	private ApplicationCache cache;
	
	@ManagedProperty(value="#{userSessionContext}")
	private UserSessionContext sessionContext;
    
	public <T> T getServiceBean(Class<T> clazz) {
		ApplicationContext appCtx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		return appCtx.getBean(clazz);
	}
	public Object getServiceBean(String beanName) {
		ApplicationContext appCtx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		return appCtx.getBean(beanName);
	}

	public UserSessionContext getSessionContext() {
		return sessionContext;
	}

	public void setSessionContext(UserSessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	public ApplicationCache getCache() {
		if (cache == null) {
			cache = getServiceBean(ApplicationCache.class);
		}
		
		return cache;
	}
	 
	public abstract void init();
}
