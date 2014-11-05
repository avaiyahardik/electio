/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplUserInfo;
import Model.UserInfo;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Hardik
 */
public class NomineeExists implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        //String elec_id = req.getParameter("election_id");
        String email = req.getParameter("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Email required";
        } else {
            try (PrintWriter out = res.getWriter()) {
                DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
                UserInfo userInfo = objU.getUserInfo(email);
                JSONObject jSONObject;
                if (objU == null) {
                    jSONObject = new JSONObject();
                    jSONObject.put("status", false);
                    jSONObject.put("name", null);
                } else {
                    jSONObject = new JSONObject();
                    jSONObject.put("status", true);
                    jSONObject.put("name", userInfo.getFirstname() + " " + userInfo.getLastname());
                }
                out.write(jSONObject.toJSONString());
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("NomineeExists Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
