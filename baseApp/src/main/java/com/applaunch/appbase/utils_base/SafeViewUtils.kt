package com.applaunch.appbase.utils_base

import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    delayMillis: Long = 300L,
    scope: CoroutineScope,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

fun View.onClick(debounceTime:Int=300,onClickListener: View.OnClickListener){
    val scope = ViewTreeLifecycleOwner.get(this)!!.lifecycleScope
    val clickDebounce: (view: View) -> Unit = debounce(scope = scope, delayMillis = debounceTime.toLong()) {
        onClickListener.onClick(it)
    }
    this.setOnClickListener(clickDebounce)
}

