/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplementation;
import Model.Nominee;
import Model.Organization;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class NomineeRegistration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("1");
        String view = "nomineeRegistration.jsp";
        String title = "Nominee Registration";
        String msg = null;
        String err = null;
        System.out.println("MSG: " + request.getParameter("election_id"));
        if (request.getParameter("election_id") == null) {
            err = "Invalid url, could not find election id";
        } else {
            System.out.println("2");
            long election_id = Long.parseLong(request.getParameter("election_id"));
            System.out.println("El ID: " + election_id);
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String organization_name = request.getParameter("organization_name");
            String organization_address = request.getParameter("organization_address");
            String about_organization = request.getParameter("about_organization");
            String password = request.getParameter("password");
            String retype_password = request.getParameter("retype_password");
            String image = "Image_path";
            String requirements_file = "requirements_path";
            boolean status = false;
            System.out.println("3");
            if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("") || mobile == null || mobile.equals("") || organization_name == null || organization_name.equals("") || organization_address == null || organization_address.equals("") || about_organization == null || about_organization.equals("") || password == null || password.equals("") || retype_password == null || retype_password.equals("")) {
                err = "Please fill all required fields";
            } else {
                if (retype_password.equals(password)) {
                    // upload image and pdf file here...
                    try {
                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                        Organization org = new Organization(organization_name, organization_address, about_organization);
                        long organization_id = obj.addNewOrganization(org);
                        Nominee nominee = new Nominee(firstname, lastname, email, mobile, organization_id, image, password, organization_id, requirements_file, status);
                        System.out.println("4");
                        if (obj.registerNominee(nominee)) {
                            msg = "Nominee registered successfully";
                        } else {
                            err = "Fail to register nominee, please retry";
                        }
                    } catch (Exception ex) {
                        err = ex.getMessage();
                        System.out.println("NomineeRegistration Err: " + ex.getMessage());
                    }
                } else {
                    err = "Retype password doesn't match";
                }
            }
        }

        request.setAttribute("msg", msg);
        request.setAttribute("err", err);
        request.setAttribute("title", title);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
