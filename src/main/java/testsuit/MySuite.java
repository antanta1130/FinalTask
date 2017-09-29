package testsuit;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;

public class MySuite {
    @ClassRule
    public static final ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            FunctionalTest.initSuit();
        };

        @Override
        protected void after() {
            FunctionalTest.testAfterSuite();
        };
    };
}
