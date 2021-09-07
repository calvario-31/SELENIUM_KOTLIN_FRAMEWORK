package utilities.listeners

import org.testng.ISuite
import org.testng.ISuiteListener
import utilities.DriverManager
import utilities.Log

class SuiteListeners : ISuiteListener {
    override fun onStart(suite: ISuite) {
        Log.info(suite.name)
        DriverManager.assignDriverParameters()
    }

    override fun onFinish(suite: ISuite?) {
        super.onFinish(suite)
    }
}