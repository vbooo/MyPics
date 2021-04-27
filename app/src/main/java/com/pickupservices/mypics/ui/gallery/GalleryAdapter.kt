package com.pickupservices.mypics.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.R
import com.pickupservices.mypics.domain.model.Photo
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * This adapter handles album photos
 */
internal class GalleryAdapter(
    @ApplicationContext val context: Context
    ) : RecyclerView.Adapter<GalleryHolder>() {

    // This list contains all the album photos
    var listPhotos: List<Photo>? = null
        set(value) {
            value?.let {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return GalleryHolder(view, context)
    }

    override fun getItemCount(): Int {
        return listPhotos?.size ?: 0
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {

        listPhotos?.let { list ->

            list[position].thumbnailUrl.let {
                holder.setUrlPhoto(it)
            }
        }
    }
}

