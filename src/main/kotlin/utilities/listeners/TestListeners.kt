package utilities.listeners

import org.openqa.selenium.WebDriver
import org.testng.ITestListener
import org.testng.ITestResult
import utilities.Base
import utilities.BrowserStackScripts.writeFailure
import utilities.BrowserStackScripts.writeInit
import utilities.BrowserStackScripts.writeSkipped
import utilities.BrowserStackScripts.writeSuccess
import utilities.DriverManager
import utilities.Log

class TestListeners : ITestListener {
    override fun onTestStart(result: ITestResult) {
        Log.startTest(result.name)
        if (DriverManager.runOnServer) {
            writeInit(getDriverFromResult(result), result.name)
        }
    }

    override fun onTestSuccess(result: ITestResult) {
        Log.endTest("PASSED", result.name)
        if (DriverManager.runOnServer) {
            writeSuccess(getDriverFromResult(result))
        }
    }

    override fun onTestFailure(result: ITestResult) {
        Log.endTest("FAILED", result.name)
        val driver = getDriverFromResult(result)
        DriverManager.getScreenshot(driver)

        if (DriverManager.runOnServer) {
            writeFailure(driver)
        }
    }

    override fun onTestSkipped(result: ITestResult) {
        Log.endTest("SKIPPED", result.name)
        if (DriverManager.runOnServer) {
            writeSkipped(getDriverFromResult(result))
        }
    }

    private fun getDriverFromResult(result: ITestResult): WebDriver {
        val currentClass = result.instance
        return (currentClass as Base).driver
    }
}