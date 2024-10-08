package eu.webcitron.services.proxy;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.*;

@ApplicationScoped
public class RoutingService {

    @Inject
    ApiManager apiManager;

    @Inject
    RestClient restClient;

    @Inject
    @ConfigProperty(name = "app.api_hosts_comma_separated")
    Optional<String> apiHostsCommaSeparated;

    @PostConstruct
    void initialize() {
        if (apiHostsCommaSeparated.isEmpty()) {
            throw new NoApiAvailableException("No API available.");
        }

        String[] hostnames = apiHostsCommaSeparated.get().split(",");
        Arrays.stream(hostnames).forEach(hostname -> apiManager.addApi(new ApplicationApi(hostname)));
    }

    public String fetchApiResponse(String payload) throws NoApiAvailableException {
        int apisCount = apiManager.getApisCount();

        for (int i = 0; i < apisCount; i++) {
            ApplicationApi api = apiManager.getHealthyApi();
            try {
                return restClient.post( api.host() + "/mirror", payload).toString();
            } catch (Exception e) {
                api.setHealthy(false);
            }
        }

        throw new NoApiAvailableException("No API available.");
    }
}
