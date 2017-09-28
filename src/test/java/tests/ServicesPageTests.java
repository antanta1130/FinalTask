package tests;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class ServicesPageTests extends FunctionalTest {

    private static final String SERVICES_HEADING = "Services";

    private HomePage homePage;

    @Test
    public void verifyServicesPageHeading() {
        homePage.open().clickOnServicesLink();
        Assert.assertEquals("Services page heading is wrong!", SERVICES_HEADING, homePage.getPageHeader());
    }
}
