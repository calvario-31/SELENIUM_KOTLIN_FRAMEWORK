package models

import com.github.javafaker.Faker

class UserDataModel {
    var firstname: String = Faker().name().firstName()
    var lastname: String = Faker().name().lastName()
    var zipcode: String = Faker().address().zipCode()
}