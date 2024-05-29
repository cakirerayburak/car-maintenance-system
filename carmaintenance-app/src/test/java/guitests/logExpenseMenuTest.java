package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.logExpenseMenu;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenancegui.userControl;
import com.ebcak.carmaintenancelogiclayer.logicJava;

import javax.swing.*;

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
    private User mockUser;
    private ServiceRecord mockServiceRecord;
    private logicJava mockLogicJava;

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
        mockUser = mock(User.class);
        mockServiceRecord = mock(ServiceRecord.class);

        when(mockServiceRecord.getCarBrand()).thenReturn("Toyota");
        when(mockServiceRecord.getDriverName()).thenReturn("John Doe");
        when(mockServiceRecord.getKilometer()).thenReturn(5000);

        // Mock the logicJava class
        mockLogicJava = mock(logicJava.class);
        logExpenseMenu.setLogicJavaInstance(mockLogicJava); // Assuming there's a setter for logicJava instance
    }

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

    @Test
    public void testListButtonWithInvalidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        txtDriverName.setText("Invalid Name");
        btnList.doClick();

        assertFalse(infoPanel.isVisible());
    }

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

        // Assuming there is a way to verify JOptionPane showing success message
    }

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

        // Assuming there is a way to verify JOptionPane showing failure message
    }

    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify main menu is opened
        assertFalse(logMenu.isVisible());
    }
}
