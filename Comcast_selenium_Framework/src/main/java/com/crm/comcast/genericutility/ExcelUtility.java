package com.crm.comcast.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Deepak
 *
 */
public class ExcelUtility {
	
	/**
	 * used to get the last used row number on specified Sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis  = new FileInputStream("./data/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
	
	/**
	 *  its used read the data from excel base don below arguments 
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @return Data
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetName , int rowNum, int celNum) throws Throwable {
		FileInputStream fis  = new FileInputStream("./data/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}

	/**
	 * used to write data back to Excel
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @param data
	 * @throws Throwable
	 */
	public void setDataExcel(String sheetName , int rowNum, int celNum ,String data) throws Throwable {
		FileInputStream fis  = new FileInputStream("./data/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(celNum);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./data/testdata.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
