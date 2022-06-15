package com.sebade.dicodingsubmissongithubusertwo.model

import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem

data class Follow(
    val follower: List<UserItem>?,
    val following: List<UserItem>?,
)
