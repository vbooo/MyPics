package com.pickupservices.mypics.ui.gallery

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.pickupservices.mypics.databinding.ActivityGalleryBinding
import com.pickupservices.mypics.domain.model.Photo


class GalleryActivity : AppCompatActivity() {

    // Adapter for managing album photos
    private lateinit var viewAdapter: GalleryAdapter

    // View binding for calling UI elements
    private lateinit var binding: ActivityGalleryBinding

    companion object {
        const val ID_ALBUM = "id_album"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpAdapter()
    }

    private fun setUpAdapter() {

        viewAdapter = GalleryAdapter(
            this
        )

        // Set our recycler view which will contain our Album list
        binding.activityGalleryRecyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = GridLayoutManager(context, 3)
            adapter = viewAdapter
        }

        viewAdapter.listPhotos = getPhotos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getPhotos(): List<Photo> {
        return listOf(
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
            Photo(1, 1, "http://i.imgur.com/DvpvklR.png"),
        )
    }
}