package testsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.AboutUsPageTests;
import tests.ContactUsPageTests;
import tests.DraggablePageTests;
import tests.HomePageTests;
import tests.ServicesPageTests;
import tests.TabsPageTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AboutUsPageTests.class,
        DraggablePageTests.class,
        HomePageTests.class,
        ServicesPageTests.class,
        TabsPageTests.class,
        ContactUsPageTests.class
})

public class MyTestSuite extends MySuite {
}