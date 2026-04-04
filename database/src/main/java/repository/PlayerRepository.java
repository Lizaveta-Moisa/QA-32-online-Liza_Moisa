package repository;

import java.sql.*;

public class PlayerRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/basketball";
    private static final String USER = "vibe";
    private static final String PASSWORD = "vibe";

    /**
     * Проверка существования игрока по имени
     */
    public static boolean playerExists(String name) {
        String sql = "SELECT COUNT(*) FROM players WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Создание игрока, возвращает ID
     */
    public int createPlayer(String name) {
        String sql = "INSERT INTO players(name) VALUES (?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Удаление игрока по ID
     */
    public boolean deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
