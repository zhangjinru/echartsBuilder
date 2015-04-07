package com.echartsBuilder.common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChartChangeData implements IChartChangeData {

	@Override
	public List<Map> getData(InputStream ins) throws Exception {
		List<Map> list = new ArrayList<Map>(); //存放excel的data 每个list是sheet
		//打开XSSFWorkbook
		XSSFWorkbook wb = new XSSFWorkbook(ins);//获取HSSWorkbook
		int sheetsNum = wb.getNumberOfSheets();//excel sheet
	 	//解析sheet的数据      
		for(int i = 0 ; i < sheetsNum ; i++){
			Map map = new HashMap();
			int columnNum = 0;
			XSSFSheet sh = wb.getSheetAt(i);//得到sheet
			//解析行
			int rowNum = 0;
			rowNum = sh.getLastRowNum() + 1;
			for(int j = 0 ; j <= sh.getLastRowNum() ; j++){
				XSSFRow row = sh.getRow(j);
				if(row == null){
					rowNum --;
					continue;
				}
				//解析列
				for(int z = 0; z < row.getLastCellNum(); z++){
					XSSFCell cell = row.getCell(z);
					if(cell != null){
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							 map.put(j + "" + z, cell.getNumericCellValue());
						}
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							 map.put(j + "" + z, cell.getStringCellValue());
						}
					}
				}
				columnNum = row.getLastCellNum();
			}
			if(sh.getLastRowNum() != 0 || columnNum != 0){
				map.put("rowNum" ,rowNum);
				map.put("columnNum", columnNum);
				list.add(map);
			}
		}
		return list;
	}

	
	/**
	 * 判断是否是合并单元格
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private int  isMergedRow(Sheet sheet,int row ,int column) {  
		  int sheetMergeCount = sheet.getNumMergedRegions();  
		  for (int i = 0; i < sheetMergeCount; i++) {  
		CellRangeAddress range = sheet.getMergedRegion(i);  
		int firstColumn = range.getFirstColumn();  
		int lastColumn = range.getLastColumn();  
		int firstRow = range.getFirstRow();  
		int lastRow = range.getLastRow();  
		if(row == firstRow && row == lastRow){  
		if(column >= firstColumn && column <= lastColumn){  
		return lastColumn - firstColumn;
		}  
		}  
		  }  
		  return -1;  
		}


	@Override
	public List<Map> getDataMerge(InputStream ins) throws Exception {
		List<Map> list = new ArrayList<Map>(); //存放excel的data 每个list是sheet
		//打开XSSFWorkbook
		XSSFWorkbook wb = new XSSFWorkbook(ins);//获取HSSWorkbook
		int sheetsNum = wb.getNumberOfSheets();//excel sheet
	 	//解析sheet的数据      
		for(int i = 0 ; i < sheetsNum ; i++){
			Map map = new HashMap();
			int columnNum = 0;
			XSSFSheet sh = wb.getSheetAt(i);//得到sheet
			//解析行
			int rowNum = 0;
			rowNum = sh.getLastRowNum() + 1;
			for(int j = 0 ; j <= sh.getLastRowNum() ; j++){
				XSSFRow row = sh.getRow(j);
				if(row == null){
					rowNum --;
					continue;
				}
				//解析列
				for(int z = 0; z < row.getLastCellNum(); z++){
					XSSFCell cell = row.getCell(z);
					if(cell != null){
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							 map.put(j + "" + z, cell.getNumericCellValue());
						}
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							 map.put(j + "" + z, cell.getStringCellValue());
						}
					}
				}
				columnNum = row.getLastCellNum();
			}
			if(sh.getLastRowNum() != 0 || columnNum != 0){
				map.put("rowNum" ,rowNum);
				map.put("columnNum", columnNum);
				list.add(map);
			}
		}
		return list;
	}  
}
