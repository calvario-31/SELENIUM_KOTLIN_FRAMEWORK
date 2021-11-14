package dummyfails

import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.TmsLink
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobjects.credentials.MainPage
import utilities.Base

class DummyFailTest : Base() {
    private lateinit var mainPage: MainPage

    @BeforeMethod(alwaysRun = true, description = "true")
    fun setUp() {
        setup()
        initPages()
    }

    @Test(groups = ["failed"])
    @Description("Test to verify the screenshot")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("2QtPrEKU")
    fun dummyFailTest() {
        mainPage.goToIndex()
        mainPage.waitPageToLoad()
        Assert.assertTrue(mainPage.errorMessageIsDisplayed(), "Error message never appeared")
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    fun tearDown() {
        teardown()
    }

    override fun initPages() {
        mainPage = MainPage(driver)
    }
}