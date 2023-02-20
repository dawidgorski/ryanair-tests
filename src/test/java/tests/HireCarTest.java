package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HireCarTest extends TestConfig {

    @Test
    public void showCarList() {
        boolean isContainerVisible = mainPage
                .openHireCarTab()
                .searchCarDefaultDates("Warsaw Modlin")
                .isResultsContainerAvailable();
        assertTrue(isContainerVisible);
    }
}
