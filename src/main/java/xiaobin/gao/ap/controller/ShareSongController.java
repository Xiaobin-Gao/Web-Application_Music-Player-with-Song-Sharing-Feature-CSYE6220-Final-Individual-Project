/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import java.util.List;
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
public class ShareSongController extends AbstractController {

    public ShareSongController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");
        List<User> friends = UserDao.fetchUserFriends(currentUserId);
        ModelAndView mv = new ModelAndView("share_song");
        if (friends != null) {
            mv.addObject("friendCount", friends.size());
            mv.addObject("friends", friends);
            mv.addObject("songId", request.getParameter("song_id"));
        }

        String uri = request.getRequestURI();
        String songId = request.getParameter("song_id");
        if (uri.contains("share/submit.htm")) {
            String[] friendsSelected = request.getParameterValues("friends");
            for (int i = 0; i < friendsSelected.length; i++) {
                int friendId = Integer.parseInt(friendsSelected[i]);
                Message m = new Message(currentUserId, "SONG", songId);
                MessageDao.register(m);
                UserDao.updateUserMessages(friendId, m, "ADD");
            }
            return new ModelAndView("share_submit");
        }
        return mv;
    }

}
