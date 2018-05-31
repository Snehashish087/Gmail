package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.gmail.generic.BasePage;

public class GmailPage extends BasePage 
{
	Logger log = Logger.getLogger("GmailPage");
	
	@FindBy(xpath="//a[.='Sign In']")
	private WebElement signIn;
	
	public GmailPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public void click_on_sign_in_button() throws InterruptedException
	{
		log.info("Click on sign in button");
		signIn.click();
	}
	
}
