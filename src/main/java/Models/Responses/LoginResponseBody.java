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
public class LoginResponseBody {
    private String token;

    public LoginResponseBody(String token) {
        this.token = token;
    }

    public LoginResponseBody() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
