package com.adi.githubsearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.adi.githubsearch.database.FavoriteUser
import com.adi.githubsearch.database.FavoriteUserDao
import com.adi.githubsearch.database.UserRoomDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDB: UserRoomDatabase? = UserRoomDatabase.getDatabase(application)

    init {
        userDao = userDB?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? = userDao?.getFavoriteUser()
}