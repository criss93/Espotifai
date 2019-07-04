/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import Espotifai.JWTService;
import Models.Responses.UnauthorizedResponseBody;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Cristian
 */
@Provider
public class AuthorizationFilter implements ContainerRequestFilter{

    private final String LOGIN_PATH = "/mymusic/auth";
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(!(requestContext.getUriInfo().getRequestUri().getPath().equals(LOGIN_PATH)
                || requestContext.getMethod().equals(HttpMethod.OPTIONS))){
            String apiKey = requestContext.getHeaderString("Authorization");
            try{
                JWTService.decodeJWTToken(apiKey);
            }catch(JwtException ex){
                requestContext.abortWith(
                        Response.status(
                                Response.Status.UNAUTHORIZED)
                                .type(MediaType.APPLICATION_JSON)
                                .entity(new UnauthorizedResponseBody("Wrong credentials.")).build());
            }
        }
    }
}

