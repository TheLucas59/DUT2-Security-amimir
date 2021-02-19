package fr.ulille.iut.amimir;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static final String BASE_URI = "http://localhost:7070/api/v1/";
    private static final LogManager logManager = LogManager.getLogManager();

    static {
        try {
            logManager.readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public static HttpServer startServer() {
        ResourceConfig rc = new ResourceConfig().packages("fr.ulille.iut.amimir");
        String logging = System.getenv("LOG_MESSAGES");
        if ( logging != null && logging.equalsIgnoreCase("true") ) {
            rc.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
                    LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
        }
        String debug = System.getenv("DEBUG_INTERNAL");
        if ( debug != null && debug.equalsIgnoreCase("true") ) {
            rc.register(DebugMapper.class);
        }
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.printf(String.format("Jersey app started with WADL available at " + "%sapplication.wadl\nexport LOG_MESSAGES=true pour voir les requêtes et réponses\nexport DEBUG_INTERNAL=true pour voir les erreurs 500\nHit enter to stop it...%n", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

