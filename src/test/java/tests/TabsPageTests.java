package tests;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class TabsPageTests extends FunctionalTest {

    private static final String TABS_HEADING = "Tabs";
    private HomePage homePage;

    @Test
    public void verifyTabsPageHeading() {
        homePage.open().clickOnTabsLink();
        Assert.assertEquals("Blog page heading is wrong!", TABS_HEADING, homePage.getPageHeader());
    }
}
