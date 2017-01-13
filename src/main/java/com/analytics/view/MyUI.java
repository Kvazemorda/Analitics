package com.analytics.view;

import com.analytics.dao.ClientDAO;
import com.analytics.entity.client.Client;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final HorizontalLayout baseLayout = new HorizontalLayout();

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + ", it works!"));
        });
        baseLayout.addComponent(showListSelect());
        baseLayout.setMargin(true);
        baseLayout.setSpacing(true);
        
        setContent(baseLayout);
    }

    private ArrayList<Client> getListClient(){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.getClientList();
    }

    private ListSelect showListSelect(){
        ListSelect selectList = new ListSelect();
        ArrayList<Client> list = getListClient();
        for(int i = 0; i < list.size(); i++){
            selectList.addItem(list.get(i));
        }
        return selectList;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
