/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.model.responses;

/**
 *
 * @author Cristian
 */
public class AddSongSuccessResponseBody {
    private String message;

    public AddSongSuccessResponseBody() {
        this.message = "Canción agregada exitosamente.";
    }

    public AddSongSuccessResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
