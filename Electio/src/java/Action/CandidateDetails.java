/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class CandidateDetails implements Controller.Action{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        
        String view = "candidateDetails.jsp";
        return view;
    }
    
}
