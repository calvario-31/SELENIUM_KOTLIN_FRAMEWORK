package pageobjects.credentials

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class MainPage(driver: WebDriver) : Page(driver) {
    private val usernameInput = By.id("user-name")
    private val passwordInput = By.id("password")
    private val loginButton = By.id("login-button")
    private val errorMessage = By.cssSelector("h3[data-test='error']")
    private val loginContainer = By.id("login_button_container")
    private val imageBot = By.className("bot_column")

    @Step("Filling the form with username: {0} and password: {1}")
    fun fillForm(username: String, password: String) {
        Log.info("Filling username: $username")
        find(usernameInput).sendKeys(username)
        Log.info("Filling password: $password")
        find(passwordInput).sendKeys(password)
        Log.info("Clicking on login button")
        find(loginButton).click()
    }

    @Step("Verifying error message is displayed")
    fun errorMessageIsDisplayed(): Boolean {
        Log.info("Verifying error message is displayed")
        return elementIsDisplayed(errorMessage)
    }

    @Step("Getting error message")
    fun getErrorMessage(): String {
        Log.info("Getting the error message as text")
        return find(errorMessage).text
    }

    @Step("Verify main page is displayed")
    fun botImageIsDisplayed(): Boolean {
        return elementIsDisplayed(imageBot)
    }

    override fun waitPageToLoad() {
        waitVisibility(loginContainer)
    }
}