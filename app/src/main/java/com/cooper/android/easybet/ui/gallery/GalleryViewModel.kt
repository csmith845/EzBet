package com.cooper.android.easybet.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cooper.android.easybet.ui.WalletRepository
import android.os.Handler
import android.os.Looper

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val bal = WalletRepository.getBal()
        val handler = Handler(Looper.getMainLooper())
        value = bal.toString()+ "EZ"



    }
    val text: LiveData<String> = _text
}