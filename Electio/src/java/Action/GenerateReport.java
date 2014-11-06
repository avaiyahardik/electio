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
import Model.Election;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class GenerateReport implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        Date date = new Date();
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        System.out.println("");
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            long id = Long.parseLong(elec_id);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                File file = new File(date.getTime() + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                PrintWriter out = new PrintWriter(fos);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                Election election = objE.getElection(id);
                out.println("Election Name: " + election.getName());
                out.println("Election Description: " + election.getDescription());
                out.println("Eligibility Criteria: " + election.getRequirements());
                out.println("Electio Type: " + objE.getElectionType(election.getType_id()));
                out.println("Election created at: " + sdf.format(new Date(election.getCreated_at().getTime())));
                out.println("Nomination Started at: " + sdf.format(new Date(election.getNomination_start().getTime())));
                out.println("Nomination ended at: " + sdf.format(new Date(election.getNomination_end().getTime())));
                out.println("Withdrawal started at: " + sdf.format(new Date(election.getWithdrawal_start().getTime())));
                out.println("Withdrawal ended at: " + sdf.format(new Date(election.getWithdrawal_end().getTime())));
                out.println("Voting started at: " + sdf.format(new Date(election.getVoting_start().getTime())));
                out.println("Voting ended at: " + sdf.format(new Date(election.getVoting_end().getTime())));
                out.println("Petition duration(in days): " + election.getPetition_duration());

                out.println();

                out.flush();
                out.close();
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Generate Report Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
