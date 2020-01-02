/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.controller;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import xiaobin.gao.ap.service.SpotifyService;

/**
 *
 * @author gao.xiaob
 */
public class SearchSongController extends AbstractController {

    public SearchSongController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
//        if (uri.contains("search.htm")) {
//            return new ModelAndView("result");
//        }

        if (uri.contains("myDashboard/search/result.htm")) {
            String q = request.getParameter("q");
            if (q.equals("")) {
                ModelAndView mv = new ModelAndView("search_song");
                mv.addObject("nullQ", true);
                return mv;
            }
            String itemsPerPage = request.getParameter("items_per_page");
            String pageNo = request.getParameter("page_no");
            int itemsPerPage2 = itemsPerPage == null ? 20 : Integer.parseInt(itemsPerPage);
            int pageNo2 = pageNo == null ? 1 : Integer.parseInt(pageNo);
            Paging<Track> trackPaging = SpotifyService.searchTracks_Sync(q, itemsPerPage2,
                    (pageNo2 - 1) * itemsPerPage2);
            Track[] tracks = trackPaging.getItems();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("q", q);
            model.put("page_no", pageNo2);
            model.put("items_per_page", itemsPerPage2);
            model.put("total", trackPaging.getTotal());
            model.put("count", tracks.length);
            int pageTotal;
            if (itemsPerPage == null) {
                if (trackPaging.getTotal() % 20 != 0) {
                    pageTotal = (int) trackPaging.getTotal() / 20 + 1;
                } else {
                    pageTotal = (int) trackPaging.getTotal() / 20;
                }
                model.put("pageTotal", pageTotal);
            } else {
                if (trackPaging.getTotal() % itemsPerPage2 != 0) {
                    pageTotal = (int) trackPaging.getTotal() / itemsPerPage2 + 1;
                } else {
                    pageTotal = (int) trackPaging.getTotal() / itemsPerPage2;
                }
                model.put("pageTotal", pageTotal);
            }
            ArrayList<Map> result = new ArrayList<Map>();
            for (int i = 0; i < tracks.length; i++) {
                Map<String, Object> track = new HashMap<String, Object>();
                ArrayList<String> artists = new ArrayList<String>();
                track.put("track_id", tracks[i].getId());
                track.put("name", tracks[i].getName());
                for (int j = 0; j < tracks[i].getArtists().length; j++) {
                    artists.add(tracks[i].getArtists()[j].getName());
                }
                track.put("isPlayable", tracks[i].getIsPlayable());
                track.put("artistsCount", tracks[i].getArtists().length);
                track.put("artists", artists);
                track.put("album", tracks[i].getAlbum().getName());
                if (tracks[i].getAlbum().getImages().length > 0) {
                    track.put("img", tracks[i].getAlbum().getImages()[0].getUrl());
                }
                track.put("uri", tracks[i].getUri());
                track.put("previewUrl", tracks[i].getPreviewUrl());
                int min = (int) TimeUnit.MILLISECONDS.toMinutes(tracks[i].getDurationMs());
                int sec = (int) TimeUnit.MILLISECONDS.toSeconds(tracks[i].getDurationMs());
                track.put("durationMin", min);
                track.put("durationSec", sec - min * 60);
                result.add(track);
            }
            model.put("tracks", result);
            return new ModelAndView("search_song", "result", model);
        }
        return new ModelAndView("search_song");
    }
}
