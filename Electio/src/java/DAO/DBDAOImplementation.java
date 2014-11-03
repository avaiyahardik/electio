package DAO;

import Model.Candidate;
import Model.Election;
import Model.ElectionCommissioner;
import Model.ElectionType;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
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
            System.out.println("New DBDAOImpl created");
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

    public boolean updateElection(Election el) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election SET name=?, description=?, requirements=?, type_id=?, nomination_start=?, nomination_end=?, withdrawal_start=?, withdrawal_end=?, voting_start=?, voting_end=?, petition_duration=? WHERE id=?");

        ps.setString(1, el.getName());
        ps.setString(2, el.getDescription());
        ps.setString(3, el.getRequirements());
        ps.setLong(4, el.getType_id());
        ps.setTimestamp(5, el.getNomination_start());
        ps.setTimestamp(6, el.getNomination_end());
        ps.setTimestamp(7, el.getWithdrawal_start());
        ps.setTimestamp(8, el.getNomination_end());
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
        if (ps.executeUpdate() > 0) {
            result = true;
        }
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

    public boolean deleteNominee(String email, long id) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_election_nominee WHERE election_id=?");
        ps.setLong(1, id);
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

    public ArrayList<Election> getElections(String email) throws SQLException {
        ArrayList<Election> elections = new ArrayList<Election>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_election WHERE election_commissioner_email=?");
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

    public ArrayList<Organization> getAllOrganizations() throws SQLException {
        ArrayList<Organization> orgs = new ArrayList<>();
        Organization org = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_organization");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            org = new Organization();
            org.setId(rs.getLong("id"));
            org.setName(rs.getString("name"));
            org.setAddress(rs.getString("address"));
            org.setAbout(rs.getString("about"));
            orgs.add(org);
        }
        return orgs;
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

    public ArrayList<Nominee> getNominees(long election_id) throws SQLException {
        ArrayList<Nominee> nominees = new ArrayList<Nominee>();
        PreparedStatement ps = con.prepareStatement("select * from tbl_user_info as u INNER JOIN tbl_election_nominee as n ON u.email=n.email WHERE election_id=?");
        ps.setLong(1, election_id);
        ResultSet rs = ps.executeQuery();
        Nominee nominee;
        while (rs.next()) {
            nominee = new Nominee();
            nominee.setEmail(rs.getString("email"));
            nominee.setFirstname(rs.getString("firstname"));
            nominee.setLastname(rs.getString("lastname"));
            nominee.setGender(rs.getInt("gender"));
            nominee.setMobile(rs.getString("mobile"));
            nominee.setOrganization_id(rs.getLong("organization_id"));
            nominee.setImage(rs.getString("image"));
            System.out.println("Nominee Image: " + nominee.getImage());
            nominee.setPassword(rs.getString("password"));
            nominee.setElection_id(rs.getLong("election_id"));
            nominee.setRequirements_file(rs.getString("requirements_file"));
            nominee.setStatus(rs.getInt("status"));
            nominees.add(nominee);
        }
        return nominees;
    }

    public Nominee getNominee(long election_id, String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from tbl_user_info as u INNER JOIN tbl_election_nominee as n ON u.email=n.email WHERE election_id=? and u.email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        Nominee nominee = null;
        if (rs.next()) {
            nominee = new Nominee();
            nominee.setEmail(rs.getString("email"));
            nominee.setFirstname(rs.getString("firstname"));
            nominee.setLastname(rs.getString("lastname"));
            nominee.setGender(rs.getInt("gender"));
            nominee.setMobile(rs.getString("mobile"));
            nominee.setOrganization_id(rs.getLong("organization_id"));
            nominee.setImage(rs.getString("image"));
            nominee.setPassword(rs.getString("password"));
            nominee.setElection_id(rs.getLong("election_id"));
            nominee.setRequirements_file(rs.getString("requirements_file"));
            nominee.setStatus(rs.getInt("status"));
        }
        return nominee;
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

    public boolean changeNomineePassword(String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_user_info SET password=? WHERE email=?");
        ps.setString(1, password);
        ps.setString(2, email);
        if (ps.executeUpdate() > 0) {
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

    public boolean registerNominee(Nominee nominee) throws SQLException {
        boolean result = false;
        con.setAutoCommit(false);
        PreparedStatement ps1 = con.prepareStatement("INSERT INTO tbl_user_info(email, firstname,lastname,gender,mobile,organization_id, image, password) VALUES(?,?,?,?,?,?,?,?)");
        ps1.setString(1, nominee.getEmail());
        ps1.setString(2, nominee.getFirstname());
        ps1.setString(3, nominee.getLastname());
        ps1.setInt(4, nominee.getGender());
        ps1.setString(5, nominee.getMobile());
        ps1.setLong(6, nominee.getOrganization_id());
        ps1.setString(7, nominee.getImage());
        ps1.setString(8, nominee.getPassword());

        PreparedStatement ps2 = con.prepareStatement("INSERT INTO tbl_election_nominee(email, election_id,requirements_file, status) VALUES(?,?,?,?)");
        ps2.setString(1, nominee.getEmail());
        ps2.setLong(2, nominee.getElection_id());
        ps2.setString(3, nominee.getRequirements_file());
        ps2.setInt(4, nominee.getStatus());

        if (ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0) {
            result = true;
            con.commit();
        } else {
            con.rollback();
        }
        return result;
    }

    public boolean nomineeLogin(long election_id, String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_user_info u INNER JOIN tbl_election_nominee n ON u.email=n.email WHERE election_id=? AND u.email=? AND password=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = true;
        }

        return result;
    }

    public boolean approveNominee(long election_id, String email, String requirements_file) throws SQLException {
        boolean result = false;
        // status = 1 means approved
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election_nominee SET status =? WHERE election_id=? and email=?");
        ps.setInt(1, 1);
        ps.setLong(2, election_id);
        ps.setString(3, email);

        if (ps.executeUpdate() > 0) {
            result = true;
        }

        PreparedStatement ps2 = con.prepareStatement("INSERT INTO tbl_election_candidate VALUES(?,?,?,?,?)");
        ps2.setString(1, email);
        ps2.setLong(2, election_id);
        ps2.setString(3, requirements_file);
        ps2.setInt(4, 0);
        ps2.setString(5, "no manifesto");
        if (ps2.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean rejectNominee(long election_id, String emai, String reason) throws SQLException {
        int flag = 0;
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_election_nominee SET status =? WHERE election_id=? and email=?");
        ps.setInt(1, 2);    // status 2 means disapproved
        ps.setLong(2, election_id);
        ps.setString(3, emai);

        if (ps.executeUpdate() > 0) {
            flag++;
        }
        ps = con.prepareStatement("INSERT INTO tbl_rejected_nominee VALUES(?,?,?");
        ps.setLong(1, election_id);
        ps.setString(2, emai);
        ps.setString(3, reason);

        if (ps.executeUpdate() > 0) {
            flag++;
        }
        if (flag == 2) {
            result = true;
        }
        return result;
    }

    public String getReason(long election_id, String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT reason FROM tbl_rejected_nominee WHERE election_id=? and email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        String reason = null;
        if (rs.next()) {
            reason = rs.getString("reason");
        }
        return reason;
    }

    public int getNomineeStatus(long election_id, String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select status from tbl_election_nominee WHERE election_id=? and email=?");
        ps.setLong(1, election_id);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        int status = 0;
        if (rs.next()) {
            status = rs.getInt("status");
        }
        return status;
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

    public boolean addProbableNominee(ProbableNominee pn) throws SQLException {
        boolean result = false;
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
            pn.setStatus(rs.getBoolean("status"));
            pns.add(pn);
        }
        return pns;
    }

}
