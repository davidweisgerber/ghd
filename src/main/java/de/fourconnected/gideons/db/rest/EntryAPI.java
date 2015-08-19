package de.fourconnected.gideons.db.rest;

import de.fourconnected.gideons.db.daos.EntryDAO;
import de.fourconnected.gideons.db.entities.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by david on 19.08.15.
 */
@Component
@Path("/entry")
public class EntryAPI {

    @Autowired
    EntryDAO entryDAO;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @GET
    public Entry getById(@PathParam("id") long id) {
        return this.entryDAO.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public long persist(Entry entry) {
        return this.entryDAO.persist(entry).getId();
    }
}
