package utilities

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object BrowserStackScripts {
    fun writeInit(driver: WebDriver, testName: String) {
        Log.info("Writing the test name for browserstack")
        val jse = driver as JavascriptExecutor
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"$testName \" }}")
    }

    fun writeSuccess(driver: WebDriver) {
        Log.info("Writing test success for browserstack")
        val jse = driver as JavascriptExecutor
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test OK\"}}")
    }

    fun writeFailure(driver: WebDriver) {
        Log.info("Writing test failure for browserstack")
        val jse = driver as JavascriptExecutor
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"\"}}")
    }

    fun writeSkipped(driver: WebDriver) {
        Log.info("Writing test skipped for browserstack")
        val jse = driver as JavascriptExecutor
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"skipped\", \"reason\": \"\"}}")
    }
}