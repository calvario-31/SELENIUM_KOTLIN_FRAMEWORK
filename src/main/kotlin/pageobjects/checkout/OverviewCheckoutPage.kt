package pageobjects.checkout

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class OverviewCheckoutPage(driver: WebDriver) : Page(driver) {
    private val priceLabel = By.className("summary_subtotal_label")
    private val finishButton = By.id("finish")
    private val title = By.className("title")

    @Step("Getting the total price from the UI")
    fun getTotalPrice(): Double {
        Log.info("Getting the total price from the UI")
        val text = find(priceLabel).text
        Log.info("Total price: " + text.substring(13))
        return text.substring(13).toDouble()
    }

    @Step("Clicking on finish checkout")
    fun finishCheckout() {
        Log.info("Clicking on finish checkout")
        find(finishButton).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(title)
    }
}