package com.cast.ObjectRepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver d;
	public Loginpage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}
	
		// 1 - Create Seperate Java Class
        // 2 - Object Creation - using@findBy
		// Object Initialization
	// Object Encapsulation
	
		@FindBy (name = "user_name")
		private WebElement usernameEdt;
		
		
		@FindBy (name = "user_password")
		private WebElement passwordEdt;
		
		
		@FindBy (id = "submitButton")
		private WebElement loginbtn;

		public WebElement getUsernameEdt() {
			return usernameEdt;
		}

		public WebElement getPasswordEdt() {
			return passwordEdt;
		}

		public WebElement getLoginbtn() {
			return loginbtn;
		}
		
		// we can provide action
		public void LoginToApp(String username,String password)
		{
			d.manage().window().maximize();
			usernameEdt.sendKeys("admin");
			passwordEdt.sendKeys("admin");
			loginbtn.click();
		}
		
		
		
		

}
