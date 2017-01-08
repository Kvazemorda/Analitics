package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.DayDAO;
import com.analytics.entity.report.Day;
import com.analytics.excel.StyleExcel;
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

public class ExcelDayOfWeek implements FillingExcel{
    public ArrayList<Day> dayOfWeeks;
    public static final int HOURSE_OF_DAY_CELL = 20;
    public static final int HOURSE_OF_DAY_VISITED_CELL = 21;
    public static final int HOURSE_OF_DAY_CONVERSATION_CELL = 22;
    public static final String HOURSE_OF_DAY_RN = "hoursOfDay";
    public static final String HOURSE_OF_DAY_VISITED_RN = "hoursOfDayVisited";
    public static final String HOURSE_OF_DAY_CONVERSATION_RN = "hoursOfDayConversation";
    public static final String HOURSE_OF_DAY_COLUMN = "U";
    public static final String HOURSE_OF_DAY_VISITED_COLUMN = "V";
    public static final String HOURSE_OF_DAY_CONVERSATION_COLUMN = "W";
    public static final String POPULAR_HOURS = "popularHours";
    private ExcelRecommendation excelRecommendation;
    private DecimalFormat decimalFormat;
    private StringBuilder hourTurnOff;

    public ExcelDayOfWeek(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        hourTurnOff = new StringBuilder();
        this.excelRecommendation = excelRecommendation;
        decimalFormat = new DecimalFormat("##0.00");
        this.dayOfWeeks = new DayDAO().getDayList(queryClient);
        fillListToExcel(CreateExcelReport.sheetData);
    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        double avrConversation = 0.0;
        int visited = 0;
        int conversation = 0;
        for(int i = 0; i < dayOfWeeks.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellHour = row.createCell(HOURSE_OF_DAY_CELL);
            cellHour.setCellValue(dayOfWeeks.get(i).getHoursOfDay());

            Cell cellVisited = row.createCell(HOURSE_OF_DAY_VISITED_CELL);
            cellVisited.setCellValue(dayOfWeeks.get(i).getHoursOfDayVisited());

            Cell cellConversation = row.createCell(HOURSE_OF_DAY_CONVERSATION_CELL);
            cellConversation.setCellValue(dayOfWeeks.get(i).getHoursOfDayConversation());

            visited += dayOfWeeks.get(i).getHoursOfDayVisited();
            conversation += dayOfWeeks.get(i).getHoursOfDayConversation();

        }

        avrConversation = conversation / (double) visited;
        for(int i = 0; i < dayOfWeeks.size(); i++){
            double conversationSingle = dayOfWeeks.get(i).getHoursOfDayConversation();
            double visitedSingle = dayOfWeeks.get(i).getHoursOfDayVisited();
            double conversationSingleAvr = conversationSingle / visitedSingle;
            if(conversationSingleAvr < avrConversation * 0.35){
                if(i + 1 < dayOfWeeks.size()){
                    addDayToTurnOff(dayOfWeeks.get(i).getHoursOfDay() + " ");
                }else {
                    addDayToTurnOff(dayOfWeeks.get(i).getHoursOfDay());
                }
            }
            excelRecommendation.setDayRecommendation("Отключить рекламу: " + hourTurnOff.toString());
        }

        changeRange(2, dayOfWeeks.size() + 1, HOURSE_OF_DAY_COLUMN, HOURSE_OF_DAY_RN);
        changeRange(2, dayOfWeeks.size() + 1, HOURSE_OF_DAY_VISITED_COLUMN, HOURSE_OF_DAY_VISITED_RN);
        changeRange(2, dayOfWeeks.size() + 1, HOURSE_OF_DAY_CONVERSATION_COLUMN, HOURSE_OF_DAY_CONVERSATION_RN);

            changeCellFromRange(POPULAR_HOURS, "Конверсии происходят приемущественно в - " + getPopularHours(dayOfWeeks));
    }

    private String getPopularHours(ArrayList<Day> list){
        int visited = 0;
        int conversation = 0;
        int countConversationExist = 0;
        double avrConversation = 0.0;
        int hourBefore;
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getHoursOfDayConversation() > 0){
                countConversationExist++;
            }
            conversation += list.get(i).getHoursOfDayConversation();
        }
        avrConversation = conversation / countConversationExist;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getHoursOfDayConversation() > avrConversation){
                stringBuilder.append(list.get(i).getHoursOfDay());
                if(i != list.size() - 1 ){
                    stringBuilder.append(", ");
                }else {
                    stringBuilder.append(".");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {
        XSSFName rangeCell = CreateExcelReport.book.getName(rangeName);
        String reference = CreateExcelReport.sheetData.getSheetName() + "!$" + column + "$" + start + ":$" + column + "$" + (end);
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
        Row r =  s.createRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        c.setCellStyle(StyleExcel.STYLE_DESCRIPTION);
    }

    private void addDayToTurnOff(String dayOfWeek){
        hourTurnOff.append(dayOfWeek);
    }
}
