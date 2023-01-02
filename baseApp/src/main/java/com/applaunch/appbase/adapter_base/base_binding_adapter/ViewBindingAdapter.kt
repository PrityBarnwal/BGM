package com.applaunch.appbase.adapter_base.base_binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.utils_base.debounce

@BindingAdapter(value = ["android:onClick","debounceTime"],requireAll = false)
fun setSafeClick(view: View, onClickListener: View.OnClickListener,debounceTime:Int) {
    val scope = ViewTreeLifecycleOwner.get(view)!!.lifecycleScope
    val clickDebounce: (view: View) -> Unit = debounce(scope = scope, delayMillis = debounceTime.toLong()) {
        onClickListener.onClick(it)
    }
    view.setOnClickListener(clickDebounce)
}