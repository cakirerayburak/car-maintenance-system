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

    @Before
    public void setUp() {
        editMenu = new editServiceEntryMenu();
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

        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        editMenu.userControlInstance = mockUserControl;

        // Mock the User class
        mockUser = mock(User.class);
    }

    @Test
    public void testShowButtonWithValidDriver() {
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);

        txtDriverName.setText("John Doe");
        btnShow.doClick();

        assertEquals("Toyota", txtCarBrand.getText());
        assertEquals("Oil Change", txtWhatToDo.getText());
        assertEquals("John Doe", txtDriverNameEdit.getText());
        assertEquals("12345", txtContactNum.getText());
        assertEquals("5000", txtKilometer.getText());
        assertTrue(infoPanel.isVisible());
    }

    @Test
    public void testShowButtonWithInvalidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        txtDriverName.setText("Invalid Name");
        btnShow.doClick();

        assertFalse(infoPanel.isVisible());
    }

    @Test
    public void testEditButtonSuccess() {
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        when(mockUserControl.updateServiceRecord(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        txtDriverName.setText("John Doe");
        btnShow.doClick();

        txtCarBrand.setText("Honda");
        txtWhatToDo.setText("Brake Check");
        txtDriverNameEdit.setText("Jane Doe");
        txtContactNum.setText("67890");
        txtKilometer.setText("6000");

        btnEdit.doClick();

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

    @Test
    public void testEditButtonFailure() {
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);
        when(mockUserControl.updateServiceRecord(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(false);

        txtDriverName.setText("John Doe");
        btnShow.doClick();

        txtCarBrand.setText("Honda");
        txtWhatToDo.setText("Brake Check");
        txtDriverNameEdit.setText("Jane Doe");
        txtContactNum.setText("67890");
        txtKilometer.setText("6000");

        btnEdit.doClick();

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

    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
        assertFalse(editMenu.isVisible());
    }

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
