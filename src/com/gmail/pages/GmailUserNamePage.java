package com.gmail.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import com.gmail.generic.BasePage;

public class GmailUserNamePage extends BasePage 
{
	
	
	//Call logger
	Logger log = Logger.getLogger("Login Page");
	
	@FindBy(xpath="//input[@id='identifierId']")
	private WebElement email;
	
	
	
	
	public GmailUserNamePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public void enter_email_id(String user_emailID) throws InterruptedException
	{	
		log.info("Entering the user's email ID");
		email.sendKeys(user_emailID);
		log.info("Clicking on enter");
		email.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	

}
