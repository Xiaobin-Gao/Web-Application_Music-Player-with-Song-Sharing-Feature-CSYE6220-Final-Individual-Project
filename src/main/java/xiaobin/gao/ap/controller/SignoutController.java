/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author gao.xiaob
 */
public class SignoutController extends AbstractController {
    
    public SignoutController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String appName = getServletContext().getInitParameter("appName");
        session.setAttribute("currentUserId", null);
//        RequestDispatcher rd = request.getRequestDispatcher("/home.htm");
//        rd.forward(request, response);
        response.sendRedirect("/" + appName + "/home.htm");
        return null;
    }
    
}
