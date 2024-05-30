/**
 * @file serviceRecordsMenuTest.java
 * @brief This file contains JUnit tests for the serviceRecordsMenu class.
 */

package guitests;

import com.ebcak.carmaintenancegui.serviceRecordsMenu;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;

/**
 * @brief JUnit tests for the serviceRecordsMenu class.
 */
public class serviceRecordsMenuTest {

    private serviceRecordsMenu serviceRecordsMenu;
    private JButton btnCreateServiceEntry;
    private JButton btnEditServiceEntry;
    private JButton btnSearchEntry;
    private JButton btnDeleteServiceEntry;
    private JButton btnBack;

    /**
     * @brief Sets up the necessary objects before each test.
     */
    @Before
    public void setUp() {
        serviceRecordsMenu = new serviceRecordsMenu();
        btnCreateServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnCreateServiceEntry");
        btnEditServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnEditServiceEntry");
        btnSearchEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnSearchEntry");
        btnDeleteServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnDeleteServiceEntry");
        btnBack = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnBack");
    }

    /**
     * @brief Test for the "CREATE SERVICE ENTRY" button.
     */
    @Test
    public void testCreateServiceEntryButton() {
        assertNotNull(btnCreateServiceEntry);
        // Check if the button is visible
        assertTrue(btnCreateServiceEntry.isVisible());
        // Check the button label
        assertEquals("CREATE SERVICE ENTRY", btnCreateServiceEntry.getText());
    }

    /**
     * @brief Test for the "EDIT SERVICE ENTRY" button.
     */
    @Test
    public void testEditServiceEntryButton() {
        assertNotNull(btnEditServiceEntry);
        // Check if the button is visible
        assertTrue(btnEditServiceEntry.isVisible());
        // Check the button label
        assertEquals("EDIT SERVICE ENTRY", btnEditServiceEntry.getText());
    }

    /**
     * @brief Test for the "SEARCH ENTRY" button.
     */
    @Test
    public void testSearchEntryButton() {
        assertNotNull(btnSearchEntry);
        // Check if the button is visible
        assertTrue(btnSearchEntry.isVisible());
        // Check the button label
        assertEquals("SEARCH ENTRY", btnSearchEntry.getText());
    }

    /**
     * @brief Test for the "DELETE SERVICE ENTRY" button.
     */
    @Test
    public void testDeleteServiceEntryButton() {
        assertNotNull(btnDeleteServiceEntry);
        // Check if the button is visible
        assertTrue(btnDeleteServiceEntry.isVisible());
        // Check the button label
        assertEquals("DELETE SERVICE ENTRY", btnDeleteServiceEntry.getText());
    }

    /**
     * @brief Test for the "BACK" button.
     */
    @Test
    public void testBackButton() {
        assertNotNull(btnBack);
        // Check if the button is visible
        assertTrue(btnBack.isVisible());
        // Check the button label
        assertEquals("BACK", btnBack.getText());
    }

    /**
     * @brief Test action for the "CREATE SERVICE ENTRY" button.
     */
    @Test
    public void testCreateServiceEntryButtonAction() {
        // Before opening a new window
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnCreateServiceEntry.doClick();

        // After opening a new window
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    /**
     * @brief Test action for the "EDIT SERVICE ENTRY" button.
     */
    @Test
    public void testEditServiceEntryButtonAction() {
        // Before opening a new window
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnEditServiceEntry.doClick();

        // After opening a new window
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    /**
     * @brief Test action for the "SEARCH ENTRY" button.
     */
    @Test
    public void testSearchEntryButtonAction() {
        // Before opening a new window
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnSearchEntry.doClick();

        // After opening a new window
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    /**
     * @brief Test action for the "DELETE SERVICE ENTRY" button.
     */
    @Test
    public void testDeleteServiceEntryButtonAction() {
        // Before opening a new window
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnDeleteServiceEntry.doClick();

        // After opening a new window
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    /**
     * @brief Test action for the "BACK" button.
     */
    @Test
    public void testBackButtonAction() {
        // Before opening a new window
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnBack.doClick();

        // After opening a new window
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }
}
