/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Espotifai;

import WebServices.HealthcheckWebService;
import WebServices.LoginWebService;
import WebServices.PlaylistWebService;
import java.util.ArrayList;
import java.util.List;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

/**
 *
 * @author Cristian
 */
public class NettyService {
    
    public static void startWebServer(int port) {
            ResteasyDeployment deployment = new ResteasyDeployment();

            List<String> classes = new ArrayList<>();
            classes.add(HealthcheckWebService.class.getName());
            classes.add(LoginWebService.class.getName());
            classes.add(PlaylistWebService.class.getName());

            deployment.setResourceClasses(classes);

            final NettyJaxrsServer server = new NettyJaxrsServer();
            server.setExecutorThreadCount(16);
            server.setDeployment(deployment);

            server.setPort(port);
            server.start();
            System.out.println("Web Server listening on port: " + port);
            System.out.println("Attached web services are: ");
            classes.forEach(x -> System.out.println("- " + x));
    }

}
