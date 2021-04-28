package com.pickupservices.mypics.ui.gallery

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Photo
import com.pickupservices.mypics.domain.usecase.GetPhotosByAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel  @Inject constructor(
    val getPhotosByAlbumUseCase: GetPhotosByAlbumUseCase
) : ViewModel() {

    // The Photo list
    val listPhoto: MutableLiveData<List<Photo>> = MutableLiveData()

    // The current album id
    var idAlbum: Int = 0

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val isError: MutableLiveData<Boolean> = MutableLiveData(false)

    fun loadPhotos() {
        viewModelScope.launch {
            getPhotosByAlbumUseCase(idAlbum).collect {
                when (it)  {
                    is Result.Loading ->  isLoading.postValue(true)
                    is Result.Error ->  {
                        isError.postValue(true)
                        isLoading.postValue(false)
                    }

                    is Result.Success ->  {
                        listPhoto.postValue(it.data)
                        isLoading.postValue(false)
                        if (it.data.isNullOrEmpty()) {
                            isError.postValue(true)
                        } else {
                            isError.postValue(false)
                        }
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