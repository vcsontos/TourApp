package hu.bme.aut.mobsoft.tourapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainActivity;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class LoginActivity extends AppCompatActivity implements LoginScreen {

    private static final String TAG = Constants.LOG_PREFIX + LoginActivity.class.getSimpleName();

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.usernameLoginEditText)
    EditText usernameEditText;

    @BindView(R.id.passwordLoginEditText)
    EditText passwordEditText;

    @BindView(R.id.loginProgressBar)
    ProgressBarCircularIndeterminate progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        TourApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.loginBtn)
    public void clickLoginBtn() {
        Log.d(TAG, "clickLoginBtn() called");
        usernameEditText.setError(null);
        passwordEditText.setError(null);
        loginPresenter.login(usernameEditText.getText().toString(),
                passwordEditText.getText().toString());
    }
}
