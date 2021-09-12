package pageobjects.checkout

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class DescriptionCheckoutPage(driver: WebDriver) : Page(driver) {
    private val checkoutButton = By.id("checkout")
    private val descriptionLabel = By.className("cart_desc_label")

    @Step("Clicking on continue checkout")
    fun continueCheckout() {
        waitPageToLoad()
        Log.info("Clicking on the checkout button")
        find(checkoutButton).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(descriptionLabel)
    }
}
