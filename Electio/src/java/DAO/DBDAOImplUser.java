/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hardik
 */
public class DBDAOImplUser {

    private Connection con;
    private static DBDAOImplUser obj = null;

    private DBDAOImplUser() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplUser getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImplUser created");
            obj = new DBDAOImplUser();
        }
        return obj;
    }

    public User getUserInfo(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_user_info WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        User ui = null;
        if (rs.next()) {
            ui = new User();
            ui.setEmail(rs.getString("email"));
            ui.setFirstname(rs.getString("firstname"));
            ui.setGender(rs.getInt("gender"));
            ui.setImage(rs.getString("image"));
            ui.setLastname(rs.getString("lastname"));
            ui.setMobile(rs.getString("mobile"));
            ui.setOrganization_id(rs.getLong("organization_id"));
            ui.setPassword(rs.getString("password"));
        }
        return ui;
    }

    public boolean updateUserInfo(User ui) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_user_info SET firstname=?,lastname=?,gender=?,mobile=?,organization_id=? WHERE email=?");
        ps.setString(1, ui.getFirstname());
        ps.setString(2, ui.getLastname());
        ps.setInt(3, ui.getGender());
        ps.setString(4, ui.getMobile());
        ps.setLong(5, ui.getOrganization_id());
        ps.setString(6, ui.getEmail());
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    public boolean updateProfilePicture(String email, String image) throws SQLException {
        boolean result = false;
        PreparedStatement ps = con.prepareStatement("UPDATE tbl_user_info SET image=? WHERE email=?");
        ps.setString(1, image);
        ps.setString(2, email);
        if (ps.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }
}
