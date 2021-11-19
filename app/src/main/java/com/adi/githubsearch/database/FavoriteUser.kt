package com.adi.githubsearch.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_user")
@Parcelize
data class FavoriteUser(
    @ColumnInfo(name = "login")
    var login: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String?,

    @ColumnInfo(name = "html_url")
    var html_url: String?,
) : Parcelable
