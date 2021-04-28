package com.pickupservices.mypics.ui.gallery

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.pickupservices.mypics.databinding.ActivityGalleryBinding
import com.pickupservices.mypics.domain.model.Photo
import com.pickupservices.mypics.ui.album.ListAlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    // Adapter for managing album photos
    private lateinit var viewAdapter: GalleryAdapter

    // View binding for calling UI elements
    private lateinit var binding: ActivityGalleryBinding

    private val viewModel: GalleryViewModel by viewModels()

    companion object {
        const val ID_ALBUM = "id_album"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the current id album
        val idExtraAlbum = intent.getIntExtra(ID_ALBUM, 0)
        viewModel.idAlbum = idExtraAlbum

        // set the AppBar back arrow
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpAdapter()

        // load photos for the corresponding album
        viewModel.loadPhotos()

        viewModel.listPhoto.observe(this, {
            viewAdapter.listPhotos = it
        })

        viewModel.isLoading.observe(this, {
            binding.activityGalleryProgressBar.visibility =
                viewModel.progressBarVisibility()
        })

        viewModel.isError.observe(this, {
            binding.activityGalleryErrorMessage.visibility =
                viewModel.errorMessageVisibility()
        })
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
}