/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Responses;

import Models.Song;
import java.util.List;

/**
 *
 * @author s_fer
 */
public class GetSongsResponseBody {
    private List<Song> songs;

    public GetSongsResponseBody() {
    }

    public GetSongsResponseBody(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
