package com.cast.ObjectRepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

	WebDriver d;
	public Home(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}
	
	@FindBy (linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy (linkText = "Contacts")
	private WebElement contlink;
	
	@FindBy (linkText = "Campaigns")
	private WebElement cmplink;

	@FindBy (linkText = "More")
	private WebElement morelink;
	
	@FindBy (xpath = "//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement adminimg;
	
	@FindBy (linkText = "Sign Out")
	private WebElement signoutLnk;
	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContlink() {
		return contlink;
	}

	public WebElement getCmplink() {
		return cmplink;
	}

	public WebElement getMorelink() {
		return morelink;
	}

	public void navigateToCampaignsPage() {
            WebDriver d = null;
			Actions a = new Actions(d);
		  a.moveToElement(morelink).perform();
		cmplink.click();
	}
          	
     public void logout() {
    	       Actions a = new Actions(d);
    	       a.moveToElement(adminimg).perform();
     }
	
}