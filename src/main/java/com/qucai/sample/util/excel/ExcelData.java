package com.qucai.sample.util.excel;

import java.util.List;
/**
*
* version 1.0
*/
public class ExcelData {

   /**
    * 单元格列数
    */
   private String fileName;
   
   /**
    * 单元格数据
    */
   private List<RowData> rows;

   public String getFileName() {
	return fileName;
}

public List<RowData> getRows() {
	return rows;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public void setRows(List<RowData> rows2) {
	this.rows = rows2;
}





}