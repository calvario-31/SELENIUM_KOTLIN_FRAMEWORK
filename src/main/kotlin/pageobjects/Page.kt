package pageobjects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class Page(private val driver: WebDriver) {
    companion object {
        const val mainUrl = "https://www.saucedemo.com/"
        const val defaultTimeOut = 5L
    }

    fun goToIndex() {
        driver.get(mainUrl)
    }

    fun find(locator: By): WebElement {
        return driver.findElement(locator)
    }

    fun waitVisibility(locator: By, timeOut: Long = defaultTimeOut): WebElement? {
        val wait = WebDriverWait(driver, timeOut)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    fun elementIsDisplayed(locator: By, timeOut: Long = defaultTimeOut): Boolean {
        return try {
            waitVisibility(locator, timeOut)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    abstract fun waitPageToLoad()
}