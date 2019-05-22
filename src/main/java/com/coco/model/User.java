/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coco.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cristian
 */
@Entity
@Table (name="users")
@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query="SELECT u FROM User u WHERE u.email = :email AND u.password = :password"),
})
public class User extends AbstractEntity {

    @Column (name = "email")
    public String email;
    @Column (name = "password")
    public String password;

    public User() {
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
