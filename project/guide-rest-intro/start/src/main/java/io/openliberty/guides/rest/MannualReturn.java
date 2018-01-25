package io.openliberty.guides.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("Test")
public class MannualReturn {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String getProperties() {
		return "Hello World";
		
	}
}
