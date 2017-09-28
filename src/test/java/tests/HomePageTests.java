package tests;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import pages.HomePage;
import testsuit.FunctionalTest;
import utils.DataUtils;

@RunWith(value = Parameterized.class)
public class HomePageTests extends FunctionalTest {
    private HomePage homePage;

    @Parameter
    public String expected;

    @Parameters
    public static List<String> data() {
        return DataUtils.getData("topMenuLinks.csv");
    }

    @Test
    public void verifyTopMenuLinkNames() {
        homePage.open();
        Assert.assertThat(String.format("%s Top Menu link is not present!", expected),
                homePage.getTopMenuLinks(), Matchers.hasItem(expected));
    }

}
