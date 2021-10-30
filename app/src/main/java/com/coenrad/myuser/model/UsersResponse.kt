package com.coenrad.myuser.model

data class UsersResponse(
    val users: List<Users> = mutableListOf()
)
