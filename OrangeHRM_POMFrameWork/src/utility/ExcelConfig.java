package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConfig {
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	
	public static void setExcelFile(String path) throws Exception{
		try{
			FileInputStream ExcelFile=new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		}
		catch(Exception e){
			//Log.error("class ExcelConfig | Method setExcelFile | Exception desc: "+e.getMessage());
			Log.info("class ExcelConfig | Method setExcelFile | Exception desc: "+e.getMessage());
			throw(e);
		}
	}
	
	public static String getCellData(int iRowNum, int iColNum, String sSheetName) throws Exception{
		ExcelWSheet=ExcelWBook.getSheet(sSheetName);
		try{
			cell=ExcelWSheet.getRow(iRowNum).getCell(iColNum);
			String sCellData=cell.getStringCellValue().trim();
			return sCellData;
		}catch(Exception e){
			//Log.error("class ExcelConfig | Method getCellData | Exception desc: "+e.getMessage());
			Log.info("class ExcelConfig | Method getCellData | Exception desc: "+e.getMessage());
			return "";
		}
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static void setCellData(String sResult, int iRowNum, int iColNum, String sSheetName) throws Exception{
		ExcelWSheet=ExcelWBook.getSheet(sSheetName);
		try{
			row=ExcelWSheet.getRow(iRowNum);
			cell=row.getCell(iColNum, row.RETURN_BLANK_AS_NULL);
			if(cell==null){
				cell=row.createCell(iColNum);
				cell.setCellValue(sResult);
			}else{
				cell.setCellValue(sResult);
			}
			FileOutputStream fileOut=new FileOutputStream(Constant.path_TestData + Constant.file_TestData);
			ExcelWBook.write(fileOut);
			//fileOut.flush();
			fileOut.close();
			ExcelWBook =new XSSFWorkbook(new FileInputStream(Constant.path_TestData + Constant.file_TestData));
		}catch(Exception e){
			//Log.logConfig(Level.INFO, 50000, "class ExcelConfig| Method setCellData |Exception desc: " +e.getMessage(), null);
			Log.info("class ExcelConfig| Method setCellData |Exception desc: " +e.getMessage());
			throw(e);
		}
	}
	public static int getRowContains(String sTestName, int iColNum, String sSheetName) throws Exception{
		int i;
		ExcelWSheet=ExcelWBook.getSheet(sSheetName);
		try{
			int iRowCount=ExcelConfig.getRowUsed(sSheetName);
			for(i=0;i<iRowCount;i++){
				if(ExcelConfig.getCellData(i, iColNum, sSheetName).equalsIgnoreCase(sTestName)){
					break;
				}
			}
			return i;
		}catch(Exception e){
			//Log.error("Class ExcelConfig | Method getRowContains |Exception desc:"+e.getMessage());
			Log.info("Class ExcelConfig | Method getRowContains |Exception desc:"+e.getMessage());
			throw(e);
		}
	}
	public static int getRowUsed(String sSheetName) throws Exception{
		ExcelWSheet=ExcelWBook.getSheet(sSheetName);
		try{
			int iRowCount=ExcelWSheet.getLastRowNum();
			return iRowCount;
		}catch(Exception e){
			//Log.error("Class ExcelConfig | Method getRowUsed | Exception desc: "+e.getMessage());
			Log.info("Class ExcelConfig | Method getRowUsed | Exception desc: "+e.getMessage());
			throw(e);
		}
	}
	public static int getTestCount(String sSheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
		try{
			for(int i=iTestCaseStart;i<=ExcelConfig.getRowUsed(sSheetName);i++){
				if(!sTestCaseID.equals(ExcelConfig.getCellData(i, Constant.col_TestID, sSheetName))){
					int number=i;
					return number;
				}
			}
			ExcelWSheet=ExcelWBook.getSheet(sSheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		}catch(Exception e){
			//Log.error("Class ExcelConfig | Method getTestCount | Exception desc: "+e.getMessage());
			Log.info("Class ExcelConfig | Method getTestCount | Exception desc: "+e.getMessage());
			//throw(e);
			return 0;
		}
	}

}
