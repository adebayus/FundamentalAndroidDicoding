package com.sebade.dicodingsubmissongithubusertwo.network.datamodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("login")
    val login: String? = null,
    @field:SerializedName("avatar_url")
    val avatar_url: String? = null,
) : Parcelable
