package menuburger

import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.TmsLink
import models.CredentialsModel
import org.testng.Assert
import org.testng.annotations.*
import pageobjects.credentials.MainPage
import pageobjects.topmenu.TopMenuPage
import utilities.Base
import utilities.datareader.DataReader

class AboutTest : Base() {
    private lateinit var mainPage: MainPage
    private lateinit var topMenuPage: TopMenuPage

    @BeforeMethod(alwaysRun = true, description = "setup")
    fun setUp() {
        setup()
        initPages()
    }

    @Test(dataProvider = "test data", groups = ["regression", "smoke"])
    @Description("Test to verify the about button redirects to sauce labs")
    @Severity(SeverityLevel.NORMAL)
    @Parameters("credentials", "sauce labs url")
    @TmsLink("moriaEyr")
    fun aboutTest(credentials: CredentialsModel, sauceLabsUrl: String) {
        mainPage.goToIndex()
        mainPage.waitPageToLoad()
        mainPage.fillForm(credentials.username, credentials.password)
        Assert.assertEquals(
            topMenuPage.getHrefFromAbout(), sauceLabsUrl,
            "Href does not equals to $sauceLabsUrl"
        )
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    fun tearDown() {
        teardown()
    }

    @DataProvider(name = "test data")
    fun methodSource(): MutableIterator<Array<Any>> {
        val testData: ArrayList<Array<Any>> = arrayListOf(
            arrayOf(
                DataReader().getValidCredentials(),
                DataReader.sauceLabsUrl
            )
        )

        return testData.iterator()
    }

    override fun initPages() {
        mainPage = MainPage(driver)
        topMenuPage = TopMenuPage(driver)
    }
}