package utilities.datareader

import com.poiji.bind.Poiji
import models.CredentialsModel
import models.ShoppingItemModel
import java.io.File

class DataReader {
    companion object {
        const val excelPath = "src/test/resources/data/testData.xlsx";
        const val sauceLabsUrl = "https://saucelabs.com/"
    }

    fun getLockedOutCredentials(): CredentialsModel {
        return Poiji.fromExcel(File(excelPath), CredentialsModel::class.java)[1]
    }

    fun getValidCredentials(): CredentialsModel {
        return Poiji.fromExcel(File(excelPath), CredentialsModel::class.java)[0]
    }

    fun getItemList(): List<ShoppingItemModel> {
        return Poiji.fromExcel(File(excelPath), ShoppingItemModel::class.java)
    }
}