/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Election;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            err = "Session expired, please login again";
        } else {
            view = "newElection.jsp";
            title = "Create New Election";
            try {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String requirements = req.getParameter("requirements");
                long type_id = Integer.parseInt(req.getParameter("type"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date = new Date();
//                date = dateFormat.parse(dateFormat.format(date));
//                Timestamp created_at = new Timestamp(date.getTime());
                Timestamp nomination_start = new Timestamp(dateFormat.parse(req.getParameter("nomination_start")).getTime());
                Timestamp nomination_end = new Timestamp(dateFormat.parse(req.getParameter("nomination_end")).getTime());
                Timestamp withdrawal_start = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_start")).getTime());
                Timestamp withdrawal_end = new Timestamp(dateFormat.parse(req.getParameter("withdrawal_end")).getTime());
                Timestamp voting_start = new Timestamp(dateFormat.parse(req.getParameter("voting_start")).getTime());
                Timestamp voting_end = new Timestamp(dateFormat.parse(req.getParameter("voting_end")).getTime());
                int petition_duration = Integer.parseInt(req.getParameter("petition_duration"));
                System.out.println(email + ", " + name + ", " + description + ", " + requirements + ", " + type_id + ", " + nomination_start + ", " + nomination_end + ", " + voting_start + ", " + voting_end + ", " + withdrawal_start + ", " + withdrawal_end + ", " + petition_duration);

                if (name == null || name.equals("")) { // more validation will be performed later
                    err = "Please fill-up required fields";
                } else {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    Election el = new Election(email, name, requirements, type_id, nomination_start, nomination_end, withdrawal_start, withdrawal_end, voting_start, voting_end, petition_duration);
                    if (obj.createElection(el)) {
                        msg = "New election created successfully";
                    } else {
                        err = "Fail to create new election, please retry";
                    }
                }
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Create New Election Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
