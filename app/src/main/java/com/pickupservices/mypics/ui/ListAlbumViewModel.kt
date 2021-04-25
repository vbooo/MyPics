package com.pickupservices.mypics.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pickupservices.mypics.domain.model.Album

/**
 * This ViewModel handles [ListAlbumActivity] data
 */
class ListAlbumViewModel : ViewModel() {

    // The Album list
    val listAlbum: MutableLiveData<List<Album>> = MutableLiveData()

    init {
        listAlbum.value = getList()
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