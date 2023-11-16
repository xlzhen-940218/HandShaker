package smartisanos.widget.letters;

import java.util.Locale;

/* renamed from: smartisanos.widget.letters.a */
/* loaded from: classes.dex */
public final class AlphabetsConfig {

    /* renamed from: a */
    private static String[] f4553a = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ".>"};

    /* renamed from: b */
    private static String[] f4554b = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", ".~", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ".>"};

    /* renamed from: c */
    private static String[] f4555c = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ", "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ま", "み", "む", "め", "も", "や", "*い", "ゆ", "*え", "よ", "ら", "り", "る", "れ", "ろ", "わ", "*い", "*う", "*え", "を", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ".~", ".~", ".~", ".>"};

    /* renamed from: d */
    private static String[] f4556d = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я", ".~", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ".>"};

    /* renamed from: a */
    public static boolean m4a(String str) {
        return Locale.CHINESE.getLanguage().equals(str) || Locale.ENGLISH.getLanguage().equals(str) || Locale.KOREA.getLanguage().equals(str) || Locale.JAPAN.getLanguage().equals(str) || "ru".equals(str);
    }

    /* renamed from: b */
    public static int m3b(String str) {
        if (Locale.CHINESE.getLanguage().equals(str)) {
            return 0;
        }
        if (Locale.ENGLISH.getLanguage().equals(str)) {
            return 1;
        }
        if (Locale.KOREA.getLanguage().equals(str)) {
            return 0;
        }
        return (Locale.JAPAN.getLanguage().equals(str) || "ru".equals(str)) ? 1 : 0;
    }
}
