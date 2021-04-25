package com.pickupservices.mypics.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickupservices.mypics.domain.usecase.FunctionalAlbum
import com.pickupservices.mypics.domain.usecase.GetAllAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.pickupservices.mypics.domain.Result

/**
 * This ViewModel handles [ListAlbumActivity] data
 */
@HiltViewModel
class ListAlbumViewModel @Inject constructor(
    val getAllAlbumsUseCase: GetAllAlbumsUseCase
) : ViewModel() {

    // The Album list
    val listAlbum: MutableLiveData<List<FunctionalAlbum>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val isError: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        viewModelScope.launch {
            getAllAlbumsUseCase(Unit).collect {
                when (it)  {
                    is Result.Loading ->  isLoading.postValue(true)
                    is Result.Error ->  {
                        isError.postValue(true)
                        isLoading.postValue(false)
                    }

                    is Result.Success ->  {
                        listAlbum.postValue(it.data)
                        isLoading.postValue(false)
                        isError.postValue(false)
                    }
                }
            }
        }
    }

    fun progressBarVisibility(): Int {
        return if (isLoading.value == true) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun errorMessageVisibility(): Int {
        return if (isError.value == true) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}