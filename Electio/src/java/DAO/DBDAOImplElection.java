/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Candidate;
import Model.Election;
import Model.ElectionType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplElection {

    private Connection con;
    private static DBDAOImplElection obj = null;

    private DBDAOImplElection() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplElection getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplElection();
        }
        return obj;
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
        ps.setTimestamp(9, el.getWithdrawal_end());
        ps.setTimestamp(10, el.getVoting_start());
        ps.setTimestamp(11, el.getVoting_end());
        ps.setInt(12, el.getPetition_duration());

        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateElection(Election el) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election SET name=?, description=?, requirements=?, type_id=?, nomination_start=?, nomination_end=?, withdrawal_start=?, withdrawal_end=?, voting_start=?, voting_end=?, petition_duration=? WHERE id=?");
        System.out.println(el.getNomination_start() + ", " + el.getNomination_end() + ", " + el.getWithdrawal_start() + ", " + el.getWithdrawal_end() + ", " + el.getVoting_start() + ", " + el.getVoting_end());
        ps.setString(1, el.getName());
        ps.setString(2, el.getDescription());
        ps.setString(3, el.getRequirements());
        ps.setLong(4, el.getType_id());
        ps.setTimestamp(5, el.getNomination_start());
        ps.setTimestamp(6, el.getNomination_end());
        ps.setTimestamp(7, el.getWithdrawal_start());
        ps.setTimestamp(8, el.getWithdrawal_end());
        ps.setTimestamp(9, el.getVoting_start());
        ps.setTimestamp(10, el.getVoting_end());
        ps.setInt(11, el.getPetition_duration());
        ps.setLong(12, el.getId());

        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean deleteElection(String email, long id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_election WHERE election_commissioner_email=? and id=?");
        ps.setString(1, email);
        ps.setLong(2, id);
        ps.executeUpdate();
        result = true;

        System.out.println("election: " + result);
        return result;
    }

    public Election getElection(long id) throws SQLException {
        Election el = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            el = new Election();
            el.setId(id);
            el.setElection_commissioner_email(rs.getString("election_commissioner_email"));
            el.setName(rs.getString("name"));
            el.setDescription(rs.getString("description"));
            el.setRequirements(rs.getString("requirements"));
            el.setType_id(rs.getLong("type_id"));
            el.setCreated_at(rs.getTimestamp("created_at"));
            el.setNomination_start(rs.getTimestamp("nomination_start"));
            el.setNomination_end(rs.getTimestamp("nomination_end"));
            el.setWithdrawal_start(rs.getTimestamp("withdrawal_start"));
            el.setWithdrawal_end(rs.getTimestamp("withdrawal_end"));
            el.setVoting_start(rs.getTimestamp("voting_start"));
            el.setVoting_end(rs.getTimestamp("voting_end"));
            el.setPetition_duration(rs.getInt("petition_duration"));
        }

        return el;
    }

    public Election getElection(long id, String email) throws SQLException {
        Election el = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE id=? AND election_commissioner_email=?");
        ps.setLong(1, id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            el = new Election();
            el.setId(id);
            el.setElection_commissioner_email(rs.getString("election_commissioner_email"));
            el.setName(rs.getString("name"));
            el.setDescription(rs.getString("description"));
            el.setRequirements(rs.getString("requirements"));
            el.setType_id(rs.getLong("type_id"));
            el.setCreated_at(rs.getTimestamp("created_at"));
            el.setNomination_start(rs.getTimestamp("nomination_start"));
            el.setNomination_end(rs.getTimestamp("nomination_end"));
            el.setWithdrawal_start(rs.getTimestamp("withdrawal_start"));
            el.setWithdrawal_end(rs.getTimestamp("withdrawal_end"));
            el.setVoting_start(rs.getTimestamp("voting_start"));
            el.setVoting_end(rs.getTimestamp("voting_end"));
            el.setPetition_duration(rs.getInt("petition_duration"));
        }
        return el;
    }

    public String getElectionName(long id) throws SQLException {
        String name = null;
        PreparedStatement ps = con.prepareStatement("SELECT name FROM tbl_election WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            name = rs.getString("name");
        }
        return name;
    }

    public String getElectionRequirements(long id) throws SQLException {
        String name = null;
        PreparedStatement ps = con.prepareStatement("SELECT requirements FROM tbl_election WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            name = rs.getString("requirements");
        }
        return name;
    }

    public ArrayList<Election> getElections(String email) throws SQLException {
        ArrayList<Election> elections = new ArrayList<Election>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE election_commissioner_email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Election el = null;
        while (rs.next()) {
            el = new Election();
            el.setId(rs.getLong("id"));
            el.setElection_commissioner_email(email);
            el.setName(rs.getString("name"));
            el.setDescription(rs.getString("description"));
            el.setRequirements(rs.getString("requirements"));
            el.setType_id(rs.getLong("type_id"));
            el.setCreated_at(rs.getTimestamp("created_at"));
            el.setNomination_start(rs.getTimestamp("nomination_start"));
            el.setNomination_end(rs.getTimestamp("nomination_end"));
            el.setWithdrawal_start(rs.getTimestamp("withdrawal_start"));
            el.setWithdrawal_end(rs.getTimestamp("withdrawal_end"));
            el.setVoting_start(rs.getTimestamp("voting_start"));
            el.setVoting_end(rs.getTimestamp("voting_end"));
            el.setPetition_duration(rs.getInt("petition_duration"));
            elections.add(el);
        }
        return elections;
    }

    public ArrayList<Election> getCompletedElections(String email) throws SQLException {
        ArrayList<Election> elections = new ArrayList<Election>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE election_commissioner_email=? AND voting_end<NOW() ORDER BY voting_end DESC");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Election el;
        while (rs.next()) {
            el = new Election();
            el.setId(rs.getLong("id"));
            el.setElection_commissioner_email(email);
            el.setName(rs.getString("name"));
            el.setDescription(rs.getString("description"));
            el.setRequirements(rs.getString("requirements"));
            el.setType_id(rs.getLong("type_id"));
            el.setCreated_at(rs.getTimestamp("created_at"));
            el.setNomination_start(rs.getTimestamp("nomination_start"));
            el.setNomination_end(rs.getTimestamp("nomination_end"));
            el.setWithdrawal_start(rs.getTimestamp("withdrawal_start"));
            el.setWithdrawal_end(rs.getTimestamp("withdrawal_end"));
            el.setVoting_start(rs.getTimestamp("voting_start"));
            el.setVoting_end(rs.getTimestamp("voting_end"));
            el.setPetition_duration(rs.getInt("petition_duration"));
            elections.add(el);
        }
        return elections;
    }

    public ElectionType getElectionType(long type_id) throws SQLException {
        ElectionType et = new ElectionType();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election_type WHERE type_id=?");
        ps.setLong(1, type_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            et.setType_id(rs.getLong("type_id"));
            et.setType(rs.getString("type"));
            et.setDescription(rs.getString("description"));

        }
        return et;
    }

    public ArrayList<Candidate> getElectionResult(long election_id) throws SQLException {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_user_info u INNER JOIN tbl_election_candidate c ON u.email=c.email WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Candidate c = new Candidate();
            c.setEmail(rs.getString("email"));
            c.setFirstname(rs.getString("firstname"));
            c.setLastname(rs.getString("lastname"));
            candidates.add(c);
        }
        return candidates;
    }

    public boolean updateElectionDates(long election_id, Timestamp voting_start, Timestamp voting_end) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election SET voting_start=?,voting_end=? WHERE id=?");
        ps.setTimestamp(1, voting_start);
        ps.setTimestamp(2, voting_end);
        ps.setLong(3, election_id);
        if (ps.executeUpdate() > 0) {
            result = true;
        }

        return result;
    }

    public boolean isValidElectionId(long id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean isValidElectionId(long id, String email) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE id=? AND election_commissioner_email=?");
        ps.setLong(1, id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public String getElectionCommissionerEmail(long id) throws SQLException {
        String email = null;
        PreparedStatement ps = con.prepareStatement("SELECT election_commissioner_email FROM tbl_election WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            email = rs.getString("election_commissioner_email");
        }
        return email;
    }
}
