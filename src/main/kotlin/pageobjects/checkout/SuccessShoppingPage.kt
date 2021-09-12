package pageobjects.checkout

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class SuccessShoppingPage(driver: WebDriver) : Page(driver) {
    private val successTitle = By.className("complete-header")
    private val backToHomeButton = By.id("back-to-products")

    @Step("Verifying the title is displayed")
    fun titleIsDisplayed(): Boolean {
        Log.info("Verifying the title is displayed")
        return elementIsDisplayed(successTitle)
    }

    @Step("Clicking on back to home")
    fun backToHome() {
        Log.info("Clicking on back to home")
        find(backToHomeButton).click()
    }

    override fun waitPageToLoad() {

    }
}