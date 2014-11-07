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
            view = "dashboard.jsp"; // view changed if login successfull
            title = "Dashboard";
            long id = Long.parseLong(elec_id);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                File file = new File(req.getServletContext().getRealPath("/temp") + File.separator + date.getTime() + ".txt");
                String filePath = File.separator + date.getTime() + ".txt";
                FileOutputStream fos = new FileOutputStream(file);
                PrintWriter out = new PrintWriter(fos);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                Election election = objE.getElection(id);
                out.print("<table>");
                out.printf("Election Name: %30s", election.getName());
                out.printf("Election Description: %30s", election.getDescription());
                out.printf("Eligibility Criteria: %30s", election.getRequirements());
                out.printf("Electio Type: " + objE.getElectionType(election.getType_id()));
                out.printf("Election created at: %30s", sdf.format(new Date(election.getCreated_at().getTime())));
                out.printf("Nomination Started at: " + sdf.format(new Date(election.getNomination_start().getTime())));
                out.printf("Nomination ended at: " + sdf.format(new Date(election.getNomination_end().getTime())));
                out.printf("Withdrawal started at: " + sdf.format(new Date(election.getWithdrawal_start().getTime())));
                out.printf("Withdrawal ended at: " + sdf.format(new Date(election.getWithdrawal_end().getTime())));
                out.printf("Voting started at: " + sdf.format(new Date(election.getVoting_start().getTime())));
                out.printf("Voting ended at: " + sdf.format(new Date(election.getVoting_end().getTime())));
                out.printf("Petition duration(in days): " + election.getPetition_duration());

                out.println();

                out.flush();
                out.close();
                req.setAttribute("file_path", filePath);
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
