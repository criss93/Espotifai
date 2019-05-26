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
public class LoginFailedResponseBody {
    private String message;

    public LoginFailedResponseBody() {
    }

    public LoginFailedResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
