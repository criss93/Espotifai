/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Controllers.SongsController;
import Models.Genre;
import Models.Responses.GetPlaylistInfoFailedResponseBody;
import Models.Responses.GetPlaylistsResponseBody;
import Models.Responses.GetSongsFailedResponseBody;
import Models.Responses.GetSongsResponseBody;
import Models.Song;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author s_fer
 */
@Path("/mymusic/songs")
public class SongsWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //cambio tipo de genre
    public void getSongs(@Suspended final AsyncResponse asyncResponse, @QueryParam("author")String author, @QueryParam("genre") Genre genre) {
        SongsController songsController = new SongsController();
        try {
            if(author != null){
                if(genre != null){
                    List<Song> songs = songsController.getSongsFilteredByAuthorAndGenre(author, genre);            
                    asyncResponse.resume(Response.ok(new GetSongsResponseBody(songs)).build());
                } else 
                {
                    List<Song> songs = songsController.getSongsFilteredByAuthor(author);            
                    asyncResponse.resume(Response.ok(new GetSongsResponseBody(songs)).build());
                }                
            } else 
            {
                if(genre != null){
                    List<Song> songs = songsController.getSongsFilteredByGenre(genre);
                    asyncResponse.resume(Response.ok(new GetSongsResponseBody(songs)).build());
                }
            }
            CompletableFuture.supplyAsync(() -> {
                return songsController.getSongs();  
            }).thenAccept(songs -> {
                 asyncResponse.resume(Response.ok(new GetSongsResponseBody(songs)).build());
            });
        } catch (Exception ex) {
            asyncResponse.resume(Response.status(500).entity(new GetSongsFailedResponseBody(ex.getMessage())).build());
        }
    }
}
