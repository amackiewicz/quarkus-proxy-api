package eu.webcitron.services.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

public class ApiManagerTest {

    @Test
    public void testGetHealthyApi_WhenAvailable() {
        ApiManager apiManager = new ApiManager();

        ApplicationApi api1 = mock(ApplicationApi.class);
        when(api1.healthy()).thenReturn(true);
        apiManager.addApi(api1);

        ApplicationApi api2 = mock(ApplicationApi.class);
        when(api2.healthy()).thenReturn(true);
        apiManager.addApi(api2);

        assertEquals(api1, apiManager.getHealthyApi());
        assertEquals(api2, apiManager.getHealthyApi());
        assertEquals(api1, apiManager.getHealthyApi());
        assertEquals(api2, apiManager.getHealthyApi());
    }

    @Test
    public void testGetHealthyApi_WhenTwoAvailable() {
        ApiManager apiManager = new ApiManager();

        ApplicationApi api1 = mock(ApplicationApi.class);
        when(api1.healthy()).thenReturn(true);
        apiManager.addApi(api1);

        ApplicationApi api2 = mock(ApplicationApi.class);
        when(api2.healthy()).thenReturn(false);
        apiManager.addApi(api2);

        ApplicationApi api3 = mock(ApplicationApi.class);
        when(api3.healthy()).thenReturn(true);
        apiManager.addApi(api3);

        assertEquals(api1, apiManager.getHealthyApi());
        assertEquals(api3, apiManager.getHealthyApi());
        assertEquals(api1, apiManager.getHealthyApi());
        assertEquals(api3, apiManager.getHealthyApi());
    }

    @Test
    public void testGetHealthyApi_WhenNoneAvailable() {
        ApiManager apiManager = new ApiManager();
        ApplicationApi api1 = mock(ApplicationApi.class);
        when(api1.healthy()).thenReturn(false);
        ApplicationApi api2 = mock(ApplicationApi.class);
        when(api2.healthy()).thenReturn(false);

        apiManager.addApi(api1);
        apiManager.addApi(api2);

        assertThrows(NoApiAvailableException.class, apiManager::getHealthyApi);
    }

    @Test
    public void testGetHealthyApi_WhanNoApiAdded() {
        ApiManager apiManager = new ApiManager();
        assertThrows(NoApiAvailableException.class, apiManager::getHealthyApi);
    }
}