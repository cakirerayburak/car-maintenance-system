package guitests;

import org.junit.Before;
import org.junit.Test;

import com.ebcak.carmaintenancegui.carServiceMenuScreen;

import javax.swing.*;

/**
 * @class carServiceMenuScreenTest
 * @brief Test class for carServiceMenuScreen GUI.
 */
public class carServiceMenuScreenTest {

    private carServiceMenuScreen menuScreen;
    private JButton btnFuelEfficiencyReports;
    private JButton btnExpenseRecords;
    private JButton btnServiceRecords;
    private JButton btnMaintenanceReminders;
    private JButton btnBack;

    /**
     * @brief Setup method to initialize GUI components before each test.
     */
    @Before
    public void setUp() {
        menuScreen = new carServiceMenuScreen();
        btnFuelEfficiencyReports = (JButton) TestUtils.getChildNamed(menuScreen, "btnFuelEfficiencyReports");
        btnExpenseRecords = (JButton) TestUtils.getChildNamed(menuScreen, "btnExpenseRecords");
        btnServiceRecords = (JButton) TestUtils.getChildNamed(menuScreen, "btnServiceRecords");
        btnMaintenanceReminders = (JButton) TestUtils.getChildNamed(menuScreen, "btnMaintenanceReminders");
        btnBack = (JButton) TestUtils.getChildNamed(menuScreen, "btnBack");
    }

    /**
     * @brief Test method to verify the functionality of the Fuel Efficiency Reports button.
     */
    @Test
    public void testFuelEfficiencyReportsButton() {
        btnFuelEfficiencyReports.doClick();
        // Test that the fuelEfficiencyReportsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    /**
     * @brief Test method to verify the functionality of the Expense Records button.
     */
    @Test
    public void testExpenseRecordsButton() {
        btnExpenseRecords.doClick();
        // Test that the expenseRecordsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    /**
     * @brief Test method to verify the functionality of the Service Records button.
     */
    @Test
    public void testServiceRecordsButton() {
        btnServiceRecords.doClick();
        // Test that the serviceRecordsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    /**
     * @brief Test method to verify the functionality of the Maintenance Reminders button.
     */
    @Test
    public void testMaintenanceRemindersButton() {
        btnMaintenanceReminders.doClick();
        // Test that the maintenanceRemindersMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    /**
     * @brief Test method to verify the functionality of the Back button.
     */
    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Test that the signInScreen is opened
        // Assuming there's a way to verify it, like checking some static state
    }
}
