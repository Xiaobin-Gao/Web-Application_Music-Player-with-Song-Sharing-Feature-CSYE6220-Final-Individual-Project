/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class PremiumController extends AbstractController {

    public PremiumController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("premium");
        String uri = request.getRequestURI();
        if (!uri.contains("submit.htm")) {
            return mv;
        }
        
        String email = request.getParameter("email");
        User user = UserDao.fetehUserByNV("email", email);
        if (user == null) {
            mv.addObject("noSuchEmail", true);
        } else {
            if (user.isPrime()) {
                mv.addObject("alreadyPrime", true);
            } else {
                UserDao.updateUserPrime(user.getId(), true);
                mv.addObject("successful", true);
            }
        }
        mv.addObject("email", email);
        return mv;

    }

}
