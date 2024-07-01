package com.example.container;
import org.testcontainers.containers.MySQLContainer;

public class CustomMySQLContainer extends MySQLContainer<CustomMySQLContainer> {
    private static final String IMAGE_VERSION = "mysql:8.3.0";
    private static CustomMySQLContainer container;

    private CustomMySQLContainer() {
        super(IMAGE_VERSION);
    }

    public static CustomMySQLContainer getInstance() {
        if (container == null) {
            container = new CustomMySQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
//        System.setProperty("DB_URL", container.getJdbcUrl());
//        System.setProperty("DB_USERNAME", container.getUsername());
//        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        // Do nothing, JVM handles shut down
    }
}

