package com.gmail.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gmail.generic.BasePage;
import com.gmail.generic.GenericUtility;

public class NewMessagePage extends BasePage
{
	Logger log=Logger.getLogger("Message Content");
	@FindBy(xpath=".//textarea[contains(@aria-label, 'To')]")
	private WebElement recipients;
	@FindBy(xpath="//input[@placeholder='Subject']")
	private WebElement subject;
	@FindBy(xpath="(.//*[@aria-label='Message Body'])[2]")
	private WebElement bodyElements;
	@FindBy(xpath="(//div[.='Send'])[2]")
	private WebElement sendBtn;
	@FindBy(xpath="//img[@data-tooltip='Save & Close']")
	private WebElement closeBtn;
	public NewMessagePage(WebDriver driver)
	
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public void enter_recipients_mail_id(String recipient_mail_id)
	{
		
		log.info("Enter the recipient's mail ID");
		
		recipients.sendKeys(recipient_mail_id);
		GenericUtility.waitForMilliSeconds(2000);
		
	}
	public void enter_mail_subject(String recipient_subject)
	{
		log.info("Entering the mail subject");
		subject.sendKeys(recipient_subject);
		GenericUtility.waitForMilliSeconds(2000);
	}
	public void enter_body_elements(String body)
	{
		log.info("Enter the body of the mail.");
		bodyElements.sendKeys(body);
		GenericUtility.waitForMilliSeconds(8000);
	}
	public void click_on_submit()
	{
			log.info("Click on Sub");
			sendBtn.click();
	}
	public void save_as_draft()
	{
		closeBtn.click();
		GenericUtility.waitForMilliSeconds(2000);
	}
	
	
	

}
