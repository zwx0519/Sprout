package com.example.kotlinbase.bean.login

class RegisterBean(
    val token: String?,
    val code: Int,
    val userInfo: UserInfo?
)

data class UserInfo(
    val avatar: String,
    val birthday: Int,
    val gender: Int,
    val nickname: String?,
    val uid: String,
    val username: String,
    val age: String?,
    val sex: String?
)