package com.qa.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
/**
 * Provides Spring configuration for the UI test automation framework.
 * Manages shared test components and dependencies, such as WebDriver
 * configuration, page objects, test utilities, and application properties.
 * Enables dependency injection to reduce manual object creation and promote
 * reusable, maintainable, and consistent test setup across UI test classes.
 */
@SpringBootApplication(scanBasePackages = "com.qa")
@ConfigurationPropertiesScan("com.qa.config")
public class SpringTestConfig {
}
