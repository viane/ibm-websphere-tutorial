
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsumingRestTest {

    private static String port;
    private static String targetUrl;

    private Client client;
    private Response response;

    @BeforeClass
    public static void oneTimeSetup() {
//        port = System.getProperty("liberty.test.port");
//        targetUrl = "http://localhost:" + port + "/artists/total/";
        
        targetUrl = "http://localhost:9080/artists/total/";
    }

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
    }

    @After
    public void teardown() {
        client.close();
    }

    private void assertResponse(String url, Response response) {
        assertEquals("Incorrect response code from " + url, 200, response.getStatus());
    }
    
    // test jsonp for total artists count
    @Test
    public void testArtistCount() {
        response = client.target(targetUrl).request().get();
        this.assertResponse(targetUrl, response);

        int expected = 3;
        int actual = response.readEntity(int.class);
        assertEquals("Expected number of artists does not match", expected, actual);

        response.close();
    }
    
    // test with jackson for each artist's albums count
    @Test
    public void testAlbumCount() {
        String[] artists = {"dj", "bar", "foo"};
        for (int i = 0; i < artists.length; i++) {
            response = client.target(targetUrl + artists[i]).request().get();
            this.assertResponse(targetUrl + artists[i], response);

            int expected = i;
            int actual = response.readEntity(int.class);
            assertEquals("Album count for " + artists[i] + " does not match", expected, actual);

            response.close();
        }
    }
    
    // test for unknown artist
    @Test
    public void testAlbumCountForUnknownArtist() {
        response = client.target(targetUrl + "unknown-artist").request().get();

        int expected = -1;
        int actual = response.readEntity(int.class);
        assertEquals("Unknown artist must have -1 albums", expected, actual);

        response.close();
    }
}