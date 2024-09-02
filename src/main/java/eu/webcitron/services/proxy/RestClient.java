package eu.webcitron.services.proxy;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RestClient {

    private final Client client;

    public RestClient() {
        client = ClientBuilder.newClient();
    }

    public Response head(String url) {
        return client.target(url).request().head();
    }

    public Response post(String url, String payload) {
        return client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(payload, MediaType.APPLICATION_JSON));
    }

    @PreDestroy
    public void closeClient() {
        if (client != null) {
            client.close();
        }
    }
}
