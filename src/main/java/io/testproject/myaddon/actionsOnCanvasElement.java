package io.testproject.myaddon;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


@Action(name = "Canvas Element Actions")
public class actionsOnCanvasElement implements WebAction {

	@Parameter(description = "Canvas Element Dimensions", direction = ParameterDirection.OUTPUT)
    private String canvasElementDimensions;
	
	@Parameter(description = "Canvas Element X offset", direction = ParameterDirection.OUTPUT)
    private String canvasElementXOffset;
	
	@Parameter(description = "Canvas Element Y offset", direction = ParameterDirection.OUTPUT)
    private String canvasElementYOffset;
	
	@Parameter(description = "Canvas Element Xpath", direction = ParameterDirection.INPUT)
    private String canvasElementXpath;
    
          
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {
	String password = "test";
    	WebDriver driver = helper.getDriver();
    	Dimension bd = new Dimension(1296, 696);
    	driver.manage().window().setSize(bd);
    	canvasElementDimensions = driver.manage().window().getSize().toString();
    	WebElement canvas = driver.findElement(By.xpath(canvasElementXpath));
    	
    	Dimension canvasDimension = canvas.getSize();    
    	//canvasElementDimensions = canvasDimension.toString();
    	int canvas_center_x = canvasDimension.getWidth()/2;
    	int canvas_center_y = canvasDimension.getHeight()/2;
    	
    	int mouse_hover_x = (canvas_center_x/-9)*2;
    	canvasElementXOffset = String.valueOf(mouse_hover_x);
    	int mouse_hover_y = (canvas_center_y/-9)*2;
    	canvasElementYOffset = String.valueOf(mouse_hover_y);
    	
		/*
		 * Actions action = new Actions(driver); //action.moveToElement(canvas, 0, 0);
		 * action.moveToElement(canvas, mouse_hover_x, mouse_hover_y)
		 * //.clickAndHold(canvas); .perform();
		 */
    	
    	new Actions(driver).moveToElement(canvas, 0, 0).click().build().perform();
    	
    	return ExecutionResult.PASSED;    
    }
    
   
}
