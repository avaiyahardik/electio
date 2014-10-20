package DAO;

import Model.Election;
import Model.ElectionCommissioner;
import Model.ElectionType;
import Model.Organization;
import Model.Voter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBDAOImplementation {

    private Connection con;
    private static DBDAOImplementation obj = null;

    private DBDAOImplementation() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplementation getInstance() throws SQLException {
        if (obj == null) {
            obj = new DBDAOImplementation();
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
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_election(election_commissioner_email, name, requirements, type_id, nomination_start, nomination_end, withdrawal_start, withdrawal_end, voting_start, voting_end, petition_duration) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, el.getElection_commissioner_email());
        ps.setString(2, el.getName());
        ps.setString(3, el.getRequirements());
        ps.setLong(4, el.getType_id());
        // ps.setTimestamp(5, el.getCreated_at());
        ps.setTimestamp(5, el.getNomination_start());
        ps.setTimestamp(6, el.getNomination_end());
        ps.setTimestamp(7, el.getWithdrawal_start());
        ps.setTimestamp(8, el.getNomination_end());
        ps.setTimestamp(9, el.getVoting_start());
        ps.setTimestamp(10, el.getVoting_end());
        ps.setInt(11, el.getPetition_duration());

        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateElection(Election el) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election SET name=?, requirements=?, type_id=?, nomination_start=?, nomination_end=?, withdrawal_start=?, withdrawal_end=?, voting_start=?, voting_end=?, petition_duration=? WHERE id=?");

        ps.setString(1, el.getName());
        ps.setString(2, el.getRequirements());
        ps.setLong(3, el.getType_id());
        ps.setTimestamp(4, el.getNomination_start());
        ps.setTimestamp(5, el.getNomination_end());
        ps.setTimestamp(6, el.getWithdrawal_start());
        ps.setTimestamp(7, el.getNomination_end());
        ps.setTimestamp(8, el.getVoting_start());
        ps.setTimestamp(9, el.getVoting_end());
        ps.setInt(10, el.getPetition_duration());
        ps.setLong(11, el.getId());

        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean addVoter(Voter voter) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_voter VALUES(?,?,?,?)");
        ps.setString(1, voter.getEmail());
        ps.setLong(2, voter.getElection_id());
        ps.setString(3, voter.getPassword());
        ps.setBoolean(4, voter.getStatus());
        result = ps.execute();
        return result;
    }

    public boolean deleteVoter(String email, long election_id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_voter WHERE email=? and election_id=?");
        ps.setString(1, email);
        ps.setLong(2, election_id);
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

    public ArrayList<Election> getElections(String email) throws SQLException {
        ArrayList<Election> elections = new ArrayList<Election>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE election_commissioner_email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Election el;
        System.out.println("B4");
        while (rs.next()) {
            System.out.println("Yes");
            el = new Election();
            el.setId(rs.getLong("id"));
            el.setElection_commissioner_email(email);
            el.setName(rs.getString("name"));
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

    public boolean loginVoter(String email, long election_id, String password) throws SQLException {
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

    public boolean loginCandidate(String email, long election_id, String password) throws SQLException {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_candidate WHERE email=? and election_id=? and password=?");
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

    public long addNewOrganization(Organization org) throws SQLException {
        long id = -1;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_organization(name,address,about) VALUES(?,?,?)");
        ps.setString(1, org.getName());
        ps.setString(2, org.getAddress());
        ps.setString(3, org.getAbout());
        if (ps.executeUpdate() > 0) {
            ps = con.prepareStatement("SELECT max(id) FROM tbl_organization");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getLong("max(id)");
            }
        }
        return id;
    }

    public long getLastOrganizationId() throws SQLException {
        long id = -1;
        PreparedStatement ps = con.prepareStatement("SELECT max(id) FROM tbl_organization");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getLong("id");
        }
        return id;
    }

    public Organization getOrganization(long id) throws SQLException {
        Organization org = new Organization();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_organization WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            org.setName(rs.getString("name"));
            org.setAddress(rs.getString("address"));
            org.setAbout(rs.getString("about"));
        }
        return org;
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
