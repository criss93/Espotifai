/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.controller;

import com.coco.dao.SongDao;
import com.coco.model.Song;

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
}
