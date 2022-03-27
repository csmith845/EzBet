package com.cooper.android.easybet.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cooper.android.easybet.Wallet

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

        value = "$" + Wallet.balance().toString()
    }
    val text: LiveData<String> = _text
}