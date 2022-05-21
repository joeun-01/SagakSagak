package com.songilcraft.sagak_sagak.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.songilcraft.sagak_sagak.config.BaseViewModel
import com.songilcraft.sagak_sagak.data.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val repository = HomeRepository()

    private val _getPostResult = MutableLiveData<Int>()
    val getPostResult : LiveData<Int> get() = _getPostResult

    private var postData = ArrayList<ArrayList<Ticket>>()

    private var userIdx = 0

    fun tryGetPost(userIdx : Int) {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val result = repository.getPost(userIdx)
            if (result.code == 1000) {
                postData = result.result
            }
            _getPostResult.postValue(result.code)
        }
    }

    fun getPostData() = postData
}