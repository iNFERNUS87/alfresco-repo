package org.smartlabs.core.services.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.apache.chemistry.opencmis.client.bindings.CmisBindingFactory;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.log4j.Logger;
import org.smartlabs.core.cmis.ws.NavigationService;
import org.smartlabs.core.cmis.ws.NavigationServicePort;
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
	
	private static final String RECEIVE_TIMEOUT = "60000";

	private static final String CONNECTION_TIMEOUT = "10000";

	private NavigationServicePort navigationServicePort;

	private URL wsdlLocation;

	
	@Override
	public boolean login() {
		
		return true;
	}


	@Override
	public String getRepoHost() {
		return alfrescoRepoHost;
	}

	@PostConstruct
	public void postConstruct() {
		try {
			wsdlLocation = new URL(alfrescoRepoHost);
			setNavigationServicePort(new NavigationService(wsdlLocation).getNavigationServicePort());
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Can't initialize web services", e);
		}
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(SessionParameter.USER, "admin");
		parameters.put(SessionParameter.PASSWORD, "admin");

		parameters.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_POLICY_SERVICE, alfrescoRepoHost);
		parameters.put(SessionParameter.WEBSERVICES_ACL_SERVICE, alfrescoRepoHost);
		
		

		/*CmisBindingFactory factory = CmisBindingFactory.newInstance();
		CmisBinding binding = factory.createCmisWebServicesBinding(parameters);	
		
		GetFolderTree folderTree = new GetFolderTree();*/

		
/*		try {
			GetFolderTreeResponse folderTree2 = getNavigationServicePort().getFolderTree(folderTree);
		} catch (CmisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	

	public NavigationServicePort getNavigationServicePort() {
		if(navigationServicePort == null){
			NavigationServicePort port = new NavigationService(wsdlLocation).getNavigationServicePort();
            ((BindingProvider)port).getRequestContext().put("javax.xml.ws.client.connectionTimeout", CONNECTION_TIMEOUT);
            ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.receiveTimeout", RECEIVE_TIMEOUT);
            setNavigationServicePort(port);
		}
		return navigationServicePort;
	}


	public void setNavigationServicePort(NavigationServicePort navigationServicePort) {
		this.navigationServicePort = navigationServicePort;
	}

}
