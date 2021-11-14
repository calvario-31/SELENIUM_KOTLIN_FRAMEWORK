package menuburger

import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.TmsLink
import models.CredentialsModel
import org.testng.Assert
import org.testng.annotations.*
import pageobjects.credentials.MainPage
import pageobjects.shopping.ShoppingPage
import pageobjects.topmenu.TopMenuPage
import utilities.Base
import utilities.datareader.DataReader

class LogoutTest : Base() {
    private lateinit var mainPage: MainPage
    private lateinit var shoppingPage: ShoppingPage
    private lateinit var topMenuPage: TopMenuPage

    @BeforeMethod(alwaysRun = true, description = "setup")
    fun setUp() {
        setup()
        initPages()
    }

    @Test(dataProvider = "test data", groups = ["regression", "smoke"])
    @Description("Test to verify the logout functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Parameters("credentials")
    @TmsLink("ksBWkBLx")
    fun logoutTest(credentials: CredentialsModel) {
        mainPage.goToIndex()
        mainPage.waitPageToLoad()
        mainPage.fillForm(credentials.username, credentials.password)

        Assert.assertTrue(
            shoppingPage.titleIsDisplayed(),
            "title was not displayed"
        )

        topMenuPage.logout()

        Assert.assertTrue(
            mainPage.botImageIsDisplayed(),
            "Bot image is not displayed"
        )
    }


    @AfterMethod(alwaysRun = true, description = "teardown")
    fun tearDown() {
        teardown()
    }

    @DataProvider(name = "test data")
    fun methodSource(): MutableIterator<Array<Any>> {
        val dataReader = DataReader()
        val testData: ArrayList<Array<Any>> = arrayListOf(
            arrayOf(
                dataReader.getValidCredentials()
            )
        )

        return testData.iterator()
    }

    override fun initPages() {
        mainPage = MainPage(driver)
        shoppingPage = ShoppingPage(driver)
        topMenuPage = TopMenuPage(driver)
    }
}