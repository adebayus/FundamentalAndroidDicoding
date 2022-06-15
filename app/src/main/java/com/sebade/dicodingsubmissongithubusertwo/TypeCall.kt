package com.sebade.dicodingsubmissongithubusertwo

enum class Type {
    FOLOWER,
    FOLOWING,
}

data class TypeCall(
    val type : Type,
    val url : String
)
