package ar.com.jdodevelopment.util.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageViewAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageLoader(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        } ?: let {
            imageView.setImageDrawable(null)
        }
    }

}