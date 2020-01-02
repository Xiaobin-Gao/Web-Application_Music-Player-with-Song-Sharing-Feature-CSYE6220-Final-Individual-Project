/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.Message;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.MessageDao;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class AddFriendController extends AbstractController {

    public AddFriendController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        Map model = new HashMap<String, Object>();
        if (uri.contains("addFriend/search.htm")) {
            model.put("search", true);
            String searchBy = request.getParameter("search_by");
            User user = null;
            if (searchBy.equals("name")) {
                user = UserDao.fetehUserByNV("name", request.getParameter("search"));
                model.put("name", request.getParameter("search"));
            }
            if (searchBy.equals("email")) {
                user = UserDao.fetehUserByNV("email", request.getParameter("search"));
                model.put("email", request.getParameter("search"));
            }
            if (user != null) {
                model.put("otherUser", user);
//                UserDao.updateUserFriends((int) session.getAttribute("currentUserId"), user, "ADD");
            } else {
                model.put("noResult", true);
            }
        }
        if (uri.contains("addFriend/search/sendFriendRequest.htm")) {
            int otherUserId = Integer.parseInt(request.getParameter("user_id"));
            User otherUser = UserDao.fetchUserById(otherUserId);
            if(!otherUser.isPrime()) {
                model.put("notPrime", true);
                return new ModelAndView("add_friend", "model", model);
            }
            List<User> friends = UserDao.fetchUserFriends(otherUserId);
            int currentUserId = (int) session.getAttribute("currentUserId");
            for (User f: friends) {
                if(f.getId()==currentUserId) {
                    model.put("alreadyFriend", true);
                    return new ModelAndView("add_friend", "model", model);
                }
            }
            Message m = new Message((int) session.getAttribute("currentUserId"), "FRIENDREQUEST", null);
            MessageDao.register(m);
            UserDao.updateUserMessages(otherUserId, m, "ADD");
            model.put("friendRSent", true);
        }
        return new ModelAndView("add_friend", "model", model);
    }

}
