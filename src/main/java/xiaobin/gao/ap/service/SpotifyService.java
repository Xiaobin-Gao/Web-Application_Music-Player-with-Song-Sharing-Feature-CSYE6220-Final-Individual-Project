/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import com.google.gson.JsonParser;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.player.PauseUsersPlaybackRequest;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import java.io.IOException;

/**
 *
 * @author gao.xiaob
 */
public class SpotifyService {

    public static Track getTrack_Sync(String id) {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
                //          .market(CountryCode.SE)
                .build();
        Track track = null;
        try {
            track = getTrackRequest.execute();
            System.out.println("Name: " + track.getName());

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return track;
    }

    public static Paging<Track> searchTracks_Sync(String q) throws IOException, SpotifyWebApiException {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
                .market(CountryCode.US)
                .limit(20)
                //          .offset(0)
                .build();
        Paging<Track> trackPaging = searchTracksRequest.execute();
        return trackPaging;
    }

    public static Paging<Track> searchTracks_Sync(String q, int limit, int offset) throws IOException, SpotifyWebApiException {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
                .market(CountryCode.US)
                .limit(limit)
                .offset(offset)
                .build();
        Paging<Track> trackPaging = searchTracksRequest.execute();
        return trackPaging;
    }

    public static void startUsersPlayback_Sync(String track_uri) {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
                .startResumeUsersPlayback()
                //          .context_uri("spotify:album:5zT1JLIj9E57p3e1rFm9Uq")
                .device_id("91fad551f9023ee8524ace98100c4a6fb7d5dc2d")
                .offset(new JsonParser().parse("{\"uri\":\"" + track_uri + "\"}").getAsJsonObject())
                .uris(new JsonParser().parse("[\"" + track_uri + "\"]").getAsJsonArray())
                //          .position_ms(10000)
                .build();
        try {
            String string = startResumeUsersPlaybackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void pauseUsersPlayback_Sync() {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
                //                .device_id("91fad551f9023ee8524ace98100c4a6fb7d5dc2d")
                .build();
        try {
            String string = pauseUsersPlaybackRequest.execute();
            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void resumeUsersPlayback_Sync() {
        SpotifyApi spotifyApi = SpotifyAPI.build();
        StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
                .startResumeUsersPlayback()
                //          .context_uri("spotify:album:5zT1JLIj9E57p3e1rFm9Uq")
                //                .device_id("91fad551f9023ee8524ace98100c4a6fb7d5dc2d")
                //                .offset(new JsonParser().parse("{\"uri\":\"spotify:track:3MjUtNVVq3C8Fn0MP3zhXa\"}").getAsJsonObject())
                //                .uris(new JsonParser().parse("[\"spotify:track:3MjUtNVVq3C8Fn0MP3zhXa\"]").getAsJsonArray())
                //          .position_ms(10000)
                .build();
        try {
            String string = startResumeUsersPlaybackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        resumeUsersPlayback_Sync();
    }
}
