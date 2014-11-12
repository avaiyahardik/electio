/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import Model.Election;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class CreateNewElection implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String title = "Login";
        String msg = null;
        String err = null;
        if (email == null || email.equals("")) {
            err = "Session expired or you are not logged in, please login";
        } else {
            view = "newElection.jsp";
            title = "New Election";
            try {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String requirements = req.getParameter("requirements");
                long type_id = Integer.parseInt(req.getParameter("type"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date = new Date();
                Timestamp nomination_start = new Timestamp(dateFormat.parse(req.getParameter("nomination_start")).getTime());
                Timestamp nomination_end = new Timestamp(dateFormat.parse(req.getParameter("nomination_end")).getTime());
                Timestamp withdrawal_start = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_start")).getTime());
                Timestamp withdrawal_end = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_end")).getTime());
                Timestamp voting_start = new Timestamp(dateFormat.parse(req.getParameter("voting_start")).getTime());
                Timestamp voting_end = new Timestamp(dateFormat.parse(req.getParameter("voting_end")).getTime());
                int petition_duration = Integer.parseInt(req.getParameter("petition_duration"));
//                System.out.println(email + ", " + name + ", " + description + ", " + requirements + ", " + type_id + ", " + nomination_start + ", " + nomination_end + ", " + voting_start + ", " + voting_end + ", " + withdrawal_start + ", " + withdrawal_end + ", " + petition_duration);
                try {
                    if (name == null || name.equals("") || description == null || description.equals("") || type_id == 0) {
                        err = "Please fill-up required fields";
                    } else {
                        if (nomination_start.before(date)) {
                            err = "Nominatoin start time should be of future time";
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
                            Election el = new Election(email, name, description, requirements, type_id, nomination_start, nomination_end, withdrawal_start, withdrawal_end, voting_start, voting_end, petition_duration);
                            DBDAOImplElection objE = DBDAOImplElection.getInstance();
                            if (objE.createElection(el)) {
                                msg = "New election created successfully";
                                view = "listElections.jsp";
                                title = "Elections";
                                ArrayList<Election> elections = null;
                                elections = objE.getElections(email);
                                req.setAttribute("elections", elections);
                            } else {
                                err = "Fail to create new election, please retry";
                            }
                        }
                    }
                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("Create New Election Err: " + ex.getMessage());
                }
            } catch (Exception ex) {
                err = "Invalid inputted data";
                System.out.println("CreateNewElection Err Parse input: " + ex.getMessage());
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}