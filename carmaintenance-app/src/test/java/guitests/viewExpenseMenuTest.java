package guitests;

import com.ebcak.carmaintenancegui.viewExpenseMenu;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenancegui.userControl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class viewExpenseMenuTest {

    private viewExpenseMenu expenseMenu;
    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JLabel lblYearlyCost;
    private JLabel lblFuelCost;
    private JLabel lblYearlyRepairCost;
    private JPanel infoPanel;
    private JButton btnList;
    private JButton btnBack;
    private userControl mockUserControl;
    private ServiceRecord mockServiceRecord;
    private ExpenseReport mockExpenseReport;

    @Before
    public void setUp() {
        expenseMenu = new viewExpenseMenu();
        
        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        expenseMenu.userCtrl = mockUserControl;

        // Mock the ServiceRecord class
        mockServiceRecord = mock(ServiceRecord.class);
        when(mockServiceRecord.getCarBrand()).thenReturn("Toyota");
        when(mockServiceRecord.getDriverName()).thenReturn("John Doe");
        when(mockServiceRecord.getKilometer()).thenReturn(5000);

        // Mock the ExpenseReport class
        mockExpenseReport = mock(ExpenseReport.class);
        when(mockExpenseReport.getServiceRecord()).thenReturn(mockServiceRecord);
        when(mockExpenseReport.getTotalCost()).thenReturn(1500.0);
        when(mockExpenseReport.getDailyFuel()).thenReturn(50.0);
        when(mockExpenseReport.getAnnualFuel()).thenReturn(500.0);

        // Retrieve components
        txtDriverName = getComponent(expenseMenu, "txtDriverName", JTextField.class);
        lblBrand = getComponent(expenseMenu, "lblBrand", JLabel.class);
        lblDriver = getComponent(expenseMenu, "lblDriver", JLabel.class);
        lblKilometer = getComponent(expenseMenu, "lblKilometer", JLabel.class);
        lblYearlyCost = getComponent(expenseMenu, "lblYearlyCost", JLabel.class);
        lblFuelCost = getComponent(expenseMenu, "lblFuelCost", JLabel.class);
        lblYearlyRepairCost = getComponent(expenseMenu, "lblYearlyRepairCost", JLabel.class);
        infoPanel = getComponent(expenseMenu, "infoPanel", JPanel.class);
        btnList = getComponent(expenseMenu, "btnList", JButton.class);
        btnBack = getComponent(expenseMenu, "btnBack", JButton.class);
    }

    @Test
    public void testListButtonWithValidDriver() {
        List<ExpenseReport> expenseReports = new ArrayList<>();
        expenseReports.add(mockExpenseReport);
        when(mockUserControl.getExpenseReportsByDriverName("John Doe")).thenReturn(expenseReports);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        assertEquals("Brand: Toyota", lblBrand.getText());
        assertEquals("Driver Name: John Doe", lblDriver.getText());
        assertEquals("Kilometer: 5000", lblKilometer.getText());
        assertEquals("Yearly Cost: 1500.0", lblYearlyCost.getText());
        assertEquals("Fuel Cost: 50.0", lblFuelCost.getText());
        assertEquals("Yearly Repair Cost: 500.0", lblYearlyRepairCost.getText());
        assertTrue(infoPanel.isVisible());
    }

    @Test
    public void testListButtonWithInvalidDriver() {
        when(mockUserControl.getExpenseReportsByDriverName("Invalid Name")).thenReturn(new ArrayList<>());

        txtDriverName.setText("Invalid Name");
        btnList.doClick();

        assertFalse(infoPanel.isVisible());
    }

    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify main menu is opened
        assertFalse(expenseMenu.isVisible());
    }

    private <T extends Component> T getComponent(Container container, String name, Class<T> componentClass) {
        for (Component component : container.getComponents()) {
            if (componentClass.isInstance(component)) {
                T typedComponent = componentClass.cast(component);
                if (name.equals(typedComponent.getName())) {
                    return typedComponent;
                }
            }
            if (component instanceof Container) {
                T childComponent = getComponent((Container) component, name, componentClass);
                if (childComponent != null) {
                    return childComponent;
                }
            }
        }
        return null;
    }
}
