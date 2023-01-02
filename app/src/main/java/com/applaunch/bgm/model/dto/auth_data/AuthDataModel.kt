package com.applaunch.bgm.model.dto.auth_data

data class AuthDataModel(
    val accessToken:String,
    val refreshToken:String,
    val userRole:String,
    val userName:String,
    val departmantName:String,
    val companyName:String,
    val companyLogo:String,
    val companyColorCode:String
)