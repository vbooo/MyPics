package com.pickupservices.mypics.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.R
import com.pickupservices.mypics.databinding.ItemAlbumBinding

internal class AlbumHolder(
    private var view: View,
    val context: Context
    ) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemAlbumBinding.bind(view)

    fun setAlbumName(value: String) {
        binding.itemAlbumName.text = value
    }

    fun setAuthorName(value: String?) {
        value?.let {
            binding.itemAlbumAuthor.text = value
        } ?: run {
            binding.itemAlbumAuthor.text = context.resources.getString(R.string.unknownAuthorName)
        }
    }
}