package com.hivian.lydia_test.data.models.dto

data class Street(

    val number: Int = 0,

    val name: String = ""

) {
    companion object {
        val EMPTY = Street(0, "")
    }
}

data class Location(

    val street: Street,

    val city: String,

    val state: String,

    val country: String,

    val postcode: String

) {

    companion object {
        val EMPTY = Location(street = Street.EMPTY, city = "", state = "", country = "", postcode = "")
    }

}