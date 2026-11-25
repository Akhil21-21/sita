package com.cast.ObjectRepository.utility;

import java.net.http.WebSocket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver d;
	public OrganizationInfoPage(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}

		@FindBy(className = "dvHeaderText")
		private WebElement headermsg;
	
		
		public WebElement getHeadermsg() {
			return headermsg;
		}



		
		

}
