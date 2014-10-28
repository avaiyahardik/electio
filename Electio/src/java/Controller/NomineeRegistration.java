/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBDAOImplementation;
import Model.Nominee;
import Model.Organization;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Hardik
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
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
            int status = 0;
            System.out.println("3");
            if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || email == null || email.equals("") || mobile == null || mobile.equals("") || organization_name == null || organization_name.equals("") || organization_address == null || organization_address.equals("") || about_organization == null || about_organization.equals("") || password == null || password.equals("") || retype_password == null || retype_password.equals("")) {
                err = "Please fill all required fields";
            } else {
                if (retype_password.equals(password)) {
                    // upload image and pdf file here...
                    uploadImage(request, response);
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

    private static final String SAVE_DIR = "user_images";

    /**
     * handles file upload
     */
    private boolean uploadImage(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        System.out.println("Upload Image Path: " + savePath);
        // temporary change location manually
//        savePath = "D:\\work";
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            System.out.println("File Name: " + fileName);
            part.write(savePath + File.separator + fileName);
        }

//        request.setAttribute("message", "Upload has been done successfully!");
//        getServletContext().getRequestDispatcher("/message.jsp").forward(
//                request, response);
        return true;
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
