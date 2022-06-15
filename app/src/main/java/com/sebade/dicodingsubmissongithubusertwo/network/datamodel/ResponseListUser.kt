package com.sebade.dicodingsubmissongithubusertwo.network.datamodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseListUser(

	@field:SerializedName("Response")
	val response: List<UserItem?>? = null
) : Parcelable


