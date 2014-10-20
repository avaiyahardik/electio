/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Hardik
 */
public class Election {

    long id;
    String election_commissioner_email;
    String name;
    String requirements;
    long type_id;
    Timestamp created_at;
    Timestamp nomination_start;
    Timestamp nomination_end;
    Timestamp witdrawal_start;
    Timestamp witdrawal_end;
    Timestamp voting_start;
    Timestamp voting_end;
    int petition_duration;

    public Election() {
    }

    public Election(String election_commissioner_email, String name, String requirements, long type_id, Timestamp nomination_start, Timestamp nomination_end, Timestamp witdrawal_start, Timestamp witdrawal_end, Timestamp voting_start, Timestamp voting_end, int petition_duration) {
        this.election_commissioner_email = election_commissioner_email;
        this.name = name;
        this.requirements = requirements;
        this.type_id = type_id;
        this.nomination_start = nomination_start;
        this.nomination_end = nomination_end;
        this.witdrawal_start = witdrawal_start;
        this.witdrawal_end = witdrawal_end;
        this.voting_start = voting_start;
        this.voting_end = voting_end;
        this.petition_duration = petition_duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getElection_commissioner_email() {
        return election_commissioner_email;
    }

    public void setElection_commissioner_email(String election_commissioner_email) {
        this.election_commissioner_email = election_commissioner_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getNomination_start() {
        return nomination_start;
    }

    public void setNomination_start(Timestamp nomination_start) {
        this.nomination_start = nomination_start;
    }

    public Timestamp getNomination_end() {
        return nomination_end;
    }

    public void setNomination_end(Timestamp nomination_end) {
        this.nomination_end = nomination_end;
    }

    public Timestamp getWitdrawal_start() {
        return witdrawal_start;
    }

    public void setWitdrawal_start(Timestamp witdrawal_start) {
        this.witdrawal_start = witdrawal_start;
    }

    public Timestamp getWitdrawal_end() {
        return witdrawal_end;
    }

    public void setWitdrawal_end(Timestamp witdrawal_end) {
        this.witdrawal_end = witdrawal_end;
    }

    public Timestamp getVoting_start() {
        return voting_start;
    }

    public void setVoting_start(Timestamp voting_start) {
        this.voting_start = voting_start;
    }

    public Timestamp getVoting_end() {
        return voting_end;
    }

    public void setVoting_end(Timestamp voting_end) {
        this.voting_end = voting_end;
    }

    public int getPetition_duration() {
        return petition_duration;
    }

    public void setPetition_duration(int petition_duration) {
        this.petition_duration = petition_duration;
    }

}
