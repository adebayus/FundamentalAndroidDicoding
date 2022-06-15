package com.sebade.dicodingsubmissongithubusertwo.detailuser


import androidx.lifecycle.*
import com.sebade.dicodingsubmissongithubusertwo.Type
import com.sebade.dicodingsubmissongithubusertwo.TypeCall
import com.sebade.dicodingsubmissongithubusertwo.model.Follow
import com.sebade.dicodingsubmissongithubusertwo.network.ApiConfig
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.ResponseDetailUserItem
import com.sebade.dicodingsubmissongithubusertwo.network.datamodel.UserItem
import retrofit2.Call
import retrofit2.Response


class DetailUserViewModel : ViewModel() {

    private var _userDetail = MutableLiveData<ResponseDetailUserItem>()
    var userDetail: LiveData<ResponseDetailUserItem> = _userDetail

    private var _follower = MutableLiveData<List<UserItem>>()
    var follower: LiveData<List<UserItem>> = _follower

    private var _following = MutableLiveData<List<UserItem>>()
    var following: LiveData<List<UserItem>> = _following

    var listFollow = MediatorLiveData<Follow>().apply {
        addSource(_follower){
            mergeFollow()
        }

        addSource(_following) {
            mergeFollow()
        }

    }

    private fun mergeFollow() {
        val followerList = follower.value
        val followingList = following.value

       listFollow.value = Follow(followerList, followingList)

    }


    fun getUserDetail(userItem: UserItem) {
        val url: String = userItem.url!!
        val usernamePath = url.substringAfter("https://api.github.com/users/")
        val client = ApiConfig.retrofitService.getDetail(usernamePath)
        client.enqueue(object : retrofit2.Callback<ResponseDetailUserItem> {
            override fun onResponse(
                call: Call<ResponseDetailUserItem>,
                response: Response<ResponseDetailUserItem>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _userDetail.value = response.body()
                        getFollowerFollowingUser(response.body()!!)

                    }
                }

            }

            override fun onFailure(call: Call<ResponseDetailUserItem>, t: Throwable) {
            }

        })
    }

    private fun getFollowerFollowingUser(detailUserItem: ResponseDetailUserItem) {
        getData(TypeCall(Type.FOLOWER, detailUserItem.followersUrl!!))
        getData(TypeCall(Type.FOLOWING, detailUserItem.followingUrl!!))

    }

    private fun getData(type: TypeCall) {
        var usernamePath = type.url.substringAfter("https://api.github.com/users/").substringBefore("/follow")
        var client = when (type.type) {
            Type.FOLOWER -> ApiConfig.retrofitService.getListUserFollower(usernamePath)
            Type.FOLOWING -> ApiConfig.retrofitService.getListUserFollowing(usernamePath)
            else -> null
        }
        client?.enqueue(object : retrofit2.Callback<List<UserItem>>{
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                if (response.isSuccessful){
                    when(type.type){
                        Type.FOLOWING -> {
                            _following.value = response.body()
                        }
                        Type.FOLOWER -> {
                            _follower.value = response.body()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
            }

        })
    }


}