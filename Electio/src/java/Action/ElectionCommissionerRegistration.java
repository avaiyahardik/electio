/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplementation;
import Model.ElectionCommissioner;
import Model.Organization;
import Utilities.RandomString;
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
        String org_id = req.getParameter("organization_id");
        String organization_name = req.getParameter("organization_name");
        String organization_address = req.getParameter("organization_address");
        String about_organization = req.getParameter("about_organization");
        String password = req.getParameter("password");
        String retype_password = req.getParameter("retype_password");
        //password = RandomString.encryptPassword(password);  // encrypt password for security reason
        System.out.println("Encrypted Pwd: " + password);
        String view = "registration.jsp";
        String title = "Registration";
        String msg = null;
        String err = null;
        System.out.println(email + ", " + firstname + ", " + lastname + ", " + mobile + ", " + organization_name + ", " + password);
        if (email == null || email.equals("") || firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || mobile == null || mobile.equals("") || org_id == null || org_id.equals("") || password == null || password.equals("") || retype_password == null || retype_password.equals("")) {
            err = "Please fill-up required fields";
        } else {
            try {
//                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                if (objEC.isEmailExists(email)) {
                    err = "Email already registered, register with different one or try forgot password";
                } else {
                    long organization_id = Long.parseLong(org_id);
                    if ((organization_id == 0) && (organization_name == null || organization_address == null || about_organization == null || organization_name.equals("") || organization_address.equals("") || about_organization.equals(""))) {
                        err = "Please fill-up required fields";
                    } else {
                        Organization org = new Organization(organization_name, organization_address, about_organization);
                        organization_id = objO.addNewOrganization(org);
                        ElectionCommissioner ec = new ElectionCommissioner(email, firstname, lastname, mobile, organization_id, password);
                        if (objEC.registerElectionCommissioner(ec)) {
                            msg = "You're registered successfully";
                            view = "index.jsp";
                            title = "Login";
                        } else {
                            err = "Fail to register, please try again later";
                        }
                    }
                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Election Commissioner Register SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
