package guitests;

import com.ebcak.carmaintenancegui.serviceRecordsMenu;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;

public class serviceRecordsMenuTest {

    private serviceRecordsMenu serviceRecordsMenu;
    private JButton btnCreateServiceEntry;
    private JButton btnEditServiceEntry;
    private JButton btnSearchEntry;
    private JButton btnDeleteServiceEntry;
    private JButton btnBack;

    @Before
    public void setUp() {
        serviceRecordsMenu = new serviceRecordsMenu();
        btnCreateServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnCreateServiceEntry");
        btnEditServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnEditServiceEntry");
        btnSearchEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnSearchEntry");
        btnDeleteServiceEntry = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnDeleteServiceEntry");
        btnBack = (JButton) TestUtils.getChildNamed(serviceRecordsMenu, "btnBack");
    }

    @Test
    public void testCreateServiceEntryButton() {
        assertNotNull(btnCreateServiceEntry);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnCreateServiceEntry.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("CREATE SERVICE ENTRY", btnCreateServiceEntry.getText());
    }

    @Test
    public void testEditServiceEntryButton() {
        assertNotNull(btnEditServiceEntry);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnEditServiceEntry.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("EDIT SERVICE ENTRY", btnEditServiceEntry.getText());
    }

    @Test
    public void testSearchEntryButton() {
        assertNotNull(btnSearchEntry);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnSearchEntry.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("SEARCH ENTRY", btnSearchEntry.getText());
    }

    @Test
    public void testDeleteServiceEntryButton() {
        assertNotNull(btnDeleteServiceEntry);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnDeleteServiceEntry.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("DELETE SERVICE ENTRY", btnDeleteServiceEntry.getText());
    }

    @Test
    public void testBackButton() {
        assertNotNull(btnBack);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnBack.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("BACK", btnBack.getText());
    }

    @Test
    public void testCreateServiceEntryButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnCreateServiceEntry.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testEditServiceEntryButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnEditServiceEntry.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testSearchEntryButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnSearchEntry.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testDeleteServiceEntryButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnDeleteServiceEntry.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testBackButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnBack.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }
}
