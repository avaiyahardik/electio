package DAO;

import Model.ElectionCommissioner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDAOImplementation {

    private Connection con;

    public DBDAOImplementation() throws SQLException {
        con = DBConnection.getConnection();
    }

    public boolean registerElectionCommissioner(ElectionCommissioner ec) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_election_commissioner VALUES(?,?,?,?,?,?)");
        ps.setString(1, ec.getEmail());
        ps.setString(2, ec.getFirstname());
        ps.setString(3, ec.getLastname());
        ps.setString(4, ec.getMobile());
        ps.setString(5, ec.getOrganization());
        ps.setString(6, ec.getPassword());
        result = ps.execute();
        return result;
    }

    public boolean loginElectionCommissioner(String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election_commissioner WHERE email=? AND password=?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean updateElectionCommissioner(ElectionCommissioner ec) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election_commissioner SET firstname=?, lastname=?, mobile=?, organization=?, password=? WHERE email=?");
        ps.setString(1, ec.getFirstname());
        ps.setString(2, ec.getLastname());
        ps.setString(3, ec.getMobile());
        ps.setString(4, ec.getOrganization());
        ps.setString(5, ec.getPassword());
        ps.setString(6, ec.getEmail());
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }
}
