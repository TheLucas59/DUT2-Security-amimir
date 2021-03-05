package fr.ulille.iut.amimir;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("api/v1/")
public class ApiV1 extends ResourceConfig {

    public ApiV1() {
        packages("fr.ulille.iut.amimir");
    }
}
