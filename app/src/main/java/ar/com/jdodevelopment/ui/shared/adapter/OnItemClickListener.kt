package ar.com.jdodevelopment.ui.shared.adapter

import android.view.View


class OnItemClickListener<T>(val onItemClickListener: (view: View, item: T) -> Unit) {
    fun onItemClick(view: View, item: T) = onItemClickListener(view, item)
}
