/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.Voter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplVoter {
        private Connection con;
    private static DBDAOImplVoter obj = null;

    private DBDAOImplVoter() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplVoter getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplVoter();
        }
        return obj;
    }
    /*
     public boolean addVoter(Voter voter) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_voter VALUES(?,?,?,?)");
        ps.setString(1, voter.getEmail());
        ps.setLong(2, voter.getElection_id());
        ps.setString(3, voter.getPassword());
        ps.setBoolean(4, voter.getStatus());
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateVoter(long election_id, String old_email, String new_email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_voter SET email=? WHERE email=? AND election_id=?");
        ps.setString(1, new_email);
        ps.setString(2, old_email);
        ps.setLong(3, election_id);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean insertVoterPassword(long election_id, String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_voter SET password=? WHERE email=? AND election_id=?");
        ps.setString(1, password);
        ps.setString(2, email);
        ps.setLong(3, election_id);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean deleteVoter(String email, long election_id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_voter WHERE election_id=? AND email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public Voter getVoter(String email, long election_id) throws SQLException {
        Voter voter = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_voter WHERE email=? and election_id=?");
        ps.setString(1, email);
        ps.setLong(2, election_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            voter = new Voter();
            voter.setEmail(email);
            voter.setElection_id(election_id);
            voter.setPassword(rs.getString("password"));
            voter.setStatus(rs.getBoolean("status"));
        }
        return voter;
    }

    public ArrayList<Voter> getVoters(long election_id) throws SQLException {
        ArrayList<Voter> voters = new ArrayList<Voter>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_voter WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        Voter voter;
        while (rs.next()) {
            voter = new Voter();
            voter.setEmail(rs.getString("email"));
            voter.setElection_id(election_id);
            voter.setPassword(rs.getString("password"));
            voter.setStatus(rs.getBoolean("status"));
            voters.add(voter);
        }
        return voters;
    }
    public Voter loginVoter1(long election_id, String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_voter WHERE email=? and election_id=?");
        ps.setString(1, email);
        ps.setLong(2, election_id);

        Voter v = new Voter();
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            v.setPassword(rs.getString("password"));
            v.setStatus(rs.getBoolean("status"));
            return v;
        } else {
            return null;
        }

    }

    public boolean loginVoter2(long election_id, String email, String password) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_voter WHERE email=? and election_id=? and password=?");
        ps.setString(1, email);
        ps.setLong(2, election_id);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }
 public boolean saveVote(long election_id, String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election_candidate WHERE election_id=? and email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        long votes = 0;
        if (rs.next()) {
            votes = rs.getLong("votes");
        }
        votes += 1;
        PreparedStatement ps2 = con.prepareStatement("UPDATE tbl_election_candidate SET votes =? WHERE election_id=? and email=?");
        ps2.setLong(1, votes);
        ps2.setLong(2, election_id);
        ps2.setString(3, email);
        if (ps2.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateVoterStatus(long election_id, String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps2 = con.prepareStatement("UPDATE tbl_voter SET status =? WHERE election_id=? and email=?");
        ps2.setInt(1, 1);
        ps2.setLong(2, election_id);
        ps2.setString(3, email);
        if (ps2.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }
*/
}
