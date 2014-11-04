/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplVoter;
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
public class UploadProbableNomineeFile extends HttpServlet {

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
        String nominee_file = "";
        try {
//            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            DBDAOImplElection objE = DBDAOImplElection.getInstance();
            DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
            DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
            DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
            DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
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
                    Election el = objE.getElection(election_id);
                    request.setAttribute("election", el);
                    ArrayList<Nominee> nominees = objN.getNominees(election_id);
                    request.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = objC.getCandidates(election_id);
                    request.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = objV.getVoters(election_id);
                    request.setAttribute("voters", voters);
                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(election_id);
                    request.setAttribute("probable_nominee", pns);
                } else if (fileItem.getFieldName().equals("nominee_file")) {
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
                    nominee_file = request.getServletContext().getRealPath("/temp") + File.separator + date.getTime() + ext;
                    System.out.println("Absolute Path at server=" + nominee_file);
                    System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                    ProbableNominee pn;
                    File f = new File(nominee_file);
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String s = br.readLine();
                    String reg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    while (s != null) {
                        if (s.matches(reg)) {
                            pn = new ProbableNominee(election_id, s, 0);
                            objP.addProbableNominee(pn);
                        } else {
                            System.out.println("invalid email address " + s);
                        }
                        s = br.readLine();
                    }
                }
            }
        } catch (FileUploadException e) {
            System.out.println("File Not Found Exception in uploading probable nominee file.");
        } catch (Exception e) {
            err = e.getMessage();
            System.out.println("Import Probable Nominee Error: " + e.toString());
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
