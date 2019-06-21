/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cristian
 */
@Entity
@Table (name="songs")
@NamedQueries({
    @NamedQuery(name = "Song.findSongsByAuthor", query="SELECT s FROM Song s WHERE s.author = :author"),
    @NamedQuery(name = "Song.findSongsByGenre", query="SELECT s FROM Song s WHERE s.genre = :genre"),
    @NamedQuery(name = "Song.findSongsByAuthorAndGenre", query="SELECT s FROM Song s WHERE s.author = :author AND s.genre = :genre"),
})
public class Song extends AbstractEntity  {
  
    @Column (name = "name")
    public String name;
    @Column (name = "author")
    public String author;
    @Enumerated(EnumType.STRING)
    public Genre genre;
    
    
    public Song(){
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
}
