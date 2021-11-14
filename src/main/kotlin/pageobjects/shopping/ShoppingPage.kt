package pageobjects.shopping

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import pageobjects.Page
import utilities.Log

class ShoppingPage(driver: WebDriver) : Page(driver) {
    private val title = By.className("title")

    @Step("Verifying the title is displayed")
    fun titleIsDisplayed(): Boolean {
        Log.info("Verifying the title is displayed")
        return elementIsDisplayed(title)
    }

    @Step("Going to item details of {0}")
    fun goToDetail(productName: String) {
        val xpathGeneric = "//div[text()='PRODUCT_NAME']"
        val xpathItemName = xpathGeneric.replace("PRODUCT_NAME", productName)
        Log.debug("Xpath of the item name: $xpathItemName")
        val itemName = By.xpath(xpathItemName)
        Log.info("Clicking on the name to go to the item detail")
        find(itemName).click()
    }

    override fun waitPageToLoad() {
        waitVisibility(title)
    }
}