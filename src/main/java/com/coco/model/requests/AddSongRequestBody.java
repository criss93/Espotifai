/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.model.requests;

/**
 *
 * @author Cristian
 */
public class AddSongRequestBody {
    private int id;

    public AddSongRequestBody() {
    }

    public AddSongRequestBody(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
