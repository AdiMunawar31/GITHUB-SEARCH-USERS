package com.adi.githubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adi.githubsearch.helpers.SettingPreferences
import com.adi.githubsearch.models.ModelSearch
import com.adi.githubsearch.models.ModelSearchData
import com.adi.githubsearch.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _listUser = MutableLiveData<ArrayList<ModelSearchData>>()
    fun getSearchUsers(): LiveData<ArrayList<ModelSearchData>> = _listUser

    fun setSearchUsers(query: String) {
        ApiClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<ModelSearch> {
                override fun onResponse(call: Call<ModelSearch>, response: Response<ModelSearch>) {
                    if (response.isSuccessful) {
                        _listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<ModelSearch>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

}