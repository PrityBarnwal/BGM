package com.applaunch.appbase.listner_base

interface BaseRepoListener {
    fun showLoader()
    fun hideLoader()
    fun showMessage(message: String)
    fun isNetworkConnected(): Boolean
    fun showErrorMessage(message: String)

}