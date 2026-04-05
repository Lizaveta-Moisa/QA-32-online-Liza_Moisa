package util;

import java.util.Random;

public class RandomUtils {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    /**
     * Генерирует случайную строку из маленьких латинских букв указанной длины.
     * @param length длина строки
     * @return случайная строка
     */
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return sb.toString();
    }
}
