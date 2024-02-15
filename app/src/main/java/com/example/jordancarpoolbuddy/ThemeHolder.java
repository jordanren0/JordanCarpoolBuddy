package com.example.jordancarpoolbuddy;

/**
 * @author Jordan Ren
 * @version 1.0
 */
public class ThemeHolder {
    public static String CURRENT_THEME = "light";

    /**
     * This method allows the user to set the current theme (Used in other classes)
     * @param theme
     */
    public static void setCurrentTheme(String theme) {
        CURRENT_THEME = theme;
    }

    /**
     * This method allows the user to get the current theme (Used in other classes)
     * @return String
     */
    public static String getCurrentTheme() {
        return CURRENT_THEME;
    }
}
