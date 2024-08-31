package eu.webcitron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.webcitron.services.proxy.NoApiAvailableException;
import eu.webcitron.services.proxy.RoutingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/proxy")
public class ProxyResource {

    @Inject
    RoutingService proxyService;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(ProxyResource.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response proxy(String payload) {
        if (!isValidJson(payload)) {
            return Response.status(422).entity(new ErrorResponseDTO("Invalid JSON given")).build();
        }

        try {
            String response = proxyService.fetchApiResponse(payload);
            return Response.ok(response).build();
        } catch (NoApiAvailableException e) {
            logger.severe(e.getMessage());
            return Response
                    .status(Response.Status.SERVICE_UNAVAILABLE)
                    .header("Retry-After", e.getRetryAfter())
                    .build();
        }
    }

    /**
     * TODO its failing on primitive json types (such as "test")
     */
    private boolean isValidJson(String input)
    {
        try {
            objectMapper.readTree(input);
        } catch (JsonProcessingException e) {
            return false;
        }

        return true;
    }
}
