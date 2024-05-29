package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.createServiceEntryMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;

public class createServiceEntryMenuTest {

    private createServiceEntryMenu createMenu;
    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverName;
    private JTextField txtDriverNum;
    private JTextField txtKilometer;
    private JButton btnAdd;
    private JButton btnBack;
    private userControl mockUserControl;

    @Before
    public void setUp() {
        createMenu = new createServiceEntryMenu();
        txtCarBrand = (JTextField) TestUtils.getChildNamed(createMenu, "txtCarBrand");
        txtWhatToDo = (JTextField) TestUtils.getChildNamed(createMenu, "txtWhatToDo");
        txtDriverName = (JTextField) TestUtils.getChildNamed(createMenu, "txtDriverName");
        txtDriverNum = (JTextField) TestUtils.getChildNamed(createMenu, "txtDriverNum");
        txtKilometer = (JTextField) TestUtils.getChildNamed(createMenu, "txtKilometer");
        btnAdd = (JButton) TestUtils.getChildNamed(createMenu, "btnAdd");
        btnBack = (JButton) TestUtils.getChildNamed(createMenu, "btnBack");

        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        createMenu.userControlInstance = mockUserControl;
    }

    @Test
    public void testAddButtonSuccess() {
        when(mockUserControl.addServiceRecord(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("5000");

        btnAdd.doClick();

        verify(mockUserControl).addServiceRecord("Toyota", "Oil Change", "John Doe", "12345", 5000);
        // Assuming there is a way to verify JOptionPane showing success message
    }

    @Test
    public void testAddButtonFailure() {
        when(mockUserControl.addServiceRecord(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(false);

        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("5000");

        btnAdd.doClick();

        verify(mockUserControl).addServiceRecord("Toyota", "Oil Change", "John Doe", "12345", 5000);
        // Assuming there is a way to verify JOptionPane showing failure message
    }

    @Test
    public void testAddButtonInvalidKilometer() {
        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("invalid");

        btnAdd.doClick();

        // Assuming there is a way to verify JOptionPane showing error message for invalid kilometer
    }

    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
    }
}
