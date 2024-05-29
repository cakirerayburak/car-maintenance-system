package guitests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.ebcak.carmaintenancegui.signInScreen;
import com.ebcak.carmaintenancegui.userControl;

import javax.swing.*;

public class signInScreenTest {

    @Mock
    private userControl mockUserControl;

    private signInScreen signInScreen;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnSignIn;
    private JButton btnSignUp;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signInScreen = new signInScreen(mockUserControl);
        usernameField = (JTextField) TestUtils.getChildNamed(signInScreen, "usernameField");
        passwordField = (JPasswordField) TestUtils.getChildNamed(signInScreen, "passwordField");
        btnSignIn = (JButton) TestUtils.getChildNamed(signInScreen, "btnSignIn");
        btnSignUp = (JButton) TestUtils.getChildNamed(signInScreen, "btnSignUp");
    }

    @Test
    public void testSignInSuccess() {
        when(mockUserControl.loginUser("validUsername", "validPassword")).thenReturn(true);

        usernameField.setText("validUsername");
        passwordField.setText("validPassword");

        btnSignIn.doClick();

        assertTrue(signInScreen.isNextScreenVisible());
        verify(mockUserControl).loginUser("validUsername", "validPassword");
    }

    @Test
    public void testSignInFailure() {
        when(mockUserControl.loginUser("invalidUsername", "invalidPassword")).thenReturn(false);

        usernameField.setText("invalidUsername");
        passwordField.setText("invalidPassword");

        btnSignIn.doClick();

        assertFalse(signInScreen.isNextScreenVisible());
        verify(mockUserControl).loginUser("invalidUsername", "invalidPassword");
    }

    @Test
    public void testSignUpButton() {
        btnSignUp.doClick();
        assertTrue(signInScreen.isSignUpScreenVisible());
    }
}
