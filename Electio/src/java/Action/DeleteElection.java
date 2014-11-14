/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplEligibleNominee;
import DAO.DBDAOImplVoter;
import Model.Election;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class DeleteElection implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String msg = null;
        String err = null;
        String view = "index.jsp";
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired, please login again";
        } else {
            if (elec_id == null || elec_id.equals("")) {
                err = "Election id missing";
            } else {
                try {
                    view = "listElections.jsp";
                    title = "Elections";
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                    DBDAOImplEligibleNominee objP = DBDAOImplEligibleNominee.getInstance();
                    ArrayList<Election> elections = null;
                    elections = objE.getElections(email);
                    req.setAttribute("elections", elections);
                    long id = Long.parseLong(elec_id);
                    if (elec_id == null || !objE.isValidElectionId(id, email)) {
                        err = "Invalid election id, please retry";
                    } else {
                        if (objV.deleteVoterForElection(id) && objN.deleteNomineeForElection(id) && objC.deleteCandidateForElection(id) && objP.deleteProbableNomineeForElection(id) && objN.deleteRejectedNomineeForElection(id) && objE.deleteElection(email, id)) {
                            msg = "Election deleted successfully";
                        } else {
                            err = "Fail to delete election, please retry";
                        }
                    }
                   
                    elections = objE.getElections(email);
                    req.setAttribute("elections", elections);
                } catch (NumberFormatException ex) {
                    err = "Invalid election id";
                    System.out.println("NFE: " + ex);
                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("DeleteElection Error: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
