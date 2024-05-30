package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.deleteServiceEntryMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;

/**
 * @class deleteServiceEntryMenuTest
 * @brief Test class for deleteServiceEntryMenu GUI.
 */
public class deleteServiceEntryMenuTest {

    private deleteServiceEntryMenu deleteMenu;
    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JButton btnList;
    private JButton btnDelete;
    private JButton btnBack;
    private JPanel infoPanel;
    private userControl mockUserControl;
    private User mockUser;

    /**
     * @brief Setup method to initialize GUI components and mock objects before each test.
     */
    @Before
    public void setUp() {
        deleteMenu = new deleteServiceEntryMenu();
        txtDriverName = (JTextField) TestUtils.getChildNamed(deleteMenu, "txtDriverName");
        lblBrand = (JLabel) TestUtils.getChildNamed(deleteMenu, "lblBrand");
        lblWhatToDo = (JLabel) TestUtils.getChildNamed(deleteMenu, "lblWhatToDo");
        lblDriverName = (JLabel) TestUtils.getChildNamed(deleteMenu, "lblDriverName");
        lblContactNum = (JLabel) TestUtils.getChildNamed(deleteMenu, "lblContactNum");
        lblKilometer = (JLabel) TestUtils.getChildNamed(deleteMenu, "lblKilometer");
        btnList = (JButton) TestUtils.getChildNamed(deleteMenu, "btnList");
        btnDelete = (JButton) TestUtils.getChildNamed(deleteMenu, "btnDelete");
        btnBack = (JButton) TestUtils.getChildNamed(deleteMenu, "btnBack");
        infoPanel = (JPanel) TestUtils.getChildNamed(deleteMenu, "infoPanel");

        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        deleteMenu.userControlInstance = mockUserControl;

        // Mock the User class
        mockUser = mock(User.class);
    }

    /**
     * @brief Test method to verify the list button functionality with a valid driver name.
     */
    @Test
    public void testListButtonWithValidDriver() {
        ServiceRecord mockRecord = createMockServiceRecord("John Doe");
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        assertEquals("Brand: Toyota", lblBrand.getText());
        assertEquals("What to do: Oil Change", lblWhatToDo.getText());
        assertEquals("Driver Name: John Doe", lblDriverName.getText());
        assertEquals("Contact Num: 12345", lblContactNum.getText());
        assertEquals("Kilometer: 5000", lblKilometer.getText());
        assertTrue(infoPanel.isVisible());
    }

    /**
     * @brief Test method to verify the list button functionality with an invalid driver name.
     */
    @Test
    public void testListButtonWithInvalidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        txtDriverName.setText("Invalid Name");
        btnList.doClick();

        assertFalse(infoPanel.isVisible());
    }

    /**
     * @brief Test method to verify the delete button functionality when deletion is successful.
     */
    @Test
    public void testDeleteButtonSuccess() {
        ServiceRecord mockRecord = createMockServiceRecord("John Doe");
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        when(mockUserControl.deleteServiceRecordByDriverName("John Doe")).thenReturn(true);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        btnDelete.doClick();

        verify(mockUserControl).deleteServiceRecordByDriverName("John Doe");
        assertFalse(infoPanel.isVisible());
        // Assuming there is a way to verify JOptionPane showing success message
    }

    /**
     * @brief Test method to verify the delete button functionality when deletion fails.
     */
    @Test
    public void testDeleteButtonFailure() {
        ServiceRecord mockRecord = createMockServiceRecord("John Doe");
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        when(mockUserControl.deleteServiceRecordByDriverName("John Doe")).thenReturn(false);

        txtDriverName.setText("John Doe");
        btnList.doClick();

        btnDelete.doClick();

        verify(mockUserControl).deleteServiceRecordByDriverName("John Doe");
        assertTrue(infoPanel.isVisible());
        // Assuming there is a way to verify JOptionPane showing failure message
    }

    /**
     * @brief Test method to verify the back button functionality.
     */
    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
        assertFalse(deleteMenu.isVisible());
    }

    /**
     * @brief Helper method to create a mock ServiceRecord.
     * @param driverName The name of the driver for the service record.
     * @return A mock ServiceRecord object.
     */
    private ServiceRecord createMockServiceRecord(String driverName) {
        ServiceRecord mockRecord = mock(ServiceRecord.class);
        when(mockRecord.getCarBrand()).thenReturn("Toyota");
        when(mockRecord.getWhatToDo()).thenReturn("Oil Change");
        when(mockRecord.getDriverName()).thenReturn(driverName);
        when(mockRecord.getDriverPhone()).thenReturn("12345");
        when(mockRecord.getKilometer()).thenReturn(5000);
        return mockRecord;
    }
}
