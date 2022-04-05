package com.cansaner.zooplus.tools;

import java.util.Locale;

/**
 * Created by cansaner on 04/04/22.
 */
public class LocaleUtils {
    public static Locale resolveMessage(String countryCode) {
        switch (countryCode) {
            case "DE":
                return Locale.GERMAN;
            case "FR":
                return Locale.FRENCH;
            case "TR":
                return new Locale("tr", "TR");
            default:
                return Locale.ENGLISH;
        }
    }
}
