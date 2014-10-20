/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.ElectionCommissioner;
import Model.Organization;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionCommissionerRegistration implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String mobile = req.getParameter("mobile");
        String organization_name = req.getParameter("organization_name");
        String organization_address = req.getParameter("organization_address");
        String about_organization = req.getParameter("about_organization");
        String password = req.getParameter("password");
        String view = "registration.jsp";
        String title = "Registration";
        String msg = "";
        String err = "";
        System.out.println(email + ", " + firstname + ", " + lastname + ", " + mobile + ", " + organization_name + ", " + password);
        if (email == null || email.equals("") || firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || mobile == null || mobile.equals("") || organization_name == null || organization_name.equals("") || password == null || password.equals("")) {
            err = "Please fill-up required fields";
        } else {
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                Organization org = new Organization(organization_name, organization_address, about_organization);
                long id = obj.addNewOrganization(org);
                System.out.println("ID; " + id);
                ElectionCommissioner ec = new ElectionCommissioner(email, firstname, lastname, mobile, id, password);
                if (obj.registerElectionCommissioner(ec)) {
                    msg = "You're registered successfully";
                    view = "index.jsp";
                    title = "Login";
                } else {
                    err = "Fail to register, please try again later";
                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Register SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
