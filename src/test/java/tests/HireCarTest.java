package tests;

import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.HireCarTab;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HireCarTest extends TestConfig {

    @Test
    public void showCarList() {
        HireCarTab hireCarTab = mainPage.openHireCarTab();
        hireCarTab.searchCarDefaultDates("Warsaw Modlin");
        assertTrue(hireCarTab.isResultsContainerAvailable());
    }
}
