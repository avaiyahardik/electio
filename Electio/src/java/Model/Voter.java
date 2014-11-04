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
public class Voter {

    private String email;
    private long election_id;
    private String password;
    private boolean status;
    private boolean link_status;

    public Voter() {
    }

    public Voter(String email, long election_id, String password, boolean status, boolean link_status) {
        this.email = email;
        this.election_id = election_id;
        this.password = password;
        this.status = status;
        this.link_status = link_status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getElection_id() {
        return election_id;
    }

    public void setElection_id(long election_id) {
        this.election_id = election_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getLinkStatus() {
        return status;
    }

    public void setLinkStatus(boolean link_status) {
        this.link_status = link_status;
    }
    
    
}
