package com.pickupservices.mypics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pickupservices.mypics.R
import com.pickupservices.mypics.databinding.ActivityListAlbumBinding
import com.pickupservices.mypics.domain.model.Album

class ListAlbumActivity : AppCompatActivity() {

    // View binding for calling UI elements
    private lateinit var binding: ActivityListAlbumBinding

    // Adapter for managing albums list
    private lateinit var viewAdapter: ListAlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        setUpAdapter()

        viewAdapter.listAlbum = getList()

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

    /**
     * TODO temporary function for getting list of albums [TO REMOVE]
     */
    private fun getList(): List<Album> {
        return listOf(
            Album("Album 1", "Jean"),
            Album("Album 2", "Michel"),
            Album("Album 3", "Kevin"),
            Album("Album 4", "Martin"),
            Album("Album 5", "Jean"),
            Album("Album 6", "Jean"),
            Album("Album 7", "Michel"),
            Album("Album 8", "Jean"),
            Album("Album 9", "Kevin"),
            Album("Album 10", "Jean"),
            Album("Album 11", "Jacques"),
        )
    }
}