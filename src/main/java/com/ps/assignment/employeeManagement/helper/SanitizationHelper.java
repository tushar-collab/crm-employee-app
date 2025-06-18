package com.ps.assignment.employeeManagement.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class SanitizationHelper {

    private static final String sanitizationRegex = "(on.+[^\\}]+}|[^\\w\\s])";

    public static Boolean isSanitizationRequired(String value) {
        Pattern pattern = Pattern.compile(sanitizationRegex,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String doSanitization(String value) {
        String val = value;
        Pattern pattern = Pattern.compile(sanitizationRegex,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            val = value.replaceAll(sanitizationRegex, "");
        }
        return val;
    }

}
