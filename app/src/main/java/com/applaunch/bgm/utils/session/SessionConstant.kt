package com.applaunch.bgm.utils.session

import androidx.datastore.preferences.core.stringPreferencesKey

object SessionConstant {
    val ACCESS_TOKEN = stringPreferencesKey("accessToken")
    val REFRESH_TOKEN = stringPreferencesKey("refreshToken")
    val COMPANY_LOGO = stringPreferencesKey("companyLogo")
    val COMPANY_COLOR = stringPreferencesKey("companyColor")
}