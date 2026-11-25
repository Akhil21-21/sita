package com.cast.ObjectRepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNeworganization {

	WebDriver d;
	public CreateNeworganization(WebDriver d) {
		this.d=d;
		PageFactory.initElements(d,this);
	}
	
	@FindBy (name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy (xpath = "//td[@class='detailedViewHeader']/parent::tr/preceding-sibling::tr//input[@title='Save [Alt+S]']")
   private WebElement saveBtn;

	@FindBy (name = "industry")
	private WebElement industDP;
	
	
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg1(String orgname) {
		OrgNameEdt.sendKeys(orgname); saveBtn.click();
	}

	public void createOrg(String orgname , String industry) {
		OrgNameEdt.sendKeys(orgname); 
		Select sel = new Select(industDP);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}

	
	
	
	
	
	
	
	
	
}
	
	
	
	

