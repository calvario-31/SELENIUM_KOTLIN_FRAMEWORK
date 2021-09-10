package models

import com.poiji.annotation.ExcelCellName
import com.poiji.annotation.ExcelSheet

@ExcelSheet("itemData")
class ShoppingItemModel {
    @ExcelCellName("itemName")
    lateinit var itemName: String

    @ExcelCellName("itemId")
    lateinit var itemId: String

    @ExcelCellName("price")
    lateinit var price: String
}