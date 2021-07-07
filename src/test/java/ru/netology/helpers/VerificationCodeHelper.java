package ru.netology.helpers;

public class VerificationCodeHelper {
    private final String code;

    public VerificationCodeHelper(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static String getInvalidVerificationCode() {
        return "111111";
    }

    public String toString() {
        return "DatabaseHelper.VerificationCode(code=" + this.getCode() + ")";
    }
}
