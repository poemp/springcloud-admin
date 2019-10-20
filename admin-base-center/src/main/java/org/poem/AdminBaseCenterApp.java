package org.poem;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author poem
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
public class AdminBaseCenterApp {

    private static final Logger logger = LoggerFactory.getLogger(AdminBaseCenterApp.class);

    public static void main(String[] args) {
        logger.info("Start AdminBaseCenterApp Service.");
        SpringApplication.run(AdminBaseCenterApp.class, args);
    }

}
