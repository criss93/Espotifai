/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.controller;

import com.coco.dao.PlaylistDao;
import com.coco.dao.SongDao;
import com.coco.model.Playlist;
import com.coco.model.Song;

/**
 *
 * @author Cristian
 */
public class PlaylistsController {
    
    public boolean userOwnsThePlaylist(int userId, int playlistId){
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        return playlist.user.equals(userId);
    }
    
    public boolean playlistExists(int playlistId){
        PlaylistDao playlistDao = new PlaylistDao();
        Playlist playlist = playlistDao.findPlaylist((long)playlistId);
        return playlist != null;
    }
    public void AddSong(int playlistId, int songId) throws Exception{
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
}
