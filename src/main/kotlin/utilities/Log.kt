package utilities

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Log {
    var Log: Logger = LogManager.getLogger("[LOG]")

    fun startTest(testName: String) {
        println("")
        Log.info("------------------------------------------------------------------------------------------")
        Log.info("Test: $testName")
        Log.info("------------------------------------------------------------------------------------------")
    }

    fun endTest(status: String, testName: String) {
        Log.info("------------------------------------------------------------------------------------------")
        Log.info("$status $testName")
        Log.info("------------------------------------------------------------------------------------------")
        println("")
    }

    fun info(message: String?) {
        Log.info(message)
    }

    fun warn(message: String?) {
        Log.warn(message)
    }

    fun error(message: String?) {
        Log.error(message)
    }

    fun fatal(message: String?) {
        Log.fatal(message)
    }

    fun debug(message: String?) {
        Log.debug(message)
    }
}