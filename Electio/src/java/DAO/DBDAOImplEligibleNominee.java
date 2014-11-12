/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.EligibleNominee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplEligibleNominee {

    private Connection con;
    private static DBDAOImplEligibleNominee obj = null;

    private DBDAOImplEligibleNominee() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplEligibleNominee getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplEligibleNominee();
        }
        return obj;
    }

    public boolean addProbableNominee(EligibleNominee pn) throws SQLException {
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

    public boolean changeProbableNomineeStatus(EligibleNominee pn) throws SQLException {
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

    public ArrayList<EligibleNominee> getAllProbableNominees(long election_id) throws SQLException {
        ArrayList<EligibleNominee> pns = new ArrayList<>();
        EligibleNominee pn = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_probable_nominee WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pn = new EligibleNominee();
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

    public boolean checkEmailExists(String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_probable_nominee WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }
}
