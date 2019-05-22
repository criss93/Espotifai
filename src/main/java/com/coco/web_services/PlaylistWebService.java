/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.web_services;

import com.coco.dao.PlaylistDao;
import com.coco.espotifai.JWTService;
import com.coco.model.Playlist;
import com.coco.model.responses.UnauthorizedResponseBody;
import io.jsonwebtoken.JwtException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
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
}