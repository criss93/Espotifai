/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Espotifai;

/**
 *
 * @author Cristian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Initializing HTTP Server.");
        NettyService.startWebServer(3001);
    }
}
