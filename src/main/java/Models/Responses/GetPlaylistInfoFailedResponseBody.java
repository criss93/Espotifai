/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Responses;

/**
 *
 * @author Cristian
 */
public class GetPlaylistInfoFailedResponseBody {
    private String message;
    
    public GetPlaylistInfoFailedResponseBody() {
    }

    public GetPlaylistInfoFailedResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
