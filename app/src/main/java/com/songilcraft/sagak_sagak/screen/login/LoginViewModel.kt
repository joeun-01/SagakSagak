package com.songilcraft.sagak_sagak.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.songilcraft.sagak_sagak.config.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel(){

    private val repository = LoginRepository()

    var id : String = ""
    var password : String = ""
    var jwt = ""
    var userIdx = 0

    private val _loginResult = MutableLiveData<Int>()
    val loginResult : LiveData<Int> get() = _loginResult

    fun tryLogin() {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val result = repository.doLogin(id, password)
            Log.d("testing", "${result.code}")
            if (result.code == 1000) {
                jwt = result.result.jwt
                userIdx = result.result.userIdx
            }

            _loginResult.postValue(result.code)
        }
    }

}