package com.example.tvshowsapp.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide


fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}



fun ImageView.setImageSrcFromUrl(url: String?, placeHolder: Int, context: Context) {
    Glide.with(context).load(url).placeholder(placeHolder)
        .into(this)
}

