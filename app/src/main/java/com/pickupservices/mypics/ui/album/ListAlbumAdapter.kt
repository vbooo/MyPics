package com.pickupservices.mypics.ui.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.R
import com.pickupservices.mypics.domain.usecase.FunctionalAlbum
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * This adapter handles albums list data
 */
internal class ListAlbumAdapter(
    @ApplicationContext val context: Context,
    private var clickListener: OnClickedItemListener
    ) : RecyclerView.Adapter<AlbumHolder>() {

    // This list contains all the Album
    var listAlbum: List<FunctionalAlbum>? = null
        set(value) {
            value?.let {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumHolder(view, context)
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
                holder.setAuthorName(it)
            }
        }

        holder.itemView.setOnClickListener {
            listAlbum?.let {
                clickListener.onItemClicked(it[position].id)
            }
        }
    }

}

interface OnClickedItemListener {
    fun onItemClicked(idAlbum: Int)
}
