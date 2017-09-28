package tests;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class DraggablePageTests extends FunctionalTest {

    private static final String DRAGGABLE_HEADING = "Draggable";
    private HomePage homePage;

    @Test
    public void verifyDraggablePageHeading() {
        homePage.open().clickOnDraggableLink();
        Assert.assertEquals("Draggable page heading is wrong!", DRAGGABLE_HEADING, homePage.getPageHeader());
    }
}
