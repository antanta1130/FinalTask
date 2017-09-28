package tests;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class ContactUsPageTests extends FunctionalTest {

    private static final String CONTACT_US_HEADING = "Contact";
    private HomePage homePage;

    @Test
    public void verifyContactUsPageHeading() {
        homePage.open().clickOnContactUsLink();
        Assert.assertEquals("Contact Us page heading is wrong!", CONTACT_US_HEADING, homePage.getPageHeader());
    }

}
