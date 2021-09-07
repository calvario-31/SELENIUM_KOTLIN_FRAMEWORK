package utilities

import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class DriverManager {
    private lateinit var driver: WebDriver

    fun buildDriver() : WebDriver{
        driver = if (runOnServer) {
            Log.info("Building remote driver")
            buildRemoteDriver()
        } else {
            Log.info("Building local driver")
            buildLocalDriver()
        }
        Log.info("Maximizing the window")
        driver.manage().window().maximize()
        Log.info("Deleting all the cookies")
        driver.manage().deleteAllCookies()
        return driver
    }

    private fun buildLocalDriver(): WebDriver {
        val driverManagerType = DriverManagerType.valueOf(browser)
        WebDriverManager.getInstance(driverManagerType).setup()
        val driverClass = Class.forName(driverManagerType.browserClass())
        driver = driverClass.newInstance() as WebDriver
        browserVersion = (driver as RemoteWebDriver).capabilities.version
        return driver
    }

    private fun buildRemoteDriver(): WebDriver {
        val browserstackLocal = System.getenv("BROWSERSTACK_LOCAL")
        val buildName = System.getenv("BROWSERSTACK_BUILD_NAME")
        val browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER")
        val username = System.getenv("BROWSERSTACK_USERNAME")
        val accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY")
        val browserStackUrl = "https://$username:$accessKey@hub-cloud.browserstack.com/wd/hub"
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability("browser", browser)
        desiredCapabilities.setCapability("browser_version", browserVersion)
        desiredCapabilities.setCapability("os", os)
        desiredCapabilities.setCapability("os_version", osVersion)
        desiredCapabilities.setCapability("browserstack.local", browserstackLocal)
        desiredCapabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier)
        desiredCapabilities.setCapability("build", buildName)
        desiredCapabilities.setCapability("browserstack.debug", "true") // for enabling visual logs
        desiredCapabilities.setCapability("browserstack.console", "info") // to enable console logs at the info level. You can also use other log levels here
        desiredCapabilities.setCapability("browserstack.networkLogs", "true") // to enable network logs to be logged
        return RemoteWebDriver(URL(browserStackUrl), desiredCapabilities)
    }

    companion object {
        var runOnServer: Boolean = false
        lateinit var browser: String
        lateinit var browserVersion: String
        lateinit var os: String
        lateinit var osVersion: String

        fun assignDriverParameters() {
            runOnServer = System.getenv("JOB_NAME") != null
            if (runOnServer) {
                browser = System.getProperty("browser")
                browserVersion = System.getProperty("browserVersion")
                os = System.getProperty("os")
                osVersion = System.getProperty("osVersion")
            } else {
                var browser = System.getProperty("browser")
                if (browser == null) {
                    Log.info("Setting default local browser to CHROME")
                    browser = "CHROME"
                }
                DriverManager.browser = browser
                os = System.getProperty("os.name")
                osVersion = System.getProperty("os.version")
            }
        }

        @Attachment(value = "Screenshot failure", type = "image/png")
        fun getScreenshot(driver: WebDriver): ByteArray {
            Log.info("Taking screenshot")
            return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
        }
    }
}