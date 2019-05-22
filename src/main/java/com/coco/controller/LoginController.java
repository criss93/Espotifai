/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.controller;

import com.coco.dao.UserDao;
import com.coco.espotifai.JWTService;
import com.coco.model.User;
import java.util.List;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Cristian
 */
public class LoginController {
    
    public String login(String email, String password) throws ValidationException {
        System.out.println("Login...");
        
        UserDao dao = new UserDao();
        List<User> users = dao.findUserByEmailAndPass(email, password);
        if (users.isEmpty()) {
            throw new ValidationException("Wrong credentials");
        } else {
            return JWTService.getJWTTokenForUser(users.get(0));
        }
    }
    
}
