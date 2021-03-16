package com.qucai.sample.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.util.FileUtil;

import com.qucai.sample.util.excel.CellData;
import com.qucai.sample.util.excel.ExcelData;
import com.qucai.sample.util.excel.RowData;

/**
 * 文件操作工具类
 */
public class PersonalInfoBatchUtil {

    private Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 获取文件的后缀名称
     * @param file
     * @return
     */
    public static String getFileSuffix(File file){
        String suffix = "";
        if(null != file && StringUtils.isBlank(file.getName())){
            String fileName = file.getName();
            suffix = fileName.substring(fileName.lastIndexOf("."));
        }

        return suffix;
    }

    /**
     * 解析excel文件
     * @param file excel文件
     * @param startRow 起始行 0为第一行
     * @param columnKey  每列对应的key值
     * @return
     */
    public static ExcelData parseExcelFile(File file, int startRow, String[] columnKey){
        List<RowData> rows = null;
        ExcelData excelData = new ExcelData();
        try {
            if(null==file || !file.exists() || columnKey.length<1){
                return excelData;
            }
            String fileName = file.getName();
            excelData.setFileName(fileName);
            if(fileName.endsWith("xls")){
                rows = parse2003Excel(file, startRow, columnKey);
            } else if(fileName.endsWith("xlsx")){
                rows = parse2007Excel(file, startRow, columnKey);
            } else {
                throw new RuntimeException("Unknown file type : "+fileName);
            }
            excelData.setRows(rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelData;
    }

    /**
     * 解析2003 excel文件
     * @param file excel文件
     * @param startRow 起始行 0为第一行  第一行已经有 头了
     * @param columnKey  每列对应的key值
     * @return
     */
    @SuppressWarnings("deprecation")
	private static List<RowData> parse2003Excel(File file, int startRow, String[] columnKey){
        List<RowData> rows = new ArrayList<RowData>();
        try {
            String fileName = file.getName();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));//获取输入流
            HSSFWorkbook wk = new HSSFWorkbook(bis); // poi提供的类   工作簿
            HSSFSheet sheet = wk.getSheetAt(0);//获取一片？  工作表
            HSSFCell cell = null;
            for(int rowIndex=startRow; rowIndex<=sheet.getLastRowNum(); rowIndex++){  //getLastRowNum 获取最后一行的行号
                HSSFRow row = sheet.getRow(rowIndex);  //获取行数据
                if(null==row){  //如果这行数据为空  继续
                    continue;
                }
                RowData rowData = new RowData(rowIndex+1);//起始是2  行数据存储对象初始化
                List<CellData> cells = new ArrayList<CellData>();//  单元格s 对象存储对象初始化
                for(int columnIndex=0; columnIndex<columnKey.length; columnIndex++){  // 列个数
                    String key = columnKey[columnIndex];
                    String cellValue = "";
                    cell = row.getCell(columnIndex); //获取独立单元格对象
                    if(null!=cell){
                        cell.setCellType(Cell.CELL_TYPE_STRING);//设置对象数据类型为String
                        cellValue = cell.getStringCellValue(); // 获取数据   是String 因为上面转换了
                    }
                    if(!StringUtils.isBlank(cellValue)){
                        cellValue = cellValue.trim(); //非空进行剪切
                    }
                    CellData cellData = new CellData(columnIndex+1, cellValue, key);//单元格数据对象实例化     参数有： 的列的位置   值   对应的列明
                    cells.add(cellData);  //添加到单元格s对象中去
                }
                rowData.setCells(cells);  // 行数据添加   cells  值
                rows.add(rowData); // 行数据列表 添加 行数据
            }
            //关闭输入流
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;  //rows 可以说是 内存中的转化好的表格格式正确文件内容对象
    }

    /**
     * 解析2007 excel文件
     * @param file excel文件
     * @param startRow 起始行 0为第一行
     * @param columnKey  每列对应的key值
     * @return
     */
    @SuppressWarnings("deprecation")
	private static List<RowData> parse2007Excel(File file, int startRow, String[] columnKey){
        List<RowData> rows = new ArrayList<RowData>();
        try {
            String fileName = file.getName();
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFCell cell = null;
            for(int rowIndex=startRow; rowIndex<=sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if(null==row){
                    continue;
                }
                RowData rowData = new RowData(rowIndex+1);
                List<CellData> cells = new ArrayList<CellData>();
                for(int columnIndex=0; columnIndex<columnKey.length; columnIndex++){
                    String key = columnKey[columnIndex];
                    String cellValue = "";
                    cell = row.getCell(columnIndex);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellValue = cell.getStringCellValue();
                    if(!StringUtils.isBlank(cellValue)){
                        cellValue = cellValue.trim();
                    }

                    CellData cellData = new CellData(columnIndex+1, cellValue, key);
                    cells.add(cellData);
                }
                rowData.setCells(cells);
                rows.add(rowData);
            }
            //关闭输入流
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}