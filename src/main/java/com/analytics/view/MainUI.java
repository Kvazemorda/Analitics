package com.analytics.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
public class MainUI extends UI {
    public static final String NEWCLIENTUI = "newclient";
    private HorizontalLayout hlayout;
    Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        hlayout = new HorizontalLayout();
        addComponentToHLayout();
        CssLayout viewLayout = new CssLayout();
        layout.addComponent(hlayout);
        layout.addComponent(viewLayout);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        navigator = new Navigator(this, viewLayout);
        navigator.addView("", BuilderReportUI.class);
        navigator.addView("newclient", NewClientUI.class);
    }

    private void addComponentToHLayout(){
        Button addNewClientButton = new Button("Add new client");
        addNewClientButton.addClickListener(e -> navigator.navigateTo(MainUI.NEWCLIENTUI));
        hlayout.addComponent(addNewClientButton);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
