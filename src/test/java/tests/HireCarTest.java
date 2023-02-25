package tests;

import config.TestConfig;
import org.testng.annotations.Test;
import pages.HireCarTab;

import static org.testng.Assert.assertTrue;


public class HireCarTest extends TestConfig {

    @Test
    public void showCarList() {
        HireCarTab hireCarTab = mainPage.openHireCarTab();
        hireCarTab.searchCarDefaultDates("Warsaw Modlin");
        assertTrue(hireCarTab.isResultsContainerAvailable());
    }
}
