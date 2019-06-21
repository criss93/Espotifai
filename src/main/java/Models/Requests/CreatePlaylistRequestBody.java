/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Requests;

/**
 *
 * @author s_fer
 */
public class CreatePlaylistRequestBody {
    private String name;

    public CreatePlaylistRequestBody(String name) {
        this.name = name;
    }

    public CreatePlaylistRequestBody() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
