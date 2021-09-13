package pageobjects.shopping

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class DetailItemPage(driver: WebDriver) : Page(driver) {
    private val itemPrice = By.className("inventory_details_price")
    private val backToProductsButton = By.id("back-to-products")

    @Step("Adding item to cart with id {idButton}")
    fun addToCart(idButton: String): Double {
        waitPageToLoad()
        Log.info("Getting the price text from UI")
        val text = find(itemPrice).text
        val price = text.substring(1).toDouble()
        Log.debug("Price: $price")
        val buttonAddToCart = By.id(idButton)
        Log.info("Clicking on add to cart")
        find(buttonAddToCart).click()
        Log.info("Clicking on back to products")
        find(backToProductsButton).click()
        return price
    }

    override fun waitPageToLoad() {
        waitVisibility(backToProductsButton)
    }
}