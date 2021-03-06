/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


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
@NamedQueries({
    @NamedQuery(name = "Playlist.findByNameAndUser", query="SELECT p FROM Playlist p WHERE p.name = :name AND p.user.id = :id_user"),
})
public class Playlist extends AbstractEntity{
    

    @Column (name = "name")
    public String name;
    @ManyToOne
    @JoinColumn (name="id_user", referencedColumnName="id")
    @JsonIgnore
    public User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn (name="id_song", referencedColumnName="id")
    public List<Song> songs;

    public Playlist() {
    }

    public Playlist(String name, User user) {
        this.name = name;
        this.user = user;
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
