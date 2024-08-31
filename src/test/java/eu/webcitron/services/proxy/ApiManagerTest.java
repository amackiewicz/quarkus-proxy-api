package eu.webcitron.services.proxy;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ApiManagerTest {

    ApiManager apiManager;

    @BeforeEach
    public void beforeEach() {
        apiManager = new ApiManager();
    }

//    @Test
//    public void init_IsSettingInitialApisAsHealthy() {
//        String[] apis = {"http://test.api2", "http://test.api2"};
//        apiManager.init(apis);
//
//        assertEquals(
//            new ArrayList<>(Arrays.asList(apis)),
//            apiManager.healthyApis(),
//            "APIs were initially set as healthy"
//        );
//
//        assertEquals(
//            new ArrayList<>(),
//            apiManager.unhealthyApis(),
//            "Initially list of unhealthy APIs is empty"
//        );
//    }
//
//    @Test
//    public void healthyApis_IsReturningOnlyHealthyApis() {
//        String[] apis = {"http://test.api1", "http://test.api2", "http://test.api3"};
//        apiManager.init(apis);
//        apiManager.setAsHealthy("http://test.api1");
//        apiManager.setAsUnhealthy("http://test.api2");
//        apiManager.setAsHealthy("http://test.api3");
//
//        assertEquals(
//            new ArrayList<>(Arrays.asList("http://test.api1", "http://test.api3")),
//            apiManager.healthyApis(),
//            "Only healthy apis should be returned."
//        );
//    }
//
//    @Test
//    public void unhealthyApis_IsReturningOnlyUnhealthyApis() {
//        String[] apis = {"http://test.api1", "http://test.api2", "http://test.api3"};
//        apiManager.init(apis);
//        apiManager.setAsHealthy("http://test.api1");
//        apiManager.setAsUnhealthy("http://test.api2");
//        apiManager.setAsUnhealthy("http://test.api3");
//
//        assertEquals(
//            new ArrayList<>(Arrays.asList("http://test.api2", "http://test.api3")),
//            apiManager.unhealthyApis(),
//            "Only unhealthy apis should be returned."
//        );
//    }
//
//    @Test
//    public void healthyApi_ThrowsExceptionWhenNoHealthyApiAvailable() {
//        assertThrows(
//            NoApiAvailableException.class,
//            () -> apiManager.healthyApi(),
//            "NoApiAvailableException exception is thrown when there is no healthy API available."
//        );
//    }
//
//    @Test
//    public void healthyApi_ReturnsHealthyApiWhenHealthyApiAvailable() {
//        String[] apis = {"http://test.api"};
//        apiManager.init(apis);
//        apiManager.setAsHealthy(apis[0]);
//
//        assertEquals(apiManager.healthyApi(), "http://test.api");
//    }
//
//    @Test
//    public void healthyApi_ReturnsHealthyApiOnRoundRobinBasis() {
//        String[] apis = {"http://test.api", "http://test.api2", "http://test.api3"};
//        apiManager.init(apis);
//
//        assertEquals(apiManager.healthyApi(), "http://test.api");
//        assertEquals(apiManager.healthyApi(), "http://test.api2");
//        assertEquals(apiManager.healthyApi(), "http://test.api3");
//        assertEquals(apiManager.healthyApi(), "http://test.api");
//        assertEquals(apiManager.healthyApi(), "http://test.api2");
//        assertEquals(apiManager.healthyApi(), "http://test.api3");
//    }
//
//    @Test
//    public void numberOfHealthyApis() {
//        String[] apis = {"http://test.api", "http://test.api2"};
//        apiManager.init(apis);
//        apiManager.setAsUnhealthy(apis[0]);
//        apiManager.setAsHealthy(apis[1]);
//
//        assertEquals(1, apiManager.numberOfHealthyApis());
//    }
//
//    @Test
//    public void setAsHealthy_ShouldMoveApiToHealthyList() {
//        String[] apis = {"http://test.api"};
//        apiManager.init(apis);
//        apiManager.setAsUnhealthy(apis[0]);
//        apiManager.setAsHealthy(apis[0]);
//        assertArrayEquals(new String[]{apis[0]}, apiManager.healthyApis().toArray());
//        assertArrayEquals(new String[0], apiManager.unhealthyApis().toArray());
//    }
//
//    @Test
//    public void setAsHealthy_AlreadyHealthy_ShouldNotDuplicateInHealthyList() {
//        String[] apis = {"http://test.api"};
//        apiManager.init(apis);
//        apiManager.setAsHealthy(apis[0]);
//        int countBefore = apiManager.healthyApis().size();
//        apiManager.setAsHealthy(apis[0]);
//        int countAfter = apiManager.healthyApis().size();
//        assertEquals(countBefore, countAfter);
//    }
//
//    @Test
//    public void setAsUnHealthy_ShouldMoveApiToUnhealthyList() {
//        String[] apis = {"http://test.api"};
//        apiManager.init(apis);
//        apiManager.setAsUnhealthy(apis[0]);
//        assertArrayEquals(new String[]{apis[0]}, apiManager.unhealthyApis().toArray());
//        assertArrayEquals(new String[0], apiManager.healthyApis().toArray());
//    }
//
//    @Test
//    public void setAsUnHealthy_ShouldNotDuplicateInUnhealthyList() {
//        String[] apis = {"http://test.api"};
//        apiManager.init(apis);
//        apiManager.setAsUnhealthy(apis[0]);
//        int countBefore = apiManager.healthyApis().size();
//        apiManager.setAsUnhealthy(apis[0]);
//        int countAfter = apiManager.healthyApis().size();
//        assertEquals(countBefore, countAfter);
//    }
}
