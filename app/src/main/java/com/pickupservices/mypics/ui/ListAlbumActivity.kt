package com.pickupservices.mypics.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.pickupservices.mypics.R
import com.pickupservices.mypics.databinding.ActivityListAlbumBinding

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

    }

    private fun setUpAdapter() {
        viewAdapter = ListAlbumAdapter(this)

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