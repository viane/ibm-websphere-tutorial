package io.openliberty.guides.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.Json;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;

public class EndpointTest {
	@Test
	public void testGetProperties() {
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        String url = "http://localhost:" + port + "/" + war + "/";

        Client client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);

        WebTarget target = client.target(url + "System/properties");
        Response response = target.request().get();

        assertEquals("Incorrect response code from " + url, 200, response.getStatus());

        JsonObject obj = response.readEntity(JsonObject.class);

        assertEquals("The system property for the local and remote JVM should match",
                     System.getProperty("os.name"),
                     obj.getString("os.name"));
        response.close();
    }
}
