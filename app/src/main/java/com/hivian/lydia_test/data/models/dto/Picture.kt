package com.hivian.lydia_test.data.models.dto

data class Picture(

    val large: String,

    val medium: String,

    val thumbnail: String

) {

    companion object {
        val EMPTY = Picture(large = "", medium = "", thumbnail = "")
    }

}