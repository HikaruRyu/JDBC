package Ex2MVC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDAO implements DAO<Sport> {
    private final Connection connection = Controller.getConnection();

    @Override
    public void add(Sport sport) {
        try (Statement stmt = connection.createStatement()) {
            String query = "INSERT INTO deportes (nombre) VALUES ('" + sport.getName() + "')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sport> getAll() {
        List<Sport> sports = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM deportes")) {
            while (rs.next()) {
                sports.add(new Sport(rs.getInt("cod"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sports;
    }
}
