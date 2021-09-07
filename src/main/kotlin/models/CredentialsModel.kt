package models

import com.poiji.annotation.ExcelCellName
import com.poiji.annotation.ExcelSheet

@ExcelSheet("credentials")
class CredentialsModel() {
    @ExcelCellName("username")
    lateinit var username: String
    @ExcelCellName("password")
    lateinit var password: String
}