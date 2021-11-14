package shopping

import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.TmsLink
import models.CredentialsModel
import models.ShoppingItemModel
import models.UserDataModel
import org.testng.Assert
import org.testng.annotations.*
import pageobjects.checkout.DescriptionCheckoutPage
import pageobjects.checkout.InformationCheckoutPage
import pageobjects.checkout.OverviewCheckoutPage
import pageobjects.checkout.SuccessShoppingPage
import pageobjects.credentials.MainPage
import pageobjects.shopping.DetailItemPage
import pageobjects.shopping.ShoppingPage
import pageobjects.topmenu.TopMenuPage
import utilities.Base
import utilities.datareader.DataReader

class ShoppingTest : Base() {
    private lateinit var mainPage: MainPage
    private lateinit var shoppingPage: ShoppingPage
    private lateinit var detailItemPage: DetailItemPage
    private lateinit var descriptionCheckoutPage: DescriptionCheckoutPage
    private lateinit var informationCheckoutPage: InformationCheckoutPage
    private lateinit var overviewCheckoutPage: OverviewCheckoutPage
    private lateinit var successShoppingPage: SuccessShoppingPage
    private lateinit var topMenuPage: TopMenuPage

    @BeforeMethod(alwaysRun = true, description = "setup")
    fun setUp() {
        setup()
        initPages()
    }

    @Test(dataProvider = "test data", groups = ["regression"])
    @Description("Test to the end to end shopping functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Parameters("credentials", "shopping list", "user data")
    @TmsLink("2E5cHwYs")
    fun shoppingTest(credentials: CredentialsModel, itemList: List<ShoppingItemModel>, userData: UserDataModel) {
        mainPage.goToIndex()
        mainPage.waitPageToLoad()
        mainPage.fillForm(credentials.username, credentials.password)
        shoppingPage.waitPageToLoad()

        var sum = 0.0
        var currentPrice: Double
        for (item in itemList) {
            shoppingPage.goToDetail(item.itemName)
            detailItemPage.waitPageToLoad()
            currentPrice = detailItemPage.addToCart(item.itemId)

            Assert.assertEquals(
                currentPrice,
                item.price.toDouble(),
                "Price were not equals"
            )

            sum += currentPrice
        }
        topMenuPage.waitPageToLoad()

        Assert.assertEquals(
            topMenuPage.getItemCount(),
            itemList.size,
            "Item count were not equal"
        )

        topMenuPage.goToCheckout()

        descriptionCheckoutPage.waitPageToLoad()
        descriptionCheckoutPage.continueCheckout()

        informationCheckoutPage.waitPageToLoad()
        informationCheckoutPage.fillForm(userData.firstname, userData.lastname, userData.zipcode)

        overviewCheckoutPage.waitPageToLoad()
        Assert.assertEquals(
            overviewCheckoutPage.getTotalPrice(),
            sum,
            "Sum were not equal"
        )

        overviewCheckoutPage.finishCheckout()

        successShoppingPage.waitPageToLoad()
        Assert.assertTrue(
            successShoppingPage.titleIsDisplayed(),
            "Success title was not displayed"
        )

        successShoppingPage.backToHome()

        shoppingPage.waitPageToLoad()
        Assert.assertTrue(
            shoppingPage.titleIsDisplayed(),
            "Shopping title was not displayed"
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
                dataReader.getValidCredentials(),
                dataReader.getItemList(),
                UserDataModel()
            )
        )

        return testData.iterator()
    }

    override fun initPages() {
        mainPage = MainPage(driver)
        shoppingPage = ShoppingPage(driver)
        detailItemPage = DetailItemPage(driver)
        descriptionCheckoutPage = DescriptionCheckoutPage(driver)
        informationCheckoutPage = InformationCheckoutPage(driver)
        overviewCheckoutPage = OverviewCheckoutPage(driver)
        successShoppingPage = SuccessShoppingPage(driver)
        topMenuPage = TopMenuPage(driver)
    }
}