package org.smartlabs;

import javax.servlet.annotation.WebServlet;

import org.smartlabs.core.services.AlfrescoRepoService;
import org.smartlabs.core.services.Registry;
import org.smartlabs.ui.MainScreen;
import org.smartlabs.ui.authentication.AccessControl;
import org.smartlabs.ui.authentication.BasicAccessControl;
import org.smartlabs.ui.authentication.LoginScreen;
import org.smartlabs.ui.authentication.LoginScreen.LoginListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("simpletheme")
@Widgetset("org.smartlabs.AlfrescoRepoWidgetset")
public class MainUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8018724446847895766L;

	private AccessControl accessControl = new BasicAccessControl();

	@Autowired
	AlfrescoRepoService repoService;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("Alfresco Repo Login");
		
		Registry.getInstance().getAlfrescoRepoService();
		
		if (!accessControl.isUserSignedIn()) {
			setContent(new LoginScreen(accessControl, new LoginListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 3574029619376034417L;

				@Override
				public void loginSuccessful() {
					showMainView();
				}
			}));
		} else {
			showMainView();
		}

	}

	protected void showMainView() {
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MainScreen(MainUI.this));
		getNavigator().navigateTo(getNavigator().getState());
	}

	public static MainUI get() {
		return (MainUI) UI.getCurrent();
	}

	public AccessControl getAccessControl() {
		return accessControl;
	}

	@WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class MainUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3272497779217338111L;
	}
}
