/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplUserInfo;
import DAO.DBDAOImplementation;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
import Model.UserInfo;
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
public class UpdateCandidate extends HttpServlet {

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
        System.out.println("1");
        String view = "Controller?action=candidate_profile";
        String title = "Profile";
        String msg = null;
        String err = null;
//      System.out.println("MSG: " + request.getParameter("election_id"));
        long election_id = 0;
        String firstname = null;
        String lastname = null;
        String email = null;
        String gender = null;
        String mobile = null;
        String organization_name = null;
        String organization_address = null;
        String about_organization = null;
        String image = null;
        String manifesto = null;

        try {
//            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
            DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
            DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            Date date;
            String ext;
            String fileName;
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                String fieldName = fileItem.getFieldName();
                //System.out.println("FieldName=" + fieldName + fileItem.getString());
                if (fieldName.equals("election_id")) {
                    election_id = Long.parseLong(fileItem.getString());
                } else if (fieldName.equals("firstname")) {
                    firstname = fileItem.getString();
                } else if (fieldName.equals("lastname")) {
                    lastname = fileItem.getString();
                } else if (fieldName.equals("email")) {
                    email = fileItem.getString();
                } else if (fieldName.equals("gender")) {
                    gender = fileItem.getString();
                } else if (fieldName.equals("mobile")) {
                    mobile = fileItem.getString();
                } else if (fieldName.equals("organization_name")) {
                    organization_name = fileItem.getString();
                } else if (fieldName.equals("organization_address")) {
                    organization_address = fileItem.getString();
                } else if (fieldName.equals("about_organization")) {
                    about_organization = fileItem.getString();
                } else if (fileItem.getFieldName().equals("photo")) {
                    System.out.println("FileName=" + fileItem.getName());
                    System.out.println("ContentType=" + fileItem.getContentType());
                    System.out.println("Size in bytes=" + fileItem.getSize());
                    // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                    fileName = fileItem.getName();
                    if (fileName != null) {
                        System.out.println("File Name: " + fileName);
                        date = new Date();
                        ext = fileName.substring(fileName.lastIndexOf('.'));
                        File file = new File(request.getServletContext().getRealPath("/user_images") + File.separator + date.getTime() + ext);
                        fileItem.write(file);
                        image = "user_images" + File.separator + date.getTime() + ext;
                        System.out.println("Absolute Path at server=" + image);
                        System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                        if (objU.updateProfilePicture(email, image)) {
                            msg = "Profile updated successfully";
                        } else {
                            err = "Fail to update profile, please retry";
                        }
                    }
                } else if (fileItem.getFieldName().equals("manifesto_file")) {
                    System.out.println("FileName=" + fileItem.getName());
                    System.out.println("ContentType=" + fileItem.getContentType());
                    System.out.println("Size in bytes=" + fileItem.getSize());
                    // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                    fileName = fileItem.getName();
                    if (fileName != null) {
                        date = new Date();
                        ext = fileName.substring(fileName.lastIndexOf('.'));
                        File file = new File(request.getServletContext().getRealPath("/manifestos") + File.separator + date.getTime() + ext);
                        fileItem.write(file);
                        manifesto = "manifesto" + File.separator + date.getTime() + ext;
                        System.out.println("Absolute Path at server=" + manifesto);
                        System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                        if (objC.updateManifesto(election_id, email, manifesto)) {
                            msg = "Profile updated successfully";
                        } else {
                            err = "Fail to update profile, please retry";
                        }
                    }
                }
                //System.out.println("lastname"+fileItem.getString());
            }

            if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("") || gender == null || gender.equals("") || mobile == null || mobile.equals("") || organization_name == null || organization_name.equals("") || organization_address == null || organization_address.equals("") || about_organization == null || about_organization.equals("")) {
                err = "Please fill all required fields";
            } else {

                //password = RandomString.encryptPassword(password);
                Organization org = new Organization(organization_name, organization_address, about_organization);
                long organization_id = objO.addNewOrganization(org);
                int gen = Integer.parseInt(gender);
                UserInfo ui = new UserInfo();
                ui.setEmail(email);
                ui.setFirstname(firstname);
                ui.setGender(gen);
                ui.setLastname(lastname);
                ui.setMobile(mobile);
                ui.setOrganization_id(organization_id);

                if (objU.updateUserInfo(ui)) {
                    msg = "Nominee updated successfully";
                } else {
                    err = "Fail to update profile, please retry";
                }

            }
        } catch (FileUploadException e) {
            System.out.println("File Not Found Exception in uploading file.");
        } catch (Exception e) {
            err = e.getMessage();
            System.out.println("ERR UpdateCandidate: " + e.toString());
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
