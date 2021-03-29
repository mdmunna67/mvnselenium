package com.selenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppConfig 
{
	static File appFile = new File(System.getProperty("user.dir") + "/src/main/java/com/selenium/config/config.properties");
	static FileInputStream fis;
	static Properties prop = new Properties();
	
	
	public static String getEnvironment() 
	{
		String Env = System.getProperty("ENV");
		if(Env!=null) {
			return Env;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("Environment");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
	}
	
	
	
	public static String getURL() 
	{
		String URL = System.getProperty("URL");
		if(URL!=null) {
			return URL;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("Url");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
		
	}
	
	public static String getBrowser() 
	{
		String Browser = System.getProperty("BROWSER");
		if(Browser!=null) {
			return Browser;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("Browser");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
	}
	
	public static String getTestDataExcelFileName() 
	{
		String TestDataExcelFileName = System.getProperty("EXCELNAME");
		if(TestDataExcelFileName!=null) {
			return TestDataExcelFileName;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("TestDataExcelFileName");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
	}
	
	public static String getRunModeSheetName() 
	{
		try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("RunModeSheetName");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
	}
	
	public static String getTestDataSheetName()
	{
		String TDSHEETNAME = System.getProperty("TDSHEETNAME");
		if(TDSHEETNAME!=null) {
			return TDSHEETNAME;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("TestDataSheetName");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
	}
	
	
	public static String getGridServer() 
	{
		String GRIDURL = System.getProperty("GRIDURL");
		if(GRIDURL!=null) {
			return GRIDURL;
		}else {
			try
			{
				fis = new FileInputStream(appFile);
				prop = new Properties();
				prop.load(fis);
				
				return prop.getProperty("GridServer");
			}
			catch(Exception e){ e.printStackTrace();}
			return null;
		}
	}
	
	public static String getTimeout() 
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("Timeout");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}

	public static String getTCIDColumn()
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("TCIDColumn");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
	public static String getRunmodeColumn()
	{
		try
		{
			fis = new FileInputStream(appFile);
			prop = new Properties();
			prop.load(fis);
			
			return prop.getProperty("RunmodeColumn");
		}
		catch(Exception e){ e.printStackTrace();}
		return null;
	}
	
}