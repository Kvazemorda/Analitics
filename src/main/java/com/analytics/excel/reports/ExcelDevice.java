package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.DeviceClientDAO;
import com.analytics.entity.report.DeviceClient;
import com.analytics.excel.ConfigExcel;
import com.analytics.excel.CreateExcelReport;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelDevice implements FillingExcel{
    public ArrayList<DeviceClient> devices;
    public static final String PC_VISITED = "pcVisited";
    public static final String PC_CONVERSATION = "pcConversation";
    public static final String PC_CONVERSATION_PER = "pcConversationPer";
    public static final String TAB_VISITED = "tabVisited";
    public static final String TAB_CONVERSATION = "tabConversation";
    public static final String TAB_CONVERSATION_PER = "tabConversationPer";
    public static final String MOBIL_VISITED = "mobilVisited";
    public static final String MOBIL_CONVERSATION = "mobilConversation";
    public static final String MOBIL_CONVERSATION_PER = "mobilConversationPer";
    public static final String ITEMS_QULITY_PER= "itemsQualPerTabAndMob";
    private ExcelRecommendation excelRecommendation;

    private DecimalFormat decimalFormat;

    public ExcelDevice(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        this.excelRecommendation = excelRecommendation;
        decimalFormat = new DecimalFormat("##0.00");
        this.devices = new DeviceClientDAO().deviceList(queryClient);
        fillListToExcel(CreateExcelReport.sheet);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        double deviceVisited = 0.0;
        double tabAndMobilVisited = 0.0;
        double conversationRateMobile = 0.0;
        double conversationRatePC = 0.0;
        double bounceRate = 0.0;
        for (DeviceClient deviceClient: devices){
            deviceVisited += deviceClient.getDeviceQuality();
        }
        for(DeviceClient deviceClient: devices){
            switch (deviceClient.getDeviceName()){
                case "ПК" :
                    changeCellFromRange(PC_VISITED, deviceClient.getDeviceQuality());
                    changeCellFromRange(PC_CONVERSATION, deviceClient.getDeviceConversation());
                    changeCellFromRange(PC_CONVERSATION_PER, (deviceClient.getDeviceQuality() / deviceVisited) * 100);
                    conversationRatePC += deviceClient.getDeviceQuality() / deviceVisited * 100;
                    break;
                case "Смартфоны" :
                    changeCellFromRange(TAB_VISITED, deviceClient.getDeviceQuality());
                    changeCellFromRange(TAB_CONVERSATION, deviceClient.getDeviceConversation());
                    changeCellFromRange(TAB_CONVERSATION_PER, (deviceClient.getDeviceQuality()) / deviceVisited * 100);
                    tabAndMobilVisited += deviceClient.getDeviceQuality();
                    conversationRateMobile += deviceClient.getDeviceQuality() / deviceVisited * 100;
                    bounceRate += deviceClient.getDeviceBounceRate();
                    break;
                case "Планшеты":
                    changeCellFromRange(MOBIL_VISITED, deviceClient.getDeviceQuality());
                    changeCellFromRange(MOBIL_CONVERSATION, deviceClient.getDeviceConversation());
                    changeCellFromRange(MOBIL_CONVERSATION_PER, (deviceClient.getDeviceQuality() / deviceVisited) * 100);
                    tabAndMobilVisited += deviceClient.getDeviceQuality();
                    conversationRateMobile += deviceClient.getDeviceQuality() / deviceVisited * 100;
                    bounceRate += deviceClient.getDeviceBounceRate();
                    break;
            }
        }

        tabAndMobilVisited = (tabAndMobilVisited / deviceVisited) * 100;
        changeCellFromRange(ITEMS_QULITY_PER, getDeviceTraffice(tabAndMobilVisited));
        setRecommendation(conversationRateMobile, conversationRatePC, bounceRate, new DeviceClientDAO().companyHasMobileAd());
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
    }

    @Override
    public void changeCellFromRange(String rangeName, String changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.getRow(cells[0].getRow());
        c = r.getCell(cells[0].getCol());
        c.setCellValue(changeValue);
        if(r.getRowNum() % 2 != 0){
            c.setCellStyle(ConfigExcel.STYLE_DEVICE);
        }else if(ITEMS_QULITY_PER.equals(rangeName)){
            c.setCellStyle(ConfigExcel.STYLE_DESCRIPTION);
        }else {
            c.setCellStyle(ConfigExcel.STYLE_DEVICE_SIMPLE);
        }
    }

    public void changeCellFromRange(String rangeName, double changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.getRow(cells[0].getRow());
        c = r.getCell(cells[0].getCol());
        c.setCellValue(changeValue);
        if(r.getRowNum() % 2 != 0){
            c.setCellStyle(ConfigExcel.STYLE_DEVICE);
        }else if(ITEMS_QULITY_PER.equals(rangeName)){
            c.setCellStyle(ConfigExcel.STYLE_DESCRIPTION);
        }else {
            c.setCellStyle(ConfigExcel.STYLE_DEVICE_SIMPLE);
        }
    }

    private String getDeviceTraffice(double visited){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String firstSource = decimalFormat.format(visited);
        return "На долю планшетов и смартфонов приходится " +  firstSource + "% трафика";
    }

    private void setRecommendation(double conversationRateMobile, double conversationRatePC, double bounceRate, boolean hasMobileAd){
        String deviceRecommendation = "";
        if(hasMobileAd){
            if(conversationRateMobile < conversationRatePC || conversationRateMobile < 0.8 || bounceRate > 30){
                deviceRecommendation = "Проверить мобильную версию сайта на адаптивность";
            }else if(conversationRateMobile > conversationRatePC){
                deviceRecommendation = "Повысить ставки на объявления для мобильных устройств";
            }
        }if(!hasMobileAd){
            deviceRecommendation = "Добавить объявления для мобильных устройств(планшеты и смартфоны)";
        }
        excelRecommendation.setDeviceRecommendation(deviceRecommendation);
    }
}