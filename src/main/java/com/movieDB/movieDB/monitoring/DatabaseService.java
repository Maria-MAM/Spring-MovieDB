package com.movieDB.movieDB.monitoring;

import lombok.SneakyThrows;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseService implements HealthIndicator {

    private DataSource dataSource;

    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public Health health() {
        Boolean isConnectionClosed = dataSource.getConnection().isClosed();
        Map<String, Object> response = new HashMap<>();
        response.put("Connection is closed", isConnectionClosed);
        if(isConnectionClosed) {
            response.put("DATABASE_SERVICE", "Service is not available!");
            return Health.down().withDetails(response).build();
        } else {
            response.put("DATABASE_SERVICE", "Service is running!");
            return Health.up().withDetails(response).build();
        }
    }
}
