/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import java.io.IOException;

/**
 *
 * @author gao.xiaob
 */
public class SpotifyAPI {

    private static final String clientId = "9b89bceb41094be298505c3970a11bcc";
    private static final String clientSecret = "bbfd5570092b4e09b88e83e744f7e848";
    private static final String refreshToken = "AQBsal4anGPs3QkMDFJM53yVZf0vR-9CGTvt2yA4d2sQ9vTqq5zgHQCFu_msnPrUHCfXZrjb0TD3sgCvyUqS-1V5UmE8-3gnL8GQgc5XxNV5Quvjp-YMpv5obtXkYmvv6Bo";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken)
            .build();
    private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
            .build();

    private static SpotifyApi authorizationCodeRefresh_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

//            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi;
    }

    public static SpotifyApi build() {
        return authorizationCodeRefresh_Sync();
    }
}
