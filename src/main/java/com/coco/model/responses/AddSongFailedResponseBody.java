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
public class AddSongFailedResponseBody {
    private String message;

    public AddSongFailedResponseBody() {
    }

    public AddSongFailedResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
