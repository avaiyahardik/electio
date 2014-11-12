/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplOrganization;
import Model.ElectionCommissioner;
import Model.Organization;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class ElectionCommissionerUpdateProfile implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "profile.jsp";
            title = "Profile";
            try {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String mobile = req.getParameter("mobile");
                String org_id = req.getParameter("organization_id");
                String organization_name = req.getParameter("organization_name");
                String organization_address = req.getParameter("organization_address");
                String about_organization = req.getParameter("about_organization");
                System.out.println(firstname + ", " + lastname + ", " + mobile + ", " + organization_name + ", " + organization_address + ", " + about_organization);
                if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || mobile.equals(null) || mobile.equals("") || org_id == null || org_id.equals("-1") || org_id.equals("")) {
                    err = "Please fill-up required fields";
                } else {
                    long organization_id = Long.parseLong(org_id);
                    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                    if (organization_id == 0) {
                        if (organization_name == null || organization_address == null || about_organization == null || organization_name.equals("") || organization_address.equals("") || about_organization.equals("")) {
                            err = "Please fill-up required fields";
                        } else {
                            Organization org = new Organization(organization_name, organization_address, about_organization);
                            organization_id = objO.addNewOrganization(org);
                        }
                    }
                    if (organization_id != 0) {
                        DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                        ElectionCommissioner ec = new ElectionCommissioner(email, firstname, lastname, mobile, organization_id, "doesn't matter");
                        if (objEC.updateElectionCommissioner(ec)) {
                            msg = "Your profile updated successfully";
                        } else {
                            err = "Fail to update profile, please retry";
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                err = "Invalid organization id";
                System.out.println("NFE: " + ex);
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("ECUpdateProfile ERR: " + ex.getMessage());
            }

            try {
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                ElectionCommissioner ec = objEC.getElectionCommissioner(email);
                Organization org = objO.getOrganization(ec.getOrganization_id());
                req.setAttribute("election_commissioner", ec);
                req.setAttribute("organization", org);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("ECUpdateProfile Err: " + ex.getMessage());
            }

        }
        System.out.println("MSG: " + msg);
        System.out.println("ERR: " + err);
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
