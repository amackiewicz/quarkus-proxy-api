package eu.webcitron.services.proxy;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@ApplicationScoped
public class ApiManager {

    @Inject
    RestClient restClient;

    private static final int HEALTHCHECK_INTERVAL_SECONDS = 3;
    private ScheduledExecutorService scheduler;
    private static final Logger logger = Logger.getLogger(ApiManager.class.getName());
    private final CopyOnWriteArrayList<ApplicationApi> apis = new CopyOnWriteArrayList<>();
    private final AtomicInteger index = new AtomicInteger(0);

    @PostConstruct
    public void initHealthcheck() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::processUnhealthyApis, 0, HEALTHCHECK_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    public void addApi(ApplicationApi api) {
        apis.add(api);
    }

    public Integer getApisCount() {
        return apis.size();
    }

    /**
     *
     * consider synchronized here
     */
    public ApplicationApi getHealthyApi() {
        ApplicationApi api;
        int maxAttempts = apis.size();

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            api = apis.get(index.getAndIncrement() % apis.size());
            if (api.healthy()) {
                return api;
            }
        }

        throw new NoApiAvailableException("No API available.");
    }

    private void processUnhealthyApis() {
        apis.stream()
            .filter(api -> !api.healthy())
            .forEach(api -> {
                try {
                    Response response = restClient.head(api.host() + "/mirror");
                    if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                        api.setHealthy(true);
                    }
                    logger.info("Healthcheck for " + api.host() + " passed" );
                } catch (Exception e) {
                    logger.info("Healthcheck for " + api.host() + " failed" );
                }
            });
    }

    @PreDestroy
    public void shutdown() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }
}
