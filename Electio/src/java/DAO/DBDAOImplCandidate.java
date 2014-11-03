/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.Candidate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplCandidate {
         private Connection con;
    private static DBDAOImplCandidate obj = null;

    private DBDAOImplCandidate() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplCandidate getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplCandidate();
        }
        return obj;
    }
     public boolean candidateLogin(long election_id, String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_user_info u tbl_election_candidate c ON u.email=c.email WHERE election_id=? AND u.email=? AND password=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }

        return result;
    }
       public Candidate getCandidate(long election_id, String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from tbl_user_info as u INNER JOIN tbl_election_candidate as c ON u.email=c.email WHERE election_id=? and u.email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        Candidate candidate = null;
        if (rs.next()) {
            candidate = new Candidate();
            candidate.setEmail(rs.getString("email"));
            candidate.setFirstname(rs.getString("firstname"));
            candidate.setLastname(rs.getString("lastname"));
            candidate.setGender(rs.getInt("gender"));
            candidate.setMobile(rs.getString("mobile"));
            candidate.setOrganization_id(rs.getLong("organization_id"));
            candidate.setImage(rs.getString("image"));
            candidate.setPassword(rs.getString("password"));
            candidate.setElection_id(rs.getLong("election_id"));
            candidate.setRequirements_file(rs.getString("requirements_file"));

        }
        return candidate;
    }

    public ArrayList<Candidate> getCandidates(long election_id) throws SQLException {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_user_info AS u INNER JOIN tbl_election_candidate AS c ON u.email=c.email WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        Candidate candidate;
        while (rs.next()) {
            candidate = new Candidate();
            candidate.setEmail(rs.getString("email"));
            candidate.setFirstname(rs.getString("firstname"));
            candidate.setLastname(rs.getString("lastname"));
            candidate.setGender(rs.getInt("gender"));
            candidate.setMobile(rs.getString("mobile"));
            candidate.setOrganization_id(rs.getLong("organization_id"));
            candidate.setImage(rs.getString("image"));
            candidate.setPassword(rs.getString("password"));
            candidate.setElection_id(rs.getLong("election_id"));
            candidate.setRequirements_file(rs.getString("requirements_file"));
            candidate.setVotes(rs.getLong("votes"));
            candidate.setManifesto(rs.getString("manifesto"));
            candidates.add(candidate);
        }
        return candidates;
    }
   public boolean updateCandidateVotes(ArrayList<Candidate> candidates, long election_id) throws SQLException {
        boolean result = false;

        for (Candidate c : candidates) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election_candidate WHERE election_id=? and email=?");
            ps.setLong(1, election_id);
            ps.setString(2, c.getEmail());
            ResultSet rs = ps.executeQuery();
            long votes = 0;
            if (rs.next()) {
                votes = rs.getLong("votes");
            }
            votes += c.getVotes();

            PreparedStatement ps2 = con.prepareStatement("UPDATE tbl_election_candidate SET votes =? WHERE election_id=? and email=?");
            ps2.setLong(1, votes);
            ps2.setLong(2, election_id);
            ps2.setString(3, c.getEmail());
            if (ps2.executeUpdate() > 0) {
                result = true;
            }
        }
        return result;
    }
}
