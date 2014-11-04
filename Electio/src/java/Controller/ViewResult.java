/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplementation;
import Model.Candidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hardik
 */
public class ViewResult extends HttpServlet {

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
            String elec_id = request.getParameter("election_id");
            System.out.println("1");
            System.out.println("Elec_Id: " + elec_id);
            long id = Long.parseLong(elec_id);
            System.out.println("Election_ID: " + elec_id);
            JSONArray array = new JSONArray();
//            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
            DBDAOImplElection objE = DBDAOImplElection.getInstance();
            ArrayList<Candidate> list = null;
            list = objE.getElectionResult(id);
            System.out.println("Msg1");
            JSONObject jSONObject = null;
            System.out.println("Msg2");
            for (Candidate c : list) {
                jSONObject = new JSONObject();
                jSONObject.put(c.getFirstname() + " " + c.getLastname(), c.getVotes() + "");
                array.add(jSONObject);
            }

            out.print(array.toString());
            System.out.println(jSONObject.toString());
        } catch (Exception ex) {
            System.out.println("Err ViewResult: " + ex.getMessage());
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
