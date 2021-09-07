package pageobjects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class Page (private val driver: WebDriver) {
    private lateinit var wait : WebDriverWait
    private val mainUrl = "https://www.saucedemo.com/"

    fun goToIndex() {
        driver.get(mainUrl)
    }

    fun find(locator : By) : WebElement{
        return driver.findElement(locator)
    }

    fun waitFor(locator : By, timeOut : Long = 5) : WebElement? {
        wait = WebDriverWait(driver, timeOut)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
    }

    fun elementIsDisplayed(locator : By, timeOut: Long = 5) : Boolean {
        return try {
            waitFor(locator)
            true
        } catch (e : Exception) {
            e.printStackTrace()
            false
        }
    }

    abstract fun waitPageToLoad()
}