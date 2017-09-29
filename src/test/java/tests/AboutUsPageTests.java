package tests;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class AboutUsPageTests extends FunctionalTest {

    private static final String ABOUT_US_HEADING = "About us";

    private HomePage homePage;

    @Test
    public void verifyAboutUsHeading() {
        homePage.open().clickOnAboutUsLink();
        Assert.assertEquals("About Us page heading is wrong!", ABOUT_US_HEADING, homePage.getPageHeader());
    }
}
