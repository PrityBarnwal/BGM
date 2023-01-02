package com.applaunch.bgm.adapter.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.applaunch.bgm.R


@BindingAdapter("isLoaded")
fun enableViewInSuccess(view: View, isSuccess: Boolean) {
    if (isSuccess) view.visibility = View.VISIBLE else view.visibility = View.GONE
}


@BindingAdapter("setLikes")
fun setLike(view: TextView, likeCount: Int) {
    view.text = view.context.getString(R.string.total_like, likeCount)
}
