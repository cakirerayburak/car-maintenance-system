/**
 * @file fuelEfficiencyReportsMenuTest.java
 * @brief Bu dosya, fuelEfficiencyReportsMenu sınıfının JUnit testlerini içerir.
 */

package guitests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ebcak.carmaintenancegui.fuelEfficiencyReportsMenu;

import javax.swing.*;
import java.awt.*;

/**
 * @brief fuelEfficiencyReportsMenu sınıfının JUnit testleri.
 */
public class fuelEfficiencyReportsMenuTest {

    private fuelEfficiencyReportsMenu fuelEfficiencyReportsMenu;
    private JButton btnCreateReports;
    private JButton btnBack;

    /**
     * @brief Testlerin öncesinde yürütülecek işlemleri hazırlar.
     */
    @Before
    public void setUp() {
        fuelEfficiencyReportsMenu = new fuelEfficiencyReportsMenu();
        btnCreateReports = (JButton) TestUtils.getChildNamed(fuelEfficiencyReportsMenu, "btnCreateReports");
        btnBack = (JButton) TestUtils.getChildNamed(fuelEfficiencyReportsMenu, "btnBack");
    }

    /**
     * @brief "CREATE REPORTS" butonunun testi.
     */
    @Test
    public void testCreateReportsButton() {
        assertNotNull(btnCreateReports);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnCreateReports.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("CREATE REPORTS", btnCreateReports.getText());
    }

    /**
     * @brief "BACK" butonunun testi.
     */
    @Test
    public void testBackButton() {
        assertNotNull(btnBack);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnBack.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("BACK", btnBack.getText());
    }

    /**
     * @brief "CREATE REPORTS" butonunun eyleminin testi.
     */
    @Test
    public void testCreateReportsButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnCreateReports.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    /**
     * @brief "BACK" butonunun eyleminin testi.
     */
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
