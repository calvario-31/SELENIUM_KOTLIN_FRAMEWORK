package utilities

import org.openqa.selenium.WebDriver
import org.testng.annotations.Listeners
import utilities.listeners.SuiteListeners
import utilities.listeners.TestListeners

@Listeners(SuiteListeners::class, TestListeners::class)
abstract class Base {
    lateinit var driver : WebDriver

    fun setup() {
        driver = DriverManager().buildDriver()
    }

    fun teardown() {
        driver.quit()
    }

    abstract fun initPages()
}