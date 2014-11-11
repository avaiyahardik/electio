/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplVoter;
import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Model.ProbableNominee;
import Model.Voter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class UpdateElection implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String view = "index.jsp";
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired, please login again";
        } else {
            try {
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                if (elec_id == null) {
                    err = "Unable to locate election id";
                    view = "listElections.jsp";
                    title = "Elections";
                    ArrayList<Election> elections = null;
                    elections = objE.getElections(email);
                    req.setAttribute("elections", elections);
                } else {
                    long id = Long.parseLong(elec_id);
                    view = "electionDetail.jsp";
                    title = "Election Detail";
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    Timestamp nomination_start = new Timestamp(dateFormat.parse(req.getParameter("nomination_start")).getTime());
                    Timestamp nomination_end = new Timestamp(dateFormat.parse(req.getParameter("nomination_end")).getTime());
                    Timestamp withdrawal_start = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_start")).getTime());
                    Timestamp withdrawal_end = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_end")).getTime());
                    Timestamp voting_start = new Timestamp(dateFormat.parse(req.getParameter("voting_start")).getTime());
                    Timestamp voting_end = new Timestamp(dateFormat.parse(req.getParameter("voting_end")).getTime());
                    Date date = new Date();
                    if (nomination_start.before(date)) {
                        err = "Nomination start time should be after today";
                    } else if (nomination_end.before(nomination_start)) {
                        err = "Nomination end time should be after nomination start time";
                    } else if (withdrawal_start.before(nomination_end)) {
                        err = "Withdrawal start time should be after nomination end time";
                    } else if (withdrawal_end.before(withdrawal_start)) {
                        err = "Withdrawal end time should be after withdrawal start time";
                    } else if (voting_start.before(withdrawal_end)) {
                        err = "Voting start time should be after withdrawal end time";
                    } else if (voting_end.before(voting_start)) {
                        err = "Voting end time should be after voting start time";
                    } else {
                        System.out.println("Election_type:" + req.getParameter("type"));
                        Election el = new Election();
                        el.setId(id);
                        el.setName(req.getParameter("name"));
                        el.setDescription(req.getParameter("description"));
                        el.setRequirements(req.getParameter("requirements"));
                        el.setType_id(Integer.parseInt(req.getParameter("type")));
                        el.setNomination_start(nomination_start);
                        el.setNomination_end(nomination_end);
                        el.setWithdrawal_start(withdrawal_start);
                        el.setWithdrawal_end(withdrawal_end);
                        el.setVoting_start(voting_start);
                        el.setVoting_end(voting_end);
                        el.setPetition_duration(Integer.parseInt(req.getParameter("petition_duration")));
                        if (objE.updateElection(el)) {
                            msg = "Election updated successfully";
                        } else {
                            err = "Fail to update election, please retry";
                        }
                    }
                    Election el = objE.getElection(id, email);
                    req.setAttribute("election", el);
                    ArrayList<Nominee> nominees = objN.getNominees(id);
                    req.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = objC.getCandidates(id);
                    req.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = objV.getVoters(id);
                    req.setAttribute("voters", voters);
                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(id);
                    req.setAttribute("probable_nominee", pns);
                }
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Update Election Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
