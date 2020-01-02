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
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.SongDao;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class DeleteSongInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");
        String songId = request.getParameter("song_id");
        Song song = SongDao.fetchSongById(songId);
        UserDao.updateUserPlaylist(currentUserId, song, "DELETE");
        return true;
    }
}
