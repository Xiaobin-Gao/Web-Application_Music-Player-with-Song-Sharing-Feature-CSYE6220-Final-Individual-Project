/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xiaobin.gao.ap.service.SpotifyService;

/**
 *
 * @author gao.xiaob
 */
public class PlaySongInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getParameter("song_uri");
        String playMode = request.getParameter("mode");
        String no = request.getParameter("no");
        if (playMode.equals("start")) {
            SpotifyService.startUsersPlayback_Sync(uri);
            playMode = "pause";
        }
        else if (playMode.equals("pause")) {
            SpotifyService.pauseUsersPlayback_Sync();
            playMode = "resume";
        }
        else if (playMode.equals("resume")) {
            SpotifyService.resumeUsersPlayback_Sync();
            playMode = "pause";
        }
        request.setAttribute("mode", playMode);
        return true;
    }
    
}
