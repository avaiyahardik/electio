/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
import Model.Voter;
import Utilities.RandomString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.FieldNameHelper;

/**
 *
 * @author Hardik
 */
public class UploadVoterFile extends HttpServlet {

    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("1");
        String view = "electionDetail.jsp";
        String title = "Election Detail";
        String msg = null;
        String err = null;
//      System.out.println("MSG: " + request.getParameter("election_id"));

        long election_id = 0;
        String voter_file = "";
        try {
            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            Date date;
            String ext;
            String fileName;
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                String fieldName = fileItem.getFieldName();
//                System.out.println("FieldName=" + fieldName + fileItem.getString());
                if (fieldName.equals("election_id")) {
                    election_id = Long.parseLong(fileItem.getString());
                    Election el = obj.getElection(election_id);
                    request.setAttribute("election", el);
                    ArrayList<Nominee> nominees = obj.getNominees(election_id);
                    request.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = obj.getCandidates(election_id);
                    request.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = obj.getVoters(election_id);
                    request.setAttribute("voters", voters);
                    ArrayList<ProbableNominee> pns = obj.getAllProbableNominees(election_id);
                    request.setAttribute("probable_nominee", pns);
                } else if (fileItem.getFieldName().equals("voter_file")) {
                    System.out.println("FileName=" + fileItem.getName());
                    System.out.println("ContentType=" + fileItem.getContentType());
                    System.out.println("Size in bytes=" + fileItem.getSize());
                    // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                    date = new Date();
                    fileName = fileItem.getName();
//                    System.out.println("File Name: " + fileName);
                    ext = fileName.substring(fileName.lastIndexOf('.'));
//                    System.out.println("Ext: " + ext);
                    File file = new File(request.getServletContext().getRealPath("/temp") + File.separator + date.getTime() + ext);
                    fileItem.write(file);
                    voter_file = request.getServletContext().getRealPath("/temp") + File.separator + date.getTime() + ext;
                    System.out.println("Absolute Path at server=" + voter_file);
                    System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                    Voter v;
                    File f = new File(voter_file);
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String s = br.readLine();
                    while (s != null) {
                        v = new Voter(s, election_id, RandomString.generateRandomPassword(), false);
                        obj.addVoter(v);
                        s = br.readLine();
                    }
                }
            }
        } catch (FileUploadException e) {
            System.out.println("File Not Found Exception in uploading voter file.");
        } catch (Exception e) {
            err = e.getMessage();
            System.out.println("Import Voter Error: " + e.toString());
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
