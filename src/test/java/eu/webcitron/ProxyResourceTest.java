package eu.webcitron;

import eu.webcitron.services.proxy.NoApiAvailableException;
import eu.webcitron.services.proxy.RoutingService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.ProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class ProxyResourceTest {

    @InjectMock
    private RoutingService routingServiceMock;

    @Test
    void testProxyEndpointReturns200AndMirroringResponse() {
        String payload = "{\"test\":\"test value\"}";

        Mockito.when(routingServiceMock.fetchApiResponse(payload)).thenReturn(payload);

        given().contentType(ContentType.JSON)
                .body(payload)
                .when().post("/proxy")
                .then()
                .statusCode(200)
                .body(is(payload));
    }

    @Test
    void testProxyEndpointReturns415OnNonJsonRequest() {
        given().when().post("/proxy")
                .then()
                .statusCode(415);
    }

    @Test
    void testProxyEndpointReturns422OnReceiveInvalidJson() {
        given().contentType(ContentType.JSON)
                .body("{\"invalid\":\"json\"")
                .when().post("/proxy")
                .then()
                .statusCode(422);
    }

    @Test
    void testProxyEndpointReturns503OnNoApiAvailableException() {
        Mockito.when(routingServiceMock.fetchApiResponse("")).thenThrow(new NoApiAvailableException(""));

        given().contentType(ContentType.JSON)
                .when().post("/proxy")
                .then()
                .statusCode(503)
                .header("Retry-After", equalTo("5"));
    }

}