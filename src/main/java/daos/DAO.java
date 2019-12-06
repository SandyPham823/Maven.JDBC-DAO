package daos;

import models.DTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements DAOinterface {

    public DTO findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM people WHERE id=" + id);
            if(rs.next()){
                return extractDTOfromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List findAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            List users = new ArrayList<DTO>();
            while(rs.next()){
                DTO user = extractDTOfromResultSet(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean update(DTO dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE people SET first_name=?, last_name=?, email=?, gender=?, state=? WHERE id=?");
            ps.setString(1, dto.getFirst_name());
            ps.setString(2, dto.getLast_name());
            ps.setString(3, dto.getEmail());
            ps.setString(4, dto.getGender());
            ps.setString(5, dto.getState());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean create(DTO dto) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("\"INSERT INTO people VALUES (NOT NULL, ?, ?, ?, ?, ?)\"");
            ps.setString(1, dto.getFirst_name());
            ps.setString(2, dto.getLast_name());
            ps.setString(3, dto.getEmail());
            ps.setString(4, dto.getGender());
            ps.setString(5, dto.getState());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public Boolean delete(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM people WHERE id=" + id);
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private DTO extractDTOfromResultSet(ResultSet rs) throws SQLException {
        DTO user = new DTO();
        user.setId( rs.getInt("id") );
        user.setFirst_name( rs.getString("first_name") );
        user.setLast_name( rs.getString("last_name") );
        user.setEmail( rs.getString("email") );
        user.setGender(rs.getString("gender"));
        user.setState(rs.getString("state"));

        return user;
    }
}
