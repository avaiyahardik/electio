/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Election;
import Model.ElectionType;
import Model.ProbableNominee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplProbableNominee {

    private Connection con;
    private static DBDAOImplProbableNominee obj = null;

    private DBDAOImplProbableNominee() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplProbableNominee getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplProbableNominee();
        }
        return obj;
    }

    public boolean addProbableNominee(ProbableNominee pn) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_probable_nominee VALUES(?,?,?)");
        ps.setLong(1, pn.getElection_id());
        ps.setString(2, pn.getEmail());
        ps.setInt(3, pn.getStatus());

        if (ps.executeUpdate() > 0) {
            result = true;
        }

        return result;
    }

    public boolean changeProbableNomineeStatus(ProbableNominee pn) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_probable_nominee SET status=? WHERE election_id=? AND email=?");
        ps.setInt(1, pn.getStatus());
        ps.setLong(2, pn.getElection_id());
        ps.setString(3, pn.getEmail());

        if (ps.executeUpdate() > 0) {
            result = true;
        }

        return result;
    }

    public ArrayList<ProbableNominee> getAllProbableNominees(long election_id) throws SQLException {
        ArrayList<ProbableNominee> pns = new ArrayList<>();
        ProbableNominee pn = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_probable_nominee WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pn = new ProbableNominee();
            pn.setElection_id(election_id);;
            pn.setEmail(rs.getString("email"));
            pn.setStatus(rs.getInt("status"));
            pns.add(pn);
        }
        return pns;
    }

    public String[] getAllProbableNomineesEmail(long election_id) throws SQLException {
        ArrayList<String> emails = new ArrayList<>();
        String email = null;
        PreparedStatement ps = con.prepareStatement("SELECT email FROM tbl_probable_nominee WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            emails.add(rs.getString("email"));
        }
        return emails.toArray(new String[]{});
    }

    public boolean deleteProbableNominee(long election_id, String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_probable_nominee WHERE election_id=? AND email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateProbableNominee(long election_id, String old_email, String new_email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_probable_nominee SET email=? WHERE email=? AND election_id=?");
        ps.setString(1, new_email);
        ps.setString(2, old_email);
        ps.setLong(3, election_id);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean deleteProbableNomineeForElection(long election_id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_probable_nominee WHERE election_id=?");
        ps.setLong(1, election_id);
        if (ps.execute()) {
            result = true;
        }
        return result;
    }

}
