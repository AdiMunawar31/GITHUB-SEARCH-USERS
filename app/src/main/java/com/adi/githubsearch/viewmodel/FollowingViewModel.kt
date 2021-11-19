package com.adi.githubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adi.githubsearch.models.ModelSearchData
import com.adi.githubsearch.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    private val _listFollowing = MutableLiveData<ArrayList<ModelSearchData>>()
    fun getListFollowing(): LiveData<ArrayList<ModelSearchData>> = _listFollowing

    fun setListFollowing(username: String) {
        ApiClient.apiInstance
            .getFollowingUser(username)
            .enqueue(object : Callback<ArrayList<ModelSearchData>> {
                override fun onResponse(
                    call: Call<ArrayList<ModelSearchData>>,
                    response: Response<ArrayList<ModelSearchData>>
                ) {
                    if (response.isSuccessful) {
                        _listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ModelSearchData>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }
}