package common.utills;

import repository.PlayerRepository;

import java.util.Random;

/**
 * Простая генерация тестовых данных для игроков
 */
public class TestDataGenerator {

    private static final int MAX_RETRIES = 5;
    private static final Random RANDOM = new Random();

    /**
     * Генерирует уникальное имя игрока с проверкой в БД
     */
    public static String generateUniquePlayerName() {
        for (int i = 0; i < MAX_RETRIES; i++) {
            String name = "Player_" + RANDOM.nextInt(1_000_000);  // случайное число до миллиона
            if (!PlayerRepository.playerExists(name)) {
                return name;
            }
        }
        throw new RuntimeException("Не удалось сгенерировать уникальное имя игрока");
    }
}
