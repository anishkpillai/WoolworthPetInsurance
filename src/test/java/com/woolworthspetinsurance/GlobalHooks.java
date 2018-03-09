package com.woolworthspetinsurance;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;



import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class GlobalHooks {
	
    private static boolean globalInit = false;
    public static WebDriver globalDriver;
    

    /*
	 * this thread is used to quit the driver before the JVM shuts down
	 */
	private static final Thread shutdownThread = new Thread() {
		@Override
		public void run() {
			globalDriver.quit();
		}
	};
	
    @Before
    public void beforeAll() {
        if(!globalInit) {
        	initializeDriver();
    		Runtime.getRuntime().addShutdownHook(shutdownThread);
        	globalInit = true;
        }
        globalDriver.manage().deleteAllCookies();
        
    }
    
    @After
    public void embedScreenshotOnFail(Scenario s) {
    	if (s.isFailed()) {
    	  try {
              byte[] screenshot = ((TakesScreenshot) globalDriver).getScreenshotAs(OutputType.BYTES);
              s.embed(screenshot, "image/png" );
              s.write("URL at failure: " + globalDriver.getCurrentUrl());
          } catch (WebDriverException wde) {
              s.write("Embed Failed " + wde.getMessage());
          } catch (ClassCastException cce) {
              cce.printStackTrace();
          }
    	}
    }

	private void initializeDriver() {
		globalDriver = new FirefoxDriver(); //command line property
		globalDriver.manage().window().maximize();
		globalDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		globalDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
}
