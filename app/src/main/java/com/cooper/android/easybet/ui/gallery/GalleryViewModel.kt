package com.cooper.android.easybet.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cooper.android.easybet.ui.gallery.GalleryFragment
class GalleryViewModel : ViewModel() {
    var wallet = Wallet()
    private val _text = MutableLiveData<String>().apply {

        value = wallet.balance().toString()
    }
    val text: LiveData<String> = _text
}