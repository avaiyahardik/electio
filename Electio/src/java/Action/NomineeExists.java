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
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try (PrintWriter out = res.getWriter()) {
            if (email == null || email.equals("")) {
                jSONObject.put("status", false);
                jSONObject.put("name", null);
            } else {

                DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
                UserInfo userInfo = objU.getUserInfo(email);
                if (userInfo == null) {
                    jSONObject.put("status", false);
                    jSONObject.put("name", null);
                } else {
                    jSONObject.put("status", true);
                    jSONObject.put("name", userInfo.getFirstname() + " " + userInfo.getLastname());
                }

            }
            out.write(jSONObject.toJSONString());
        } catch (Exception ex) {
            System.out.println("NomineeExists Error: " + ex.getMessage());
        }
        return null;
    }
}
