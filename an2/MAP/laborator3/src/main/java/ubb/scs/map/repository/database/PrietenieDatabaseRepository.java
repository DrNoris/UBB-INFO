package ubb.scs.map.repository.database;

import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrietenieDatabaseRepository implements Repository<Tuple<Long, Long>, Prietenie> {
    private final String url;
    private final String username;
    private final String password;

    public PrietenieDatabaseRepository(String username, String password, String url) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public Optional<Prietenie> findOne(Tuple<Long, Long> id) {
        String sql = "SELECT * FROM Prietenie WHERE user_id1 = ? AND user_id2 = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id.getLeft());
            stmt.setLong(2, id.getRight());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Prietenie prietenie = mapResultSetToPrietenie(rs);
                return Optional.of(prietenie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Prietenie> findAll() {
        List<Prietenie> friendships = new ArrayList<>();
        String sql = "SELECT * FROM Prietenie";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prietenie prietenie = mapResultSetToPrietenie(rs);
                friendships.add(prietenie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Optional<Prietenie> save(Prietenie entity) {
        String sql = "INSERT INTO Prietenie (user_id1, user_id2, date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, entity.getId().getLeft());
            stmt.setLong(2, entity.getId().getRight());
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getDate()));
            stmt.executeUpdate();
            return Optional.empty(); // No existing entity to return in this case
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Prietenie> delete(Tuple<Long, Long> id) {
        Optional<Prietenie> existingFriendship = findOne(id);
        if (existingFriendship.isPresent()) {
            String sql = "DELETE FROM Prietenie WHERE user_id1 = ? AND user_id2 = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, id.getLeft());
                stmt.setLong(2, id.getRight());
                stmt.executeUpdate();
                return existingFriendship;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Prietenie> update(Prietenie entity) {
        String sql = "UPDATE Prietenie SET date = ? WHERE user_id1 = ? AND user_id2 = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(entity.getDate()));
            stmt.setLong(2, entity.getId().getLeft());
            stmt.setLong(3, entity.getId().getRight());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return Optional.empty(); // Return empty if update was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(entity);
    }

    private Prietenie mapResultSetToPrietenie(ResultSet rs) throws SQLException {
        Long userId1 = rs.getLong("user_id1");
        Long userId2 = rs.getLong("user_id2");
        LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
        Prietenie prietenie = new Prietenie();
        prietenie.setId(new Tuple<>(userId1, userId2));
        prietenie.setDate(date);
        return prietenie;
    }
}
