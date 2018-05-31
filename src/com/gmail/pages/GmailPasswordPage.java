package com.gmail.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gmail.generic.BasePage;

public class GmailPasswordPage extends BasePage
{
	Logger log=Logger.getLogger("Login");
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	
	public GmailPasswordPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enter_password(String user_password)
	{
		log.info("Enter user password");
		password.sendKeys(user_password);
		password.sendKeys(Keys.ENTER);
	}
	
	

}
