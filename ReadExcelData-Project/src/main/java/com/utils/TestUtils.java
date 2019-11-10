package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.List;

import java.util.Random;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.ArrayUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;





/*
 * All the utilities needed for the framework is placed in this class including excel utilities, screenshot capture.
 * We have used method overloading concept in getCellContent Method.
 */
public class TestUtils {

public static FileInputStream fs;
public static XSSFWorkbook workbook;
public static XSSFSheet sheet;
public static List<String> testCases= new ArrayList<String>();
public static List<String> runStatus= new ArrayList<String>();
public static List<String> testDescription= new ArrayList<String>();
public static List<String> invocationCount= new ArrayList<String>();
public static List<String> priority= new ArrayList<String>();
public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();



/*
* Reads the data from the excel sheet and store the values in respective lists which will be used in annotation transformer class
*/





/*
* public static Object getRowNumForTestCase(String testcasename) { Object
* a=null; for(Map.Entry m:rowAndTestCaseMap.entrySet()){
* if(m.getValue().toString().equalsIgnoreCase(testcasename)) { a= m.getKey(); }
* } return a; }
*/

/*
* Takes rowname and sheetname as parameter
* return row number based of rowname
*/
public static int getRowNumForRowName(String sheetname,String rowName) {
int rownum=0;
sheet=workbook.getSheet(sheetname);
for(int i=1;i<=getLastRowNum(sheetname);i++) {
if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
rownum=i;
break;
}
}

return rownum;
}

/*
* Takes columnname and sheetname as parameter
* return column number based of columnheader
*/

public static int getColumnNumForColumnName(String sheetname, String columnname) {
int colnum=0;
sheet=workbook.getSheet(sheetname);
for(int i=0;i<getLastColumnNum(sheetname, 0);i++) {
if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
colnum=i;
break;
}
}

return colnum;

}


/*
* Takes sheetname as parameter
* return last row number of the sheet
*/
public static int getLastRowNum(String sheetname) {
return workbook.getSheet(sheetname).getLastRowNum();
}

/*
* Takes sheetname, row number as parameter
* return last cell number of the row
*/
public static int getLastColumnNum(String sheetname, int rownum) {
return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
}


/*
* Takes sheetname, row number, column number as parameter
* return cell value
*/
public static String getCellContent(String sheetname,int rownum,int colnum) {
sheet=workbook.getSheet(sheetname);
String temp = null;
int celltype=sheet.getRow(rownum).getCell(colnum).getCellType();
if(celltype==1) {
temp= sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();
}
else if(celltype==0||celltype==2) {
temp= Double.toString(sheet.getRow(rownum).getCell(colnum).getNumericCellValue());
}
System.out.println(temp);
return temp;

}
public static String getNumericCellContent(String sheetname,int rownum,int colnum) {
sheet=workbook.getSheet(sheetname);
String temp;
// if(sheet.getRow(rownum).getCell(colnum).getCellType()==0) {
temp= Double.toString(sheet.getRow(rownum).getCell(colnum).getNumericCellValue());
/* }
else {
temp= sheet.getRow(rownum).getCell(colnum).getStringCellValue();
}*/
return temp;
}

/*
* Takes sheetname, row number, column name as parameter
* return cell value
*/
public static String getCellContent(String sheetname,int rownum,String columnname) {
sheet=workbook.getSheet(sheetname);
return sheet.getRow(rownum).getCell(getColumnNumForColumnName(sheetname, columnname)).getStringCellValue().concat("").toString();

}

/*
* Takes sheetname, row name, column name as parameter
* return cell value
*/
public static String getCellContent(String sheetname,String rowname,String columnname) {
sheet=workbook.getSheet(sheetname);
int rownum=getRowNumForRowName(sheetname, rowname);
int colnum=getColumnNumForColumnName(sheetname, columnname);
return sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();

}



/*
* Takes screenshot
* Make sure parameter ScreenshotsRequired is Yes in TestRunDetails.properties
*
*/



/*
* Captures screenshot and returns the screenshot path
*/



/*
* Gives a base64 image which is used to append the screenshots in the extent report.
* Converting to base64 format avoids screenshots broken image if sent the exent report through email.
*/


/*
* Sends test results to the respective stakeholders
* Make sure to set the parameter SendExecutionResultsInEmail to Yes in TestRunDetails.properties
*/




/*
* Used to separate email list from the TestRunDetails.properties based on comma and return them as a String array.
*/


/*
*
* DataProvider method used to provide data for multiple iterations.
* Never try to use multiple iterations when the invocation count is greater than 1. It may result in adhoc results.
* As long as the first name of the TestData has the same test case name it will be treated as iteration.
*
*/


/*
* Used to return the rownumber of the test cases for multiple iterations.
* Suppose if testcase 1 is available in row 4 and 7 is test data , it return the arraylist with values 4,7
*/



@DataProvider(name="dataProviderForIterationsForRough")
public static Object[][] dataProviderForIterationsForRough(Method m) throws IOException{
fs=new FileInputStream("./src/test/resources/Data-Driven-Automation-Framework.xlsx");
workbook=new XSSFWorkbook(fs);
sheet=workbook.getSheet("ExpectedVactual");

return getDataForDataproviderForRough("./src/test/resources/Data-Driven-Automation-Framework.xlsx","ExpectedVactual",m.getName());
}

private static Object[][] getDataForDataproviderForRough(String testdataname, String sheetname, String testcasename) {

int totalcolumns=getLastColumnNum(sheetname, 0);
//ArrayList<Integer> rowscount=getNumberofIterationsForATestCase(sheetname, testcasename);
int rowscount=getLastRowNum(sheetname);
System.out.println(sheetname);
Object[][] b=new Object[rowscount][1];
Hashtable<String,String> table =null;
for(int i=3;i<=rowscount-1;i++) {
table=new Hashtable<String,String>();
for(int j=0;j<totalcolumns;j++){
System.out.println("i : "+i);
System.out.println("j : "+j);
if(j<2||j>28) {
table.put(getCellContent(sheetname, 2, j), getCellContent(sheetname, i, j));
b[i-3][0]=table;

}
//first element should be b[0][0]
//second element will be b[1][0]
}
}
return b;


}

@DataProvider(name="dataProviderForIterationsForColumns")
public static Object[][] dataProviderForIterationsForColumns(Method m) throws IOException{
fs=new FileInputStream("./src/test/resources/Data-Driven-Automation-Framework.xlsx");
workbook=new XSSFWorkbook(fs);
sheet=workbook.getSheet("ExpectedVactual");

return getDataForDataproviderForColumns("./src/test/resources/Data-Driven-Automation-Framework.xlsx","ExpectedVactual",m.getName());
}

private static Object[][] getDataForDataproviderForColumns(String testdataname, String sheetname, String testcasename) {

int totalcolumns=getLastColumnNum(sheetname, 0);
//ArrayList<Integer> rowscount=getNumberofIterationsForATestCase(sheetname, testcasename);
int rowscount=getLastRowNum(sheetname);
System.out.println(sheetname);
Object[][] b=new Object[totalcolumns][1];
Hashtable<String,String> table =null;
for(int i=28;i<=totalcolumns-1;i++) {
table=new Hashtable<String,String>();
for(int j=2;j<rowscount;j++){

table.put(getCellContent(sheetname, j, 0), getCellContent(sheetname, j, i));
b[i][0]=table;

}
}
return b;


}


}