package com.songilcraft.sagak_sagak.screen.signup.models

import android.util.Log
import com.songilcraft.sagak_sagak.screen.signup.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService {
    private lateinit var signUpView: SignUpView

    fun setSignUpView(signUpView : SignUpView) {
        this.signUpView = signUpView
    }

    fun signUp(user: User) {
        val authService = getRetrofit().create(SignUpRetrofitInterface::class.java)

        authService.signUp(user).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                // 응답이 왔을 때 처리
                Log.d("SIGNUP / SUCCESS", response.toString())

                val resp : SignUpResponse = response.body()!!

                when(resp.code) {
                    1000 -> signUpView.onSignUpSuccess(resp.message)  // 회원가입 성공
                    else -> signUpView.onSignUpFailure(resp.message)
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // 네트워크 연결이 실패했을 때 실행
                Log.d("SIGNUP / FAILURE", t.message.toString())
            }

        })  // 유저 정보를 넣어주면서 api 호출, eunqueue에서 응답 처리

        Log.d("SIGNUP", "HELLO")
    }
}