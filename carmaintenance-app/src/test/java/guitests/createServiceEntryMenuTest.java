package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.createServiceEntryMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @class createServiceEntryMenuTest
 * @brief Test class for createServiceEntryMenu GUI.
 */
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
    private Robot robot;

    /**
     * @brief Setup method to initialize GUI components and mock objects before each test.
     */
    @Before
    public void setUp() throws Exception {
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

        // Initialize Robot for simulating button clicks
        robot = new Robot();
    }

    /**
     * @brief Helper method to click the OK button on a popup dialog.
     */
    private void clickOKButtonOnPopup() throws InterruptedException {
        // Wait for the dialog to appear
        Thread.sleep(1000);

        // Find all windows of type JDialog (JOptionPane creates JDialog)
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;

                // Get the OK button from the dialog
                for (Component component : dialog.getContentPane().getComponents()) {
                    if (component instanceof JButton) {
                        JButton okButton = (JButton) component;
                        if (okButton.getText().equals("OK")) {
                            // Get location of the OK button
                            Point location = okButton.getLocationOnScreen();
                            robot.mouseMove(location.x + okButton.getWidth() / 2, location.y + okButton.getHeight() / 2);
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * @brief Test method to verify successful addition of a service record.
     */
    @Test
    public void testAddButtonSuccess() throws InterruptedException {
        when(mockUserControl.addServiceRecord(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("5000");

        btnAdd.doClick();
        clickOKButtonOnPopup();

        verify(mockUserControl).addServiceRecord("Toyota", "Oil Change", "John Doe", "12345", 5000);
    }

    /**
     * @brief Test method to verify behavior when adding a service record fails.
     */
    @Test
    public void testAddButtonFailure() throws InterruptedException {
        when(mockUserControl.addServiceRecord(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(false);

        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("5000");

        btnAdd.doClick();
        clickOKButtonOnPopup();

        verify(mockUserControl).addServiceRecord("Toyota", "Oil Change", "John Doe", "12345", 5000);
    }

    /**
     * @brief Test method to verify behavior when an invalid kilometer value is entered.
     */
    @Test
    public void testAddButtonInvalidKilometer() throws InterruptedException {
        txtCarBrand.setText("Toyota");
        txtWhatToDo.setText("Oil Change");
        txtDriverName.setText("John Doe");
        txtDriverNum.setText("12345");
        txtKilometer.setText("invalid");

        btnAdd.doClick();
        clickOKButtonOnPopup();

        // Verify that no service record was added
        verify(mockUserControl, never()).addServiceRecord(anyString(), anyString(), anyString(), anyString(), anyInt());
    }

    /**
     * @brief Test method to verify the back button functionality.
     */
    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
    }
}
