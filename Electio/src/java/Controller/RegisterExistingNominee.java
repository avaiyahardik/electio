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
        System.out.println("Two");
        String password = null;
        String firstname = null;
        String lastname = null;
        String requirements_file = "requirements_files" + File.separator + "electio.pdf";

        try {
            DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
            UserInfo user = null;
            try {
                user = objU.getUserInfo(email);
                firstname = user.getFirstname();
                lastname = user.getLastname();
                System.out.println("out: EMAIL " + email);
                System.out.println("out: ElecID: " + elec_id);
                request.setAttribute("email", email);
                request.setAttribute("election_id", elec_id);
                request.setAttribute("name", firstname + " " + lastname);
                System.out.println("SMD: " + cmd);
                System.out.println("Email:" + email);
                System.out.println("Election_ID: " + elec_id);
            } catch (Exception e) {
                System.out.println("RegExistingNom Err: " + e.getMessage());
            }
            if (cmd == null) {
                System.out.println("cmd: save");
                List<FileItem> fileItemsList = uploader.parseRequest(request);
                Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
                long election_id = 0;
                if (elec_id != null) {
                    election_id = Long.parseLong(elec_id);
                }
                while (fileItemsIterator.hasNext()) {
                    FileItem fileItem = fileItemsIterator.next();
                    String fieldName = fileItem.getFieldName();
                    System.out.println("FieldName=" + fieldName);
                    if (fieldName.equals("cmd")) {
                        cmd = fileItem.getString();
                        System.out.println("cmd: " + cmd);
                    } else if (fieldName.equals("election_id")) {
                        election_id = Long.parseLong(fileItem.getString());
                        System.out.println("Elec_ID: " + election_id);
                    } else if (fieldName.equals("email")) {
                        email = fileItem.getString();
                        System.out.println("Emeail: " + email);
                    } else if (fieldName.equals("password")) {
                        password = fileItem.getString();
                        System.out.println("PWD: " + password);
                    } else if (fieldName.equals("requirements_file")) {

                        System.out.println("FileName=" + fileItem.getName());
                        System.out.println("ContentType=" + fileItem.getContentType());
                        System.out.println("Size in bytes=" + fileItem.getSize());
                        // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                        String fileName = fileItem.getName();
                        Date date = new Date();
                        String ext = fileName.substring(fileName.lastIndexOf('.'));
                        File file = new File(request.getServletContext().getRealPath("/requirements_files") + File.separator + date.getTime() + ext);
                        fileItem.write(file);
                        requirements_file = "requirements_files" + File.separator + date.getTime() + ext;
                        System.out.println("Absolute Path at server=" + requirements_file);
                        System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                    }
                }
                System.out.println("Msg1");
                if (cmd.equals("save")) {
                    objU = DBDAOImplUserInfo.getInstance();
                    user = objU.getUserInfo(email);
                    firstname = user.getFirstname();
                    lastname = user.getLastname();
                    System.out.println("SAVE: EMAIL " + email);
                    System.out.println("SAVE: ElecID: " + election_id + "");
                    request.setAttribute("email", email);
                    request.setAttribute("election_id", election_id + "");
                    request.setAttribute("name", firstname + " " + lastname);
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    System.out.println("Going to checkNominee Login");
                    if (objN.checkNomineeLogin(email, password)) {
                        if (objN.registerNominee(election_id, email, requirements_file, 0)) {
                            System.out.println("Existing Nominee in diff election registered");
                            msg = "Registered Successfully";
                        } else {
                            err = "Error occurs while registering";
                            System.out.println("Error while registering existing nominee");
                        }
                    } else {
                        System.out.println("Invalid pwd");
                        err = "Invalid password";
                    }
                    view = "index.jsp?election_id=" + election_id;
                    title = "Login";
                } else {
                    view = "index.jsp?election_id=" + election_id;
                    title = "Login";
                    err = "Invalid command";
                }
            } else if (cmd.equals("password")) {
                String newPassword = RandomString.generateRandomPassword();
                EmailSender.sendMail("electio@jaintele.com", "electio_2014", "New Password", newPassword, email);
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                objC.changeCandidatePassword(email, newPassword); // it'll update password
            }
            // else it'll be register
        } catch (Exception e) {
            System.out.println("RegExistNominee: " + e);
        }
        request.setAttribute("msg", msg);
        request.setAttribute("err", err);
        request.setAttribute("view", view);
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
