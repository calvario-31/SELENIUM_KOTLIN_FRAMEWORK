package pageobjects.topmenu

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log.debug
import utilities.Log.error
import utilities.Log.info

class TopMenuPage (driver: WebDriver) : Page(driver) {
    private val menuBurger = By.id("react-burger-menu-btn")
    private val buttonLogout = By.id("logout_sidebar_link")
    private val buttonAbout = By.id("about_sidebar_link")
    private val itemCount = By.className("shopping_cart_badge")
    private val buttonCheckout = By.id("shopping_cart_container")

    @Step("Getting the href from about button and verifying is enabled")
    fun getHrefFromAbout(): String? {
        openBurgerMenu()
        val aboutElement = find(buttonAbout)
        info("Verifying the button is enabled")
        return if (aboutElement.isEnabled) {
            info("The button is enabled, getting the href")
            aboutElement.getAttribute("href")
        } else {
            error("The button is not enabled")
            null
        }
    }

    @Step("Clicking on logout")
    fun logout() {
        openBurgerMenu()
        info("Clicking on the button logout")
        waitFor(buttonLogout)?.click()
    }

    @Step("Getting the item count from the UI")
    fun getItemCount(): Int {
        waitPageToLoad()
        info("Getting item count text")
        val text = find(itemCount).text
        debug("Item count test: $text")
        return text.toInt()
    }

    @Step("Clicking on checkout")
    fun goToCheckout() {
        waitPageToLoad()
        info("Clicking on the checkout button")
        find(buttonCheckout).click()
    }

    private fun openBurgerMenu() {
        waitPageToLoad()
        info("Clicking on the menu burger")
        find(menuBurger).click()
    }
    override fun waitPageToLoad() {
        waitFor(menuBurger)
    }
}