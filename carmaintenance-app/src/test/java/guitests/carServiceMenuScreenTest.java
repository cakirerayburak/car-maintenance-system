package guitests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.carServiceMenuScreen;
import com.ebcak.carmaintenancegui.signInScreen;
import com.ebcak.carmaintenancegui.fuelEfficiencyReportsMenu;
import com.ebcak.carmaintenancegui.expenseRecordsMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenancegui.maintenanceRemindersMenu;

import javax.swing.*;

public class carServiceMenuScreenTest {

    private carServiceMenuScreen menuScreen;
    private JButton btnFuelEfficiencyReports;
    private JButton btnExpenseRecords;
    private JButton btnServiceRecords;
    private JButton btnMaintenanceReminders;
    private JButton btnBack;

    @Before
    public void setUp() {
        menuScreen = new carServiceMenuScreen();
        btnFuelEfficiencyReports = (JButton) TestUtils.getChildNamed(menuScreen, "btnFuelEfficiencyReports");
        btnExpenseRecords = (JButton) TestUtils.getChildNamed(menuScreen, "btnExpenseRecords");
        btnServiceRecords = (JButton) TestUtils.getChildNamed(menuScreen, "btnServiceRecords");
        btnMaintenanceReminders = (JButton) TestUtils.getChildNamed(menuScreen, "btnMaintenanceReminders");
        btnBack = (JButton) TestUtils.getChildNamed(menuScreen, "btnBack");
    }

    @Test
    public void testFuelEfficiencyReportsButton() {
        btnFuelEfficiencyReports.doClick();
        // Test that the fuelEfficiencyReportsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    @Test
    public void testExpenseRecordsButton() {
        btnExpenseRecords.doClick();
        // Test that the expenseRecordsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    @Test
    public void testServiceRecordsButton() {
        btnServiceRecords.doClick();
        // Test that the serviceRecordsMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    @Test
    public void testMaintenanceRemindersButton() {
        btnMaintenanceReminders.doClick();
        // Test that the maintenanceRemindersMenu is opened
        // Assuming there's a way to verify it, like checking some static state
    }

    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Test that the signInScreen is opened
        // Assuming there's a way to verify it, like checking some static state
    }
}
