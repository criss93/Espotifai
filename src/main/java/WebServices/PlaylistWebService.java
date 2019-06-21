/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Controllers.PlaylistsController;
import Controllers.SongsController;
import Daos.PlaylistDao;
import Espotifai.JWTService;
import Models.Playlist;
import Models.Requests.AddSongRequestBody;
import Models.Requests.CreatePlaylistRequestBody;
import Models.Requests.UpdatePlaylistNameRequestBody;
import Models.Responses.AddSongFailedResponseBody;
import Models.Responses.AddSongSuccessResponseBody;
import Models.Responses.CreatePlaylistFailedResponseBody;
import Models.Responses.CreatePlaylistSuccessResponseBody;
import Models.Responses.DeletePlaylistFailedResponseBody;
import Models.Responses.DeletePlaylistSuccessResponseBody;
import Models.Responses.DeleteSongFailedResponseBody;
import Models.Responses.DeleteSongSuccessResponseBody;
import Models.Responses.GetPlaylistInfoFailedResponseBody;
import Models.Responses.GetPlaylistsResponseBody;
import Models.Responses.UnauthorizedResponseBody;
import Models.Responses.UpdatePlaylistNameFailedResponseBody;
import Models.Responses.UpdatePlaylistNameSuccessResponseBody;
import io.jsonwebtoken.JwtException;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public Response getPlaylists() {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            List<String> playlists = playlistsController.getPlaylists();            
            return Response.ok(new GetPlaylistsResponseBody(playlists)).build();
        } catch (Exception ex) {
            return Response.status(500).entity(new GetPlaylistInfoFailedResponseBody(ex.getMessage())).build();
        }
    }
    
    @POST
    //No tiene el path porque ya tenemos el path base en la clase
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(@HeaderParam("Authorization") String authorization, CreatePlaylistRequestBody request) {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            int user_id = parseInt(JWTService.decodeJWTToken(authorization));            
            if(playlistsController.thePlaylistExists(request.getName(), user_id))
                return Response.status(400).entity(new CreatePlaylistFailedResponseBody("The playlist already exists.")).build();
            playlistsController.createPlaylist(request.getName(), user_id);
            return Response.ok(new CreatePlaylistSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new CreatePlaylistFailedResponseBody(ex.getMessage())).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistInfo(@PathParam("id") int playlistId) {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new GetPlaylistInfoFailedResponseBody("Playlist not found.")).build();
            Playlist playlist = playlistsController.getPlaylistInfo(playlistId);
            return Response.ok(playlist).build();
        } catch (Exception ex) {
            return Response.status(500).entity(new GetPlaylistInfoFailedResponseBody(ex.getMessage())).build();
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
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new AddSongFailedResponseBody("Playlist not found.")).build();
            if(!playlistsController.userOwnsThePlaylist(user_id, playlistId))
                return Response.status(401).entity(new AddSongFailedResponseBody("The user doesn't have permission to add songs in the playlist.")).build();
            if(!songsController.songExist(request.getId()))
                return Response.status(400).entity(new AddSongFailedResponseBody("Invalid song.")).build();            
            if(playlistsController.isTheSongInThePlaylist(request.getId(), playlistId))
                return Response.status(400).entity(new AddSongFailedResponseBody("The song is already in the playlist.")).build();
            playlistsController.addSong(playlistId, request.getId());
            return Response.ok( new AddSongSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new AddSongFailedResponseBody(ex.getMessage())).build();
        }
    }
    
    @DELETE
    @Path("{id}/songs/{song_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteASongFromAPlaylist(@HeaderParam("Authorization") String authorization, @PathParam("id") int playlistId, @PathParam("song_id") int songId) {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            int user_id = parseInt(JWTService.decodeJWTToken(authorization));
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new DeleteSongFailedResponseBody("Playlist not found.")).build();
            if(!playlistsController.userOwnsThePlaylist(user_id, playlistId))
                return Response.status(401).entity(new DeleteSongFailedResponseBody("The user doesn't have permission to remove songs from the playlist.")).build();
            if(!playlistsController.isTheSongInThePlaylist(songId, playlistId))
                return Response.status(400).entity(new DeleteSongFailedResponseBody("The song is not in the playlist.")).build();
            playlistsController.deleteSong(playlistId, songId);
            return Response.ok( new DeleteSongSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new DeleteSongFailedResponseBody(ex.getMessage())).build();
        }
    }
        
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@HeaderParam("Authorization") String authorization, @PathParam("id") int playlistId) {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            int user_id = parseInt(JWTService.decodeJWTToken(authorization));
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new DeletePlaylistFailedResponseBody("Playlist not found.")).build();
            if(!playlistsController.userOwnsThePlaylist(user_id, playlistId))
                return Response.status(401).entity(new DeletePlaylistFailedResponseBody("The user doesn't have permission to delete the playlist.")).build();
            playlistsController.deletePlaylist(playlistId);
            return Response.ok( new DeletePlaylistSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new DeletePlaylistFailedResponseBody(ex.getMessage())).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylistName(@HeaderParam("Authorization") String authorization, @PathParam("id") int playlistId, UpdatePlaylistNameRequestBody request) {
        PlaylistsController playlistsController = new PlaylistsController();
        try {
            int user_id = parseInt(JWTService.decodeJWTToken(authorization));
            if(!playlistsController.playlistExists(playlistId))
                return Response.status(404).entity(new UpdatePlaylistNameFailedResponseBody("Playlist not found.")).build();
            if(!playlistsController.userOwnsThePlaylist(user_id, playlistId))
                return Response.status(401).entity(new UpdatePlaylistNameFailedResponseBody("The user doesn't have permission to edit the playlist.")).build();
            playlistsController.updatePlaylistName(playlistId, request.getName());
            return Response.ok( new UpdatePlaylistNameSuccessResponseBody()).build();
        } catch (JwtException ex) {
            UnauthorizedResponseBody responseBody = new UnauthorizedResponseBody(ex.getMessage());
            return Response.status(401).entity(responseBody).build();
        } catch (Exception ex){
            return Response.status(500).entity(new UpdatePlaylistNameFailedResponseBody(ex.getMessage())).build();
        }
    }    
}