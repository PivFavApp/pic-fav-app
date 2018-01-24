package newagency.picfav.view.sign.up.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
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
import newagency.picfav.util.DateTimeUtil;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.sign.up.SignUpContract;
import newagency.picfav.view.sign.up.SignUpValidator;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    public static void launch(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SignUpActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Inject
    SignUpContract.PresenterI presenterI;

    @BindView(R.id.date_of_birth)
    TextInputEditText etDateOfBirth;

    @BindView(R.id.et_first_name)
    TextInputEditText etFirstName;

    @BindView(R.id.et_last_name)
    TextInputEditText etLastName;

    @BindView(R.id.et_username)
    TextInputEditText etUsername;

    @BindView(R.id.et_password)
    TextInputEditText etPassword;

    @BindView(R.id.btn_signup)
    AppCompatButton btnSignUp;

    @Override
    protected void onViewReady() {
        initDateOfBirth();
    }

    @Override
    protected void onViewDestroy() {
        presenterI.onStop();
    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_sign_up;
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.btn_signup)
    void signIn() {
        if (checkFields()) {
            //TODO
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initDateOfBirth() {
        etDateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    etDateOfBirth.setError(null);
                    DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            etDateOfBirth.setText(DateTimeUtil.toNewFormat(date));
                        }
                    });
                    datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
                }
                return false;
            }
        });
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    private boolean checkFields() {
        String isSignUpValid = SignUpValidator.isFirstNameValid(etFirstName.getText().toString());
        if (isSignUpValid != null) {
            etFirstName.setText("");
            etFirstName.setError(isSignUpValid);
            return false;
        }

        String isLastNameValid = SignUpValidator.isLastNameValid(etLastName.getText().toString());
        if (isLastNameValid != null) {
            etLastName.setText("");
            etLastName.setError(isLastNameValid);
            return false;
        }

        String isUsernameValid = SignUpValidator.isUsernameValid(etUsername.getText().toString());
        if (isUsernameValid != null) {
            etUsername.setText("");
            etUsername.setError(isUsernameValid);
            return false;
        }

        String isPasswordValid = SignUpValidator.isPasswordValid(etPassword.getText().toString());
        if (isPasswordValid != null) {
            etPassword.setText("");
            etPassword.setError(isPasswordValid);
            return false;
        }

        String isDateOfBirthValid = SignUpValidator.isDateOfBirthValid(etDateOfBirth.getText().toString());
        if (isDateOfBirthValid != null) {
            etDateOfBirth.setText("");
            etDateOfBirth.setError(isDateOfBirthValid);
            return false;
        }
        return true;
    }
}
