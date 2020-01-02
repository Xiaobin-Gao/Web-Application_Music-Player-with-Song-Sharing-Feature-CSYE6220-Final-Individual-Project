/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.Message;
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.SongDao;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class DashboardController extends AbstractController {

    public DashboardController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");
        User user = UserDao.fetchUserById(currentUserId);
        List<Song> currentUserPlaylist = UserDao.fetchUserPlaylist(currentUserId);
        Map<String, Object> model = new HashMap<String, Object>();
        int count = 0;
        ArrayList songContainer = null;
        if (currentUserPlaylist != null) {
            songContainer = new ArrayList<Map<String, Object>>();
            Iterator currentUserPlaylistItr = currentUserPlaylist.iterator();
            while (currentUserPlaylistItr.hasNext()) {
                Song song = (Song) currentUserPlaylistItr.next();
                Map<String, Object> songDetails = new HashMap<String, Object>();
                if (song != null) {
                    count++;
                    songDetails.put("songId", song.getId());
                    songDetails.put("name", song.getName());
                    songDetails.put("imgUrl", song.getImgUrl());
                    songDetails.put("uri", song.getUri());
                    songDetails.put("previewUrl", song.getPreviewUrl());
                    songDetails.put("durationMin", song.getDurationMin());
                    songDetails.put("durationSec", song.getDurationSec());
                    songDetails.put("albumName", song.getAlbumName());
                    List artists = SongDao.fetchSongArtists(song.getId());
                    songDetails.put("artistCount", artists.size());
                    songDetails.put("artists", artists);
                    songContainer.add(songDetails);
                }
            }
        }

        String mode = "start";
        if (uri.contains("myDashboard/play.htm")) {
            mode = (String) request.getAttribute("mode");
        }

        List<Message> messages = UserDao.fetchUserMessages(currentUserId);
        // deal with add-as-friend requests
        if (messages != null) {
            int friendRqsCount = 0;
            Map<Integer, Integer> songMess = new HashMap<Integer, Integer>();
            List<User> fromUsers = new ArrayList<User>();
            int songMessCount = 0;
            for (Message m : messages) {
                if (m.getmType().equals("FRIENDREQUEST")) {
                    friendRqsCount++;
                    int fromUserId = m.getFromUserId();
                    User fromUser = UserDao.fetchUserById(fromUserId);
                    fromUsers.add(fromUser);
                }
                if (m.getmType().equals("SONG")) {
                    songMessCount++;
                    int fromUserId = m.getFromUserId();
                    Integer c = songMess.get(fromUserId);
                    if (c==null) {
                        songMess.put(fromUserId, 1);
                    } else {
                        songMess.put(fromUserId, c+1);
                    }
                }
            }
            model.put("songMessCount", songMessCount);
            model.put("friendRqsCount", friendRqsCount);
            model.put("fromUsers", fromUsers);
            model.put("songMess", songMess);
        }

        // display friend list
        List<User> friends = UserDao.fetchUserFriends(currentUserId);
        if (friends != null) {
            model.put("friendCount", friends.size());
            model.put("friends", friends);
        }

        model.put("no", request.getParameter("no"));
        model.put("playMode", mode);
        model.put("currentSongUri", request.getParameter("song_uri"));
        model.put("currentUser", user);
        model.put("songCount", count);
        model.put("songs", songContainer);
        return new ModelAndView("dashboard", "model", model);
    }

}
