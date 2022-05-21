package com.songilcraft.sagak_sagak.screen.signup.models

interface SignUpView {  // activity와 AuthService를 연결
    fun onSignUpSuccess(message: String)
    fun onSignUpFailure(message: String)
}