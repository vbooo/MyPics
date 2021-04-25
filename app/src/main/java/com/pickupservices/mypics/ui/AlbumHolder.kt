package com.pickupservices.mypics.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.databinding.ItemAlbumBinding

internal class AlbumHolder(private var view: View) :
    RecyclerView.ViewHolder(view) {

    val binding = ItemAlbumBinding.bind(view)

    fun setAlbumName(value: String) {
        binding.itemAlbumName.text = value
    }

    fun setAuthorName(value: String) {
        binding.itemAlbumAuthor.text = value
    }
}