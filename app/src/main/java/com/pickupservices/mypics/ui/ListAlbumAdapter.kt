package com.pickupservices.mypics.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.R
import com.pickupservices.mypics.domain.model.Album
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * This adapter handles albums list data
 */
internal class ListAlbumAdapter(@ApplicationContext val context: Context) :
    RecyclerView.Adapter<AlbumHolder>() {

    // This list contains all the Album
    var listAlbum: List<Album>? = null
        set(value) {
            value?.let {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumHolder(view)
    }

    override fun getItemCount(): Int {
        return listAlbum?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {

        listAlbum?.let { list ->

            list[position].nameAlbum.let {
                holder.setAlbumName(it)
            }

            list[position].nameAuthor.let {
                holder.setAlbumName(it)
            }
        }
    }
}
