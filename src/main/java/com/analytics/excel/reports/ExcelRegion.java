package com.analytics.excel.reports;

import com.analytics.client.QueryClient;
import com.analytics.dao.RegionDAO;
import com.analytics.entity.report.Region;
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

public class ExcelRegion implements FillingExcel{
    public ArrayList<Region> regions;
    public static final int REGION_NAME_CELL = 9;
    public static final int REGION_QUALITY_CELL = 10;
    public static final String REGION_NAME_RN = "regionName";
    public static final String REGION_QUALITY_RN = "regionQuality";
    public static final String REGION_NAME_COLUMN = "J";
    public static final String REGION_QUALITY_COLUMN = "K";
    public static final String REGION_LIDER = "regionLider";
    public static final String REGION_CONVERS_LIDER = "regionConversLider";
    private ExcelRecommendation excelRecommendation;

    private DecimalFormat decimalFormat;

    public ExcelRegion(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        decimalFormat = new DecimalFormat("##0.00");
        this.regions = new RegionDAO().regionList(queryClient);
        fillListToExcel(CreateExcelReport.sheet);
    }


    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        for(int i = 0; i < regions.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellSource = row.createCell(REGION_NAME_CELL);
            cellSource.setCellValue(regions.get(i).getRegionName() + " (" + regions.get(i).getRegionConversation() + "%)");

            Cell cellQuality = row.createCell(REGION_QUALITY_CELL);
            cellQuality.setCellValue(regions.get(i).getRegionQuality());
        }
        changeRange(2, regions.size() + 1, REGION_NAME_COLUMN, REGION_NAME_RN);
        changeRange(2, regions.size() + 1, REGION_QUALITY_COLUMN, REGION_QUALITY_RN);
        changeCellFromRange(REGION_LIDER, getLeadOfRegion());
        changeCellFromRange(REGION_CONVERS_LIDER, getLeadOfConversation());
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
        Row r =  s.createRow(cells[0].getRow());
        c = r.createCell(cells[0].getCol());
        c.setCellValue(changeValue);
        c.setCellStyle(ConfigExcel.STYLE_DESCRIPTION);
    }
    private String getLeadOfRegion(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < regions.size(); i++){
            if(max < regions.get(i).getRegionQuality()){
                max = regions.get(i).getRegionQuality();
                index = i;
            }
        }
        String firstSource = decimalFormat.format(max);
        return "Лидер по посетителям - " + regions.get(index).getRegionName() + " (" + firstSource + ")";
    }

    private String getLeadOfConversation(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < regions.size(); i++){
            if(max < regions.get(i).getRegionConversation()){
                max = regions.get(i).getRegionConversation();
                index = i;
            }
        }
        String firstQuality = decimalFormat.format(max);
        return "% Конверсии больше - " + regions.get(index).getRegionName() + " (" + firstQuality + "%)";
    }
}
