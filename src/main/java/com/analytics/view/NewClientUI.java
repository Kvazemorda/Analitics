package com.analytics.view;

import com.analytics.report.Constant;
import com.analytics.report.dao.TokenGetterDAO;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;


public class NewClientUI extends VerticalLayout implements View{
    private Label loginLabel, metrics, direct;
    private Button buttonAccessMetrics, buttonAccessDirect;
    private TextField login;
    private Navigator navigator;

    public NewClientUI(Navigator navigator) {
        this.navigator = navigator;
        setSizeFull();
        loginLabel = new Label("укажи новый логин yandex");
        login = new TextField();


    }

    public Button getButtonAccessMetrics() {
        buttonAccessDirect = new Button();
        buttonAccessDirect.addClickListener(clickEvent -> new TokenGetterDAO().getCode(login.getValue(), Constant.METRIC_ID));
        return buttonAccessMetrics;
    }

    public Button getButtonAccessDirect() {
        buttonAccessDirect = new Button();
        buttonAccessDirect.addClickListener(clickEvent -> new TokenGetterDAO().getCode(login.getValue(), Constant.DIRECT_ID));
        return buttonAccessMetrics;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("You can add new client");
    }
}
