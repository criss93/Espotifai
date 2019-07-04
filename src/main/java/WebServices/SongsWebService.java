/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Controllers.SongsController;
import Models.Genre;
import Models.Responses.GetSongsResponseBody;
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
    public void getSongs(@Suspended final AsyncResponse asyncResponse, @QueryParam("author") String author, @QueryParam("genre") Genre genre) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            SongsController songsController = new SongsController();
            if (author != null) {
                if (genre != null) {
                    return songsController.getSongsFilteredByAuthorAndGenre(author, genre);
                } else {
                    return songsController.getSongsFilteredByAuthor(author);
                }
            } else if (genre != null) {
                return songsController.getSongsFilteredByGenre(genre);
            }
            return songsController.getSongs();
        }).thenApplyAsync(songs -> 
            asyncResponse.resume(Response.ok(new GetSongsResponseBody(songs)).build())).join();
    }
}
