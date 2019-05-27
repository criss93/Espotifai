/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Responses;

import java.util.List;

/**
 *
 * @author Cristian
 */
public class GetPlaylistsResponseBody {
    private List<String> playlists;

    public GetPlaylistsResponseBody() {
    }

    public GetPlaylistsResponseBody(List<String> playlists) {
        this.playlists = playlists;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }
}
