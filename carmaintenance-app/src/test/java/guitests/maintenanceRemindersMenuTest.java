package guitests;


import com.ebcak.carmaintenancegui.maintenanceRemindersMenu;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;

public class maintenanceRemindersMenuTest {

    private maintenanceRemindersMenu maintenanceRemindersMenu;
    private JButton btnSetReminder;
    private JButton btnViewReminder;
    private JButton btnBack;

    @Before
    public void setUp() {
        maintenanceRemindersMenu = new maintenanceRemindersMenu();
        btnSetReminder = (JButton) TestUtils.getChildNamed(maintenanceRemindersMenu, "btnSetReminder");
        btnViewReminder = (JButton) TestUtils.getChildNamed(maintenanceRemindersMenu, "btnViewReminder");
        btnBack = (JButton) TestUtils.getChildNamed(maintenanceRemindersMenu, "btnBack");
    }

    @Test
    public void testSetReminderButton() {
        assertNotNull(btnSetReminder);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnSetReminder.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("SET REMINDER", btnSetReminder.getText());
    }

    @Test
    public void testViewReminderButton() {
        assertNotNull(btnViewReminder);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnViewReminder.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("VIEW REMINDER", btnViewReminder.getText());
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
    public void testSetReminderButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnSetReminder.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testViewReminderButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnViewReminder.doClick();

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
