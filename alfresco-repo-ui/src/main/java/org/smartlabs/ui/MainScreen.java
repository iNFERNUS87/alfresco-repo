package org.smartlabs.ui;

import org.smartlabs.MainUI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8671797266717072683L;

	public MainScreen(MainUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);


        navigator.addViewChangeListener(viewChangeListener);

        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        /**
		 * 
		 */
		private static final long serialVersionUID = 6067845258387228321L;

		@Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			// TODO Auto-generated method stub
			
		}


    };
}
