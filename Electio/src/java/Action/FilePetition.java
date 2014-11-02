/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import Utilities.EmailSender;
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
                view="petition.jsp";
                title="File Petition";
            }
            else if(description.equals("save")){
                String[] to={email};
                if(EmailSender.sendMail("electio@jaintele.com", "electio_2014","File Petition", description, to)){
                    msg="Approve File Petition";
                }
                else{
                    err="Not Valid Reason";
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
    
}
