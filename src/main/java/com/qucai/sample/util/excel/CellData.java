package com.qucai.sample.util.excel;


/**
 *
 * version 1.0
 */
public class CellData {

    /**
     * 单元格列数
     */
    private int columnIndex;

    /**
     * 单元格数据
     */
    private String cellValue;

    /**
     * 附加信息
     */
    private String extraInfo;

    /**
     * 对应数据的key
     */
    private String key;

    /**
     * 是否通过校验
     * 1 通过
     * 0 未通过
     */
    private int passed=1;

    private boolean mobile = true;


    public CellData(){}

    public CellData(int index, String content, String dataKey){
        this.columnIndex = index;
        this.cellValue = content;
        this.key = dataKey;
    }


    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }
}