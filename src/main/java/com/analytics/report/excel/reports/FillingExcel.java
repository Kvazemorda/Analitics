package com.analytics.report.excel.reports;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface FillingExcel {

    public void fillListToExcel(XSSFSheet sheet);

    public void changeRange(int start, int end, String column, String rangeName);

    public void changeCellFromRange(String rangeName, String changeValue);


}
