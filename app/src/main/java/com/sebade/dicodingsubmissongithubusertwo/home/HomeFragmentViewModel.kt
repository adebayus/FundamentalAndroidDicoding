package com.sebade.dicodingsubmissongithubusertwo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebade.dicodingsubmissongithubusertwo.network.ApiConfig
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseDetailUserItem
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseSearchUser
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem
import retrofit2.Call
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {

    private var _test = MutableLiveData<String>("asdasd")
    var test: LiveData<String> = _test

    private var _listUser = MutableLiveData<MutableList<ResponseDetailUserItem>>()
    var listUser: LiveData<MutableList<ResponseDetailUserItem>> = _listUser

    private var _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    var _userItem = MutableLiveData<List<UserItem>?>()


    init {
        getListUser()
    }


    fun searchUser(username: String) {
        val client = ApiConfig.retrofitService.searchListUser(username)
        client.enqueue(object : retrofit2.Callback<ResponseSearchUser> {
            override fun onResponse(
                call: Call<ResponseSearchUser>,
                response: Response<ResponseSearchUser>
            ) {
                val rBody = response.body()
                if (response.isSuccessful) {
                    val listUser = rBody?.listSearchUser
                    _userItem.value = listUser
                }
            }

            override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {

            }

        })
    }


     fun getListUser() {
        _loading.value = true
        val client = ApiConfig.retrofitService.getListUser()
        client.enqueue(object : retrofit2.Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {

                val responseBody = response.body()!!
                _userItem.value = responseBody
                _loading.postValue(false)
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                _loading.postValue(false)
            }

        })
    }
}