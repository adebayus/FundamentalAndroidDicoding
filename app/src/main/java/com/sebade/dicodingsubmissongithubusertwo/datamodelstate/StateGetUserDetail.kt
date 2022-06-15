package com.sebade.dicodingsubmissongithubusertwo.datamodelstate

import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseDetailUserItem

data class StateGetUserDetail(
    var responseBody: ResponseDetailUserItem? = null,
    var errorResponse: Throwable? = null
)
