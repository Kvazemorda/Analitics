package com.analytics.report.excel.reports;

import com.analytics.report.excel.CreateExcelReport;
import com.analytics.report.excel.StyleExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelAddRecommendation {
    private int lastRow;
    private int cellRedLine;
    private int TWO_STEPS = 2;
    private int ONE_STEP = 1;
    private ExcelRecommendation excelRecommendation;
    private XSSFSheet sheet;

    public ExcelAddRecommendation(ExcelRecommendation excelRecommendation) {
        lastRow = 1;
        this.excelRecommendation = excelRecommendation;
        cellRedLine = 1;
        sheet = CreateExcelReport.sheetRecommendation;
        if(excelRecommendation.getSearchOrContext() != null){
            addRSearchOrCompany();
        }
        if(excelRecommendation.getDeviceRecommendation()!= null){
            addRDevices();
        }
        if(excelRecommendation.getAgeUserLowConversationRecommendation() != null){
            addRUserAges();
        }
        if(excelRecommendation.getMaleOrFemaleRecommendation() != null){
            addRMaleOrFemale();
        }
        if(excelRecommendation.getRegionRecommendation() != null){
            addRRegion();
        }
        if(excelRecommendation.getWeekRecommendation() != null){
            addRDayOfWeek();
        }
        if(excelRecommendation.getAgeUserLowConversationRecommendation() != null){
            addRHourOfDay();
        }
        System.out.println(excelRecommendation.toString());
    }
    private void addRSearchOrCompany(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по рекламной кампании:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getSearchOrContext());
    }

    private void addRDevices(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по устройствам:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getDeviceRecommendation());
    }

    private void addRUserAges(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по возрастной группе:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getAgeUserLowConversationRecommendation());
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getAgeUserHighConversationRecommendation());
    }
    private void addRMaleOrFemale(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по полу посетителей:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getMaleOrFemaleRecommendation());
    }

    private void addRRegion(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по географии:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getRegionRecommendation());
    }

    private void addRDayOfWeek(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по дням недели:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getWeekRecommendation());
    }

    private void addRHourOfDay(){
        addRTitle(getLastRow(TWO_STEPS), "Рекомендации по времени суток:");
        addRecommendation(getLastRow(ONE_STEP), excelRecommendation.getDayRecommendation());
    }

    private void addRTitle(int lastRow, String title){
        Row row = sheet.createRow(lastRow);
        Cell cellTitle = row.createCell(cellRedLine);
        cellTitle.setCellValue(title);
        cellTitle.setCellStyle(StyleExcel.STYLE_RECOMMENDATION_TITLE);
    }

    private void addRecommendation(int lastRow, String recommendation){
        Row row = sheet.createRow(lastRow);
        Cell cellTitle = row.createCell(cellRedLine);
        cellTitle.setCellValue(recommendation);
        cellTitle.setCellStyle(StyleExcel.STYLE_RECOMMENDATION);
    }

    private int getLastRow(int quantityNewRow){
        lastRow += quantityNewRow;
        return lastRow;
    }
}
