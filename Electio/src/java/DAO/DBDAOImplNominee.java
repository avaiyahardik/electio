/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.Nominee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplNominee {
        private Connection con;
    private static DBDAOImplNominee obj = null;

    private DBDAOImplNominee() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplNominee getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplNominee();
        }
        return obj;
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
        ps2.setString(5, "manifestos/electio.pdf");
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

}
