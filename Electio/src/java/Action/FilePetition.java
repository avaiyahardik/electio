/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Utilities.EmailSender;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class FilePetition implements Controller.Action{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String elec_id=(String)req.getSession().getAttribute("election_id");
        String email=(String)req.getSession().getAttribute("candidate_email");
        String description=req.getParameter("description");
        String err="";
        String title="Login";
        String msg="";
        String view="index.jsp";
        if(email.equals("") || email == null || elec_id.equals("") || elec_id == null){
            err = "Session expired please login again";
        }
        else{
            if(description==null){
                try {
                    res.sendRedirect("Controller?action=election_result&election_id=1&err=Please enter some description about your petition");
                } catch (IOException ex) {
                    Logger.getLogger(FilePetition.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                String[] to={email};
                if(EmailSender.sendMail("electio@jaintele.com", "electio_2014","File Petition", description, to)){
                    try {
                        res.sendRedirect("Controller?action=election_result&election_id=1&msg=Petition Filed successfully!");
                    } catch (IOException ex) {
                        Logger.getLogger(FilePetition.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
    
}
