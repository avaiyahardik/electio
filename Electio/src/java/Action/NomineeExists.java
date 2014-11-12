/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplUserInfo;
import Model.User;
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
        String elec_id = req.getParameter("election_id");
        String email = req.getParameter("email");
        System.out.println("ElecId: " + elec_id + ", email: " + email);
        JSONObject jSONObject;
        String view = "nomineeRegistration.jsp?elction_id=" + elec_id;
        try (PrintWriter out = res.getWriter()) {
            if (elec_id == null || email == null || elec_id.equals("") || email.equals("")) {
                res.sendRedirect(view);
            } else {
                jSONObject = new JSONObject();

                if (email == null || email.equals("")) {
                    jSONObject.put("status", false);
                    jSONObject.put("name", null);
                } else {

                    DBDAOImplUserInfo objU = DBDAOImplUserInfo.getInstance();
                    User userInfo = objU.getUserInfo(email);
                    if (userInfo == null) {
                        jSONObject.put("status", false);
                        jSONObject.put("name", null);
                    } else {
                        jSONObject.put("status", true);
                        jSONObject.put("name", userInfo.getFirstname() + " " + userInfo.getLastname());
                    }

                }
                out.write(jSONObject.toJSONString());

            }
        } catch (Exception ex) {
            System.out.println("NomineeExists Error: " + ex.getMessage());
        }
        return null;
    }
}
