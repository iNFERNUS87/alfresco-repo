package org.smartlabs;

import javax.servlet.annotation.WebServlet;

import org.smartlabs.core.services.AlfrescoRepoService;
import org.smartlabs.core.services.Registry;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("simpletheme")
@Widgetset("org.smartlabs.AlfrescoRepoWidgetset")
public class MainUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8018724446847895766L;
	

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        final AlfrescoRepoService alfrescoRepoService = Registry.getAlfrescoRepoService();

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 4452696287370682275L;

			@Override
            public void buttonClick(ClickEvent event) {
				
				if(alfrescoRepoService.login()){
					layout.addComponent(new Label("Login cusscess to " + alfrescoRepoService.getRepoHost()));
				}else{
					layout.addComponent(new Label("Login fails!."));
				}
               
            }
        });
        layout.addComponent(button);

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
