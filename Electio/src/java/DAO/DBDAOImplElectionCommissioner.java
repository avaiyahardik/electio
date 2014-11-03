/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Election;
import Model.ElectionCommissioner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author darshit
 */
public class DBDAOImplElectionCommissioner {

    private Connection con;
    private static DBDAOImplElectionCommissioner obj = null;

    private DBDAOImplElectionCommissioner() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplElectionCommissioner getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplElectionCommissioner();
        }
        return obj;
    }
     public boolean registerElectionCommissioner(ElectionCommissioner ec) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_election_commissioner VALUES(?,?,?,?,?,?)");
        ps.setString(1, ec.getEmail());
        ps.setString(2, ec.getFirstname());
        ps.setString(3, ec.getLastname());
        ps.setString(4, ec.getMobile());
        ps.setLong(5, ec.getOrganization_id());
        ps.setString(6, ec.getPassword());
        if (ps.executeUpdate() > 0) {
            result = true;
        }
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

    public ElectionCommissioner getElectionCommissioner(String email) throws SQLException {
        ElectionCommissioner ec = new ElectionCommissioner();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election_commissioner WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        ec.setEmail(email);
        if (rs.next()) {
            ec.setFirstname(rs.getString("firstname"));
            ec.setLastname(rs.getString("lastname"));
            ec.setMobile(rs.getString("mobile"));
            ec.setOrganization_id(rs.getLong("organization_id"));
            ec.setPassword(rs.getString("password"));
        }
        return ec;
    }

    public boolean updateElectionCommissioner(ElectionCommissioner ec) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election_commissioner SET firstname=?, lastname=?, mobile=?, organization=?, password=? WHERE email=?");
        ps.setString(1, ec.getFirstname());
        ps.setString(2, ec.getLastname());
        ps.setString(3, ec.getMobile());
        ps.setLong(4, ec.getOrganization_id());
        ps.setString(5, ec.getPassword());
        ps.setString(6, ec.getEmail());
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean createElection(Election el) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_election(election_commissioner_email, name, description, requirements, type_id, nomination_start, nomination_end, withdrawal_start, withdrawal_end, voting_start, voting_end, petition_duration) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, el.getElection_commissioner_email());
        ps.setString(2, el.getName());
        ps.setString(3, el.getDescription());
        ps.setString(4, el.getRequirements());
        ps.setLong(5, el.getType_id());
        // ps.setTimestamp(6, el.getCreated_at());
        ps.setTimestamp(6, el.getNomination_start());
        ps.setTimestamp(7, el.getNomination_end());
        ps.setTimestamp(8, el.getWithdrawal_start());
        ps.setTimestamp(9, el.getNomination_end());
        ps.setTimestamp(10, el.getVoting_start());
        ps.setTimestamp(11, el.getVoting_end());
        ps.setInt(12, el.getPetition_duration());

        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }
     public boolean isValidElectionCommissioner(String email, String password) throws SQLException {
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

    public boolean changeElectionCommissionerPassword(String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election_commissioner SET password=? WHERE email=?");
        ps.setString(1, password);
        ps.setString(2, email);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;

    }
}