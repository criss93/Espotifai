/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Daos.PlaylistDao;
import Daos.SongDao;
import Daos.UserDao;
import Models.Playlist;
import Models.Song;
import Models.User;
import java.util.List;
import java.util.stream.Collectors;
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
    
    public void updatePlaylistName(int playlistId, String playlistName) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        playlist.name = playlistName;
        try{
            playlistDao.edit(playlist);
        }
        catch(Exception ex){
            throw ex;
        }     
    }
    
    public Playlist getPlaylistInfo(int playlistId) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        try{
            Playlist playlist = playlistDao.findPlaylist((long)playlistId);
            return playlist;
        }
        catch(Exception ex){
            throw ex;
        }     
    }
    
    public List<String> getPlaylists(){
        PlaylistDao playlistDao = new PlaylistDao();
        List<Playlist> playlists = playlistDao.findPlaylistEntities();
        List<String> playlistsNames = playlists.stream().map(p -> p.getName()).collect(Collectors.toList());
        return playlistsNames;
    }
    
    public void createPlaylist(String namePlaylist, int userId) throws Exception{
        PlaylistDao playlistDao = new PlaylistDao();
        UserDao userDao = new UserDao();
        User userObj = userDao.findUser((long) userId);
        Playlist playlist = new Playlist(namePlaylist, userObj);        
        try{
            playlistDao.create(playlist);
        }
        catch(Exception ex){
            throw ex;
        }     
    }
    
    public boolean thePlaylistExists(String name, int user_id){
        PlaylistDao playlistDao = new PlaylistDao();
        List<Playlist> playlists = playlistDao.findPlaylist(name, (long)user_id);
        return !playlists.isEmpty();
    }
}
