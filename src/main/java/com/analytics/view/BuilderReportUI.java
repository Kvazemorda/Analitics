package com.analytics.view;

import com.analytics.entity.client.Client;
import com.analytics.entity.client.QueryClient;
import com.analytics.report.dao.BannerStatItemDAO;
import com.analytics.report.excel.CreateExcelReport;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BuilderReportUI extends VerticalLayout implements View{
    Panel panel;
    public Client client;
    private String dateStart, dateEnd;
    public Double costCPA;
    public QueryClient queryClient;
    public VerticalLayout layout;
    HorizontalLayout horizontalLayout, horizontalForDate;
    Label labelCPA;
    TextField maxCPATextField;

    public BuilderReportUI() {
        addComponent(getLayout());
    }

    private VerticalLayout getLayout(){
        horizontalLayout = new HorizontalLayout();
        horizontalForDate = new HorizontalLayout();
        panel = new Panel();
        labelCPA = new Label("Укажи макс. стоимость CPA");
        maxCPATextField = new TextField();
        PopupDateField dateFieldStart = showDateStart();
        PopupDateField dateFieldEnd = showDateEnd();

        listenerSetMaxCPA(maxCPATextField);

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            queryClient = new QueryClient();
            queryClient.setClient(client);
            queryClient.setDate1(dateStart);
            queryClient.setDate2(dateEnd);
            queryClient.setMaxCpa(costCPA);
            if (client == null || dateStart == null || dateEnd == null || costCPA == null) {
                layout.addComponent(new Label(queryClient.toString()));
            } else {
                layout.addComponent(new Label(queryClient.toString()));
                getExcelReport(queryClient);
                File file = new File(CreateExcelReport.pathToSaveReport);
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    fileInputStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                FileResource resource = new FileResource(file);
                layout.addComponent(new Label(file.getPath()));
                FileDownloader fileDownloader = new FileDownloader(resource);
                Button btn = new Button("save");
                layout.addComponent(btn);
                fileDownloader.extend(btn);
            }
        });
        horizontalForDate.addComponent(dateFieldStart);
        horizontalForDate.addComponent(dateFieldEnd);
        horizontalLayout.addComponent(showListSelectWithButton());
        horizontalLayout.addComponent(panel);

        layout = new VerticalLayout();

        layout.addComponent(horizontalForDate);
        layout.addComponent(horizontalLayout);
        layout.addComponent(labelCPA);
        layout.addComponent(maxCPATextField);
        layout.addComponent(button);

        return layout;
    }


    private ArrayList<Client> getListClient() {
        // ClientDAO clientDAO = new ClientDAO();
        Client client1 = new Client();
        client1.setMetricsID("33661869");
        client1.setoOAuthorIDMetric("AQAAAAAX4cvvAAOfZ6owq72SMETPiz7NAAIik7Q");
        client1.setoAuthorIDDirect("AQAAAAAX4cvvAAPC2-tQJOpLaEY8vk_70YJuZ_U");
        client1.setLoginDirect("AristokratDirect");
        ArrayList<Client> list = new ArrayList();
        list.add(client1);
        return list;// clientDAO.getClientList();
    }

    private PopupDateField showDateStart() {
        PopupDateField dateFieldStart = new PopupDateField();
        dateFieldStart.setDateFormat("yyyy-MM-dd");
        dateFieldStart.addValueChangeListener(e -> setDateStart((Date) e.getProperty().getValue()));
        return dateFieldStart;
    }

    private XSSFWorkbook getExcelReport(QueryClient queryClient) {
        queryClient.setCompanyDirect(new ArrayList<>());
        new BannerStatItemDAO().getCompanyName(queryClient);
        BannerStatItemDAO bannerStatItemDAO = new BannerStatItemDAO();
        bannerStatItemDAO.getCompanyName(queryClient);
        CreateExcelReport createExcelTemplate = new CreateExcelReport(queryClient);
        return createExcelTemplate.book;
    }

        private PopupDateField showDateEnd() {
            PopupDateField dateFieldStart = new PopupDateField();
            dateFieldStart.setDateFormat("yyyy-MM-dd");
            dateFieldStart.addValueChangeListener(e -> setDateEnd((Date) e.getProperty().getValue()));
            return dateFieldStart;
        }

        private void setDateStart(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateStart = sdf.format(date);
        }

        private void setDateEnd(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateEnd = sdf.format(date);
        }

        private HorizontalLayout showListSelectWithButton() {
            ListSelect selectList = new ListSelect();
            selectList.addItems(getListClient());
            selectList.addValueChangeListener(e -> client = (Client) e.getProperty().getValue());

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.addComponent(selectList);
            return horizontalLayout;
        }

        private void listenerSetMaxCPA(TextField textField) {
            final String[] text = new String[1];
            textField.addValueChangeListener(e -> costCPA = Double.parseDouble((String) e.getProperty().getValue()));
        }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
