package pageobjects.topmenu

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class TopMenuPage(driver: WebDriver) : Page(driver) {
    private val burgerMenuButton = By.id("react-burger-menu-btn")
    private val logoutButton = By.id("logout_sidebar_link")
    private val aboutButton = By.id("about_sidebar_link")
    private val itemCountLabel = By.className("shopping_cart_badge")
    private val checkoutButton = By.id("shopping_cart_container")

    @Step("Getting the href from about button and verifying is enabled")
    fun getHrefFromAbout(): String? {
        openBurgerMenu()
        val aboutElement = find(aboutButton)
        Log.info("Verifying the button is enabled")
        return if (aboutElement.isEnabled) {
            Log.info("The button is enabled, getting the href")
            aboutElement.getAttribute("href")
        } else {
            Log.error("The button is not enabled")
            null
        }
    }

    @Step("Clicking on logout")
    fun logout() {
        openBurgerMenu()
        Log.info("Clicking on the button logout")
        waitVisibility(logoutButton).click()
    }

    @Step("Getting the item count from the UI")
    fun getItemCount(): Int {
        Log.info("Getting item count text")
        val text = find(itemCountLabel).text
        Log.debug("Item count test: $text")
        return text.toInt()
    }

    @Step("Clicking on checkout")
    fun goToCheckout() {
        Log.info("Clicking on the checkout button")
        find(checkoutButton).click()
    }

    private fun openBurgerMenu() {
        Log.info("Clicking on the menu burger")
        find(burgerMenuButton).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(burgerMenuButton)
    }
}