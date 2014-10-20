/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hardik
 */
public class ElectionCommissioner {

    private String email;
    private String firstname;
    private String lastname;
    private String mobile;
    private long organization_id;
    private String password;

    public ElectionCommissioner() {
    }

    public ElectionCommissioner(String email, String firstname, String lastname, String mobile, long organization_id, String password) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.organization_id = organization_id;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(long organization_id) {
        this.organization_id = organization_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
