package com.selenium.base;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.selenium.util.WaitHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

    public static WebDriver driver;
//    public static Properties prop;
    static Logger logger;

    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    
    public TestBase() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resource/log4j.properties");
		logger = Logger.getLogger(TestBase.class.getName());
    }

    public static void initialization() throws IOException {
    	
    	String URL = AppConfig.getURL();
    	String browserName = AppConfig.getBrowser();
        String environment = AppConfig.getEnvironment();
        String headless = System.getProperty("HEADLESS");
        
        if(headless!=null) {
        	headless = System.getProperty("HEADLESS");
        }else {
        	headless = "true";
        }
    	
    	System.out.println("*******************Start:System Args**********************");
    	System.out.println("ENV :"+environment);
    	System.out.println("BROWSER :"+browserName);
    	System.out.println("URL :"+URL);
    	System.out.println("HEADLESS :"+headless);
    	System.out.println("********************End:System Args***********************");
    
        
        switch (environment) {
        
            case "local":
                switch (browserName) {
                    case "chrome":
                        logger.info("Starting tests on chrome browser.");
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        if(headless.equalsIgnoreCase("true")) {
                        	options.addArguments("--headless");
                        	options.addArguments("--no-sandbox");
                            System.setProperty("webdriver.chrome.args", "--disable-logging");
                            System.setProperty("webdriver.chrome.silentOutput", "true");
                            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                            options.addArguments("disable-infobars"); // disabling infobars
                            options.addArguments("--disable-extensions"); // disabling extensions
                            options.addArguments("--disable-gpu"); // applicable to windows os only
                            options.addArguments("window-size=1024,768"); // Bypass OS security model
                            //options.setCapability("chrome.verbose", false); //disable logging
                        }
                        driver = new ChromeDriver(options);
                        break;

                    case "firefox":
                        logger.info("Starting tests on firefox browser.");
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                        
                    case "ie":
                    	logger.info("Starting tests on IE browser.");
                    	WebDriverManager.iedriver().setup();
        				InternetExplorerOptions opt = new InternetExplorerOptions();
        				opt.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        				opt.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        				opt.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 10000);
        				opt.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
        				opt.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        				opt.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
        		//		opt.setCapability(InternetExplorerDriver.NATIVE_EVENTS,true);
        		//		opt.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.ACCEPT);
        				opt.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        		//		opt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,false);
        				opt.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
        		//		opt.setCapability(CapabilityType.PROXY,Proxy.NO_PROXY);
        				driver = new InternetExplorerDriver(opt);
        				
                    default:
                        logger.info("Browser not defined.");
                        break;
                }
                break;

            case "grid":
                switch (browserName) {
                    case "chrome":
                        logger.info("Grid:Starting tests on chrome browser.");
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--start-maximized");
                        options.addArguments("--disable-extensions");
                        options.setCapability("platform", "LINUX");
                        driver = new RemoteWebDriver(new URL(AppConfig.getGridServer()), options);
                        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                        logger.info("Grid:Setting Up Selenium Grid.");
                        break;

                    case "firefox":
                        logger.info("Grid:Starting tests on firefox browser.");
                        FirefoxOptions FFoptions = new FirefoxOptions();
                        driver = new RemoteWebDriver(new URL(AppConfig.getGridServer()), FFoptions);   
                        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                        break;
                     
                    case "ie":
                       logger.info("Grid:Starting tests on ie browser.");
                       InternetExplorerOptions ieoptions = new InternetExplorerOptions();
                       driver = new RemoteWebDriver(new URL(AppConfig.getGridServer()), ieoptions);   
                       driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                       break;

                    default:
                        logger.info("Browser is not defined, Not able to set Driver object.");
                        break;
                }
                break;

            default:
                logger.info("No environment defined.");
                break;
        }

        driver.manage().window().maximize();
		WaitHelper.setPageLoadTimeout(Long.parseLong(AppConfig.getTimeout()));
//        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("Timeout")), TimeUnit.SECONDS);
        driver.get(URL);
        logger.info("Driver initialization completed.");
    }

}
