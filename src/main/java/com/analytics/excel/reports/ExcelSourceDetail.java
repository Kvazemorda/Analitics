package com.analytics.excel.reports;

import com.analytics.dao.SourceDetailDAO;
import com.analytics.entity.report.SourceDetail;
import com.analytics.excel.ConfigExcel;
import com.analytics.excel.CreateExcelReport;
import com.analytics.excel.Main;
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

public class ExcelSourceDetail implements FillingExcel {
    public ArrayList<SourceDetail> sourceDetailList;
    public static final int SOURCE_DETAIL_CELL = 3;
    public static final int QUALITY_DETAIL_CELL = 2;
    public static final String SOURCE_DETAIL_PER_RN = "sourceDetailPer";
    public static final String SOURCE_DETAIL_RN = "sourceDetail";
    public static final String SOURCE_DETAIL_COLUMN = "D";
    public static final String QUALITY_DETAIL_COLUMN = "C";
    public static final String VISITED_DETAIL = "detailVisited";
    public static final String CONVERSATION_DETAIL = "detailConversation";
    private DecimalFormat decimalFormat;

    public ExcelSourceDetail() {
        decimalFormat = new DecimalFormat("##0.00");
        this.sourceDetailList = new SourceDetailDAO().getSourceDetail(Main.queryClient);
        fillListToExcel(CreateExcelReport.sheet);

    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {
        for(int i = 0; i < sourceDetailList.size(); i++){
            Row row = sheet.getRow(i + 1);
            if(row == null){
                row = sheet.createRow(i + 1);
            }
            Cell cellSource = row.createCell(SOURCE_DETAIL_CELL);
            String conversation = decimalFormat.format(sourceDetailList.get(i).getConversationDetail());
            cellSource.setCellValue(sourceDetailList.get(i).getSourceDetail()+ " (" + conversation
            + ")");
            Cell cellQuality = row.createCell(QUALITY_DETAIL_CELL);
            cellQuality.setCellValue(sourceDetailList.get(i).getCountVisited());
        }
        changeRange(2, sourceDetailList.size() + 1, SOURCE_DETAIL_COLUMN, SOURCE_DETAIL_PER_RN);
        changeRange(2, sourceDetailList.size() + 1, QUALITY_DETAIL_COLUMN, SOURCE_DETAIL_RN);

        changeCellFromRange(VISITED_DETAIL, getFirstSource());
        changeCellFromRange(CONVERSATION_DETAIL, getFirstConversation());
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

    private String getFirstSource(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < sourceDetailList.size(); i++){
            if(max < sourceDetailList.get(i).getCountVisited()){
                max = sourceDetailList.get(i).getCountVisited();
                index = i;
            }
        }
        String firstSource = decimalFormat.format(max);
        return "Болше всего трафика - " + sourceDetailList.get(index).getSourceDetail() + " (" + firstSource + ")";
    }

    private String getFirstConversation(){
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double max = 0.0;
        int index = 0;
        for(int i = 0; i < sourceDetailList.size(); i++){
            if(max < sourceDetailList.get(i).getConversationDetail()){
                max = sourceDetailList.get(i).getConversationDetail();
                index = i;
            }
        }
        String firstQuality = decimalFormat.format(max);
        if(max == 0){
            return "Нет конверсий по источникам";
        }
        else {
            return "Самый высокий % конверсии - " + sourceDetailList.get(index).getSourceDetail() + " (" + firstQuality + "%)";
        }
    }
}
