package com.pickupservices.mypics.ui.gallery

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

internal class GalleryHolder(
    private var view: View,
    val context: Context
    ) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemPhotoBinding.bind(view)

    fun setUrlPhoto(url: String) {
        Picasso.get().load(url).into(binding.itemPhoto)
    }
}