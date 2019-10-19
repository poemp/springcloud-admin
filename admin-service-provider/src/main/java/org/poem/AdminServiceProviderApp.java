package org.poem;

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
public class AdminServiceProviderApp {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceProviderApp.class);

    public static void main(String[] args) {
        logger.info("Start AdminServiceProviderApp Service .");
        SpringApplication.run(AdminServiceProviderApp.class, args);
    }
}
