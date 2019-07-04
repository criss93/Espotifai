/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristian
 */
@Path("/mymusic/health")
public class HealthcheckWebService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAccount() {
        return Response.ok("{\"status\": \"ok\"}").build();
    }
}
