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
public class EligibleNominee {

    private long election_id;
    private String email;
    private int status;

    public EligibleNominee() {
    }

    public EligibleNominee(long election_id, String email, int status) {
        this.election_id = election_id;
        this.email = email;
        this.status = status;
    }

    public long getElection_id() {
        return election_id;
    }

    public void setElection_id(long election_id) {
        this.election_id = election_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
