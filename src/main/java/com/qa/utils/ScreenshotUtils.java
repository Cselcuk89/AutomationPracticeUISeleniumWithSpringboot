package com.qa.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides reusable utilities for capturing screenshots during UI test execution.
 * Helps capture browser states when tests fail or at important checkpoints,
 * supporting debugging, failure analysis, and attachment of visual evidence
 * to test reports.
 */
public final class ScreenshotUtils {
    private static final Logger log = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final Path DIR = Paths.get("target", "screenshots");
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private ScreenshotUtils() {}
    public static void captureOnFailure(WebDriver driver,String testName){
        if (!(driver instanceof TakesScreenshot)){
            log.warn("Driver can not take screenshots;skipping capture for {}",testName);
            return;
        }
        byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Attach to Allure (shown inline on the failed step/test).
        Allure.addAttachment(testName + " - failure", "image/png",
                new ByteArrayInputStream(png), "png");

        // Also persist to disk for CI artifact retention.
        try {
            Files.createDirectories(DIR);
            Path file = DIR.resolve(testName + "-" + LocalDateTime.now().format(TS) + ".png");
            Files.write(file, png);
            log.info("Saved failure screenshot: {}", file.toAbsolutePath());
        } catch (IOException e) {
            log.warn("Could not write screenshot to disk: {}", e.getMessage());
        }
    }
}
