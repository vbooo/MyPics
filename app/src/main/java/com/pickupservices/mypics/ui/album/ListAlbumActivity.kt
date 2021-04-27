package com.pickupservices.mypics.ui.album

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.pickupservices.mypics.R
import com.pickupservices.mypics.databinding.ActivityListAlbumBinding
import com.pickupservices.mypics.ui.gallery.GalleryActivity
import com.pickupservices.mypics.ui.gallery.GalleryActivity.Companion.ID_ALBUM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAlbumActivity : AppCompatActivity() {

    // View binding for calling UI elements
    private lateinit var binding: ActivityListAlbumBinding

    // Adapter for managing albums list
    private lateinit var viewAdapter: ListAlbumAdapter

    // ViewModel for managing UI related data
    private val viewModel: ListAlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        setUpAdapter()

        // We observe the value of listAlbum and update it when the data changes
        viewModel.listAlbum.observe(this, {
            viewAdapter.listAlbum = it
        })

        viewModel.isLoading.observe(this, {
            binding.scrollViewAlbumActivity.contentScrollingProgressBar.visibility =
                viewModel.progressBarVisibility()
        })

        viewModel.isError.observe(this, {
            binding.scrollViewAlbumActivity.contentScrollingErrorMessage.visibility =
                viewModel.errorMessageVisibility()
        })
    }

    private fun setUpAdapter() {

        val listener = object: OnClickedItemListener {
            override fun onItemClicked(idAlbum: Int) {
                val intent = Intent(baseContext, GalleryActivity::class.java)
                intent.putExtra(ID_ALBUM, idAlbum)
                startActivity(intent)
            }
        }

        viewAdapter = ListAlbumAdapter(
            this,
            listener
        )

        // Set our recycler view which will contain our Album list
        binding.scrollViewAlbumActivity.contentScrollingRecyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = viewAdapter
            val dividerItemDecoration = DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
            this.addItemDecoration(dividerItemDecoration)
        }
    }
}