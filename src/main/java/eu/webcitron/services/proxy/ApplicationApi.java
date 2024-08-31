package eu.webcitron.services.proxy;

public class ApplicationApi {

    private final String host;
    private Boolean healthy;

    public ApplicationApi(String host)
    {
        this.host = host;
        this.healthy = true;
    }

    public String host() {
        return host;
    }

    public Boolean healthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }
}
