package com.example.jordancarpoolbuddy;

public class ThemeHolder {
    public static String CURRENT_THEME = "light";

    public static void setCurrentTheme(String theme) {
        CURRENT_THEME = theme;
    }

    public static String getCurrentTheme() {
        return CURRENT_THEME;
    }
}
