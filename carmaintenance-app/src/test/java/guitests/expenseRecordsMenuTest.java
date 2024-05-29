package guitests;


import com.ebcak.carmaintenancegui.expenseRecordsMenu;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.*;

public class expenseRecordsMenuTest {

    private expenseRecordsMenu expenseRecordsMenu;
    private JButton btnLogExpense;
    private JButton btnViewExpense;
    private JButton btnBack;

    @Before
    public void setUp() {
        expenseRecordsMenu = new expenseRecordsMenu();
        btnLogExpense = (JButton) TestUtils.getChildNamed(expenseRecordsMenu, "btnLogExpense");
        btnViewExpense = (JButton) TestUtils.getChildNamed(expenseRecordsMenu, "btnViewExpense");
        btnBack = (JButton) TestUtils.getChildNamed(expenseRecordsMenu, "btnBack");
    }

    @Test
    public void testLogExpenseButton() {
        assertNotNull(btnLogExpense);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnLogExpense.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("LOG EXPENSE", btnLogExpense.getText());
    }

    @Test
    public void testViewExpenseButton() {
        assertNotNull(btnViewExpense);
        // Butonun görünür olup olmadığını kontrol et
        assertTrue(btnViewExpense.isVisible());
        // Butonun etiketini kontrol et
        assertEquals("VIEW EXPENSE", btnViewExpense.getText());
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
    public void testLogExpenseButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnLogExpense.doClick();

        // Yeni bir pencere açıldıktan sonra
        Frame[] afterFrames = Frame.getFrames();
        int afterFrameCount = afterFrames.length;

        assertTrue(afterFrameCount > beforeFrameCount);
    }

    @Test
    public void testViewExpenseButtonAction() {
        // Yeni bir pencere açılmadan önce
        Frame[] beforeFrames = Frame.getFrames();
        int beforeFrameCount = beforeFrames.length;

        btnViewExpense.doClick();

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
