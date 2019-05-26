/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Cristian
 */
public abstract class AbstractDao {
    
    private static EntityManager instance = null;
    
    public EntityManager getEntityManager() {
        final String puName = "Espotifai_jar_1.0PU";
        instance = Persistence.createEntityManagerFactory(puName).createEntityManager();
        return instance;
    }
}
