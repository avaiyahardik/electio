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
public class Nominee extends UserInfo {

    private long election_id;
    private String requirements_file;
    private int status;

    public Nominee() {
    }

    public Nominee(String firstname, String lastname, String email, int gender, String mobile, long organization_id, String image, String password, long election_id, String requirements_file, int status) {
        super.setFirstname(firstname);
        super.setLastname(lastname);
        super.setEmail(email);
        super.setGender(gender);
        super.setMobile(mobile);
        super.setOrganization_id(organization_id);
        super.setImage(image);
        super.setPassword(password);
        this.election_id = election_id;
        this.requirements_file = requirements_file;
        this.status = status;
    }

    public long getElection_id() {
        return election_id;
    }

    public void setElection_id(long election_id) {
        this.election_id = election_id;
    }

    public String getRequirements_file() {
        return requirements_file;
    }

    public void setRequirements_file(String requirements_file) {
        this.requirements_file = requirements_file;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
