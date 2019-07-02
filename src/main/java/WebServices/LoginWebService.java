/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Controllers.LoginController;
import Models.User;
import Models.Requests.LoginRequestBody;
import Models.Responses.LoginFailedResponseBody;
import Models.Responses.LoginResponseBody;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Cristian
 */
@Path("/mymusic/auth")
public class LoginWebService {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(LoginRequestBody loginRequest) {
        LoginController controller = new LoginController();
        String token;
        try {
            token = controller.login(loginRequest.getEmail(), loginRequest.getPassword());
            LoginResponseBody responseBody = new LoginResponseBody(token);
            return Response.ok(responseBody)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "content-type, authorization")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .build();
        } catch (ValidationException ex) {
            LoginFailedResponseBody responseBody = new LoginFailedResponseBody(ex.getMessage());
            return Response.status(403).entity(responseBody).build();
        }
    }
}
