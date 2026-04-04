package service.team;

import common.DbConnection;
import entity.team.TeamEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamDatabaseService {

    private final DbConnection dbConnection = DbConnection.getInstance();

    public Optional<TeamEntity> getTeamById(Long id) {
        String sql = "SELECT id, name FROM teams WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTeam(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch team by id: " + id, e);
        }

        return Optional.empty();
    }

    public Optional<TeamEntity> getTeamByName(String name) {
        String sql = "SELECT id, name FROM teams WHERE name = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTeam(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch team by name: " + name, e);
        }

        return Optional.empty();
    }

    public List<TeamEntity> getAllTeams() {
        String sql = "SELECT id, name FROM teams";
        List<TeamEntity> teams = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                teams.add(mapTeam(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch teams", e);
        }

        return teams;
    }

    public TeamEntity saveTeam(TeamEntity team) {
        String sql = "INSERT INTO teams (name) VALUES (?) RETURNING id, name";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, team.getName());

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapTeam(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save team: " + team.getName(), e);
        }

        throw new RuntimeException("Team was not saved: " + team.getName());
    }

    public TeamEntity updateTeam(TeamEntity team) {
        String sql = "UPDATE teams SET name = ? WHERE id = ? RETURNING id, name";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, team.getName());
            statement.setLong(2, team.getId());

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapTeam(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update team with id: " + team.getId(), e);
        }

        throw new RuntimeException("Team was not updated, id not found: " + team.getId());
    }

    public boolean deleteTeamById(Long id) {
        String sql = "DELETE FROM teams WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete team with id: " + id, e);
        }
    }

    private TeamEntity mapTeam(ResultSet rs) throws SQLException {
        return TeamEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
