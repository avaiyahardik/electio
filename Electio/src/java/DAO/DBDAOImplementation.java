package DAO;

import Model.Election;
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

    public boolean createElection(Election el) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_election(election_commissioner_email, election_name, type_id, nomination_start, nomination_end, withdrawal_start, withdrawal_end, voting_start, voting_end, petition_duration) VALUES(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, el.getElection_commissioner_email());
        ps.setString(2, el.getElection_name());
        ps.setLong(3, el.getType_id());
        ps.setTimestamp(4, el.getNomination_start());
        ps.setTimestamp(5, el.getNomination_end());
        ps.setTimestamp(6, el.getWitdrawal_start());
        ps.setTimestamp(7, el.getNomination_end());
        ps.setTimestamp(8, el.getVoting_start());
        ps.setTimestamp(9, el.getVoting_end());
        ps.setInt(10, el.getPetition_duration());

        result = ps.execute();
        return result;
    }

}
