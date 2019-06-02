/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Models.Genre;
import Models.Song;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cristian
 */
public class SongDao extends AbstractDao implements Serializable {

    public void create(Song song) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(song);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Song song) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            song = em.merge(song);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = song.getId();
                if (findSong(id) == null) {
                    throw new Exception("The song with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Song song;
            try {
                song = em.getReference(Song.class, id);
                song.getId();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The song with id " + id + " no longer exists.", enfe);
            }
            em.remove(song);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Song> findSongEntities() {
        return findSongEntities(true, -1, -1);
    }

    public List<Song> findSongEntities(int maxResults, int firstResult) {
        return findSongEntities(false, maxResults, firstResult);
    }

    private List<Song> findSongEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Song.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Song findSong(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Song.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Song> getSongsFilteredByAuthor(String author) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Song.findSongsByAuthor");
            q.setParameter("author", author);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    //tambien acá
    public List<Song> getSongsFilteredByGenre(Genre genre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Song.findSongsByGenre");
            q.setParameter("genre", genre);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    //Y acá
    public List<Song> getSongsFilteredByAuthorAndGenre(String author, Genre genre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Song.findSongsByAuthorAndGenre");
            q.setParameter("author", author);
            q.setParameter("genre", genre);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getSongCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Song> rt = cq.from(Song.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
