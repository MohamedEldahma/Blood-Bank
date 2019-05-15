package com.example.pr_pro.newbloodapplication.helper;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class VaildClass {


    public static boolean validName(String name) {
        final String UserName_Patern = "^[a-z0-9_-]{3,15}$";
        return name.matches(UserName_Patern);

    }

    public static boolean validPhone(String phone) {

        return phone.length() == 11 && TextUtils.isDigitsOnly(phone);

    }


    public static boolean validPassword(String password) {

        return password.length() >= 3;
    }

    public static boolean validConfirmPassword(String passwordConfirm, String fullConfirmPassword) {

        return passwordConfirm.equals(fullConfirmPassword);

    }

    public static boolean validEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean checkUser(String userInput, String userCod) {

        return userInput.equals(userCod);
    }

    public static boolean isEmptyuserEmpty(String text) {
        return TextUtils.isEmpty(text);
    }


}
