/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author darshit
 */
public class DBDAOImplOrganization {
            private Connection con;
    private static DBDAOImplOrganization obj = null;

    private DBDAOImplOrganization() throws SQLException {
        con = DBConnection.getConnection();
    }

    public static DBDAOImplOrganization getInstance() throws SQLException {
        if (obj == null) {
            System.out.println("New DBDAOImpl created");
            obj = new DBDAOImplOrganization();
        }
        return obj;
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
}
