/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class FriendRequestController extends AbstractController {

    public FriendRequestController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        String userName = request.getParameter("user_name");
        int currentUserId = (int) request.getSession().getAttribute("currentUserId");
        User currentUser = UserDao.fetchUserById(currentUserId);
        User fromUser = UserDao.fetehUserByNV("name", userName);
        if (uri.contains("accept.htm")) {
            UserDao.updateUserFriends(currentUserId, fromUser, "ADD");
            UserDao.updateUserFriends(fromUser.getId(), currentUser, "ADD");
        }
        Message m = MessageDao.fetchMessageByFromUserId(fromUser.getId());
        UserDao.updateUserMessages(currentUserId, m, "DELETE");
        if (uri.contains("refuse.htm")) {
            MessageDao.deleteMessageById(m.getId());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/myDashboard.htm");
        rd.forward(request, response);
        return null;
    }
}
