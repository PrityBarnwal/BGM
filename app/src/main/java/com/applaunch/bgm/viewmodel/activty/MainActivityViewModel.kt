package com.applaunch.bgm.viewmodel.activty

import android.os.Bundle
import androidx.navigation.NavController
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.R
import com.applaunch.bgm.state.activity.MainActivityState
import com.google.android.material.bottomnavigation.BottomNavigationView
import doNothing

class MainActivityViewModel : BaseViewModel<MainActivityState>() {

    private var mainActivityState: MainActivityState = MainActivityState.Init
        set(value) {
            field = value
            publishState(mainActivityState)
        }

    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }

    fun initBottomNav(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            mainActivityState = MainActivityState.BottomNav(
                when (destination.id) {
                    R.id.navigation_home,
                    R.id.navigation_mediathek,
                    R.id.navigation_challenges,
                    R.id.navigation_favorites,
                    R.id.navigation_setting,
                    R.id.favoriteListFragment
                    -> true
                    else -> false
                }
            )
        }
    }

    fun bottomNavItemClick(bottomNav:BottomNavigationView){
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    mainActivityState = MainActivityState.BottomNavFragment(R.id.navigation_home)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_mediathek -> {
                    mainActivityState = MainActivityState.BottomNavFragment(R.id.navigation_mediathek)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_challenges -> {
                    mainActivityState = MainActivityState.BottomNavFragment(R.id.navigation_challenges)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    mainActivityState = MainActivityState.BottomNavFragment(R.id.navigation_favorites)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_setting -> {
                    mainActivityState = MainActivityState.BottomNavFragment(R.id.navigation_setting)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
        bottomNav.setOnItemReselectedListener { doNothing() }
    }
}