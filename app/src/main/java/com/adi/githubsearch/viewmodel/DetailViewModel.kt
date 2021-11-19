package com.adi.githubsearch.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adi.githubsearch.database.FavoriteUser
import com.adi.githubsearch.database.FavoriteUserDao
import com.adi.githubsearch.database.UserRoomDatabase
import com.adi.githubsearch.models.ModelUser
import com.adi.githubsearch.networking.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDB: UserRoomDatabase? = UserRoomDatabase.getDatabase(application)

    init {
        userDao = userDB?.favoriteUserDao()
    }

    private val _detailUser = MutableLiveData<ModelUser>()
    fun getUserDetail(): LiveData<ModelUser> = _detailUser

    fun setUserDetail(username: String) {
        ApiClient.apiInstance
            .getDetailUser(username)
            .enqueue(object : Callback<ModelUser> {
                override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
                    if (response.isSuccessful) {
                        _detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ModelUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun addToFavorite(username: String?, id: Int, avatar_url: String?, html_url: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(username, id, avatar_url, html_url)
            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}