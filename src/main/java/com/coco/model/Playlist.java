/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.model;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cristian
 */
@Entity
@Table (name="playlists")
public class Playlist extends AbstractEntity{
    

    @Column (name = "name")
    public String name;
    @ManyToOne
    @JoinColumn (name="id_user", referencedColumnName="id")
//    @JsonIgnore
    public User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn (name="id_song", referencedColumnName="id")
    public List<Song> songs;

    public Playlist() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
}
