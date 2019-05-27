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
public class UpdatePlaylistNameSuccessResponseBody {
    private String message;

    public UpdatePlaylistNameSuccessResponseBody() {
        this.message = "The playlist name was updated successfully.";
    }

    public UpdatePlaylistNameSuccessResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
