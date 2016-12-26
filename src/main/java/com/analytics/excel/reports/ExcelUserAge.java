package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.UserAgeDAO;
import com.analytics.entity.report.UserAge;
import com.analytics.excel.ConfigExcel;
import com.analytics.excel.CreateExcelReport;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExcelUserAge implements FillingExcel {
    private ArrayList<UserAge> userAges;
    public static int RANGE_AGE_USER = 11;
    public static int USER_VISITED = 12;
    public static int USER_CONVERSATION = 13;
    public static final String RANGE_AGE_USER_COLUMN = "L";
    public static final String USER_VISITED_COLUMN = "M";
    public static final String USER_CONVERSATION_COLUMN = "N";
    public static final String RANGE_AGE_USER_RN = "rangeAgeuser";
    public static final String USER_VISITED_RN = "userVisited";
    public static final String USER_CONVERSATION_RN = "userConversation";
    public static final String REGION_AGE_LIDER = "regionAgeLider";
    public static final String REGION_AGE_CONVERSATION_LIDER = "regionAgeConversationLider";
    private ExcelRecommendation excelRecommendation;
    private StringBuilder stringLowRecommendation, stringHighRecommendation;

    public ExcelUserAge(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        this.excelRecommendation = excelRecommendation;
        this.userAges = new UserAgeDAO().userAgesList(queryClient);
        stringHighRecommendation = new StringBuilder();
        stringLowRecommendation = new StringBuilder();
        fillListToExcel(CreateExcelReport.sheet);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        int visitALL = 0;
        int conversationAll = 0;
        double conversationRateALL = 0.0;
        for(int i = 0; i < userAges.size(); i++) {
            visitALL += userAges.get(i).getUserVisited();
            conversationAll += userAges.get(i).getUserConversation();
        }
        conversationRateALL = conversationAll / (double) visitALL;
        for(int i = 0; i < userAges.size(); i++) {
            Row row = sheet.getRow(i + 1);
            if (row == null) {
                row = sheet.createRow(i + 1);
            }
            Cell nameCell = row.createCell(RANGE_AGE_USER);
            nameCell.setCellValue(userAges.get(i).getRangeAgeUser());

            Cell visitedCell = row.createCell(USER_VISITED);
            visitedCell.setCellValue(userAges.get(i).getUserVisited());

            Cell conversationCell = row.createCell(USER_CONVERSATION);
            conversationCell.setCellValue(userAges.get(i).getUserConversation());
            if (i + 1 == userAges.size()) {
                if (userAges.get(i).getUserConversation() / userAges.get(i).getUserVisited() * 100 < 0.6) {
                    createTextLowRecommendation(userAges.get(i).getRangeAgeUser() + ".");
                }
                if(userAges.get(i).getUserConversation() / userAges.get(i).getUserVisited() >= conversationRateALL){
                    createTextHighRecommendation(userAges.get(i).getRangeAgeUser() + ".");
                }
            }else {
                if (userAges.get(i).getUserConversation() / userAges.get(i).getUserVisited() * 100 < 0.6) {
                    createTextLowRecommendation(userAges.get(i).getRangeAgeUser() + ", ");
                }
                if(userAges.get(i).getUserConversation() / userAges.get(i).getUserVisited() >= conversationRateALL){
                    createTextHighRecommendation(userAges.get(i).getRangeAgeUser() + ", ");
                }
            }
        }
        //change range for name column
        changeRange(2, userAges.size() + 1, RANGE_AGE_USER_COLUMN, RANGE_AGE_USER_RN);
        //change range for visited column
        changeRange(2, userAges.size() + 1, USER_VISITED_COLUMN, USER_VISITED_RN);
        //change range for conversation column
        changeRange(2, userAges.size() + 1, USER_CONVERSATION_COLUMN, USER_CONVERSATION_RN);

        changeCellFromRange(REGION_AGE_LIDER, getFirstRange());
        changeCellFromRange(REGION_AGE_CONVERSATION_LIDER, getBestCoeff());

        String lowRecommendation = "Отключить объявления для возрастных групп: " + stringLowRecommendation.toString();
        excelRecommendation.setAgeUserLowConversationRecommendation(lowRecommendation);

        String highRecommendation = "Повысить ставки для возрастных групп: " + stringHighRecommendation.toString();
        excelRecommendation.setAgeUserLowConversationRecommendation(highRecommendation);
    }

    private String getFirstRange(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < userAges.size(); i++){
            if(max < userAges.get(i).getUserVisited()){
                max = userAges.get(i).getUserVisited();
                index = i;
            }
        }
        String firstSource = decimalFormat.format(max);
        if(index == 0 && max == 0.0) {
            return "Нет сеансов";
        }else {
            return "Болше всего сеансов у группы - " + userAges.get(index).getRangeAgeUser() + " (" + firstSource + ")";
        }
    }

    private String getBestCoeff() {
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < userAges.size(); i++){
            double coeff = (userAges.get(i).getUserConversation() / userAges.get(i).getUserVisited()) * 100;
            if(max < coeff){
                max = coeff;
                index = i;
            }
        }
        String firstSource = decimalFormat.format(max);
        if(index == 0 && max == 0.0) {
            return "Нет конверсии";
        }else {
            return "Лучший коэффициент конверсии у группы " + userAges.get(index).getRangeAgeUser() + " (" + firstSource + "%)";
        }
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheet.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
        rangeCell.setRefersToFormula(reference);
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
        c.setCellStyle(ConfigExcel.STYLE_DESCRIPTION);
    }

    private void createTextLowRecommendation(String text){
        stringLowRecommendation.append(text);
    }
    private void createTextHighRecommendation(String text){
        stringHighRecommendation.append(text);
    }
}