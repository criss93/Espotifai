/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Responses;

/**
 *
 * @author s_fer
 */
public class CreatePlaylistSuccessResponseBody {
    private String message;

    public CreatePlaylistSuccessResponseBody() {
        this.message = "The playlist was created successfully.";
    }

    public CreatePlaylistSuccessResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
