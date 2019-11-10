package com.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.Hashtable;
import java.util.Set;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.report.ExtentReport;
import com.utils.TestUtils;

public class DataDrivenExcel {
	
	@BeforeSuite
	public void setUp() {
	ExtentReport.initialize();
	}
	@BeforeMethod
	public void beforeMethod(){

	}

	@Test(dataProviderClass=TestUtils.class,dataProvider="dataProviderForIterationsForRough")
	public void test4(Hashtable<String,String> data) {

	ExtentReport.loggertest=ExtentReport.report.startTest(data.get("TestCaseId")+" "+data.get("Test Case Description"));


	Set<String> keys= data.keySet();
	for(String key:keys) {
	if(!key.contains("Test")) {
	if(!(data.get(key).equals("0.00")||data.get(key).equals("0.0"))) {
	ExtentReport.loggertest.log(LogStatus.FAIL, "Data Not Matched with column:"+key +"and value : "+data.get(key));
	}
	else {
	ExtentReport.loggertest.log(LogStatus.PASS,"Data Matched "+key);
	}
	}
	}

	ExtentReport.report.endTest(ExtentReport.loggertest);

	}
	/*
	*
	*
	*/

	@Test(dataProviderClass=TestUtils.class,dataProvider="dataProviderForIterationsForColumns",alwaysRun=true)
	public void test5(Hashtable<String,String> data) {

	ExtentReport.loggertest=ExtentReport.report.startTest(data.get("TestCaseId"));


	Set<String> keys= data.keySet();
	for(String key:keys) {
	if(!key.contains("Test")) {
	if(!(data.get(key).equals("0.00")||data.get(key).equals("0.0"))){
	ExtentReport.loggertest.log(LogStatus.FAIL, "Test Case failed is: "+key+ "and value difference is"+data.get(key));
	}
	else {
	ExtentReport.loggertest.log(LogStatus.PASS,"Test Case passed  :" +key);
	}
	}
	}
	ExtentReport.report.endTest(ExtentReport.loggertest);

	}


	@AfterSuite
	public void wrapUp() {
	ExtentReport.report.flush();
	ExtentReport.report.close();

	}
	}



