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
public class Candidate extends UserInfo {

    private long election_id;
    private String requirements_file;
    private long votes;
    private String manifesto;
    private boolean petition_filed;

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

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }

    public boolean isPetition_filed() {
        return petition_filed;
    }

    public void setPetition_filed(boolean petition_filed) {
        this.petition_filed = petition_filed;
    }
}
