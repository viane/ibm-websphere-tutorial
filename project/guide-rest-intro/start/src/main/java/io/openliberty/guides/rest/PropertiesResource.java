package io.openliberty.guides.rest;

import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonGenerator;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.Json;
import javax.json.JsonNumber;

@Path("properties")
public class PropertiesResource {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProperties() {

//        JsonObjectBuilder builder = Json.createObjectBuilder();
//
//        System.getProperties()
//              .entrySet()
//              .stream()
//              .forEach(entry -> builder.add((String)entry.getKey(),
//                                            (String)entry.getValue()));
//        
//       return builder.build();

    // pretty printing
	Map<String, Object> config = new HashMap<String, Object>();
	config.put(JsonGenerator.PRETTY_PRINTING, true);
    JsonBuilderFactory factory = Json.createBuilderFactory(config);
    JsonObjectBuilder value = factory.createObjectBuilder();
       
       		System.getProperties()
               .entrySet()
               .stream()
               .forEach(entry -> value.add((String)entry.getKey(),
                                             (String)entry.getValue()));
       		return value.build();
	}
}
