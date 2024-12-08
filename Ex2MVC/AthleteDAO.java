package Ex2MVC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO implements DAO<Athlete> {
    private final Connection connection = Controller.getConnection();

    @Override
    public void add(Athlete athlete) {
        String query = "INSERT INTO DEPORTISTAS (nombre, cod_deporte) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, athlete.getName());
            pstmt.setInt(2, athlete.getSportId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Athlete> getAll() {
        List<Athlete> athletes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM DEPORTISTAS")) {
            while (rs.next()) {
                athletes.add(new Athlete(rs.getInt("COD"), rs.getString("NOMBRE"), rs.getInt("COD_DEPORTE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }
    
    public List<Athlete> findByName(String name) {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT * FROM DEPORTISTAS WHERE NOMBRE LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                athletes.add(new Athlete(rs.getInt("COD"), rs.getString("NOMBRE"), rs.getInt("COD_DEPORTE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }
    
    public List<Athlete> findBySportId(int sportId) {
        List<Athlete> athletes = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT D.COD, D.NOMBRE, DEP.NOMBRE AS SPORT_NAME " +
                "FROM DEPORTISTAS D JOIN DEPORTES DEP ON D.COD_DEPORTE = DEP.COD " +
                "WHERE DEP.COD = ?")) {
            pstmt.setInt(1, sportId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                athletes.add(new Athlete(rs.getInt("COD"), rs.getString("NOMBRE"), sportId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }


}
