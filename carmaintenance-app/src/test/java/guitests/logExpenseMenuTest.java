/**
 * @file logExpenseMenuTest.java
 * @brief Bu dosya, logExpenseMenu sınıfının JUnit testlerini içerir.
 */

package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Window;

import com.ebcak.carmaintenancegui.logExpenseMenu;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenancegui.userControl;
import com.ebcak.carmaintenancelogiclayer.logicJava;

import javax.swing.*;

/**
 * @brief logExpenseMenu sınıfının JUnit testleri.
 */
public class logExpenseMenuTest {

    private logExpenseMenu logMenu;
    private JTextField txtDriverName;
    private JTextField txtFuelCost;
    private JTextField txtYearlyCost;
    private JTextField txtYearlyRepairCost;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JButton btnList;
    private JButton btnAdd;
    private JButton btnBack;
    private JPanel infoPanel;
    private userControl mockUserControl;
    private ServiceRecord mockServiceRecord;
    private logicJava.ExpenseReportLogic mockLogicJava;
    private User mockUser;

    /**
     * @brief Testlerin öncesinde yürütülecek işlemleri hazırlar.
     */
    @Before
    public void setUp() {
        logMenu = new logExpenseMenu();
        txtDriverName = (JTextField) TestUtils.getChildNamed(logMenu, "txtDriverName");
        txtFuelCost = (JTextField) TestUtils.getChildNamed(logMenu, "txtFuelCost");
        txtYearlyCost = (JTextField) TestUtils.getChildNamed(logMenu, "txtYearlyCost");
        txtYearlyRepairCost = (JTextField) TestUtils.getChildNamed(logMenu, "txtYearlyRepairCost");
        lblBrand = (JLabel) TestUtils.getChildNamed(logMenu, "lblBrand");
        lblDriver = (JLabel) TestUtils.getChildNamed(logMenu, "lblDriver");
        lblKilometer = (JLabel) TestUtils.getChildNamed(logMenu, "lblKilometer");
        btnList = (JButton) TestUtils.getChildNamed(logMenu, "btnList");
        btnAdd = (JButton) TestUtils.getChildNamed(logMenu, "btnAdd");
        btnBack = (JButton) TestUtils.getChildNamed(logMenu, "btnBack");
        infoPanel = (JPanel) TestUtils.getChildNamed(logMenu, "infoPanel");

        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        logMenu.userControlInstance = mockUserControl;

        // Mock the User class
        mockUser = new User(1, "testuser", "password", "testuser@example.com");

        // Mock the ServiceRecord class
        mockServiceRecord = new ServiceRecord(1, "Toyota", "Change oil", "John Doe", "123456789", 5000, 1, mockUser);

        // Mock the logicJava class
        mockLogicJava = mock(logicJava.ExpenseReportLogic.class);
        logExpenseMenu.setLogicJavaInstance(mockLogicJava);
    }

    /**
     * @brief "List" butonunun geçerli sürücü için testi.
     */
    @Test
    public void testListButtonWithValidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockServiceRecord);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        assertEquals("Brand: Toyota", lblBrand.getText());
        assertEquals("Driver Name: John Doe", lblDriver.getText());
        assertEquals("Kilometer: 5000", lblKilometer.getText());
        assertTrue(infoPanel.isVisible());
    }

    /**
     * @brief "List" butonunun geçersiz sürücü için testi.
     */
    @Test
    public void testListButtonWithInvalidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        txtDriverName.setText("Invalid Name");
        btnList.doClick();

        assertFalse(infoPanel.isVisible());
    }

    /**
     * @brief "Add" butonunun başarılı senaryo testi.
     */
    @Test
    public void testAddButtonSuccess() {
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockServiceRecord);
        when(mockLogicJava.addExpenseReport(anyString(), anyDouble(), anyDouble(), anyDouble())).thenReturn(true);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        txtFuelCost.setText("50.0");
        txtYearlyCost.setText("1000.0");
        txtYearlyRepairCost.setText("500.0");

        btnAdd.doClick();

        verify(mockLogicJava).addExpenseReport(
                eq("John Doe"),
                eq(50.0),
                eq(1000.0),
                eq(500.0)
        );

        // Simulate closing of the success message after 1.5 seconds
        Timer timer = new Timer(1500, e -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog) {
                    window.dispose();
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * @brief "Add" butonunun başarısız senaryo testi.
     */
    @Test
    public void testAddButtonFailure() {
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockServiceRecord);
        when(mockLogicJava.addExpenseReport(anyString(), anyDouble(), anyDouble(), anyDouble())).thenReturn(false);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        txtFuelCost.setText("50.0");
        txtYearlyCost.setText("1000.0");
        txtYearlyRepairCost.setText("500.0");

        btnAdd.doClick();

        verify(mockLogicJava).addExpenseReport(
                eq("John Doe"),
                eq(50.0),
                eq(1000.0),
                eq(500.0)
        );

        // Simulate closing of the failure message after 1.5 seconds
        Timer timer = new Timer(1500, e -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog) {
                    window.dispose();
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * @brief "Back" butonunun testi.
     */
    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify main menu is opened
        assertFalse(logMenu.isVisible());
    }
}
