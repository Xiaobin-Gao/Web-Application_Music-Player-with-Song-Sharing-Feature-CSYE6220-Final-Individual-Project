/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.interceptor;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class SignupInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map errors = new HashMap<String, Boolean>();
        String uName = request.getParameter("name");
        String uEmail = request.getParameter("email");

        User nameQR = UserDao.fetehUserByNV("name", uName);
        if (nameQR != null) {
            errors.put("nameErr", true);
            request.setAttribute("errors", errors);
            RequestDispatcher rd = request.getRequestDispatcher("../signup.htm");
            rd.forward(request, response);
            return false;
        }
        Object emailQR = UserDao.fetehUserByNV("email", uEmail);
        if (emailQR != null) {
            errors.put("emailErr", true);
            request.setAttribute("errors", errors);
            RequestDispatcher rd = request.getRequestDispatcher("../signup.htm");
            rd.forward(request, response);
            return false;
        }
        return true;
    }

}
