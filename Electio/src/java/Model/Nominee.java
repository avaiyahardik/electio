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

    private String election_id;
    private String requirements_file;
    private boolean status;

    public String getElection_id() {
        return election_id;
    }

    public void setElection_id(String election_id) {
        this.election_id = election_id;
    }

    public String getRequirements_file() {
        return requirements_file;
    }

    public void setRequirements_file(String requirements_file) {
        this.requirements_file = requirements_file;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
