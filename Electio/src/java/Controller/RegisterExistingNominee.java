/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplUserInfo;
import DAO.DBDAOImplementation;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
import Model.UserInfo;
import Utilities.EmailSender;
import Utilities.RandomString;
import java.io.File;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
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
public class RegisterExistingNominee extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        System.out.println("One");
        String cmd = request.getParameter("cmd");
        String elec_id = request.getParameter("election_id");
        String email = request.getParameter("email");
        String view = "oldRegistration.jsp";
        String title = "Nominee Registration";
        String msg = null;
        String err = null;
//      System.out.println("MSG: " + request.getParameter("election_id"));

        String password = null;

        String requirements_file = null;

        try {
            DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
            UserInfo user = objU.getUserInfo(email);
            String firstname = user.getFirstname();
            String lastname = user.getLastname();
            request.setAttribute("email", email);
            request.setAttribute("election_id", elec_id);
            request.setAttribute("name", firstname + " " + lastname);
            if (cmd.equals("register")) {
            } else if (cmd.equals("password")) {
                String newPassword = RandomString.generateRandomPassword();
                EmailSender.sendMail("electio@jaintele.com", "electio_2014", "New Password", newPassword, email);
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                objC.changeCandidatePassword(email, newPassword); // it'll update password
            }
        } catch (Exception e) {
            System.out.println("RegExistNominee: " + e);
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
        /*  System.out.println("Two");
         try {
         List<FileItem> fileItemsList = uploader.parseRequest(request);
         Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
         Date date;
         String ext;
         String fileName;
         System.out.println("Three");
         while (fileItemsIterator.hasNext()) {
         FileItem fileItem = fileItemsIterator.next();
         String fieldName = fileItem.getFieldName();
         //                System.out.println("FieldName=" + fieldName + fileItem.getString());
         if (fieldName.equals("election_id")) {
         election_id = Long.parseLong(fileItem.getString());
         } else if (fieldName.equals("firstname")) {
         firstname = fileItem.getString();
         } else if (fieldName.equals("lastname")) {
         lastname = fileItem.getString();
         } else if (fieldName.equals("email")) {
         email = fileItem.getString();
         } else if (fieldName.equals("cmd")) {
         cmd = fileItem.getString();
         } else if (fieldName.equals("password")) {
         password = fileItem.getString();
         }
         else if (fileItem.getFieldName().equals("requirements_file")) {
         if (cmd!=null && cmd.equals("save")) {
         System.out.println("FileName=" + fileItem.getName());
         System.out.println("ContentType=" + fileItem.getContentType());
         System.out.println("Size in bytes=" + fileItem.getSize());
         // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
         fileName = fileItem.getName();
         date = new Date();
         ext = fileName.substring(fileName.lastIndexOf('.'));
         File file = new File(request.getServletContext().getRealPath("/requirements_files") + File.separator + date.getTime() + ext);
         fileItem.write(file);
         requirements_file = "requirements_files" + File.separator + date.getTime() + ext;
         System.out.println("Absolute Path at server=" + requirements_file);
         System.out.println("File " + fileItem.getName() + " uploaded successfully.");
         }
         }
         }
         if (cmd.equals("register")) {
         System.out.println("resisterrrrrrr");
         view = "oldRegistration.jsp";
         System.out.println("election_ID: " + election_id + ", email: " + email + ", name: " + firstname + " " + lastname);
         request.setAttribute("election_id", election_id);
         request.setAttribute("email", email);
         request.setAttribute("name", firstname + " " + lastname);

         } else if (cmd.equals("password")) {
         System.out.println("passworddddddd");
         String newPassword = RandomString.generateRandomPassword();
         EmailSender.sendMail("electio@jaintele.com", "electio_2014", "New Password", newPassword, email);
         DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
         objC.changeCandidatePassword(email, newPassword); // it'll update password
         view = "oldRegistration.jsp";
         request.setAttribute("election_id", election_id);
         request.setAttribute("email", email);
         request.setAttribute("name", firstname + " " + lastname);
         } else if (cmd.equals("save")) {
         System.out.println("saveeeeeeee");
         if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("")) {
         err = "Please fill all required fields";
         } else {
         DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
         if (objN.nomineeLogin(election_id, email, password)) {
         if (objN.updateNominee(election_id, email, requirements_file)) {
         view = "index.jsp?election_id=" + election_id;
         msg = "update nominee successful";
         } else {
         err = "fail update nominee";
         }
         }
         }
         }
         } catch (FileUploadException e) {
         System.out.println("ERr RegisterNimineeExisting: " + e.getMessage());
         } catch (Exception e) {
         err = e.getMessage();
         System.out.println("ERR NomineeRegistration: " + e.toString());

         }
         request.setAttribute("msg", msg);
         request.setAttribute("err", err);
         request.setAttribute("title", title);
         RequestDispatcher rd = request.getRequestDispatcher(view);
         rd.forward(request, response);
         */
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
