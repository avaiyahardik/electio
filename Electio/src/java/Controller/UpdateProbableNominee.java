/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplementation;
import Model.ProbableNominee;
import Model.Voter;
import Utilities.RandomString;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class UpdateProbableNominee extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String email = (String) request.getSession().getAttribute("email");
            if (email == null || email.equals("")) {
                out.print("You are not logged in, or session already expired");
            } else {
                try {
                    String cmd = request.getParameter("cmd");
                    if (cmd == null || cmd.equals("")) {
                        out.print("Command not found");
                    } else {
                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                        long election_id = Long.parseLong(request.getParameter("election_id"));
                        if (cmd.equals("delete")) {
                            String nominee_email = request.getParameter("probable_nominee_email");
                            if (obj.deleteProbableNominee(election_id, nominee_email)) {
                                out.print("Deleted");
                            } else {
                                out.print("Error");
                            }
                        } else if (cmd.equals("update")) {
                            String old_email = request.getParameter("old_email");
                            String new_email = request.getParameter("new_email");
                            if (obj.updateProbableNominee(election_id, old_email, new_email)) {
                                out.print("Updated");
                            } else {
                                out.print("Error");
                            }
                        } else if (cmd.equals("add")) {
                            String nominee_email = request.getParameter("email");
                            ProbableNominee pn = new ProbableNominee(election_id, nominee_email, false);
                            if (obj.addProbableNominee(pn)) {
                                out.print("Added");
                            } else {
                                out.print("Error");
                            }
                        }
                    }
                } catch (Exception ex) {
                    out.print(ex.getMessage());
                    System.out.println("UpdateProbableNominee Error: " + ex.getMessage());
                }
            }
        }
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
