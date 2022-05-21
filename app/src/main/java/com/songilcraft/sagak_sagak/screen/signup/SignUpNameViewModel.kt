package com.songilcraft.sagak_sagak.screen.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.songilcraft.sagak_sagak.screen.signup.models.SignUpService
import com.songilcraft.sagak_sagak.screen.signup.models.SignUpView

class SignUpNameViewModel : ViewModel(), SignUpView {
    private var user : User = User("", "", "")

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess : LiveData<Boolean> get() = _signUpSuccess

    fun saveUserName(userInfo : User) {
        user.id = userInfo.id
        user.password = userInfo.password
        user.name = userInfo.name

        Log.d("check", user.toString())
    }

    fun signUp() {  // 네트워크를 이용하여 회원가입 진행
        val authService = SignUpService()

        authService.setSignUpView(this)
        authService.signUp(user)
    }

    override fun onSignUpSuccess(message: String) {
        _signUpSuccess.postValue(true)
        Log.d("success : ", message)
    }

    override fun onSignUpFailure(message: String) {
        _signUpSuccess.postValue(false)
        Log.d("failure : ", message)
    }
}