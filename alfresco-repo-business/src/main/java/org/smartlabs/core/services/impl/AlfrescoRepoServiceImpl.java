package org.smartlabs.core.services.impl;

import java.io.Serializable;

import org.smartlabs.core.services.AlfrescoRepoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AlfrescoRepoServiceImpl implements AlfrescoRepoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8988579508779851000L;

	@Value("${alfrescoRepoHost}")
	private String alfrescoRepoHost;

	
	@Override
	public boolean login() {
		return true;
	}


	@Override
	public String getRepoHost() {
		return alfrescoRepoHost;
	}

}
