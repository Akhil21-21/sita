package com.cast.ObjectRepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	WebDriver d;
	public OrganizationPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}
	
		@FindBy (xpath = "//img[@title='Create Organization...']") 
		private WebElement CreateNewOrgBtn;
	
		public WebElement getCreateNewOrgBtn() {
			return CreateNewOrgBtn;
		}
		
		
		
		
		
	}


