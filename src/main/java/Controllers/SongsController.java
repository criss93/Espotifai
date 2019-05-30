/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Daos.SongDao;
import Models.Song;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class SongsController {
    
    public boolean songExist(int songId){
        SongDao songDao = new SongDao();
        Song song = songDao.findSong((long)songId);
        return song != null;
    }
    
    public List<Song> getSongs (){
        SongDao songDao = new SongDao();
        List<Song> songs = songDao.findSongEntities();
        return songs;
    }
    
    public List<Song> getSongsFilteredByAuthor(String author){
        SongDao songDao = new SongDao();
        List<Song> songs = songDao.getSongsFilteredByAuthor(author);
        return songs;
    }
    
    public List<Song> getSongsFilteredByGenre(String genre){
        SongDao songDao = new SongDao();
        List<Song> songs = songDao.getSongsFilteredByGenre(genre);
        return songs;
    }
    
    public List<Song> getSongsFilteredByAuthorAndGenre(String author, String genre){
        SongDao songDao = new SongDao();
        List<Song> songs = songDao.getSongsFilteredByAuthorAndGenre(author, genre);
        return songs;
    }
}
