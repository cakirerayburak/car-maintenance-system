package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.editServiceEntryMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;

public class editServiceEntryMenuTest {

    private editServiceEntryMenu editMenu;
    private JTextField txtDriverName;
    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverNameEdit;
    private JTextField txtContactNum;
    private JTextField txtKilometer;
    private JButton btnShow;
    private JButton btnEdit;
    private JButton btnBack;
    private JPanel infoPanel;
    private userControl mockUserControl;
    private User mockUser;

    /**
     * @brief Setup method to initialize GUI components and mock objects before each test.
     */
    @Before
    public void setUp() {
        editMenu = new editServiceEntryMenu();
        // Initializing the GUI components using TestUtils to find them by their names
        txtDriverName = (JTextField) TestUtils.getChildNamed(editMenu, "txtDriverName");
        txtCarBrand = (JTextField) TestUtils.getChildNamed(editMenu, "txtCarBrand");
        txtWhatToDo = (JTextField) TestUtils.getChildNamed(editMenu, "txtWhatToDo");
        txtDriverNameEdit = (JTextField) TestUtils.getChildNamed(editMenu, "txtDriverNameEdit");
        txtContactNum = (JTextField) TestUtils.getChildNamed(editMenu, "txtContactNum");
        txtKilometer = (JTextField) TestUtils.getChildNamed(editMenu, "txtKilometer");
        btnShow = (JButton) TestUtils.getChildNamed(editMenu, "btnShow");
        btnEdit = (JButton) TestUtils.getChildNamed(editMenu, "btnEdit");
        btnBack = (JButton) TestUtils.getChildNamed(editMenu, "btnBack");
        infoPanel = (JPanel) TestUtils.getChildNamed(editMenu, "infoPanel");

        // Mocking the userControl class
        mockUserControl = mock(userControl.class);
        editMenu.userControlInstance = mockUserControl;

        // Mocking the User class
        mockUser = mock(User.class);
    }

    /**
     * @brief Test method to verify the show button functionality with a valid driver name.
     */
    @Test
    public void testShowButtonWithValidDriver() {
        // Creating a mock ServiceRecord object with valid details
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        // Defining behavior for getServiceRecordByDriverName method when "John Doe" is provided
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);

        // Setting the driver's name and simulating the Show button click
        txtDriverName.setText("John Doe");
        btnShow.doClick();

        // Asserting that the text fields are populated with the correct details from the mock record
        assertEquals("Toyota", txtCarBrand.getText());
        assertEquals("Oil Change", txtWhatToDo.getText());
        assertEquals("John Doe", txtDriverNameEdit.getText());
        assertEquals("12345", txtContactNum.getText());
        assertEquals("5000", txtKilometer.getText());
        assertTrue(infoPanel.isVisible());
    }

    /**
     * @brief Test method to verify the show button functionality with an invalid driver name.
     */
    @Test
    public void testShowButtonWithInvalidDriver() {
        // Defining behavior for getServiceRecordByDriverName method when an invalid name is provided
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        // Setting the driver's name to an invalid name and simulating the Show button click
        txtDriverName.setText("Invalid Name");
        btnShow.doClick();

        // Asserting that the information panel is not visible as no valid record was found
        assertFalse(infoPanel.isVisible());
    }

    /**
     * @brief Test method to verify the edit button functionality when editing is successful.
     */
    @Test
    public void testEditButtonSuccess() {
        // Creating a mock ServiceRecord object with valid details
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        // Defining behavior for getServiceRecordByDriverName method when "John Doe" is provided
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        // Defining behavior for updateServiceRecord method to return true, indicating success
        when(mockUserControl.updateServiceRecord(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        // Setting the driver's name and simulating the Show button click
        txtDriverName.setText("John Doe");
        btnShow.doClick();

        // Modifying the text fields to new values
        txtCarBrand.setText("Honda");
        txtWhatToDo.setText("Brake Check");
        txtDriverNameEdit.setText("Jane Doe");
        txtContactNum.setText("67890");
        txtKilometer.setText("6000");

        // Simulating the Edit button click
        btnEdit.doClick();

        // Verifying that updateServiceRecord method was called with the correct arguments
        verify(mockUserControl).updateServiceRecord(
                eq(1),
                eq("Honda"),
                eq("Brake Check"),
                eq("Jane Doe"),
                eq("67890"),
                eq(6000)
        );

        // Assuming there is a way to verify JOptionPane showing success message
    }

    /**
     * @brief Test method to verify the edit button functionality when editing fails.
     */
    @Test
    public void testEditButtonFailure() {
        // Creating a mock ServiceRecord object with valid details
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        // Defining behavior for getServiceRecordByDriverName method when "John Doe" is provided
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        // Defining behavior for updateServiceRecord method to return false, indicating failure
        when(mockUserControl.updateServiceRecord(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(false);

        // Setting the driver's name and simulating the Show button click
        txtDriverName.setText("John Doe");
        btnShow.doClick();

        // Modifying the text fields to new values
        txtCarBrand.setText("Honda");
        txtWhatToDo.setText("Brake Check");
        txtDriverNameEdit.setText("Jane Doe");
        txtContactNum.setText("67890");
        txtKilometer.setText("6000");

        // Simulating the Edit button click
        btnEdit.doClick();

        // Verifying that updateServiceRecord method was called with the correct arguments
        verify(mockUserControl).updateServiceRecord(
                eq(1),
                eq("Honda"),
                eq("Brake Check"),
                eq("Jane Doe"),
                eq("67890"),
                eq(6000)
        );

        // Assuming there is a way to verify JOptionPane showing failure message
    }

    /**
     * @brief Test method to verify the back button functionality.
     */
    @Test
    public void testBackButton() {
        // Simulating the Back button click
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
        assertFalse(editMenu.isVisible());
    }

    /**
     * @brief Helper method to create a mock ServiceRecord.
     * @return A mock ServiceRecord object with predefined values.
     */
    private ServiceRecord createMockServiceRecord() {
        ServiceRecord mockRecord = mock(ServiceRecord.class);
        when(mockRecord.getCarBrand()).thenReturn("Toyota");
        when(mockRecord.getWhatToDo()).thenReturn("Oil Change");
        when(mockRecord.getDriverName()).thenReturn("John Doe");
        when(mockRecord.getDriverPhone()).thenReturn("12345");
        when(mockRecord.getKilometer()).thenReturn(5000);
        return mockRecord;
    }
}
