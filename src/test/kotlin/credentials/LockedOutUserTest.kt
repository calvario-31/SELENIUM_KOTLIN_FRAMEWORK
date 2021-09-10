package credentials

import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.TmsLink
import models.CredentialsModel
import org.testng.Assert
import org.testng.annotations.*
import pageobjects.credentials.MainPage
import utilities.Base
import utilities.datareader.DataReader

class LockedOutUserTest : Base() {
    private lateinit var mainPage: MainPage

    @BeforeMethod(alwaysRun = true, description = "setup")
    fun setUp() {
        setup()
        initPages()
    }

    @Test(dataProvider = "dp locked out user", groups = ["regression", "smoke"])
    @Description("Test to verify the error message when bad credentials are used")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("2QtPrEKU")
    @Parameters("credentials", "error message")
    fun lockedOutTest(credentials: CredentialsModel, errorMessage: String) {
        mainPage.goToIndex()
        mainPage.fillForm(credentials.username, credentials.password)
        Assert.assertTrue(
            mainPage.errorMessageIsDisplayed(),
            "Error message never appeared"
        )
        Assert.assertEquals(
            mainPage.getErrorMessage(), errorMessage,
            "Error message text was not the expected"
        )
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    fun tearDown() {
        teardown()
    }

    @DataProvider(name = "dp locked out user")
    fun methodSource(): MutableIterator<Array<Any>> {
        val testData: ArrayList<Array<Any>> = arrayListOf(
            arrayOf(
                DataReader().getLockedOutCredentials(),
                "Epic sadface: Sorry, this user has been locked out."
            )
        )

        return testData.iterator()
    }

    override fun initPages() {
        mainPage = MainPage(driver)
    }
}