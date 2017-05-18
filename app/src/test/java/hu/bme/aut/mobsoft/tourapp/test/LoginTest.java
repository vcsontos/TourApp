package hu.bme.aut.mobsoft.tourapp.test;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import hu.bme.aut.mobsoft.tourapp.BuildConfig;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginActivity;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginScreen;
import hu.bme.aut.mobsoft.tourapp.utils.RobolectricDaggerTestRunner;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;

import static hu.bme.aut.mobsoft.tourapp.TestHelper.setTestInjector;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by valentin on 2017. 05. 15..
 */

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    private LoginPresenter loginPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        Context context = Robolectric.setupActivity(LoginActivity.class);
        Hawk.init(context).build();
        loginPresenter = new LoginPresenter();
    }

    @Test
    public void testWhenUsernameAndPasswordAreNull() {
        // GIVEN
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);

        // WHEN
        loginPresenter.login(null, null);

        // THEN
        verify(loginScreen, times(1)).showProgressBar();
        verify(loginScreen, times(1)).hideProgressBar();
        verify(loginScreen, times(1)).showMessage(R.string.invalid_login_credentials);
        verify(loginScreen, never()).navigateToHome();
        Assert.assertFalse(Utils.isUserLoggedIn());
    }

    @Test
    public void testWhenInvalidCredentials() {
        // GIVEN
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);

        // WHEN
        loginPresenter.login("", "");

        // THEN
        verify(loginScreen, times(1)).showProgressBar();
        verify(loginScreen, times(1)).hideProgressBar();
        verify(loginScreen, times(1)).showMessage(R.string.invalid_login_credentials);
        verify(loginScreen, never()).navigateToHome();
        Assert.assertFalse(Utils.isUserLoggedIn());
    }

    @Test
    public void testWhenUserNotFound() {
        // GIVEN
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        Repository repository = mock(Repository.class);

        // WHEN
        when(repository.getUser("asd", "asd")).thenReturn(null);
        loginPresenter.login("asd", "asd");

        // THEN
        verify(loginScreen, times(1)).showProgressBar();
        verify(loginScreen, times(1)).hideProgressBar();
        verify(loginScreen, times(1)).showMessage(R.string.invalid_login_credentials);
        verify(loginScreen, never()).navigateToHome();
        Assert.assertFalse(Utils.isUserLoggedIn());
    }

    @Test(expected = Exception.class)
    public void testWhenLoginThrowsException() {
        // GIVEN
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        Repository repository = mock(Repository.class);

        // WHEN
        when(repository.getUser("asd", "asd")).thenThrow(new Exception("Repository is not exist"));
        loginPresenter.login("asd", "asd");

        // THEN
        verify(loginScreen, times(1)).showProgressBar();
        verify(loginScreen, times(1)).hideProgressBar();
        verify(loginScreen, times(1)).navigateToHome();
        verify(loginScreen, never()).showMessage(anyInt());
        Assert.assertTrue(Utils.isUserLoggedIn());
    }

    @Test
    public void testWhenLoginSuccessful() {
        // GIVEN
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        Repository repository = mock(Repository.class);

        // WHEN
        when(repository.getUser("user", "user")).thenReturn(new User());
        loginPresenter.login("user", "user");

        // THEN
        verify(loginScreen, times(1)).showProgressBar();
        verify(loginScreen, times(1)).hideProgressBar();
        verify(loginScreen, times(1)).navigateToHome();
        verify(loginScreen, never()).showMessage(anyInt());
        Assert.assertTrue(Utils.isUserLoggedIn());
    }

    @After
    public void tearDown() {
        loginPresenter.detachScreen();
        Hawk.destroy();
    }
}
