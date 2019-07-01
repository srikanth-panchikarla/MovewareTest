package com.moveware.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {
	private static File file ;
	private static XSSFWorkbook wb =null;
	private static Map<String,Map> excelData = new HashMap<String, Map>();
	private static Map<String, String> map = new HashMap<String, String>();
	
	public static Map<String,Map> readExcel(String testDataPath, String sheetName) {
		try {
			//System.out.println("In ExcelReadWrite->readExcel() ");
			file = new File(testDataPath);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheet(sheetName);
			/*Iterator<Row> rows = sheet.iterator();
			Row r = rows.next();
			Iterator<Cell> cells = r.cellIterator();
			Cell c = cells.next();
			c = cells.next();*/
			int rowCount = sheet.getLastRowNum();
			//System.out.println(rowCount);
			int colCount = sheet.getRow(0).getLastCellNum();
			if (rowCount>0) {
				for (int i = 1; i<=rowCount;i++ ) {
					Row row = sheet.getRow(i);
					map  = new HashMap<String, String>();
					for (int j = 1; j<colCount; j++) {
						//System.out.println(sheet.getRow(0).getCell(j).toString()+" "+row.getCell(j).toString());
						//System.out.println(row.getCell(j).toString());
						map.put(sheet.getRow(0).getCell(j).toString(),row.getCell(j).toString());
					}
					excelData.put(sheet.getRow(i).getCell(0).toString(),map);
				}
			}
			/*System.out.println(excelData.size());  C:\MovewareAutomation\MovewareAutomation\src\InputData\ManageCodesNonUI.xlsx
			System.out.println(excelData);
			Set<String> keys = excelData.keySet();
			int i = 1;
			for(String key: keys) {
				System.out.println("Row: "+i);
				Set<String> mapKeys= excelData.get(key).keySet();
				System.out.println(mapKeys.size());
				for (String mapKey: mapKeys) {
					System.out.println(mapKey + "---"+excelData.get(key).get(mapKey));
				}
				i++;
			}*/
			wb.close();
			sheet = null;
			wb = null;
		}catch(InvalidFormatException |IOException ioe) {
			ioe.printStackTrace();
		}
		return excelData;
	}
}
