package com.selenium.util;

import java.util.concurrent.TimeUnit;


import com.selenium.base.TestBase;

public class WaitHelper 
{	
	public static void setImplicitWait(long timeout) {
		TestBase.driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}
	
	public static void setPageLoadTimeout(long timeout) {
		TestBase.driver.manage().timeouts().pageLoadTimeout(timeout,TimeUnit.SECONDS);
	}
	

}