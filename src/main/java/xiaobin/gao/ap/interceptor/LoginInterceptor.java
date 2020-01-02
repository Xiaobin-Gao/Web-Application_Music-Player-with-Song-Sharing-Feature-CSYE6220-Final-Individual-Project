/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("currentUserId") != null) {
            return true;
        } else {
            String uemail = request.getParameter("email");
            String upwd = request.getParameter("password");
            User result = UserDao.fetehUserByEP(uemail, upwd);
            if (result == null) {
                response.sendRedirect("login.htm?error=1");
                return false;
            }
            session.setAttribute("currentUserId", result.getId());
            return true;
        }
    }
}
