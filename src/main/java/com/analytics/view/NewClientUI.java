package com.analytics.view;

import com.analytics.report.Constant;
import com.analytics.report.dao.TokenGetterDAO;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

@Theme("mytheme")
public class NewClientUI extends VerticalLayout implements View{
    private Label loginLabel, metrics, direct, metricsId;
    private Button buttonAccessMetrics, buttonAccessDirect;
    private TextField login, tfMetricsId;

    public NewClientUI() {
        setSpacing(true);
        setSizeFull();
        loginLabel = new Label("укажи новый логин yandex");
        login = new TextField();
        login.setValue("AristokratDirect");
        metricsId = new Label("укажи id метрики");
        tfMetricsId = new TextField();
        tfMetricsId.setValue("33661869");
        addComponent(loginLabel);
        addComponent(login);
        addComponent(metricsId);
        addComponent(tfMetricsId);
        addComponent(getButtonAccessDirect());
        addComponent(getButtonAccessMetrics());

    }

    public HorizontalLayout getButtonAccessMetrics() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        buttonAccessMetrics = new Button("Получить доступ к метрике");
        buttonAccessMetrics.addClickListener(clickEvent ->{
            getUI().getPage().open(
                    new TokenGetterDAO().getCode(login.getValue(), Constant.METRIC_ID, Constant.TypeMetric, tfMetricsId.getValue()), "_bland");
        });
        horizontalLayout.addComponent(buttonAccessMetrics);

        return horizontalLayout;
    }

    public Button getButtonAccessDirect() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        buttonAccessDirect = new Button("Получить доступ к директу");
        buttonAccessDirect.addClickListener(clickEvent -> {
            getUI().getPage().open(
                    new TokenGetterDAO().getCode(login.getValue(), Constant.DIRECT_ID, Constant.TypeDirect), "_bland");
        });
        horizontalLayout.addComponent(buttonAccessDirect);

        return buttonAccessDirect;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("You can add new client");
    }
}
