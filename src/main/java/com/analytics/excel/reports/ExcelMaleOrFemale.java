package com.analytics.excel.reports;

import com.analytics.dao.MaleOrFemaleDAO;
import com.analytics.entity.report.MaleOrFemale;
import com.analytics.excel.ConfigExcel;
import com.analytics.excel.CreateExcelReport;
import com.analytics.excel.Main;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.analytics.excel.reports.ExcelDevice.ITEMS_QULITY_PER;

public class ExcelMaleOrFemale implements FillingExcel {
    public ArrayList<MaleOrFemale> maleOrFemales;
    public static final int MALE_OR_FEMALE = 14;
    public static final int MALE_OR_FEMALE_VISITED = 15;
    public static final int MALE_OR_FEMALE_CONVERSATION_PER = 16;
    public static final String MALE_OR_FEMALE_RN = "maleOrFemale";
    public static final String MALE_OR_FEMALE_VISITED_RN = "maleOrFemaleVisited";
    public static final String MALE_OR_FEMALE_CONVERSATION_PER_RN = "maleOrFemaleConverstionPer";
    public static final String MALE_OR_FEMALE_COLUMN = "O";
    public static final String MALE_OR_FEMALE_VISITED_COLUMN = "P";
    public static final String MALE_OR_FEMALE_CONVERSATION_PER_COLUMN = "Q";
    public static final String WHO_MORE_ON_SITE = "whoMoreOnSite";
    public static final String WHICH_CONVERSATION_MORE = "whichConversationMore";
    public static final String FEMALE_CONVERSTAION_PER = "femaleConversation";
    public static final String MALE_CONVERSTAION_PER = "maleConversationPer";

    private DecimalFormat decimalFormat;

    public ExcelMaleOrFemale() {
        decimalFormat = new DecimalFormat("##0.00");
        this.maleOrFemales = new MaleOrFemaleDAO().maleOrFemaleList(Main.queryClient);
        fillListToExcel(CreateExcelReport.sheet);
    }


    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        double maleVisited = 0.0;
        double femaleVisited = 0.0;
        double tabAndMobilVisited = 0.0;
        for(int i = 0; i < maleOrFemales.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell nameCell = row.createCell(MALE_OR_FEMALE);
            nameCell.setCellValue(maleOrFemales.get(i).getMaleOrFemale());

            Cell visitedCell = row.createCell(MALE_OR_FEMALE_VISITED);
            visitedCell.setCellValue(maleOrFemales.get(i).getMaleOrFemaleVisited());

            Cell conversationCell = row.createCell(MALE_OR_FEMALE_CONVERSATION_PER);
            conversationCell.setCellValue(maleOrFemales.get(i).getMaleOrFemaleVisited());

            if(maleOrFemales.get(i).getMaleOrFemale().equals("Мужчины")){
                maleVisited += maleOrFemales.get(i).getMaleOrFemaleVisited();
            }else{
                femaleVisited += maleOrFemales.get(i).getMaleOrFemaleVisited();
            }
        }

        if(maleVisited > femaleVisited){

        }else{

        }

       /* tabAndMobilVisited = (tabAndMobilVisited / deviceVisited) * 100;
        changeCellFromRange(ITEMS_QULITY_PER, getDeviceTraffice(tabAndMobilVisited));*/
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
    private String getDeviceTraffice(double visited){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String firstSource = decimalFormat.format(visited);
        return "На долю планшетов и смартфонов приходится " +  firstSource + "% трафика";
    }

}
