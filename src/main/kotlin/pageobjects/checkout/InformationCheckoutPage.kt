package pageobjects.checkout

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class InformationCheckoutPage(driver: WebDriver) : Page(driver) {
    private val inputFirstname = By.id("first-name")
    private val inputLastname = By.id("last-name")
    private val inputZipcode = By.id("postal-code")
    private val buttonContinue = By.id("continue")
    private val title = By.className("title")

    @Step("Filling the form with {0}, {1}, {2}")
    fun fillForm(firstname: String, lastname: String, zipcode: String) {
        waitPageToLoad()
        Log.info("Filling the username")
        Log.debug("Firstname: $firstname")
        find(inputFirstname).sendKeys(firstname)
        Log.info("Filling the lastname")
        Log.debug("Lastname: $lastname")
        find(inputLastname).sendKeys(lastname)
        Log.info("Filling the zipcode")
        Log.debug("Zipcode: $zipcode")
        find(inputZipcode).sendKeys(zipcode)
        Log.info("Clicking on continue")
        find(buttonContinue).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(title)
    }
}