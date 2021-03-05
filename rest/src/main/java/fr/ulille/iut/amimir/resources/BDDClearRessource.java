package fr.ulille.iut.amimir.resources;

import java.sql.SQLException;

import fr.ulille.iut.amimir.BDDFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 * BDDClearRessource
 */
@Path("clearDatabase")
public class BDDClearRessource {

    @GET
    public void clearDatabase()  throws SQLException {
        BDDFactory.dropTables();
    }
}
