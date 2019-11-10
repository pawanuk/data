package com.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentReport {

	public static ExtentReports report=null;
	public static ExtentTest loggertest=null;
	public static String extentreportpath="";
	

	//To avoid external initialization
	private ExtentReport() {
		
		
		extentreportpath="./ExtentReports/Test Report.html";
		report=new ExtentReports(extentreportpath);
		report.loadConfig(new File("./src/test/resources/extentreport.xml"));
	}

	public static void initialize()
	{
		ExtentReport report=new ExtentReport();
	}

}
