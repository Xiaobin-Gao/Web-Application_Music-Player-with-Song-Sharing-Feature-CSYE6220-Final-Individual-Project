/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;
import xiaobin.gao.ap.service.SongDao;
import xiaobin.gao.ap.service.SpotifyService;
import xiaobin.gao.ap.service.UserDao;

/**
 *
 * @author gao.xiaob
 */
public class AddSongController extends AbstractController {

    public AddSongController() {

    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String songId = request.getParameter("song_id");
//        Track track = SpotifyService.getTrack_Sync(songId);
        ModelAndView mv = new ModelAndView("add_result");
        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("currentUserId");
        User currentUser = UserDao.fetchUserById(currentUserId);
        boolean prime = currentUser.isPrime();
        if (prime==false) {
            List<Song> playlist = UserDao.fetchUserPlaylist(currentUserId);
            if (playlist.size()==20) {
                mv.addObject("noMoreSong", true);
                return mv;
            }
        }
        Track track = SpotifyService.getTrack_Sync(songId);
        Song song = new Song();
        song.setId(track.getId());
        song.setName(track.getName());
        song.setImgUrl(track.getAlbum().getImages()[0].getUrl());
        song.setUri(track.getUri());
        song.setPreviewUrl(track.getPreviewUrl());
        int durationMs = (int) track.getDurationMs();
        int durationMin = (int) TimeUnit.MILLISECONDS.toMinutes(durationMs);
        int durationSec = (int) TimeUnit.MILLISECONDS.toSeconds(durationMs) - durationMin * 60;
        song.setDurationMin(durationMin);
        song.setDurationSec(durationSec);
        song.setAlbumName(track.getAlbum().getName());
        List artists = new ArrayList<String>();
        ArtistSimplified[] ass = track.getArtists();
        for (int i = 0; i < ass.length; i++) {
            artists.add(ass[i].getName());
        }
        song.setArtists(artists);
        SongDao.register(song);

        boolean songInPlaylist = false;
        List<Song> playlist = UserDao.fetchUserPlaylist(currentUserId);
        for (Song s : playlist) {
            if (s.getId().equals(song.getId())) {
                songInPlaylist = true;
            }
        }
        if (songInPlaylist == false) {
            UserDao.updateUserPlaylist(currentUserId, song, "ADD");
        }
        mv.addObject("songInPlaylist", songInPlaylist);
        return mv;
    }
}
