package com.adi.githubsearch.models

data class ModelUser(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val html_url: String,
    val name: String,
    val bio: String,
    val public_repos: String,
    val location: String,
    val followers: String,
    val following: String
)
