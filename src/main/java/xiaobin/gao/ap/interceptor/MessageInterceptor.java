/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xiaobin.gao.ap.model.Message;
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.MessageDao;
import xiaobin.gao.ap.service.SongDao;
import xiaobin.gao.ap.service.SpotifyService;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class MessageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");

        String uri = request.getRequestURI();
        if (uri.contains("message/interested.htm")) {
            String song_id = request.getParameter("song_id");
            List<Song> playlist = UserDao.fetchUserPlaylist(currentUserId);
          
            for (Song s : playlist) {
                if (s.getId().equals(song_id)) {
                    request.setAttribute("songExisted", true);
                    break;
                }
            }
            if (request.getAttribute("songExisted") == null) {
                UserDao.updateUserPlaylist(currentUserId, SongDao.fetchSongById(song_id), "ADD");
                request.setAttribute("songAdded", true);
            }

            int messId = Integer.parseInt(request.getParameter("message_id"));
            Message m = new Message();
            m.setId(messId);
            UserDao.updateUserMessages(currentUserId, m, "DELETE");
            MessageDao.deleteMessageById(messId);
        }

        if (uri.contains("message/not_interested.htm")) {
            int messId = Integer.parseInt(request.getParameter("message_id"));
            Message m = new Message();
            m.setId(messId);
            UserDao.updateUserMessages(currentUserId, m, "DELETE");
            MessageDao.deleteMessageById(messId);
        }
        return true;
    }
}
