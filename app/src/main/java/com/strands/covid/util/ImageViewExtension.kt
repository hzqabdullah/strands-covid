package com.strands.covid.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.strands.covid.R

private fun createListener(
    imageView: ImageView,
    onSuccess: () -> Unit,
    onFailed: (error: Throwable?) -> Unit
): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onFailed(e)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            imageView.setImageDrawable(resource)
            onSuccess()
            return true
        }
    }
}

@SuppressLint("CheckResult")
fun ImageView.loadImageUrl(
    context: Context,
    imageUrlPath: String?,
    placeholder: Int = R.drawable.ic_placeholder,
    onSuccess: (() -> Unit)? = null,
    onFailed: ((error: Throwable?) -> Unit)? = null
) = silence {
    val builder = Glide.with(context.applicationContext)
        .load(imageUrlPath)
        .placeholder(placeholder)

    if (onSuccess != null && onFailed != null) {
        builder.addListener(
            createListener(
                imageView = this,
                onSuccess = onSuccess,
                onFailed = onFailed
            )
        )
    }

    builder.into(this)
}
