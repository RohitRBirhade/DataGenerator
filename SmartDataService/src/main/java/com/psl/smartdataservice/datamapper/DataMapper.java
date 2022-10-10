package com.psl.smartdataservice.datamapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**Data Writer Class
 * Writes data from list into ExalSheet
 * 
 */
public class DataMapper {
	String fileName = "D:\\Excel Demo\\GeneratedData.xlsx";
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
    XSSFSheet xssfSheet = xssfWorkbook.createSheet();
	
    /**Takes JSONArray as a parameters
     * Writes data into exalsheet 
     * @param json_ArrayList
     */
	public void jsonToExalWriter(JSONArray json_ArrayList) {
		List<String> l = (List<String>) json_ArrayList.get(0);
		for(int i=0;i<l.size();i++) {
			XSSFRow row =   xssfSheet.createRow((short)i);
			for(int j=0;j<json_ArrayList.size();j++) {
				List<String> l1 = (List<String>) json_ArrayList.get(j);
				row.createCell(j).setCellValue(l1.get(i));
			}
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			xssfWorkbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
