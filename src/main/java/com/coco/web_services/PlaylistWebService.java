/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.web_services;

import com.coco.controller.PlaylistsController;
import com.coco.controller.SongsController;
import com.coco.dao.PlaylistDao;
import com.coco.espotifai.JWTService;
import com.coco.model.Playlist;
import com.coco.model.requests.AddSongRequestBody;
import com.coco.model.responses.AddSongFailedResponseBody;
import com.coco.model.responses.AddSongSuccessResponseBody;
import com.coco.model.responses.UnauthorizedResponseBody;
import io.jsonwebtoken.JwtException;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristian
 */
@Path("/mymusic/playlists")
public class PlaylistWebService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@HeaderParam("Authorization") String authorization) {
        
        try {
            JWTService.decodeJWTToken(authorization);
            PlaylistDao dao = new PlaylistDao();
            List<Playlist> playlists = dao.findPlaylistEntities();
            return Response.ok(playlists).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        }
    }
    
    @POST
    @Path("{id}/songs/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addASongToAPlaylist(@HeaderParam("Authorization") String authorization, @PathParam("id") int playlistId, AddSongRequestBody request) {
        PlaylistsController playlistsController = new PlaylistsController();
        SongsController songsController = new SongsController();
        try {
            int user_id = parseInt(JWTService.decodeJWTToken(authorization));
            if(!playlistsController.userOwnsThePlaylist(user_id, playlistId))
                return Response.status(401).entity(new AddSongFailedResponseBody("The user doesn't have permission to add songs to this playlist.")).build();
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new AddSongFailedResponseBody("Playlist not found.")).build();
            if(!songsController.songExist(request.getId()))
                return Response.status(400).entity(new AddSongFailedResponseBody("Invalid song.")).build();
            playlistsController.AddSong(playlistId, request.getId());
            return Response.ok( new AddSongSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new AddSongFailedResponseBody(ex.getMessage())).build();
        }
    }
}