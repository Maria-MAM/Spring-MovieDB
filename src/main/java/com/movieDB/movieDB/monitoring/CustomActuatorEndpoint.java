package com.movieDB.movieDB.monitoring;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "customEndpointGreeting")
@Component
public class CustomActuatorEndpoint {

    @ReadOperation
    public Map<String, String> customEndpoint(@Selector String name) {
        Map<String, String>  map = new HashMap<>();
        map.put("Hello", name);
        return map;
    }

}
