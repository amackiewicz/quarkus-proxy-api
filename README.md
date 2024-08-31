# quarkus-proxy-api

## Env variables
1. API_HOSTS_COMMA_SEPARATED - required, comma separated list of application apis, eg `http://api1:8080,http://api2:8080`

## Development
```
API_HOSTS_COMMA_SEPARATED="http://localhost:8081,http://localhost:8082" quarkus dev
```

## TODO
1. Currently proxy is marking application APIs as healthy on init. Then, during api call its handling failign response from api. It would be better to mark all apis as unhealthy at the start and then switch to healthy these which passed healthcheck. Then data in app will be much relevant and more up to date.   
2. Add Unit Tests
3. Make rest client calls asynchronous (?)

# quarkus-proxy-api
