package newagency.picfav.view.sign.up.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.util.AppUtils;
import newagency.picfav.util.DateTimeUtil;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.login.view.LoginActivity;
import newagency.picfav.view.sign.up.SignUpContract;
import newagency.picfav.view.sign.up.SignUpValidator;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    public static void launch(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SignUpActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Inject
    SignUpContract.PresenterI presenterI;

    @BindView(R.id.root)
    ConstraintLayout mRoot;

    @BindView(R.id.date_of_birth)
    TextInputLayout etDateOfBirth;

    @BindView(R.id.et_first_name)
    TextInputLayout etFirstName;

    @BindView(R.id.et_last_name)
    TextInputLayout etLastName;

    @BindView(R.id.et_username)
    TextInputLayout etUsername;

    @BindView(R.id.et_password)
    TextInputLayout etPassword;

    @BindView(R.id.btn_signup)
    AppCompatButton btnSignUp;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                etDateOfBirth.setError(null);
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        etDateOfBirth.getEditText().setText(DateTimeUtil.toNewFormat(date));
                    }
                });
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
            }
            return false;
        }
    };

    @Override
    protected void onViewReady() {
        initDateOfBirth();
    }

    @Override
    protected void onViewDestroy() {
        presenterI.onStop();
        etDateOfBirth.getEditText().setOnTouchListener(null);
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_sign_up;
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        AppUtils.hideSoftInputFromWindow(getCurrentFocus());
        onBackPressed();
    }

    @OnClick(R.id.btn_signup)
    void signIn() {
        AppUtils.hideSoftInputFromWindow(getCurrentFocus());
        if (checkFields()) {
            presenterI.signUp(getFieldValue(etFirstName), getFieldValue(etLastName),
                    getFieldValue(etUsername), getFieldValue(etPassword), getFieldValue(etDateOfBirth));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initDateOfBirth() {
        etDateOfBirth.getEditText().setOnTouchListener(mOnTouchListener);
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    //  SignUpContract.View methods
    @Override
    public void showProgress() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String error) {
        Snackbar.make(mRoot, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void redirectToLogin() {
        finish();
        LoginActivity.start(this);
    }

    private String getFieldValue(TextInputLayout field) {
        if (field.getEditText() == null) return "";
        return field.getEditText().getText().toString();
    }

    private boolean checkFields() {
        clearAllError();
        String isSignUpValid = SignUpValidator.isFirstNameValid(getFieldValue(etFirstName));
        if (isSignUpValid != null) {
            etFirstName.getEditText().setText("");
            etFirstName.setError(isSignUpValid);
            return false;
        }

        String isLastNameValid = SignUpValidator.isLastNameValid(getFieldValue(etLastName));
        if (isLastNameValid != null) {
            etLastName.getEditText().setText("");
            etLastName.setError(isLastNameValid);
            return false;
        }

        String isUsernameValid = SignUpValidator.isUsernameValid(getFieldValue(etUsername));
        if (isUsernameValid != null) {
            etUsername.getEditText().setText("");
            etUsername.setError(isUsernameValid);
            return false;
        }

        String isPasswordValid = SignUpValidator.isPasswordValid(getFieldValue(etPassword));
        if (isPasswordValid != null) {
            etPassword.getEditText().setText("");
            etPassword.setError(isPasswordValid);
            return false;
        }

        String isDateOfBirthValid = SignUpValidator.isDateOfBirthValid(getFieldValue(etDateOfBirth));
        if (isDateOfBirthValid != null) {
            etDateOfBirth.getEditText().setText("");
            etDateOfBirth.setError(isDateOfBirthValid);
            return false;
        }
        return true;
    }

    private void clearAllError() {
        etFirstName.setError(null);
        etLastName.setError(null);
        etUsername.setError(null);
        etPassword.setError(null);
        etDateOfBirth.setError(null);
    }
}
