package com.gmail.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gmail.generic.ExcelData;
import com.gmail.generic.BasePage;
import com.gmail.generic.BaseTest;
import com.gmail.generic.ConfigFileReader;
import com.gmail.generic.GenericUtility;
import com.gmail.pages.GmailHomePage;
import com.gmail.pages.GmailPage;
import com.gmail.pages.GmailPasswordPage;
import com.gmail.pages.GmailUserNamePage;
import com.gmail.pages.NewMessagePage;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.gmail.generic.TestListeners.class)
public class MailTest extends BaseTest
{
	//Logger instance
	Logger log=Logger.getLogger("check_mail_service");
	
	
	//Fetch contents from config file
	ConfigFileReader read = new ConfigFileReader();
	String username = read.get_username();
	String password = read.get_password();
	
	//Path for the excel files for test data and data engine
	String GMAIL_TEST_DATA_PATH = "./test-data/gmail_test_data.xlsx";
	String GMAIL_DATA_ENGINE = "./data-engine/gmail_data_engine.xlsx";
	
	
	
	@Test
	public void check_mail_scenario() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		logger=extent.startTest("Verify the scenarios in gmail for draft and unread modules", "As a user, I should be able to login to the gmail account with username and password"
				+ "and if I try to compose a mail and do not send, the mail should automatically be added in my draft. I should also be able to view my subject and the sender's mail ID for my unread mails.");

		
		//Object repository//
		GmailPage gmail = new GmailPage(driver);
		GmailUserNamePage login_username = new GmailUserNamePage(driver);
		GmailPasswordPage login_password = new GmailPasswordPage(driver);
		GmailHomePage homePage = new GmailHomePage(driver);
		NewMessagePage message = new NewMessagePage(driver);
		
		//Compose mail email extractor
		String draftEmailID =  ExcelData.getData(GMAIL_TEST_DATA_PATH, "TestData", 1, 1);
		
		
		// Iteration starting after navigating to the URL 
		for(int i = 3; i<=13; i++)
		{
			String sActionKeyword = ExcelData.getData(GMAIL_DATA_ENGINE, "TestSteps", i, 5);
			if(sActionKeyword.equalsIgnoreCase("click_on_sign_in_button"))
			{
				
				log.info(" Step 1: Log in to the gmail account using email ID and password");
				gmail.click_on_sign_in_button();
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_email_id"))
			{
				log.info("Step 2: The email and password should be read from a config file");
				login_username.enter_email_id(username);
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_password"))
			{
				login_password.enter_password(password);
				Thread.sleep(9000);
			}
			else if(sActionKeyword.equalsIgnoreCase("click_on_compose_btn"))
			{
				log.info("Click on compose button");
				homePage.click_on_compose_btn();
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_recipients_mail_id"))
			{
				log.info("Step 3: Compose a new mail and save the same to draft.");
				message.enter_recipients_mail_id(draftEmailID);
			}
			else if(sActionKeyword.equalsIgnoreCase("save_as_draft"))
			{
				message.save_as_draft();
				Thread.sleep(6000);
			}
			else if(sActionKeyword.equalsIgnoreCase("get_unread_message_count"))
			{
				log.info("Step 4: Print the unread email count in inbox");
				homePage.click_on_inbox_filters();
				homePage.click_on_unread_option();
				try
				{
					String count= homePage.get_unread_message_count();
					logger.log(LogStatus.PASS, "Count from unread message: "+count);
				}
				catch(Exception e)
				{
					logger.log(LogStatus.FAIL, "Failed to retrieve the count."); 
				}
				
				
				
			}
			else if(sActionKeyword.equalsIgnoreCase("click_on_inbox_filters"))
			{
				homePage.click_on_inbox_filters();
			}
			else if(sActionKeyword.equalsIgnoreCase("click_on_unread_option"))
			{
				homePage.click_on_unread_option();
			}
			else if(sActionKeyword.equalsIgnoreCase("get_text_from_first_mail_subject"))
			{
				log.info("Step 5: Print the subject line of the first email");
				try
				{
					String subject = homePage.get_text_from_first_mail_subject();
					logger.log(LogStatus.PASS, "Text from first mail subject: "+subject);
				}
				catch(Exception e)
				{
					logger.log(LogStatus.FAIL, "Failed to retrieve the subject from the first unread mail"); 
				}
			}
			else if(sActionKeyword.equalsIgnoreCase("get_mailID_from_first_sender"))
			{
				log.info("Step 6: Print the Sender email address of the first unread email");
				try
				{
					String mailID = homePage.get_mailID_from_first_sender();
					logger.log(LogStatus.PASS, "First unread sender's mail id: "+mailID);
				}
				catch(Exception e)
				{
					logger.log(LogStatus.FAIL, "Failed to retrieve the mailID for the first unread sender's mail"); 
				}
			}
		}
	}
}

				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
	

