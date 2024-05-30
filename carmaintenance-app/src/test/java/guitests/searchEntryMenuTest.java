/**
 * @file searchEntryMenuTest.java
 * @brief Bu dosya, searchEntryMenu sınıfının JUnit testlerini içerir.
 */

package guitests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.searchEntryMenu;
import com.ebcak.carmaintenancegui.serviceRecordsMenu;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;

/**
 * @brief searchEntryMenu sınıfının JUnit testleri.
 */
public class searchEntryMenuTest {

    private searchEntryMenu searchMenu;
    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JPanel infoPanel;
    private JButton btnSearch;
    private JButton btnBack;
    private userControl mockUserControl;
    private User mockUser;

    /**
     * @brief Testlerin öncesinde yürütülecek işlemleri hazırlar.
     */
    @Before
    public void setUp() {
        searchMenu = new searchEntryMenu();
        txtDriverName = (JTextField) TestUtils.getChildNamed(searchMenu, "txtDriverName");
        lblBrand = (JLabel) TestUtils.getChildNamed(searchMenu, "lblBrand");
        lblWhatToDo = (JLabel) TestUtils.getChildNamed(searchMenu, "lblWhatToDo");
        lblDriverName = (JLabel) TestUtils.getChildNamed(searchMenu, "lblDriverName");
        lblContactNum = (JLabel) TestUtils.getChildNamed(searchMenu, "lblContactNum");
        lblKilometer = (JLabel) TestUtils.getChildNamed(searchMenu, "lblKilometer");
        infoPanel = (JPanel) TestUtils.getChildNamed(searchMenu, "infoPanel");
        btnSearch = (JButton) TestUtils.getChildNamed(searchMenu, "btnSearch");
        btnBack = (JButton) TestUtils.getChildNamed(searchMenu, "btnBack");

        // Mock the userControl class
        mockUserControl = mock(userControl.class);
        searchMenu.userControlInstance = mockUserControl;

        // Mock the User class
        mockUser = mock(User.class);
    }

    /**
     * @brief "Search" butonunun geçerli sürücü için testi.
     */
    @Test
    public void testSearchButtonWithValidDriver() {
        ServiceRecord mockRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "12345", 5000, 1, mockUser);
        when(mockUserControl.getServiceRecordByDriverName("John Doe")).thenReturn(mockRecord);

        txtDriverName.setText("John Doe");
        btnSearch.doClick();

        assertEquals("Brand: Toyota", lblBrand.getText());
        assertEquals("What to do: Oil Change", lblWhatToDo.getText());
        assertEquals("Driver Name: John Doe", lblDriverName.getText());
        assertEquals("Contact Num: 12345", lblContactNum.getText());
        assertEquals("Kilometer: 5000", lblKilometer.getText());
        assertTrue(infoPanel.isVisible());
    }

    /**
     * @brief "Search" butonunun geçersiz sürücü için testi.
     */
    @Test
    public void testSearchButtonWithInvalidDriver() {
        when(mockUserControl.getServiceRecordByDriverName("Invalid Name")).thenReturn(null);

        txtDriverName.setText("Invalid Name");
        btnSearch.doClick();

        assertFalse(infoPanel.isVisible());
    }

    /**
     * @brief "Back" butonunun testi.
     */
    @Test
    public void testBackButton() {
        btnBack.doClick();
        // Assuming there is a way to verify serviceRecordsMenu is opened
        // For example, you might check if the current frame is disposed
        assertFalse(searchMenu.isVisible());
    }
}
