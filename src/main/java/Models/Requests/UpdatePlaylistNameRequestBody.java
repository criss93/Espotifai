/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Requests;

/**
 *
 * @author Cristian
 */
public class UpdatePlaylistNameRequestBody {
    private String name;

    public UpdatePlaylistNameRequestBody() {
    }

    public UpdatePlaylistNameRequestBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}
