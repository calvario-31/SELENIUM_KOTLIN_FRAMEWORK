package pageobjects.checkout

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class InformationCheckoutPage(driver: WebDriver) : Page(driver) {
    private val firstnameInput = By.id("first-name")
    private val lastnameInput = By.id("last-name")
    private val zipcodeInput = By.id("postal-code")
    private val continueButton = By.id("continue")
    private val title = By.className("title")

    @Step("Filling the form with {0}, {1}, {2}")
    fun fillForm(firstname: String, lastname: String, zipcode: String) {
        waitPageToLoad()
        Log.info("Filling the username")
        Log.debug("Firstname: $firstname")
        find(firstnameInput).sendKeys(firstname)
        Log.info("Filling the lastname")
        Log.debug("Lastname: $lastname")
        find(lastnameInput).sendKeys(lastname)
        Log.info("Filling the zipcode")
        Log.debug("Zipcode: $zipcode")
        find(zipcodeInput).sendKeys(zipcode)
        Log.info("Clicking on continue")
        find(continueButton).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(title)
    }
}