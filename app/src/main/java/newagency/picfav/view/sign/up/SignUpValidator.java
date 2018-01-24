package newagency.picfav.view.sign.up;

import android.support.annotation.NonNull;

public class SignUpValidator {
    //if null valid
    public static String isFirstNameValid(@NonNull String firstName) {
        if (firstName.isEmpty()) {
            return "Please enter your First name";
        } else {
            return null;
        }
    }

    public static String isLastNameValid(@NonNull String lastName) {
        if (lastName.isEmpty()) {
            return "Please enter your Last name";
        } else {
            return null;
        }
    }

    public static String isUsernameValid(@NonNull String username) {
        if (username.isEmpty()) {
            return "Please enter your username";
        } else {
            if (username.length() < 8) {
                return "Username should be more than 8 symbol";
            } else {
                return null;
            }
        }
    }

    public static String isPasswordValid(@NonNull String password) {
        if (password.isEmpty()) {
            return "Please enter your password";
        } else {
            if (password.length() < 8) {
                return "Password should be more than 8 symbol";
            } else {
                return null;
            }
        }
    }

    public static String isDateOfBirthValid(@NonNull String dateOfBirth) {
        if (dateOfBirth.isEmpty()) {
            return "Please enter your Date of Birth";
        } else {
            return null;
        }
    }
}
