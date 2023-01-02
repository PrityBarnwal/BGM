package com.applaunch.appbase.utils_base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Activity.navigateTo(bundle: Bundle?, className: String) {
    val i = Intent()
    i.setClassName(this, className)
    if (bundle != null)
        i.putExtras(bundle)
    startActivity(i)
}

fun Activity.navigateToWithResult(bundle: Bundle?, className: String): Intent {
    val i = Intent()
    i.setClassName(this, className)

    if (bundle != null)
        i.putExtras(bundle)
    return i
}

fun Activity.navigateNew(bundle: Bundle?, className: String) {
    val i = Intent()
    i.setClassName(this, className)

    if (bundle != null)
        i.putExtras(bundle)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(i)
    finishAffinity()
}

fun Activity.navigateNewClass(cls: Class<*>) {
    val i = Intent()
    i.setClass(this, cls)
    startActivity(i)
}

//################## Fragment

fun Fragment.navigateTo(bundle: Bundle?, className: String) {
    val i = Intent()
    i.setClassName(requireContext(), className)
    if (bundle != null)
        i.putExtras(bundle)
    startActivity(i)
}

fun Fragment.navigateToWithResult(bundle: Bundle?, className: String): Intent {
    val i = Intent()
    i.setClassName(requireContext(), className)

    if (bundle != null)
        i.putExtras(bundle)
    return i
}

fun Fragment.navigateNew(className: String, bundle: Bundle?) {
    val i = Intent()
    i.setClassName(requireContext(), className)

    if (bundle != null)
        i.putExtras(bundle)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(i)
    requireActivity().finishAffinity()
}

fun Fragment.navigateNewClass(cls: Class<*>) {
    val i = Intent()
    i.setClass(requireContext(), cls)
    startActivity(i)
}


fun NavController.navigateTo(id: Int, bundle: Bundle? = null) {
    if (bundle != null)
        this.navigate(id, bundle)
    else
        this.navigate(id)
}

fun NavController.navigateNew(
    nextDestinationId: Int,
    bundle: Bundle? = null
) {

    val navOptions = NavOptions.Builder()
        .setPopUpTo(this.currentDestination!!.id, true)
        .build()
    this.navigate(nextDestinationId, bundle, navOptions)


}


fun Fragment.navigateTo(id: Int, bundle: Bundle? = null) {
    try {
        findNavController().apply {
            if (bundle != null) navigate(id, bundle)
            else navigate(id)
        }

    } catch (e: Exception) {
        Print.log(e.printStackTrace().toString())
    }

}

fun Fragment.navigateNew(
    nextDestinationId: Int,
    bundle: Bundle? = null
) {
    try {
        findNavController().apply {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(currentDestination!!.id, true)
                .build()
            navigate(nextDestinationId, bundle, navOptions)
        }


    } catch (e: Exception) {
        Print.log(e.printStackTrace().toString())
    }

}

fun Fragment.navigateFresh(id: Int, bundle: Bundle? = null) {
    try {
        findNavController().apply {
            if (bundle != null)
                navigate(id, bundle)
            else
                navigate(id)
            backQueue.clear()
        }


    } catch (e: Exception) {
        Print.log(e.printStackTrace().toString())
    }

}


