package com.pickupservices.mypics.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickupservices.mypics.domain.data
import com.pickupservices.mypics.domain.model.Album
import com.pickupservices.mypics.domain.successOr
import com.pickupservices.mypics.domain.usecase.GetAllAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This ViewModel handles [ListAlbumActivity] data
 */
@HiltViewModel
class ListAlbumViewModel @Inject constructor(
    val getAllAlbumsUseCase: GetAllAlbumsUseCase
) : ViewModel() {

    // The Album list
    val listAlbum: MutableLiveData<List<Album>> = MutableLiveData()

    init {
        viewModelScope.launch {
            getAllAlbumsUseCase(Unit).let {
                listAlbum.postValue(it.successOr(null))
            }
        }
    }
}