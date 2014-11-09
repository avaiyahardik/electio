/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplementation;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
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
        String view = "nomineeRegistration.jsp";
        String title = "Nominee Registration";
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
        String password = null;
        String retype_password = null;
        String image = null;
        String requirements_file = null;
        int status = 0;
        long image_size = 0, requirements_file_size = 0;

        try {
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
                    view += "?election_id=" + election_id;
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
                } else if (fieldName.equals("password")) {
                    password = fileItem.getString();
                } else if (fieldName.equals("retype_password")) {
                    retype_password = fileItem.getString();
                } else if (fileItem.getFieldName().equals("photo")) {
                    System.out.println("FileName=" + fileItem.getName());
                    System.out.println("ContentType=" + fileItem.getContentType());
                    System.out.println("Size in bytes=" + fileItem.getSize());
                    image_size = fileItem.getSize();
                    if (image_size > (50 * 1000)) {
                        continue;
                    }
                    // File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                    fileName = fileItem.getName();
                    System.out.println("File Name: " + fileName);
                    date = new Date();
                    ext = fileName.substring(fileName.lastIndexOf('.'));
                    File file = new File(request.getServletContext().getRealPath("/user_images") + File.separator + date.getTime() + ext);
//                    File file = new File(request.getRequestURL().substring(0, request.getRequestURL().indexOf("Electio") + 8).toString() + File.separator + "user_images" + File.separator + date.getTime() + ext);
                    fileItem.write(file);
                    image = "user_images" + File.separator + date.getTime() + ext;
                    System.out.println("Absolute Path at server=" + image);
                    System.out.println("File " + fileItem.getName() + " uploaded successfully.");
                } else if (fileItem.getFieldName().equals("requirements_file")) {
                    System.out.println("FileName=" + fileItem.getName());
                    System.out.println("ContentType=" + fileItem.getContentType());
                    System.out.println("Size in bytes=" + fileItem.getSize());
                    requirements_file_size = fileItem.getSize();
                    if (requirements_file_size > (100 * 1000)) {
                        continue;
                    }
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
            if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("") || gender == null || gender.equals("") || mobile == null || mobile.equals("") || organization_name == null || organization_name.equals("") || organization_address == null || organization_address.equals("") || about_organization == null || about_organization.equals("") || password == null || password.equals("") || retype_password == null || retype_password.equals("")) {
                err = "Please fill all required fields";
            } else {
                if (image_size > (50 * 1000)) {
                    err = "Image size should be less than 50kb";
                } else if (requirements_file_size > (100 * 1000)) {
                    err = "PDF file size should be less than 100kb";
                } else {
                    if (retype_password.equals(password)) {
                        password = RandomString.encryptPassword(password);
                        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                        DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                        DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                        Organization org = new Organization(organization_name, organization_address, about_organization);
                        long organization_id = objO.addNewOrganization(org);
                        int gen = Integer.parseInt(gender);
                        Nominee nominee = new Nominee(firstname, lastname, email, gen, mobile, organization_id, image, password, election_id, requirements_file, status);
                        if (objN.registerNominee(nominee)) {
                            if (objP.checkEmailExists(email)) {
                                ProbableNominee pn = new ProbableNominee(election_id, email, 2);
                                objP.changeProbableNomineeStatus(pn);
                            }
                            view = "index.jsp?election_id=" + election_id;
                            msg = "Nominee registered successfully";
                        } else {
                            err = "Fail to register nominee, please retry";
                        }
                    } else {
                        err = "Retype password doesn't match";
                    }
                }
            }
        } catch (FileUploadException e) {
            System.out.println("File Not Found Exception in uploading file.");
        } catch (Exception e) {
            err = e.getMessage();
            System.out.println("ERR NomineeRegistration: " + e.toString());
        }

        System.out.println("Nom Reg MSG: " + msg);
        System.out.println("Nom Reg Err: " + err);
        view += "&msg=" + msg + "&err=" + err + "&title=" + title;
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
