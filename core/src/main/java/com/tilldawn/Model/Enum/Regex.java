package com.tilldawn.Model.Enum;

import java.util.regex.Matcher;

public enum Regex {
    PASSWORD_REGEX("^(?=.*[A-Z])(?=.*\\d)(?=.*[_()*&%$#@]).{8,}$");

    private String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }

    
}
