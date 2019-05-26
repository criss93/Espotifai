/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Daos.PlaylistDao;
import Daos.SongDao;
import Models.Playlist;
import Models.Song;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 *
 * @author Cristian
 */
public class PlaylistsController {
    
    public boolean isTheSongInThePlaylist(int songId, int playlistId){
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        Song song = playlist.songs.stream().filter(s -> s.getId() == songId).findFirst().orElse(null);
        return song != null;
    }
    
    public boolean userOwnsThePlaylist(int userId, int playlistId){
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        return playlist.user.getId() == userId;
    }
    
    public boolean playlistExists(int playlistId){
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        return playlist != null;
    }
    public void addSong(int playlistId, int songId) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        SongDao songDao = new SongDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        Song song = songDao.findSong((long)songId);
        playlist.songs.add(song);
        try{
            playlistDao.edit(playlist);
        }
        catch(Exception ex){
            throw ex;
        }     
    }
    
    public void deleteSong(int playlistId, int songId) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        int index = IntStream.range(0, playlist.songs.size()).filter(i -> playlist.songs.get(i).getId() == songId).findFirst().orElse(-1);
        playlist.songs.remove(index);
        try{
            playlistDao.edit(playlist);
        }
        catch(Exception ex){
            throw ex;
        }     
    }
    
    public void deletePlaylist(int playlistId) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        try{
            playlistDao.destroy((long)playlistId);
        }
        catch(Exception ex){
            throw ex;
        }     
    }
}
