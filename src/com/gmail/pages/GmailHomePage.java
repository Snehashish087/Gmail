package com.gmail.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.gmail.generic.BasePage;
import com.gmail.generic.GenericUtility;
import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.description.type.TypeDescription.Generic;

public class GmailHomePage extends BasePage
{
	Logger log=Logger.getLogger("Home Page");
	@FindBy(xpath="(//div[contains(.,'COMPOSE')])[14]")
	private WebElement compose;
	@FindBy(xpath="//a[contains(.,'Drafts')]")
	private WebElement draft;
	@FindBy(xpath="//a[contains(.,'Inbox')]")
	private WebElement inbox_link;
	@FindBy(xpath="//div[@class='TN bzz aHS-bnt']//img")
	private WebElement inbox_filters;
	@FindBy(xpath="(//div[contains(.,'Unread first')])[15]") 
	private WebElement filter_options;
	@FindBy(css="div.xT>div.y6>span>b")
	private WebElement firstMailSubject;
	@FindBy(xpath="(//span[@class='zF'])[2]")
	private WebElement firstSender;
	@FindBy(css=".vta")
	private WebElement getMail;
	
	
	public GmailHomePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void click_on_compose_btn() throws InterruptedException
	{
		log.info("Wait for the compose element to be visible.");
		BasePage.waitTillVisibilityOfElement(compose);
		log.info("Click on compose button.");
		compose.click();
		Thread.sleep(2000);
	}
	
	public void click_on_draft() throws InterruptedException
	{
		
		draft.click();
		Thread.sleep(2000);
	}
	
	public String get_unread_message_count()
	{
		
		
		log.info("Getting the attribute value.");
		String attribute_value = inbox_link.getAttribute("aria-label");
		log.info("Retrieving the count");
		String count=GenericUtility.Separate(attribute_value);
		Reporter.log("Unread Messages in Inbox: "+count, true);
		return count;
	}
	public void click_on_inbox_filters()
	{
		GenericUtility.waitForMilliSeconds(3000);
		GenericUtility.mouseHover(inbox_link);
		GenericUtility.waitForMilliSeconds(3000);
		GenericUtility.mouseHoverAndClick(inbox_filters);
		GenericUtility.waitForMilliSeconds(3000);
		log.info("Click on inbox filter option");
		//inbox_filters.click();
		GenericUtility.waitForMilliSeconds(4000);
	}
	public void click_on_unread_option()
	{
		log.info("Click on unread option from the dropdown");
		GenericUtility.mouseHoverAndClick(filter_options);
		//filter_options.click();
		//GenericUtility.waitForMilliSeconds(9000);
	}
	public String get_text_from_first_mail_subject()
	{
		GenericUtility.waitForMilliSeconds(5000); 
		String mail_subject=firstMailSubject.getText();
		GenericUtility.waitForMilliSeconds(5000);
		Reporter.log("Subject: "+mail_subject,true);
		return mail_subject;
		
	}
	
	public String get_mailID_from_first_sender()
	{
		
		log.info("Getting the details of the mail ID from the first sender which is an unread mail.");
		GenericUtility.waitForMilliSeconds(5000);
		String mailID=firstSender.getAttribute("email");
		GenericUtility.waitForMilliSeconds(5000);
		Reporter.log("Details of the ID from the first unread mail sender: "+mailID,true);
		return mailID;
		
		
	}
	
	
	

}
