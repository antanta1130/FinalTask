package tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pages.HomePage;
import testsuit.FunctionalTest;

public class HomePageTests extends FunctionalTest {
    List<String> expectedList = Arrays.asList("Home", "About us", "Services", "Demo", "Blog", "Contact");
    private HomePage homePage;

    @Test
    public void verifyTopMenuLinkNames() {
        homePage.open();
        Assert.assertEquals(homePage.getTopMenuLinks(), expectedList);
    }

}
