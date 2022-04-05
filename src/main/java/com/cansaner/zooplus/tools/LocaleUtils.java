package com.cansaner.zooplus.tools;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by cansaner on 04/04/22.
 */
public class LocaleUtils {
    private static final Map<String, String> currencySymbolMap = new HashMap<String, String>() {{
        put("AED", "د.إ");
        put("AFN", "؋");
        put("ALL", "L");
        put("AMD", "֏");
        put("ANG", "ƒ");
        put("AOA", "Kz");
        put("ARS", "$");
        put("AUD", "$");
        put("AWG", "ƒ");
        put("AZN", "₼");
        put("BAM", "KM");
        put("BBD", "$");
        put("BDT", "৳");
        put("BGN", "лв");
        put("BHD", ".د.ب");
        put("BIF", "FBu");
        put("BMD", "$");
        put("BND", "$");
        put("BOB", "$b");
        put("BOV", "BOV");
        put("BRL", "R$");
        put("BSD", "$");
        put("BTC", "₿");
        put("BTN", "Nu.");
        put("BWP", "P");
        put("BYN", "Br");
        put("BYR", "Br");
        put("BZD", "BZ$");
        put("CAD", "$");
        put("CDF", "FC");
        put("CHE", "CHE");
        put("CHF", "CHF");
        put("CHW", "CHW");
        put("CLF", "CLF");
        put("CLP", "$");
        put("CNY", "¥");
        put("COP", "$");
        put("COU", "COU");
        put("CRC", "₡");
        put("CUC", "$");
        put("CUP", "₱");
        put("CVE", "$");
        put("CZK", "Kč");
        put("DJF", "Fdj");
        put("DKK", "kr");
        put("DOP", "RD$");
        put("DZD", "دج");
        put("EEK", "kr");
        put("EGP", "£");
        put("ERN", "Nfk");
        put("ETB", "Br");
        put("ETH", "Ξ");
        put("EUR", "€");
        put("FJD", "$");
        put("FKP", "£");
        put("GBP", "£");
        put("GEL", "₾");
        put("GGP", "£");
        put("GHC", "₵");
        put("GHS", "GH₵");
        put("GIP", "£");
        put("GMD", "D");
        put("GNF", "FG");
        put("GTQ", "Q");
        put("GYD", "$");
        put("HKD", "$");
        put("HNL", "L");
        put("HRK", "kn");
        put("HTG", "G");
        put("HUF", "Ft");
        put("IDR", "Rp");
        put("ILS", "₪");
        put("IMP", "£");
        put("INR", "₹");
        put("IQD", "ع.د");
        put("IRR", "﷼");
        put("ISK", "kr");
        put("JEP", "£");
        put("JMD", "J$");
        put("JOD", "JD");
        put("JPY", "¥");
        put("KES", "KSh");
        put("KGS", "лв");
        put("KHR", "៛");
        put("KMF", "CF");
        put("KPW", "₩");
        put("KRW", "₩");
        put("KWD", "KD");
        put("KYD", "$");
        put("KZT", "₸");
        put("LAK", "₭");
        put("LBP", "£");
        put("LKR", "₨");
        put("LRD", "$");
        put("LSL", "M");
        put("LTC", "Ł");
        put("LTL", "Lt");
        put("LVL", "Ls");
        put("LYD", "LD");
        put("MAD", "MAD");
        put("MDL", "lei");
        put("MGA", "Ar");
        put("MKD", "ден");
        put("MMK", "K");
        put("MNT", "₮");
        put("MOP", "MOP$");
        put("MRO", "UM");
        put("MRU", "UM");
        put("MUR", "₨");
        put("MVR", "Rf");
        put("MWK", "MK");
        put("MXN", "$");
        put("MXV", "MXV");
        put("MYR", "RM");
        put("MZN", "MT");
        put("NAD", "$");
        put("NGN", "₦");
        put("NIO", "C$");
        put("NOK", "kr");
        put("NPR", "₨");
        put("NZD", "$");
        put("OMR", "﷼");
        put("PAB", "B/.");
        put("PEN", "S/.");
        put("PGK", "K");
        put("PHP", "₱");
        put("PKR", "₨");
        put("PLN", "zł");
        put("PYG", "Gs");
        put("QAR", "﷼");
        put("RMB", "￥");
        put("RON", "lei");
        put("RSD", "Дин.");
        put("RUB", "₽");
        put("RWF", "R₣");
        put("SAR", "﷼");
        put("SBD", "$");
        put("SCR", "₨");
        put("SDG", "ج.س.");
        put("SEK", "kr");
        put("SGD", "S$");
        put("SHP", "£");
        put("SLL", "Le");
        put("SOS", "S");
        put("SRD", "$");
        put("SSP", "£");
        put("STD", "Db");
        put("STN", "Db");
        put("SVC", "$");
        put("SYP", "£");
        put("SZL", "E");
        put("THB", "฿");
        put("TJS", "SM");
        put("TMT", "T");
        put("TND", "د.ت");
        put("TOP", "T$");
        put("TRL", "₤");
        put("TRY", "₺");
        put("TTD", "TT$");
        put("TVD", "$");
        put("TWD", "NT$");
        put("TZS", "TSh");
        put("UAH", "₴");
        put("UGX", "USh");
        put("USD", "$");
        put("UYI", "UYI");
        put("UYU", "$U");
        put("UYW", "UYW");
        put("UZS", "лв");
        put("VEF", "Bs");
        put("VES", "Bs.S");
        put("VND", "₫");
        put("VUV", "VT");
        put("WST", "WS$");
        put("XAF", "FCFA");
        put("XBT", "Ƀ");
        put("XCD", "$");
        put("XOF", "CFA");
        put("XPF", "₣");
        put("XSU", "Sucre");
        put("XUA", "XUA");
        put("YER", "﷼");
        put("ZAR", "R");
        put("ZMW", "ZK");
        put("ZWD", "Z$");
        put("ZWL", "$");
    }};

    public static String resolveCurrencySymbol(String currencyCode) {
        String symbol = currencySymbolMap.get(currencyCode);
        if (symbol == null || symbol.trim().isEmpty()) {
            return currencyCode;
        }
        return symbol;
    }

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
