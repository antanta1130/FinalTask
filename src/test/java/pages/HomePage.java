package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
    private static final String URL = "http://demoqa.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // private static final Logger log =
    // LoggerFactory.getLogger(AboutUsPage.class);

    @FindBy(partialLinkText = "About us")
    private WebElement aboutUsLink;

    @FindBy(linkText = "Contact")
    private WebElement contactUsLink;

    @FindBy(partialLinkText = "Draggable")
    private WebElement draggableLink;

    @FindBy(partialLinkText = "Services")
    private WebElement servicesLink;

    @FindBy(linkText = "Tabs")
    private WebElement tabsLink;

    @FindBy(xpath = "//h1")
    private WebElement pageHeader;

    @FindBy(xpath = "//ul[@id='menu-primary-menu']/li/a")
    private List<WebElement> topMenuLinks;

    public HomePage open() {
        open(URL);
        return this;
    }

    public void clickOnAboutUsLink() {
        aboutUsLink.click();
    }

    public void clickOnContactUsLink() {
        contactUsLink.click();
    }

    public String getPageHeader() {
        return pageHeader.getText();
    }

    public void clickOnDraggableLink() {
        draggableLink.click();
    }

    public List<String> getTopMenuLinks() {
        List<String> linkNames = new ArrayList<String>();
        for (WebElement link : topMenuLinks) {
            linkNames.add(link.getText());
        }
        return linkNames;
    }

    public void clickOnServicesLink() {
        servicesLink.click();

    }

    public void clickOnTabsLink() {
        driver.findElement(By.linkText("Tabs")).click();
    }

}
