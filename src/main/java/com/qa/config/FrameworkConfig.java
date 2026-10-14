package com.qa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

/**
 * Centralizes configuration for the Selenium UI automation framework
 * with Spring Boot integration. Defines and manages shared components,
 * including WebDriver setup, browser options, timeouts, framework properties,
 * and reusable test utilities through Spring dependency injection.
 * Promotes consistent configuration, component reuse, and maintainability
 * across test classes and supports environment-specific settings.
 */
@ConfigurationProperties(prefix = "framework")
@ConstructorBinding
public class FrameworkConfig {
    private final String baseUrl;
    private final Browser browser;
    private final Timeouts timeouts;

    public FrameworkConfig(String baseUrl, Browser browser, Timeouts timeouts) {
        this.baseUrl = (baseUrl == null || baseUrl.trim().isEmpty()) ? "https://www.automationexercise.com" : baseUrl;
        this.browser = (browser == null) ? new Browser("chrome",true,1920,1080) : browser;
        this.timeouts = (timeouts == null) ? new Timeouts(0, 20, 30) : timeouts;

    }
    public String baseUrl(){
        return baseUrl;
    }
    public Browser browser(){
        return browser;
    }
    public Timeouts timeouts() {
        return timeouts;
    }
    /** Browser Settings.*/
    public static class Browser{
        private final String name;
        private final boolean headless;
        private final int windowWidth;
        private final int windowHeight;

        public Browser(String name, boolean headless, int windowWidth, int windowHeight) {
            this.name = (name == null || name.trim().isEmpty()) ? "chrome" : name;
            this.headless = headless;
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
        }
        public String name(){
            return name;
        }
        public boolean headless() {
            return headless;
        }

        public int windowWidth() {
            return windowWidth;
        }

        public int windowHeight() {
            return windowHeight;
        }
    }
    /** Timeout settings (seconds). */
    public static class Timeouts {
        private final int implicitSeconds;
        private final int explicitSeconds;
        private final int pageLoadSeconds;

        public Timeouts(int implicitSeconds, int explicitSeconds, int pageLoadSeconds) {
            this.implicitSeconds = implicitSeconds;
            this.explicitSeconds = explicitSeconds;
            this.pageLoadSeconds = pageLoadSeconds;
        }

        public int implicitSeconds() {
            return implicitSeconds;
        }

        public int explicitSeconds() {
            return explicitSeconds;
        }

        public int pageLoadSeconds() {
            return pageLoadSeconds;
        }

        public Duration explicit() {
            return Duration.ofSeconds(explicitSeconds);
        }

        public Duration pageLoad() {
            return Duration.ofSeconds(pageLoadSeconds);
        }

        public Duration implicitWait() {
            return Duration.ofSeconds(implicitSeconds);
        }
    }

}
