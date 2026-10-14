package com.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Provides reusable Selenium explicit-wait methods for UI automation.
 * Waits for elements to become visible, clickable, or meet specific
 * conditions before interacting with them, improving test stability,
 * reducing timing-related failures, and avoiding duplicated wait logic.
 */
public final class WaitUtils {
    private final WebDriverWait wait;
    public WaitUtils(WebDriver driver, Duration timeout) {
        this.wait = new WebDriverWait(driver, timeout);
    }
    public WebElement visible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public boolean isVisible(By locator) {
        try {
            return visible(locator).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }
    public boolean textPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    public boolean urlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }



}
